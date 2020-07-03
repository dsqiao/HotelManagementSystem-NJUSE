package com.example.hotel.bl.hotel;

import com.example.hotel.po.HotelRoom;
import com.example.hotel.po.OneRoom;

import java.util.List;

public interface RoomService {

    /**
     * 获取某个酒店的全部房间信息
     * @param hotelId
     * @return
     */
    List<HotelRoom> retrieveHotelRoomInfo(Integer hotelId);

    /**
     * 添加酒店客房信息
     * @param hotelRoom
     */
    void insertRoomInfo(HotelRoom hotelRoom);

    /**
     * 更新剩余房间数目
     * @param hotelId
     * @param checkInDate
     * @param checkOutDate
     * @return
     */
    List<HotelRoom> updateRestRooms(int hotelId,String checkInDate,String checkOutDate);

    /**
     * 检查房间是否被占用
     * @param checkInStr
     * @param checkOutStr
     * @param oneRoom
     * @return
     */
    boolean checkIsConflicted(String checkInStr, String checkOutStr, OneRoom oneRoom);
}
