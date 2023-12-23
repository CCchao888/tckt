package me.thinkchao.tckt.wechat.controller;


import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import me.thinkchao.tckt.exception.TcktException;
import me.thinkchao.tckt.model.wechat.Menu;
import me.thinkchao.tckt.result.Result;
import me.thinkchao.tckt.vo.wechat.MenuVo;
import me.thinkchao.tckt.wechat.service.MenuService;
import me.thinkchao.tckt.wechat.utils.ConstantPropertiesUtil;
import me.thinkchao.tckt.wechat.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 微信公众号菜单 前端控制器
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-11
 */
@RestController
@RequestMapping("admin/wechat/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    //公众号菜单删除
    @ApiOperation(value = "删除菜单")
    @DeleteMapping("removeMenu")
    public Result deleteMenu() {
        menuService.removeMenu();
        return Result.success(null);
    }

    //同步菜单的方法
    @ApiOperation(value = "同步菜单")
    @GetMapping("syncMenu")
    public Result syncMenu() {
        menuService.syncMenu();
        return Result.success(null);
    }

    //获取access_token
    @GetMapping("getAccessToken")
    public Result getAccessToken() {
        try {
            //拼接请求地址
            StringBuffer buffer = new StringBuffer();
            buffer.append("https://api.weixin.qq.com/cgi-bin/token");
            buffer.append("?grant_type=client_credential");
            buffer.append("&appid=%s");
            buffer.append("&secret=%s");
            //请求地址设置参数
            String url = String.format(buffer.toString(),
                    ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            //发送http请求
            String tokenString = HttpClientUtils.get(url);
            //获取access_token
            JSONObject jsonObject = JSONObject.parseObject(tokenString);
            String access_token = jsonObject.getString("access_token");
            //返回
            return Result.success(access_token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new TcktException(20001, "获取access_token失败");
        }
    }

    //获取所有菜单，按照一级和二级菜单封装
    @GetMapping("findMenuInfo")
    public Result findMenuInfo() {
        List<MenuVo> list = menuService.findMenuInfo();
        return Result.success(list);
    }

    //获取所有一级菜单
    @GetMapping("findOneMenuInfo")
    public Result findOneMenuInfo() {
        List<Menu> list = menuService.findMenuOneInfo();
        return Result.success(list);
    }

    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Menu menu = menuService.getById(id);
        return Result.success(menu);
    }

    @ApiOperation(value = "新增")
    @PostMapping("save")
    public Result save(@RequestBody Menu menu) {
        menuService.save(menu);
        return Result.success(null);
    }

    @ApiOperation(value = "修改")
    @PutMapping("update")
    public Result updateById(@RequestBody Menu menu) {
        menuService.updateById(menu);
        return Result.success(null);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        menuService.removeById(id);
        return Result.success(null);
    }

    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        menuService.removeByIds(idList);
        return Result.success(null);
    }

}

