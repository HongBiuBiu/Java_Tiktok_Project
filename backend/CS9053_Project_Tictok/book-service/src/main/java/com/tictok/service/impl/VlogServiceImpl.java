package com.tictok.service.impl;

import com.github.pagehelper.PageHelper;
import com.tictok.base.BaseInfoProperties;
import com.tictok.bo.VlogBO;
import com.tictok.enums.MessageEnum;
import com.tictok.enums.YesOrNo;
import com.tictok.mapper.MyLikedVlogMapper;
import com.tictok.mapper.VlogMapper;
import com.tictok.mapper.VlogMapperCustom;
import com.tictok.pojo.MyLikedVlog;
import com.tictok.pojo.Vlog;
import com.tictok.service.FansService;
import com.tictok.service.MsgService;
import com.tictok.service.VlogService;
import com.tictok.utils.PagedGridResult;
import com.tictok.vo.IndexVlogVO;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VlogServiceImpl extends BaseInfoProperties implements VlogService {

    @Autowired
    private VlogMapper vlogMapper;

    @Autowired
    private VlogMapperCustom vlogMapperCustom;

    @Autowired
    private MyLikedVlogMapper myLikedVlogMapper;

    @Autowired
    private FansService fansService;
    @Autowired
    private MsgService msgService;

    @Autowired
    private Sid sid;

    @Transactional
    @Override
    public void createVlog(VlogBO vlogBO) {

        String vid = sid.nextShort();

        Vlog vlog = new Vlog();
        BeanUtils.copyProperties(vlogBO, vlog);

        vlog.setId(vid);

        vlog.setLikeCounts(0);
        vlog.setCommentsCounts(0);
        vlog.setIsPrivate(YesOrNo.NO.type);

        vlog.setCreatedTime(new Date());
        vlog.setUpdatedTime(new Date());

        vlogMapper.insert(vlog);
    }

    @Override
    public PagedGridResult getIndexVlogList(String userId,
                                            String search,
                                            Integer page,
                                            Integer pageSize) {

        PageHelper.startPage(page, pageSize);

        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isNotBlank(search)) {
            map.put("search", search);
        }
        List<IndexVlogVO> list = vlogMapperCustom.getIndexVlogList(map);

        for (IndexVlogVO v : list) {
            String vlogerId = v.getVlogerId();
            String vlogId = v.getVlogId();

            if (StringUtils.isNotBlank(userId)) {
                boolean doIFollowVloger = fansService.queryDoIFollowVloger(userId, vlogerId);
                v.setDoIFollowVloger(doIFollowVloger);

                v.setDoILikeThisVlog(doILikeVlog(userId, vlogId));
            }

            v.setLikeCounts(getVlogBeLikedCounts(vlogId));
        }

//        return list;
        return setterPagedGrid(list, page);
    }

    private IndexVlogVO setterVO(IndexVlogVO v, String userId) {
        String vlogerId = v.getVlogerId();
        String vlogId = v.getVlogId();

        if (StringUtils.isNotBlank(userId)) {
            boolean doIFollowVloger = fansService.queryDoIFollowVloger(userId, vlogerId);
            v.setDoIFollowVloger(doIFollowVloger);

            v.setDoILikeThisVlog(doILikeVlog(userId, vlogId));
        }

        v.setLikeCounts(getVlogBeLikedCounts(vlogId));

        return v;
    }

    @Override
    public Integer getVlogBeLikedCounts(String vlogId) {
        String countsStr = redis.get(REDIS_VLOG_BE_LIKED_COUNTS + ":" + vlogId);
        if (StringUtils.isBlank(countsStr)) {
            countsStr = "0";
        }
        return Integer.valueOf(countsStr);
    }

    private boolean doILikeVlog(String myId, String vlogId) {

        String doILike = redis.get(REDIS_USER_LIKE_VLOG + ":" + myId + ":" + vlogId);
        boolean isLike = false;
        if (StringUtils.isNotBlank(doILike) && doILike.equalsIgnoreCase("1")) {
            isLike = true;
        }
        return isLike;
    }

    @Override
    public IndexVlogVO getVlogDetailById(String userId, String vlogId) {

        Map<String, Object> map = new HashMap<>();
        map.put("vlogId", vlogId);

        List<IndexVlogVO> list = vlogMapperCustom.getVlogDetailById(map);

        if (list != null && list.size() > 0 && !list.isEmpty()) {
            IndexVlogVO vlogVO = list.get(0);
            return setterVO(vlogVO, userId);
        }

        return null;
    }

    @Transactional
    @Override
    public void changeToPrivateOrPublic(String userId,
                                        String vlogId,
                                        Integer yesOrNo) {
        Example example = new Example(Vlog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", vlogId);
        criteria.andEqualTo("vlogerId", userId);

        Vlog pendingVlog = new Vlog();
        pendingVlog.setIsPrivate(yesOrNo);

        vlogMapper.updateByExampleSelective(pendingVlog, example);
    }

    @Override
    public PagedGridResult queryMyVlogList(String userId,
                                           Integer page,
                                           Integer pageSize,
                                           Integer yesOrNo) {

        Example example = new Example(Vlog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("vlogerId", userId);
        criteria.andEqualTo("isPrivate", yesOrNo);

        PageHelper.startPage(page, pageSize);
        List<Vlog> list = vlogMapper.selectByExample(example);

        return setterPagedGrid(list, page);
    }

    @Transactional
    @Override
    public void userLikeVlog(String userId, String vlogId) {

        String rid = sid.nextShort();

        MyLikedVlog likedVlog = new MyLikedVlog();
        likedVlog.setId(rid);
        likedVlog.setVlogId(vlogId);
        likedVlog.setUserId(userId);

        myLikedVlogMapper.insert(likedVlog);


        Vlog vlog = this.getVlog(vlogId);
        Map msgContent = new HashMap();
        msgContent.put("vlogId", vlogId);
        msgContent.put("vlogCover", vlog.getCover());
        msgService.createMsg(userId,
                            vlog.getVlogerId(),
                            MessageEnum.LIKE_VLOG.type,
                            msgContent);
    }

    @Override
    public Vlog getVlog(String id) {
        return vlogMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public void userUnLikeVlog(String userId, String vlogId) {

        MyLikedVlog likedVlog = new MyLikedVlog();
        likedVlog.setVlogId(vlogId);
        likedVlog.setUserId(userId);

        myLikedVlogMapper.delete(likedVlog);
    }

    @Override
    public PagedGridResult getMyLikedVlogList(String userId,
                                              Integer page,
                                              Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        List<IndexVlogVO> list = vlogMapperCustom.getMyLikedVlogList(map);

        return setterPagedGrid(list, page);
    }

    @Override
    public PagedGridResult getMyFollowVlogList(String myId,
                                               Integer page,
                                               Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        Map<String, Object> map = new HashMap<>();
        map.put("myId", myId);

        List<IndexVlogVO> list = vlogMapperCustom.getMyFollowVlogList(map);

        for (IndexVlogVO v : list) {
            String vlogerId = v.getVlogerId();
            String vlogId = v.getVlogId();

            if (StringUtils.isNotBlank(myId)) {
                v.setDoIFollowVloger(true);

                v.setDoILikeThisVlog(doILikeVlog(myId, vlogId));
            }

            v.setLikeCounts(getVlogBeLikedCounts(vlogId));
        }

        return setterPagedGrid(list, page);
    }

    @Override
    public PagedGridResult getMyFriendVlogList(String myId,
                                               Integer page,
                                               Integer pageSize) {

        PageHelper.startPage(page, pageSize);

        Map<String, Object> map = new HashMap<>();
        map.put("myId", myId);

        List<IndexVlogVO> list = vlogMapperCustom.getMyFriendVlogList(map);

        for (IndexVlogVO v : list) {
            String vlogerId = v.getVlogerId();
            String vlogId = v.getVlogId();

            if (StringUtils.isNotBlank(myId)) {
                v.setDoIFollowVloger(true);

                v.setDoILikeThisVlog(doILikeVlog(myId, vlogId));
            }

            v.setLikeCounts(getVlogBeLikedCounts(vlogId));
        }

        return setterPagedGrid(list, page);
    }
}
