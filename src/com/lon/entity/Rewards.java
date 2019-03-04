package com.lon.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Rewards {

	private int id; //奖惩ID
	private int empid; // 员工ID
	private String title;  //奖惩标题
	private String content;  //奖惩内容
	private int type;  //奖惩类别
	@JSONField(format="yyyy-MM-dd")
	private Date createDate; //奖惩时间
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Rewards(int id, int empid, String title, String content, int type, Date createDate) {
		super();
		this.id = id;
		this.empid = empid;
		this.title = title;
		this.content = content;
		this.type = type;
		this.createDate = createDate;
	}
	public Rewards() {
		super();
	}
	@Override
	public String toString() {
		return "Rewards [id=" + id + ", empid=" + empid + ", title=" + title + ", content=" + content + ", type=" + type
				+ ", createDate=" + createDate + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
