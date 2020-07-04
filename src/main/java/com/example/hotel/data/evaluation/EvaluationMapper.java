package com.example.hotel.data.evaluation;


import com.example.hotel.po.Evaluation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EvaluationMapper {
    /**
     * 增加评价
     * @param evaluation
     * @return
     */
    int addEvaluation(Evaluation evaluation);

    /**
     * 选择某酒店的评价
     * @param hotelId
     * @return
     */
    List<Evaluation> selectEvaluationsByHotelId(@Param("hotelId") Integer hotelId);
}
