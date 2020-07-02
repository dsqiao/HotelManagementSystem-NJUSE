package com.example.hotel.blImpl.credit;

import com.example.hotel.bl.credit.CreditService;
import com.example.hotel.data.credit.CreditMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.po.Credit;
import com.example.hotel.vo.CreditVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class CreditServiceImpl implements CreditService {
    @Autowired
    CreditMapper creditMapper;

    @Autowired
    AccountMapper accountMapper;

    /**
     * 信用值增加的操作
     */
    private List<String> addAction=new ArrayList<String>(){{
        add("充值");
        add("初始化");
        add("订单执行");
    }};


    /**
     * 信用值减少的操作
     */
    private List<String> subAction=new ArrayList<String>(){{
        add("订单异常");
    }};


    /**
     *  获取用户的所有信用的变化
     * @param userId
     * @return
     */
    @Override
    public List<CreditVO> getUserCredit(int userId) {
        List<Credit> creditList=creditMapper.getUserCredit(userId);
        List<CreditVO> creditVOList=new ArrayList<>();
        for(int i=0;i<creditList.size();++i){
            Credit current=creditList.get(i);
            CreditVO currentVO=new CreditVO();
            currentVO.setUserId(current.getUserId());
            currentVO.setAction(current.getAction());
            String signChange=current.getChange()>=0?"+":"-";
            currentVO.setChange(signChange+Math.abs(current.getChange()));
            String signCurrent=current.getCurrent()>=0?"":"-";
            currentVO.setCurrent(signCurrent+Math.abs(current.getCurrent()));
            String time=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(current.getTime());
            currentVO.setTime(time);
            creditVOList.add(currentVO);
        }
        return creditVOList;
    }


    /**
     *  根据action的类型更新信用的值
     * @param userId
     * @param action
     * @param change
     * @param current
     */
    @Override
    public void updateCredit(int userId,String action,double change,double current) {
        Credit credit=new Credit();
        build(credit,userId,action,change,current);
        update(credit);
    }


    private void handleChange(Credit credit,String action,double change,double current){
        if(addAction.contains(action)){
            credit.setChange(change);
            credit.setCurrent(current+change);
        }
        else if(subAction.contains(action)){
            credit.setChange(-change);
            credit.setCurrent(current-change);
        }
    }


    private void build(Credit credit, int userId, String action, double change, double current){
        credit.setUserId(userId);
        credit.setAction(action);
        handleChange(credit,action,change,current);
        LocalDateTime localDateTime=LocalDateTime.now();
        credit.setTime(localDateTime);
    }

    private void update(Credit credit){
        creditMapper.updateCredit(credit);
        accountMapper.updateCredit(credit.getUserId(),credit.getCurrent());
    }

}
