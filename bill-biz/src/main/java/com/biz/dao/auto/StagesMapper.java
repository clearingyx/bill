package com.biz.dao.auto;

import com.biz.model.auto.Stages;
import com.biz.model.auto.StagesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface StagesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stages
     *
     * @mbggenerated Sat Oct 08 13:24:09 CST 2016
     */
    int countByExample(StagesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stages
     *
     * @mbggenerated Sat Oct 08 13:24:09 CST 2016
     */
    int deleteByExample(StagesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stages
     *
     * @mbggenerated Sat Oct 08 13:24:09 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stages
     *
     * @mbggenerated Sat Oct 08 13:24:09 CST 2016
     */
    int insert(Stages record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stages
     *
     * @mbggenerated Sat Oct 08 13:24:09 CST 2016
     */
    int insertSelective(Stages record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stages
     *
     * @mbggenerated Sat Oct 08 13:24:09 CST 2016
     */
    List<Stages> selectByExampleWithRowbounds(StagesExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stages
     *
     * @mbggenerated Sat Oct 08 13:24:09 CST 2016
     */
    List<Stages> selectByExample(StagesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stages
     *
     * @mbggenerated Sat Oct 08 13:24:09 CST 2016
     */
    Stages selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stages
     *
     * @mbggenerated Sat Oct 08 13:24:09 CST 2016
     */
    int updateByExampleSelective(@Param("record") Stages record, @Param("example") StagesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stages
     *
     * @mbggenerated Sat Oct 08 13:24:09 CST 2016
     */
    int updateByExample(@Param("record") Stages record, @Param("example") StagesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stages
     *
     * @mbggenerated Sat Oct 08 13:24:09 CST 2016
     */
    int updateByPrimaryKeySelective(Stages record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stages
     *
     * @mbggenerated Sat Oct 08 13:24:09 CST 2016
     */
    int updateByPrimaryKey(Stages record);
}