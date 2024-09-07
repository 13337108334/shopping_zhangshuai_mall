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

/**
 * 银行卡支付
 * @author zhangshuai
 * @Date 2024/09/03
 */
@Component
public class TransBankServiceImpl extends AbstractPayService {
    private static final Logger logger = LoggerFactory.getLogger(TransBankServiceImpl.class);

    public static final String ROUTING_KEY_ORDER_WECHAT = "order_transbankpay";

    @Autowired
    private RabbitMqSenderService rabbitMqSenderService;

    @Override
    public ResultData<Order> payOrder(PayTypeEnum payTypeEnum, Long id) {
        if (payTypeEnum == null) {
            logger.error("TransBankServiceImpl payOrder payTypeEnum is null");
            throw new BizException(ErrorCodeAndMessage.MMP_CHECK_INPUT_NULL.getStringErrorCode(), ErrorCodeAndMessage.MMP_CHECK_INPUT_NULL.getErrorMessage());
        }
        if (id == null) {
            logger.error("TransBankServiceImpl payOrder id is null");
            throw new BizException(ErrorCodeAndMessage.MMP_CHECK_INPUT_ID_NULL.getStringErrorCode(), ErrorCodeAndMessage.MMP_CHECK_INPUT_ID_NULL.getErrorMessage());
        }
        logger.info("TransBankServiceImpl payOrder payTypeEnum.value:{}, id:{}", JSON.toJSONString(payTypeEnum.getValue()), JSON.toJSONString(id));
        ResultData<Order> resultData = getOrder(payTypeEnum, id);
        if(resultData == null || !resultData.isSuccess() || resultData.getData() == null) {
            return resultData;
        }
        //todo 银行订单支付对接-待开发
        // 支付成功发送银行订单成功消息 同步到宽表
        rabbitMqSenderService.send(RabbitFanoutExchangeConfig.EXCHANGE, ROUTING_KEY_ORDER_WECHAT, new Message(getJsonObject(payTypeEnum, resultData).toJSONString().getBytes()));
        return resultData;
    }

    @Override
    public PayTypeEnum getSourceBizType() {
        return PayTypeEnum.TRANSBANK;
    }
}
