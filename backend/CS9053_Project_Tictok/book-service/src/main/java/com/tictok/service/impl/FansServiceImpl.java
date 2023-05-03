package com.tictok.service.impl;

import com.github.pagehelper.PageHelper;
import com.tictok.base.BaseInfoProperties;
import com.tictok.enums.MessageEnum;
import com.tictok.enums.YesOrNo;
import com.tictok.mapper.FansMapper;
import com.tictok.mapper.FansMapperCustom;
import com.tictok.pojo.Fans;
import com.tictok.service.FansService;
import com.tictok.service.MsgService;
import com.tictok.utils.PagedGridResult;
import com.tictok.vo.FansVO;
import com.tictok.vo.VlogerVO;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FansServiceImpl extends BaseInfoProperties implements FansService {

    @Autowired
    private FansMapper fansMapper;
    @Autowired
    private FansMapperCustom fansMapperCustom;

    @Autowired
    private MsgService msgService;

    @Autowired
    private Sid sid;

    @Transactional
    @Override
    public void doFollow(String myId, String vlogerId) {

        String fid = sid.nextShort();

        Fans fans = new Fans();
        fans.setId(fid);
        fans.setFanId(myId);
        fans.setVlogerId(vlogerId);

        Fans vloger = queryFansRelationship(vlogerId, myId);
        if (vloger != null) {
            fans.setIsFanFriendOfMine(YesOrNo.YES.type);

            vloger.setIsFanFriendOfMine(YesOrNo.YES.type);
            fansMapper.updateByPrimaryKeySelective(vloger);
        } else {
            fans.setIsFanFriendOfMine(YesOrNo.NO.type);
        }

        fansMapper.insert(fans);


        msgService.createMsg(myId, vlogerId, MessageEnum.FOLLOW_YOU.type, null);
    }

    public Fans queryFansRelationship(String fanId, String vlogerId) {
        Example example = new Example(Fans.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("vlogerId", vlogerId);
        criteria.andEqualTo("fanId", fanId);

        List list =  fansMapper.selectByExample(example);

        Fans fan = null;
        if (list != null && list.size() > 0 && !list.isEmpty()) {
            fan = (Fans)list.get(0);
        }

        return fan;
    }

    @Transactional
    @Override
    public void doCancel(String myId, String vlogerId) {

        Fans fan = queryFansRelationship(myId, vlogerId);
        if (fan != null && fan.getIsFanFriendOfMine() == YesOrNo.YES.type) {
            Fans pendingFan = queryFansRelationship(vlogerId, myId);
            pendingFan.setIsFanFriendOfMine(YesOrNo.NO.type);
            fansMapper.updateByPrimaryKeySelective(pendingFan);
        }

        fansMapper.delete(fan);
    }

    @Override
    public boolean queryDoIFollowVloger(String myId, String vlogerId) {
        Fans vloger = queryFansRelationship(myId, vlogerId);
        return vloger != null;
    }

    @Override
    public PagedGridResult queryMyFollows(String myId,
                                          Integer page,
                                          Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("myId", myId);

        PageHelper.startPage(page, pageSize);

        List<VlogerVO> list = fansMapperCustom.queryMyFollows(map);

        return setterPagedGrid(list, page);
    }

    @Override
    public PagedGridResult queryMyFans(String myId,
                                       Integer page,
                                       Integer pageSize) {


        Map<String, Object> map = new HashMap<>();
        map.put("myId", myId);

        PageHelper.startPage(page, pageSize);

        List<FansVO> list = fansMapperCustom.queryMyFans(map);

        for (FansVO f : list) {
            String relationship = redis.get(REDIS_FANS_AND_VLOGGER_RELATIONSHIP + ":" + myId + ":" + f.getFanId());
            if (StringUtils.isNotBlank(relationship) && relationship.equalsIgnoreCase("1")) {
                f.setFriend(true);
            }
        }

        return setterPagedGrid(list, page);
    }
}
