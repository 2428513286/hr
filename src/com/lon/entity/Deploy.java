package com.lon.entity;

import java.util.Date;

public class Deploy {

	private int id;  //
	private int empid;   //员工ID
	private String olddept;   // 旧部门
	private String newdept; // 新部门
	private String oldjob;  //旧职位
	private String newjob;   //新职位
	private Date mixDate;   //离职时间
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
	public String getOlddept() {
		return olddept;
	}
	public void setOlddept(String olddept) {
		this.olddept = olddept;
	}
	public String getNewdept() {
		return newdept;
	}
	public void setNewdept(String newdept) {
		this.newdept = newdept;
	}
	public String getOldjob() {
		return oldjob;
	}
	public void setOldjob(String oldjob) {
		this.oldjob = oldjob;
	}
	public String getNewjob() {
		return newjob;
	}
	public void setNewjob(String newjob) {
		this.newjob = newjob;
	}
	public Date getMixDate() {
		return mixDate;
	}
	public void setMixDate(Date mixDate) {
		this.mixDate = mixDate;
	}
	public Deploy() {
		super();
	}
	
	public Deploy(int id, int empid, String olddept, String newdept, String oldjob, String newjob, Date mixDate) {
		super();
		this.id = id;
		this.empid = empid;
		this.olddept = olddept;
		this.newdept = newdept;
		this.oldjob = oldjob;
		this.newjob = newjob;
		this.mixDate = mixDate;
	}
	@Override
	public String toString() {
		return "Deploy [id=" + id + ", empid=" + empid + ", olddept=" + olddept + ", newdept=" + newdept + ", oldjob="
				+ oldjob + ", newjob=" + newjob + ", mixDate=" + mixDate + "]";
	}
	
	
	
	
	
	
	
}
