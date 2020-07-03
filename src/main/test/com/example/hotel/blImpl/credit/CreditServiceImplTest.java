package com.example.hotel.blImpl.credit;

import com.example.hotel.HotelApplication;
import com.example.hotel.bl.credit.CreditService;
import com.example.hotel.po.Credit;
import com.example.hotel.vo.CreditVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HotelApplication.class)
class CreditServiceImplTest {


    @Autowired
    private CreditService creditService;

    @Test
    /**
     *
     */
    void getUserCreditTest1() {
        List<CreditVO> creditList=creditService.getUserCredit(4);
        assert creditList.size()>0;
    }

    @Test
    /**
     * 充值
     */
    void updateCreditTest1() {
        String action="充值";
        double change=100;
        double current=100;
        creditService.updateCredit(4,action,change,current);
        current+=change;
        List<CreditVO> creditVOS=creditService.getUserCredit(4);
        CreditVO latest=creditVOS.get(creditVOS.size()-1);
        assertEquals(latest.getAction(),action);
        assertEquals(latest.getChange(),"+"+change);
        assertEquals(latest.getCurrent(),String.valueOf(current));
    }

    @Test
    /**
     * 执行订单
     */
    void updateCreditTest2() {
        String action="订单执行";
        double change=15.8;
        double current=100;
        creditService.updateCredit(4,action,change,current);
        current+=change;
        List<CreditVO> creditVOS=creditService.getUserCredit(4);
        CreditVO latest=creditVOS.get(creditVOS.size()-1);
        assertEquals(latest.getAction(),action);
        assertEquals(latest.getChange(),"+"+(double)Math.round(change));//数据库里double固定长度为(255,0)，因此四舍五入
        assertEquals(latest.getCurrent(),String.valueOf((double)Math.round(current)));
    }


    @Test
    /**
     * 订单异常，信用值扣完大于0
     */
    void updateCreditTest3() {
        String action="订单异常";
        double change=15.8;
        double current=100;
        creditService.updateCredit(4,action,change,current);
        current-=change;
        List<CreditVO> creditVOS=creditService.getUserCredit(4);
        CreditVO latest=creditVOS.get(creditVOS.size()-1);
        assertEquals(latest.getAction(),action);
        assertEquals(latest.getChange(),"-"+(double)Math.round(change));//数据库里double固定长度为(255,0)，因此四舍五入
        assertEquals(latest.getCurrent(),String.valueOf((double) Math.round(current)));
    }


    @Test
    /**
     * 订单异常，信用值扣完等于0
     */
    void updateCreditTest4() {
        String action="订单异常";
        double change=100;
        double current=100;
        creditService.updateCredit(4,action,change,current);
        current-=change;
        List<CreditVO> creditVOS=creditService.getUserCredit(4);
        CreditVO latest=creditVOS.get(creditVOS.size()-1);
        assertEquals(latest.getAction(),action);
        assertEquals(latest.getChange(),"-"+(double)Math.round(change));//数据库里double固定长度为(255,0)，因此四舍五入
        assertEquals(latest.getCurrent(),String.valueOf((double) Math.round(current)));
    }


    @Test
    /**
     * 订单异常，信用值扣完小于0
     */
    void updateCreditTest5() {
        String action="订单异常";
        double change=152.1;
        double current=100;
        creditService.updateCredit(4,action,change,current);
        current-=change;
        List<CreditVO> creditVOS=creditService.getUserCredit(4);
        CreditVO latest=creditVOS.get(creditVOS.size()-1);
        assertEquals(latest.getAction(),action);
        assertEquals(latest.getChange(),"-"+(double)Math.round(change));//数据库里double固定长度为(255,0)，因此四舍五入
        assertEquals(latest.getCurrent(),String.valueOf((double) Math.round(current)));
    }
}