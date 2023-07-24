package com.atguigu.srb.core.controller.admin;


import com.atguigu.srb.common.result.R;
import com.atguigu.srb.core.pojo.entity.BorrowInfo;
import com.atguigu.srb.core.pojo.vo.BorrowInfoApprovalVO;
import com.atguigu.srb.core.service.BorrowInfoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 借款信息表 前端控制器
 * </p>
 *
 * @author Mr.xu
 * @since 2023-07-03
 */
@RestController
@RequestMapping("/admin/core/borrowInfo")
public class AdminBorrowInfoController {

    @Autowired
    BorrowInfoService borrowInfoService;

    @GetMapping("getBorrowInfoList")
    public R getBorrowInfoList(){
        List<BorrowInfo> borrowInfoList = borrowInfoService.getBorrowInfoList();
        return R.ok().data("borrowInfoList",borrowInfoList);
    }

    @ApiOperation("获取借款信息")
    @GetMapping("/show/{id}")
    public R show(
            @ApiParam(value = "借款id", required = true)
            @PathVariable Long id) {
        Map<String, Object> borrowInfoDetail = borrowInfoService.getBorrowInfoDetail(id);
        return R.ok().data("borrowInfoDetail", borrowInfoDetail);
    }

    @PostMapping("approvalSubmit")
    public R approvalSubmit(@RequestBody BorrowInfoApprovalVO borrowInfoApprovalVO){
        borrowInfoService.approvalSubmit(borrowInfoApprovalVO);
        return R.ok();
    }

}

