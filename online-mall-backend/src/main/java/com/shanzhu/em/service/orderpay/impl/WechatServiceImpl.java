package com.shanzhu.em.service.orderpay.impl;

import com.alibaba.fastjson.JSON;
import com.shanzhu.em.entity.Order;
import com.shanzhu.em.utils.PayTypeEnum;
import com.shanzhu.em.service.orderpay.AbstractPayService;
import com.shanzhu.em.service.rabbitmq.RabbitFanoutExchangeConfig;
import com.shanzhu.em.service.rabbitmq.RabbitMqSenderService;
import com.shanzhu.em.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 微信订单支付
 * @author zhangshuai
 * @Date 2024/09/03
 */
@Component
public class WechatServiceImpl extends AbstractPayService {
    private static final Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);

    public static final String ROUTING_KEY_ORDER_WECHAT = "order_wechat";

    @Autowired
    private RabbitMqSenderService rabbitMqSenderService;

    @Override
    public ResultData<Order> buildParam(PayTypeEnum payTypeEnum, Long id) {
        if (payTypeEnum == null) {
            logger.error("WechatServiceImpl buildParam payTypeEnum is null");
            throw new BizException(ErrorCodeAndMessage.MMP_CHECK_INPUT_NULL.getStringErrorCode(), ErrorCodeAndMessage.MMP_CHECK_INPUT_NULL.getErrorMessage());
        }
        if (id == null) {
            logger.error("WechatServiceImpl buildParam id is null");
            throw new BizException(ErrorCodeAndMessage.MMP_CHECK_INPUT_ID_NULL.getStringErrorCode(), ErrorCodeAndMessage.MMP_CHECK_INPUT_ID_NULL.getErrorMessage());
        }
        logger.info("WechatServiceImpl buildParam payTypeEnum.value:{}, id:{}", JSON.toJSONString(payTypeEnum.getValue()), JSON.toJSONString(id));
        //todo 微信订单业务逻辑
        ResultData<Order> resultData = getOrder(payTypeEnum, id);
        // 发送微信订单支付消息
        rabbitMqSenderService.send(RabbitFanoutExchangeConfig.EXCHANGE, ROUTING_KEY_ORDER_WECHAT, new Message(getJsonObject(payTypeEnum, resultData).toJSONString().getBytes()));
        return resultData;
    }

    @Override
    public PayTypeEnum getSourceBizType() {
        return PayTypeEnum.WECHATPAY;
    }
}
