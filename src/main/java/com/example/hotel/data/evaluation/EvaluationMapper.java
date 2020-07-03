package com.example.hotel.data.evaluation;


import com.example.hotel.po.Evaluation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EvaluationMapper {

    int addEvaluation(Evaluation evaluation);

    List<Evaluation> selectEvaluationsByHotelId(@Param("hotelId") Integer hotelId);
}
