package com.shanzhu.em.service.rabbitmq;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 3次重试/5s重试一次
 * @author zhangshuai
 * @datetime 2022/9/1 16:08
 * @desc 消息接收者
 */

//todo 本地测试需放开注解交给spring管理，上线前放开测试消费,不放开是为了在rabbitmq控制台查看消息
@Component
public class Listener {

    private static final Logger logger = LoggerFactory.getLogger(Listener.class);

    /**
     * 消息接收者 - 用户支付后 异步 更新DB数据库并将数据同步到宽表
     *
     * @param msg,消息体
     * @return
     * @author zhangshuai
     * @datetime 2022/9/1:16:11
     */
    @RabbitListener(queues = "demo.queue")
    public void demoHandle(String msg) throws Exception {
        // 1、转换模型
        logger.info("消息已成功接收 msg body:{}", JSON.toJSONString(msg));
    }

}
