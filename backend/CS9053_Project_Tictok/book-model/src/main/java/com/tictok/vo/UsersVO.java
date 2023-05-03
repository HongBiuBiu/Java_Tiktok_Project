package com.tictok.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsersVO {
    private String id;
    private String mobile;
    private String nickname;
    private String tictokNum;
    private String face;
    private Integer gender;
    private Date birthday;
    private String country;
    private String province;
    private String city;
    private String district;
    private String description;
    private String bgImg;
    private Integer canTictokNumBeUpdated;
    private Date createdTime;
    private Date updatedTime;

    private String userToken;

    private Integer myFollowsCounts;
    private Integer myFansCounts;
    private Integer totalLikeMeCounts;

}