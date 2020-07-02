package com.example.hotel.controller.salesman;

import com.example.hotel.bl.salesman.SalesmanService;
import com.example.hotel.vo.RechargeUser;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/salesman")
public class SalesmanController {
    @Autowired
    SalesmanService salesmanService;

    @PostMapping("/getAllUser")
    public ResponseVO getAllUser(){
        return ResponseVO.buildSuccess(salesmanService.getAllUser());
    }

    @PostMapping("recharge")
    public ResponseVO recharge(@RequestBody RechargeUser rechargeUser){
        return ResponseVO.buildSuccess(salesmanService.recharge(rechargeUser));
    }
}
