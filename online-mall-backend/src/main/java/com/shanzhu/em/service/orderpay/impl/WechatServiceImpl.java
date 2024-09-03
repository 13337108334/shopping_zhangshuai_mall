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
    public ResultData<Order> buildParam(SourceBizTypeEnum sourceBizTypeEnum, Long id) {
        if (sourceBizTypeEnum == null) {
            logger.error("WechatServiceImpl buildParam sourceBizTypeEnum is null");
            throw new BizException(ErrorCodeAndMessage.MMP_CHECK_INPUT_NULL.getStringErrorCode(), ErrorCodeAndMessage.MMP_CHECK_INPUT_NULL.getErrorMessage());
        }
        if (id == null) {
            logger.error("WechatServiceImpl buildParam id is null");
            throw new BizException(ErrorCodeAndMessage.MMP_CHECK_INPUT_ID.getStringErrorCode(), ErrorCodeAndMessage.MMP_CHECK_INPUT_ID.getErrorMessage());
        }
        logger.info("WechatServiceImpl buildParam sourceBizTypeEnum.value:{}, id:{}", JSON.toJSONString(sourceBizTypeEnum.getValue()), JSON.toJSONString(id));
        //todo 微信订单业务逻辑
        return getOrder(sourceBizTypeEnum, id);
    }

    @Override
    public SourceBizTypeEnum getSourceBizType() {
        return SourceBizTypeEnum.WECHATPAY;
    }
}
