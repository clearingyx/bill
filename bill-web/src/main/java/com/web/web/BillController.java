package com.web.web;

import com.biz.dao.auto.*;
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
    @Autowired
    BillMapper billMapper;

    /**
     * 跳转报账页面
     * @param openId
     * @param model
     * @return
     */
    @RequestMapping("sub_bill")
    public String bill(String openId, String msg, Model model){
        //返回消费分类
        CateExample example = new CateExample();
        List<Cate> catelist = cateMapper.selectByExample(example);
        model.addAttribute("catelist", catelist);

        //罗列该房屋的所用租户
        House house = houseBizMapper.houseAllPerson(openId);
        List<String> list = new ArrayList<>();
        //加入承租人，承租人也是会分担费用的
        list.add(house.getCreatePerson());
        //循环加入合租人
        if(null != house.getPlusPerson() && house.getPlusPerson().contains(",")) {
            String[] persons = house.getPlusPerson().split(",");
            for (int i = 0; i < persons.length; i++){
                list.add(persons[i]);
            }
        }

        //得到这些用户的信息
        PersonExample exa = new PersonExample();
        PersonExample.Criteria criteria = exa.createCriteria();
        criteria.andOpenIdIn(list);
        List<Person> perlist = personMapper.selectByExample(exa);
        model.addAttribute("perlist", perlist);

        model.addAttribute("openId", openId);

        //提交账单后会重定向到这里携带msg参数，在jsp页面会判断如果存在该值会弹窗
        if(null != msg && !"".equals(msg)){
            model.addAttribute("msg", msg);
        }

        return "bill.jsp";
    }

    /**
     * 提交账单
     * @param file
     * @param request
     * @param bill
     * @param plus_person
     * @param model
     * @param response
     * @throws IOException
     */
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
            }
        }

        billService.insertBill(bill, plus_person);
        response.sendRedirect("sub_bill.do?msg=success&openId="+openId);
        //传到重定向的方法openId的参数多了，奇怪，再看看，先用response
//        return "redirect:sub_bill.do?msg=success&openId=" + bill.getOpenId();
    }

    /**
     * 订单详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("bill_info")
    public String bill_info(Integer id, Model model){
        Bill bill = billMapper.selectByPrimaryKey(id);
        model.addAttribute("bill", bill);
        return "bill_desc.jsp";
    }

    /**
     * 跳转订单查询条件界面
     */
    @RequestMapping("search_condition")
    public String search_condition(String openId ,Model model){
        model.addAttribute("openId", openId);
        return "bill_search.jsp";
    }
}
