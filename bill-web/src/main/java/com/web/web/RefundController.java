package com.web.web;

import com.biz.dao.auto.RefundMapper;
import com.biz.dao.biz.RefundBizMapper;
import com.biz.model.auto.Refund;
import com.biz.model.biz.RefundReq;
import com.biz.service.RefundService;
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
    @Autowired
    RefundService refundService;

    /**
     * 还款查询
     * @param refund
     * @param now_year
     * @param now_month
     * @param model
     * @param map
     * @return
     */
    @RequestMapping("search")
    public String search(Refund refund, Integer now_year, Integer now_month, Model model, Map<String, Object> map){
        //返回查询条件
        model.addAttribute("refund", refund);
        //openId是单独的，所以冗余一下
        model.addAttribute("openId", refund.getOpenId());

        //确定查询时间，默认是本月
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
        map.put("refund", refund);

        List<RefundReq> list = refundBizMapper.selectByCondition(map);

        //总款
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

    /**
     * 批量还款
     * @param bill_ids
     * @return
     */
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

    /**
     * 跳转到单个还款详情
     * @param refund
     * @param now_year
     * @param now_month
     * @param model
     * @return
     */
    @RequestMapping("jump_refund_one")
    public String jump_refund_one(Refund refund, Integer now_year, Integer now_month, Model model){
        RefundReq r = refundBizMapper.selectByRefundInfo(refund.getId());
        model.addAttribute("refund", r);
        model.addAttribute("now_year", now_year);
        model.addAttribute("now_month", now_month);
        model.addAttribute("old_refund", refund);
        return "refund_one.jsp";
    }

    /**
     * 单个还款，可能有图片和备注，比批量多一些参数
     * @param file
     * @param request
     * @param refund
     * @param model
     * @param response
     * @param now_year
     * @param now_month
     * @throws IOException
     */
    @RequestMapping("refund_one")
    public void refund_one(@RequestParam(value = "file", required = false) MultipartFile file,
                             HttpServletRequest request, Refund refund, Model model,
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
        //成功后返回的查询页面带这查询条件
        response.sendRedirect("search.do?msg=success&openId="+openId+
                "&type="+refund.getType()+"&refundStatus="+refund.getRefundStatus()+
                "&now_year="+now_year+"&now_month="+now_month);
    }

    /**
     * 还款
     * @param refundReq
     * @param msg
     * @param model
     * @return
     */
    @RequestMapping("confirmBill")
    public String confirmBill(RefundReq refundReq, String msg, Model model){
        if(null != msg){
            model.addAttribute("msg", msg);
        }
        refundReq.setRefundStatus(1);
        List list = refundBizMapper.selectRebackRefundList(refundReq);

        model.addAttribute("openId", refundReq.getOpenId());
        if(list.size() == 0){
            model.addAttribute("msg", "none");
        } else {
            model.addAttribute("list", list);
        }
        return "reback.jsp";
    }

    /**
     * 订单发起方确认还款
     * @param refund
     * @return
     */
    @RequestMapping("resure")
    @ResponseBody
    public Object resure(Refund refund){
        int temp = refundService.updateRefundAndBill(refund);
        if(temp < 1){
            return "error";
        } else {
            return "success";
        }
    }

    /**
     * 订单发起方关闭订单
     * @param id
     * @param openId
     * @return
     */
    @RequestMapping("appeal")
    public String appeal(Integer id, String openId){
        Refund refund = new Refund();
        refund.setId(id);
        refund.setRefundStatus(-1);
        int temp = refundService.updateRefundAndBill(refund);
        if(temp < 1){
            return "redirect:confirmBill.do?msg=error&openId=" + openId;
        } else {
            return "rediect:confirmBill.do?msg=success&openId=" + openId;
        }
    }

    /**
     * 看谁没还款
     * @param refundReq
     * @param model
     * @return
     */
    @RequestMapping("not_refund")
    public String confirmBill(RefundReq refundReq, Model model){
        refundReq.setRefundStatus(0);
        List list = refundBizMapper.selectRebackRefundList(refundReq);

        model.addAttribute("openId", refundReq.getOpenId());
        if(list.size() == 0){
            model.addAttribute("msg", "none");
        } else {
            model.addAttribute("list", list);
        }
        return "not_refund.jsp";
    }

}
