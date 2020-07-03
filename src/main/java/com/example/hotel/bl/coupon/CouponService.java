package com.example.hotel.bl.coupon;

import com.example.hotel.po.Coupon;
import com.example.hotel.vo.*;

import java.util.List;

public interface CouponService {
    /**
     * 返回某一订单可用的优惠策略列表
     * @param orderVO order
     * @return coupon的list
     */
    List<Coupon> getMatchOrderCoupon(OrderVO orderVO);

    /**
     * 查看某个酒店提供的所有优惠策略（包括失效的）
     * @param hotelId hotelId
     * @return coupon的list
     */
    List<Coupon> getHotelAllCoupon(Integer hotelId);

    /**
     * 获得四种类型的网站优惠
     * @return coupon的list
     */
    List<Coupon> getWebsiteCoupon1List();//生日
    List<Coupon> getWebsiteCoupon2List();//多间
    List<Coupon> getWebsiteCoupon3List();//满减
    List<Coupon> getWebsiteCoupon4List();//限时

    /**
     * 删除该酒店所有优惠策略
     * @param hotelId
     * @return  coupon的list
     */
    List<Coupon> deleteHotelAllCoupon(Integer hotelId);

    /**
     * 添加酒店满减优惠策略
     * @param couponVO
     * @return couponVO
     */
    CouponVO addHotelTargetMoneyCoupon(HotelTargetMoneyCouponVO couponVO);

    /**
     * 添加酒店限时策略
     * @param couponVO
     * @return couponVO
     */
    CouponVO addTimeCoupon(TimeCouponVO couponVO);

    /**
     *添加酒店多间优惠策略
     * @param couponVO
     * @return  couponVO
     */
    CouponVO addMultipleCoupon(MultipleCouponVO couponVO);

    /**
     *添加生日特惠
     * @param couponVO
     * @return couponVO
     */
    CouponVO addBirthCoupon(BirthCouponVO couponVO);
}
