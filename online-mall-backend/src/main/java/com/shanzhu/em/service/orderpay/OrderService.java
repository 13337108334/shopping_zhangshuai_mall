package com.shanzhu.em.service.orderpay;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanzhu.em.constants.Status;
import com.shanzhu.em.entity.Order;
import com.shanzhu.em.entity.OrderGoods;
import com.shanzhu.em.entity.OrderItem;
import com.shanzhu.em.service.CartService;
import com.shanzhu.em.utils.*;
import com.shanzhu.em.mapper.GoodMapper;
import com.shanzhu.em.mapper.OrderGoodsMapper;
import com.shanzhu.em.mapper.OrderMapper;
import com.shanzhu.em.mapper.StandardMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 订单 服务层
 *
 * @author zhangshuai
 * @Date 2024/09/03
 */
@Service
@RequiredArgsConstructor
public class OrderService extends ServiceImpl<OrderMapper, Order> {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderMapper orderMapper;

    private final OrderGoodsMapper orderGoodsMapper;

    private final StandardMapper standardMapper;

    private final GoodMapper goodMapper;

    private final CartService cartService;

    @Autowired
    private PayLogic payLogic;

    /**
     * 分页查询订单
     *
     * @param pageNum  页数
     * @param pageSize 页大小
     * @param orderNo  订单编号
     * @param state    状态
     * @return 订单
     */
    public IPage<Order> selectByPage(
            Integer pageNum,
            Integer pageSize,
            String orderNo,
            String state
    ) {
        QueryWrapper<Order> orderQueryWrapper =
                Wrappers.<Order>query()
                        .ne("state", "待付款")
                        .eq(StrUtil.isNotBlank(state), "state", state)
                        .like(StrUtil.isNotBlank(orderNo), "order_no", orderNo)
                        .orderByDesc("create_time");

        return this.page(new Page<>(pageNum, pageSize), orderQueryWrapper);
    }

    /**
     * 保存订单
     *
     * @param order 订单信息
     * @return 订单编号
     */
    @Transactional
    public String saveOrder(Order order) {
        order.setUserId(TokenUtils.getCurrentUser().getId());
        String orderNo = DateUtil.format(
                new Date(), DatePattern.PURE_DATETIME_PATTERN) + RandomUtil.randomNumbers(6);
        order.setOrderNo(orderNo);
        order.setCreateTime(DateUtil.now());
        //插入用户订单
        orderMapper.insert(order);

        //订单商品关联信息
        OrderGoods orderGoods = new OrderGoods();
        orderGoods.setOrderId(order.getId());

        //遍历order里携带的goods数组，并用orderItem对象来接收
        String goods = order.getGoods();
        List<OrderItem> orderItems = JSON.parseArray(goods, OrderItem.class);
        for (OrderItem orderItem : orderItems) {
            Long good_id = orderItem.getId();
            String standard = orderItem.getStandard();
            int num = orderItem.getNum();
            orderGoods.setGoodId(good_id);
            orderGoods.setCount(num);
            orderGoods.setStandard(standard);

            //插入到order_good表
            orderGoodsMapper.insert(orderGoods);
        }

        // 清除购物车
        cartService.removeById(order.getCartId());

        return orderNo;
    }

    /**
     * 支付订单
     *
     * @param orderNo 订单编号
     */
    @Transactional
    public void payOrder(String orderNo,String payType) {
        //给对应规格减库存
        Map<String, Object> orderMap = orderMapper.selectByOrderNo(orderNo);
        int count = (int) orderMap.get("count");
        Long goodId = (Long) orderMap.get("goodId");
        String standard = (String) orderMap.get("standard");
        int store = standardMapper.getStore(goodId, standard);
        if (store < count) {
            throw new BizException(Status.CODE_500, "商品库存不足");
        }

        //减库存
        standardMapper.deductStore(goodId, standard, store - count);

        //给对应商品加销量和销售额
        Order order = lambdaQuery().eq(Order::getOrderNo, orderNo).one();

        //商品售出
        goodMapper.saleGood(goodId, count, order.getTotalPrice());

        // 根据类型去匹配 并异步发送消息给宽表 同步数据
        payLogic.logic(PayTypeEnum.of(payType),order.getId());

    }

    /**
     * 查询用户订单
     *
     * @param userid 用户id
     * @return 订单信息
     */
    public List<Map<String, Object>> selectByUserId(int userid) {
        return orderMapper.selectByUserId(userid);
    }

    /**
     * 通过订单编号查询订单
     *
     * @param orderNo 订单编号
     * @return 订单信息
     */
    public Map<String, Object> selectByOrderNo(String orderNo) {
        return orderMapper.selectByOrderNo(orderNo);
    }

    /**
     * 订单发货
     *
     * @param orderNo 订单编号
     */
    public void delivery(String orderNo) {
        this.update(
                Wrappers.<Order>lambdaUpdate()
                        .eq(Order::getOrderNo, orderNo)
                        .set(Order::getState, "已发货")
        );
    }

    /**
     * 确认收货
     *
     * @param orderNo 订单编号
     * @return 结果
     */
    public boolean receiveOrder(String orderNo) {
        return orderMapper.receiveOrder(orderNo);
    }

    /**
     * 根据id查询订单详情
     *
     * @param id
     * @return
     */
    public Order getOrder(Long id) {
        if (id == null) {
            logger.error("OrderService getOrder service过来的 id is null");
            return null;
        }
        return orderMapper.selectById(id);
    }

    /**
     * 更新订单
     *
     * @param order
     * @return 0/1
     */
    public Boolean updateOrder(Order order) {
        if (order == null || order.getId() == null) {
            logger.error("传入订单数据为空/传入订单id为空");
            throw new BizException(ErrorCodeAndMessage.ORDER_IS_NULL.getStringErrorCode(), ErrorCodeAndMessage.ORDER_IS_NULL.getErrorMessage());
        }
        int i = orderMapper.updateById(order);
        return i == 1;
    }

}
