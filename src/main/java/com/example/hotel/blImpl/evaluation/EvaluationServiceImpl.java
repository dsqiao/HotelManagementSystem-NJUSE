package com.example.hotel.blImpl.evaluation;

import com.example.hotel.bl.evaluation.EvaluationService;
import com.example.hotel.data.evaluation.EvaluationMapper;
import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.po.Evaluation;
import com.example.hotel.vo.EvaluationVO;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {
    @Autowired
    EvaluationMapper evaluationMapper;
    @Autowired
    HotelMapper hotelMapper;

    @Override
    public ResponseVO addEvaluation(EvaluationVO evaluationVO){
        int userId = evaluationVO.getUserId();
        int hotelId = evaluationVO.getHotelId();
        int rate = evaluationVO.getRate();
        HotelVO hotelVO=hotelMapper.selectById(hotelId);
        int evaluatePeople=hotelVO.getEvaluatePeople()+1;
        double totalRate=(hotelVO.getRate()*(evaluatePeople-1)+(double)rate)/evaluatePeople;
        BigDecimal b   =   new   BigDecimal(totalRate);
        totalRate = b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
        hotelMapper.updateRate(hotelId,evaluatePeople,totalRate);
        String comment = evaluationVO.getEvaluation();
        try {
            Evaluation evaluation = new Evaluation();
            evaluation.setEvaluation(comment);
            evaluation.setRate(rate);
            evaluation.setUserId(userId);
            evaluation.setHotelId(hotelId);
            evaluationMapper.addEvaluation(evaluation);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("评论失败");
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<Evaluation> retrieveHotelEvaluationInfo(Integer hotelId){
        return evaluationMapper.selectEvaluationsByHotelId(hotelId);
    }
}
