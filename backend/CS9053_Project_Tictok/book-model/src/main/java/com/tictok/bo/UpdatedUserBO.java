package com.tictok.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdatedUserBO {
    private String id;
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
}