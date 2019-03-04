package com.lon.entity;

import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

public class Archive {

	private int id;		//档案ID
	private int empid;		//	员工ID
	private String code;	//档案编号
	private String name;	//档案名称
	private String content;		//内容
	private int type;		//	类型
	private String remark;	//备注说明
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp createTime;	//创建时间
	
	
	public Archive() {
		super();
	}
	public Archive(int id, int empid, String code, String name, String content, int type, String remark,
			Timestamp createTime) {
		super();
		this.id = id;
		this.empid = empid;
		this.code = code;
		this.name = name;
		this.content = content;
		this.type = type;
		this.remark = remark;
		this.createTime = createTime;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Archive [id=" + id + ", empid=" + empid + ", code=" + code + ", name=" + name + ", content=" + content
				+ ", type=" + type + ", remark=" + remark + ", createTime=" + createTime + "]";
	}
	
	
	
	
}
