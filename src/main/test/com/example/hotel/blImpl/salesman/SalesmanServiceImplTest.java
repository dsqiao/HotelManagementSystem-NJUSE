package com.example.hotel.blImpl.salesman;

import com.example.hotel.HotelApplication;
import com.example.hotel.bl.salesman.SalesmanService;
import com.example.hotel.blImpl.admin.AdminServiceImpl;
import com.example.hotel.data.salesman.SalesmanMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.User;
import com.example.hotel.vo.RechargeUser;
import com.example.hotel.vo.UserForm;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HotelApplication.class)
class SalesmanServiceImplTest {

    @Autowired
    SalesmanServiceImpl salesmanService;
    @Autowired
    AdminServiceImpl adminService;
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    SalesmanMapper salesmanMapper;

    @Test
    /**
     * 单元测试：获取用户信息列表（共3个用户）
     */
    void getAllUser() {
        User c1 = new User();
        c1.setEmail("m1@qq.com");
        c1.setPassword("123");
        c1.setUserType(UserType.Client);

        User c2 = new User();
        c2.setEmail("s1@qq.com");
        c2.setPassword("321");
        c2.setUserType(UserType.Client);

        User c3 = new User();
        c3.setEmail("m2@qq.com");
        c3.setPassword("1234");
        c3.setUserType(UserType.Client);

        accountMapper.createNewAccount(c1);
        accountMapper.createNewAccount(c2);
        accountMapper.createNewAccount(c3);

        List<User> users = salesmanService.getAllUser();
        System.out.println(users.size());
        User newAdd1 = users.get(users.size() - 3);
        User newAdd2 = users.get(users.size() - 2);
        User newAdd3 = users.get(users.size() - 1);

        assertEquals("m1@qq.com", newAdd1.getEmail());
        assertEquals("123", newAdd1.getPassword());
        assertEquals("s1@qq.com", newAdd2.getEmail());
        assertEquals("321", newAdd2.getPassword());
        assertEquals("m2@qq.com", newAdd3.getEmail());
        assertEquals("1234", newAdd3.getPassword());

        adminService.deleteUser(newAdd1.getId());
        adminService.deleteUser(newAdd2.getId());
        adminService.deleteUser(newAdd3.getId());
    }


    /**
     * 功能测试：用户信用充值
     */
    @Test
    void recharge() {
        User user = new User();
        user.setCredit(100);
        user.setUserType(UserType.Client);
        user.setEmail("9@qq.com");
        user.setPassword("123321");

        RechargeUser rechargeUser = new RechargeUser();
        rechargeUser.setEmail("9@qq.com");
        rechargeUser.setCredit(10);

        accountMapper.createNewAccount(user);
        salesmanService.recharge(rechargeUser);


        List<User> users = salesmanService.getAllUser();
        User newAdd = users.get(users.size() - 1);
        assertEquals(110, newAdd.getCredit());

        adminService.deleteUser(newAdd.getId());
    }
}