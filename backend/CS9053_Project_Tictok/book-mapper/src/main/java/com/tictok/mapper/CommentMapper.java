package com.tictok.mapper;

import com.tictok.my.mapper.MyMapper;
import com.tictok.pojo.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper extends MyMapper<Comment> {
}