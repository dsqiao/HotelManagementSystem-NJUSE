package com.example.hotel.enums;

/**
 * @Author: chenyizong
 * @Date: 2020-02-29
 */
public enum UserType {
    Client("1"), // 客户
    HotelManager("2"), // 酒店工作人员
    Admin("3"), // 网站管理人员
    Salesman("4"); // 网站营销人员
    private String value;

    UserType(String value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return value;
    }

}
