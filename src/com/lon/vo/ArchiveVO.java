package com.lon.vo;

import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

public class ArchiveVO{

	private int id;		//档案ID
	private String empname;//员工名称
	private String code;	//档案编号
	private String name;	//档案名称
	private String content;		//内容
	private String atname;//类型名称
	private String remark;	//备注说明
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp createTime;	//创建时间
	
	
	public ArchiveVO() {
		super();
	}
	public ArchiveVO(int id, String empname, String code, String name, String content, String atname, String remark,
			Timestamp createTime) {
		super();
		this.id = id;
		this.empname = empname;
		this.code = code;
		this.name = name;
		this.content = content;
		this.atname = atname;
		this.remark = remark;
		this.createTime = createTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
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
	public String getAtname() {
		return atname;
	}
	public void setAtname(String atname) {
		this.atname = atname;
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
		return "ArchiveVO [id=" + id + ", empname=" + empname + ", code=" + code + ", name=" + name + ", content="
				+ content + ", atname=" + atname + ", remark=" + remark + ", createTime=" + createTime + "]";
	}
	
	
}
