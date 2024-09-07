package com.shanzhu.em.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanzhu.em.entity.Order;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * 订单 持久层（mapper）
 *
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    @Update("update t_order set state = '已支付',pay_type = #{payType} where order_no = #{orderNo} ")
    void payOrder(String orderNo,String payType);

    @MapKey("id")
    List<Map<String, Object>> selectByUserId(int userId);

    @Update("update t_order set state = '已收货' where order_no = #{orderNo}")
    boolean receiveOrder(String orderNo);

    Map<String, Object> selectByOrderNo(String orderNo);

    @Select("select * from t_order where order_no = #{orderNo}")
    Order selectByOrderNoPlus(String orderNo);
}
