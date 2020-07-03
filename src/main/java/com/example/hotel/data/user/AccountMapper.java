package com.example.hotel.data.user;

import com.example.hotel.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface AccountMapper {

    /**
     * 创建一个新的账号
     *
     * @return
     */
     int createNewAccount(User user);

    /**
     * 根据用户名查找账号
     * @param email
     * @return
     */
     User getAccountByName(@Param("email") String email);

     User getAccountById(@Param("id") int id);

    /**
     * 更新用户信息
     * @param id
     * @param password
     * @param username
     * @param email
     * @param phonenumber
     * @return
     */
    int updateAccount(
            @Param("id") int id,
            @Param("password") String password,
            @Param("userName") String username,
            @Param("email") String email,
            @Param("phoneNumber") String phonenumber,
            @Param("avatarUrl") String avatarUrl
    );

     int beMember(@Param("id") int id, @Param("memberType") String memberType, @Param("birthday") String birthday, @Param("companyName") String companyName);

     int updateCredit(@Param("id") int id,@Param("credit") double credit);

}
