package com.biz.dao.biz;

import com.biz.model.biz.RefundReq;

import java.util.List;
import java.util.Map;

public interface RefundBizMapper {

    List<RefundReq> selectByCondition(Map map);

    RefundReq selectByRefundInfo(Integer id);
}