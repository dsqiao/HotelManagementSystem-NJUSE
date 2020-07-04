package com.example.hotel.data.admin;

import com.example.hotel.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Mapper
@Repository
public interface AdminMapper {
    /**
     * 增加管理人员
     * @param user
     * @return
     */
    int addManager(User user);

    /**
     * 增加网络营销人员
     * @param user
     * @return
     */
    int addSalesman(User user);

    /**
     * 得到所有管理者
     * @return
     */
    List<User> getAllManagers();

    /**
     * 得到所有客户
     * @return
     */
    List<User> getAllClients();

    /**
     * 得到所有网络营销人员
     * @return
     */
    List<User> getAllSalesman();

    /**
     * 删除客户
     * @param userId
     */
    void deleteUser(Integer userId);
}
