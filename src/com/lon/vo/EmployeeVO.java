package com.lon.vo;

import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

public class EmployeeVO {
	private int id;   //员工id
	private String name; //员工姓名
	private int sex;   //性别
	private int age;	//年龄
	private int edu;	//	学历
	private int degree;		//学位
	private String job;		//职位
	private String deptname;		//部门名称
	private int state;	//	状态
	private String phone;	//	手机号
	private String address;		//	地址
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp createTime;		// 入职时间
	
	
	public EmployeeVO() {
		super();
	}
	public EmployeeVO(int id, String name, int sex, int age, int edu, int degree, String job, String deptname,
			int state, String phone, String address, Timestamp createTime) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.edu = edu;
		this.degree = degree;
		this.job = job;
		this.deptname = deptname;
		this.state = state;
		this.phone = phone;
		this.address = address;
		this.createTime = createTime;
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
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getEdu() {
		return edu;
	}
	public void setEdu(int edu) {
		this.edu = edu;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "EmployeeVO [id=" + id + ", name=" + name + ", sex=" + sex + ", age=" + age + ", edu=" + edu
				+ ", degree=" + degree + ", job=" + job + ", deptname=" + deptname + ", state=" + state + ", phone="
				+ phone + ", address=" + address + ", createTime=" + createTime + "]";
	}
	
	
}
