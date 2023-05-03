package com.tictok.controller;

import com.tictok.base.BaseInfoProperties;
import com.tictok.bo.CommentBO;
import com.tictok.enums.MessageEnum;
import com.tictok.grace.result.GraceJSONResult;
import com.tictok.pojo.Comment;
import com.tictok.pojo.Vlog;
import com.tictok.service.CommentService;
import com.tictok.service.MsgService;
import com.tictok.service.VlogService;
import com.tictok.vo.CommentVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Api(tags = "CommentController comment api")
@RequestMapping("comment")
@RestController
public class CommentController extends BaseInfoProperties {

    @Autowired
    private CommentService commentService;
    @Autowired
    private MsgService msgService;
    @Autowired
    private VlogService vlogService;

    @PostMapping("create")
    public GraceJSONResult create(@RequestBody @Valid CommentBO commentBO)
            throws Exception {

        CommentVO commentVO = commentService.createComment(commentBO);
        return GraceJSONResult.ok(commentVO);
    }

    @GetMapping("counts")
    public GraceJSONResult counts(@RequestParam String vlogId) {

        String countsStr = redis.get(REDIS_VLOG_COMMENT_COUNTS + ":" + vlogId);
        if (StringUtils.isBlank(countsStr)) {
            countsStr = "0";
        }

        return GraceJSONResult.ok(Integer.valueOf(countsStr));
    }

    @GetMapping("list")
    public GraceJSONResult list(@RequestParam String vlogId,
                                @RequestParam(defaultValue = "") String userId,
                                @RequestParam Integer page,
                                @RequestParam Integer pageSize) {

        return GraceJSONResult.ok(
                commentService.queryVlogComments(
                        vlogId,
                        userId,
                        page,
                        pageSize));
    }

    @DeleteMapping("delete")
    public GraceJSONResult delete(@RequestParam String commentUserId,
                                @RequestParam String commentId,
                                @RequestParam String vlogId) {
        commentService.deleteComment(commentUserId,
                                    commentId,
                                    vlogId);
        return GraceJSONResult.ok();
    }

    @PostMapping("like")
    public GraceJSONResult like(@RequestParam String commentId,
                                @RequestParam String userId) {

        redis.incrementHash(REDIS_VLOG_COMMENT_LIKED_COUNTS, commentId, 1);
        redis.setHashValue(REDIS_USER_LIKE_COMMENT, userId + ":" + commentId, "1");
//        redis.hset(REDIS_USER_LIKE_COMMENT, userId, "1");


        Comment comment = commentService.getComment(commentId);
        Vlog vlog = vlogService.getVlog(comment.getVlogId());
        Map msgContent = new HashMap();
        msgContent.put("vlogId", vlog.getId());
        msgContent.put("vlogCover", vlog.getCover());
        msgContent.put("commentId", commentId);
        msgService.createMsg(userId,
                            comment.getCommentUserId(),
                            MessageEnum.LIKE_COMMENT.type,
                            msgContent);


        return GraceJSONResult.ok();
    }

    @PostMapping("unlike")
    public GraceJSONResult unlike(@RequestParam String commentId,
                                @RequestParam String userId) {

        redis.decrementHash(REDIS_VLOG_COMMENT_LIKED_COUNTS, commentId, 1);
        redis.hdel(REDIS_USER_LIKE_COMMENT, userId + ":" + commentId);

        return GraceJSONResult.ok();
    }
}
