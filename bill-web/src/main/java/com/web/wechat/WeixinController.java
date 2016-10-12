package com.web.wechat;

import com.biz.dao.auto.PersonMapper;
import com.biz.model.auto.Person;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.BaseEvent;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.github.sd4324530.fastweixin.servlet.WeixinControllerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 2016/10/6.
 */
@RestController
@RequestMapping("/weixin")
public class WeixinController extends WeixinControllerSupport {
    private static final Logger LOG = LoggerFactory.getLogger(WeixinController.class);
    String host = ResourceBundle.getBundle("config").getString("host");

    @Autowired
    PersonMapper personMapper;

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
        return this.aes_key;
//        return null;
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

    /**
     * 主方法
     * @param request
     * @return
     */
    @RequestMapping("weixin")
    public Object weixin(HttpServletRequest request) {
        LOG.info("进入weixin方法");
        return processRequest(request);
    }

    /**
     * 重写父类方法，返回访问链接
     */
    @Override
    protected BaseMsg handleTextMsg(TextReqMsg msg) {
        return new TextMsg(host + "/base/index.do?openId="+msg.getFromUserName());
    }

    /**
     * 重写关注方法
     * @param event
     * @return
     */
    @Override
    protected BaseMsg handleSubscribe(BaseEvent event) {
        Person person = personMapper.selectByPrimaryKey(event.getFromUserName());
        if(person == null){
            person = new Person();
            person.setOpenId(event.getFromUserName());
            person.setCreateDate(new Date(event.getCreateTime()));
            int temp = personMapper.insertSelective(person);
            if (temp < 1) {
                LOG.error("新关注用户插入数据库失败！");
            }
        } else {
            person.setStatus(1);
            int temp = personMapper.updateByPrimaryKey(person);
            if (temp < 1) {
                LOG.error("已经取消关注用户重新关注修改数据库失败！");
            }
        }
        return new TextMsg(host + "/base/index.do?openId="+event.getFromUserName());
    }

    /**
     * 重写取消关注方法
     * @param event
     * @return
     */
    @Override
    protected BaseMsg handleUnsubscribe(BaseEvent event) {
        Person person = new Person();
        person.setOpenId(event.getFromUserName());
        person.setStatus(2);
        int temp = personMapper.updateByPrimaryKey(person);
        if(temp < 1){
            LOG.error("用户取消关注失败！");
        }
        return new BaseMsg();
    }
}