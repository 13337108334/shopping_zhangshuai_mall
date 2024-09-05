package com.shanzhu.em.service.orderpay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shanzhu.em.entity.Order;
import com.shanzhu.em.utils.ErrorCodeAndMessage;
import com.shanzhu.em.utils.PayTypeEnum;
import com.shanzhu.em.utils.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 策略模式-抽象层
 *
 * @author zhangshuai
 * @Date 2024/09/03
 */
@Component
public abstract class AbstractPayService implements PayService {

    private static final Logger logger = LoggerFactory.getLogger(AbstractPayService.class);

    @Autowired
    OrderService orderService;

    protected ResultData<Order> getOrder(PayTypeEnum payTypeEnum, Long id) {
        try {
            Order order = orderService.getOrder(id);
            if (order == null) {
                logger.error("AbstractPayService getOrder order is null 订单id为:{},sourceBizTypeEnum:{}", JSON.toJSONString(id), JSON.toJSONString(payTypeEnum));
                return ResultData.genError(ErrorCodeAndMessage.ORDER_IS_NULL.getStringErrorCode(), ErrorCodeAndMessage.ORDER_IS_NULL.getErrorMessage());
            }
            // 植入来源类型在model的payType字段，方便通过返回值看来源类型
            order.setPayType(payTypeEnum.getValue());
            ResultData<Order> resultData = ResultData.genSuccess(order);
            logger.info("AbstractPayService getOrder sourceBizTypeEnum.value:{} result:{}", JSON.toJSONString(payTypeEnum.getValue()), JSON.toJSONString(resultData));
            return resultData;
        } catch (Exception e) {
            logger.error("AbstractPayService getOrder error,id:{},e:{}", id, e);
            return ResultData.genException(ErrorCodeAndMessage.THROW_DB_EXCEPTION.getStringErrorCode(), ErrorCodeAndMessage.THROW_DB_EXCEPTION.getErrorMessage());
        }
    }

    /**
     *
     * @param payTypeEnum 来源类型
     * @param resultData        订单数据
     * @return  消息内容
     */
    protected JSONObject getJsonObject(PayTypeEnum payTypeEnum, ResultData<Order> resultData) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", resultData.getData().getId());
        jsonObject.put("payType", payTypeEnum.getValue());
        jsonObject.put("resultData", resultData);
        jsonObject.put("status", "订单已支付");
        jsonObject.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return jsonObject;
    }

}
