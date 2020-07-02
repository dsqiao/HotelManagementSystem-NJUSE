package com.example.hotel.bl.oneRoom;

import com.example.hotel.po.OneRoom;
import com.example.hotel.vo.OneRoomVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;

import java.util.List;

public interface OneRoomService {
    ResponseVO distributeRooms(OrderVO orderVO);
    void checkOutRooms(int orderId);
    List<Integer> getDistributedRooms(int orderId);
    List<OneRoom> getAllRooms();
}
