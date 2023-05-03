package com.tictok.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Users {
    @Id
    private String id;

    private String mobile;

    private String nickname;

    @Column(name = "tictok_num")
    private String tictokNum;

    private String face;

    private Integer gender;

    private Date birthday;

    private String country;

    private String province;

    private String city;

    private String district;

    private String description;

    @Column(name = "bg_img")
    private String bgImg;

    @Column(name = "can_tictok_num_be_updated")
    private Integer canTictokNumBeUpdated;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTictokNum() {
        return tictokNum;
    }

    public void setTictokNum(String tictokNum) {
        this.tictokNum = tictokNum;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public Integer getGender() {
        return gender;
    }

    public void setSex(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBgImg() {
        return bgImg;
    }

    public void setBgImg(String bgImg) {
        this.bgImg = bgImg;
    }

    public Integer getCanTictokNumBeUpdated() {
        return canTictokNumBeUpdated;
    }

    public void setCanTictokNumBeUpdated(Integer canTictokNumBeUpdated) {
        this.canTictokNumBeUpdated = canTictokNumBeUpdated;
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