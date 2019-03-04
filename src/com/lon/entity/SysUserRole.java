package com.lon.entity;

public class SysUserRole {

	private int id; //ID
	private int uid;	//用户编号
	private int rid;	//角色编号
	
	
	
	public SysUserRole() {
		super();
	}
	public SysUserRole(int id, int uid, int rid) {
		super();
		this.id = id;
		this.uid = uid;
		this.rid = rid;
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
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	@Override
	public String toString() {
		return "SysUserRole [id=" + id + ", uid=" + uid + ", rid=" + rid + "]";
	}
	
	
}
