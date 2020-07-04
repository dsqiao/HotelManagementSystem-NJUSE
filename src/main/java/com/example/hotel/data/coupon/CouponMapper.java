package com.example.hotel.data.coupon;

import com.example.hotel.po.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CouponMapper {
    /**
     * 增加coupon
     * @param coupon
     * @return
     */
    int insertCoupon(Coupon coupon);

    /**
     * 通过hotelId得到该酒店的coupon
     * @param hotelId
     * @return
     */
    List<Coupon> selectByHotelId(Integer hotelId);

    /**
     * 得到网站优惠
     * @return
     */
    List<Coupon> selectWebsiteCoupon1();//生日
    List<Coupon> selectWebsiteCoupon2();//多间
    List<Coupon> selectWebsiteCoupon3();//满减
    List<Coupon> selectWebsiteCoupon4();//限时

    /**
     * 删除酒店所有coupon
     * @param hotelId
     */
    void deleteByHotelId(Integer hotelId);
}
