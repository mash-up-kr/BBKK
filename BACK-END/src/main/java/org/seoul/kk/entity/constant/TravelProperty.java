package org.seoul.kk.entity.constant;

public enum TravelProperty {

    ARTIST(1, "예술가"),
    FOODFIGHTER(2, "푸드파이터"),
    VISITOR(3, "관광객"),
    EXPLORER(4, "탐험가"),
    BEGGER(5, "알뜰방랑자");

    private String type;
    private int code;

    TravelProperty() {
    }

    TravelProperty(int code, String type) {
        this.type = type;
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public int getCode() {
        return code;
    }

}
