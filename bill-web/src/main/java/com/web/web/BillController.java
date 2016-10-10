package com.web.web;

import com.biz.dao.auto.CateMapper;
import com.biz.dao.auto.HouseMapper;
import com.biz.dao.auto.PersonMapper;
import com.biz.dao.auto.RefundMapper;
import com.biz.dao.biz.HouseBizMapper;
import com.biz.model.auto.*;
import com.biz.service.BillService;
import com.biz.util.RequestUtil;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

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
    @Autowired
    BillService billService;
    @Autowired
    CateMapper cateMapper;
    @Autowired
    HouseBizMapper houseBizMapper;
    @Autowired
    RefundMapper refundMapper;

    /**
     * 报账流程
     * @param openId
     * @param model
     * @return
     */
    @RequestMapping("sub_bill")
    public String bill(String openId, String msg, Model model){
        CateExample example = new CateExample();
        List<Cate> catelist = cateMapper.selectByExample(example);
        model.addAttribute("catelist", catelist);

        House house = houseBizMapper.houseAllPerson(openId);
        List<String> list = new ArrayList<>();
        list.add(house.getCreatePerson());
        if(null != house.getPlusPerson() && house.getPlusPerson().contains(",")) {
            String[] persons = house.getPlusPerson().split(",");
            for (int i = 0; i < persons.length; i++){
                list.add(persons[i]);
            }
        }
        PersonExample exa = new PersonExample();
        PersonExample.Criteria criteria = exa.createCriteria();
        criteria.andOpenIdIn(list);
        List<Person> perlist = personMapper.selectByExample(exa);
        model.addAttribute("perlist", perlist);

        model.addAttribute("openId", openId);
        if(null != msg && !"".equals(msg)){
            model.addAttribute("msg", msg);
        }
        return "bill.jsp";
    }

    @RequestMapping("bill_add")
    public void bill_add(@RequestParam(value = "file", required = false) MultipartFile file,
                           HttpServletRequest request, Bill bill, String plus_person, Model model,
                           HttpServletResponse response) throws IOException {
        String openId = bill.getOpenId();
        model.addAttribute("openId", openId);
        if (!file.isEmpty()) {
            try {
                String newName = UUID.randomUUID().toString().replace("-", "") +
                        file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                // 文件保存路径
                String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"
                        + newName;
                // 转存文件
                file.transferTo(new File(filePath));
                bill.setImg(request.getContextPath() + "/upload/" + newName);
            } catch (Exception e) {
                LOG.error("创建订单图片上传失败");
                e.printStackTrace();
            }finally {
                response.sendRedirect("sub_bill.do?msg=success&openId="+openId);
            }
        }

        billService.insertBill(bill, plus_person);
        response.sendRedirect("sub_bill.do?msg=success&openId="+openId);
        //传到重定向的方法openId的参数多了，奇怪，再看看，先用response
//        return "redirect:sub_bill.do?msg=success&openId=" + bill.getOpenId();
    }

    @RequestMapping("refundBill")
    public String refundBill(String openId, Integer refundStatus, Model model){
        RefundExample example = new RefundExample();
        RefundExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        criteria.andRefundStatusEqualTo(refundStatus);
        List<Refund> list = refundMapper.selectByExample(example);
        model.addAttribute("list", list);
        return "refund.jsp";
    }


}