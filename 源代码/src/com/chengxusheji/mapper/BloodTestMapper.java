package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.BloodTest;

public interface BloodTestMapper {
	/*添加献血化验信息*/
	public void addBloodTest(BloodTest bloodTest) throws Exception;

	/*按照查询条件分页查询献血化验记录*/
	public ArrayList<BloodTest> queryBloodTest(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有献血化验记录*/
	public ArrayList<BloodTest> queryBloodTestList(@Param("where") String where) throws Exception;

	/*按照查询条件的献血化验记录数*/
	public int queryBloodTestCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条献血化验记录*/
	public BloodTest getBloodTest(int testId) throws Exception;

	/*更新献血化验记录*/
	public void updateBloodTest(BloodTest bloodTest) throws Exception;

	/*删除献血化验记录*/
	public void deleteBloodTest(int testId) throws Exception;

}
