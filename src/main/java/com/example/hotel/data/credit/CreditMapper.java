package com.example.hotel.data.credit;


import com.example.hotel.po.Credit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface CreditMapper {

    int updateCredit(Credit credit);

    List<Credit> getUserCredit(@Param("userId") int userId);
}
