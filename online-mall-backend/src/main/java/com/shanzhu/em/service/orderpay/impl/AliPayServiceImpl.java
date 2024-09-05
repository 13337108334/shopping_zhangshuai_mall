package com.shanzhu.em.service.orderpay.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shanzhu.em.entity.Order;
import com.shanzhu.em.service.orderpay.AbstractPayService;
import com.shanzhu.em.service.rabbitmq.RabbitFanoutExchangeConfig;
import com.shanzhu.em.service.rabbitmq.RabbitMqSenderService;
import com.shanzhu.em.utils.BizException;
import com.shanzhu.em.utils.ErrorCodeAndMessage;
import com.shanzhu.em.utils.ResultData;
import com.shanzhu.em.utils.SourceBizTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zhangshuai
 * @Date 2024/09/03
 */
@Component
public class AliPayServiceImpl extends AbstractPayService {

    public static final String ROUTING_KEY_ORDER_AlIPAY = "order_aliPay";

    @Autowired
    private RabbitMqSenderService rabbitMqSenderService;


    private static final Logger logger = LoggerFactory.getLogger(AliPayServiceImpl.class);


    @Override
    public ResultData<Order> buildParam(SourceBizTypeEnum sourceBizTypeEnum, Long id) {
        if (sourceBizTypeEnum == null) {
            logger.error("AliPayServiceImpl buildParam sourceBizTypeEnum is null");
            throw new BizException(ErrorCodeAndMessage.MMP_CHECK_INPUT_NULL.getStringErrorCode(), ErrorCodeAndMessage.MMP_CHECK_INPUT_NULL.getErrorMessage());
        }
        if (id == null) {
            logger.error("AliPayServiceImpl buildParam id is null");
            throw new BizException(ErrorCodeAndMessage.MMP_CHECK_INPUT_NULL.getStringErrorCode(), ErrorCodeAndMessage.MMP_CHECK_INPUT_NULL.getErrorMessage());
        }
        logger.info("AliPayServiceImpl buildParam sourceBizTypeEnum.value:{}, id:{}", JSON.toJSONString(sourceBizTypeEnum.getValue()), JSON.toJSONString(id));
        //todo 阿里订单业务逻辑
        ResultData<Order> resultData = getOrder(sourceBizTypeEnum, id);;
        // 发送阿里订单消息
        rabbitMqSenderService.send(RabbitFanoutExchangeConfig.EXCHANGE, ROUTING_KEY_ORDER_AlIPAY, new Message(getJsonObject(sourceBizTypeEnum, resultData).toJSONString().getBytes()));
        return resultData;
    }

    @Override
    public SourceBizTypeEnum getSourceBizType() {
        return SourceBizTypeEnum.ALIPAY;
    }

}
