package com.shanzhu.em.service.util;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;


/**
 * 异常类
 */
@Component
public class ExceptionHelper {
	
	private static final Logger LOG = LoggerFactory.getLogger(ExceptionHelper.class);

	@Autowired
	private MessageSource messageSource;


	/**
	 * 抛出业务异常
	 * @param desc     目标日志对象，如果不传打印到默认日志里  
	 * @param method   异常方法
	 * @param request  请求参数
	 * @param errorCode 错误代码
	 * @param messageTextParamters  错误代码配置文件里对应的错误消息参数对象  aMessage = "用户:{0}密码错误"  传入 “张三” 字符串代替 {0}
	 */
	public BizException newBizException(Logger desc, String method, Object request, String errorCode, Throwable parent, Object ...messageTextParamters) {
	    String errorMessage = messageSource.getMessage(errorCode, messageTextParamters, errorCode, null);
	    String logPrefix = String.format("meet errcode:{%s} when call:{%s}", errorCode, method, JSON.toJSONString(request));
	    BizException t =  new BizException(errorCode, errorMessage, parent);
        if (desc != null) {
            desc.error(String.format("%s, exception:%s", logPrefix, errorMessage),t);
        }else {
            LOG.error(String.format("%s, exception:%s", logPrefix, errorMessage),t);
        }
        return t;
	}
	
	   /**
     * 抛出业务异常
     * @param desc     目标日志对象，如果不传打印到默认日志里
     * @param method   异常方法
     * @param request  请求参数
     * @param errorCode 错误代码
     * @param messageTextParamters  错误代码配置文件里对应的错误消息参数对象  aMessage = "用户:{0}密码错误"  传入 “张三” 字符串代替 {0}
     */
    public BizException newBizException(Logger desc, String method, Object request, ErrorCodeEnum errorCode, Throwable parent, Object ...messageTextParamters) {
		String errorMessage = null;
		errorMessage = messageSource.getMessage(errorCode.getErrorCode(), messageTextParamters, errorCode.getErrorCode(), null);
		String logPrefix = String.format("meet errcode:{%s} when call:{%s},requstParam:{%s}", errorCode, method, JSON.toJSONString(request));
        BizException t =  new BizException(errorCode.getErrorCode(), errorMessage, parent);
        if (desc != null) {
            desc.error(String.format("%s, exception:%s", logPrefix, errorMessage),t);
        }else {
            LOG.error(String.format("%s, exception:%s", logPrefix, errorMessage),t);
        }
        return t;
    }

	public BizException newBizException(Logger desc, String method, Object request, ErrorCodeEnum errorCode, String errorMsg) {
		String errorMessage = messageSource.getMessage(errorCode.getErrorCode(), null, errorCode.getErrorCode(), null);
		if (StringUtils.isNotBlank(errorMsg)) {
			errorMessage = errorMessage + "，"+ errorMsg;
		}
		String logPrefix = String.format("meet errcode:{%s} when call:{%s},requstParam:{%s}", errorCode, method, JSON.toJSONString(request));
		BizException t =  new BizException(errorCode.getErrorCode(), errorMessage);
		if (desc != null) {
			desc.error(String.format("%s, exception:%s", logPrefix, errorMessage),t);
		}else {
			LOG.error(String.format("%s, exception:%s", logPrefix, errorMessage),t);
		}
		return t;
	}

	///**
	// * 抛出业务异常
	// * @param desc     目标日志对象，如果不传打印到默认日志里
	// * @param method   异常方法
	// * @param request  请求参数
	// * @param errorCode 错误代码
	// * @param messageTextParamters  错误代码配置文件里对应的错误消息参数对象  aMessage = "用户:{0}密码错误"  传入 “张三” 字符串代替 {0}
	// */
	//public BizException newBizException(Logger desc, String method, Object request, ErrorCodeEnum errorCode, BizException parent, Object ...messageTextParamters) {
	//	String errorMessage = null;
	//	errorMessage = messageSource.getMessage(errorCode.getErrorCode(), messageTextParamters, errorCode.getErrorCode(), null);
	//	if (parent != null && StringUtils.isNotBlank(parent.getErrMsg())) {
	//		errorMessage = errorMessage +"|"+ parent.getErrMsg();
	//	}
	//	String logPrefix = String.format("meet errcode:{%s} when call:{%s},requstParam:{%s}, traceid:{%s}", errorCode, method, JSON.toJSONString(request), EagleEye.getTraceId());
	//	BizException t =  new BizException(errorCode.getErrorCode(), errorMessage, parent);
	//	if (desc != null) {
	//		desc.error(String.format("%s, exception:%s", logPrefix, errorMessage),t);
	//	}else {
	//		LOG.error(String.format("%s, exception:%s", logPrefix, errorMessage),t);
	//	}
	//	return t;
	//}


    
    public String getMessage(String errorCode, Object ...messageTextParamters) {
        try {
            return messageSource.getMessage(errorCode, messageTextParamters, null);
        } catch (Exception e) {
            return messageSource.getMessage(ErrorCodeEnum.SYSTEM_ERROR.getErrorCode(), messageTextParamters,null);
        }
    }

    public BizException newBizException(Logger log, String method, Object request, ErrorCodeEnum errorCode) {
        return newBizException(log, method, request, errorCode, null);
    }

    public TechException newTechException(Logger log, String method, String request, ErrorCodeEnum errorCode, String message) {
        return newTechException(log, method, request, errorCode,message);
    }
    
}
