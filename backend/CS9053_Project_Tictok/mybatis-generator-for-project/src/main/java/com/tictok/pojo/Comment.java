package com.tictok.pojo;

import java.util.Date;
import javax.persistence.*;

public class Comment {
    @Id
    private String id;

    @Column(name = "vloger_id")
    private String vlogerId;

    @Column(name = "father_comment_id")
    private String fatherCommentId;

    @Column(name = "vlog_id")
    private String vlogId;

    @Column(name = "comment_user_id")
    private String commentUserId;

    private String content;

    @Column(name = "like_counts")
    private Integer likeCounts;

    @Column(name = "create_time")
    private Date createTime;

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

    public String getFatherCommentId() {
        return fatherCommentId;
    }

    public void setFatherCommentId(String fatherCommentId) {
        this.fatherCommentId = fatherCommentId;
    }

    public String getVlogId() {
        return vlogId;
    }

    public void setVlogId(String vlogId) {
        this.vlogId = vlogId;
    }

    public String getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(Integer likeCounts) {
        this.likeCounts = likeCounts;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}