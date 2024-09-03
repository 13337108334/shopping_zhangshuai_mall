package com.shanzhu.em.service.orderpay;

import com.shanzhu.em.entity.Order;
import com.shanzhu.em.utils.ResultData;
import com.shanzhu.em.utils.SourceBizTypeEnum;

/**
 * @author zhangshuai
 * @Date 2024/09/03
 */
public interface PayService {

    ResultData<Order> buildParam(SourceBizTypeEnum sourceBizTypeEnum, Long id);

    SourceBizTypeEnum getSourceBizType();

}
