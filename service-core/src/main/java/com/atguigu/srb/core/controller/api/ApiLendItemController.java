package com.atguigu.srb.core.controller.api;


import com.atguigu.srb.common.exception.Assert;
import com.atguigu.srb.common.result.R;
import com.atguigu.srb.common.result.ResponseEnum;
import com.atguigu.srb.core.hfbapi.RequestHelper;
import com.atguigu.srb.core.pojo.entity.LendItem;
import com.atguigu.srb.core.pojo.entity.TransFlow;
import com.atguigu.srb.core.pojo.vo.InvestVO;
import com.atguigu.srb.core.service.LendItemService;
import com.atguigu.srb.core.service.TransFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 * 标的出借记录表 前端控制器
 * </p>
 *
 * @author Mr.xu
 * @since 2023-07-03
 */
@RestController
@RequestMapping("/api/core/lendItem")
public class ApiLendItemController {

    @Autowired
    LendItemService lendItemService;

    @Autowired
    TransFlowService transFlowService;

    @PostMapping("notify")
    public String notifyCommitInvest(HttpServletRequest request){
        Map<String, String[]> requestParameterMap = request.getParameterMap();
        Map<String, Object> stringObjectMap = RequestHelper.switchMap(requestParameterMap);
        String agentBillNo= (String)stringObjectMap.get("agentBillNo");
        // 幂等性校验
        boolean b = transFlowService.isTranExist(agentBillNo);
        Assert.isTrue(!b, ResponseEnum.ERROR);
        lendItemService.notifyCommitInvest(stringObjectMap);
        return "success";
    }


    @PostMapping("commitInvest")
    public R commitInvest(@RequestBody InvestVO investVO, HttpServletRequest request){
        String investUserId = request.getHeader("userId");
        String form = lendItemService.commitInvest(investUserId,investVO);
        return R.ok().data("form",form);
    }


}

