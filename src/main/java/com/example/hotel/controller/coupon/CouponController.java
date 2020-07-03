package com.example.hotel.controller.coupon;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;



    @PostMapping("/hotelTargetMoney")
    public ResponseVO addCoupon(@RequestBody HotelTargetMoneyCouponVO hotelTargetMoneyCouponVO) {

        CouponVO couponVO = couponService.addHotelTargetMoneyCoupon(hotelTargetMoneyCouponVO);

        return ResponseVO.buildSuccess(couponVO);
    }

    @PostMapping("/hotelMultipleCoupon")
    public ResponseVO addCoupon(@RequestBody MultipleCouponVO multipleCouponVO) {
        CouponVO couponVO = couponService.addMultipleCoupon(multipleCouponVO);
        return  ResponseVO.buildSuccess(couponVO);
    }
    @PostMapping("/hotelTimeCoupon")

    public ResponseVO addCoupon(@RequestBody TimeCouponVO timeCouponVO){
        CouponVO couponVO=couponService.addTimeCoupon(timeCouponVO);
        return ResponseVO.buildSuccess(couponVO);
    }
    @PostMapping("/hotelBirthCoupon")
    public ResponseVO addCoupon(@RequestBody BirthCouponVO birthCouponVO){
        CouponVO couponVO=couponService.addBirthCoupon(birthCouponVO);
        return ResponseVO.buildSuccess(couponVO);
    }
    @PostMapping("/deleteHotelCoupons")
    public ResponseVO deleteHotelAllCoupons(@RequestParam Integer hotelId){

        return  ResponseVO.buildSuccess(couponService.deleteHotelAllCoupon(hotelId));
    }

    @GetMapping("/hotelAllCoupons")
    public ResponseVO getHotelAllCoupons(@RequestParam Integer hotelId) {
        return ResponseVO.buildSuccess(couponService.getHotelAllCoupon(hotelId));
    }

    @GetMapping("/orderMatchCoupons")
    public ResponseVO getOrderMatchCoupons(@RequestParam Integer userId,
                                           @RequestParam Integer hotelId,
                                           @RequestParam Double orderPrice,
                                           @RequestParam Integer roomNum,
                                           @RequestParam String checkIn,
                                           @RequestParam String checkOut) {
        OrderVO requestOrderVO = new OrderVO();
        requestOrderVO.setUserId(userId);
        requestOrderVO.setHotelId(hotelId);
        requestOrderVO.setPrice(orderPrice);
        requestOrderVO.setRoomNum(roomNum);
        requestOrderVO.setCheckInDate(checkIn);
        requestOrderVO.setCheckOutDate(checkOut);
        return ResponseVO.buildSuccess(couponService.getMatchOrderCoupon(requestOrderVO));
    }

    // 网站生日特惠
    @GetMapping("/getWebsiteCoupon1List")
    public ResponseVO getWebsiteCoupon1List() {
        return ResponseVO.buildSuccess(couponService.getWebsiteCoupon1List());
    }
    // 网站多间特惠
    @GetMapping("/getWebsiteCoupon2List")
    public ResponseVO getWebsiteCoupon2List() {
        return ResponseVO.buildSuccess(couponService.getWebsiteCoupon2List());
    }
    // 网站满减特惠
    @GetMapping("/getWebsiteCoupon3List")
    public ResponseVO getWebsiteCoupon3List() {
        return ResponseVO.buildSuccess(couponService.getWebsiteCoupon3List());
    }
    // 网站限时特惠
    @GetMapping("/getWebsiteCoupon4List")
    public ResponseVO getWebsiteCoupon4List() {
        return ResponseVO.buildSuccess(couponService.getWebsiteCoupon4List());
    }
}
