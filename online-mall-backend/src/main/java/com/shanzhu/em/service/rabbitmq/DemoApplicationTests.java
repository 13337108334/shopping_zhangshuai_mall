package com.shanzhu.em.service.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

@ActiveProfiles("application.yml")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

  @Autowired
  private RabbitMqSenderService rabbitMqSenderService;

  @Test
  public void testRabbitMq() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("name", "豫章故郡 洪都新府 星分翼轸 地接衡庐 襟三江而带五湖 控蛮荆而引瓯越");
    jsonObject.put("age", JSON.toJSONString(18));
    jsonObject.put("address", "江西省南昌市");
    jsonObject.put("time", JSON.toJSONString(new Date(System.currentTimeMillis())));
    Message message = new Message(jsonObject.toJSONString().getBytes());
    rabbitMqSenderService.send(RabbitFanoutExchangeConfig.EXCHANGE, "test", message);
  }
}
