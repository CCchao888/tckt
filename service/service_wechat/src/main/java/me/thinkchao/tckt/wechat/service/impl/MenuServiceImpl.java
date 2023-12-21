package me.thinkchao.tckt.wechat.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.thinkchao.tckt.exception.TcktException;
import me.thinkchao.tckt.model.wechat.Menu;
import me.thinkchao.tckt.vo.wechat.MenuVo;
import me.thinkchao.tckt.wechat.mapper.MenuMapper;
import me.thinkchao.tckt.wechat.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-11
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private WxMpService wxMpService;

    // 获取一级菜单
    @Override
    public List<Menu> findMenuOneInfo() {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",0);
        wrapper.eq("is_deleted",0);
        List<Menu> list = baseMapper.selectList(wrapper);
        return list;
    }

    //获取所有菜单
    @Override
    public List<MenuVo> findMenuInfo() {
        List<MenuVo> finalMenuList = new ArrayList<>();
        List<Menu> menuList = baseMapper.selectList(null);
        List<Menu> menuOneList = menuList.stream()
                .filter(menu -> menu.getParentId().longValue() == 0)
                .collect(Collectors.toList());
        for(Menu oneMenu : menuOneList){
            MenuVo menuVo = new MenuVo();
            BeanUtils.copyProperties(oneMenu,menuVo);

            List<Menu> menuTwoList = menuList.stream()
                    .filter(menu -> menu.getParentId().longValue() == oneMenu.getId())
                    .collect(Collectors.toList());
            List<MenuVo> children = new ArrayList<>();
            for(Menu twoMenu : menuTwoList){
                MenuVo menuVoTwo = new MenuVo();
                BeanUtils.copyProperties(twoMenu,menuVoTwo);
                children.add(menuVoTwo);
            }
            menuVo.setChildren(children);
            finalMenuList.add(menuVo);
        }
        return finalMenuList;
    }

    // 同步菜单
    @Override
    public void syncMenu() {
        //获取所有菜单数据
        List<MenuVo> menuVoList = this.findMenuInfo();
        //封装button里面结构，数组格式
        JSONArray buttonList = new JSONArray();
        for(MenuVo oneMenuVo : menuVoList){
            //json对象，一级菜单
            JSONObject one = new JSONObject();
            one.put("name",oneMenuVo.getName());
            //json数组   二级菜单
            JSONArray subButton  = new JSONArray();
            for(MenuVo twoMenuVo : oneMenuVo.getChildren()){
                    JSONObject view = new JSONObject();
                    view.put("type", twoMenuVo.getType());
                    if(twoMenuVo.getType().equals("view")) {
                        view.put("name", twoMenuVo.getName());
                        view.put("url", "http://localhost:8840/#"+twoMenuVo.getUrl());
//                        view.put("url", "http://ggkt2.vipgz1.91tunnel.com/#"
//                                +twoMenuVo.getUrl());
                    } else {
                        view.put("name", twoMenuVo.getName());
                        view.put("key", twoMenuVo.getMeunKey());
                    }
                    subButton.add(view);
                }
            one.put("sub_button", subButton);
            buttonList.add(one);
            }
        //封装最外层的button部分
        JSONObject button = new JSONObject();
        button.put("button",buttonList);
        try {
            this.wxMpService.getMenuService().menuCreate(button.toJSONString());
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new TcktException(20001,"公众号菜单同步失败");
        }
    }

    // 删除菜单
    @Override
    public void removeMenu() {
        try {
            wxMpService.getMenuService().menuDelete();
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new TcktException(20001,"公众号菜单删除失败");
        }
    }


}


