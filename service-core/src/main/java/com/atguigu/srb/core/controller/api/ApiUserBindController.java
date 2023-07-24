package com.atguigu.srb.core.controller.api;


import com.atguigu.srb.common.result.R;
import com.atguigu.srb.core.hfbapi.RequestHelper;
import com.atguigu.srb.core.pojo.entity.UserBind;
import com.atguigu.srb.core.pojo.vo.UserBindVO;
import com.atguigu.srb.core.service.UserBindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 用户绑定表 前端控制器
 * </p>
 *
 * @author Mr.xu
 * @since 2023-07-03
 */
@RestController
@RequestMapping("/api/core/userBind")
public class ApiUserBindController {

    @Autowired
    UserBindService userBindService;

    @PostMapping("notify")
    public String commitBindNotify(HttpServletRequest request){
        Map<String, String[]> requestParameterMap = request.getParameterMap();
        Map<String, Object> stringObjectMap = RequestHelper.switchMap(requestParameterMap);
        userBindService.commitBindNotify(stringObjectMap);
        return "success";
    }


    @PostMapping("commitBind")
    public R commitBind(@RequestBody UserBindVO userBindVO, HttpServletRequest request){
        String userId = request.getHeader("userId");
        String form = userBindService.commitBind(userBindVO,userId);
        return R.ok().data("form",form);
    }



}

