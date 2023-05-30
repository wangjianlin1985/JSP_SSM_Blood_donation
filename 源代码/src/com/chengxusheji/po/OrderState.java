package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

public class OrderState {
    /*状态id*/
    private Integer stateId;
    public Integer getStateId(){
        return stateId;
    }
    public void setStateId(Integer stateId){
        this.stateId = stateId;
    }

    /*状态名称*/
    @NotEmpty(message="状态名称不能为空")
    private String stateName;
    public String getStateName() {
        return stateName;
    }
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonOrderState=new JSONObject(); 
		jsonOrderState.accumulate("stateId", this.getStateId());
		jsonOrderState.accumulate("stateName", this.getStateName());
		return jsonOrderState;
    }}