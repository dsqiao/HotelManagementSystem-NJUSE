package com.example.hotel.data.hotel;

import com.example.hotel.po.OneRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OneRoomMapper {
    List<Integer> selectRoomsByOrderId(@Param("orderId") Integer orderId);

    List<OneRoom> selectRoomInfoByRoomId(@Param("roomId") Integer roomId,
                                         @Param("hotelId") Integer hotelId
                                         );

    void insertRoomInfo(OneRoom oneRoom);

    void deleteRoomInfo(@Param("orderId") Integer orderId);

    List<OneRoom> getUsingRooms(@Param("hotelId") Integer hotelId,@Param("roomType") String roomType);

    List<OneRoom> getAllRooms();
}
