package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

public class BloodTest {
    /*化验id*/
    private Integer testId;
    public Integer getTestId(){
        return testId;
    }
    public void setTestId(Integer testId){
        this.testId = testId;
    }

    /*准献血人*/
    private UserInfo userObj;
    public UserInfo getUserObj() {
        return userObj;
    }
    public void setUserObj(UserInfo userObj) {
        this.userObj = userObj;
    }

    /*蛋白*/
    @NotNull(message="必须输入蛋白")
    private Float protein;
    public Float getProtein() {
        return protein;
    }
    public void setProtein(Float protein) {
        this.protein = protein;
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

    /*ALT*/
    @NotNull(message="必须输入ALT")
    private Float alt;
    public Float getAlt() {
        return alt;
    }
    public void setAlt(Float alt) {
        this.alt = alt;
    }

    /*HBsAg*/
    @NotEmpty(message="HBsAg不能为空")
    private String hbsag;
    public String getHbsag() {
        return hbsag;
    }
    public void setHbsag(String hbsag) {
        this.hbsag = hbsag;
    }

    /*抗-HCV*/
    @NotEmpty(message="抗-HCV不能为空")
    private String antiHCV;
    public String getAntiHCV() {
        return antiHCV;
    }
    public void setAntiHCV(String antiHCV) {
        this.antiHCV = antiHCV;
    }

    /*抗-HIV*/
    @NotEmpty(message="抗-HIV不能为空")
    private String antiHIV;
    public String getAntiHIV() {
        return antiHIV;
    }
    public void setAntiHIV(String antiHIV) {
        this.antiHIV = antiHIV;
    }

    /*梅毒*/
    @NotEmpty(message="梅毒不能为空")
    private String meidu;
    public String getMeidu() {
        return meidu;
    }
    public void setMeidu(String meidu) {
        this.meidu = meidu;
    }

    /*检测医生*/
    @NotEmpty(message="检测医生不能为空")
    private String doctor;
    public String getDoctor() {
        return doctor;
    }
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    /*检测时间*/
    @NotEmpty(message="检测时间不能为空")
    private String testTime;
    public String getTestTime() {
        return testTime;
    }
    public void setTestTime(String testTime) {
        this.testTime = testTime;
    }

    /*化验结果*/
    @NotEmpty(message="化验结果不能为空")
    private String testResult;
    public String getTestResult() {
        return testResult;
    }
    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonBloodTest=new JSONObject(); 
		jsonBloodTest.accumulate("testId", this.getTestId());
		jsonBloodTest.accumulate("userObj", this.getUserObj().getName());
		jsonBloodTest.accumulate("userObjPri", this.getUserObj().getUser_name());
		jsonBloodTest.accumulate("protein", this.getProtein());
		jsonBloodTest.accumulate("bloodType", this.getBloodType());
		jsonBloodTest.accumulate("alt", this.getAlt());
		jsonBloodTest.accumulate("hbsag", this.getHbsag());
		jsonBloodTest.accumulate("antiHCV", this.getAntiHCV());
		jsonBloodTest.accumulate("antiHIV", this.getAntiHIV());
		jsonBloodTest.accumulate("meidu", this.getMeidu());
		jsonBloodTest.accumulate("doctor", this.getDoctor());
		jsonBloodTest.accumulate("testTime", this.getTestTime().length()>19?this.getTestTime().substring(0,19):this.getTestTime());
		jsonBloodTest.accumulate("testResult", this.getTestResult());
		return jsonBloodTest;
    }}