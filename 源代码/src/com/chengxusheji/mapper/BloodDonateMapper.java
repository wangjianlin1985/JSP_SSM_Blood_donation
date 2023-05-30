package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.BloodDonate;

public interface BloodDonateMapper {
	/*添加血液捐献信息*/
	public void addBloodDonate(BloodDonate bloodDonate) throws Exception;

	/*按照查询条件分页查询血液捐献记录*/
	public ArrayList<BloodDonate> queryBloodDonate(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有血液捐献记录*/
	public ArrayList<BloodDonate> queryBloodDonateList(@Param("where") String where) throws Exception;

	/*按照查询条件的血液捐献记录数*/
	public int queryBloodDonateCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条血液捐献记录*/
	public BloodDonate getBloodDonate(int donateId) throws Exception;

	/*更新血液捐献记录*/
	public void updateBloodDonate(BloodDonate bloodDonate) throws Exception;

	/*删除血液捐献记录*/
	public void deleteBloodDonate(int donateId) throws Exception;

}
