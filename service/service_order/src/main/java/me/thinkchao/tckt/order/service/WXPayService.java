package me.thinkchao.tckt.order.service;

import java.util.Map;

/**
 * Author:chao
 * Date:2023-11-18
 * Description:
 */
public interface WXPayService {

    // 微信支付
    Object createJsapi(String orderNo);

    //根据订单号调用微信支付接口查询支付状态
    Map<String, String> queryPayStatus(String orderNo);
}
