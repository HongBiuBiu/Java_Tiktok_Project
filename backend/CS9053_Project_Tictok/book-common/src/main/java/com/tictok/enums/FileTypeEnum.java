package com.tictok.enums;

public enum FileTypeEnum {
    BGIMG(1, "User background image"),
    FACE(2, "User avatar");

    public final Integer type;
    public final String value;

    FileTypeEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
