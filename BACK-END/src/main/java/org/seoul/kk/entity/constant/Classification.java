package org.seoul.kk.entity.constant;

public enum Classification {

    ADJECTIVE(1,"형용사"),
    NOUN(2,"명사");

    private int code;
    private String value;

    Classification(int code, String value) {
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
