package com.lon.entity;

import java.util.Date;

public class SysLog {

	private int id; //日志ID
	private int uid;  //用户编号
	private Date loginTime;  //登录时间
	private Date logoutTime; //登出时间
	
	public SysLog() {
		super();
	}
	public SysLog(int id, int uid, Date loginTime, Date logoutTime) {
		super();
		this.id = id;
		this.uid = uid;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public Date getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}
	@Override
	public String toString() {
		return "SysLog [id=" + id + ", uid=" + uid + ", loginTime=" + loginTime + ", logoutTime=" + logoutTime + "]";
	}
	
	
	
	
	
	
}
