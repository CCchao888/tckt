package me.thinkchao.tckt.order.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.thinkchao.tckt.model.order.OrderInfo;
import me.thinkchao.tckt.order.service.OrderInfoService;
import me.thinkchao.tckt.result.Result;
import me.thinkchao.tckt.vo.order.OrderInfoQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 订单表 订单表 前端控制器
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-10
 */
@RestController
@RequestMapping("admin/order/orderInfo")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    //订单列表
    @GetMapping("{page}/{limit}")
    public Result listOrder(@PathVariable("page") Long page,
                            @PathVariable("limit") Long limit,
                            OrderInfoQueryVo orderInfoQueryVo) {

        //创建page对象
        Page<OrderInfo> pageParam = new Page<>(page, limit);
        Map<String, Object> map = orderInfoService.selectOrderInfoPage(pageParam, orderInfoQueryVo);
        return Result.success(map);

    }











}

