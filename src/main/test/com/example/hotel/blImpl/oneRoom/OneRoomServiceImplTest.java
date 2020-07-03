package com.example.hotel.blImpl.oneRoom;

import com.example.hotel.HotelApplication;
import com.example.hotel.bl.oneRoom.OneRoomService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.blImpl.order.OrderServiceImpl;
import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.po.OneRoom;
import com.example.hotel.po.Order;
import com.example.hotel.vo.OrderVO;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HotelApplication.class)

class OneRoomServiceImplTest {

    @Autowired
    OrderService orderService=new OrderServiceImpl();
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OneRoomService oneRoomService=new OneRoomServiceImpl();

    @BeforeEach
    void setUp() {
        OrderVO order1=new OrderVO();
        order1.setHotelId(1);
        order1.setUserId(4);
        order1.setCheckInDate("2020-06-20 12:00:00");
        order1.setCheckOutDate("2020-06-25 12:00:00");
        order1.setRoomNum(2);
        order1.setHaveChild(false);
        order1.setRoomType("Family");
        orderService.addOrder(order1);
        OrderVO order2=new OrderVO();
        order2.setHotelId(1);
        order2.setUserId(5);
        order2.setCheckInDate("2020-06-21 12:00:00");
        order2.setCheckOutDate("2020-06-24 12:00:00");
        order2.setRoomNum(3);
        order2.setHaveChild(true);
        order2.setRoomType("BigBed");
        orderService.addOrder(order2);
    }

    @AfterEach
    void tearDown() {
        List<Order> orders=orderService.getAllOrders();
        for(int i=0;i<orders.size();++i){
            orderMapper.annulOrder(orders.get(i).getId());
            oneRoomService.checkOutRooms(orders.get(i).getId());
        }
    }


    /**
     * 功能测试：用户退房（订单号为Setup中的订单）
     */
    @Test
    void checkOutRooms() {
        int before=oneRoomService.getAllRooms().size();
        List<Order> orders=orderService.getAllOrders();
        for(int i=0;i<orders.size();++i){
            orderMapper.annulOrder(orders.get(i).getId());
            int size=orders.get(i).getRoomNum();
            oneRoomService.checkOutRooms(orders.get(i).getId());
            int after=oneRoomService.getAllRooms().size();
            assertEquals(before-size,after);
            before-=size;
        }
    }

    @Test
    /**
     * 集成测试：检查预订房间及订单生成后的房间号分配情况
     * 分配两个家庭房，三个大床房，剩余数量足够，无冲突
     */
    void getDistributedRoomsTest1() {

        List<List<Integer>> roomLists=new ArrayList<List<Integer>>(){{
           add(new ArrayList<Integer>(){{add(301);add(302);}});
           add(new ArrayList<Integer>(){{add(101);add(102);add(103);}});
        }};
        List<Order> orderList=orderService.getAllOrders();
        for(int i=0;i<orderList.size();++i){
            List<Integer> current=oneRoomService.getDistributedRooms(orderList.get(i).getId());
            assert roomLists.get(i).equals(current);
        }

    }

    @Test
    /**
     * 集成测试：检查预订房间及订单生成后的房间号分配情况
     * 分配两个家庭房，十一个大床房，超过剩余数量且时间冲突
     */
    void getDistributedRoomsTest2() {
        //3个大床房
        OrderVO order3=new OrderVO();
        order3.setHotelId(1);
        order3.setUserId(4);
        order3.setCheckInDate("2020-06-22 12:00:00");
        order3.setCheckOutDate("2020-06-24 12:00:00");
        order3.setRoomNum(3);
        order3.setHaveChild(false);
        order3.setRoomType("BigBed");
        orderService.addOrder(order3);
        //3个大床房
        OrderVO order4=new OrderVO();
        order4.setHotelId(1);
        order4.setUserId(5);
        order4.setCheckInDate("2020-06-22 12:00:00");
        order4.setCheckOutDate("2020-06-23 12:00:00");
        order4.setRoomNum(3);
        order4.setHaveChild(false);
        order4.setRoomType("BigBed");
        orderService.addOrder(order4);
        //2个大床房
        OrderVO order5=new OrderVO();
        order5.setHotelId(1);
        order5.setUserId(4);
        order5.setCheckInDate("2020-06-21 12:00:00");
        order5.setCheckOutDate("2020-06-23 12:00:00");
        order5.setRoomNum(2);
        order5.setHaveChild(false);
        order5.setRoomType("BigBed");
        orderService.addOrder(order5);

        List<List<Integer>> roomLists=new ArrayList<List<Integer>>(){{
            add(new ArrayList<Integer>(){{add(301);add(302);}});
            add(new ArrayList<Integer>(){{add(101);add(102);add(103);}});
            add(new ArrayList<Integer>(){{add(104);add(105);add(106);}});
            add(new ArrayList<Integer>(){{add(107);add(108);add(109);}});
        }};
        List<Order> orderList=orderService.getAllOrders();
        for(int i=0;i<orderList.size();++i){
            List<Integer> current=oneRoomService.getDistributedRooms(orderList.get(i).getId());
            assert roomLists.get(i).equals(current);
        }
    }

    @Test
    /**
     * 集成测试：检查预订房间及订单生成后的房间号分配情况
     * 分配两个家庭房，十一个大床房，超过剩余数量，但时间未冲突
     */
    void getDistributedRoomsTest3() {
        //3个大床房
        OrderVO order3=new OrderVO();
        order3.setHotelId(1);
        order3.setUserId(4);
        order3.setCheckInDate("2020-06-21 12:00:00");
        order3.setCheckOutDate("2020-06-23 12:00:00");
        order3.setRoomNum(3);
        order3.setHaveChild(false);
        order3.setRoomType("BigBed");
        orderService.addOrder(order3);
        //3个大床房
        OrderVO order4=new OrderVO();
        order4.setHotelId(1);
        order4.setUserId(5);
        order4.setCheckInDate("2020-06-22 12:00:00");
        order4.setCheckOutDate("2020-06-24 12:00:00");
        order4.setRoomNum(3);
        order4.setHaveChild(false);
        order4.setRoomType("BigBed");
        orderService.addOrder(order4);
        //2个大床房，此时剩余房间为104,105,106(按照退房时间已退房),110
        OrderVO order5=new OrderVO();
        order5.setHotelId(1);
        order5.setUserId(4);
        order5.setCheckInDate("2020-06-23 12:00:00");
        order5.setCheckOutDate("2020-06-25 12:00:00");
        order5.setRoomNum(2);
        order5.setHaveChild(false);
        order5.setRoomType("BigBed");
        orderService.addOrder(order5);

        List<List<Integer>> roomLists=new ArrayList<List<Integer>>(){{
            add(new ArrayList<Integer>(){{add(301);add(302);}});
            add(new ArrayList<Integer>(){{add(101);add(102);add(103);}});
            add(new ArrayList<Integer>(){{add(104);add(105);add(106);}});
            add(new ArrayList<Integer>(){{add(107);add(108);add(109);}});
            add(new ArrayList<Integer>(){{add(104);add(105);}});
        }};
        List<Order> orderList=orderService.getAllOrders();
        for(int i=0;i<orderList.size();++i){
            List<Integer> current=oneRoomService.getDistributedRooms(orderList.get(i).getId());
            assert roomLists.get(i).equals(current);
        }
    }

    @Test
    /**
     * 集成测试：检查预订房间及订单生成后的房间号分配情况
     * 分配两个家庭房，三个大床房，三个总统套房，一个双床房
     */
    void getDistributedRoomsTest5() {
        //3个总统套房
        OrderVO order4=new OrderVO();
        order4.setHotelId(1);
        order4.setUserId(5);
        order4.setCheckInDate("2020-06-22 12:00:00");
        order4.setCheckOutDate("2020-06-24 12:00:00");
        order4.setRoomNum(3);
        order4.setHaveChild(false);
        order4.setRoomType("PresidentBed");
        orderService.addOrder(order4);
        //1个双床房
        OrderVO order5=new OrderVO();
        order5.setHotelId(1);
        order5.setUserId(4);
        order5.setCheckInDate("2020-06-21 12:00:00");
        order5.setCheckOutDate("2020-06-24 12:00:00");
        order5.setRoomNum(1);
        order5.setHaveChild(false);
        order5.setRoomType("DoubleBed");
        orderService.addOrder(order5);

        List<List<Integer>> roomLists=new ArrayList<List<Integer>>(){{
            add(new ArrayList<Integer>(){{add(301);add(302);}});
            add(new ArrayList<Integer>(){{add(101);add(102);add(103);}});
            add(new ArrayList<Integer>(){{add(401);add(402);add(403);}});
            add(new ArrayList<Integer>(){{add(201);}});
        }};
        List<Order> orderList=orderService.getAllOrders();
        for(int i=0;i<orderList.size();++i){
            List<Integer> current=oneRoomService.getDistributedRooms(orderList.get(i).getId());
            assert roomLists.get(i).equals(current);
        }
    }


    @Test
    /**
     * 集成测试：检查预订房间及订单生成后的房间号分配情况
     * 分配两个家庭房，十个大床房，刚好无剩余房间
     */
    void getDistributedRoomsTest4() {
        //3个大床房
        OrderVO order3=new OrderVO();
        order3.setHotelId(1);
        order3.setUserId(4);
        order3.setCheckInDate("2020-06-21 12:00:00");
        order3.setCheckOutDate("2020-06-23 12:00:00");
        order3.setRoomNum(3);
        order3.setHaveChild(false);
        order3.setRoomType("BigBed");
        orderService.addOrder(order3);
        //3个大床房
        OrderVO order4=new OrderVO();
        order4.setHotelId(1);
        order4.setUserId(5);
        order4.setCheckInDate("2020-06-22 12:00:00");
        order4.setCheckOutDate("2020-06-24 12:00:00");
        order4.setRoomNum(3);
        order4.setHaveChild(false);
        order4.setRoomType("BigBed");
        orderService.addOrder(order4);
        //1个大床房,剩余房间为110
        OrderVO order5=new OrderVO();
        order5.setHotelId(1);
        order5.setUserId(4);
        order5.setCheckInDate("2020-06-21 12:00:00");
        order5.setCheckOutDate("2020-06-24 12:00:00");
        order5.setRoomNum(1);
        order5.setHaveChild(false);
        order5.setRoomType("BigBed");
        orderService.addOrder(order5);

        List<List<Integer>> roomLists=new ArrayList<List<Integer>>(){{
            add(new ArrayList<Integer>(){{add(301);add(302);}});
            add(new ArrayList<Integer>(){{add(101);add(102);add(103);}});
            add(new ArrayList<Integer>(){{add(104);add(105);add(106);}});
            add(new ArrayList<Integer>(){{add(107);add(108);add(109);}});
            add(new ArrayList<Integer>(){{add(110);}});
        }};
        List<Order> orderList=orderService.getAllOrders();
        System.out.println(orderList.size());
        for(int i=0;i<orderList.size();++i){
            List<Integer> current=oneRoomService.getDistributedRooms(orderList.get(i).getId());
            assert roomLists.get(i).equals(current);
        }
    }

}