package me.thinkchao.tckt.user.controller;


import io.swagger.annotations.ApiOperation;
import me.thinkchao.tckt.model.user.UserInfo;
import me.thinkchao.tckt.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-10
 */
@RestController
@RequestMapping("admin/user/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    //提高user_id获取用户信息
    @ApiOperation(value = "获取")
    @GetMapping("inner/getById/{id}")
    public UserInfo getById(@PathVariable Long id) {
        return userInfoService.getById(id);
    }
}

