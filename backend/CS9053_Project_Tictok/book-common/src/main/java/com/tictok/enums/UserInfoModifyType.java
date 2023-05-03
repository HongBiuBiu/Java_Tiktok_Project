package com.tictok.enums;

import com.tictok.exceptions.GraceException;
import com.tictok.grace.result.ResponseStatusEnum;

/**
 * @Desc: Modify user information type Enumeration
 */
public enum UserInfoModifyType {
    NICKNAME(1, "nickname"),
    TICTOKNUM(2, "tictok number"),
    GENDER(3, "gender"),
    BIRTHDAY(4, "birthday"),
    LOCATION(5, "location"),
    DESC(6, "description");

    public final Integer type;
    public final String value;

    UserInfoModifyType(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

    public static void checkUserInfoTypeIsRight(Integer type) {
        if (type != UserInfoModifyType.NICKNAME.type &&
                type != UserInfoModifyType.TICTOKNUM.type &&
                type != UserInfoModifyType.GENDER.type &&
                type != UserInfoModifyType.BIRTHDAY.type &&
                type != UserInfoModifyType.LOCATION.type &&
                type != UserInfoModifyType.DESC.type) {
            GraceException.display(ResponseStatusEnum.USER_INFO_UPDATED_ERROR);
        }
    }
}
