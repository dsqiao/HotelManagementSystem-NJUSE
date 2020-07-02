package com.example.hotel.blImpl.salesman;

import com.example.hotel.bl.credit.CreditService;
import com.example.hotel.bl.salesman.SalesmanService;
import com.example.hotel.data.salesman.SalesmanMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.po.User;
import com.example.hotel.vo.RechargeUser;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesmanServiceImpl implements SalesmanService {
    @Autowired
    SalesmanMapper salesmanMapper;

    @Autowired
     AccountMapper accountMapper;

    @Autowired
    CreditService creditService;

    @Override
    public List<User> getAllUser(){
        return salesmanMapper.getAllUser();
    }

    @Override
    public ResponseVO recharge(RechargeUser rechargeUser){
        String action="充值";
        User user=accountMapper.getAccountByName(rechargeUser.getEmail());
        creditService.updateCredit(user.getId(),action,rechargeUser.getCredit(),user.getCredit());
        return ResponseVO.buildSuccess(true);
    }
}
