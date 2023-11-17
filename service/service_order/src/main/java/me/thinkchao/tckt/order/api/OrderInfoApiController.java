package me.thinkchao.tckt.order.api;

import io.swagger.annotations.ApiOperation;
import me.thinkchao.tckt.order.service.OrderInfoService;
import me.thinkchao.tckt.result.Result;
import me.thinkchao.tckt.vo.order.OrderFormVo;
import me.thinkchao.tckt.vo.order.OrderInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Author:chao
 * Date:2023-11-17
 * Description:
 */
@RestController
@RequestMapping("api/order/orderInfo")
public class OrderInfoApiController {

    @Autowired
    private OrderInfoService orderInfoService;

    @ApiOperation("新增点播课程订单")
    @PostMapping("submitOrder")
    public Result submitOrder(@RequestBody OrderFormVo orderFormVo, HttpServletRequest request) {
        //返回订单id
        Long orderId = orderInfoService.submitOrder(orderFormVo);
        return Result.success(orderId);
    }

    //根据订单id获取订单信息
    @ApiOperation(value = "获取")
    @GetMapping("getInfo/{id}")
    public Result getInfo(@PathVariable Long id) {
        OrderInfoVo orderInfoVo = orderInfoService.getOrderInfoVoById(id);
        return Result.success(orderInfoVo);
    }
}