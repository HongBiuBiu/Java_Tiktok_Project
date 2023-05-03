package com.tictok.enums;

/**
 * @Desc: Gender Enumeration
 */
public enum Gender {
    woman(0, "female"),
    man(1, "male"),
    secret(2, "private");

    public final Integer type;
    public final String value;

    Gender(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
