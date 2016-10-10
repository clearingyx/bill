package com.biz.dao.biz;


import com.biz.model.auto.House;

public interface HouseBizMapper {

    int personInHouseRepeat(String openId);

    int addPersonInHouse(House house);

    House houseAllPerson(String openId);

    int delHouseByAdminOpenId(String openId);

    int delHouseByPersonOpenId(String openId);
}