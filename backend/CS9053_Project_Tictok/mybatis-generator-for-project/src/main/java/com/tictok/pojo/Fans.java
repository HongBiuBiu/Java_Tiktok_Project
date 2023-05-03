package com.tictok.pojo;

import javax.persistence.*;

public class Fans {
    @Id
    private String id;

    @Column(name = "vloger_id")
    private String vlogerId;

    @Column(name = "fan_id")
    private String fanId;

    @Column(name = "is_fan_friend_of_mine")
    private Integer isFanFriendOfMine;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVlogerId() {
        return vlogerId;
    }

    public void setVlogerId(String vlogerId) {
        this.vlogerId = vlogerId;
    }

    public String getFanId() {
        return fanId;
    }

    public void setFanId(String fanId) {
        this.fanId = fanId;
    }

    public Integer getIsFanFriendOfMine() {
        return isFanFriendOfMine;
    }

    public void setIsFanFriendOfMine(Integer isFanFriendOfMine) {
        this.isFanFriendOfMine = isFanFriendOfMine;
    }
}