package me.thinkchao.tckt.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.thinkchao.tckt.model.order.OrderInfo;
import me.thinkchao.tckt.vo.order.OrderInfoQueryVo;

import java.util.Map;

/**
 * <p>
 * 订单表 订单表 服务类
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-10
 */
public interface OrderInfoService extends IService<OrderInfo> {

    // 分页查询订单列表
    Map<String, Object> selectOrderInfoPage(Page<OrderInfo> pageParam, OrderInfoQueryVo orderInfoQueryVo);
}
