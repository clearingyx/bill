package com;

import com.biz.dao.auto.PersonMapper;
import com.biz.model.auto.Person;
import com.biz.model.auto.PersonExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/10/5.
 */
@Controller
@RequestMapping("test")
public class TestController {
    private static final Logger LOG  = LoggerFactory.getLogger(TestController.class);

    @Autowired
    PersonMapper personMapper;

    @RequestMapping("test")
    @ResponseBody
    public Object getTest(){
        Person person = new Person();
        person.setOpenId("tom");
        int temp = personMapper.insertSelective(person);
        LOG.info("插入了{}条数据",temp);
        //查询
        PersonExample example = new PersonExample();
        PersonExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo("tom");
        List<Person> list = personMapper.selectByExample(example);
        return list;
    }
}
