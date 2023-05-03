package com.tictok.controller;

import com.tictok.MinIOConfig;
import com.tictok.base.BaseInfoProperties;
import com.tictok.bo.UpdatedUserBO;
import com.tictok.enums.FileTypeEnum;
import com.tictok.enums.UserInfoModifyType;
import com.tictok.grace.result.GraceJSONResult;
import com.tictok.grace.result.ResponseStatusEnum;
import com.tictok.pojo.Users;
import com.tictok.service.UserService;
import com.tictok.utils.MinIOUtils;
import com.tictok.vo.UsersVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Api(tags = "UserInfoController user api")
@RequestMapping("userInfo")
@RestController
public class UserInfoController extends BaseInfoProperties {

    @Autowired
    private UserService userService;

    @GetMapping("query")
    public GraceJSONResult query(@RequestParam String userId) throws Exception {

        Users user = userService.getUser(userId);

        UsersVO usersVO = new UsersVO();
        BeanUtils.copyProperties(user, usersVO);

        String myFollowsCountsStr = redis.get(REDIS_MY_FOLLOWS_COUNTS + ":" + userId);
        String myFansCountsStr = redis.get(REDIS_MY_FANS_COUNTS + ":" + userId);
        String likedVlogerCountsStr = redis.get(REDIS_VLOGER_BE_LIKED_COUNTS + ":" + userId);

        Integer myFollowsCounts = 0;
        Integer myFansCounts = 0;
        Integer likedVlogCounts = 0;
        Integer likedVlogerCounts = 0;
        Integer totalLikeMeCounts = 0;

        if (StringUtils.isNotBlank(myFollowsCountsStr)) {
            myFollowsCounts = Integer.valueOf(myFollowsCountsStr);
        }
        if (StringUtils.isNotBlank(myFansCountsStr)) {
            myFansCounts = Integer.valueOf(myFansCountsStr);
        }
        if (StringUtils.isNotBlank(likedVlogerCountsStr)) {
            likedVlogerCounts = Integer.valueOf(likedVlogerCountsStr);
        }
        totalLikeMeCounts = likedVlogCounts + likedVlogerCounts;

        usersVO.setMyFollowsCounts(myFollowsCounts);
        usersVO.setMyFansCounts(myFansCounts);
        usersVO.setTotalLikeMeCounts(totalLikeMeCounts);

        return GraceJSONResult.ok(usersVO);
    }

    @PostMapping("modifyUserInfo")
    public GraceJSONResult modifyUserInfo(@RequestBody UpdatedUserBO updatedUserBO,
                                          @RequestParam Integer type)
            throws Exception {

        UserInfoModifyType.checkUserInfoTypeIsRight(type);

        Users newUserInfo = userService.updateUserInfo(updatedUserBO, type);

        return GraceJSONResult.ok(newUserInfo);
    }

    @Autowired
    private MinIOConfig minIOConfig;

    @PostMapping("modifyImage")
    public GraceJSONResult modifyImage(@RequestParam String userId,
                                       @RequestParam Integer type,
                                       MultipartFile file) throws Exception {

        if (type != FileTypeEnum.BGIMG.type && type != FileTypeEnum.FACE.type) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.FILE_UPLOAD_FAILD);
        }

        String fileName = file.getOriginalFilename();

        MinIOUtils.uploadFile(minIOConfig.getBucketName(),
                fileName,
                file.getInputStream());

        String imgUrl = minIOConfig.getFileHost()
                + "/"
                + minIOConfig.getBucketName()
                + "/"
                + fileName;


        UpdatedUserBO updatedUserBO = new UpdatedUserBO();
        updatedUserBO.setId(userId);

        if (type == FileTypeEnum.BGIMG.type) {
            updatedUserBO.setBgImg(imgUrl);
        } else {
            updatedUserBO.setFace(imgUrl);
        }
        Users users = userService.updateUserInfo(updatedUserBO);

        return GraceJSONResult.ok(users);
    }
}
