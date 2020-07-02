package com.example.hotel.blImpl.hotel;

import com.example.hotel.HotelApplication;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.enums.RoomType;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.po.OneRoom;
import javafx.application.Application;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HotelApplication.class)
class RoomServiceImplTest {

    @Autowired
    private RoomService roomService=new RoomServiceImpl();

    private String pattern="yyyy-MM-dd HH:mm:ss";

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void retrieveHotelRoomInfo() {
        List<HotelRoom> hotelRoomList1=roomService.retrieveHotelRoomInfo(1);
        assertEquals(hotelRoomList1.size(),4);
        List<HotelRoom> hotelRoomList2=roomService.retrieveHotelRoomInfo(2);
        List<HotelRoom> expectedList2=new ArrayList<>();
        HotelRoom hotelRoom=new HotelRoom();
        hotelRoom.setId(6);
        expectedList2.add(hotelRoom);
        assert hotelRoomList2.size()==expectedList2.size();
        for(int i=0;i<hotelRoomList2.size();++i){
            assertEquals(hotelRoomList2.get(i).getId(),expectedList2.get(i).getId());
        }
    }

    @org.junit.jupiter.api.Test
    void insertRoomInfo() {
        HotelRoom hotelRoom=new HotelRoom();
        List<HotelRoom> before=roomService.retrieveHotelRoomInfo(1);
        hotelRoom.setHotelId(1);
        roomService.insertRoomInfo(hotelRoom);
        List<HotelRoom> after=roomService.retrieveHotelRoomInfo(1);
        assertEquals(before.size()+1,after.size());
    }

    @Test
    void updateRestRooms() {

    }

    @Test
    /**
     * 开始日期与入住时期相同
     */
    void checkIsConflicted1() {
        OneRoom oneRoom=new OneRoom();
        String checkInStr="2020-06-20 12:00:00";
        String checkOutStr="2020-06-25 12:00:00";
        String start="2020-06-20";
        String end="2020-06-28";
        LocalDateTime checkInDate=LocalDateTime.from(DateTimeFormatter.ofPattern(pattern).parse(checkInStr));
        LocalDateTime checkOutDate=LocalDateTime.from(DateTimeFormatter.ofPattern(pattern).parse(checkOutStr));
        oneRoom.setCheckInDate(checkInDate);
        oneRoom.setCheckOutDate(checkOutDate);
        assertEquals(roomService.checkIsConflicted(start,end,oneRoom),true);
    }


    @Test
    /**
     * 结束日期与退房时期相同
     */
    void checkIsConflicted2() {
        OneRoom oneRoom=new OneRoom();
        String checkInStr="2020-06-20 12:00:00";
        String checkOutStr="2020-06-25 12:00:00";
        String start="2020-06-19";
        String end="2020-06-25";
        LocalDateTime checkInDate=LocalDateTime.from(DateTimeFormatter.ofPattern(pattern).parse(checkInStr));
        LocalDateTime checkOutDate=LocalDateTime.from(DateTimeFormatter.ofPattern(pattern).parse(checkOutStr));
        oneRoom.setCheckInDate(checkInDate);
        oneRoom.setCheckOutDate(checkOutDate);
        assertEquals(roomService.checkIsConflicted(start,end,oneRoom),true);
    }

    @Test
    /**
     * 开始日期在退房日期之后
     */
    void checkIsConflicted3() {
        OneRoom oneRoom=new OneRoom();
        String checkInStr="2020-06-20 12:00:00";
        String checkOutStr="2020-06-25 12:00:00";
        String start="2020-06-26";
        String end="2020-07-02";
        LocalDateTime checkInDate=LocalDateTime.from(DateTimeFormatter.ofPattern(pattern).parse(checkInStr));
        LocalDateTime checkOutDate=LocalDateTime.from(DateTimeFormatter.ofPattern(pattern).parse(checkOutStr));
        oneRoom.setCheckInDate(checkInDate);
        oneRoom.setCheckOutDate(checkOutDate);
        assertEquals(roomService.checkIsConflicted(start,end,oneRoom),false);
    }

    @Test
    /**
     * 结束日期在入住日期之前
     */
    void checkIsConflicted4() {
        OneRoom oneRoom=new OneRoom();
        String checkInStr="2020-06-20 12:00:00";
        String checkOutStr="2020-06-25 12:00:00";
        String start="2020-06-18";
        String end="2020-06-19";
        LocalDateTime checkInDate=LocalDateTime.from(DateTimeFormatter.ofPattern(pattern).parse(checkInStr));
        LocalDateTime checkOutDate=LocalDateTime.from(DateTimeFormatter.ofPattern(pattern).parse(checkOutStr));
        oneRoom.setCheckInDate(checkInDate);
        oneRoom.setCheckOutDate(checkOutDate);
        assertEquals(roomService.checkIsConflicted(start,end,oneRoom),false);
    }

    @Test
    /**
     * 只有开始日期介于入住和退房日期之间
     */
    void checkIsConflicted5() {
        OneRoom oneRoom=new OneRoom();
        String checkInStr="2020-06-20 12:00:00";
        String checkOutStr="2020-06-25 12:00:00";
        String start="2020-06-21";
        String end="2020-07-02";
        LocalDateTime checkInDate=LocalDateTime.from(DateTimeFormatter.ofPattern(pattern).parse(checkInStr));
        LocalDateTime checkOutDate=LocalDateTime.from(DateTimeFormatter.ofPattern(pattern).parse(checkOutStr));
        oneRoom.setCheckInDate(checkInDate);
        oneRoom.setCheckOutDate(checkOutDate);
        assertEquals(roomService.checkIsConflicted(start,end,oneRoom),true);
    }

    @Test
    /**
     * 只有结束日期介于入住和退房之间
     */
    void checkIsConflicted6() {
        OneRoom oneRoom=new OneRoom();
        String checkInStr="2020-06-20 12:00:00";
        String checkOutStr="2020-06-25 12:00:00";
        String start="2020-06-19";
        String end="2020-06-24";
        LocalDateTime checkInDate=LocalDateTime.from(DateTimeFormatter.ofPattern(pattern).parse(checkInStr));
        LocalDateTime checkOutDate=LocalDateTime.from(DateTimeFormatter.ofPattern(pattern).parse(checkOutStr));
        oneRoom.setCheckInDate(checkInDate);
        oneRoom.setCheckOutDate(checkOutDate);
        assertEquals(roomService.checkIsConflicted(start,end,oneRoom),true);
    }

    @Test
    /**
     * 开始日期和结束日期都介于入住和退房之间
     */
    void checkIsConflicted7() {
        OneRoom oneRoom=new OneRoom();
        String checkInStr="2020-06-20 12:00:00";
        String checkOutStr="2020-06-25 12:00:00";
        String start="2020-06-21";
        String end="2020-06-24";
        LocalDateTime checkInDate=LocalDateTime.from(DateTimeFormatter.ofPattern(pattern).parse(checkInStr));
        LocalDateTime checkOutDate=LocalDateTime.from(DateTimeFormatter.ofPattern(pattern).parse(checkOutStr));
        oneRoom.setCheckInDate(checkInDate);
        oneRoom.setCheckOutDate(checkOutDate);
        assertEquals(roomService.checkIsConflicted(start,end,oneRoom),true);
    }

    @Test
    /**
     * 开始日期和退房时间相同
     */
    void checkIsConflicted8() {
        OneRoom oneRoom=new OneRoom();
        String checkInStr="2020-06-20 12:00:00";
        String checkOutStr="2020-06-25 12:00:00";
        String start="2020-06-25";
        String end="2020-06-27";
        LocalDateTime checkInDate=LocalDateTime.from(DateTimeFormatter.ofPattern(pattern).parse(checkInStr));
        LocalDateTime checkOutDate=LocalDateTime.from(DateTimeFormatter.ofPattern(pattern).parse(checkOutStr));
        oneRoom.setCheckInDate(checkInDate);
        oneRoom.setCheckOutDate(checkOutDate);
        assertEquals(roomService.checkIsConflicted(start,end,oneRoom),false);
    }

    @Test
    /**
     * 结束日期和入住时间相同
     */
    void checkIsConflicted9() {
        OneRoom oneRoom=new OneRoom();
        String checkInStr="2020-06-20 12:00:00";
        String checkOutStr="2020-06-25 12:00:00";
        String start="2020-06-16";
        String end="2020-06-20";
        LocalDateTime checkInDate=LocalDateTime.from(DateTimeFormatter.ofPattern(pattern).parse(checkInStr));
        LocalDateTime checkOutDate=LocalDateTime.from(DateTimeFormatter.ofPattern(pattern).parse(checkOutStr));
        oneRoom.setCheckInDate(checkInDate);
        oneRoom.setCheckOutDate(checkOutDate);
        assertEquals(roomService.checkIsConflicted(start,end,oneRoom),false);
    }


}