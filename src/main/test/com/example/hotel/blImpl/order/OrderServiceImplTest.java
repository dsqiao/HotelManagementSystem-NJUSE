package com.example.hotel.blImpl.order;

import com.example.hotel.HotelApplication;
import com.example.hotel.bl.oneRoom.OneRoomService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.blImpl.order.OrderServiceImpl;
import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.po.OneRoom;
import com.example.hotel.po.Order;
import com.example.hotel.vo.OrderVO;
import org.apache.ibatis.annotations.Param;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HotelApplication.class)
class OrderServiceImplTest {

    @Autowired
    OrderService orderService=new OrderServiceImpl();
    @Autowired
    OrderMapper orderMapper;




    /**
     * 功能测试：添加单个订单
     */
    @org.junit.jupiter.api.Test
    void addOrder() {
        OrderVO orderVO=new OrderVO();
        orderVO.setCheckInDate("2020-07-01 12:00:00");
        orderVO.setCheckOutDate("2020-07-25 12:00:00");
        orderVO.setHotelId(1);
        orderVO.setCreateDate("2020-06-27");
        orderVO.setClientName("67");
        orderVO.setPhoneNumber("13636376767");
        orderVO.setRoomNum(3);
        orderVO.setHaveChild(true);
        orderVO.setUserId(5);
        orderVO.setPeopleNum(3);
        orderVO.setRoomType("PresidentBed");
        orderVO.setHotelName("汉庭酒店");
        orderVO.setPrice(500.0);
        orderService.addOrder(orderVO);
        List<Order> order=orderMapper.getAllOrders();
        Order newAdd=order.get(order.size()-1);
        assertEquals("2020-07-01 12:00:00",newAdd.getCheckInDate());
        assertEquals("2020-07-25 12:00:00",newAdd.getCheckOutDate());
    }


    /**
     *  功能测试：删除订单
     */
    @org.junit.jupiter.api.Test

    public void annulOrder() {
        List<Order> order=orderMapper.getAllOrders();
        Order newAdd=order.get(order.size()-1);
        int orderid=newAdd.getId();
        orderService.annulOrder(orderid);
        assertEquals("已撤销",orderMapper.getAllOrders().get(orderMapper.getAllOrders().size()-1).getOrderState());
    }


    /**
     * 单元测试：执行订单
     */
    @org.junit.jupiter.api.Test

    public void executeOrder() {
        List<Order> order=orderMapper.getAllOrders();
        Order newAdd=order.get(order.size()-1);
        int orderid=newAdd.getId();
        orderService.executeOrder(orderid);
        assertEquals("已入住",orderMapper.getAllOrders().get(orderMapper.getAllOrders().size()-1).getOrderState());

    }


    /**
     * 单元测试：用户退房
     */
    @org.junit.jupiter.api.Test

    public void checkOutOrder() {
        List<Order> order=orderMapper.getAllOrders();
        Order newAdd=order.get(order.size()-1);
        int orderid=newAdd.getId();
        orderService.checkOutOrder(orderid);
        assertEquals("已退房",orderMapper.getAllOrders().get(orderMapper.getAllOrders().size()-1).getOrderState());
    }
}