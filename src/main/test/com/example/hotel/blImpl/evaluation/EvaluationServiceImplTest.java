package com.example.hotel.blImpl.evaluation;

import com.example.hotel.HotelApplication;
import com.example.hotel.data.evaluation.EvaluationMapper;
import com.example.hotel.po.Evaluation;
import com.example.hotel.vo.EvaluationVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HotelApplication.class)
class EvaluationServiceImplTest {

    @Autowired
    EvaluationServiceImpl evaluationService;
    @Autowired
    EvaluationMapper evaluationMapper;


    @Test
    void addEvaluationTest() {
        EvaluationVO evaluation = new EvaluationVO();
        evaluation.setHotelId(1);
        evaluation.setUserId(2);
        evaluation.setRate(5);
        evaluation.setEvaluation("还行");
        evaluationService.addEvaluation(evaluation);
        List<Evaluation> evaluations = evaluationMapper.selectEvaluationsByHotelId(1);
        Evaluation newAdd = evaluations.get(evaluations.size() - 1);
        assertEquals(Integer.valueOf(5), newAdd.getRate());
        assertEquals("还行", newAdd.getEvaluation());
    }

    @Test
    void retrieveHotelEvaluationInfoTest() {
        Evaluation evaluation = new Evaluation();
        evaluation.setHotelId(2);
        evaluation.setUserId(2);
        evaluation.setRate(5);
        evaluation.setEvaluation("还行");

        Evaluation evaluation2 = new Evaluation();
        evaluation2.setHotelId(2);
        evaluation2.setUserId(3);
        evaluation2.setRate(4);
        evaluation2.setEvaluation("还可以");

        evaluationMapper.addEvaluation(evaluation);
        evaluationMapper.addEvaluation(evaluation2);

        List<Evaluation> evaluations = evaluationService.retrieveHotelEvaluationInfo(2);
        Evaluation newAdd1 = evaluations.get(evaluations.size() - 2);
        Evaluation newAdd2 = evaluations.get(evaluations.size() - 1);

        assertEquals("还行", newAdd1.getEvaluation());
        assertEquals(Integer.valueOf(5), newAdd1.getRate());
        assertEquals(Integer.valueOf(2), newAdd1.getUserId());

        assertEquals("还可以", newAdd2.getEvaluation());
        assertEquals(Integer.valueOf(4), newAdd2.getRate());
        assertEquals(Integer.valueOf(3), newAdd2.getUserId());
    }
}