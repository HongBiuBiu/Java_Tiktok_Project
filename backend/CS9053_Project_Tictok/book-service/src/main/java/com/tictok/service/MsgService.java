package com.tictok.service;

import com.tictok.mo.MessageMO;

import java.util.List;
import java.util.Map;

public interface MsgService {

    public void createMsg(String fromUserId,
                          String toUserId,
                          Integer type,
                          Map msgContent);

    public List<MessageMO> queryList(String toUserId,
                                     Integer page,
                                     Integer pageSize);

}
