package com.lon.entity;

public class SysRoleMenu {

	private int id;//角色菜单ID
	private int rid;//角色编号
	private int mid;//菜单编号
	
	
	
	
	public SysRoleMenu() {
		super();
	}
	public SysRoleMenu(int id, int rid, int mid) {
		super();
		this.id = id;
		this.rid = rid;
		this.mid = mid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	@Override
	public String toString() {
		return "SysRoleMenu [id=" + id + ", rid=" + rid + ", mid=" + mid + "]";
	}
	
	
	
}
