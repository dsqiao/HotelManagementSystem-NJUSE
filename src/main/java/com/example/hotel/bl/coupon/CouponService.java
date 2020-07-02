package com.example.hotel.bl.coupon;

import com.example.hotel.po.Coupon;
import com.example.hotel.vo.*;

import java.util.List;

public interface CouponService {
    /**
     * 返回某一订单可用的优惠策略列表
     * @param orderVO
     * @return
     */
    List<Coupon> getMatchOrderCoupon(OrderVO orderVO);

    /**
     * 查看某个酒店提供的所有优惠策略（包括失效的）
     * @param hotelId
     * @return
     */
    List<Coupon> getHotelAllCoupon(Integer hotelId);

    /**
     * 获得四种类型的网站优惠
     * @return
     */
    List<Coupon> getWebsiteCoupon1List();
    List<Coupon> getWebsiteCoupon2List();
    List<Coupon> getWebsiteCoupon3List();
    List<Coupon> getWebsiteCoupon4List();

    /**
     * 删除该酒店所有优惠策略
     * @param hotelId
     */
    List<Coupon> deleteHotelAllCoupon(Integer hotelId);

    /**
     * 添加酒店满减优惠策略
     * @param couponVO
     * @return
     */
    CouponVO addHotelTargetMoneyCoupon(HotelTargetMoneyCouponVO couponVO);

    /**
     * *
     * @param couponVO
     * @return
     */
    CouponVO addTimeCoupon(TimeCouponVO couponVO);

    /**
     *
     * @param couponVO
     * @return
     */
    CouponVO addMultipleCoupon(MultipleCouponVO couponVO);

    /**
     *
     * @param couponVO
     * @return
     */
    CouponVO addBirthCoupon(BirthCouponVO couponVO);
}
