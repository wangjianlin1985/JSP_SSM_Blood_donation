package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.UserInfo;
import com.chengxusheji.po.BloodTest;

import com.chengxusheji.mapper.BloodTestMapper;
@Service
public class BloodTestService {

	@Resource BloodTestMapper bloodTestMapper;
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

    /*添加献血化验记录*/
    public void addBloodTest(BloodTest bloodTest) throws Exception {
    	bloodTestMapper.addBloodTest(bloodTest);
    }

    /*按照查询条件分页查询献血化验记录*/
    public ArrayList<BloodTest> queryBloodTest(UserInfo userObj,String doctor,String testTime,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(null != userObj &&  userObj.getUser_name() != null  && !userObj.getUser_name().equals(""))  where += " and t_bloodTest.userObj='" + userObj.getUser_name() + "'";
    	if(!doctor.equals("")) where = where + " and t_bloodTest.doctor like '%" + doctor + "%'";
    	if(!testTime.equals("")) where = where + " and t_bloodTest.testTime like '%" + testTime + "%'";
    	int startIndex = (currentPage-1) * this.rows;
    	return bloodTestMapper.queryBloodTest(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<BloodTest> queryBloodTest(UserInfo userObj,String doctor,String testTime) throws Exception  { 
     	String where = "where 1=1";
    	if(null != userObj &&  userObj.getUser_name() != null && !userObj.getUser_name().equals(""))  where += " and t_bloodTest.userObj='" + userObj.getUser_name() + "'";
    	if(!doctor.equals("")) where = where + " and t_bloodTest.doctor like '%" + doctor + "%'";
    	if(!testTime.equals("")) where = where + " and t_bloodTest.testTime like '%" + testTime + "%'";
    	return bloodTestMapper.queryBloodTestList(where);
    }

    /*查询所有献血化验记录*/
    public ArrayList<BloodTest> queryAllBloodTest()  throws Exception {
        return bloodTestMapper.queryBloodTestList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber(UserInfo userObj,String doctor,String testTime) throws Exception {
     	String where = "where 1=1";
    	if(null != userObj &&  userObj.getUser_name() != null && !userObj.getUser_name().equals(""))  where += " and t_bloodTest.userObj='" + userObj.getUser_name() + "'";
    	if(!doctor.equals("")) where = where + " and t_bloodTest.doctor like '%" + doctor + "%'";
    	if(!testTime.equals("")) where = where + " and t_bloodTest.testTime like '%" + testTime + "%'";
        recordNumber = bloodTestMapper.queryBloodTestCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取献血化验记录*/
    public BloodTest getBloodTest(int testId) throws Exception  {
        BloodTest bloodTest = bloodTestMapper.getBloodTest(testId);
        return bloodTest;
    }

    /*更新献血化验记录*/
    public void updateBloodTest(BloodTest bloodTest) throws Exception {
        bloodTestMapper.updateBloodTest(bloodTest);
    }

    /*删除一条献血化验记录*/
    public void deleteBloodTest (int testId) throws Exception {
        bloodTestMapper.deleteBloodTest(testId);
    }

    /*删除多条献血化验信息*/
    public int deleteBloodTests (String testIds) throws Exception {
    	String _testIds[] = testIds.split(",");
    	for(String _testId: _testIds) {
    		bloodTestMapper.deleteBloodTest(Integer.parseInt(_testId));
    	}
    	return _testIds.length;
    }
}
