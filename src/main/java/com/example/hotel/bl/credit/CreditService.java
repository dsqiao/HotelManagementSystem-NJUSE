package com.example.hotel.bl.credit;

import com.example.hotel.vo.CreditVO;

import java.util.List;

public interface CreditService {
    List<CreditVO> getUserCredit(int userId);

    void updateCredit(int userId,String action,double change,double current);
}
