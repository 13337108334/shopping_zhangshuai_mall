package com.shanzhu.em.service.orderpay;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.shanzhu.em.entity.Order;
import com.shanzhu.em.utils.ResultData;
import com.shanzhu.em.utils.SourceBizTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PayLogic implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(PayLogic.class);

    @Autowired
    private List<PayService> payService;

    Map<String, PayService> serviceMap = Maps.newHashMap();

    //此处封装好 其他地方注入logic类直接调用
    public ResultData<Order> logic(SourceBizTypeEnum sourceBizTypeEnum, Long id) {
        if(sourceBizTypeEnum == null){
            log.error("PayLogic logic sourceBizTypeEnum is null");
            return null;
        }
        if(id == null){
            log.error("PayLogic logic id is null");
            return null;
        }
        PayService payService = serviceMap.get(sourceBizTypeEnum.getValue());
        log.info("PayLogic logic payService:{}", JSON.toJSONString(payService));
        return payService.buildParam(sourceBizTypeEnum,id);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        payService.forEach(
                e -> {
                    String sourceBizValue = e.getSourceBizType().getValue();
                    serviceMap.put(sourceBizValue, e);
                }
        );
    }

}
