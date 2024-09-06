package com.shanzhu.em.sync;

import com.alibaba.fastjson.JSON;
import com.shanzhu.em.entity.Order;
import com.shanzhu.em.service.orderpay.OrderService;
import com.shanzhu.em.service.rabbitmq.RabbitFanoutExchangeConfig;
import com.shanzhu.em.sync.model.OpenSearchOrderParam;
import com.shanzhu.em.utils.BizException;
import com.shanzhu.em.utils.ErrorCodeAndMessage;
import com.shanzhu.em.utils.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 3次重试/5s重试一次
 * @author zhangshuai
 * @datetime 2022/9/1 16:08
 * @desc 消息接收者
 */

//todo 本地测试需放开注解交给spring管理，上线前放开测试消费,不放开是为了在rabbitmq控制台查看消息
@Component
public class OrderDBSyncOpenSearchHandler {

    @Autowired
    private OrderService orderService;

    private static final Logger logger = LoggerFactory.getLogger(OrderDBSyncOpenSearchHandler.class);

    /**
     * 消息接收者 - 用户支付后 异步 更新DB数据库并将数据同步到宽表
     *
     * @param msg,消息体
     * @return
     * @author zhangshuai
     * @datetime 2022/9/1:16:11
     */
    @RabbitListener(queues = RabbitFanoutExchangeConfig.QUEUE)
    @Transactional
    public void SyncOpenSearchReceiverMsgHandle(String msg) throws Exception {
        // 1、转换模型
        logger.info("消息已成功接收 msg body:{}", JSON.toJSONString(msg));
        Map<String, Map<String, String>> map = new HashMap<>();
        ResultData sourceResultData = new ResultData();
        Map<String, String> sourceResultMap;
        try {
            map = JSON.parseObject(msg, Map.class);
            sourceResultMap = map.get("resultData");
            sourceResultData = JSON.parseObject(String.valueOf(sourceResultMap), ResultData.class);
        } catch (Exception e) {
            logger.error("消息消费失败 JSON转换map异常, e:{}", e);
            return;
        }
        ResultData<Order> targetResult = new ResultData<>();
        convertResult(sourceResultData, targetResult);
        Order orderModel = targetResult.getData();

        // 2、校验数据
        if (targetResult == null || !targetResult.isSuccess() || targetResult.getData() == null) {
            logger.error("消息消费失败 , targetResult is null");
            return;
        }

        // 3、更新DB 并同步openSearch宽表
        // 行动点
        String actionType = String.valueOf(map.get("actionType"));
        // 消息发送时间
        String messageCreateTime = String.valueOf(map.get("time"));

        logger.info("actionType:{}", JSON.toJSONString(actionType));
            // 支付成功后更新数据库字段
            dbUpdate(orderModel);
            // 支付成功后根据类型去同步到宽表数据
            // 事务回滚测试
            // orderModel = null ;
            openSearchSynchronize(orderModel, actionType, messageCreateTime);
    }


    public void dbUpdate(Order orderModel) {
        Boolean sign = orderService.updateOrder(orderModel);
        if (!sign) {
            logger.error("操作DB更新订单失败");
            throw new BizException(ErrorCodeAndMessage.UPDATE_ORDER_ERROR.getStringErrorCode(), ErrorCodeAndMessage.UPDATE_ORDER_ERROR.getErrorMessage());
        }
        logger.info("操作DB更新订单成功");
    }


    public void openSearchSynchronize(Order orderModel, String actionType, String messageCreateTime) {
        OpenSearchOrderParam openSearchOrderParam = new OpenSearchOrderParam();
        BeanUtils.copyProperties(orderModel, openSearchOrderParam);
        // 宽表创建时间为当前时间
        openSearchOrderParam.setOpenSearchCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:ms").format(new Date()));
        // 消息发送时间为msg的"time"
        openSearchOrderParam.setMessageCreateTime(messageCreateTime);
        openSearchOrderParam.setActionType(actionType);
        switch (actionType) {
            case ActionTypeContent.INSERT:
                // todo 调用宽表插入接口 插入前校验ID是否存在，存在则更新 不存在则创建-兼容
                logger.info("openSearchSynchronize 调用宽表插入接口 actionType:{}", JSON.toJSONString(actionType));
                logger.info("openSearchSynchronize INSERT 消息消费成功！ openSearchOrderParam：{}", JSON.toJSONString(openSearchOrderParam));
                break;
            case ActionTypeContent.UPDATE:
                // todo 调用宽表更新接口 插入前校验ID是否存在，存在则更新 不存在则创建-兼容
                logger.info("openSearchSynchronize 调用宽表更新接口 actionType:{}", JSON.toJSONString(actionType));
                logger.info("openSearchSynchronize UPDATE 消息消费成功！ openSearchOrderParam：{}", JSON.toJSONString(openSearchOrderParam));
                break;
            case ActionTypeContent.DELETE:
                // todo 调用宽表删除接口 删除前校验ID是否存在,存在则删除，不存在则抛异常-不兼容
                logger.info("openSearchSynchronize 调用宽表删除接口 actionType:{}", JSON.toJSONString(actionType));
                logger.info("openSearchSynchronize DELETE 消息消费成功！ openSearchOrderParam：{}", JSON.toJSONString(openSearchOrderParam));
                break;
            default:
                logger.error("openSearchSynchronize 宽表类型 actionType字段 传入有误 抛出异常 ");
                throw new BizException(ErrorCodeAndMessage.OPEN_SEARCH_ACTION_IS_NULL.getStringErrorCode(), ErrorCodeAndMessage.OPEN_SEARCH_ACTION_IS_NULL.getErrorMessage());
        }
    }

    private void convertResult(ResultData sourceResultData, ResultData<Order> targetResult) {
        targetResult.setResultCode(sourceResultData.getResultCode());
        targetResult.setMessage(sourceResultData.getMessage());
        targetResult.setSuccess(sourceResultData.isSuccess());
        targetResult.setData(JSON.parseObject(JSON.toJSONString(sourceResultData.getData()), Order.class));
        logger.info("消息正在消费 转换订单参数 targetResult:{}", JSON.toJSONString(targetResult));
    }

}
