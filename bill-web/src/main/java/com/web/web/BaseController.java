package com.web.web;

import com.biz.dao.auto.HouseMapper;
import com.biz.dao.auto.PersonMapper;
import com.biz.model.auto.House;
import com.biz.model.auto.Person;
import com.biz.model.auto.PersonExample;
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
@RequestMapping("base")
public class BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    HouseMapper houseMapper;
    @Autowired
    PersonMapper personMapper;

    @RequestMapping("index")
    public String index(String openId, Model model){
        model.addAttribute("openId", openId);
        return "index.jsp";
    }
}
