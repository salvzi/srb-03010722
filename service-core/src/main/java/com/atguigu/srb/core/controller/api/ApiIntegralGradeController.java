package com.atguigu.srb.core.controller.api;


import com.atguigu.srb.common.exception.Assert;
import com.atguigu.srb.common.exception.SrbBusinessException;
import com.atguigu.srb.common.result.R;
import com.atguigu.srb.common.result.ResponseEnum;
import com.atguigu.srb.core.pojo.entity.IntegralGrade;
import com.atguigu.srb.core.service.IntegralGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 积分等级表 前端控制器
 * </p>
 *
 * @author Mr.xu
 * @since 2023-07-03
 */
@RestController
@RequestMapping("/api/core/integralGrade")
public class ApiIntegralGradeController {

    @Autowired
    IntegralGradeService integralGradeService;



    @GetMapping("/getBorrowAmount")
    public R getBorrowAmount(HttpServletRequest request){
        String userId = request.getHeader("userId");
        BigDecimal borrowAmount = integralGradeService.getBorrowAmount(userId);
        return R.ok().data("borrowAmount",borrowAmount);
    }



    @PostMapping("/save")
    public R saveGrade(@RequestBody IntegralGrade integralGrade){
        // 积分开始，积分结束，借款金额，都不可以小于等于0
        BigDecimal borrowAmount = integralGrade.getBorrowAmount();
        Integer integralStart = integralGrade.getIntegralStart();
        Integer integralEnd = integralGrade.getIntegralEnd();
        Assert.isTrue(borrowAmount!=null&&borrowAmount.intValue()>0&&integralStart>0&&integralEnd>0&&integralEnd>integralStart,ResponseEnum.ERROR);
        integralGradeService.save(integralGrade);
        return R.ok();
    }

    @GetMapping("/list")
    public R getList(){
        List<IntegralGrade> list = integralGradeService.list();
        return R.ok().data("list",list);
    }

    @PutMapping("updateId")
    public R updateById(@RequestBody IntegralGrade integralGrade){
        boolean b = integralGradeService.updateById(integralGrade);
        R ok = R.ok();
        ok.data("flag",b);
        return R.ok().data("flag",b);
    }
}

