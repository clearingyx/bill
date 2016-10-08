package com.web.web;

import com.biz.dao.auto.HouseMapper;
import com.biz.dao.auto.PersonMapper;
import com.biz.model.auto.House;
import com.biz.model.auto.Person;
import com.web.wechat.WeixinController;
import org.apache.log4j.varia.FallbackErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ResourceBundle;

/**
 * Created by Administrator on 2016/10/7.
 */
@Controller
@RequestMapping("bill")
public class WebController {
    private static final Logger LOG = LoggerFactory.getLogger(WebController.class);
    String host = ResourceBundle.getBundle("config").getString("host");

    @Autowired
    HouseMapper houseMapper;
    @Autowired
    PersonMapper personMapper;

    @RequestMapping("index")
    public String index(String openId, Model model){
        model.addAttribute("openId", openId);
        model.addAttribute("host", host);
        //找到合租人，在hourse里面

        return "index.jsp";
    }

    /**
     * 登记住房信息，成为承租人，加入合租人等等，暂时不用
     * @param openId
     * @param model
     * @return
     */
    @RequestMapping("house")
    public String house(String openId, Model model){
        model.addAttribute("openId", openId);
        return "house.jsp";
    }

    @ResponseBody
    @RequestMapping("house_add")
    public Object house_add(Person person, String power){
        boolean result = false;
        if(null == power || null == person || null == person.getOpenId() || null == person.getNickname()) {
            return result;
        }
        if(person.getNickname().contains("#") || person.getNickname().contains("$") || "".equals(person.getNickname().trim()) ||
                person.getNickname().contains("%") || person.getNickname().contains("&")){
            return "error";
        }

        int temp = personMapper.updateByPrimaryKey(person);
        if(temp < 1){
            LOG.error("更新用户昵称电话失败！");
            return false;
        }

        House house = new House();
        if ("sup".equals(power)) {
            house.setCreatePerson(person.getOpenId());
            houseMapper.insertSelective(house);
            if (temp < 1) {
                LOG.error("添加承租人失败");
                return false;
            } else result = true;
        }
        return result;
    }

    /**
     * 报账流程
     * @param openId
     * @param model
     * @return
     */
    @RequestMapping("sub_bill")
    public String bill(String openId, Model model){
        model.addAttribute("openId", openId);
        return "bill.jsp";
    }
}
