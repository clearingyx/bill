package com.web.web;

import com.biz.dao.auto.HouseMapper;
import com.biz.dao.auto.PersonMapper;
import com.biz.dao.biz.HouseBizMapper;
import com.biz.dao.biz.PersonBizMapper;
import com.biz.model.auto.House;
import com.biz.model.auto.HouseExample;
import com.biz.model.auto.Person;
import com.biz.model.auto.PersonExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 2016/10/7.
 */
@Controller
@RequestMapping("house")
public class HouseController {
    private static final Logger LOG = LoggerFactory.getLogger(HouseController.class);

    @Autowired
    HouseMapper houseMapper;
    @Autowired
    HouseBizMapper houseBizMapper;
    @Autowired
    PersonBizMapper personBizMapper;
    @Autowired
    PersonMapper personMapper;

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

    @RequestMapping("personInHouseRepeat")
    @ResponseBody
    public Object personInHouseRepeat(String openId){
        int temp = houseBizMapper.personInHouseRepeat(openId);
        if(temp > 0) return "repeat";
        else return "success";
    }

    @ResponseBody
    @RequestMapping("house_add")
    public Object house_add(Person person, String power, String myOpenId){
        //后台判断数据是否合法
        boolean result = false;
        if(null == power || null == person || null == person.getOpenId() || null == person.getNickname()) {
            return result;
        }
        if(person.getNickname().contains("#") || person.getNickname().contains("$") || "".equals(person.getNickname().trim()) ||
                person.getNickname().contains("%") || person.getNickname().contains("&")){
            return "error";
        }
        if(personBizMapper.repeatNickname(person) > 0){
            return "repeat";
        }

        House house = new House();
        if ("sup".equals(power)) {
            int temp = personMapper.updateByPrimaryKey(person);
            if(temp < 1){
                LOG.error("更新用户昵称电话失败！");
                return false;
            }
            house.setCreatePerson(person.getOpenId());
            houseMapper.insertSelective(house);
            if (temp < 1) {
                LOG.error("添加承租人失败");
                return false;
            } else result = true;
        }else{
            String openId = person.getOpenId();
            person.setOpenId(myOpenId);
            int temp = personMapper.updateByPrimaryKey(person);
            if(temp < 1){
                LOG.error("更新用户昵称电话失败！");
                return false;
            }
            house.setCreatePerson(openId);
            house.setPlusPerson(myOpenId);
            temp = houseBizMapper.addPersonInHouse(house);
            if(temp < 1){
                LOG.error("给{}的承租人添加合租人失败！", person.getOpenId());
                return false;
            } else result = true;
        }
        return result;
    }

    @RequestMapping("search_admin")
    @ResponseBody
    public Object search_admin(String nickname){
        boolean result = false;
        if(null == nickname) {
            return result;
        }
        if(nickname.contains("#") || nickname.contains("$") || "".equals(nickname.trim()) ||
                nickname.contains("%") || nickname.contains("&")){
            return "error";
        }

        return personBizMapper.searchHouseAdminByNickname(nickname);
    }

    @RequestMapping("unbundling")
    @ResponseBody
    public Object unbundling(String openId, String power){
        int temp = 0;
        if("admin".equals(power)) {
            temp = houseBizMapper.delHouseByAdminOpenId(openId);
        } else if ("person".equals(power)){
            temp = houseBizMapper.delHouseByPersonOpenId(openId+",");
        }
        if(temp < 1){
            LOG.error("解绑承租人失败！");
            return "error";
        } else {
            return "success";
        }
    }

}
