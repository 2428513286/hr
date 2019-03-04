package com.lon.vo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class ContractVO {

	private int id; // 合同ID
	private String empname;	//员工姓名
	private String code;	//合同编号
	@JSONField(format="yyyy-MM-dd")
	private Date beginDate; //开始时间
	@JSONField(format="yyyy-MM-dd")
	private Date endDate;	//结束时间
	private String job;		//职位
	private String content;		//内容
	private String attachment;	//合同的电子文档
	
	
	public ContractVO() {
		super();
	}


	public ContractVO(int id, String empname, String code, Date beginDate, Date endDate, String job, String content,
			String attachment) {
		super();
		this.id = id;
		this.empname = empname;
		this.code = code;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.job = job;
		this.content = content;
		this.attachment = attachment;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	@Override
	public String toString() {
		return "ContractVO [id=" + id + ", empname=" + empname + ", code=" + code + ", beginDate=" + beginDate
				+ ", endDate=" + endDate + ", job=" + job + ", content=" + content + ", attachment=" + attachment + "]";
	}
	
	
}
