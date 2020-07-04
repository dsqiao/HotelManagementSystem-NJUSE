package com.example.hotel.data.order;

import com.example.hotel.po.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Mapper
@Repository
public interface OrderMapper {
    /**
     * 添加订单
     * @param order
     * @return
     */
    int addOrder(Order order);

    /**
     * 得到所有订单
     * @return
     */
    List<Order> getAllOrders();

    /**
     * 得到某用户所有订单
     * @param userid
     * @return
     */
    List<Order> getUserOrders(@Param("userid") int userid);

    /**
     * 删除订单
     * @param orderId
     * @return
     */
    int annulOrder(@Param("orderId") int orderId);

    /**
     * 得到某订单
     * @param orderid
     * @return
     */
    Order getOrderById(@Param("orderid") int orderid);

    /**
     * 得到某管理员管理的订单
     * @param managerId
     * @return
     */
    List<Order> getManagedOrders(@Param("managerId") int managerId);

    /**
     * 更新订单状态
     * @param orderId
     * @param orderState
     * @return
     */
    int updateOrderState(@Param("orderId") int orderId,@Param("orderState") String orderState);

}
