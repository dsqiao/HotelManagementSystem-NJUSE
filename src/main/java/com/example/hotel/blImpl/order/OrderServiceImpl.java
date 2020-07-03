package com.example.hotel.blImpl.order;

import com.example.hotel.bl.credit.CreditService;
import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.oneRoom.OneRoomService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.po.Order;
import com.example.hotel.po.User;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Service
public class OrderServiceImpl implements OrderService {
    private final static String RESERVE_ERROR = "预订失败";
    private final static String ROOM_LACK = "预订房间数量剩余不足";
    private final static String ANNUL_ERROR="取消失败";
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    HotelService hotelService;
    @Autowired
    AccountService accountService;
    @Autowired
    OrderService orderService;
    @Autowired
    CreditService creditService;
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    OneRoomService oneRoomService;

    @Override
    public ResponseVO addOrder(OrderVO orderVO) {
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            String curdate = sf.format(date);
            String latest=curdate+" 18:00:00";
            orderVO.setLatestTime(latest);
            orderVO.setCreateDate(curdate);
            orderVO.setOrderState("已预订");
            User user = accountService.getUserInfo(orderVO.getUserId());
            orderVO.setClientName(user.getUserName());
            orderVO.setPhoneNumber(user.getPhoneNumber());
            Order order = new Order();
            BeanUtils.copyProperties(orderVO,order);
            orderMapper.addOrder(order);
            orderVO.setId(order.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(RESERVE_ERROR);
        }
        return oneRoomService.distributeRooms(orderVO);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderMapper.getAllOrders();
    }

    @Override
    public List<Order> getUserOrders(int userid) {
        return orderMapper.getUserOrders(userid);
    }



    @Override
    public ResponseVO annulOrder(int orderid) {
        //取消订单逻辑的具体实现（注意可能有和别的业务类之间的交互）
        Order thisOrder=orderMapper.getOrderById(orderid);
        try {
            thisOrder.setOrderState("已撤销");
            User thisUser=accountMapper.getAccountById(thisOrder.getUserId());
            DateTimeFormatter sf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            LocalDateTime end=LocalDateTime.parse(thisOrder.getCheckInDate(),sf);
            Duration duration=Duration.between(LocalDateTime.now(),end);
            long hours= duration.toHours();
            if(hours<=6){
                thisUser.setCredit(thisUser.getCredit()-thisOrder.getPrice()/2);
            }
            orderMapper.updateOrderState(orderid,thisOrder.getOrderState());//更新订单状态
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("撤销失败");
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<Order> getManagedOrders(int managerId) {
        return orderMapper.getManagedOrders(managerId);
    }

    @Override
    public ResponseVO executeOrder(int orderId) {
        String orderState="已入住";
        String action="订单执行";
        Order order=orderMapper.getOrderById(orderId);
        User user=accountService.getUserInfo(order.getUserId());
        double change=order.getPrice()*0.01;
        creditService.updateCredit(user.getId(),action,change,user.getCredit());//更新信用值
        return ResponseVO.buildSuccess(orderMapper.updateOrderState(orderId,orderState));
    }

    @Override
    public List<Order> updateOverTimeOrders(int userId,String type) {
        List<Order> orders=new ArrayList<>();
        if(type.equals("user")) orders=getUserOrders(userId);
        else if(type.equals("hotelManager")) orders=getManagedOrders(userId);
        updateOverTimeOrders(orders);//更新是否发生异常
        return orders;
    }

    @Override
    public ResponseVO checkOutOrder(int orderId) {
        String orderState="已退房";
        orderMapper.updateOrderState(orderId,orderState);
        oneRoomService.checkOutRooms(orderId);
        return ResponseVO.buildSuccess(true);
    }

    /**
     * 检查订单是否超时导致异常
     * @param Orders
     */

    public void updateOverTimeOrders(List<Order> Orders){
        for(int i=0;i<Orders.size();++i){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String executed=Orders.get(i).getCheckInDate().substring(0,10)+" 18:00:00";//到六点之前的话执行不算违约
            TemporalAccessor parse=formatter.parse(executed);
            LocalDateTime latest=LocalDateTime.from(parse);
            LocalDateTime current=LocalDateTime.now();
            if(latest.compareTo(current)<0&&Orders.get(i).getOrderState().equals("已预订")){
                String orderState="异常";
                Orders.get(i).setOrderState(orderState);
                orderMapper.updateOrderState(Orders.get(i).getId(),orderState);//订单状态更新
                int userId=Orders.get(i).getUserId();
                String action="订单异常";
                User user=accountService.getUserInfo(userId);
                double change=Orders.get(i).getPrice()*0.01;
                creditService.updateCredit(userId,action,change,user.getCredit());//信用值更新
            }
        }
    }


    /**
     * @param hotelId
     * @return
     */
    @Override
    public List<Order> getHotelOrders(Integer hotelId) {
        List<Order> orders = orderService.getAllOrders();
        return orders.stream().filter(order -> order.getHotelId().equals(hotelId)).collect(Collectors.toList());
    }

}
