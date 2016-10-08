package com.web.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2016/10/5.
 */
public class TestController {
    private static final Logger LOG  = LoggerFactory.getLogger(TestController.class);


//    public Object selectByPage(UserReq userReq)
//    {
//        UserExample example = new UserExample();
//        //各种条件，排序分页等，这里省略
//
//        // 分页参数
//        RowBounds rowBounds = PageUtil.initRowBounds(userReq);
//
//        // 读取数据条数
//        int rowCount = userMapper.countByExample(example);
//        int pageCount = PageUtil.calculatePageCount(rowCount, userReq.getPageSize());
//        //分页查询
//        List<User> list = userMapper.selectByExampleWithRowbounds(example,rowBounds);
//
//        // 返回结果，如果不需要resp，直接注释掉，返回list即可
//        UserResp resp = new UserResp();
//        resp.setPageCount(pageCount);
//        resp.setRowCount(rowCount);
//        resp.setUserList(list);
//        return resp;
//        //return list;
//    }
}
