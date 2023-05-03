package com.tictok.service.impl;

import com.tictok.bo.UpdatedUserBO;
import com.tictok.enums.UserInfoModifyType;
import com.tictok.enums.YesOrNo;
import com.tictok.exceptions.GraceException;
import com.tictok.grace.result.ResponseStatusEnum;
import com.tictok.mapper.UsersMapper;
import com.tictok.pojo.Users;
import com.tictok.service.UserService;
import com.tictok.utils.DateUtil;
import com.tictok.utils.DesensitizationUtil;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;
    private static final String USER_FACE1 = "http://122.152.205.72:88/group1/M00/00/05/CpoxxF6ZUySASMbOAABBAXhjY0Y649.png";

    @Override
    public Users queryMobileIsExist(String mobile) {
        Example userExample = new Example(Users.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("mobile", mobile);
        Users user = usersMapper.selectOneByExample(userExample);
        return user;
    }

    @Transactional
    @Override
    public Users createUser(String mobile) {

        String userId = sid.nextShort();

        Users user = new Users();
        user.setId(userId);

        user.setMobile(mobile);
        user.setNickname("user: " + DesensitizationUtil.commonDisplay(mobile));
        user.setTictokNum("user: " + DesensitizationUtil.commonDisplay(mobile));
        user.setFace(USER_FACE1);

        user.setBirthday(DateUtil.stringToDate("1900-01-01"));
        user.setSex(com.tictok.enums.Gender.secret.type);

        user.setCountry("china");
        user.setProvince("");
        user.setCity("");
        user.setDistrict("");
        user.setDescription("This guy was lazy and didn't leave anything~");
        user.setCanTictokNumBeUpdated(YesOrNo.YES.type);

        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());

        usersMapper.insert(user);

        return user;
    }

    @Override
    public Users getUser(String userId) {
        return usersMapper.selectByPrimaryKey(userId);
    }

    @Transactional
    @Override
    public Users updateUserInfo(UpdatedUserBO updatedUserBO) {

        Users pendingUser = new Users();
        BeanUtils.copyProperties(updatedUserBO, pendingUser);

        int result = usersMapper.updateByPrimaryKeySelective(pendingUser);
        if (result != 1) {
            GraceException.display(ResponseStatusEnum.USER_UPDATE_ERROR);
        }

        return getUser(updatedUserBO.getId());
    }

    @Transactional
    @Override
    public Users updateUserInfo(UpdatedUserBO updatedUserBO, Integer type) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        if (type == UserInfoModifyType.NICKNAME.type) {
            criteria.andEqualTo("nickname", updatedUserBO.getNickname());
            Users user = usersMapper.selectOneByExample(example);
            if (user != null) {
                GraceException.display(ResponseStatusEnum.USER_INFO_UPDATED_NICKNAME_EXIST_ERROR);
            }
        }

        if (type == UserInfoModifyType.TICTOKNUM.type) {
            criteria.andEqualTo("tictokNum", updatedUserBO.getTictokNum());
            Users user = usersMapper.selectOneByExample(example);
            if (user != null) {
                GraceException.display(ResponseStatusEnum.USER_INFO_UPDATED_NICKNAME_EXIST_ERROR);
            }

            Users tempUser =  getUser(updatedUserBO.getId());
            if (tempUser.getCanTictokNumBeUpdated() == YesOrNo.NO.type) {
                GraceException.display(ResponseStatusEnum.USER_INFO_CANT_UPDATED_TICTOKNUM_ERROR);
            }

            updatedUserBO.setCanTictokNumBeUpdated(YesOrNo.NO.type);
        }

        return updateUserInfo(updatedUserBO);
    }
}
