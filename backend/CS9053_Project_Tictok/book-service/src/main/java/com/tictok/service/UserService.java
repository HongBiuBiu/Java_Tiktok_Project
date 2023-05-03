package com.tictok.service;

import com.tictok.bo.UpdatedUserBO;
import com.tictok.pojo.Users;

public interface UserService {

    public Users queryMobileIsExist(String mobile);

    public Users createUser(String mobile);

    public Users getUser(String userId);

    public Users updateUserInfo(UpdatedUserBO updatedUserBO);

    public Users updateUserInfo(UpdatedUserBO updatedUserBO, Integer type);
}
