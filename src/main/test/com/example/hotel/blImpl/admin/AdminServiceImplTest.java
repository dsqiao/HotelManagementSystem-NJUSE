package com.example.hotel.blImpl.admin;

import com.example.hotel.HotelApplication;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.blImpl.user.AccountServiceImpl;
import com.example.hotel.data.admin.AdminMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.User;
import com.example.hotel.vo.UserForm;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Service
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HotelApplication.class)
class AdminServiceImplTest {

    @Autowired
    AdminServiceImpl adminService;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    AccountMapper accountMapper;

    @Test
    void addManagerTest() {
        String email = "41771278@qq.com";
        String password = "123321";
        UserForm userForm = new UserForm();
        userForm.setEmail(email);
        userForm.setPassword(password);
        userForm.setUserType("HotelManager");
        adminService.addManager(userForm);
        List<User> managers = adminMapper.getAllManagers();
        User newAdd = managers.get(managers.size() - 1);
        assertEquals(email, newAdd.getEmail());
        assertEquals(password, newAdd.getPassword());
        assertEquals(UserType.HotelManager, newAdd.getUserType());

        adminMapper.deleteUser(newAdd.getId());
    }

    @Test
    void addSalesmanTest() {
        String email = "salesman@qq.com";
        String password = "salesman";
        UserForm userForm = new UserForm();
        userForm.setEmail(email);
        userForm.setPassword(password);
        userForm.setUserType("Salesman");
        adminService.addSalesman(userForm);
        List<User> salesman = adminMapper.getAllSalesman();
        User newAdd = salesman.get(salesman.size() - 1);
        assertEquals(email, newAdd.getEmail());
        assertEquals(password, newAdd.getPassword());
        assertEquals(UserType.Salesman, newAdd.getUserType());

        adminMapper.deleteUser(newAdd.getId());
    }

    @Test
    void getAllManagersTest() {
        User manager1 = new User();
        manager1.setEmail("manager1@qq.com");
        manager1.setPassword("123456");
        manager1.setUserType(UserType.HotelManager);

        User manager2 = new User();
        manager2.setEmail("manager2@qq.com");
        manager2.setPassword("1234567");
        manager2.setUserType(UserType.HotelManager);

        User manager3 = new User();
        manager3.setEmail("manager3@qq.com");
        manager3.setPassword("12345678");
        manager3.setUserType(UserType.HotelManager);

        adminMapper.addManager(manager1);
        adminMapper.addManager(manager2);
        adminMapper.addManager(manager3);

        List<User> managers = adminService.getAllManagers();
        User newAdd1 = managers.get(managers.size() - 3);
        User newAdd2 = managers.get(managers.size() - 2);
        User newAdd3 = managers.get(managers.size() - 1);

        assertEquals("manager1@qq.com", newAdd1.getEmail());
        assertEquals("123456", newAdd1.getPassword());
        assertEquals("manager2@qq.com", newAdd2.getEmail());
        assertEquals("1234567", newAdd2.getPassword());
        assertEquals("manager3@qq.com", newAdd3.getEmail());
        assertEquals("12345678", newAdd3.getPassword());

        adminMapper.deleteUser(newAdd1.getId());
        adminMapper.deleteUser(newAdd2.getId());
        adminMapper.deleteUser(newAdd3.getId());
    }

    @Test
    void getAllClients() {
        User client1 = new User();
        client1.setEmail("client1@qq.com");
        client1.setPassword("123456");
        client1.setUserType(UserType.Client);

        User client2 = new User();
        client2.setEmail("client2@qq.com");
        client2.setPassword("1234567");
        client2.setUserType(UserType.Client);

        User client3 = new User();
        client3.setEmail("client3@qq.com");
        client3.setPassword("12345678");
        client3.setUserType(UserType.Client);

        accountMapper.createNewAccount(client1);
        accountMapper.createNewAccount(client2);
        accountMapper.createNewAccount(client3);

        List<User> users = adminService.getAllClients();
        User newAdd1 = users.get(users.size() - 3);
        User newAdd2 = users.get(users.size() - 2);
        User newAdd3 = users.get(users.size() - 1);

        assertEquals("client1@qq.com", newAdd1.getEmail());
        assertEquals("123456", newAdd1.getPassword());
        assertEquals("client2@qq.com", newAdd2.getEmail());
        assertEquals("1234567", newAdd2.getPassword());
        assertEquals("client3@qq.com", newAdd3.getEmail());
        assertEquals("12345678", newAdd3.getPassword());
    }

    @Test
    void getAllSalesman() {
        User salesman1 = new User();
        salesman1.setEmail("salesman1@qq.com");
        salesman1.setPassword("123456");
        salesman1.setUserType(UserType.Salesman);

        User salesman2 = new User();
        salesman2.setEmail("salesman2@qq.com");
        salesman2.setPassword("1234567");
        salesman2.setUserType(UserType.Salesman);

        User salesman3 = new User();
        salesman3.setEmail("salesman3@qq.com");
        salesman3.setPassword("12345678");
        salesman3.setUserType(UserType.Salesman);

        adminMapper.addSalesman(salesman1);
        adminMapper.addSalesman(salesman2);
        adminMapper.addSalesman(salesman3);

        List<User> salesman = adminService.getAllSalesman();
        User newAdd1 = salesman.get(salesman.size() - 3);
        User newAdd2 = salesman.get(salesman.size() - 2);
        User newAdd3 = salesman.get(salesman.size() - 1);

        assertEquals("salesman1@qq.com", newAdd1.getEmail());
        assertEquals("123456", newAdd1.getPassword());
        assertEquals("salesman2@qq.com", newAdd2.getEmail());
        assertEquals("1234567", newAdd2.getPassword());
        assertEquals("salesman3@qq.com", newAdd3.getEmail());
        assertEquals("12345678", newAdd3.getPassword());

        adminMapper.deleteUser(newAdd1.getId());
        adminMapper.deleteUser(newAdd2.getId());
        adminMapper.deleteUser(newAdd3.getId());

    }
}