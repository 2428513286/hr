package com.lon.entity;

public class EduType {

	private int id;  //培训类别ID
	private String name;  //培训名称
	
	
	
	public EduType() {
		super();
	}
	public EduType(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	@Override
	public String toString() {
		return "EduType [id=" + id + ", name=" + name + "]";
	}
	
	
	
	
}
