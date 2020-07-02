package com.example.hotel.enums;

import java.util.HashMap;
import java.util.Map;

public enum HotelStar {


    Three("三星级"),
    Four("四星级"),
    Five("五星级");

    private String value;
    HotelStar(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }



}
