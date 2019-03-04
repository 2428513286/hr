package com.lon.entity;

import java.sql.Timestamp;

public class SysUser {

	private int id;  //ID
	private String username; //用户名
	private String password; //密码
	private int empid;//员工ID
	private int state; //状态
	private Timestamp createTime;//创建时间
	
	
	public SysUser() {
		super();
	}
	public SysUser(int id, String username, String password, int empid, int state, Timestamp createTime) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.empid = empid;
		this.state = state;
		this.createTime = createTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "SysUser [id=" + id + ", username=" + username + ", password=" + password + ", empid=" + empid
				+ ", state=" + state + ", createTime=" + createTime + "]";
	}
	
	
	
	
}
