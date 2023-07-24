package com.atguigu.srb.core.controller.api;


import com.atguigu.srb.common.result.R;
import com.atguigu.srb.core.pojo.entity.BorrowInfo;
import com.atguigu.srb.core.service.BorrowInfoService;
import com.atguigu.srb.core.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 借款信息表 前端控制器
 * </p>
 *
 * @author Mr.xu
 * @since 2023-07-03
 */
@RestController
@RequestMapping("/api/core/borrowInfo")
public class ApiBorrowInfoController {

    @Autowired
    BorrowInfoService borrowInfoService;

    @PostMapping("save")
    public R saveBorrowInfo(HttpServletRequest request,@RequestBody BorrowInfo borrowInfo){
        String userId = request.getHeader("userId");
        borrowInfoService.saveBorrowInfo(borrowInfo,userId);
        return R.ok();
    }

    @GetMapping("getBorrowInfoStatusById")
    public R getBorrowInfoStatusById(HttpServletRequest request){
        String userId = request.getHeader("userId");
        Integer borrowInfoStatus = borrowInfoService.getBorrowInfoStatusById(userId);
        return R.ok().data("borrowInfoStatus",borrowInfoStatus);
    }

}

