package com.shanzhu.em.service.orderpay.impl;

import com.alibaba.fastjson.JSON;
import com.shanzhu.em.entity.Order;
import com.shanzhu.em.service.orderpay.AbstractPayService;
import com.shanzhu.em.service.rabbitmq.RabbitFanoutExchangeConfig;
import com.shanzhu.em.service.rabbitmq.RabbitMqSenderService;
import com.shanzhu.em.utils.BizException;
import com.shanzhu.em.utils.ErrorCodeAndMessage;
import com.shanzhu.em.utils.PayTypeEnum;
import com.shanzhu.em.utils.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 电子承兑汇票
 * @author zhangshuai
 * @Date 2024/09/03
 */
@Component
public class MyBankEFTServiceImpl extends AbstractPayService {
    private static final Logger logger = LoggerFactory.getLogger(MyBankEFTServiceImpl.class);

    public static final String ROUTING_KEY_ORDER_MYBANKEFTPAY = "order_mybankeftpay";

    @Autowired
    private RabbitMqSenderService rabbitMqSenderService;

    @Override
    public ResultData<Order> payOrder(PayTypeEnum payTypeEnum, Long id) {
        logger.info("----------MyBankEFTServiceImpl payOrder ----------");
        ResultData<Order> resultData = getOrderResultData(payTypeEnum, id);
        //todo 电票订单支付对接-待开发
        // 支付成功发送电票订单成功消息 同步到宽表
        rabbitMqSenderService.send(RabbitFanoutExchangeConfig.EXCHANGE, ROUTING_KEY_ORDER_MYBANKEFTPAY, new Message(getJsonObject(payTypeEnum, resultData).toJSONString().getBytes()));
        return resultData;
    }

    @Override
    public PayTypeEnum getSourceBizType() {
        return PayTypeEnum.MYBANK_EFT;
    }
}
