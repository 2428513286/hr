package com.lon.entity;

import java.util.Date;

public class Engage {

	private int id;    //聘任ID
	private int empid;   //员工ID
	private String skillname;   //聘任职称
	private Date createDate;   //创建时间
	
	
	
	
	
	public Engage() {
		super();
	}
	public Engage(int id, int empid, String skillname, Date createDate) {
		super();
		this.id = id;
		this.empid = empid;
		this.skillname = skillname;
		this.createDate = createDate;
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
	public String getSkillname() {
		return skillname;
	}
	public void setSkillname(String skillname) {
		this.skillname = skillname;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Engage [id=" + id + ", empid=" + empid + ", skillname=" + skillname + ", createDate=" + createDate
				+ "]";
	}
	
	
	
}
