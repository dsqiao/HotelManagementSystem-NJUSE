package com.example.hotel.vo;

import org.springframework.web.bind.annotation.RequestParam;

public class SearchListVO {
      private String address;
      private String hotelName;
   private String bizRegion;
private Integer lowStar;
    private Integer highStar;
    private Integer rooms;
    private Integer roomType;
    private Double lowPrice;
    private  Double highPrice;
    private String InDate;
    private String OutDate;

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setBizRegion(String bizRegion) {
        this.bizRegion = bizRegion;
    }

    public String getBizRegion() {
        return bizRegion;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setLowStar(Integer lowStar) {
        this.lowStar = lowStar;
    }

    public Integer getLowStar() {
        return lowStar;
    }
    public Integer getHighStar() {
        return highStar;
    }

    public void setHighStar(Integer highStar) {
        this.highStar = highStar;
    }
    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }
    public Integer getRoomType() {
        return roomType;
    }

    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
    }
    public Double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }
    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public void setInDate(String inDate) {
        InDate = inDate;
    }

    public void setOutDate(String outDate) {
        OutDate = outDate;
    }

    public String getInDate() {
        return InDate;
    }

    public String getOutDate() {
        return OutDate;
    }
}
