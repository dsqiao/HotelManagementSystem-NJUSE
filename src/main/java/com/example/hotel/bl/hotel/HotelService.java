package com.example.hotel.bl.hotel;

import com.example.hotel.enums.BizRegion;
import com.example.hotel.enums.HotelStar;
import com.example.hotel.enums.RoomType;
import com.example.hotel.enums.BizRegion;
import com.example.hotel.po.Hotel;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.po.Order;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.HotelVO;

import java.util.List;

public interface HotelService {

    /**
     * 添加酒店
     * @param hotelVO
     * @throws
     */
    void addHotel(HotelVO hotelVO) throws ServiceException;



    /**
     * 列表获取酒店信息
     * @return
     */
    List<HotelVO> retrieveHotels();

    /**
     * 获取某家酒店详细信息
     * @param hotelId
     * @return
     */
    HotelVO retrieveHotelDetails(Integer hotelId);



    /**
     * 搜索符合相关信息的酒店
     * @param address
     * @param hotelName
     * @param bizRegion
     * @param lowStar 星级最小值
     * @param highStar 星级最大值
     * @param lowPrice
     * @param highPrice
     * @param rooms
     * @param roomType
     * @param InDate 入住时间
     * @param OutDate 退房时间
     */
   List<HotelVO> searchHotels(String address, String hotelName, String bizRegion, Integer  lowStar, Integer highStar, Integer rooms, Integer roomType, Double lowPrice,Double highPrice,String InDate, String OutDate)throws ServiceException;


    /**
     * 根据商圈和地址列出对应的酒店列表
     * @param bizRegion
     * @param address
     * @return
     */
    List<HotelVO> getHotelByCondition(String bizRegion,String address,boolean ordered,int userId);



    /**
     * 根据评价人数排序
     * @param hotelList
     * @return
     */
    List<HotelVO> sortByEvaluationPeople(List<HotelVO> hotelList);


    /**
     * 根据星级排序
     * @param hotelList
     * @return
     */
    List<HotelVO> sortByStar(List<HotelVO> hotelList);


    /**
     * 根据评分排序
     * @param hotelList
     * @return
     */
    List<HotelVO> sortByScore(List<HotelVO> hotelList);




    void deleteHotel(Integer hotelId);


    List<HotelVO> getHotelListByManagerId(Integer managerId);

}
