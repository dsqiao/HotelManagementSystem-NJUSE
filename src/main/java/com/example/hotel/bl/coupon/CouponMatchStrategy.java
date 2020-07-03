package com.example.hotel.bl.coupon;

import com.example.hotel.po.Coupon;

import com.example.hotel.vo.OrderVO;

public interface CouponMatchStrategy {
    /**
     * 检查命令与对应coupon是否匹配
     * @param orderVO order
     * @param coupon coupon
     * @return 是否匹配
     */
    boolean isMatch(OrderVO orderVO, Coupon coupon);

}
