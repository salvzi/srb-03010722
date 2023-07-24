package com.atguigu.srb.core.controller.api;


import com.atguigu.srb.common.result.R;
import com.atguigu.srb.core.pojo.entity.UserInfo;
import com.atguigu.srb.core.pojo.vo.LoginVO;
import com.atguigu.srb.core.pojo.vo.RegisterVO;
import com.atguigu.srb.core.pojo.vo.UserInfoVO;
import com.atguigu.srb.core.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户基本信息 前端控制器
 * </p>
 *
 * @author Mr.xu
 * @since 2023-07-03
 */
@RestController
@RequestMapping("/api/core/userInfo")
//@CrossOrigin
public class ApiUserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @GetMapping("test")
    public R test(HttpServletRequest request){
        String userId = request.getHeader("userId");
        System.out.println("鉴权测试");
        return R.ok();
    }

    @PostMapping("register")
    public R register(@RequestBody RegisterVO registerVO){
        userInfoService.register(registerVO);
        return R.ok();
    }

    @PostMapping("login")
    public R login(@RequestBody LoginVO loginVO){
        UserInfoVO userInfoVO = userInfoService.login(loginVO);
        return R.ok().data("userInfo",userInfoVO);
    }

    @GetMapping("/checkMobileExist/{mobile}")
    public boolean checkMobileExist(@PathVariable String mobile){
        boolean b = userInfoService.checkMobileExist(mobile);
        return b;
    }
}

