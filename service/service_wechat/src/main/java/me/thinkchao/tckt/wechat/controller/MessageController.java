package me.thinkchao.tckt.wechat.controller;

import me.chanjar.weixin.common.error.WxErrorException;
import me.thinkchao.tckt.result.Result;
import me.thinkchao.tckt.wechat.service.MessageService;
import me.thinkchao.tckt.wechat.utils.SHA1;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:chao
 * Date:2023-11-12
 * Description:
 */
@RestController
@RequestMapping("/api/wechat/message")
public class MessageController {

    private static final String token = "tckt";

    @Autowired
    private MessageService messageSe;


    // 推送支付消息
    @GetMapping("/pushPayMessage")
    public Result pushPayMessage() throws WxErrorException {
        messageSe.pushPayMessage(1L);
        return Result.success(null);
    }

    /**
     * 服务器有效性验证
     * @param request
     * @return
     */
    @GetMapping
    public String verifyToken(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if (this.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }
        return echostr;
    }

    /**
     * 接收微信服务器发送来的消息
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping
    public String receiveMessage(HttpServletRequest request) throws Exception {

//        WxMpXmlMessage wxMpXmlMessage = WxMpXmlMessage.fromXml(request.getInputStream());
//        System.out.println(JSONObject.toJSONString(wxMpXmlMessage));

        Map<String, String> map = this.parseXml(request);
        String message = messageSe.receiveMessage(map);
        return message;
    }

    private Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        InputStream inputStream = request.getInputStream();
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        inputStream.close();
        inputStream = null;
        return map;
    }

    private boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] str = new String[]{token, timestamp, nonce};
        //排序
        Arrays.sort(str);
        //拼接字符串
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < str.length; i++) {
            buffer.append(str[i]);
        }
        //进行sha1加密
        String temp = SHA1.encode(buffer.toString());
        //与微信提供的signature进行匹对
        return signature.equals(temp);
    }
}