package com.lon.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lon.dao.ResumeDao;
import com.lon.entity.Resume;
import com.lon.util.DBUtils;
import com.lon.vo.ResumeVO;

public class ResumeDaoImpl implements ResumeDao{

	@Override
	public void add(Resume resume) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst=null;
		String sql = "insert into hr_resume(empid,orgname,job,edu,content,result,beginDate,endDate) values(?,?,?,?,?,?,?,?)";
		try {
			pst=con.prepareStatement(sql);
			pst.setInt(1, resume.getEmpid());
			pst.setString(2, resume.getOrgname());
			pst.setString(3, resume.getJob());
			pst.setString(4, resume.getEdu());
			pst.setString(5, resume.getContent());
			pst.setString(6, resume.getResult());
			pst.setDate(7, new Date(resume.getBeginDate().getTime()));
			pst.setDate(8, new Date(resume.getEndDate().getTime()));
			System.out.println("成功增加"+pst.executeUpdate()+"记录");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
		
	}

	@Override
	public void addMore(List<Resume> list) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst=null;
		String sql = "insert into hr_resume(empid,orgname,job,edu,content,result,beginDate,endDate) values(?,?,?,?,?,?,?,?)";
		try {
			pst=con.prepareStatement(sql);
			for(int i=0;i<list.size();i++) {
				Resume r = list.get(i);
				pst.setInt(1, r.getEmpid());
				pst.setString(2, r.getOrgname());
				pst.setString(3, r.getJob());
				pst.setString(4, r.getEdu());
				pst.setString(5, r.getContent());
				pst.setString(6, r.getResult());
				pst.setDate(7, new Date(r.getBeginDate().getTime()));
				pst.setDate(8, new Date(r.getEndDate().getTime()));
				
				pst.addBatch();
				if(i%300==0) {
					pst.executeBatch();
					pst.clearBatch();
				}
			}
			pst.executeBatch();
			pst.clearBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
		
	}

	@Override
	public void deleteById(int id) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		String sql = "delete from hr_resume where id=?";
		try {
			pst=con.prepareStatement(sql);
			pst.setInt(1, id);
			System.out.println("成功删除"+pst.executeUpdate()+"记录");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
		
	}

	@Override
	public void deleteMore(String ids) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		String sql = "delete from hr_resume where id in("+ids+")";
		try {
			pst=con.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public void update(Resume resume) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst=null;
		String sql = "update hr_resume set empid=?,orgname=?,job=?,edu=?,content=?,result=?,beginDate=?,endDate=? where id=?";
		try {
			pst=con.prepareStatement(sql);
			pst.setInt(1, resume.getEmpid());
			pst.setString(2, resume.getOrgname());
			pst.setString(3, resume.getJob());
			pst.setString(4, resume.getEdu());
			pst.setString(5, resume.getContent());
			pst.setString(6, resume.getResult());
			pst.setDate(7, new Date(resume.getBeginDate().getTime()));
			pst.setDate(8, new Date(resume.getEndDate().getTime()));
			pst.setInt(9, resume.getId());
			System.out.println("成功更新"+pst.executeUpdate()+"记录");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public Resume queryById(int id) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Resume r = null;
		String sql="select * from hr_resume where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				r = new Resume();
				r.setId(rs.getInt("id"));
				r.setEmpid(rs.getInt("empid"));
				r.setOrgname(rs.getString("orgname"));
				r.setJob(rs.getString("job"));
				r.setEdu(rs.getString("edu"));
				r.setContent(rs.getString("content"));
				r.setResult(rs.getString("result"));
				r.setBeginDate(rs.getDate("beginDate"));
				r.setEndDate(rs.getDate("endDate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return r;
	}

	@Override
	public List<Resume> queryAll() {
		List<Resume> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_resume where id=?";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if(rs.next()) {
				Resume r = new Resume();
				r = new Resume();
				r.setId(rs.getInt("id"));
				r.setEmpid(rs.getInt("empid"));
				r.setOrgname(rs.getString("orgname"));
				r.setJob(rs.getString("job"));
				r.setEdu(rs.getString("edu"));
				r.setContent(rs.getString("content"));
				r.setResult(rs.getString("result"));
				r.setBeginDate(rs.getDate("beginDate"));
				r.setEndDate(rs.getDate("endDate"));
				list.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public List<Resume> queryByPage(int currentPage, int pageSize) {
		List<Resume> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_resume order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Resume r = new Resume();
				r = new Resume();
				r.setId(rs.getInt("id"));
				r.setEmpid(rs.getInt("empid"));
				r.setOrgname(rs.getString("orgname"));
				r.setJob(rs.getString("job"));
				r.setEdu(rs.getString("edu"));
				r.setContent(rs.getString("content"));
				r.setResult(rs.getString("result"));
				r.setBeginDate(rs.getDate("beginDate"));
				r.setEndDate(rs.getDate("endDate"));
				list.add(r);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public List<Resume> queryByPage(int currentPage, int pageSize, String condition) {
		List<Resume> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_resume "+condition+" order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Resume r = new Resume();
				r = new Resume();
				r.setId(rs.getInt("id"));
				r.setEmpid(rs.getInt("empid"));
				r.setOrgname(rs.getString("orgname"));
				r.setJob(rs.getString("job"));
				r.setEdu(rs.getString("edu"));
				r.setContent(rs.getString("content"));
				r.setResult(rs.getString("result"));
				r.setBeginDate(rs.getDate("beginDate"));
				r.setEndDate(rs.getDate("endDate"));
				list.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public int getTotals() {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select count(*) from hr_resume");
			rs = pst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return 0;
	}

	@Override
	public int getTotals(String condition) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select count(*) from hr_resume "+condition);
			rs = pst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return 0;
	}

	@Override
	public List<ResumeVO> rqueryByPage(int currentPage, int pageSize) {
		List<ResumeVO> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_resume left JOIN hr_employee ON  hr_resume.empid=hr_employee.id left JOIN hr_department ON hr_resume.orgname=hr_department.name GROUP BY hr_resume.id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				ResumeVO r = new ResumeVO();
				r.setId(rs.getInt("hr_resume.id"));
				r.setEmpname(rs.getString("hr_employee.name"));
				r.setDename(rs.getString("hr_department.name"));
				r.setJob(rs.getString("hr_resume.job"));
				r.setEdu(rs.getString("hr_resume.edu"));
				r.setContent(rs.getString("hr_resume.content"));
				r.setResult(rs.getString("hr_resume.result"));
				r.setBeginDate(rs.getDate("hr_resume.beginDate"));
				r.setEndDate(rs.getDate("hr_resume.endDate"));
				list.add(r);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return list;
	}

	
}
