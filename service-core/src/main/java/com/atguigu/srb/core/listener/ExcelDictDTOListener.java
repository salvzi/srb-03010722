package com.atguigu.srb.core.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.atguigu.srb.core.mapper.DictMapper;
import com.atguigu.srb.core.pojo.dto.DictDTO;
import com.atguigu.srb.core.pojo.entity.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class ExcelDictDTOListener extends AnalysisEventListener<DictDTO> {

    // 批处理
    List<DictDTO> batch = new ArrayList<>();

    DictMapper dictMapper;

    public ExcelDictDTOListener(DictMapper baseMapper) {
        this.dictMapper = baseMapper;
    }

    @Override
    public void invoke(DictDTO dictDTO, AnalysisContext analysisContext) {
        batch.add(dictDTO);
        if(batch.size()>=10){
            System.out.println("处理一次数据:"+batch.size());
            dictMapper.insertBatch(batch);
            batch.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("当所有数据被读取之后，断后方法");
        System.out.println("处理最后"+batch.size()+"条数据，断后方法");
        dictMapper.insertBatch(batch);

    }

}
