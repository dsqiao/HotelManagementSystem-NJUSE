package com.example.hotel.bl.admin;

import com.example.hotel.po.User;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
public interface AdminService {

    /**
     * 添加酒店管理人员账号
     * @param userForm
     * @return
     */
    ResponseVO addManager(UserForm userForm);

    /**
     * 添加网站营销人员账号
     */
    ResponseVO addSalesman(UserForm userForm);
    /**
     * 获得所有酒店管理人员信息
     * @return
     */
    List<User> getAllManagers();

    /**
     * 获得所有客户的信息
     */
    List<User> getAllClients();

    /**
     * 获取所有网站营销人员的信息
     */
    List<User> getAllSalesman();

    /**
     * 删除用户
     * @return
     */
    ResponseVO deleteUser(Integer userId);
}
