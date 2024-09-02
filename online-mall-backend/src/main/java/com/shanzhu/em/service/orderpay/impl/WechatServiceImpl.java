package com.shanzhu.em.service.orderpay.impl;

import com.alibaba.fastjson.JSON;
import com.shanzhu.em.entity.Order;
import com.shanzhu.em.service.orderpay.AbstractPayService;
import com.shanzhu.em.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class WechatServiceImpl extends AbstractPayService {
    private static final Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);

    @Override
    public ResultData<Order> buildParam(Long id) {
        logger.info("WechatServiceImpl buildParam id:{}", JSON.toJSONString(id));
        if (id == null) {
            logger.error("WechatServiceImpl buildParam id is null");
            throw new BizException(ErrorCodeAndMessage.MMP_CHECK_INPUT_ID.getStringErrorCode(),ErrorCodeAndMessage.MMP_CHECK_INPUT_ID.getErrorMessage());
        }
        ResultData<Order> order = getOrder(id);
        if (order == null || !order.isSuccess() ) {
            logger.error("WechatServiceImpl buildParam order is null");
            throw new BizException(ErrorCodeAndMessage.REMOTE_RESULT_NULL.getStringErrorCode(),ErrorCodeAndMessage.REMOTE_RESULT_NULL.getErrorMessage());
        }
        //todo 微信订单业务逻辑
        return order;
    }

    @Override
    public SourceBizTypeEnum getSourceBizType() {
        return SourceBizTypeEnum.WECHATPAY;
    }
}
