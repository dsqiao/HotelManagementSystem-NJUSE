package com.example.hotel.controller.evaluation;


import com.example.hotel.bl.evaluation.EvaluationService;
import com.example.hotel.vo.EvaluationVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/evaluate")
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;

    @PostMapping("/addEvaluation")
    public ResponseVO addEvaluation(@RequestBody EvaluationVO evaluationVO) {
        return evaluationService.addEvaluation(evaluationVO);
    }
}
