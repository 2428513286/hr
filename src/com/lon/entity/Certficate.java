package com.lon.entity;

import java.util.Date;

public class Certficate {

	private int id;		//证书ID
	private int empid; 	//学员ID
	private int eduid;	//培训ID
	private String name;	//证书名称
	private String code;	//证书编号
	private Date getDate;	//证书日期
	
	
	
	public Certficate() {
		super();
	}
	public Certficate(int id, int empid, int eduid, String name, String code, Date getDate) {
		super();
		this.id = id;
		this.empid = empid;
		this.eduid = eduid;
		this.name = name;
		this.code = code;
		this.getDate = getDate;
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
	public int getEduid() {
		return eduid;
	}
	public void setEduid(int eduid) {
		this.eduid = eduid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getGetDate() {
		return getDate;
	}
	public void setGetDate(Date getDate) {
		this.getDate = getDate;
	}
	@Override
	public String toString() {
		return "Certficate [id=" + id + ", empid=" + empid + ", eduid=" + eduid + ", name=" + name + ", code=" + code
				+ ", getDate=" + getDate + "]";
	}
	
	
	
}
