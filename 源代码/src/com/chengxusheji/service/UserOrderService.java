package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.UserInfo;
import com.chengxusheji.po.OrderState;
import com.chengxusheji.po.UserOrder;

import com.chengxusheji.mapper.UserOrderMapper;
@Service
public class UserOrderService {

	@Resource UserOrderMapper userOrderMapper;
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

    /*添加预约献血记录*/
    public void addUserOrder(UserOrder userOrder) throws Exception {
    	userOrderMapper.addUserOrder(userOrder);
    }

    /*按照查询条件分页查询预约献血记录*/
    public ArrayList<UserOrder> queryUserOrder(UserInfo userObj,String mobilePhone,String cardType,String cardNumber,String orderDate,OrderState orderStateObj,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(null != userObj &&  userObj.getUser_name() != null  && !userObj.getUser_name().equals(""))  where += " and t_userOrder.userObj='" + userObj.getUser_name() + "'";
    	if(!mobilePhone.equals("")) where = where + " and t_userOrder.mobilePhone like '%" + mobilePhone + "%'";
    	if(!cardType.equals("")) where = where + " and t_userOrder.cardType like '%" + cardType + "%'";
    	if(!cardNumber.equals("")) where = where + " and t_userOrder.cardNumber like '%" + cardNumber + "%'";
    	if(!orderDate.equals("")) where = where + " and t_userOrder.orderDate like '%" + orderDate + "%'";
    	if(null != orderStateObj && orderStateObj.getStateId()!= null && orderStateObj.getStateId()!= 0)  where += " and t_userOrder.orderStateObj=" + orderStateObj.getStateId();
    	int startIndex = (currentPage-1) * this.rows;
    	return userOrderMapper.queryUserOrder(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<UserOrder> queryUserOrder(UserInfo userObj,String mobilePhone,String cardType,String cardNumber,String orderDate,OrderState orderStateObj) throws Exception  { 
     	String where = "where 1=1";
    	if(null != userObj &&  userObj.getUser_name() != null && !userObj.getUser_name().equals(""))  where += " and t_userOrder.userObj='" + userObj.getUser_name() + "'";
    	if(!mobilePhone.equals("")) where = where + " and t_userOrder.mobilePhone like '%" + mobilePhone + "%'";
    	if(!cardType.equals("")) where = where + " and t_userOrder.cardType like '%" + cardType + "%'";
    	if(!cardNumber.equals("")) where = where + " and t_userOrder.cardNumber like '%" + cardNumber + "%'";
    	if(!orderDate.equals("")) where = where + " and t_userOrder.orderDate like '%" + orderDate + "%'";
    	if(null != orderStateObj && orderStateObj.getStateId()!= null && orderStateObj.getStateId()!= 0)  where += " and t_userOrder.orderStateObj=" + orderStateObj.getStateId();
    	return userOrderMapper.queryUserOrderList(where);
    }

    /*查询所有预约献血记录*/
    public ArrayList<UserOrder> queryAllUserOrder()  throws Exception {
        return userOrderMapper.queryUserOrderList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber(UserInfo userObj,String mobilePhone,String cardType,String cardNumber,String orderDate,OrderState orderStateObj) throws Exception {
     	String where = "where 1=1";
    	if(null != userObj &&  userObj.getUser_name() != null && !userObj.getUser_name().equals(""))  where += " and t_userOrder.userObj='" + userObj.getUser_name() + "'";
    	if(!mobilePhone.equals("")) where = where + " and t_userOrder.mobilePhone like '%" + mobilePhone + "%'";
    	if(!cardType.equals("")) where = where + " and t_userOrder.cardType like '%" + cardType + "%'";
    	if(!cardNumber.equals("")) where = where + " and t_userOrder.cardNumber like '%" + cardNumber + "%'";
    	if(!orderDate.equals("")) where = where + " and t_userOrder.orderDate like '%" + orderDate + "%'";
    	if(null != orderStateObj && orderStateObj.getStateId()!= null && orderStateObj.getStateId()!= 0)  where += " and t_userOrder.orderStateObj=" + orderStateObj.getStateId();
        recordNumber = userOrderMapper.queryUserOrderCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取预约献血记录*/
    public UserOrder getUserOrder(int orderId) throws Exception  {
        UserOrder userOrder = userOrderMapper.getUserOrder(orderId);
        return userOrder;
    }

    /*更新预约献血记录*/
    public void updateUserOrder(UserOrder userOrder) throws Exception {
        userOrderMapper.updateUserOrder(userOrder);
    }

    /*删除一条预约献血记录*/
    public void deleteUserOrder (int orderId) throws Exception {
        userOrderMapper.deleteUserOrder(orderId);
    }

    /*删除多条预约献血信息*/
    public int deleteUserOrders (String orderIds) throws Exception {
    	String _orderIds[] = orderIds.split(",");
    	for(String _orderId: _orderIds) {
    		userOrderMapper.deleteUserOrder(Integer.parseInt(_orderId));
    	}
    	return _orderIds.length;
    }
}
