package com.example.hotel.data.coupon;

import com.example.hotel.po.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CouponMapper {
    int insertCoupon(Coupon coupon);

    List<Coupon> selectByHotelId(Integer hotelId);

    List<Coupon> selectWebsiteCoupon1();
    List<Coupon> selectWebsiteCoupon2();
    List<Coupon> selectWebsiteCoupon3();
    List<Coupon> selectWebsiteCoupon4();

    void deleteByHotelId(Integer hotelId);
}
