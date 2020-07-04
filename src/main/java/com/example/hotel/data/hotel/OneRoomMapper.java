package com.example.hotel.data.hotel;

import com.example.hotel.po.OneRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OneRoomMapper {
    /**
     * 通过oderid选择可预定的房间
     * @param orderId
     * @return 可预订数量
     */
    List<Integer> selectRoomsByOrderId(@Param("orderId") Integer orderId);

    /**
     * 通过房间id，酒店id选择房间
     * @param roomId
     * @param hotelId
     * @return 房间列表
     */
    List<OneRoom> selectRoomInfoByRoomId(@Param("roomId") Integer roomId,
                                         @Param("hotelId") Integer hotelId
                                         );

    /**
     * 插入房间信息
     *
     * @param oneRoom
     */
    void insertRoomInfo(OneRoom oneRoom);

    /**
     * 删除房间信息
     * @param orderId
     */
    void deleteRoomInfo(@Param("orderId") Integer orderId);

    /**
     * 得到已经被使用的房间列表
     * @param hotelId
     * @param roomType
     * @return
     */
    List<OneRoom> getUsingRooms(@Param("hotelId") Integer hotelId,@Param("roomType") String roomType);

    /**
     * 得到所有房间
     * @return
     */
    List<OneRoom> getAllRooms();
}
