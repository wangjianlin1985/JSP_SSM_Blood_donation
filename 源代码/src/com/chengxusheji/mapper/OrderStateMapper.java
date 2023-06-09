package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.OrderState;

public interface OrderStateMapper {
	/*添加预约状态信息*/
	public void addOrderState(OrderState orderState) throws Exception;

	/*按照查询条件分页查询预约状态记录*/
	public ArrayList<OrderState> queryOrderState(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有预约状态记录*/
	public ArrayList<OrderState> queryOrderStateList(@Param("where") String where) throws Exception;

	/*按照查询条件的预约状态记录数*/
	public int queryOrderStateCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条预约状态记录*/
	public OrderState getOrderState(int stateId) throws Exception;

	/*更新预约状态记录*/
	public void updateOrderState(OrderState orderState) throws Exception;

	/*删除预约状态记录*/
	public void deleteOrderState(int stateId) throws Exception;

}
