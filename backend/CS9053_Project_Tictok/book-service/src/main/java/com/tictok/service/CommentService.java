package com.tictok.service;

import com.tictok.bo.CommentBO;
import com.tictok.pojo.Comment;
import com.tictok.utils.PagedGridResult;
import com.tictok.vo.CommentVO;

public interface CommentService {

    public CommentVO createComment(CommentBO commentBO);

    public PagedGridResult queryVlogComments(String vlogId,
                                             String userId,
                                             Integer page,
                                             Integer pageSize);

    public void deleteComment(String commentUserId,
                              String commentId,
                              String vlogId);

    public Comment getComment(String id);
}
