package com.example.hotel.data.hotel;

import com.example.hotel.po.Hotel;
import com.example.hotel.po.HotelRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoomMapper {
    /**
     * 更新房间信息
     * @param hotelId
     * @param roomType
     * @param rest
     * @return
     */
    int updateRoomInfo(@Param("hotelId") Integer hotelId,@Param("roomType") String roomType,@Param("rest") Integer rest);

    /**
     * 添加 房间
     * @param hotelRoom
     * @return
     */
    int insertRoom(HotelRoom hotelRoom);

    /**
     * 通过酒店id选择房间
     * @param hotelId
     * @return
     */
    List<HotelRoom> selectRoomsByHotelId(@Param("hotelId") Integer hotelId);

    /**
     * 选择某类型的房间
     * @param hotelId
     * @param roomType
     * @return
     */
    HotelRoom selectSpecificRooms(@Param("hotelId") Integer hotelId,@Param("roomType") String roomType);
}
