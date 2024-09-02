package com.shanzhu.em.service.orderpay;

import com.google.common.collect.Maps;
import com.shanzhu.em.entity.Order;
import com.shanzhu.em.utils.ResultData;
import com.shanzhu.em.utils.SourceBizTypeEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PayLogic implements InitializingBean {


    @Autowired
    private List<PayService> payService;

    Map<String, PayService> serviceMap = Maps.newHashMap();

    // todo 此处封装好 其他地方注入logic类直接调用
    ResultData<Order> logic(SourceBizTypeEnum sourceBizTypeEnum, Long id) {
        PayService payService = serviceMap.get(sourceBizTypeEnum.getValue());
        return payService.buildParam(id);
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
