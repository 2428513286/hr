package com.lon.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lon.dao.ArchiveDao;
import com.lon.dao.ArchiveTypeDao;
import com.lon.dao.impl.ArchiveDaoImpl;
import com.lon.dao.impl.ArchiveTypeDaoImpl;
import com.lon.entity.Archive;
import com.lon.entity.ArchiveType;

@Controller
public class ArchiveTypeController {

	@RequestMapping("/aradd")
	public void add(HttpServletResponse response,ArchiveType s) throws IOException{
		response.setContentType("text/html");
		ArchiveTypeDao dao = new ArchiveTypeDaoImpl();
		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();
		try {
			dao.add(s);
			jo.put("state", 0);
			jo.put("msg", "成功新增记录");
		}catch(Exception e) {
			jo.put("state", -1);
			jo.put("msg", "新增记录失败：");
		}finally {
			String str = JSON.toJSONString(jo);
			out.write(str);
			out.flush();
			out.close();
		}
		
	}
	@RequestMapping("/ardeleteById")
	public void deleteById(HttpServletResponse response,int id) throws IOException {
		ArchiveTypeDao  dao =new ArchiveTypeDaoImpl();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();
		try {
			dao.deleteById(id);
			jo.put("state", 0);
			jo.put("msg", "成功删除记录");
		}catch(Exception e) {
			jo.put("state", -1);
			jo.put("msg", "删除记录失败："+e.getMessage());
		}finally {
			String str = JSON.toJSONString(jo);
			out.write(str);
			out.flush();
			out.close();
		}
		
	}
	@RequestMapping("/ardeleteMore")
	public void deleteMore(HttpServletResponse response,String ids) throws IOException {
		ArchiveTypeDao dao = new ArchiveTypeDaoImpl();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();
		try {
			dao.deleteMore(ids);
			jo.put("state", 0);
			jo.put("msg", "成功删除记录");
		}catch(Exception e) {
			jo.put("state", -1);
			jo.put("msg", "删除记录失败："+e.getMessage());
		}finally {
			String str = JSON.toJSONString(jo);
			out.write(str);
			out.flush();
			out.close();
		}
		
	}
	@RequestMapping("/arupdate")
	public void update(HttpServletResponse response,ArchiveType s) throws IOException {
		ArchiveTypeDao  dao = new ArchiveTypeDaoImpl();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();
		try {
			dao.update(s);
			jo.put("state", 0);
			jo.put("msg", "成功更新记录");
		}catch(Exception e) {
			jo.put("state", -1);
			jo.put("msg", "更新记录失败："+e.getMessage());
		}finally {
			String str = JSON.toJSONString(jo);
			out.write(str);
			out.flush();
			out.close();
		}
		
		
	}
	@RequestMapping("/arqueryById")
	public ArchiveType queryById(HttpServletRequest request,int id,String currentPage,Map<String, Object> map) {
		String qname = request.getParameter("qname");
		String qusername = request.getParameter("qusername");
		
		ArchiveTypeDao  dao = new ArchiveTypeDaoImpl();
		ArchiveType s = dao.queryById(id);
		map.put("archiveType", s);
		map.put("currentPage", currentPage);
		map.put("qname", qname);
		map.put("qusername", qusername);
		return s;
	}
	
	@RequestMapping("/arqueryAll")
	@ResponseBody
	public List<ArchiveType> queryAll(){
		ArchiveTypeDao  dao = new ArchiveTypeDaoImpl();
		List<ArchiveType> s = dao.queryAll();
		return s;
	}
	
	@RequestMapping("/arqueryByPage")
	@ResponseBody
	public void queryByPage(HttpServletRequest request,HttpServletResponse response, String currentPage){
		String qname = request.getParameter("qname");
		String qusername = request.getParameter("qusername");
		String qsex = request.getParameter("qsex");
		//获取当前页面
		currentPage = request.getParameter("page");
		//获取每页显示记录数
		String rows = request.getParameter("rows");
	
		String condition = " where 1=1 ";
		if(qname!=null&&!qname.equals("")&&!qname.equalsIgnoreCase("null")){
			condition += " and name like '%"+qname+"%'";
		}
		if(qusername!=null&&!qusername.equals("")&&!qname.equalsIgnoreCase("null")){
			condition += " and username like '%"+qusername+"%'";
		}
		if(qsex!=null&&!qsex.equals("")&&!qsex.equals("-1")&&!qname.equalsIgnoreCase("null")){
			condition += " and sex = "+qsex;
		}
		ArchiveTypeDao dao = new ArchiveTypeDaoImpl();
		//当前页
		int sp = 1;
		//总记录数
		int totals = dao.getTotals(condition);
		//每页记录数
		int pageSize = Integer.parseInt(rows);
		//总页数
		int pageCounts = totals/pageSize;
		if(totals%pageSize!=0){
			pageCounts++;
		}
		try{
			sp = Integer.parseInt(currentPage);
		}catch(Exception e){
			sp = 1;
		}
		if(sp>pageCounts){
			sp = pageCounts;
		}
		if(sp<1){
			sp = 1;
		}
		List<ArchiveType> list = dao.queryByPage(sp, pageSize,condition);
		
		response.setContentType("text/html");
		PrintWriter out;
		try {
			out = response.getWriter();
			JSONObject jo = new JSONObject();
			jo.put("total", totals);
			jo.put("rows", list);
			String json = JSON.toJSONString(jo);
			out.write(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 处理参数为日期格式
	 * @param binder
	 */
	 @InitBinder
	    public void initBinder(ServletRequestDataBinder binder){
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),
	                true));
	    }
}
