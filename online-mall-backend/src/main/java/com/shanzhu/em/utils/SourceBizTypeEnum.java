package com.shanzhu.em.utils;


import com.alibaba.druid.util.StringUtils;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 来源业务类型
 */
public enum SourceBizTypeEnum {

    /**
     * 支付宝
     */
    ALIPAY("purchase", "询价单", 1L, "00000008"),

    /**
     * 微信
     */
    WECHATPAY("purchase", "询价单", 1L, "00000088"),

    /**
     * 其他支付
     */
    OTHERPAY("purchase", "询价单", 1L, "00000888"),

    /**
     * 询价
     */
    RFQ("purchase", "询价单", 1L, "00000001"),
    /**
     * 报价单
     */
    QUOTATION("quotation", "询报价", 2L, "00000010"),
    /**
     * 招投标
     */
    BID("bid","招投标", 3L, "00000011"),
    /**
     * 招标单
     */
    BID_BIDDING("bid_bidding","招标单", 16L,"00010000"),
    /**
     * 合同
     * 原框架协议
     */
    FRAMEWORK_AGREEMENT("framework","合同", 4L, "00000100"),
    /**
     * 请购单
     */
    REQUISITION("requisition", "请购单", 5L, "00000101"),
    /**
     * 内部商城
     */
    MALL("mall", "内部商城", 6L, "00000110"),
    /**
     * 直营商城
     */
    DIRECTMALL("direct_mall", "直营商城", 7L, "00000111"),
    /**
     * 请购单匹配框架协议下采购订单类型
     */
    REQUISITION_MATCH_FRAMEWORK("requisition_framework", "请购-框架协议", 8L, "00001000"),
    /**
     * 内部商城到请购单下采购订单类型
     */
    MALL_REQUISITION("mall_requisition", "内部商城-请购", 9L, "00001001"),
    /**
     * 外部系统集成
     */
    OUTERSYSTEM("outersystem", "外部系统集成", 10L, "00001010"),

    /**
     * 外部招投标
     */
    OUTER_BID("outerbid", "外部招投标", 11L, "00001011"),

    /**
     * 比价单
     */
    PRICING_CHART("pricingChart","比价单", 12L, "00001100"),
    /**
     * 竞价单
     */
    AUCTION("auction","竞价单", 13L,"00001101"),

    /**
     * 竞价报价单
     */
    AUCTION_QUOTATION("auction_quotation", "竞价报价单", 14L, "00001110"),

    /**
     * 集采两阶段内采订单
     */
    CENTRALIZED_TWO_STAGE_INNER_ORDER("centralized_twostage_innerorder","集采内采订单", 15L,"00001111"),

    /**
     * 订单
     */
    PO_ORDER("poOrder", "订单", 16L, "00010000"),

    /**
     * 线下寻源
     */
    OFFLINE_SOURCING("offline_sourcing","线下寻源",17L,"00010001");


    private String value;
    private String descr;
    /**
     * 十进制数字
     */
    private Long decimalNum;
    /**
     * 二进制数字
     */
    private String binaryNum;
    
    private SourceBizTypeEnum(String value, String descr) {
        this.value = value;
        this.descr = descr;
    }

    SourceBizTypeEnum(String value, String desc, Long decimalNum, String binaryNum) {
        this.value = value;
        this.descr = desc;
        this.decimalNum = decimalNum;
        this.binaryNum = binaryNum;
    }
    
    public String getValue() {
        return value;
    }

    
    public String getDescr() {
        return descr;
    }
    
    public static SourceBizTypeEnum of(String sourceTypeValue) {
        Optional<SourceBizTypeEnum> findType = Arrays.asList(SourceBizTypeEnum.values()).stream()
                .filter(sourceType ->  StringUtils.equalsIgnoreCase(sourceType.getValue(), sourceTypeValue)).findFirst();
        if (findType.isPresent()) {
            return findType.get();
        } else {
            return null;
        }
    }

    public Long getDecimalNum() {
        return decimalNum;
    }

    public String getBinaryNum() {
        return binaryNum;
    }

    public static SourceBizTypeEnum parseByBinaryNum(String poSourceBizTypeBinaryNum) {
        if (poSourceBizTypeBinaryNum == null) {
            return null;
        }
        Optional<SourceBizTypeEnum> opt = Stream.of(SourceBizTypeEnum.values())
            .filter(poSourceSceneEnum -> poSourceSceneEnum.binaryNum.equalsIgnoreCase(poSourceBizTypeBinaryNum))
            .findFirst();
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    public static SourceBizTypeEnum parseByDecimalNum(String poSourceBizTypeDecimalNum) {
        if (poSourceBizTypeDecimalNum == null) {
            return null;
        }
        Optional<SourceBizTypeEnum> opt = Stream.of(SourceBizTypeEnum.values())
            .filter(poSourceSceneEnum -> poSourceSceneEnum.decimalNum.equals(poSourceBizTypeDecimalNum))
            .findFirst();
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }
}
