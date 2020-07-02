package com.example.hotel.bl.evaluation;

import com.example.hotel.po.Evaluation;
import com.example.hotel.vo.EvaluationVO;
import com.example.hotel.vo.ResponseVO;

import java.util.List;

public interface EvaluationService {
    /**
     * 添加评论
     */
    ResponseVO addEvaluation(EvaluationVO evaluationVO);

    /**
     * 获取评论
     */
    List<Evaluation> retrieveHotelEvaluationInfo(Integer hotelId);
}
