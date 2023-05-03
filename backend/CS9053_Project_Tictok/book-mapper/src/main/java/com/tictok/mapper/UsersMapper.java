package com.tictok.mapper;

import com.tictok.my.mapper.MyMapper;
import com.tictok.pojo.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersMapper extends MyMapper<Users> {
}