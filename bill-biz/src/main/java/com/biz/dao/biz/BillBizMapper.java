package com.biz.dao.biz;

import com.biz.model.auto.Bill;

public interface BillBizMapper {
    int insertSelective(Bill record);
}