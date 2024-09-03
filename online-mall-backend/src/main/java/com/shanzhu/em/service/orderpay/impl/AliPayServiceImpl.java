package com.shanzhu.em.service.orderpay.impl;

import com.alibaba.fastjson.JSON;
import com.shanzhu.em.entity.Order;
import com.shanzhu.em.service.orderpay.AbstractPayService;
import com.shanzhu.em.utils.BizException;
import com.shanzhu.em.utils.ErrorCodeAndMessage;
import com.shanzhu.em.utils.ResultData;
import com.shanzhu.em.utils.SourceBizTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AliPayServiceImpl extends AbstractPayService {
    private static final Logger logger = LoggerFactory.getLogger(AliPayServiceImpl.class);


    @Override
    public ResultData<Order> buildParam(SourceBizTypeEnum sourceBizTypeEnum,Long id) {
        if (sourceBizTypeEnum == null) {
            logger.error("AliPayServiceImpl buildParam sourceBizTypeEnum is null");
            throw new BizException(ErrorCodeAndMessage.MMP_CHECK_INPUT_NULL.getStringErrorCode(),ErrorCodeAndMessage.MMP_CHECK_INPUT_NULL.getErrorMessage());
        }
        if (id == null) {
            logger.error("AliPayServiceImpl buildParam id is null");
            throw new BizException(ErrorCodeAndMessage.MMP_CHECK_INPUT_NULL.getStringErrorCode(),ErrorCodeAndMessage.MMP_CHECK_INPUT_NULL.getErrorMessage());
        }
        logger.info("AliPayServiceImpl buildParam sourceBizTypeEnum.value:{}, id:{}", JSON.toJSONString(sourceBizTypeEnum.getValue()),JSON.toJSONString(id));
        //todo 阿里订单业务逻辑
        return getOrder(sourceBizTypeEnum,id);
    }

    @Override
    public SourceBizTypeEnum getSourceBizType() {
        return SourceBizTypeEnum.ALIPAY;
    }

}
