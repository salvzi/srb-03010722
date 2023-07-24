package com.atguigu.srb.core.controller.admin;


import com.atguigu.srb.common.exception.Assert;
import com.atguigu.srb.common.result.R;
import com.atguigu.srb.common.result.ResponseEnum;
import com.atguigu.srb.core.pojo.entity.IntegralGrade;
import com.atguigu.srb.core.service.IntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 积分等级表 前端控制器
 * </p>
 *
 * @author Mr.xu
 * @since 2023-07-03
 */
@RestController
@RequestMapping("/admin/core/integralGrade")
@Api(tags = "积分等级管理接口")
@Slf4j
public class AdminIntegralGradeController {

    @Autowired
    IntegralGradeService integralGradeService;

    @DeleteMapping("/removeById/{id}")
    public R removeById(@PathVariable Long id){
        integralGradeService.removeById(id);
        return R.ok();
    }


    @GetMapping("/getById/{id}")
    public R getById(@PathVariable Long id){
        IntegralGrade byId = integralGradeService.getById(id);
        return R.ok().data("integralGrade",byId);
    }

    @GetMapping("/list")
    public R getList(){
        List<IntegralGrade> list = integralGradeService.list();

        for (IntegralGrade integralGrade : list) {
            
        }

        for (int i = 0; i < list.size(); i++) {
            
        }

        List<Object> objects = list.stream().map((integralGrade)->{
            Object o = new Object();
            return o;
        }).collect(Collectors.toList());
        
        return R.ok().data("list",list);
    }

    @PutMapping("updateById")
    public R updateById(@RequestBody IntegralGrade integralGrade){
        boolean b = integralGradeService.updateById(integralGrade);
        R ok = R.ok();
        ok.data("flag",b);
        return R.ok().data("flag",b);
    }

    @PostMapping("/save")
    @ApiOperation(value = "积分等级新增")
    public R saveGrade(@ApiParam(value = "积分等级对象") @RequestBody IntegralGrade integralGrade){
        // 积分开始，积分结束，借款金额，都不可以小于等于0
        BigDecimal borrowAmount = integralGrade.getBorrowAmount();
        Integer integralStart = integralGrade.getIntegralStart();
        Integer integralEnd = integralGrade.getIntegralEnd();
        Assert.isTrue(borrowAmount!=null&&borrowAmount.intValue()>0&&integralStart>0&&integralEnd>0&&integralEnd>integralStart, ResponseEnum.ERROR);
        integralGradeService.save(integralGrade);
        return R.ok();
    }
}

