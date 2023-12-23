package me.thinkchao.tckt.wechat.service;

import java.util.Map;

/**
 * Author:chao
 * Date:2023-11-13
 * Description:
 */
public interface MessageService {

    //接收微信服务器发送来的消息
    String receiveMessage(Map<String, String> map);

    //推送支付消息
    void pushPayMessage(long id);
}
