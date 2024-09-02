package com.shanzhu.em.service.orderpay;

import com.shanzhu.em.entity.Order;
import com.shanzhu.em.utils.ResultData;
import com.shanzhu.em.utils.SourceBizTypeEnum;

public interface PayService {

    ResultData<Order> buildParam(Long id);

    SourceBizTypeEnum getSourceBizType();

}
