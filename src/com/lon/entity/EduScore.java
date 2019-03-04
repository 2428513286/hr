package com.lon.entity;

public class EduScore {

	private int id;
	private int empid;	//员工ID
	private int eduid;  //培训ID
	private int score;  //培训成绩
	
	
	
	
	public EduScore() {
		super();
	}
	public EduScore(int id, int empid, int eduid, int score) {
		super();
		this.id = id;
		this.empid = empid;
		this.eduid = eduid;
		this.score = score;
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
	public int getEduid() {
		return eduid;
	}
	public void setEduid(int eduid) {
		this.eduid = eduid;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "EduScore [id=" + id + ", empid=" + empid + ", eduid=" + eduid + ", score=" + score + "]";
	}
	
	
	
	
	
	
}
