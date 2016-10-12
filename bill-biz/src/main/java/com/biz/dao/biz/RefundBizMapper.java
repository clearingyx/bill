package com.biz.dao.biz;

import com.biz.model.auto.Refund;
import com.biz.model.biz.RefundReq;

import java.util.List;
import java.util.Map;

public interface RefundBizMapper {

    List<RefundReq> selectByCondition(Map map);

    RefundReq selectByRefundInfo(Integer id);

    List<RefundReq> selectRebackRefundList(RefundReq refundReq);

    Integer repeatRefundStateToCount(Integer id);

    List<Refund> sumNotRefundList(String openId);
}