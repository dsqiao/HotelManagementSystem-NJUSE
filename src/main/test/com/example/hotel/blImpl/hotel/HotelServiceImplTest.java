package com.example.hotel.blImpl.hotel;

import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.enums.BizRegion;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.OrderVO;
import org.apache.catalina.User;
import org.apache.catalina.filters.ExpiresFilter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class HotelServiceImplTest {
    @Autowired
    HotelServiceImpl hotelService;
    @Autowired
    HotelMapper hotelMapper;


    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    /**
     * 功能测试：添加酒店（添加一个酒店）
     */
    void addHotel() throws ServiceException {
        HotelVO hotelVO=new HotelVO();
        hotelVO.setAddress("北京朝阳区");
        hotelVO.setBizRegion("WanDa");
        hotelVO.setDescription("一家北京的好旅馆");
        hotelVO.setName("朝阳旅馆");
        hotelVO.setPhoneNum("1234556");
        hotelVO.setManagerId(8);
        hotelVO.setRate(4.2);
        hotelVO.setHotelStar("Four");
        int size1=hotelService.retrieveHotels().size();
        hotelService.addHotel(hotelVO);
        int size2=hotelService.retrieveHotels().size();
        assertEquals(size1,size2-1);
    }


    /**
     * 单元测试：获取所有酒店的列表
     */
    @Test
    void retrieveHotels() {
        List<HotelVO> hotelVOS=hotelMapper.selectAllHotel();
        assertEquals(hotelVOS.size(),hotelService.retrieveHotels().size());
    }


    /**
     * 单元测试：获取单个酒店的细节信息
     */
    @Test
    void retrieveHotelDetails() {
        HotelVO hotelVO=hotelService.retrieveHotelDetails(1);
        assertEquals(hotelVO.getAddress(),"上海市虹口区四川北路1688号");
        assertEquals(hotelVO.getBizRegion(),"XiDan");
        assertEquals(hotelVO.getName(),"汉庭酒店");
        assertEquals(hotelVO.getDescription(),"欢迎您入住");
        assertEquals(hotelVO.getPhoneNum(),"18293738191");
        assertEquals(hotelVO.getHotelStar(), "Four");
        assertTrue(hotelVO.getRate()==4.80);
        assertEquals(hotelVO.getEvaluatePeople(),1);
    }


    /**
     * 功能测试：搜索酒店
     * @throws ServiceException
     */
    @Test
    void searchHotels() throws ServiceException {
       List<HotelVO> hotelVOS=hotelService.searchHotels("南京","null","WanDa",1,5,3,4,0.0,500.0,"2020-07-01 12:00:00","2020-07-15 12:00:00" );
       assertEquals(hotelVOS.get(0).getName(),"维也纳酒店");
        List<HotelVO> hotelVOS1=hotelService.searchHotels("上海","null","XiDan",1,5,3,4,0.0,500.0,"2020-07-01 12:00:00","2020-07-15 12:00:00" );
        assertEquals(hotelVOS1.size(),1);

    }


    /**
     * 集成测试：根据条件筛选酒店（地址、商圈、是否预订）
     */
    @Test
    void getHotelByCondition() {
        List<HotelVO> hotelVOs=hotelService.getHotelByCondition("XiDan","上海",true,5);
        for(HotelVO hotelVO:hotelVOs ){
           assertEquals(hotelVO.getName(),"汉庭酒店");
        }
    }


    /**
     * 单元测试：根据地址和商圈筛选酒店
     */
    @Test
    void getHotelByBizRegionAndAddress() {
        List<HotelVO> hotelVOs=hotelService.getHotelByBizRegionAndAddress("WanDa","南京");
            assertEquals(hotelVOs.get(0).getName(),"维也纳酒店");
            assertEquals(hotelVOs.get(1).getName(),"格林豪泰酒店");
    }


    /**
     * 单元测试：按酒店评价人数降序排序
     */
    @Test
    void sortByEvaluationPeople() {
        List<HotelVO> hotelVOs=hotelService.retrieveHotels();
        hotelService.sortByEvaluationPeople(hotelVOs);
        for(int i=0;i<hotelVOs.size()-1;i++){
            assertTrue(hotelVOs.get(i).getEvaluatePeople()>=hotelVOs.get(i+1).getEvaluatePeople());
        }
    }


    /**
     * 单元测试：按酒店星级降序排序
     */
    @Test
    void sortByStar() {
        List<HotelVO> hotelVOs=hotelService.retrieveHotels();
        hotelService.sortByStar(hotelVOs);
        Map<String,Integer> stars=new HashMap<>();
        stars.put("Five",5);
        stars.put("Four",4);
        stars.put("Three",3);
        for(int i=0;i<hotelVOs.size()-1;i++){
            System.out.println(hotelVOs.get(i).getName());
            assertTrue(stars.get(hotelVOs.get(i).getHotelStar())>=stars.get(hotelVOs.get(i+1).getHotelStar()));
        }
    }


    /**
     * 单元测试：按酒店评分降序排序
     */
    @Test
    void sortByScore() {
        List<HotelVO> hotelVOs=hotelService.retrieveHotels();
        hotelService.sortByScore(hotelVOs);
        for(int i=0;i<hotelVOs.size()-1;i++){
            System.out.println(hotelVOs.get(i).getName());
            assertTrue(hotelVOs.get(i).getRate()>=hotelVOs.get(i+1).getRate());
        }
    }


    /**
     * 功能测试：管理员删除酒店
     * @throws ServiceException
     */
    @Test
    void deleteHotel() throws ServiceException {
        addHotel();
        int size1=hotelService.retrieveHotels().size();
        hotelService.deleteHotel(size1);
        int size2=hotelService.retrieveHotels().size();
        assertEquals(size1-1,size2);
    }


    /**
     * 单元测试：根据管理员Id获取酒店列表
     */
    @Test
    void getHotelListByManagerId() {
        List<HotelVO> hotelVOS=hotelService.getHotelListByManagerId(8);
        List<HotelVO> hotelVOS1=hotelMapper.selectManagedHotel(8);
        assertEquals(hotelVOS.size(),hotelVOS1.size());
    }
}