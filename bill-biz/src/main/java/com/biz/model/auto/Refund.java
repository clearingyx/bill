package com.biz.model.auto;

import java.util.Date;

public class Refund {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column refund.id
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column refund.open_id
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    private String openId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column refund.bill_id
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    private Integer billId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column refund.account
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    private Double account;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column refund.type
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column refund.refund_status
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    private Integer refundStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column refund.remark
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column refund.create_date
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column refund.update_date
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column refund.photo
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    private String photo;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column refund.id
     *
     * @return the value of refund.id
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column refund.id
     *
     * @param id the value for refund.id
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column refund.open_id
     *
     * @return the value of refund.open_id
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column refund.open_id
     *
     * @param openId the value for refund.open_id
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column refund.bill_id
     *
     * @return the value of refund.bill_id
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    public Integer getBillId() {
        return billId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column refund.bill_id
     *
     * @param billId the value for refund.bill_id
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column refund.account
     *
     * @return the value of refund.account
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    public Double getAccount() {
        return account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column refund.account
     *
     * @param account the value for refund.account
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    public void setAccount(Double account) {
        this.account = account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column refund.type
     *
     * @return the value of refund.type
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column refund.type
     *
     * @param type the value for refund.type
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column refund.refund_status
     *
     * @return the value of refund.refund_status
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    public Integer getRefundStatus() {
        return refundStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column refund.refund_status
     *
     * @param refundStatus the value for refund.refund_status
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column refund.remark
     *
     * @return the value of refund.remark
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column refund.remark
     *
     * @param remark the value for refund.remark
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column refund.create_date
     *
     * @return the value of refund.create_date
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column refund.create_date
     *
     * @param createDate the value for refund.create_date
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column refund.update_date
     *
     * @return the value of refund.update_date
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column refund.update_date
     *
     * @param updateDate the value for refund.update_date
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column refund.photo
     *
     * @return the value of refund.photo
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column refund.photo
     *
     * @param photo the value for refund.photo
     *
     * @mbggenerated Mon Oct 10 15:33:29 CST 2016
     */
    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }
}