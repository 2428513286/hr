package com.lon.entity;

public class SysRole {

	private int id;			//角色ID
	private String name;	//角色名称
	private String remark;		//	备注说明
	
	
	
	public SysRole() {
		super();
	}
	public SysRole(int id, String name, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.remark = remark;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "SysRole [id=" + id + ", name=" + name + ", remark=" + remark + "]";
	}
	
	
	
	
}
