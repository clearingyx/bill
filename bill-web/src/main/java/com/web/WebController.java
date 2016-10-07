package com.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/10/7.
 */
@Controller
public class WebController {

    @RequestMapping("bill")
    public String bill(String openId, Model model){
        model.addAttribute("openId", openId);
        return "bill.jsp";
    }
}
