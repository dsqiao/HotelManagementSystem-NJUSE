package com.example.hotel.data.hotel;

import com.example.hotel.enums.BizRegion;
import com.example.hotel.po.Hotel;
import com.example.hotel.vo.HotelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HotelMapper {
    /**
     * 增加hotel
     * @param hotel
     * @return
     */
    int insertHotel(Hotel hotel);

    /**
     * 选择所有酒店
     * @return
     */
    List<HotelVO> selectAllHotel();

    /**
     * 通过id选择酒店
     * @param id
     * @return
     */
    HotelVO selectById(@Param("id") Integer id);

    /**
     * 通过id删除酒店
     * @param id
     * @return
     */

    int deleteById(@Param("id") Integer id);

    /**
     * 选择预定过的酒店
     * @param userId
     * @return
     */
    List<HotelVO> selectOrderedHotel(@Param("userId") Integer userId);

    /**
     * 选择管理员管理的酒店
     * @param managerId
     * @return
     */
    List<HotelVO> selectManagedHotel(@Param("managerId") Integer managerId);

    /**
     * 更新评分
     * @param id
     * @param evaluatePeople
     * @param rate
     * @return
     */
    int updateRate(@Param("id") Integer id,@Param("evaluatePeople") Integer evaluatePeople,@Param("rate") double rate);


}
