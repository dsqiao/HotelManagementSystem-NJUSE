package com.example.hotel.controller.oneRoom;


import com.example.hotel.bl.oneRoom.OneRoomService;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotel")
public class OneRoomController {

    @Autowired
    OneRoomService oneRoomService;

    @GetMapping("/{orderId}/getDistributedRooms")
    public ResponseVO getOrderedRooms(@PathVariable("orderId") int orderId){
        return ResponseVO.buildSuccess(oneRoomService.getDistributedRooms(orderId));
    }

}
