package com.biz.service;

import com.biz.dao.auto.BillMapper;
import com.biz.dao.auto.RefundMapper;
import com.biz.dao.biz.BillBizMapper;
import com.biz.model.auto.Bill;
import com.biz.model.auto.Refund;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 2016/10/10.
 */
@Service
public class BillService {
    private static final Logger LOG = LoggerFactory.getLogger(BillService.class);
    @Autowired
    BillMapper billMapper;
    @Autowired
    BillBizMapper billBizMapper;
    @Autowired
    RefundMapper refundMapper;

    public void insertBill(Bill bill, String plus_person){
        //分担的用户集合
        String[] openIds = plus_person.split(",");
        //分账后的价格
        BigDecimal price = new BigDecimal(bill.getAccount()/openIds.length).setScale(1, BigDecimal.ROUND_HALF_UP);
        //分账，默认type是自己记账-0
        bill.setType(1);
        bill.setCount(openIds.length);
        bill.setCreateDate(new Date());
        //如果分担的用户集合有自己，则bill对象也存入
        if(plus_person.contains(bill.getOpenId())){
            bill.setMyAccount(price.doubleValue());
            if (openIds.length == 1){
                //说明是个人记账，状态变为1（type暂时没用了，不想写新页面了）
                bill.setUseStatus(1);
            }
        }
        int temp = billBizMapper.insertSelective(bill);
        if (temp < 1){
            LOG.error("生成总订单失败");
        } else {
            Refund refund = new Refund();
            refund.setCreateDate(new Date());
            refund.setAccount(price.doubleValue());
            //refund.setType(0); 默认一次性还，否则是1-分次还
            refund.setBillId(bill.getId());
           for (int i = 0; i < openIds.length; i++){
               if(!bill.getOpenId().equals(openIds[i])) {
                   refund.setOpenId(openIds[i]);
                   temp = refundMapper.insertSelective(refund);
                   temp++;
               }
           }
            if (temp != openIds.length){
                LOG.error("生成分订单失败，失败的总订单id：" + bill.getId() +
                        "，需要分" + openIds.length + "个，总成功"+temp+"个");
            }
        }
    }
}
