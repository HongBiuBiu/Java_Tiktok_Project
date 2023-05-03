package com.tictok.enums;

public enum MessageEnum {
    FOLLOW_YOU(1, "follow"),
    LIKE_VLOG(2, "like the video"),
    COMMENT_VLOG(3, "comment the video"),
    REPLY_YOU(4, "reply comment"),
    LIKE_COMMENT(5, "like comment");

    public final Integer type;
    public final String value;

    MessageEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
