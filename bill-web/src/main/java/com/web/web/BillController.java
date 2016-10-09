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
@RequestMapping("bill")
public class BillController {
    private static final Logger LOG = LoggerFactory.getLogger(BillController.class);

    @Autowired
    HouseMapper houseMapper;
    @Autowired
    PersonMapper personMapper;

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
