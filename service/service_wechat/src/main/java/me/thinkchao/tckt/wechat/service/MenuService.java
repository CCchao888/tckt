package me.thinkchao.tckt.wechat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.thinkchao.tckt.model.wechat.Menu;
import me.thinkchao.tckt.vo.wechat.MenuVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-11
 */
public interface MenuService extends IService<Menu> {

    // 获取一级菜单
    List<Menu> findMenuOneInfo();

    // 获取所有菜单
    List<MenuVo> findMenuInfo();

    // 同步菜单
    void syncMenu();

    // 删除菜单
    void removeMenu();
}
