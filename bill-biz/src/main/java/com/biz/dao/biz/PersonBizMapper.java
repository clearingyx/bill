package com.biz.dao.biz;

import com.biz.model.auto.Person;
import com.biz.model.auto.PersonExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface PersonBizMapper {

    Person searchHouseAdminByNickname(String nickname);

    int repeatNickname(String nickname);
}