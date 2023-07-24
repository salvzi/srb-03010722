package com.atguigu.srb.core;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyExcelListener extends AnalysisEventListener<Student> {

    // 批处理
    List<Student> batch = new ArrayList<>();

    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        System.out.println("每读取一行数据:"+student.getName());
        batch.add(student);
        if(batch.size()>=10){
            System.out.println("处理一次数据:"+batch.size());
            batch.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("当所有数据被读取之后，断后方法");
        System.out.println("处理最后"+batch.size()+"条数据，断后方法");

    }
}
