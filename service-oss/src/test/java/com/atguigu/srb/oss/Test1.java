package com.atguigu.srb.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSBuilder;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.atguigu.srb.oss.config.OssProperties;
import org.junit.Test;

import java.io.File;

public class Test1 {


    @Test
    public void d(){
        String fileNameWithPath = "q/aaa.jpg";// 带路径的文件名
        OSS oss = new OSSClientBuilder().build("oss-cn-shenzhen.aliyuncs.com","LTAI5tJ5hVrnSsFDrMwwEhJJ","9htMEmLs29ZsWW42VgED64Zv7N07xi");
        oss.deleteObject("srb-oss-0301",fileNameWithPath);
        oss.shutdown();
    }


    @Test
    public void c(){
        String fileNameWithPath = "q/aaa.jpg";// 带路径的文件名
        OSS oss = new OSSClientBuilder().build("oss-cn-shenzhen.aliyuncs.com","LTAI5tJ5hVrnSsFDrMwwEhJJ","9htMEmLs29ZsWW42VgED64Zv7N07xi");
        oss.putObject("srb-oss-0301",fileNameWithPath,new File("e:/aaa.jpg"),null);
        oss.shutdown();
    }


    @Test
    public void b(){
        OSS oss = new OSSClientBuilder().build("oss-cn-shenzhen.aliyuncs.com","LTAI5tJ5hVrnSsFDrMwwEhJJ","9htMEmLs29ZsWW42VgED64Zv7N07xi");
        oss.setBucketAcl("srb-oss-0301-test1", CannedAccessControlList.PublicRead);
        oss.shutdown();
    }

    @Test
    public void a(){
        OSS oss = new OSSClientBuilder().build("oss-cn-shenzhen.aliyuncs.com","LTAI5tJ5hVrnSsFDrMwwEhJJ","9htMEmLs29ZsWW42VgED64Zv7N07xi");
        oss.createBucket("srb-oss-0301-test1");
        oss.shutdown();
    }
}
