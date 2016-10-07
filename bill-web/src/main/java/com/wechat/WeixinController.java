package com.wechat;

import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.BaseEvent;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.github.sd4324530.fastweixin.servlet.WeixinControllerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 2016/10/6.
 */
//@RestController
@Controller
@RequestMapping("/weixin")
public class WeixinController extends WeixinControllerSupport {
    private static final Logger LOG = LoggerFactory.getLogger(WeixinController.class);
    private ResourceBundle rb = ResourceBundle.getBundle("wx");
    private String TOKEN = rb.getString("token");
    private String appid = rb.getString("wechat_appid");
    private String aes_key = rb.getString("aes_key");
    //设置TOKEN，用于绑定微信服务器
    @Override
    protected String getToken() {
        return TOKEN;
    }
    //使用安全模式时设置：APPID
    //不再强制重写，有加密需要时自行重写该方法
    @Override
    protected String getAppId() {
        return this.appid;
    }
    //使用安全模式时设置：密钥
    //不再强制重写，有加密需要时自行重写该方法
    @Override
    protected String getAESKey() {
        //return this.aes_key;
        return null;
    }

    /**
     * 绑定服务器的时候需要
     * @param request
     * @return
     */
//    @Override
//    @RequestMapping("weixin")
//    public void bindServer(HttpServletRequest request, HttpServletResponse response) {
//        super.bindServer(request, response);
//    }

    @RequestMapping("weixin")
    @ResponseBody
    public Object weixin(HttpServletRequest request) {
        return processRequest(request);
    }

    //重写父类方法，处理对应的微信消息
    @Override
    protected BaseMsg handleTextMsg(TextReqMsg msg) {
        return new TextMsg("点击报账：http://zp.tunnel.phpor.me/bill.do?openId="+msg.getToUserName());
    }

    @Override
    protected BaseMsg handleSubscribe(BaseEvent event) {
        return new TextMsg("点击报账：http://zp.tunnel.phpor.me/bill.do?openId="+event.getFromUserName());
    }
}