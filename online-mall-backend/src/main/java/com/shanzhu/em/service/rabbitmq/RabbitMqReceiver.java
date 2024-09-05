package com.shanzhu.em.service.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.shanzhu.em.entity.Order;
import com.shanzhu.em.utils.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangshuai
 * @datetime 2022/9/1 16:08
 * @desc 消息接收者
 *
 */

//todo 本地测试需放开注解交给spring管理，上线前放开测试消费,不放开是为了在rabbitmq控制台查看消息
//@Component
public class RabbitMqReceiver {

  private static final Logger logger = LoggerFactory.getLogger(RabbitMqReceiver.class);
  /**
   * 消息接收者
   *
   * @author zhangshuai
   * @datetime 2022/9/1:16:11
   * @param msg 消息体
   * @return
   */
  @RabbitListener(queues = RabbitFanoutExchangeConfig.QUEUE)
  public void receiverLogAll(String msg) {
    // {"address":"江西省南昌市","name":"豫章故郡 洪都新府 星分翼轸 地接衡庐 襟三江而带五湖 控蛮荆而引瓯越","time":1725522598091,"age":18}
    logger.info("消息已成功接收 msg body:{}", JSON.toJSONString(msg));
    Map<String,String> map = new HashMap<>();
    try {
      map = JSON.parseObject(msg, Map.class);
    } catch (Exception e) {
      logger.error("消息已接收 JSON转换map异常, e:{}",e);
      return;
    }
    logger.info("消息消费成功 resultMap:{}", JSON.toJSONString(map));
  }
}
