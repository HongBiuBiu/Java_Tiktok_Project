package com.tictok.mapper;

import com.tictok.my.mapper.MyMapper;
import com.tictok.pojo.Fans;
import com.tictok.vo.FansVO;
import com.tictok.vo.VlogerVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FansMapperCustom extends MyMapper<Fans> {

    public List<VlogerVO> queryMyFollows(@Param("paramMap") Map<String, Object> map);

    public List<FansVO> queryMyFans(@Param("paramMap") Map<String, Object> map);

}