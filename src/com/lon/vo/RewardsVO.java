package com.lon.vo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class RewardsVO {

	private int id; //奖惩ID
	private String empname; // 员工姓名
	private String title;  //奖惩标题
	private String content;  //奖惩内容
	private int type;  //奖惩类别
	@JSONField(format="yyyy-MM-dd")
	private Date createDate; //奖惩时间
	
	
	public RewardsVO() {
		super();
	}
	public RewardsVO(int id, String empname, String title, String content, int type, Date createDate) {
		super();
		this.id = id;
		this.empname = empname;
		this.title = title;
		this.content = content;
		this.type = type;
		this.createDate = createDate;
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
	@Override
	public String toString() {
		return "RewardsVO [id=" + id + ", empname=" + empname + ", title=" + title + ", content=" + content + ", type="
				+ type + ", createDate=" + createDate + "]";
	}
	
	
}
