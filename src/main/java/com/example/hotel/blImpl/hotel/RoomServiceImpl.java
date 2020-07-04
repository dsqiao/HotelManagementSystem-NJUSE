package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.data.hotel.OneRoomMapper;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.po.OneRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OneRoomMapper oneRoomMapper;

    private String pattern="yyyy-MM-dd HH:mm:ss";

    private Map<String,String> poToVo=new HashMap<String, String>(){{
       put("总统套房","PresidentBed");
       put("家庭房","Family");
       put("双床房","DoubleBed");
       put("大床房","BigBed");
    }};

    @Override
    public List<HotelRoom> retrieveHotelRoomInfo(Integer hotelId) {
        return roomMapper.selectRoomsByHotelId(hotelId);
    }

    @Override
    public void insertRoomInfo(HotelRoom hotelRoom) {
        roomMapper.insertRoom(hotelRoom);
    }

    @Override
    public List<HotelRoom> updateRestRooms(int hotelId, String checkInDate, String checkOutDate) {
        List<HotelRoom> hotelRoomList=retrieveHotelRoomInfo(hotelId);
        for(int i=0;i<hotelRoomList.size();++i){
            HotelRoom hotelRoom=hotelRoomList.get(i);
            int rest=hotelRoom.getTotal();
            String roomType=poToVo.get(hotelRoom.getRoomType().toString());
            List<OneRoom> using=oneRoomMapper.getUsingRooms(hotelId,roomType);
            for(int j=0;j<using.size();++j){
                if(checkIsConflicted(checkInDate,checkOutDate,using.get(j))){//判断是否该时间段房间被占用
                    if(rest==0) break;
                    rest--;
                }
            }
            roomMapper.updateRoomInfo(hotelId,roomType,rest);
        }
        return retrieveHotelRoomInfo(hotelId);
    }

    @Override
    public boolean checkIsConflicted(String checkInStr,String checkOutStr,OneRoom oneRoom){
        checkInStr+=" 12:00:00";
        checkOutStr+=" 12:00:00";
        LocalDateTime checkInDate=LocalDateTime.from(DateTimeFormatter.ofPattern(pattern).parse(checkInStr));
        LocalDateTime checkOutDate=LocalDateTime.from(DateTimeFormatter.ofPattern(pattern).parse(checkOutStr));
        LocalDateTime t1=oneRoom.getCheckInDate();
        LocalDateTime t2=oneRoom.getCheckOutDate();
        if( t1.compareTo(checkInDate)==0 ||
            t1.compareTo(checkInDate)*t1.compareTo(checkOutDate)<0||
            t2.compareTo(checkOutDate)==0||
            t2.compareTo(checkInDate)*t2.compareTo(checkOutDate)<0||
            (t1.compareTo(checkInDate)<0&&t2.compareTo(checkOutDate)>0)) return true;
        return false;
    }

}
