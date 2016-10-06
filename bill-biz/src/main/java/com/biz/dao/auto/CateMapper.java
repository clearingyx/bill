package com.biz.dao.auto;

import com.biz.model.auto.Cate;
import com.biz.model.auto.CateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cate
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    int countByExample(CateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cate
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    int deleteByExample(CateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cate
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cate
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    int insert(Cate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cate
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    int insertSelective(Cate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cate
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    List<Cate> selectByExampleWithRowbounds(CateExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cate
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    List<Cate> selectByExample(CateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cate
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    Cate selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cate
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    int updateByExampleSelective(@Param("record") Cate record, @Param("example") CateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cate
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    int updateByExample(@Param("record") Cate record, @Param("example") CateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cate
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    int updateByPrimaryKeySelective(Cate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cate
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    int updateByPrimaryKey(Cate record);
}