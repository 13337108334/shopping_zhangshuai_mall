package com.shanzhu.em.service.orderpay;

import com.shanzhu.em.entity.Order;
import com.shanzhu.em.utils.PayTypeEnum;
import com.shanzhu.em.utils.ResultData;

/**
 * 支付接口
 * @author zhangshuai
 * @Date 2024/09/03
 */
public interface PayService {

    ResultData<Order> buildParam(PayTypeEnum payTypeEnum, Long id);

    PayTypeEnum getSourceBizType();

}
