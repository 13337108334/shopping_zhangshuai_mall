package com.shanzhu.em.service.orderpay;

import com.shanzhu.em.entity.Order;
import com.shanzhu.em.utils.ErrorCodeEnum;
import com.shanzhu.em.utils.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractPayService implements PayService {

    private static final Logger logger = LoggerFactory.getLogger(AbstractPayService.class);

    @Autowired
    OrderService orderService;

    protected ResultData<Order> getOrder(Long id) {
        try {
            Order order = orderService.getOrder(id);
            ResultData<Order> resultData = new ResultData<>();
            if (order != null) {
                resultData.setSuccess(true);
                resultData.setData(order);
            } else {
                resultData.setData(null);
                resultData.setSuccess(false);
                resultData.setMessage("订单还未付款");
                resultData.setResultCode(ErrorCodeEnum.ORDER_WAIT_PAY.getErrorCode());
            }
            return resultData;
        } catch (Exception e) {
            logger.error("AbstractPayService getOrder error,id:{},e:{}", id, e);
            return null;
        }
    }
}
