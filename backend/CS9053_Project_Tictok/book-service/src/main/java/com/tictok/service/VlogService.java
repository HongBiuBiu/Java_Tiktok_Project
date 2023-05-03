package com.tictok.service;

import com.tictok.bo.VlogBO;
import com.tictok.pojo.Vlog;
import com.tictok.utils.PagedGridResult;
import com.tictok.vo.IndexVlogVO;

public interface VlogService {

    public void createVlog(VlogBO vlogBO);


    public PagedGridResult getIndexVlogList(String userId,
                                            String search,
                                            Integer page,
                                            Integer pageSize);

    public IndexVlogVO getVlogDetailById(String userId, String vlogId);

    public void changeToPrivateOrPublic(String userId,
                                        String vlogId,
                                        Integer yesOrNo);

    public PagedGridResult queryMyVlogList(String userId,
                                Integer page,
                                Integer pageSize,
                                Integer yesOrNo);

    public void userLikeVlog(String userId, String vlogId);


    public void userUnLikeVlog(String userId, String vlogId);

    public Integer getVlogBeLikedCounts(String vlogId);

    public PagedGridResult getMyLikedVlogList(String userId,
                                           Integer page,
                                           Integer pageSize);


    public PagedGridResult getMyFollowVlogList(String myId,
                                              Integer page,
                                              Integer pageSize);

    public PagedGridResult getMyFriendVlogList(String myId,
                                               Integer page,
                                               Integer pageSize);

    public Vlog getVlog(String id);
}
