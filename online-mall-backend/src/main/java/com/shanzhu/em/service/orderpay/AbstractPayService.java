package com.shanzhu.em.service.orderpay;

import com.alibaba.fastjson.JSON;
import com.shanzhu.em.entity.Order;
import com.shanzhu.em.utils.ErrorCodeAndMessage;
import com.shanzhu.em.utils.ErrorCodeEnum;
import com.shanzhu.em.utils.ResultData;
import com.shanzhu.em.utils.SourceBizTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractPayService implements PayService {

    private static final Logger logger = LoggerFactory.getLogger(AbstractPayService.class);

    @Autowired
    OrderService orderService;

    protected ResultData<Order> getOrder(SourceBizTypeEnum sourceBizTypeEnum,Long id) {
        try {
            Order order = orderService.getOrder(id);
            if(order == null){
                logger.error("AbstractPayService getOrder order is null 订单id为:{}", JSON.toJSONString(id));
                return ResultData.genError(ErrorCodeAndMessage.ORDER_IS_NULL.getStringErrorCode(),ErrorCodeAndMessage.ORDER_IS_NULL.getErrorMessage());
            }
            order.setGoods(sourceBizTypeEnum.getValue());
            ResultData<Order> resultData = ResultData.genSuccess(order);
            logger.info("AbstractPayService getOrder sourceBizTypeEnum.value:{} result:{}", JSON.toJSONString(sourceBizTypeEnum.getValue()),JSON.toJSONString(resultData));
            return resultData;
        } catch (Exception e) {
            logger.error("AbstractPayService getOrder error,id:{},e:{}", id, e);
            return null;
        }
    }
}
