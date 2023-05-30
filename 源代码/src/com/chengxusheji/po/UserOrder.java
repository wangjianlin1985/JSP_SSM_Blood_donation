package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

public class UserOrder {
    /*订单id*/
    private Integer orderId;
    public Integer getOrderId(){
        return orderId;
    }
    public void setOrderId(Integer orderId){
        this.orderId = orderId;
    }

    /*预约人*/
    private UserInfo userObj;
    public UserInfo getUserObj() {
        return userObj;
    }
    public void setUserObj(UserInfo userObj) {
        this.userObj = userObj;
    }

    /*手机号*/
    @NotEmpty(message="手机号不能为空")
    private String mobilePhone;
    public String getMobilePhone() {
        return mobilePhone;
    }
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /*证件类型*/
    @NotEmpty(message="证件类型不能为空")
    private String cardType;
    public String getCardType() {
        return cardType;
    }
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    /*证件号码*/
    @NotEmpty(message="证件号码不能为空")
    private String cardNumber;
    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /*预约日期*/
    @NotEmpty(message="预约日期不能为空")
    private String orderDate;
    public String getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    /*预约状态*/
    private OrderState orderStateObj;
    public OrderState getOrderStateObj() {
        return orderStateObj;
    }
    public void setOrderStateObj(OrderState orderStateObj) {
        this.orderStateObj = orderStateObj;
    }

    /*提交时间*/
    @NotEmpty(message="提交时间不能为空")
    private String orderTime;
    public String getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    /*订单备注*/
    private String orderMemo;
    public String getOrderMemo() {
        return orderMemo;
    }
    public void setOrderMemo(String orderMemo) {
        this.orderMemo = orderMemo;
    }

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonUserOrder=new JSONObject(); 
		jsonUserOrder.accumulate("orderId", this.getOrderId());
		jsonUserOrder.accumulate("userObj", this.getUserObj().getName());
		jsonUserOrder.accumulate("userObjPri", this.getUserObj().getUser_name());
		jsonUserOrder.accumulate("mobilePhone", this.getMobilePhone());
		jsonUserOrder.accumulate("cardType", this.getCardType());
		jsonUserOrder.accumulate("cardNumber", this.getCardNumber());
		jsonUserOrder.accumulate("orderDate", this.getOrderDate().length()>19?this.getOrderDate().substring(0,19):this.getOrderDate());
		jsonUserOrder.accumulate("orderStateObj", this.getOrderStateObj().getStateName());
		jsonUserOrder.accumulate("orderStateObjPri", this.getOrderStateObj().getStateId());
		jsonUserOrder.accumulate("orderTime", this.getOrderTime().length()>19?this.getOrderTime().substring(0,19):this.getOrderTime());
		jsonUserOrder.accumulate("orderMemo", this.getOrderMemo());
		return jsonUserOrder;
    }}