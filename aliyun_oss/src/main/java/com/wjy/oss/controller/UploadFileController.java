package com.wjy.oss.controller;

import com.wjy.oss.utils.OSSClientUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.ch.FileKey;

import java.io.IOException;

/**
 * @author wangjingyang
 * @date 2021-02-03 16:41
 */
@RestController
@RequestMapping("/upload")
public class UploadFileController {

    @RequestMapping(value = "/file",method = RequestMethod.POST)
    public String uploadFile(MultipartFile file) throws IOException {
        String upload = OSSClientUtil.upload(file);
        return upload;
    }
}
