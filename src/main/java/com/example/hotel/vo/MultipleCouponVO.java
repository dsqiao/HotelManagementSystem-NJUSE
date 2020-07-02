package com.example.hotel.vo;

public class MultipleCouponVO extends CouponVO {
    private Integer hotelId;
    private Integer numOfRoom;
    private Integer discountMoney;
    private double discount;


    public Integer getNumOfRoom() {
        return numOfRoom;
    }

    public void setNumofRoom(Integer num) {
        numOfRoom = num;
    }

    public Integer getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }
}
