package com.tictok.controller;

import com.tictok.MinIOConfig;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "FileController file upload api")
@RestController
public class FileController {

    @Autowired
    private MinIOConfig minIOConfig;

//    @PostMapping("upload")
//    public GraceJSONResult upload(MultipartFile file) throws Exception {
//
//        String fileName = file.getOriginalFilename();
//
//        MinIOUtils.uploadFile(minIOConfig.getBucketName(),
//                              fileName,
//                              file.getInputStream());
//
//        String imgUrl = minIOConfig.getFileHost()
//                        + "/"
//                        + minIOConfig.getBucketName()
//                        + "/"
//                        + fileName;
//
//        return GraceJSONResult.ok(imgUrl);
//    }
}
