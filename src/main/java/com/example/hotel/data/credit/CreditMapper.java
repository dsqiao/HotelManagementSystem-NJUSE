package com.example.hotel.data.credit;


import com.example.hotel.po.Credit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface CreditMapper {
    /**
     * 更新呢信用值
     * @param credit
     * @return
     */

    int updateCredit(Credit credit);

    /**
     * 得到某用户的信用值
     * @param userId
     * @return
     */
    List<Credit> getUserCredit(@Param("userId") int userId);
}
