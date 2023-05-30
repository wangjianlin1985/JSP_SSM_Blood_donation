package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

public class BloodDonate {
    /*捐献id*/
    private Integer donateId;
    public Integer getDonateId(){
        return donateId;
    }
    public void setDonateId(Integer donateId){
        this.donateId = donateId;
    }

    /*献血人*/
    private UserInfo userObj;
    public UserInfo getUserObj() {
        return userObj;
    }
    public void setUserObj(UserInfo userObj) {
        this.userObj = userObj;
    }

    /*血型*/
    @NotEmpty(message="血型不能为空")
    private String bloodType;
    public String getBloodType() {
        return bloodType;
    }
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    /*献血量*/
    @NotNull(message="必须输入献血量")
    private Integer donateNumber;
    public Integer getDonateNumber() {
        return donateNumber;
    }
    public void setDonateNumber(Integer donateNumber) {
        this.donateNumber = donateNumber;
    }

    /*爱心血库*/
    @NotEmpty(message="爱心血库不能为空")
    private String loveFlag;
    public String getLoveFlag() {
        return loveFlag;
    }
    public void setLoveFlag(String loveFlag) {
        this.loveFlag = loveFlag;
    }

    /*献血时间*/
    @NotEmpty(message="献血时间不能为空")
    private String donateTime;
    public String getDonateTime() {
        return donateTime;
    }
    public void setDonateTime(String donateTime) {
        this.donateTime = donateTime;
    }

    /*采血地点*/
    @NotEmpty(message="采血地点不能为空")
    private String place;
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }

    /*采血医生*/
    @NotEmpty(message="采血医生不能为空")
    private String doctor;
    public String getDoctor() {
        return doctor;
    }
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    /*献血备注*/
    private String donateMemo;
    public String getDonateMemo() {
        return donateMemo;
    }
    public void setDonateMemo(String donateMemo) {
        this.donateMemo = donateMemo;
    }

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonBloodDonate=new JSONObject(); 
		jsonBloodDonate.accumulate("donateId", this.getDonateId());
		jsonBloodDonate.accumulate("userObj", this.getUserObj().getName());
		jsonBloodDonate.accumulate("userObjPri", this.getUserObj().getUser_name());
		jsonBloodDonate.accumulate("bloodType", this.getBloodType());
		jsonBloodDonate.accumulate("donateNumber", this.getDonateNumber());
		jsonBloodDonate.accumulate("loveFlag", this.getLoveFlag());
		jsonBloodDonate.accumulate("donateTime", this.getDonateTime().length()>19?this.getDonateTime().substring(0,19):this.getDonateTime());
		jsonBloodDonate.accumulate("place", this.getPlace());
		jsonBloodDonate.accumulate("doctor", this.getDoctor());
		jsonBloodDonate.accumulate("donateMemo", this.getDonateMemo());
		return jsonBloodDonate;
    }}