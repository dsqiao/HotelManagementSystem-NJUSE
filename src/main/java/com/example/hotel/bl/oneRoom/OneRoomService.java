package com.example.hotel.bl.oneRoom;

import com.example.hotel.po.OneRoom;
import com.example.hotel.vo.OneRoomVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;

import java.util.List;

public interface OneRoomService {
    /**
     * 分配房间号
     * @param orderVO
     * @return
     */
    ResponseVO distributeRooms(OrderVO orderVO);

    /**
     * 退房
     * @param orderId
     */
    void checkOutRooms(int orderId);

    /**
     *获取订单对应的房间号
     * @param orderId
     * @return
     */
    List<Integer> getDistributedRooms(int orderId);

    /**
     * 获取所有OneRoom（测试用）
     * @return
     */
    List<OneRoom> getAllRooms();
}
