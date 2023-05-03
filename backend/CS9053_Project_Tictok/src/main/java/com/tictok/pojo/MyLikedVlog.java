package com.tictok.pojo;

import javax.persistence.*;

@Table(name = "my_liked_vlog")
public class MyLikedVlog {
    @Id
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "vlog_id")
    private String vlogId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVlogId() {
        return vlogId;
    }

    public void setVlogId(String vlogId) {
        this.vlogId = vlogId;
    }
}