package com.atguigu.srb.core.controller.api;


import com.atguigu.srb.common.exception.Assert;
import com.atguigu.srb.common.result.R;
import com.atguigu.srb.common.result.ResponseEnum;
import com.atguigu.srb.core.hfbapi.RequestHelper;
import com.atguigu.srb.core.pojo.entity.TransFlow;
import com.atguigu.srb.core.pojo.entity.UserAccount;
import com.atguigu.srb.core.service.TransFlowService;
import com.atguigu.srb.core.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 * 用户账户 前端控制器
 * </p>
 *
 * @author Mr.xu
 * @since 2023-07-03
 */
@RestController
@RequestMapping("/api/core/userAccount")
public class ApiUserAccountController {

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    TransFlowService transFlowService;

    @GetMapping("getUserAccount")
    public R getUserAccount(HttpServletRequest request){
        String userId = request.getHeader("userId");
        BigDecimal account = userAccountService.getUserAccount(userId);
        return R.ok().data("account",account);
    }


    @PostMapping("commitCharge/{chargeAmt}")
    public R commitCharge(@PathVariable BigDecimal chargeAmt, HttpServletRequest request){
        System.out.println(1);
        String userId = request.getHeader("userId");
        String form = userAccountService.commitCharge(userId,chargeAmt);
        return R.ok().data("form",form);
    }

    @PostMapping("notify")
    public String notifyCommitCharge(HttpServletRequest request){
        Map<String, String[]> requestParameterMap = request.getParameterMap();
        Map<String, Object> stringObjectMap = RequestHelper.switchMap(requestParameterMap);
        String agentBillNo= (String)stringObjectMap.get("agentBillNo");

        // 幂等性校验
        boolean b = transFlowService.isTranExist(agentBillNo);
        Assert.isTrue(!b, ResponseEnum.ERROR);
        userAccountService.notifyCommitCharge(stringObjectMap);
        return "success";
    }


}

