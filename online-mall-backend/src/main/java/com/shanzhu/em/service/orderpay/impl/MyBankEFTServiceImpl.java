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
        if (payTypeEnum == null) {
            logger.error("MyBankEFTServiceImpl payOrder payTypeEnum is null");
            throw new BizException(ErrorCodeAndMessage.MMP_CHECK_INPUT_NULL.getStringErrorCode(), ErrorCodeAndMessage.MMP_CHECK_INPUT_NULL.getErrorMessage());
        }
        if (id == null) {
            logger.error("MyBankEFTServiceImpl payOrder id is null");
            throw new BizException(ErrorCodeAndMessage.MMP_CHECK_INPUT_ID_NULL.getStringErrorCode(), ErrorCodeAndMessage.MMP_CHECK_INPUT_ID_NULL.getErrorMessage());
        }
        logger.info("MyBankEFTServiceImpl payOrder payTypeEnum.value:{}, id:{}", JSON.toJSONString(payTypeEnum.getValue()), JSON.toJSONString(id));
        ResultData<Order> resultData = getOrder(payTypeEnum, id);
        if(resultData == null || !resultData.isSuccess() || resultData.getData() == null) {
            throw new BizException(ErrorCodeAndMessage.ORDER_IS_NULL.getStringErrorCode(), ErrorCodeAndMessage.ORDER_IS_NULL.getErrorMessage());
        }
        if(StringUtils.isEmpty(resultData.getData().getState())) {
            throw new BizException(ErrorCodeAndMessage.ORDER_STATE_IS_NULL.getStringErrorCode(), ErrorCodeAndMessage.ORDER_STATE_IS_NULL.getErrorMessage());
        }
        if ("已支付".equals(resultData.getData().getState())) {
            logger.error("MyBankEFTServiceImpl getOrder order state 订单id为:{},status:{} desc:{}", JSON.toJSONString(id), JSON.toJSONString(resultData.getData().getState()),"该订单已支付 无需再次支付");
            return ResultData.genError(ErrorCodeAndMessage.ORDER_IS_ALREADY_PAY.getStringErrorCode(), ErrorCodeAndMessage.ORDER_IS_ALREADY_PAY.getErrorMessage());
        }
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
