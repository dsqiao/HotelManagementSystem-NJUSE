package com.example.hotel.blImpl.coupon;


import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.blImpl.user.AccountServiceImpl;
import com.example.hotel.data.coupon.CouponMapper;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CouponServiceImplTest {
    @Autowired
    CouponServiceImpl couponService;
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    AccountServiceImpl accountService;


    @Test
    //单元测试
    void getMatchOrderCoupon1() {
        //限时策略检验
        deleteHotelAllCoupon();
        addTimeCoupon();
        OrderVO orderVO1=new OrderVO();
        orderVO1.setCheckInDate("2020-07-01 12:00:00");
        orderVO1.setCheckOutDate("2020-07-25 12:00:00");
        orderVO1.setHotelId(1);
        orderVO1.setCreateDate("2020-06-27");
        orderVO1.setClientName("67");
        orderVO1.setPhoneNumber("13636376767");
        orderVO1.setRoomNum(3);
        orderVO1.setHaveChild(true);
        orderVO1.setUserId(5);
        orderVO1.setPeopleNum(3);
        orderVO1.setRoomType("PresidentBed");
        orderVO1.setHotelName("汉庭酒店");
        orderVO1.setPrice(500.0);
        Coupon coupon1=couponService.getMatchOrderCoupon(orderVO1).get(0);
        assertEquals(coupon1.getCouponName(),"限时优惠");
        //满减策略检验
        deleteHotelAllCoupon();
        addHotelTargetMoneyCoupon();
        Coupon coupon2=couponService.getMatchOrderCoupon(orderVO1).get(0);
        assertEquals(coupon2.getCouponName(),"满减优惠");
        //多间策略检验
        deleteHotelAllCoupon();
        addMultipleCoupon();
        Coupon coupon3=couponService.getMatchOrderCoupon(orderVO1).get(0);
        assertEquals(coupon3.getCouponName(),"多间优惠");
        //生日策略检验
        deleteHotelAllCoupon();
        addBirthCoupon();
        accountService.beMember(5,"企业会员", LocalDate.now().toString(),"王氏集团");
        Coupon coupon4=couponService.getMatchOrderCoupon(orderVO1).get(0);
        assertEquals(coupon4.getCouponName(),"生日特惠");
    }
    @Test
    //集成测试
    void getMatchOrderCoupon2() {
        deleteHotelAllCoupon();
        addTimeCoupon();
        addBirthCoupon();
        addMultipleCoupon();
        addHotelTargetMoneyCoupon();
        OrderVO orderVO=new OrderVO();
        orderVO.setCheckInDate("2020-07-01 12:00:00");
        orderVO.setCheckOutDate("2020-07-25 12:00:00");
        orderVO.setHotelId(1);
        orderVO.setCreateDate("2020-06-27");
        orderVO.setClientName("67");
        orderVO.setPhoneNumber("13636376767");
        orderVO.setRoomNum(3);
        orderVO.setHaveChild(true);
        orderVO.setUserId(5);
        orderVO.setPeopleNum(3);
        orderVO.setRoomType("PresidentBed");
        orderVO.setHotelName("汉庭酒店");
        orderVO.setPrice(500.0);
        List<Coupon> coupons=couponService.getMatchOrderCoupon(orderVO);
        for(Coupon coupon:coupons){
            System.out.println(coupon.getCouponName());
        }
        assertEquals(coupons.size(),3);
    }

    @org.junit.jupiter.api.Test
    void getHotelAllCoupon() {
        List<Coupon> testList = couponService.getHotelAllCoupon(1);
        List<Coupon> couponList = couponMapper.selectByHotelId(1);
        couponList.addAll(couponMapper.selectByHotelId(-1));
        for(int i=0;i<testList.size();i++){
            assertEquals(testList.get(i).getId(),couponList.get(i).getId());
        }
    }

    @org.junit.jupiter.api.Test
    void getWebsiteCoupon1List() {
        List<Coupon> testList=couponService.getWebsiteCoupon1List();
        List<Coupon> couponList = couponMapper.selectWebsiteCoupon1();
        for(int i=0;i<testList.size();i++){
            assertEquals(testList.get(i).getId(),couponList.get(i).getId());
        }

    }

    @org.junit.jupiter.api.Test
    void getWebsiteCoupon2List() {
        List<Coupon> testList=couponService.getWebsiteCoupon2List();
        List<Coupon> couponList = couponMapper.selectWebsiteCoupon2();
        for(int i=0;i<testList.size();i++){
            assertEquals(testList.get(i).getId(),couponList.get(i).getId());
        }
    }

    @org.junit.jupiter.api.Test
    void getWebsiteCoupon3List() {
        List<Coupon> testList=couponService.getWebsiteCoupon3List();
        List<Coupon> couponList = couponMapper.selectWebsiteCoupon3();
        for(int i=0;i<testList.size();i++){
            assertEquals(testList.get(i).getId(),couponList.get(i).getId());
        }
    }

    @org.junit.jupiter.api.Test
    void getWebsiteCoupon4List() {
        List<Coupon> testList=couponService.getWebsiteCoupon4List();
        List<Coupon> couponList = couponMapper.selectWebsiteCoupon4();
        for(int i=0;i<testList.size();i++){
            assertEquals(testList.get(i).getId(),couponList.get(i).getId());
        }
    }

    @org.junit.jupiter.api.Test
    void addHotelTargetMoneyCoupon() {
        HotelTargetMoneyCouponVO couponVO=new HotelTargetMoneyCouponVO();
        couponVO.setDiscountMoney(100);
        couponVO.setHotelId(1);
        couponVO.setType(3);
        couponVO.setName("满减优惠");
        couponVO.setTargetMoney(200);
        couponService.addHotelTargetMoneyCoupon(couponVO);

    }

    @org.junit.jupiter.api.Test
    void addTimeCoupon() {
        TimeCouponVO couponVO=new TimeCouponVO();
        couponVO.setDiscount(9);
        couponVO.setStartTime(LocalDateTime.of(2020,6,30,22,40));
        couponVO.setEndTime(LocalDateTime.of(2020,7,30,22,40));
        couponVO.setHotelid(1);
        couponVO.setName("限时优惠" );
        couponVO.setDiscountMoney(0);
        couponVO.setType(4);
        couponService.addTimeCoupon(couponVO);
    }

    @org.junit.jupiter.api.Test
    void addMultipleCoupon() {
        MultipleCouponVO couponVO=new MultipleCouponVO();
        couponVO.setDiscountMoney(100);
        couponVO.setHotelId(1);
        couponVO.setName("多间优惠");
        couponVO.setNumofRoom(3);
        couponVO.setType(2);
        couponService.addMultipleCoupon(couponVO);
    }

    @org.junit.jupiter.api.Test
    void addBirthCoupon() {
        BirthCouponVO couponVO=new BirthCouponVO();
        couponVO.setHotelId(1);
        couponVO.setName("生日特惠");
        couponVO.setType(1);
        couponVO.setDiscount(8.5);
        couponService.addBirthCoupon(couponVO);
    }

    @org.junit.jupiter.api.Test
    void deleteHotelAllCoupon() {
        couponService.deleteHotelAllCoupon(1);
        assertEquals(couponMapper.selectByHotelId(1).size(),0);
    }
}