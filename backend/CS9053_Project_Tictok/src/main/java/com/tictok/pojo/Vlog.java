package com.tictok.pojo;

import java.util.Date;
import javax.persistence.*;

public class Vlog {
    @Id
    private String id;

    @Column(name = "vloger_id")
    private String vlogerId;

    private String url;

    private String cover;

    private String title;

    private Integer width;

    private Integer height;

    @Column(name = "like_counts")
    private Integer likeCounts;

    @Column(name = "comments_counts")
    private Integer commentsCounts;

    @Column(name = "is_private")
    private Integer isPrivate;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "updated_time")
    private Date updatedTime;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(Integer likeCounts) {
        this.likeCounts = likeCounts;
    }

    public Integer getCommentsCounts() {
        return commentsCounts;
    }

    public void setCommentsCounts(Integer commentsCounts) {
        this.commentsCounts = commentsCounts;
    }

    public Integer getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Integer isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}