package com.example.hotel.vo;

public class BirthCouponVO extends CouponVO {
    private Integer hotelId;
    private double discount;
    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }
}
