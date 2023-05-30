package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.UserOrder;

public interface UserOrderMapper {
	/*添加预约献血信息*/
	public void addUserOrder(UserOrder userOrder) throws Exception;

	/*按照查询条件分页查询预约献血记录*/
	public ArrayList<UserOrder> queryUserOrder(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有预约献血记录*/
	public ArrayList<UserOrder> queryUserOrderList(@Param("where") String where) throws Exception;

	/*按照查询条件的预约献血记录数*/
	public int queryUserOrderCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条预约献血记录*/
	public UserOrder getUserOrder(int orderId) throws Exception;

	/*更新预约献血记录*/
	public void updateUserOrder(UserOrder userOrder) throws Exception;

	/*删除预约献血记录*/
	public void deleteUserOrder(int orderId) throws Exception;

}
