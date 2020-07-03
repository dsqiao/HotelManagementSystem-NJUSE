package com.example.hotel.bl.credit;

import com.example.hotel.vo.CreditVO;

import java.util.List;

public interface CreditService {
    /**
     * 得到该用户信用值
     * @param userId
     * @return creditVO的list
     */
    List<CreditVO> getUserCredit(int userId);

    /**
     *更新信用值
     * @param userId
     * @param action
     * @param change
     * @param current
     */
    void updateCredit(int userId,String action,double change,double current);
}
