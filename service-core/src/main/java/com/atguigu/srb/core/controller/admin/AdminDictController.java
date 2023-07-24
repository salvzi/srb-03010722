package com.atguigu.srb.core.controller.admin;


import com.alibaba.excel.EasyExcel;
import com.atguigu.srb.common.result.R;
import com.atguigu.srb.core.pojo.dto.DictDTO;
import com.atguigu.srb.core.pojo.entity.Dict;
import com.atguigu.srb.core.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author Mr.xu
 * @since 2023-07-03
 */
@RestController
@RequestMapping("/admin/core/dict")
public class AdminDictController {

    @Autowired
    DictService dictService;

    @GetMapping("getDictListByParentId/{parentId}")
    public R getDictListByParentId(@PathVariable Long parentId){
        List<Dict> dicts = dictService.getDictListByParentId(parentId);
        return R.ok().data("list",dicts);
    }

    @GetMapping("exportExcel")
    public void exportExcel(HttpServletResponse response){
        ServletOutputStream outputStream = null;
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = "dict";
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        try {
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<DictDTO> dictDTOS = dictService.getDictDTOS();
        EasyExcel.write(outputStream, DictDTO.class).sheet("字典").doWrite(dictDTOS);
    }

    @PostMapping("importExcel")
    public R importExcel(@RequestParam("file") MultipartFile multipartFile){
        dictService.importExcel(multipartFile);
        return R.ok();
    }

}

