package com.lon.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lon.dao.EngageDao;
import com.lon.entity.Engage;
import com.lon.util.DBUtils;

public class EngageDaoImpl implements EngageDao{

	@Override
	public void add(Engage engage) {
		Connection con = com.lon.util.DBUtils.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_engage(empid,skillname,createDate) values(?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, engage.getEmpid());
			pst.setString(2, engage.getSkillname());
			pst.setDate(3, new Date(engage.getCreateDate().getTime()));
			
			System.out.println("成功新增【"+pst.executeUpdate()+"】条记录");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
		
	}

	@Override
	public void addMore(List<Engage> list) {
		Connection con = com.lon.util.DBUtils.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_engage(empid,skillname,createDate) values(?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			for(int i=0;i<list.size();i++) {
				Engage engage = list.get(i);
				pst.setInt(1, engage.getEmpid());
				pst.setString(2, engage.getSkillname());
				pst.setDate(3, new Date(engage.getCreateDate().getTime()));
				pst.addBatch();
				if(i%300==0) {
					pst.executeBatch();
					pst.clearBatch();
				}
			}
			pst.executeBatch();
			pst.clearBatch();
			System.out.println("成功新增【"+pst.executeUpdate()+"】条记录");
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
		String sql = "delete from hr_engage where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			System.out.println("成功删除【"+pst.executeUpdate()+"】条记录");
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
		String sql = "delete from hr_engage where id in ("+ids+")";
		try {
			pst = con.prepareStatement(sql);
			System.out.println("成功删除【"+pst.executeUpdate()+"】条记录");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
		
	}

	@Override
	public void update(Engage engage) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		String sql = "update hr_engage set empid=?,skillname=?,createDate=? where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, engage.getEmpid());
			pst.setString(2, engage.getSkillname());
			pst.setDate(3, new Date(engage.getCreateDate().getTime()));
			pst.setInt(4, engage.getId());
			System.out.println("成功更新【"+pst.executeUpdate()+"】条记录");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public Engage queryById(int id) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Engage engage = null;
		try {
			pst = con.prepareStatement("select * from hr_engage where id=?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				engage = new Engage();
				engage.setId(rs.getInt("id"));
				engage.setEmpid(rs.getInt("empid"));
				engage.setSkillname(rs.getString("skillname"));
				engage.setCreateDate(rs.getDate("createDate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return engage;
	}

	@Override
	public List<Engage> queryAll() {
		List<Engage> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_engage order by id";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				Engage a = new Engage();
				a.setId(rs.getInt("id"));
				a.setEmpid(rs.getInt("empid"));
				a.setSkillname(rs.getString("skillname"));
				a.setCreateDate(rs.getDate("createDate"));
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public List<Engage> queryByPage(int currentPage, int pageSize) {
		List<Engage> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_engage order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Engage a = new Engage();
				a.setId(rs.getInt("id"));
				a.setEmpid(rs.getInt("empid"));
				a.setSkillname(rs.getString("skillname"));
				a.setCreateDate(rs.getDate("createDate"));
				list.add(a);
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
	public List<Engage> queryByPage(int currentPage, int pageSize, String condition) {
		List<Engage> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_engage "+condition+" order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Engage a = new Engage();
				a.setId(rs.getInt("id"));
				a.setEmpid(rs.getInt("empid"));
				a.setSkillname(rs.getString("skillname"));
				a.setCreateDate(rs.getDate("createDate"));
				list.add(a);
			}
		} catch (SQLException e) {
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
		String sql = "select count(*) from hr_engage";
		try {
			pst = con.prepareStatement(sql);
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
		String sql = "select count(*) from hr_engage "+condition;
		try {
			pst = con.prepareStatement(sql);
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

	
}
