package com.tictok.enums;

/**
 * @Desc: YesOrNo Enumeration
 */
public enum YesOrNo {
    NO(0, "NO"),
    YES(1, "YES");

    public final Integer type;
    public final String value;

    YesOrNo(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
