package com.lon.entity;

import java.util.Date;

public class Edu {

	private int id;		//
	private String name;	//培训名称
	private Date biginDate;	//开始时间
	private Date endDate;		//结束时间
	private String adress;	//培训地点
	private int type;   //培训类别
	
	
	
	public Edu() {
		super();
	}
	public Edu(int id, String name, Date biginDate, Date endDate, String adress, int type) {
		super();
		this.id = id;
		this.name = name;
		this.biginDate = biginDate;
		this.endDate = endDate;
		this.adress = adress;
		this.type = type;
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
	public Date getBiginDate() {
		return biginDate;
	}
	public void setBiginDate(Date biginDate) {
		this.biginDate = biginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Edu [id=" + id + ", name=" + name + ", biginDate=" + biginDate + ", endDate=" + endDate + ", adress="
				+ adress + ", type=" + type + "]";
	}
	
	
	
}
