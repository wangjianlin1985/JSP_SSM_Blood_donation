<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%> 
<%@ page import="com.chengxusheji.po.BloodTest" %>
<%@ page import="com.chengxusheji.po.UserInfo" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    List<BloodTest> bloodTestList = (List<BloodTest>)request.getAttribute("bloodTestList");
    //获取所有的userObj信息
    List<UserInfo> userInfoList = (List<UserInfo>)request.getAttribute("userInfoList");
    int currentPage =  (Integer)request.getAttribute("currentPage"); //当前页
    int totalPage =   (Integer)request.getAttribute("totalPage");  //一共多少页
    int recordNumber =   (Integer)request.getAttribute("recordNumber");  //一共多少记录
    UserInfo userObj = (UserInfo)request.getAttribute("userObj");
    String doctor = (String)request.getAttribute("doctor"); //检测医生查询关键字
    String testTime = (String)request.getAttribute("testTime"); //检测时间查询关键字
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
<title>献血化验查询</title>
<link href="<%=basePath %>plugins/bootstrap.css" rel="stylesheet">
<link href="<%=basePath %>plugins/bootstrap-dashen.css" rel="stylesheet">
<link href="<%=basePath %>plugins/font-awesome.css" rel="stylesheet">
<link href="<%=basePath %>plugins/animate.css" rel="stylesheet">
<link href="<%=basePath %>plugins/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
</head>
<body style="margin-top:70px;">
<div class="container">
<jsp:include page="../header.jsp"></jsp:include>
	<div class="row"> 
		<div class="col-md-9 wow fadeInDown" data-wow-duration="0.5s">
			<div>
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
			    	<li><a href="<%=basePath %>index.jsp">首页</a></li>
			    	<li role="presentation" class="active"><a href="#bloodTestListPanel" aria-controls="bloodTestListPanel" role="tab" data-toggle="tab">献血化验列表</a></li>
			    	<li role="presentation" ><a href="<%=basePath %>BloodTest/bloodTest_frontAdd.jsp" style="display:none;">添加献血化验</a></li>
				</ul>
			  	<!-- Tab panes -->
			  	<div class="tab-content">
				    <div role="tabpanel" class="tab-pane active" id="bloodTestListPanel">
				    		<div class="row">
				    			<div class="col-md-12 top5">
				    				<div class="table-responsive">
				    				<table class="table table-condensed table-hover">
				    					<tr class="success bold"><td>序号</td><td>化验id</td><td>准献血人</td><td>蛋白</td><td>血型</td><td>ALT</td><td>HBsAg</td><td>抗-HCV</td><td>抗-HIV</td><td>梅毒</td><td>检测医生</td><td>检测时间</td><td>化验结果</td><td>操作</td></tr>
				    					<% 
				    						/*计算起始序号*/
				    	            		int startIndex = (currentPage -1) * 5;
				    	            		/*遍历记录*/
				    	            		for(int i=0;i<bloodTestList.size();i++) {
					    	            		int currentIndex = startIndex + i + 1; //当前记录的序号
					    	            		BloodTest bloodTest = bloodTestList.get(i); //获取到献血化验对象
 										%>
 										<tr>
 											<td><%=currentIndex %></td>
 											<td><%=bloodTest.getTestId() %></td>
 											<td><%=bloodTest.getUserObj().getName() %></td>
 											<td><%=bloodTest.getProtein() %></td>
 											<td><%=bloodTest.getBloodType() %></td>
 											<td><%=bloodTest.getAlt() %></td>
 											<td><%=bloodTest.getHbsag() %></td>
 											<td><%=bloodTest.getAntiHCV() %></td>
 											<td><%=bloodTest.getAntiHIV() %></td>
 											<td><%=bloodTest.getMeidu() %></td>
 											<td><%=bloodTest.getDoctor() %></td>
 											<td><%=bloodTest.getTestTime() %></td>
 											<td><%=bloodTest.getTestResult() %></td>
 											<td>
 												<a href="<%=basePath  %>BloodTest/<%=bloodTest.getTestId() %>/frontshow"><i class="fa fa-info"></i>&nbsp;查看</a>&nbsp;
 												<a href="#" onclick="bloodTestEdit('<%=bloodTest.getTestId() %>');" style="display:none;"><i class="fa fa-pencil fa-fw"></i>编辑</a>&nbsp;
 												<a href="#" onclick="bloodTestDelete('<%=bloodTest.getTestId() %>');" style="display:none;"><i class="fa fa-trash-o fa-fw"></i>删除</a>
 											</td> 
 										</tr>
 										<%}%>
				    				</table>
				    				</div>
				    			</div>
				    		</div>

				    		<div class="row">
					            <div class="col-md-12">
						            <nav class="pull-left">
						                <ul class="pagination">
						                    <li><a href="#" onclick="GoToPage(<%=currentPage-1 %>,<%=totalPage %>);" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
						                     <%
						                    	int startPage = currentPage - 5;
						                    	int endPage = currentPage + 5;
						                    	if(startPage < 1) startPage=1;
						                    	if(endPage > totalPage) endPage = totalPage;
						                    	for(int i=startPage;i<=endPage;i++) {
						                    %>
						                    <li class="<%= currentPage==i?"active":"" %>"><a href="#"  onclick="GoToPage(<%=i %>,<%=totalPage %>);"><%=i %></a></li>
						                    <%  } %> 
						                    <li><a href="#" onclick="GoToPage(<%=currentPage+1 %>,<%=totalPage %>);"><span aria-hidden="true">&raquo;</span></a></li>
						                </ul>
						            </nav>
						            <div class="pull-right" style="line-height:75px;" >共有<%=recordNumber %>条记录，当前第 <%=currentPage %>/<%=totalPage %> 页</div>
					            </div>
				            </div> 
				    </div>
				</div>
			</div>
		</div>
	<div class="col-md-3 wow fadeInRight">
		<div class="page-header">
    		<h1>献血化验查询</h1>
		</div>
		<form name="bloodTestQueryForm" id="bloodTestQueryForm" action="<%=basePath %>BloodTest/frontlist" class="mar_t15" method="post">
            <div class="form-group">
            	<label for="userObj_user_name">准献血人：</label>
                <select id="userObj_user_name" name="userObj.user_name" class="form-control">
                	<option value="">不限制</option>
	 				<%
	 				for(UserInfo userInfoTemp:userInfoList) {
	 					String selected = "";
 					if(userObj!=null && userObj.getUser_name()!=null && userObj.getUser_name().equals(userInfoTemp.getUser_name()))
 						selected = "selected";
	 				%>
 				 <option value="<%=userInfoTemp.getUser_name() %>" <%=selected %>><%=userInfoTemp.getName() %></option>
	 				<%
	 				}
	 				%>
 			</select>
            </div>
			<div class="form-group">
				<label for="doctor">检测医生:</label>
				<input type="text" id="doctor" name="doctor" value="<%=doctor %>" class="form-control" placeholder="请输入检测医生">
			</div>






			<div class="form-group">
				<label for="testTime">检测时间:</label>
				<input type="text" id="testTime" name="testTime" class="form-control"  placeholder="请选择检测时间" value="<%=testTime %>" onclick="SelectDate(this,'yyyy-MM-dd')" />
			</div>
            <input type=hidden name=currentPage value="<%=currentPage %>" />
            <button type="submit" class="btn btn-primary">查询</button>
        </form>
	</div>

		</div>
	</div> 
<div id="bloodTestEditDialog" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><i class="fa fa-edit"></i>&nbsp;献血化验信息编辑</h4>
      </div>
      <div class="modal-body" style="height:450px; overflow: scroll;">
      	<form class="form-horizontal" name="bloodTestEditForm" id="bloodTestEditForm" enctype="multipart/form-data" method="post"  class="mar_t15">
		  <div class="form-group">
			 <label for="bloodTest_testId_edit" class="col-md-3 text-right">化验id:</label>
			 <div class="col-md-9"> 
			 	<input type="text" id="bloodTest_testId_edit" name="bloodTest.testId" class="form-control" placeholder="请输入化验id" readOnly>
			 </div>
		  </div> 
		  <div class="form-group">
		  	 <label for="bloodTest_userObj_user_name_edit" class="col-md-3 text-right">准献血人:</label>
		  	 <div class="col-md-9">
			    <select id="bloodTest_userObj_user_name_edit" name="bloodTest.userObj.user_name" class="form-control">
			    </select>
		  	 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="bloodTest_protein_edit" class="col-md-3 text-right">蛋白:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="bloodTest_protein_edit" name="bloodTest.protein" class="form-control" placeholder="请输入蛋白">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="bloodTest_bloodType_edit" class="col-md-3 text-right">血型:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="bloodTest_bloodType_edit" name="bloodTest.bloodType" class="form-control" placeholder="请输入血型">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="bloodTest_alt_edit" class="col-md-3 text-right">ALT:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="bloodTest_alt_edit" name="bloodTest.alt" class="form-control" placeholder="请输入ALT">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="bloodTest_hbsag_edit" class="col-md-3 text-right">HBsAg:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="bloodTest_hbsag_edit" name="bloodTest.hbsag" class="form-control" placeholder="请输入HBsAg">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="bloodTest_antiHCV_edit" class="col-md-3 text-right">抗-HCV:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="bloodTest_antiHCV_edit" name="bloodTest.antiHCV" class="form-control" placeholder="请输入抗-HCV">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="bloodTest_antiHIV_edit" class="col-md-3 text-right">抗-HIV:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="bloodTest_antiHIV_edit" name="bloodTest.antiHIV" class="form-control" placeholder="请输入抗-HIV">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="bloodTest_meidu_edit" class="col-md-3 text-right">梅毒:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="bloodTest_meidu_edit" name="bloodTest.meidu" class="form-control" placeholder="请输入梅毒">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="bloodTest_doctor_edit" class="col-md-3 text-right">检测医生:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="bloodTest_doctor_edit" name="bloodTest.doctor" class="form-control" placeholder="请输入检测医生">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="bloodTest_testTime_edit" class="col-md-3 text-right">检测时间:</label>
		  	 <div class="col-md-9">
                <div class="input-group date bloodTest_testTime_edit col-md-12" data-link-field="bloodTest_testTime_edit">
                    <input class="form-control" id="bloodTest_testTime_edit" name="bloodTest.testTime" size="16" type="text" value="" placeholder="请选择检测时间" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
		  	 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="bloodTest_testResult_edit" class="col-md-3 text-right">化验结果:</label>
		  	 <div class="col-md-9">
			    <textarea id="bloodTest_testResult_edit" name="bloodTest.testResult" rows="8" class="form-control" placeholder="请输入化验结果"></textarea>
			 </div>
		  </div>
		</form> 
	    <style>#bloodTestEditForm .form-group {margin-bottom:5px;}  </style>
      </div>
      <div class="modal-footer"> 
      	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      	<button type="button" class="btn btn-primary" onclick="ajaxBloodTestModify();">提交</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<jsp:include page="../footer.jsp"></jsp:include> 
<script src="<%=basePath %>plugins/jquery.min.js"></script>
<script src="<%=basePath %>plugins/bootstrap.js"></script>
<script src="<%=basePath %>plugins/wow.min.js"></script>
<script src="<%=basePath %>plugins/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>plugins/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jsdate.js"></script>
<script>
var basePath = "<%=basePath%>";
/*跳转到查询结果的某页*/
function GoToPage(currentPage,totalPage) {
    if(currentPage==0) return;
    if(currentPage>totalPage) return;
    document.bloodTestQueryForm.currentPage.value = currentPage;
    document.bloodTestQueryForm.submit();
}

/*可以直接跳转到某页*/
function changepage(totalPage)
{
    var pageValue=document.bloodTestQueryForm.pageValue.value;
    if(pageValue>totalPage) {
        alert('你输入的页码超出了总页数!');
        return ;
    }
    document.bloodTestQueryForm.currentPage.value = pageValue;
    documentbloodTestQueryForm.submit();
}

/*弹出修改献血化验界面并初始化数据*/
function bloodTestEdit(testId) {
	$.ajax({
		url :  basePath + "BloodTest/" + testId + "/update",
		type : "get",
		dataType: "json",
		success : function (bloodTest, response, status) {
			if (bloodTest) {
				$("#bloodTest_testId_edit").val(bloodTest.testId);
				$.ajax({
					url: basePath + "UserInfo/listAll",
					type: "get",
					success: function(userInfos,response,status) { 
						$("#bloodTest_userObj_user_name_edit").empty();
						var html="";
		        		$(userInfos).each(function(i,userInfo){
		        			html += "<option value='" + userInfo.user_name + "'>" + userInfo.name + "</option>";
		        		});
		        		$("#bloodTest_userObj_user_name_edit").html(html);
		        		$("#bloodTest_userObj_user_name_edit").val(bloodTest.userObjPri);
					}
				});
				$("#bloodTest_protein_edit").val(bloodTest.protein);
				$("#bloodTest_bloodType_edit").val(bloodTest.bloodType);
				$("#bloodTest_alt_edit").val(bloodTest.alt);
				$("#bloodTest_hbsag_edit").val(bloodTest.hbsag);
				$("#bloodTest_antiHCV_edit").val(bloodTest.antiHCV);
				$("#bloodTest_antiHIV_edit").val(bloodTest.antiHIV);
				$("#bloodTest_meidu_edit").val(bloodTest.meidu);
				$("#bloodTest_doctor_edit").val(bloodTest.doctor);
				$("#bloodTest_testTime_edit").val(bloodTest.testTime);
				$("#bloodTest_testResult_edit").val(bloodTest.testResult);
				$('#bloodTestEditDialog').modal('show');
			} else {
				alert("获取信息失败！");
			}
		}
	});
}

/*删除献血化验信息*/
function bloodTestDelete(testId) {
	if(confirm("确认删除这个记录")) {
		$.ajax({
			type : "POST",
			url : basePath + "BloodTest/deletes",
			data : {
				testIds : testId,
			},
			success : function (obj) {
				if (obj.success) {
					alert("删除成功");
					$("#bloodTestQueryForm").submit();
					//location.href= basePath + "BloodTest/frontlist";
				}
				else 
					alert(obj.message);
			},
		});
	}
}

/*ajax方式提交献血化验信息表单给服务器端修改*/
function ajaxBloodTestModify() {
	$.ajax({
		url :  basePath + "BloodTest/" + $("#bloodTest_testId_edit").val() + "/update",
		type : "post",
		dataType: "json",
		data: new FormData($("#bloodTestEditForm")[0]),
		success : function (obj, response, status) {
            if(obj.success){
                alert("信息修改成功！");
                $("#bloodTestQueryForm").submit();
            }else{
                alert(obj.message);
            } 
		},
		processData: false,
		contentType: false,
	});
}

$(function(){
	/*小屏幕导航点击关闭菜单*/
    $('.navbar-collapse a').click(function(){
        $('.navbar-collapse').collapse('hide');
    });
    new WOW().init();

    /*检测时间组件*/
    $('.bloodTest_testTime_edit').datetimepicker({
    	language:  'zh-CN',  //语言
    	format: 'yyyy-mm-dd hh:ii:ss',
    	weekStart: 1,
    	todayBtn:  1,
    	autoclose: 1,
    	minuteStep: 1,
    	todayHighlight: 1,
    	startView: 2,
    	forceParse: 0
    });
})
</script>
</body>
</html>

