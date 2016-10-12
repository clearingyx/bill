package com.biz.service;

import com.biz.dao.auto.BillMapper;
import com.biz.dao.auto.RefundMapper;
import com.biz.dao.biz.BillBizMapper;
import com.biz.dao.biz.RefundBizMapper;
import com.biz.model.auto.Bill;
import com.biz.model.auto.Refund;
import com.biz.model.biz.RefundReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/10.
 */
@Service
public class RefundService {
    private static final Logger LOG = LoggerFactory.getLogger(RefundService.class);
    @Autowired
    BillMapper billMapper;
    @Autowired
    RefundMapper refundMapper;
    @Autowired
    RefundBizMapper refundBizMapper;

    public int updateRefundAndBill(Refund refund){
        //更新还款状态，只要这个成功了就返回success
        int temp = refundMapper.updateByPrimaryKeySelective(refund);
        if(temp > 0){
            //判断订单是否还清，如果还清则修改订单状态;sql语句判断不是2的就是没有还款的
            int num = refundBizMapper.repeatRefundStateToCount(refund.getId());
            //等于0则说明状态都是2，订单已经还清
            if(num == 0){
                Bill bill = new Bill();
                bill.setId(refund.getBillId());
                bill.setUseStatus(1);
                int temp1 = billMapper.updateByPrimaryKeySelective(bill);
                if(temp1 < 1){
                    return temp1;
                } else {
                    LOG.error("还款全部完成更新订单表状态失败，订单表id：{}", refund.getBillId());
                    return temp;
                }
            } else {
                return temp;
            }
        } else {
            LOG.error("还款成功更新表状态失败，还款表id：{}", refund.getId());
            return temp;
        }
    }
}
