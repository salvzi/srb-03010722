package com.atguigu.srb.core.controller.api;


import com.atguigu.srb.common.result.R;
import com.atguigu.srb.common.util.JwtUtils;
import com.atguigu.srb.core.pojo.entity.Borrower;
import com.atguigu.srb.core.pojo.vo.BorrowerVO;
import com.atguigu.srb.core.service.BorrowerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 借款人 前端控制器
 * </p>
 *
 * @author Mr.xu
 * @since 2023-07-03
 */
@RestController
@RequestMapping("/api/core/borrower")
public class ApiBorrowerController {

    @Autowired
    BorrowerService borrowerService;



    @ApiOperation("保存借款人信息")
    @PostMapping("/auth/save")
    public R save(@RequestBody BorrowerVO borrowerVO, HttpServletRequest request) {
        String userId = request.getHeader("userId");
        borrowerService.saveBorrowerVOByUserId(borrowerVO, userId);
        return R.ok();
    }

    @GetMapping("getBorrowerStatus")
    public R getBorrowerStatus(HttpServletRequest request){
        String userId = request.getHeader("userId");
        Integer borrowerStatus = borrowerService.getBorrowerStatus(userId);
        return R.ok().data("borrowerStatus",borrowerStatus);
    }

}

