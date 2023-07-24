package com.atguigu.srb.oss.service.impl;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.srb.oss.config.OssProperties;
import com.atguigu.srb.oss.service.OssService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {


    @Override
    public String uploadImage(MultipartFile multipartFile, String imageType) {

        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String dateFolder = new DateTime().toString("/yyyy/MM/dd/");
        String originalFilename = multipartFile.getOriginalFilename();
        String filenameExtension = StringUtils.getFilenameExtension(originalFilename);
        String fileNameWithPath = imageType+dateFolder+ UUID.randomUUID().toString() +"."+filenameExtension;// 带路径的文件名
        
        OSS oss = new OSSClientBuilder().build(OssProperties.ENDPOINT,OssProperties.KEY_ID,OssProperties.KEY_SECRET);
        oss.putObject(OssProperties.BUCKET_NAME,fileNameWithPath,inputStream,null);
        oss.shutdown();

        return "https://"+OssProperties.BUCKET_NAME+"."+OssProperties.ENDPOINT+"/"+fileNameWithPath;
    }

    @Override
    public void deleteImage(String url) {
        OSS oss = new OSSClientBuilder().build(OssProperties.ENDPOINT,OssProperties.KEY_ID,OssProperties.KEY_SECRET);
        // 从url中截取fileName
        String fileNameWithPath = url.substring(("https://"+OssProperties.BUCKET_NAME+".oss-cn-shenzhen.aliyuncs.com/").length());
        oss.deleteObject(OssProperties.BUCKET_NAME,fileNameWithPath);
        oss.shutdown();
    }

    public static void main(String[] args) {
        System.out.println("/2023/7/11/");

        String s = new DateTime().toString("/yyyy/MM/dd/");
        System.out.println(s);

    }
}
