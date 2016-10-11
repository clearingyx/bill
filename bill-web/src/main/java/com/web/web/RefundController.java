package com.web.web;

import com.biz.dao.auto.RefundMapper;
import com.biz.dao.biz.RefundBizMapper;
import com.biz.model.auto.Refund;
import com.biz.model.biz.RefundReq;
import com.biz.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2016/10/7.
 */
@Controller
@RequestMapping("refund")
public class RefundController {
    private static final Logger LOG = LoggerFactory.getLogger(RefundController.class);

    @Autowired
    RefundMapper refundMapper;
    @Autowired
    RefundBizMapper refundBizMapper;

    @RequestMapping("search")
    public String search(Refund refund, Integer now_year, Integer now_month, Model model, Map<String, Object> map){
        model.addAttribute("refund", refund);
        model.addAttribute("openId", refund.getOpenId());

        map.put("refund", refund);
        String begindate = "";
        String enddate = "";
        if(null == now_year || null == now_month){
            begindate = DateUtil.thisMonthBegin();
            enddate = DateUtil.thisMonthEnd();
            String[] times = begindate.split("-");
            now_year = Integer.valueOf(times[0]);
            now_month = Integer.valueOf(times[1]);
        } else {
            begindate = now_year + "-" + now_month + "-" + "-01";
            enddate = now_year + "-" + (now_month + 1) + "-" + "-01";
        }
        map.put("begindate", begindate);
        map.put("enddate", enddate);

        List<RefundReq> list = refundBizMapper.selectByCondition(map);
        double price = 0.0d;
        for (int i = 0;i < list.size(); i++){
            price += list.get(i).getAccount();
        }
        BigDecimal account = new BigDecimal(price).setScale(1, BigDecimal.ROUND_HALF_UP);
        model.addAttribute("account", account);
        model.addAttribute("list", list);
        model.addAttribute("now_year", now_year);
        model.addAttribute("now_month", now_month);
        model.addAttribute("monthList", DateUtil.getMonthList());
        model.addAttribute("yearList", DateUtil.getYearList());

        return "refund.jsp";
    }

    @RequestMapping("batch_refund.do")
    @ResponseBody
    public Object batchRefund(String bill_ids){
        String[] bills = bill_ids.split(",");
        int temp = 0;
        Refund refund = new Refund();
        for (int i = 0; i < bills.length; i++){
            refund.setId(Integer.valueOf(bills[i]));
            refund.setRefundStatus(1);
            temp += refundMapper.updateByPrimaryKeySelective(refund);
        }
        if(bills.length != temp){
            return "error";
        } else {
            return "success";
        }
    }

    @RequestMapping("jump_refund_one")
    public String jump_refund_one(Refund refund, Integer now_year, Integer now_month, Model model){
        RefundReq r = refundBizMapper.selectByRefundInfo(refund.getId());
        model.addAttribute("refund", r);
        model.addAttribute("now_year", now_year);
        model.addAttribute("now_month", now_month);
        model.addAttribute("old_refund", refund);
        return "refund_one.jsp";
    }

    @RequestMapping("refund_one")
    public void refund_one(@RequestParam(value = "file", required = false) MultipartFile file,
                             HttpServletRequest request, Refund refund, String plus_person, Model model,
                             HttpServletResponse response, String now_year, String now_month) throws IOException {
        Refund r = new Refund();

        String openId = refund.getOpenId();
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
                r.setPhoto(request.getContextPath() + "/upload/" + newName);
            } catch (Exception e) {
                LOG.error("创建订单图片上传失败");
                e.printStackTrace();
            }
        }

        if(null != refund.getRemark()) {
            r.setRemark(refund.getRemark());
        }
        r.setId(refund.getId());
        r.setRefundStatus(1);
        int temp = refundMapper.updateByPrimaryKeySelective(r);
        if (temp < 1) {
            LOG.error("单个订单还款失败");
        }
        response.sendRedirect("search.do?msg=success&openId="+openId+
                "&type="+refund.getType()+"&refundStatus="+refund.getRefundStatus()+
                "&now_year="+now_year+"&now_month="+now_month);
    }
}
