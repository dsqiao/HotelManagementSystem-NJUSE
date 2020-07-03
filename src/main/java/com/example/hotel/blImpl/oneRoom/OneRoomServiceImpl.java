package com.example.hotel.blImpl.oneRoom;

import com.example.hotel.bl.oneRoom.OneRoomService;
import com.example.hotel.data.hotel.OneRoomMapper;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.po.OneRoom;
import com.example.hotel.vo.OneRoomVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.apache.ibatis.annotations.One;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OneRoomServiceImpl implements OneRoomService {
    private final static String DATE_ERROR = "当前时间段预订房间数量剩余不足";
    @Autowired
    OneRoomMapper oneRoomMapper;

    @Autowired
    RoomMapper roomMapper;

    @Autowired
    OrderMapper orderMapper;

    Map<String,Integer> roomLayers=new HashMap<String, Integer>(){{
        put("PresidentBed",400);
        put("Family",300);
        put("DoubleBed",200);
        put("BigBed",100);
    }};
    String pattern="yyyy-MM-dd HH:mm:ss";


    /**
     * 分配房间号
     * @param orderVO
     * @return
     */
    @Override
    public ResponseVO distributeRooms(OrderVO orderVO) {
        List<OneRoom> oneRooms=new ArrayList<>();
        int needed = orderVO.getRoomNum();
        int base=roomLayers.get(orderVO.getRoomType());
        HotelRoom hotelRoom=roomMapper.selectSpecificRooms(orderVO.getHotelId(),orderVO.getRoomType());
        int total=hotelRoom.getTotal();

        for(int i=1;i<=total;++i){
            int roomId=base+i;
            if(checkIsValidDate(orderVO.getCheckInDate(),
                    orderVO.getCheckOutDate(),
                    orderVO.getHotelId(),
                    roomId)){
                OneRoom oneRoom=new OneRoom();
                oneRoom.setHotelId(orderVO.getHotelId());
                oneRoom.setCheckInDate(strToLdt(orderVO.getCheckInDate(),pattern));
                oneRoom.setCheckOutDate(strToLdt(orderVO.getCheckOutDate(),pattern));
                oneRoom.setOrderId(orderVO.getId());
                oneRoom.setRoomId(roomId);
                oneRoom.setRoomType(orderVO.getRoomType());
                oneRooms.add(oneRoom);
            }
            if(oneRooms.size()==needed) break;
        }
        if(oneRooms.size()<needed) {
            orderMapper.annulOrder(orderVO.getId());
            return ResponseVO.buildFailure(DATE_ERROR);
        }
        for(int i=0;i<oneRooms.size();++i){
            oneRoomMapper.insertRoomInfo(oneRooms.get(i));
        }
        return ResponseVO.buildSuccess(true);
    }

    private OneRoom voToPo(OneRoomVO oneRoomVO){
        OneRoom oneRoom=new OneRoom();
        oneRoom.setHotelId(oneRoomVO.getHotelId());
        oneRoom.setRoomId(oneRoomVO.getRoomId());
        oneRoom.setOrderId(oneRoomVO.getOrderId());
        oneRoom.setCheckInDate(strToLdt(oneRoomVO.getCheckInDate(),pattern));
        oneRoom.setCheckOutDate(strToLdt(oneRoomVO.getCheckOutDate(),pattern));
        return oneRoom;
    }

    private OneRoomVO poToVo(OneRoom oneRoom){
        OneRoomVO oneRoomVO=new OneRoomVO();
        oneRoomVO.setHotelId(oneRoom.getHotelId());
        oneRoomVO.setRoomId(oneRoom.getRoomId());
        oneRoomVO.setOrderId(oneRoom.getOrderId());
        oneRoomVO.setCheckInDate(ldtToStr(oneRoom.getCheckInDate(),pattern));
        oneRoomVO.setCheckOutDate(ldtToStr(oneRoom.getCheckOutDate(),pattern));
        return oneRoomVO;
    }


    public LocalDateTime strToLdt(String str,String pattern){
        return LocalDateTime.from(DateTimeFormatter.ofPattern(pattern).parse(str));
    }

    public String ldtToStr(LocalDateTime ldt,String pattern){
        return DateTimeFormatter.ofPattern(pattern).format(ldt);
    }

    /**
     * 查看房间日期是否能够兼容
     * @param checkInStr
     * @param checkOutStr
     * @param hotelId
     * @param roomId
     * @return
     */
    public boolean checkIsValidDate(String checkInStr,String checkOutStr,int hotelId,int roomId){
        List<OneRoom> ordered=oneRoomMapper.selectRoomInfoByRoomId(roomId,hotelId);
        LocalDateTime checkInDate=strToLdt(checkInStr,pattern);
        LocalDateTime checkOutDate=strToLdt(checkOutStr,pattern);
        for(int i=0;i<ordered.size();++i){
            LocalDateTime t1=ordered.get(i).getCheckInDate();
            LocalDateTime t2=ordered.get(i).getCheckOutDate();
            if(checkInDate.compareTo(t1)==0 ||
                    checkInDate.compareTo(t1)*checkInDate.compareTo(t2)<0 ||
                    checkOutDate.compareTo(t2)==0 ||
                    checkOutDate.compareTo(t1)*checkOutDate.compareTo(t2)<0||
                    (checkInDate.compareTo(t1)<0&&checkOutDate.compareTo(t2)>0)) return false;
        }
        return true;
    }

    /**
     * 退房
     * @param orderId
     */
    @Override
    public void checkOutRooms(int orderId) {
        oneRoomMapper.deleteRoomInfo(orderId);
    }

    @Override
    public List<Integer> getDistributedRooms(int orderId) {
        List<Integer> rooms=oneRoomMapper.selectRoomsByOrderId(orderId);
        return rooms;
    }

    @Override
    public List<OneRoom> getAllRooms() {
        return oneRoomMapper.getAllRooms();
    }


}
