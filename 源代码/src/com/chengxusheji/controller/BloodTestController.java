package com.chengxusheji.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.chengxusheji.utils.ExportExcelUtil;
import com.chengxusheji.utils.UserException;
import com.chengxusheji.service.BloodTestService;
import com.chengxusheji.po.BloodTest;
import com.chengxusheji.service.UserInfoService;
import com.chengxusheji.po.UserInfo;

//BloodTest管理控制层
@Controller
@RequestMapping("/BloodTest")
public class BloodTestController extends BaseController {

    /*业务层对象*/
    @Resource BloodTestService bloodTestService;

    @Resource UserInfoService userInfoService;
	@InitBinder("userObj")
	public void initBinderuserObj(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("userObj.");
	}
	@InitBinder("bloodTest")
	public void initBinderBloodTest(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("bloodTest.");
	}
	/*跳转到添加BloodTest视图*/
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request) throws Exception {
		model.addAttribute(new BloodTest());
		/*查询所有的UserInfo信息*/
		List<UserInfo> userInfoList = userInfoService.queryAllUserInfo();
		request.setAttribute("userInfoList", userInfoList);
		return "BloodTest_add";
	}

	/*客户端ajax方式提交添加献血化验信息*/
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@Validated BloodTest bloodTest, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
		boolean success = false;
		if (br.hasErrors()) {
			message = "输入信息不符合要求！";
			writeJsonResponse(response, success, message);
			return ;
		}
        bloodTestService.addBloodTest(bloodTest);
        message = "献血化验添加成功!";
        success = true;
        writeJsonResponse(response, success, message);
	}
	/*ajax方式按照查询条件分页查询献血化验信息*/
	@RequestMapping(value = { "/list" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute("userObj") UserInfo userObj,String doctor,String testTime,Integer page,Integer rows, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (page==null || page == 0) page = 1;
		if (doctor == null) doctor = "";
		if (testTime == null) testTime = "";
		if(rows != 0)bloodTestService.setRows(rows);
		List<BloodTest> bloodTestList = bloodTestService.queryBloodTest(userObj, doctor, testTime, page);
	    /*计算总的页数和总的记录数*/
	    bloodTestService.queryTotalPageAndRecordNumber(userObj, doctor, testTime);
	    /*获取到总的页码数目*/
	    int totalPage = bloodTestService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = bloodTestService.getRecordNumber();
        response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象
		JSONObject jsonObj=new JSONObject();
		jsonObj.accumulate("total", recordNumber);
		JSONArray jsonArray = new JSONArray();
		for(BloodTest bloodTest:bloodTestList) {
			JSONObject jsonBloodTest = bloodTest.getJsonObject();
			jsonArray.put(jsonBloodTest);
		}
		jsonObj.accumulate("rows", jsonArray);
		out.println(jsonObj.toString());
		out.flush();
		out.close();
	}

	/*ajax方式按照查询条件分页查询献血化验信息*/
	@RequestMapping(value = { "/listAll" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void listAll(HttpServletResponse response) throws Exception {
		List<BloodTest> bloodTestList = bloodTestService.queryAllBloodTest();
        response.setContentType("text/json;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = new JSONArray();
		for(BloodTest bloodTest:bloodTestList) {
			JSONObject jsonBloodTest = new JSONObject();
			jsonBloodTest.accumulate("testId", bloodTest.getTestId());
			jsonArray.put(jsonBloodTest);
		}
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}

	/*前台按照查询条件分页查询献血化验信息*/
	@RequestMapping(value = { "/frontlist" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String frontlist(@ModelAttribute("userObj") UserInfo userObj,String doctor,String testTime,Integer currentPage, Model model, HttpServletRequest request) throws Exception  {
		if (currentPage==null || currentPage == 0) currentPage = 1;
		if (doctor == null) doctor = "";
		if (testTime == null) testTime = "";
		List<BloodTest> bloodTestList = bloodTestService.queryBloodTest(userObj, doctor, testTime, currentPage);
	    /*计算总的页数和总的记录数*/
	    bloodTestService.queryTotalPageAndRecordNumber(userObj, doctor, testTime);
	    /*获取到总的页码数目*/
	    int totalPage = bloodTestService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = bloodTestService.getRecordNumber();
	    request.setAttribute("bloodTestList",  bloodTestList);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("recordNumber", recordNumber);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("userObj", userObj);
	    request.setAttribute("doctor", doctor);
	    request.setAttribute("testTime", testTime);
	    List<UserInfo> userInfoList = userInfoService.queryAllUserInfo();
	    request.setAttribute("userInfoList", userInfoList);
		return "BloodTest/bloodTest_frontquery_result"; 
	}
	
	/*前台按照查询条件分页查询献血化验信息*/
	@RequestMapping(value = { "/frontUserlist" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String frontUserlist(@ModelAttribute("userObj") UserInfo userObj,String doctor,String testTime,Integer currentPage, Model model, HttpServletRequest request,HttpSession session) throws Exception  {
		if (currentPage==null || currentPage == 0) currentPage = 1;
		if (doctor == null) doctor = "";
		if (testTime == null) testTime = "";
		
		userObj = new UserInfo();
		userObj.setUser_name((String)session.getAttribute("user_name"));
		
		List<BloodTest> bloodTestList = bloodTestService.queryBloodTest(userObj, doctor, testTime, currentPage);
	    /*计算总的页数和总的记录数*/
	    bloodTestService.queryTotalPageAndRecordNumber(userObj, doctor, testTime);
	    /*获取到总的页码数目*/
	    int totalPage = bloodTestService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = bloodTestService.getRecordNumber();
	    request.setAttribute("bloodTestList",  bloodTestList);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("recordNumber", recordNumber);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("userObj", userObj);
	    request.setAttribute("doctor", doctor);
	    request.setAttribute("testTime", testTime);
	    List<UserInfo> userInfoList = userInfoService.queryAllUserInfo();
	    request.setAttribute("userInfoList", userInfoList);
		return "BloodTest/bloodTest_frontUserquery_result"; 
	}
	

     /*前台查询BloodTest信息*/
	@RequestMapping(value="/{testId}/frontshow",method=RequestMethod.GET)
	public String frontshow(@PathVariable Integer testId,Model model,HttpServletRequest request) throws Exception {
		/*根据主键testId获取BloodTest对象*/
        BloodTest bloodTest = bloodTestService.getBloodTest(testId);

        List<UserInfo> userInfoList = userInfoService.queryAllUserInfo();
        request.setAttribute("userInfoList", userInfoList);
        request.setAttribute("bloodTest",  bloodTest);
        return "BloodTest/bloodTest_frontshow";
	}

	/*ajax方式显示献血化验修改jsp视图页*/
	@RequestMapping(value="/{testId}/update",method=RequestMethod.GET)
	public void update(@PathVariable Integer testId,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
        /*根据主键testId获取BloodTest对象*/
        BloodTest bloodTest = bloodTestService.getBloodTest(testId);

        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONObject jsonBloodTest = bloodTest.getJsonObject();
		out.println(jsonBloodTest.toString());
		out.flush();
		out.close();
	}

	/*ajax方式更新献血化验信息*/
	@RequestMapping(value = "/{testId}/update", method = RequestMethod.POST)
	public void update(@Validated BloodTest bloodTest, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
    	boolean success = false;
		if (br.hasErrors()) { 
			message = "输入的信息有错误！";
			writeJsonResponse(response, success, message);
			return;
		}
		try {
			bloodTestService.updateBloodTest(bloodTest);
			message = "献血化验更新成功!";
			success = true;
			writeJsonResponse(response, success, message);
		} catch (Exception e) {
			e.printStackTrace();
			message = "献血化验更新失败!";
			writeJsonResponse(response, success, message); 
		}
	}
    /*删除献血化验信息*/
	@RequestMapping(value="/{testId}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable Integer testId,HttpServletRequest request) throws UnsupportedEncodingException {
		  try {
			  bloodTestService.deleteBloodTest(testId);
	            request.setAttribute("message", "献血化验删除成功!");
	            return "message";
	        } catch (Exception e) { 
	            e.printStackTrace();
	            request.setAttribute("error", "献血化验删除失败!");
				return "error";

	        }

	}

	/*ajax方式删除多条献血化验记录*/
	@RequestMapping(value="/deletes",method=RequestMethod.POST)
	public void delete(String testIds,HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
		String message = "";
    	boolean success = false;
        try { 
        	int count = bloodTestService.deleteBloodTests(testIds);
        	success = true;
        	message = count + "条记录删除成功";
        	writeJsonResponse(response, success, message);
        } catch (Exception e) { 
            //e.printStackTrace();
            message = "有记录存在外键约束,删除失败";
            writeJsonResponse(response, success, message);
        }
	}

	/*按照查询条件导出献血化验信息到Excel*/
	@RequestMapping(value = { "/OutToExcel" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void OutToExcel(@ModelAttribute("userObj") UserInfo userObj,String doctor,String testTime, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
        if(doctor == null) doctor = "";
        if(testTime == null) testTime = "";
        List<BloodTest> bloodTestList = bloodTestService.queryBloodTest(userObj,doctor,testTime);
        ExportExcelUtil ex = new ExportExcelUtil();
        String _title = "BloodTest信息记录"; 
        String[] headers = { "化验id","准献血人","蛋白","血型","ALT","HBsAg","抗-HCV","抗-HIV","梅毒","检测医生","检测时间","化验结果"};
        List<String[]> dataset = new ArrayList<String[]>(); 
        for(int i=0;i<bloodTestList.size();i++) {
        	BloodTest bloodTest = bloodTestList.get(i); 
        	dataset.add(new String[]{bloodTest.getTestId() + "",bloodTest.getUserObj().getName(),bloodTest.getProtein() + "",bloodTest.getBloodType(),bloodTest.getAlt() + "",bloodTest.getHbsag(),bloodTest.getAntiHCV(),bloodTest.getAntiHIV(),bloodTest.getMeidu(),bloodTest.getDoctor(),bloodTest.getTestTime(),bloodTest.getTestResult()});
        }
        /*
        OutputStream out = null;
		try {
			out = new FileOutputStream("C://output.xls");
			ex.exportExcel(title,headers, dataset, out);
		    out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		OutputStream out = null;//创建一个输出流对象 
		try { 
			out = response.getOutputStream();//
			response.setHeader("Content-disposition","attachment; filename="+"BloodTest.xls");//filename是下载的xls的名，建议最好用英文 
			response.setContentType("application/msexcel;charset=UTF-8");//设置类型 
			response.setHeader("Pragma","No-cache");//设置头 
			response.setHeader("Cache-Control","no-cache");//设置头 
			response.setDateHeader("Expires", 0);//设置日期头  
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			ex.exportExcel(rootPath,_title,headers, dataset, out);
			out.flush();
		} catch (IOException e) { 
			e.printStackTrace(); 
		}finally{
			try{
				if(out!=null){ 
					out.close(); 
				}
			}catch(IOException e){ 
				e.printStackTrace(); 
			} 
		}
    }
}
