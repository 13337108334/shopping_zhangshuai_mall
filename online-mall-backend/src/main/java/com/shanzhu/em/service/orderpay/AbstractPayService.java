package com.shanzhu.em.service.orderpay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shanzhu.em.entity.Order;

import com.shanzhu.em.sync.ActionTypeContent;
import com.shanzhu.em.utils.BizException;
import com.shanzhu.em.utils.ErrorCodeAndMessage;
import com.shanzhu.em.utils.PayTypeEnum;
import com.shanzhu.em.utils.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
            if (order == null || order.getState() == null) {
                logger.error("AbstractPayService getOrder order is null 订单id为:{},sourceBizTypeEnum:{}", JSON.toJSONString(id), JSON.toJSONString(payTypeEnum));
                return ResultData.genError(ErrorCodeAndMessage.ORDER_IS_NULL.getStringErrorCode(), ErrorCodeAndMessage.ORDER_IS_NULL.getErrorMessage());
            }
            ResultData<Order> resultData = ResultData.genSuccess(order);
            logger.info("AbstractPayService getOrder sourceBizTypeEnum.value:{} result:{}", JSON.toJSONString(payTypeEnum.getValue()), JSON.toJSONString(resultData));
            return resultData;
        } catch (Exception e) {
            logger.error("AbstractPayService getOrder error,id:{},e:{}", id, e);
            return ResultData.genException(ErrorCodeAndMessage.THROW_DB_EXCEPTION.getStringErrorCode(), ErrorCodeAndMessage.THROW_DB_EXCEPTION.getErrorMessage());
        }
    }

    /**
     * 转换消息体为JSONObject
     * @param payTypeEnum 来源类型
     * @param resultData        订单数据
     * @return  消息内容
     */
    protected JSONObject getJsonObject(PayTypeEnum payTypeEnum, ResultData<Order> resultData) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", resultData.getData().getId());
        jsonObject.put("payType", payTypeEnum.getValue());
        jsonObject.put("resultData", resultData);
        jsonObject.put("status",resultData.getData().getState());
        jsonObject.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:ms").format(new Date()));
        // 同步宽表行动点-插入
        jsonObject.put("actionType", ActionTypeContent.INSERT);
        return jsonObject;
    }

    protected ResultData<Order> getOrderResultData(PayTypeEnum payTypeEnum, Long id) {
        if (payTypeEnum == null) {
            logger.error("getOrderResultData payOrder payTypeEnum is null");
            throw new BizException(ErrorCodeAndMessage.MMP_CHECK_INPUT_NULL.getStringErrorCode(), ErrorCodeAndMessage.MMP_CHECK_INPUT_NULL.getErrorMessage());
        }
        if (id == null) {
            logger.error("getOrderResultData payOrder id is null");
            throw new BizException(ErrorCodeAndMessage.MMP_CHECK_INPUT_ID_NULL.getStringErrorCode(), ErrorCodeAndMessage.MMP_CHECK_INPUT_ID_NULL.getErrorMessage());
        }
        logger.info("getOrderResultData payOrder payTypeEnum.value:{}, id:{}", JSON.toJSONString(payTypeEnum.getValue()), JSON.toJSONString(id));
        ResultData<Order> resultData = getOrder(payTypeEnum, id);
        if(resultData == null || !resultData.isSuccess() || resultData.getData() == null) {
            throw new BizException(ErrorCodeAndMessage.ORDER_IS_NULL.getStringErrorCode(), ErrorCodeAndMessage.ORDER_IS_NULL.getErrorMessage());
        }
        if(StringUtils.isEmpty(resultData.getData().getState())) {
            throw new BizException(ErrorCodeAndMessage.ORDER_STATE_IS_NULL.getStringErrorCode(), ErrorCodeAndMessage.ORDER_STATE_IS_NULL.getErrorMessage());
        }
        if ("已支付".equals(resultData.getData().getState())) {
            logger.error("getOrderResultData getOrder order state 订单id为:{},status:{} desc:{}", JSON.toJSONString(id), JSON.toJSONString(resultData.getData().getState()),"该订单已支付 无需再次支付");
            return ResultData.genError(ErrorCodeAndMessage.ORDER_IS_ALREADY_PAY.getStringErrorCode(), ErrorCodeAndMessage.ORDER_IS_ALREADY_PAY.getErrorMessage());
        }
        return resultData;
    }

}
