package com.biz.model.auto;

import java.util.Date;

public class Bill {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bill.id
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bill.open_id
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    private String openId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bill.account
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    private Double account;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bill.use_status
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    private Integer useStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bill.type
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bill.cate
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    private Integer cate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bill.remark
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bill.create_date
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bill.update_date
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    private Date updateDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bill.id
     *
     * @return the value of bill.id
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bill.id
     *
     * @param id the value for bill.id
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bill.open_id
     *
     * @return the value of bill.open_id
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bill.open_id
     *
     * @param openId the value for bill.open_id
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bill.account
     *
     * @return the value of bill.account
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    public Double getAccount() {
        return account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bill.account
     *
     * @param account the value for bill.account
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    public void setAccount(Double account) {
        this.account = account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bill.use_status
     *
     * @return the value of bill.use_status
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    public Integer getUseStatus() {
        return useStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bill.use_status
     *
     * @param useStatus the value for bill.use_status
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bill.type
     *
     * @return the value of bill.type
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bill.type
     *
     * @param type the value for bill.type
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bill.cate
     *
     * @return the value of bill.cate
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    public Integer getCate() {
        return cate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bill.cate
     *
     * @param cate the value for bill.cate
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    public void setCate(Integer cate) {
        this.cate = cate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bill.remark
     *
     * @return the value of bill.remark
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bill.remark
     *
     * @param remark the value for bill.remark
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bill.create_date
     *
     * @return the value of bill.create_date
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bill.create_date
     *
     * @param createDate the value for bill.create_date
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bill.update_date
     *
     * @return the value of bill.update_date
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bill.update_date
     *
     * @param updateDate the value for bill.update_date
     *
     * @mbggenerated Wed Oct 05 21:48:07 CST 2016
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}