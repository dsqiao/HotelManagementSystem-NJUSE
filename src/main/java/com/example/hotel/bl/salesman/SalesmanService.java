package com.example.hotel.bl.salesman;

import com.example.hotel.po.User;
import com.example.hotel.vo.RechargeUser;
import com.example.hotel.vo.ResponseVO;

import java.util.List;

public interface SalesmanService {
    /**
     * 获取所有的用户信息（邮箱、用户名、手机号）用于给用户充值信用
     * @return
     */
    List<User> getAllUser();

    ResponseVO recharge(RechargeUser rechargeUser);
}
