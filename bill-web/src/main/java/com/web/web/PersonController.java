package com.web.web;

import com.biz.dao.auto.BillMapper;
import com.biz.dao.auto.CateMapper;
import com.biz.dao.auto.PersonMapper;
import com.biz.dao.auto.RefundMapper;
import com.biz.dao.biz.PersonBizMapper;
import com.biz.dao.biz.RefundBizMapper;
import com.biz.model.auto.*;
import com.biz.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;


/**
 * Created by Administrator on 2016/10/12.
 */
@Controller
@RequestMapping("person")
public class PersonController {
    @Autowired
    BillMapper billMapper;
    @Autowired
    RefundMapper refundMapper;
    @Autowired
    RefundBizMapper refundBizMapper;
    @Autowired
    CateMapper cateMapper;
    @Autowired
    PersonMapper personMapper;
    @Autowired
    PersonBizMapper personBizMapper;

    @RequestMapping("person_info")
    public String person_info(String openId, Model model){
        //存入当年当月，前端展示
        model.addAttribute("month", DateUtil.getYearMonth());
        //当月的时间
        String begin_date = DateUtil.thisMonthBegin();

        //支出汇总
        BillExample be = new BillExample();
        BillExample.Criteria ce = be.createCriteria();
        ce.andOpenIdEqualTo(openId);

        //总支出
        double sum_b = 0.00d;
        //当月支出
        double month_b = 0.00d;
        List<Bill> all_b = billMapper.selectByExample(be);
        for (int i = 0; i < all_b.size(); i++){
            Bill b = all_b.get(i);
            //总支出
            sum_b += b.getMyAccount();
            int judge = DateUtil.compare_date(b.getCreateDate(), begin_date);
            //当月支出
            if(judge == 1){
                month_b += b.getMyAccount();
            }
        }
        model.addAttribute("sum_b", new BigDecimal(sum_b).setScale(1, BigDecimal.ROUND_HALF_UP));
        model.addAttribute("month_b", new BigDecimal(month_b).setScale(1, BigDecimal.ROUND_HALF_UP));

        //欠款汇总
        RefundExample er = new RefundExample();
        RefundExample.Criteria cr = er.createCriteria();
        cr.andRefundStatusEqualTo(0);
        cr.andOpenIdEqualTo(openId);
        List<Refund> all_r = refundMapper.selectByExample(er);
        double sum_r = 0.00d;
        double month_r = 0.00d;
        for (int i = 0; i < all_r.size(); i++){
            Refund r = all_r.get(i);
            sum_r += r.getAccount();
            int judge = DateUtil.compare_date(r.getCreateDate(), begin_date);
            if(judge == 1){
                month_r += r.getAccount();
            }
        }
        model.addAttribute("sum_r", new BigDecimal(sum_r).setScale(1, BigDecimal.ROUND_HALF_UP));
        model.addAttribute("month_r", new BigDecimal(month_r).setScale(1, BigDecimal.ROUND_HALF_UP));

        //未收款汇总
        List<Refund> all_n = refundBizMapper.sumNotRefundList(openId);
        double sum_n = 0.00d;
        double month_n = 0.00d;
        for (int i = 0; i < all_n.size(); i++){
            Refund r = all_n.get(i);
            sum_n += r.getAccount();
            int judge = DateUtil.compare_date(r.getCreateDate(), begin_date);
            if(judge == 1){
                month_n += r.getAccount();
            }
        }
        model.addAttribute("sum_n", new BigDecimal(sum_n).setScale(1, BigDecimal.ROUND_HALF_UP));
        model.addAttribute("month_n", new BigDecimal(month_n).setScale(1, BigDecimal.ROUND_HALF_UP));

        //分类金额
        //获得分类
//        CateExample cateE = new CateExample();
//        List<Cate> cate_list = cateMapper.selectByExample(cateE);

        double sum_eat = 0.00d;
        double sum_wear = 0.00d;
        double sum_live = 0.00d;
        double sum_do = 0.00d;
        double sum_move = 0.00d;
        double month_eat = 0.00d;
        double month_wear = 0.00d;
        double month_live = 0.00d;
        double month_do = 0.00d;
        double month_move = 0.00d;
        for (int i = 0; i < all_b.size(); i++){
            Bill b = all_b.get(i);
            if(1 == b.getCate()){
                sum_eat += b.getMyAccount();
            }else if(2 == b.getCate()){
                sum_wear += b.getMyAccount();
            }else if(3 == b.getCate()){
                sum_live += b.getMyAccount();
            }else if(4 == b.getCate()){
                sum_do += b.getMyAccount();
            }else if(5 == b.getCate()){
                sum_move += b.getMyAccount();
            }
            int judge = DateUtil.compare_date(b.getCreateDate(), begin_date);
            if(judge == 1){
                if(1 == b.getCate()){
                    month_eat += b.getMyAccount();
                }else if(2 == b.getCate()){
                    month_wear += b.getMyAccount();
                }else if(3 == b.getCate()){
                    month_live += b.getMyAccount();
                }else if(4 == b.getCate()){
                    month_do += b.getMyAccount();
                }else if(5 == b.getCate()){
                    month_move += b.getMyAccount();
                }
            }
        }
        model.addAttribute("sum_eat", new BigDecimal(sum_eat).setScale(1, BigDecimal.ROUND_HALF_UP));
        model.addAttribute("month_eat", new BigDecimal(month_eat).setScale(1, BigDecimal.ROUND_HALF_UP));
        model.addAttribute("sum_wear", new BigDecimal(sum_wear).setScale(1, BigDecimal.ROUND_HALF_UP));
        model.addAttribute("month_wear", new BigDecimal(month_wear).setScale(1, BigDecimal.ROUND_HALF_UP));
        model.addAttribute("sum_live", new BigDecimal(sum_live).setScale(1, BigDecimal.ROUND_HALF_UP));
        model.addAttribute("month_live", new BigDecimal(month_live).setScale(1, BigDecimal.ROUND_HALF_UP));
        model.addAttribute("sum_do", new BigDecimal(sum_do).setScale(1, BigDecimal.ROUND_HALF_UP));
        model.addAttribute("month_do", new BigDecimal(month_do).setScale(1, BigDecimal.ROUND_HALF_UP));
        model.addAttribute("sum_move", new BigDecimal(sum_move).setScale(1, BigDecimal.ROUND_HALF_UP));
        model.addAttribute("month_move", new BigDecimal(month_move).setScale(1, BigDecimal.ROUND_HALF_UP));
        return "info.jsp";
    }

    /**
     * 跳转到用户页面
     * @param openId
     * @param model
     * @return
     */
    @RequestMapping("person_page")
    public String person_page(String openId, String msg, Model model){
        model.addAttribute("person", personMapper.selectByPrimaryKey(openId));
        model.addAttribute("msg", msg);
        return "person_info.jsp";
    }

    /**
     * 判断用户昵称是否重复
     * @param person
     * @return
     */
    @RequestMapping("judge_name")
    @ResponseBody
    public Object judge_name(Person person){
        if(personBizMapper.repeatNickname(person) > 0){
            return "repeat";
        }else{
            return "success";
        }
    }

    @RequestMapping("update")
    public String updatePerson(Person person){
        int temp = personMapper.updateByPrimaryKeySelective(person);
        if(temp < 1){
            return "redirect:person_page.do?openId=" + person.getOpenId() + "&msg=error";
        } else {
            return "redirect:person_page.do?openId=" + person.getOpenId() + "&msg=success";
        }
    }
}
