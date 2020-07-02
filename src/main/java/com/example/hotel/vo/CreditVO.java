package com.example.hotel.vo;


public class CreditVO {
    private Integer id;
    private Integer userId;
    private String action;
    private String change;
    private String current;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public void setAction(String action) {
        this.action = action;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }


    public String getAction() {
        return action;
    }

    public String getChange() {
        return change;
    }

    public String getCurrent() {
        return current;
    }
}
