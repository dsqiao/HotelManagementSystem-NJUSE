package com.example.hotel.data.salesman;


import com.example.hotel.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SalesmanMapper {
    List<User> getAllUser();
//    int recharge(@Param("email") String email, @Param("credit") Integer credit);
}
