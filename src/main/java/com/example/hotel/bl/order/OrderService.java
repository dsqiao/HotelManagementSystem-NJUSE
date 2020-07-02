package com.example.hotel.bl.order;

import com.example.hotel.po.Order;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
public interface OrderService {

    /**
     * 预订酒店
     * @param orderVO
     * @return
     */
    ResponseVO addOrder(OrderVO orderVO);

    /**
     * 获得所有订单信息
     * @return
     */
    List<Order> getAllOrders();

    /**
     * 获得指定用户的所有订单信息
     * @param userid
     * @return
     */
    List<Order> getUserOrders(int userid);

    /**
     * 撤销订单
     * @param orderid
     * @return
     */
    ResponseVO annulOrder(int orderid);


    /**
     * 查看酒店的所有订单
     * @param hotelId
     * @return
     */
    List<Order> getHotelOrders(Integer hotelId);

    /**
     * 查看属于该管理员的所有订单
     * @param managerId
     * @return
     */
    List<Order> getManagedOrders(int managerId);

    /**
     * 执行订单
     * @param orderId
     * @return
     */
    ResponseVO executeOrder(int orderId);

    /**
     * 检查订单是否超过最晚执行时间发生异常
     * @param userId
     * @param type
     * @return
     */
    List<Order> updateOverTimeOrders(int userId,String type);

    /**
     * 退房
     * @param orderId
     * @return
     */
    ResponseVO checkOutOrder(int orderId);
}
