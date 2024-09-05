package com.shanzhu.em.utils;

import com.alibaba.druid.util.StringUtils;

import java.util.Arrays;
import java.util.Optional;

/**
 * 订单支付类型枚举类
 */
public enum PayTypeEnum {

    ALIPAY("alipay", "支付宝支付", 1L, "00000001"),
    WECHATPAY("wechatpay", "微信", 2L, "00000002"),
    OTHERPAY("otherpay", "其他支付方式", 3L, "00000003"),
    ;

    private String value;
    private String desc;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getDecimalNum() {
        return decimalNum;
    }

    public void setDecimalNum(Long decimalNum) {
        this.decimalNum = decimalNum;
    }

    public String getBinaryNum() {
        return binaryNum;
    }

    public void setBinaryNum(String binaryNum) {
        this.binaryNum = binaryNum;
    }

    /**
     * 十进制数字
     */
    private Long decimalNum;
    /**
     * 二进制数字
     */
    private String binaryNum;

    PayTypeEnum(String value, String desc, Long decimalNum, String binaryNum) {
        this.value = value;
        this.desc = desc;
        this.decimalNum = decimalNum;
        this.binaryNum = binaryNum;
    }

    public static PayTypeEnum of(String sourceTypeValue) {
        Optional<PayTypeEnum> findType = Arrays.asList(PayTypeEnum.values()).stream()
                .filter(sourceType ->  StringUtils.equalsIgnoreCase(sourceType.getValue(), sourceTypeValue)).findFirst();
        if (findType.isPresent()) {
            return findType.get();
        } else {
            return null;
        }
    }

}
