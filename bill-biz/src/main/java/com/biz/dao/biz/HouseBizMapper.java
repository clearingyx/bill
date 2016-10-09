package com.biz.dao.biz;


import com.biz.model.auto.House;

public interface HouseBizMapper {

    int personInHouseRepeat(String openId);

    int addPersonInHouse(House house);
}