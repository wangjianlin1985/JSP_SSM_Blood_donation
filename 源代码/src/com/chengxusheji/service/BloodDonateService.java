package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.UserInfo;
import com.chengxusheji.po.BloodDonate;

import com.chengxusheji.mapper.BloodDonateMapper;
@Service
public class BloodDonateService {

	@Resource BloodDonateMapper bloodDonateMapper;
    /*每页显示记录数目*/
    private int rows = 10;;
    public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}

    /*保存查询后总的页数*/
    private int totalPage;
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    /*保存查询到的总记录数*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*添加血液捐献记录*/
    public void addBloodDonate(BloodDonate bloodDonate) throws Exception {
    	bloodDonateMapper.addBloodDonate(bloodDonate);
    }

    /*按照查询条件分页查询血液捐献记录*/
    public ArrayList<BloodDonate> queryBloodDonate(UserInfo userObj,String bloodType,String loveFlag,String donateTime,String place,String doctor,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(null != userObj &&  userObj.getUser_name() != null  && !userObj.getUser_name().equals(""))  where += " and t_bloodDonate.userObj='" + userObj.getUser_name() + "'";
    	if(!bloodType.equals("")) where = where + " and t_bloodDonate.bloodType like '%" + bloodType + "%'";
    	if(!loveFlag.equals("")) where = where + " and t_bloodDonate.loveFlag like '%" + loveFlag + "%'";
    	if(!donateTime.equals("")) where = where + " and t_bloodDonate.donateTime like '%" + donateTime + "%'";
    	if(!place.equals("")) where = where + " and t_bloodDonate.place like '%" + place + "%'";
    	if(!doctor.equals("")) where = where + " and t_bloodDonate.doctor like '%" + doctor + "%'";
    	int startIndex = (currentPage-1) * this.rows;
    	return bloodDonateMapper.queryBloodDonate(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<BloodDonate> queryBloodDonate(UserInfo userObj,String bloodType,String loveFlag,String donateTime,String place,String doctor) throws Exception  { 
     	String where = "where 1=1";
    	if(null != userObj &&  userObj.getUser_name() != null && !userObj.getUser_name().equals(""))  where += " and t_bloodDonate.userObj='" + userObj.getUser_name() + "'";
    	if(!bloodType.equals("")) where = where + " and t_bloodDonate.bloodType like '%" + bloodType + "%'";
    	if(!loveFlag.equals("")) where = where + " and t_bloodDonate.loveFlag like '%" + loveFlag + "%'";
    	if(!donateTime.equals("")) where = where + " and t_bloodDonate.donateTime like '%" + donateTime + "%'";
    	if(!place.equals("")) where = where + " and t_bloodDonate.place like '%" + place + "%'";
    	if(!doctor.equals("")) where = where + " and t_bloodDonate.doctor like '%" + doctor + "%'";
    	return bloodDonateMapper.queryBloodDonateList(where);
    }

    /*查询所有血液捐献记录*/
    public ArrayList<BloodDonate> queryAllBloodDonate()  throws Exception {
        return bloodDonateMapper.queryBloodDonateList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber(UserInfo userObj,String bloodType,String loveFlag,String donateTime,String place,String doctor) throws Exception {
     	String where = "where 1=1";
    	if(null != userObj &&  userObj.getUser_name() != null && !userObj.getUser_name().equals(""))  where += " and t_bloodDonate.userObj='" + userObj.getUser_name() + "'";
    	if(!bloodType.equals("")) where = where + " and t_bloodDonate.bloodType like '%" + bloodType + "%'";
    	if(!loveFlag.equals("")) where = where + " and t_bloodDonate.loveFlag like '%" + loveFlag + "%'";
    	if(!donateTime.equals("")) where = where + " and t_bloodDonate.donateTime like '%" + donateTime + "%'";
    	if(!place.equals("")) where = where + " and t_bloodDonate.place like '%" + place + "%'";
    	if(!doctor.equals("")) where = where + " and t_bloodDonate.doctor like '%" + doctor + "%'";
        recordNumber = bloodDonateMapper.queryBloodDonateCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取血液捐献记录*/
    public BloodDonate getBloodDonate(int donateId) throws Exception  {
        BloodDonate bloodDonate = bloodDonateMapper.getBloodDonate(donateId);
        return bloodDonate;
    }

    /*更新血液捐献记录*/
    public void updateBloodDonate(BloodDonate bloodDonate) throws Exception {
        bloodDonateMapper.updateBloodDonate(bloodDonate);
    }

    /*删除一条血液捐献记录*/
    public void deleteBloodDonate (int donateId) throws Exception {
        bloodDonateMapper.deleteBloodDonate(donateId);
    }

    /*删除多条血液捐献信息*/
    public int deleteBloodDonates (String donateIds) throws Exception {
    	String _donateIds[] = donateIds.split(",");
    	for(String _donateId: _donateIds) {
    		bloodDonateMapper.deleteBloodDonate(Integer.parseInt(_donateId));
    	}
    	return _donateIds.length;
    }
}
