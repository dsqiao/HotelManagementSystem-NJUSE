package com.example.hotel.controller.credit;


import com.example.hotel.bl.credit.CreditService;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/credit")
public class CreditController {
    @Autowired
    private CreditService creditService;


    @GetMapping("/{userId}/userCredit")
    public ResponseVO getUserCredit(@PathVariable int userId){
        return ResponseVO.buildSuccess(creditService.getUserCredit(userId));
    }

    @PostMapping("/updateCredit")
    public ResponseVO updateCredit(@RequestParam int userId,@RequestParam String action,@RequestParam double change,@RequestParam double current){
        creditService.updateCredit(userId, action, change, current);
        return ResponseVO.buildSuccess(true);
    }
}
