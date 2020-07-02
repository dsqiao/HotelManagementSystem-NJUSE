package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.po.Coupon;
import com.example.hotel.po.User;
import com.example.hotel.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
@Service
public class BirthCouponStrategylmpl implements CouponMatchStrategy {
    @Autowired
    AccountMapper accountMapper;
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        User user= accountMapper.getAccountById(orderVO.getUserId());
        Date date = new Date();//获取当前的日期//
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String str = df.format(date);//获取String

        if(coupon.getCouponType()==1&& user.getMemberType().equals("企业会员") && user.getBirthday().equals(str)){
            return true;
        }
        return false;

    }
}
