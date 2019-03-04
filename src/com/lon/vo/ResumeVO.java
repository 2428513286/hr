package com.lon.vo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class ResumeVO {

	private int id; // 履历ID
	private String empname;// 员工姓名
	private String dename;//部门名称
	private String job;		// 职位
	private String edu;		// 学历
	private String content;		// 内容
	private String result;		//业绩
	@JSONField(format="yyyy-MM-dd")
	private Date beginDate;		// 开始时间
	@JSONField(format="yyyy-MM-dd")
	private Date endDate;		// 结束时间
	
	
	public ResumeVO() {
		super();
	}
	public ResumeVO(int id, String empname, String dename, String job, String edu, String content, String result,
			Date beginDate, Date endDate) {
		super();
		this.id = id;
		this.empname = empname;
		this.dename = dename;
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
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getDename() {
		return dename;
	}
	public void setDename(String dename) {
		this.dename = dename;
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
		return "ResumeVO [id=" + id + ", empname=" + empname + ", dename=" + dename + ", job=" + job + ", edu=" + edu
				+ ", content=" + content + ", result=" + result + ", beginDate=" + beginDate + ", endDate=" + endDate
				+ "]";
	}
	
	
}
