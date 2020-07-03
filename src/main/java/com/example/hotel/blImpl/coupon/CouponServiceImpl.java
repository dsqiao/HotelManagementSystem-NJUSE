package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.data.coupon.CouponMapper;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;


@Service
public class CouponServiceImpl implements CouponService {


    private final  TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy;
    private final  TimeCouponStrategyImpl timeCouponStrategy;
    private final BirthCouponStrategylmpl birthCouponStrategy;
    private final MultipleRoomStrategylmpl multipleRoomStrategy;
    private final CouponMapper couponMapper;

    private static List<CouponMatchStrategy> strategyList = new ArrayList<>();

    /**
     * 添加多种策略到strategylist中
     * @param targetMoneyCouponStrategy
     * @param multipleRoomStrategy
     * @param timeCouponStrategy
     * @param birthCouponStrategy
     * @param couponMapper
     */
    @Autowired
    public CouponServiceImpl(TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy,
                             MultipleRoomStrategylmpl multipleRoomStrategy,
                             TimeCouponStrategyImpl timeCouponStrategy,
                             BirthCouponStrategylmpl birthCouponStrategy,
                             CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
        this.targetMoneyCouponStrategy = targetMoneyCouponStrategy;
        this.timeCouponStrategy = timeCouponStrategy;
        this.birthCouponStrategy=birthCouponStrategy;
        this.multipleRoomStrategy=multipleRoomStrategy;
        strategyList.add(targetMoneyCouponStrategy);
        strategyList.add(timeCouponStrategy);
        strategyList.add(birthCouponStrategy);
        strategyList.add(multipleRoomStrategy);
    }


    /**
     * 得到与改订单匹配的最好的优惠策略
     * @param orderVO
     * @return
     */
    @Override
    public List<Coupon> getMatchOrderCoupon(OrderVO orderVO) {

        List<Coupon> hotelCoupons = getHotelAllCoupon(orderVO.getHotelId());

        List<Coupon> availAbleCoupons = new ArrayList<>();
        Double maxdiscountMoney=0.0;
        Double mindiscount=10.0;

        for (int i = 0; i < hotelCoupons.size(); i++) {
            for (CouponMatchStrategy strategy : strategyList) {
                Coupon hotelCoupon=hotelCoupons.get(i);
                if (strategy.isMatch(orderVO, hotelCoupon)) {
                    if(hotelCoupon.getDiscount()<=mindiscount||hotelCoupon.getDiscountMoney()>=maxdiscountMoney){
                    availAbleCoupons.add(hotelCoupon);
                    mindiscount=min(hotelCoupon.getDiscount(),mindiscount);
                    maxdiscountMoney= max(hotelCoupon.getDiscountMoney(),maxdiscountMoney);
                    }
                }
            }
        }
        for(int j=0;j<availAbleCoupons.size();j++){
            if(availAbleCoupons.get(j).getDiscountMoney()<maxdiscountMoney&&availAbleCoupons.get(j).getDiscount()>mindiscount){
                availAbleCoupons.remove(j);
            }
        }

        return availAbleCoupons;
    }

    @Override
    public List<Coupon> getHotelAllCoupon(Integer hotelId) {
        List<Coupon> hotelCoupons = couponMapper.selectByHotelId(hotelId);
        List<Coupon> AllCoupons=couponMapper.selectByHotelId(-1);
        hotelCoupons.addAll(AllCoupons);
        return hotelCoupons;
    }
    @Override
    public List<Coupon> getWebsiteCoupon1List(){
        List<Coupon> coupon1 = couponMapper.selectWebsiteCoupon1();
        System.out.println(coupon1.size());
        return couponMapper.selectWebsiteCoupon1();
    }
    @Override
    public List<Coupon> getWebsiteCoupon2List(){
        return couponMapper.selectWebsiteCoupon2();
    }
    @Override
    public List<Coupon> getWebsiteCoupon3List(){
        return couponMapper.selectWebsiteCoupon3();
    }
    @Override
    public List<Coupon> getWebsiteCoupon4List(){
        return couponMapper.selectWebsiteCoupon4();
    }

    @Override
    public CouponVO addHotelTargetMoneyCoupon(HotelTargetMoneyCouponVO couponVO) {
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setTargetMoney(couponVO.getTargetMoney());
        coupon.setHotelId(couponVO.getHotelId());
        coupon.setDiscountMoney(couponVO.getDiscountMoney());
        coupon.setStatus(1);
        int result = couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        return couponVO;
    }
    @Override
    public CouponVO addTimeCoupon(TimeCouponVO couponVO){
        Coupon coupon=new Coupon();
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setStartTime(couponVO.getStartTime());
        coupon.setHotelId(couponVO.getHotelId());
        coupon.setEndTime(couponVO.getEndTime());
        coupon.setDiscountMoney(couponVO.getDiscountMoney());
        coupon.setDiscount(couponVO.getDiscount());
        coupon.setStatus(1);
        int result=couponMapper.insertCoupon(coupon);
        couponVO.setId(result);

        return couponVO;

    }

    @Override
    public CouponVO addMultipleCoupon(MultipleCouponVO couponVO) {
        Coupon coupon =new Coupon();
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setHotelId(couponVO.getHotelId());
        coupon.setDiscountMoney(couponVO.getDiscountMoney());
        coupon.setDiscount(couponVO.getDiscount());
        coupon.setTargetRoomNum(couponVO.getNumOfRoom());
        coupon.setStatus(1);
        int result=couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        System.out.println(couponVO.getId());
        return couponVO;
    }
    @Override
    public  CouponVO addBirthCoupon(BirthCouponVO couponVO){
        Coupon coupon=new Coupon();
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setHotelId(couponVO.getHotelId());
        coupon.setDiscount(couponVO.getDiscount());
        coupon.setStatus(1);
        int result=couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        return couponVO;
    }

    @Override
    public List<Coupon> deleteHotelAllCoupon(Integer hotelId) {
        couponMapper.deleteByHotelId(hotelId);
        List<Coupon> hotelCoupon = couponMapper.selectByHotelId(hotelId);
        return hotelCoupon;
    }
}
