package org.seoul.kk.entity.constant;

public enum Season {

    SPRING(1, "봄"),
    SUMMER(2, "여름"),
    AUTUMN(3, "가을"),
    WINTER(4, "겨울");

    private int code;
    private String value;

    Season() {
    }

    Season(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

}
