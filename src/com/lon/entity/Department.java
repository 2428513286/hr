package com.lon.entity;

public class Department {

	private int id; //主键编号
	private String name;  //部门名称
	private String manager; //部门负责人
	private String offceNo;		//部门办公室编号
	private String phone;		//部门电话
	private String remark;		//部门说明
	
	
	
	
	
	
	public Department() {
		super();
	}
	public Department(int id, String name, String manager, String offceNo, String phone, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.manager = manager;
		this.offceNo = offceNo;
		this.phone = phone;
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
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getOffceNo() {
		return offceNo;
	}
	public void setOffceNo(String offceNo) {
		this.offceNo = offceNo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", manager=" + manager + ", offceNo=" + offceNo + ", phone="
				+ phone + ", remark=" + remark + "]";
	}
	
	
	
	
}
