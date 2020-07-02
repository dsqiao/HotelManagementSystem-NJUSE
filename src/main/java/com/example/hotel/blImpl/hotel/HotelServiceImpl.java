package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.evaluation.EvaluationService;
import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.data.hotel.OneRoomMapper;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.enums.BizRegion;
import com.example.hotel.enums.HotelStar;
import com.example.hotel.enums.RoomType;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.*;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.EvaluationVO;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.RoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private AccountService accountService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private EvaluationService evaluationService;
    @Autowired
    OneRoomMapper oneRoomMapper;

    @Override
    public void addHotel(HotelVO hotelVO) throws ServiceException {
        User manager = accountService.getUserInfo(hotelVO.getManagerId());
        if(manager == null || !manager.getUserType().equals(UserType.HotelManager)){
            throw new ServiceException("管理员不存在或者无权限！创建酒店失败！");
        }
        Hotel hotel = new Hotel();
        hotel.setDescription(hotelVO.getDescription());
        hotel.setBizRegion(hotel.getBizRegion());
        hotel.setAddress(hotelVO.getAddress());
        hotel.setHotelName(hotelVO.getName());
        hotel.setPhoneNum(hotelVO.getPhoneNum());
        hotel.setManagerId(hotelVO.getManagerId());
        hotel.setRate(hotelVO.getRate());
        hotel.setBizRegion(BizRegion.valueOf(hotelVO.getBizRegion()));
        hotel.setHotelStar(HotelStar.valueOf(hotelVO.getHotelStar()));
        hotelMapper.insertHotel(hotel);
    }


    @Override
    public List<HotelVO> retrieveHotels() {

        return hotelMapper.selectAllHotel();
    }

    @Override
    public HotelVO retrieveHotelDetails(Integer hotelId) {
        HotelVO hotelVO = hotelMapper.selectById(hotelId);

        List<HotelRoom> rooms = roomService.retrieveHotelRoomInfo(hotelId);
        List<Evaluation> evaluations = evaluationService.retrieveHotelEvaluationInfo(hotelId);
        List<RoomVO> roomVOS = rooms.stream().map(r -> {
            RoomVO roomVO = new RoomVO();
            roomVO.setId(r.getId());
            roomVO.setPrice(r.getPrice());
            roomVO.setRoomType(r.getRoomType().toString());
            roomVO.setTotal(r.getTotal());
            roomVO.setRest(r.getRest());
            return roomVO;
        }).collect(Collectors.toList());

        List<EvaluationVO> evaluationVOS = evaluations.stream().map(e -> {
            EvaluationVO evaluationVO = new EvaluationVO();
            evaluationVO.setId(e.getId());
            evaluationVO.setRate(e.getRate());
            evaluationVO.setHotelId(e.getHotelId());
            evaluationVO.setUserId(e.getUserId());
            evaluationVO.setEvaluation(e.getEvaluation());
            return evaluationVO;
        }).collect(Collectors.toList());

        hotelVO.setRooms(roomVOS);
        hotelVO.setEvaluations(evaluationVOS);

        return hotelVO;
    }

    @Override
    public List<HotelVO> searchHotels(String address, String hotelName, String bizRegion, Integer  lowStar, Integer highStar, Integer rooms, Integer roomType, Double lowPrice,Double highPrice,String InDate, String OutDate)throws ServiceException {
        if(address.equals("null") || bizRegion.equals("null")){
            throw new ServiceException("必须输入地址和商圈");
        }
        List<HotelVO> hotelVOList=new ArrayList<>();
        List<HotelVO> allhotels=retrieveHotels();
        for(HotelVO hotelVO:allhotels){
            if(isValid(hotelVO,address,bizRegion)){
                if(isValid(hotelVO,lowStar,highStar)&&isValid(hotelVO,rooms,roomType,lowPrice,highPrice,InDate,OutDate)&&isValid(hotelVO,hotelName)){
                    hotelVOList.add(hotelVO);
                }

            }
        }
        return hotelVOList;
    }
    public boolean isValid(HotelVO hotelVO,String address,String bizRegion){
        if(hotelVO.getAddress()==null||hotelVO.getBizRegion()==null) return false;
        int AddLength=address.length();
        int BizLength=bizRegion.length();
        boolean ADDR=false;
        boolean BIZ=false;
        String ADDRESS=hotelVO.getAddress();
        String BIZREGION=hotelVO.getBizRegion();
        for(int i=0;i<ADDRESS.length()-AddLength+1;i++){
            if(ADDRESS.substring(i,i+AddLength).equals(address)) {
                ADDR = true;
                break;
            }
        }
        for(int j=0;j<BIZREGION.length()-BizLength+1;j++){
            if(BIZREGION.substring(j,j+BizLength).equals(bizRegion)){
                BIZ=true;
                break;
            }
        }
        return  ADDR&&BIZ;
    }

    public boolean isValid(HotelVO hotelVO,Integer lowStar,Integer highStar){
        if (hotelVO.getHotelStar()==null) return  false;
        HotelStar hotelStar=HotelStar.valueOf(hotelVO.getHotelStar());
        if(lowStar<0&&highStar<0)
            return true;
        if(lowStar<0 && highStar>=hotelStar.ordinal()+3)
            return true;
        if(highStar<0&& lowStar<=hotelStar.ordinal()+3)
            return  true;
        if(lowStar<=hotelStar.ordinal()+3&&highStar>=hotelStar.ordinal()+3)
            return true;
        return  false;
    }
    public boolean isValid(HotelVO hotelVO,Integer rooms ,Integer roomtype,Double lowPrice,Double highPrice,String InDate, String OutDate){
        List<HotelRoom> room = roomService.retrieveHotelRoomInfo(hotelVO.getId());
        List<OneRoom> using=oneRoomMapper.getUsingRooms(hotelVO.getId(),RoomType.getValue(roomtype));
        Integer num=0;
        for(HotelRoom theRoom:room){
            if(roomtype==theRoom.getRoomType().ordinal()+1||roomtype<0) {
                if (lowPrice <= theRoom.getPrice() && highPrice >= theRoom.getPrice() || lowPrice <0&& highPrice >= theRoom.getPrice() || highPrice < 0 && lowPrice <= theRoom.getPrice()
                        || lowPrice < 0 && highPrice < 0)
                    num=theRoom.getRest();
                if(!InDate.equals("Invalid date") || !OutDate.equals("Invalid date")){
                for(int j=0;j<using.size();++j){
                    if(roomService.checkIsConflicted(InDate.substring(0,10),OutDate.substring(0,10),using.get(j)))num--;
                }
            }}
        }
        if(num>=rooms&&num>=1)return true;
        return false;
    }
    public boolean isValid(HotelVO hotelVO,String hotelName){
        if(hotelName.equals("null"))return true;
        if(hotelVO.getName().equals(hotelName))return true;
        return false;
    }


    /**
     *
     * @param bizRegion 商圈
     * @param address   地址
     * @param ordered   是否预定
     * @return
     */
    @Override
    public List<HotelVO> getHotelByCondition(String bizRegion,String address,boolean ordered,int userId){
        List<HotelVO> selectedHotel=getHotelByBizRegionAndAddress(bizRegion, address);
        if(!ordered) return selectedHotel;
        List<HotelVO> orderedHotel=hotelMapper.selectOrderedHotel(userId);
        List<HotelVO> selectedOrderedHotel=new ArrayList<>();
        for(int i=0;i<selectedHotel.size();++i){
            for(int j=0;j<orderedHotel.size();++j){
                if(selectedHotel.get(i).getId().equals(orderedHotel.get(j).getId())){
                    selectedOrderedHotel.add(orderedHotel.get(j));
                }
            }
        }
        return selectedOrderedHotel;
    }

    public List<HotelVO> getHotelByBizRegionAndAddress(String bizRegion,String address){
        List<HotelVO> allHotel=hotelMapper.selectAllHotel();
        List<HotelVO> selected = new ArrayList<>();
        for(int i=0;i<allHotel.size();++i){
            HotelVO current=allHotel.get(i);
            if(bizRegion.equals("全部")&&address.equals("全部")) selected.add(current);
            else if(bizRegion.equals("全部")){
                if(current.getAddress()!=null&&
                        current.getAddress().substring(0,address.length()).equals(address)){
                    selected.add(current);
                }
            }
            else if(address.equals("全部")){
                if(current.getBizRegion()!=null&&
                        current.getBizRegion().equals(bizRegion)){
                    selected.add(current);
                }
            }
            else{
                if(current.getBizRegion()!=null&&current.getAddress()!=null
                        &&current.getAddress().substring(0,address.length()).equals(address)
                        &&current.getBizRegion().equals(bizRegion)){
                    selected.add(current);
                }
            }
        }
        return selected;
    }

    /**
     * 根据评价人数排序
     * @param hotelList
     * @return
     */
    @Override
    public List<HotelVO> sortByEvaluationPeople(List<HotelVO> hotelList) {
        hotelList.sort((o1, o2) -> o2.getEvaluatePeople()-o1.getEvaluatePeople());
        return hotelList;
    }


    /**
     * 根据星级排序
     * @param hotelList
     * @return
     */
    @Override
    public List<HotelVO> sortByStar(List<HotelVO> hotelList) {
        Map<String,Integer> stars=new HashMap<>();
        stars.put("Five",5);
        stars.put("Four",4);
        stars.put("Three",3);
        hotelList.sort((o1, o2) -> {
            if (stars.get(o2.getHotelStar())>stars.get(o1.getHotelStar())) return 1;
            else if(stars.get(o2.getHotelStar()).equals(stars.get(o1.getHotelStar()))) return 0;
            else return -1;
        });
        return hotelList;
    }


    /**
     * 根据评分排序
     * @param hotelList
     * @return
     */
    @Override
    public List<HotelVO> sortByScore(List<HotelVO> hotelList) {
        hotelList.sort((o1, o2) -> {
            if(o2.getRate()>o1.getRate()) return 1;
            else if(o2.getRate().equals(o1.getRate())) return 0;
            else return -1;
        });
        return hotelList;
    }




    /**
     * 删除酒店
     * @param hotelId
     */
    @Override
    public void deleteHotel(Integer hotelId) {
        hotelMapper.deleteById(hotelId);
    }


    /**
     * 获取管理的酒店
     * @param managerId
     * @return
     */
    @Override
    public List<HotelVO> getHotelListByManagerId(Integer managerId) {
        return hotelMapper.selectManagedHotel(managerId);
    }
}

