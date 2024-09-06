package com.shanzhu.em.service.rabbitmq.sync;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单宽表数据模型
 *
 * @author: zhangshuai
 * @date: 2024-09-06
 */
@Data
public class OpenSearchOrder implements Serializable {

    private static final long                     serialVersionUID = -884605515550771001L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 下单人id
     */
    private int userId;

    /**
     * 联系人
     */
    private String linkUser;

    /**
     * 联系电话
     */
    private String linkPhone;

    /**
     * 送货地址
     */
    private String linkAddress;

    /**
     * 状态
     */
    private String state;

    /**
     * DB创建时间
     */
    private String createTime;

    /**
     * 消息发送时间
     */
    private String messageCreateTime;

    /**
     * 宽表创建时间
     */
    private String openSearchCreateTime;

    /**
     * 该订单包含的商品信息
     */
    private String goods;

    /**
     * 对应购物车id
     */
    private Long cartId;

    /**
     * 支付类型  PayTypeEnum
     */
    private String payType;

    /**
     * 行为类型，增、删、改
     */
    private String actionType;

}