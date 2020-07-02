package com.example.hotel.enums;

public enum RoomType {
    BigBed("大床房"),
    DoubleBed("双床房"),
    Family("家庭房"),
    PresidentBed("总统套房");
    private String value;

    RoomType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    static public String getValue(Integer position) {
        switch (position) {
            case (1): {
                return "BigBed";
            }
            case (2): {
                return "DoubleBed";
            }
            case (3): {
                return "Family";
            }
            case (4): {
                return "PresidentBed";
            }
        }
        return null;
    }
}
