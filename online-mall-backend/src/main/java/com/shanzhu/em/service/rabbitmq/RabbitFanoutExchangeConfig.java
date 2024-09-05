package com.shanzhu.em.service.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangshuai
 * @datetime 2022/9/1 15:41
 * @desc rabbitmq 配置类
 */
@Configuration
public class RabbitFanoutExchangeConfig {

    /**
     * 交换机初始化
     *
     * @author zhangshuai
     * @datetime 2022/9/1:15:43
     * @return
     */
    public static final String EXCHANGE = "zhangshuai.exchange";

    @Bean(name = EXCHANGE)
    public FanoutExchange demoExchange() {
        return new FanoutExchange(EXCHANGE, true, false);
    }

    /**
     * 队列初始化
     *
     * @author zhangshuai
     * @datetime 2022/9/1:15:43
     * @return
     */
    public static final String QUEUE = "zhangshuai.queue";

    @Bean(name = QUEUE)
    public Queue demoQueue() {
        return new Queue(QUEUE, true, false, false);
    }

    /**
     * 交换机队列绑定
     *
     * @author zhangshuai
     * @datetime 2022/9/1:15:45
     * @param demoQueue 消息队列
     * @param fanoutExchange 扇形交换机
     * @return 返回绑定的对象
     */
    @Bean
    public Binding bindingSimpleQueue1(
            @Qualifier(QUEUE) Queue demoQueue,
            @Qualifier(EXCHANGE) FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(demoQueue).to(fanoutExchange);
    }
}
