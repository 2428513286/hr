package com.lon.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Resume {

	private int id; // 履历ID
	private int empid;// 员工编号
	private String orgname;//部门
	private String job;		// 职位
	private String edu;		// 学历
	private String content;		// 内容
	private String result;		//业绩
	@JSONField(format="yyyy-MM-dd")
	private Date beginDate;		// 开始时间
	@JSONField(format="yyyy-MM-dd")
	private Date endDate;		// 结束时间
	
	
	
	
	
	public Resume() {
		super();
	}
	public Resume(int id, int empid, String orgname, String job, String edu, String content, String result,
			Date beginDate, Date endDate) {
		super();
		this.id = id;
		this.empid = empid;
		this.orgname = orgname;
		this.job = job;
		this.edu = edu;
		this.content = content;
		this.result = result;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getEdu() {
		return edu;
	}
	public void setEdu(String edu) {
		this.edu = edu;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "Resume [id=" + id + ", empid=" + empid + ", orgname=" + orgname + ", job=" + job + ", edu=" + edu
				+ ", content=" + content + ", result=" + result + ", beginDate=" + beginDate + ", endDate=" + endDate
				+ "]";
	}
	
	
	
	
}
