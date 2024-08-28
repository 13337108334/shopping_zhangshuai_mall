package com.shanzhu.em.interceptor;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.shanzhu.em.constants.RedisConstants;
import com.shanzhu.em.constants.Status;
import com.shanzhu.em.controller.UserController;
import com.shanzhu.em.entity.User;

import com.shanzhu.em.utils.BizException;
import com.shanzhu.em.utils.UserHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * jwt拦截器
 *
 * 处理token检验等
 *
 *
 * @author: ZhangDaYe
 * @date: 2024-08-24
 * 用法：
 * 1、preHandle：此方法的作用是在请求进入到Controller进行拦截，有返回值。（返回true则将请求放行进入Controller控制层，false则请求结束返回错误信息）
 * 用法：登录验证（判断用户是否登录）权限验证：判断用户是否有权访问资源（校验token）
 *
 * 2、postHandle：该方法是在Controller控制器执行完成但是还没有返回模板进行渲染拦截。没有返回值。就是Controller----->拦截------>ModelAndView。
 * 用法：因此我们可以将Controller层返回来的参数进行一些修改，它就包含在ModelAndView中，所以该方法多了一个ModelAndView参数。
 *
 * 3、afterCompletion：该方法是在ModelAndView返回给前端渲染后执行。
 * 用法：例如登录的时候，我们经常把用户信息放到ThreadLocal中，为了防止内存泄漏，就需要将其remove掉，该操作就是在这里执行的。
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

    @Resource
    RedisTemplate<String, User> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        //如果不是映射到方法，直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        //验证是否有token
        if(!StringUtils.hasLength(token)){
            throw  new BizException(Status.TOKEN_ERROR,"token失效,请重新登陆");
        }
        //通过token，将redis中的user存到threadlocal（UserHolder）
        User user = redisTemplate.opsForValue().get(RedisConstants.USER_TOKEN_KEY + token);

        if(user == null){
            throw  new BizException(Status.TOKEN_ERROR,"token失效,请重新登陆");
        }
        UserHolder.saveUser(user);
        //重置过期时间
        redisTemplate.expire(RedisConstants.USER_TOKEN_KEY +token, RedisConstants.USER_TOKEN_TTL, TimeUnit.MINUTES);

        //验证token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getUsername())).build();
        try {
            jwtVerifier.verify(token);
        }catch (JWTVerificationException e){
            throw new BizException(Status.TOKEN_ERROR,"token验证失败，请重新登陆");
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception e) throws Exception{
        try {
            UserHolder.removeUser();
        } catch (Exception exception) {
            log.error("afterCompletion ThreadLocal removeUser exception:{}",
                   JSON.toJSONString(exception));
        }
        log.error("Controller----->拦截------>ModelAndView afterCompletion ThreadLocal removeUser 防止内存溢出");
    }
}
