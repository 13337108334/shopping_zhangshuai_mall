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
    public ResultData<Order> buildParam(Long id) {
        logger.info("AliPayServiceImpl buildParam id:{}", JSON.toJSONString(id));
        if (id == null) {
            logger.error("AliPayServiceImpl buildParam id is null");
            throw new BizException(ErrorCodeAndMessage.MMP_CHECK_INPUT_ID.getStringErrorCode(),ErrorCodeAndMessage.MMP_CHECK_INPUT_ID.getErrorMessage());
        }
        ResultData<Order> resultData = getOrder(id);
        if (resultData == null || resultData.getData() == null || !resultData.isSuccess() ) {
            logger.error("AliPayServiceImpl buildParam order is null");
            throw new BizException(ErrorCodeAndMessage.REMOTE_RESULT_NULL.getStringErrorCode(),ErrorCodeAndMessage.REMOTE_RESULT_NULL.getErrorMessage());
        }
        //todo 阿里订单业务逻辑
        return resultData;
    }

    @Override
    public SourceBizTypeEnum getSourceBizType() {
        return SourceBizTypeEnum.ALIPAY;
    }

}
