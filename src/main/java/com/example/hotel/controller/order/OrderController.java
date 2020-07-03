package com.example.hotel.controller.order;

import com.example.hotel.bl.order.OrderService;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: chenyizong
 * @Date: 2020-02-29
 */


@RestController()
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public ResponseVO reserveHotel(@RequestBody OrderVO orderVO){
        return orderService.addOrder(orderVO);
    }

    @GetMapping("/getAllOrders")
    public ResponseVO retrieveAllOrders(){
        return ResponseVO.buildSuccess(orderService.getAllOrders());
    }

    @GetMapping("/{userId}/getUserOrders")
    public  ResponseVO retrieveUserOrders(@PathVariable int userId){
        return ResponseVO.buildSuccess(orderService.getUserOrders(userId));
    }

    @GetMapping("/{orderid}/annulOrder")
    public ResponseVO annulOrder(@PathVariable int orderid){
        return orderService.annulOrder(orderid);
    }

    @GetMapping("/{managerId}/managedOrders")
    public ResponseVO getManagedOrders(@PathVariable Integer managerId){
        return ResponseVO.buildSuccess(orderService.getManagedOrders(managerId));
    }

    @PostMapping("/{orderId}/executeOrder")
    public ResponseVO executeOrder(@PathVariable Integer orderId){
        return orderService.executeOrder(orderId);
    }

    @GetMapping("/updateOverTimeOrders")
    public ResponseVO updateOverTimeOrders(@RequestParam Integer userId,String type){
        return ResponseVO.buildSuccess(orderService.updateOverTimeOrders(userId,type));
    }
    @GetMapping("/{hotelId}/allOrders")
    public ResponseVO retrieveHotelOrders(@PathVariable Integer hotelId) {
        return ResponseVO.buildSuccess(orderService.getHotelOrders(hotelId));
    }


    @PostMapping("/{orderId}/checkOutOrder")
    public ResponseVO checkOutOrder(@PathVariable Integer orderId){
        return orderService.checkOutOrder(orderId);
    }
}
