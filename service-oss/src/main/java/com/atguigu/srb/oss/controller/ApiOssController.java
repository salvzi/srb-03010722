package com.atguigu.srb.oss.controller;


import com.atguigu.srb.common.result.R;
import com.atguigu.srb.oss.service.OssService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/oss/file")
public class ApiOssController {

    @Autowired
    OssService ossService;


    @DeleteMapping("/deleteImage")
    public R deleteImage(String url){
        ossService.deleteImage(url);
        return R.ok();
    }

    @PostMapping("/uploadImage")
    public R uploadImage(@RequestParam("file")MultipartFile multipartFile,String imageType){
        String url = ossService.uploadImage(multipartFile,imageType);
        return R.ok().data("url",url);
    }
}
