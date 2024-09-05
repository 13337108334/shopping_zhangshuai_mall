package com.shanzhu.em.service.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.shanzhu.em.service.orderpay.impl.AliPayServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author zhangshuai
 * @datetime 2022/9/1 15:59
 * @desc rabbitmq发送者服务
 */
@Component
public class RabbitMqSenderService {

  private static final Logger logger = LoggerFactory.getLogger(RabbitMqSenderService.class);

  @Autowired private RabbitTemplate rabbitTemplate;
  /**
   * 消息发送者
   *
   * @author zhangshuai
   * @datetime 2022/9/1:16:06
   * @param exchange 交换机
   * @param routingKey 路由键值
   * @param message 消息信息
   * @return
   */
  public void send(String exchange, String routingKey, Message message) {
    CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
    logger.info("开始发送消息 exchange:{},routingKey:{},message:{}", JSON.toJSONString(exchange),JSON.toJSONString(routingKey),JSON.toJSONString(message));
    rabbitTemplate.convertAndSend(exchange, routingKey, message, correlationId);
    logger.info("结束发送消息 message:{}", JSON.toJSONString(message));
  }
}
