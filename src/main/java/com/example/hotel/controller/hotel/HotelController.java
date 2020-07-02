package com.example.hotel.controller.hotel;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomService roomService;


    @PostMapping("/addHotel")
    public ResponseVO createHotel(@RequestBody HotelVO hotelVO) throws ServiceException {

        hotelService.addHotel(hotelVO);
        return ResponseVO.buildSuccess(true);
    }

    @GetMapping("/all")
    public ResponseVO retrieveAllHotels(){
        return ResponseVO.buildSuccess(hotelService.retrieveHotels());
    }

    @PostMapping("/roomInfo")
    public ResponseVO addRoomInfo(@RequestBody HotelRoom hotelRoom) {
        roomService.insertRoomInfo(hotelRoom);
        return ResponseVO.buildSuccess(true);
    }

    @GetMapping("/{hotelId}/detail")
    public ResponseVO retrieveHotelDetail(@PathVariable Integer hotelId) {
        return ResponseVO.buildSuccess(hotelService.retrieveHotelDetails(hotelId));
    }
    @GetMapping("/searchHotels")
    public ResponseVO RightHotels(@RequestParam("address") String address,
                                  @RequestParam("name") String hotelName,
                                  @RequestParam("bizRegion") String bizRegion,
                                  @RequestParam("lowStar") Integer lowStar,
                                  @RequestParam("highStar") Integer highStar,
                                  @RequestParam("roomNum") Integer rooms,
                                  @RequestParam("type") Integer roomType,
                                  @RequestParam("lowPrice") Double lowPrice,
                                  @RequestParam("highPrice") Double highPrice,
                                  @RequestParam("InDate") String InDate,
                                  @RequestParam("OutDate") String OutDate)throws  ServiceException{


        return ResponseVO.buildSuccess(hotelService.searchHotels(address,hotelName,bizRegion,lowStar,highStar,rooms,roomType,lowPrice,highPrice,InDate,OutDate));
    }


    /**
     *
     * @param bizRegion
     * @param address
     * @return 根据商圈和地址选择对应的酒店列表
     */
    @GetMapping("/selected")
    public ResponseVO retrieveSelectedHotels(@RequestParam String bizRegion,
                                             @RequestParam String address,
                                             @RequestParam boolean ordered,
                                             @RequestParam int userId)
    {
        return ResponseVO.buildSuccess(hotelService.getHotelByCondition(bizRegion, address, ordered,userId));
    }



    @GetMapping("/defaultSorted")
    public ResponseVO sortByDefault(@RequestParam String bizRegion,@RequestParam String address,@RequestParam boolean ordered,@RequestParam int userId){
        List<HotelVO> current=hotelService.getHotelByCondition(bizRegion,address,ordered,userId);
        return ResponseVO.buildSuccess(current);
    }


    @GetMapping("/evaluationSorted")
    public ResponseVO sortByPrice(@RequestParam String bizRegion,@RequestParam String address,@RequestParam boolean ordered,@RequestParam int userId){
        List<HotelVO> current=hotelService.getHotelByCondition(bizRegion, address,ordered,userId);
        current=hotelService.sortByEvaluationPeople(current);
        return ResponseVO.buildSuccess(current);
    }


    @GetMapping("/scoreSorted")
    public ResponseVO sortByScore(@RequestParam String bizRegion,String address,@RequestParam boolean ordered,@RequestParam int userId){
        List<HotelVO> current=hotelService.getHotelByCondition(bizRegion, address,ordered,userId);
        current=hotelService.sortByScore(current);
        return ResponseVO.buildSuccess(current);
    }


    @GetMapping("/starSorted")
    public ResponseVO sortByStar(@RequestParam String bizRegion,String address,@RequestParam boolean ordered,@RequestParam int userId){
        List<HotelVO> current=hotelService.getHotelByCondition(bizRegion, address,ordered,userId);
        current=hotelService.sortByStar(current);
        return ResponseVO.buildSuccess(current);
    }

    @PostMapping("/{hotelId}/deleteHotel")
    public ResponseVO deleteHotel(@PathVariable Integer hotelId){
        hotelService.deleteHotel(hotelId);
        return ResponseVO.buildSuccess(true);
    }

    @GetMapping("/{managerId}/managedHotel")
    public ResponseVO getHotelListByManagerId(@PathVariable Integer managerId){
        return ResponseVO.buildSuccess(hotelService.getHotelListByManagerId(managerId));
    }

    @PostMapping("/updateRestRooms")
    public ResponseVO getRestRooms(@RequestParam int hotelId,
                                   @RequestParam String checkInDate,
                                   @RequestParam String checkOutDate){
        return ResponseVO.buildSuccess(roomService.updateRestRooms(hotelId,checkInDate,checkOutDate));
    }
}
