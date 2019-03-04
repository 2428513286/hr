package com.lon.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lon.dao.DeployDao;
import com.lon.entity.Deploy;
import com.lon.util.DBUtils;

public class DeployDaoImpl implements DeployDao{

	@Override
	public void add(Deploy deploy) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst=null;
		String sql = "insert into hr_deploy(empid,olddept,newdept,oldjob,newjob,mixDate) values(?,?,?,?,?,?)";
		try {
			pst=con.prepareStatement(sql);
			pst.setInt(1, deploy.getEmpid());
			pst.setString(2, deploy.getOlddept());
			pst.setString(3, deploy.getNewdept());
			pst.setString(4, deploy.getOldjob());
			pst.setString(5, deploy.getNewjob());
			pst.setDate(6, new Date(deploy.getMixDate().getTime()));
			System.out.println("成功增加"+pst.executeUpdate()+"记录");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public void addMore(List<Deploy> list) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst=null;
		String sql = "insert into hr_deploy(empid,olddept,newdept,oldjob,newjob,mixDate) values(?,?,?,?,?,?)";
		try {
			pst=con.prepareStatement(sql);
			for(int i=0;i<list.size();i++) {
				Deploy d = list.get(i);
				pst.setInt(1, d.getEmpid());
				pst.setString(2, d.getOlddept());
				pst.setString(3, d.getNewdept());
				pst.setString(4, d.getOldjob());
				pst.setString(5, d.getNewjob());
				pst.setDate(6, new Date(d.getMixDate().getTime()));
				System.out.println("成功增加"+pst.executeUpdate()+"记录");
				
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
		String sql = "delete from hr_deploy where id=?";
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
		String sql = "delete from hr_deploy where id in("+ids+")";
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
	public void update(Deploy deploy) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst=null;
		String sql = "update hr_deploy set empid=?,olddept=?,newdept=?,oldjob=?,newjob=?,mixDate=? where id=?";
		try {
			pst=con.prepareStatement(sql);
			pst.setInt(1, deploy.getEmpid());
			pst.setString(2, deploy.getOlddept());
			pst.setString(3, deploy.getNewdept());
			pst.setString(4, deploy.getOldjob());
			pst.setString(5, deploy.getNewjob());
			pst.setDate(6, new Date(deploy.getMixDate().getTime()));
			pst.setInt(7, deploy.getId());
			System.out.println("成功更新"+pst.executeUpdate()+"记录");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public Deploy queryById(int id) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Deploy d = null;
		String sql="select * from hr_deploy where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				d = new Deploy();
				d.setId(rs.getInt("id"));
				d.setEmpid(rs.getInt("empid"));
				d.setOlddept(rs.getString("olddept"));
				d.setNewdept(rs.getString("newdept"));
				d.setOldjob(rs.getString("oldjob"));
				d.setNewjob(rs.getString("newjob"));
				d.setMixDate(rs.getDate("mixDate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return d;
	}

	@Override
	public List<Deploy> queryAll() {
		List<Deploy> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_deploy where id=?";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if(rs.next()) {
				Deploy d = new Deploy();
				d.setId(rs.getInt("id"));
				d.setEmpid(rs.getInt("empid"));
				d.setOlddept(rs.getString("olddept"));
				d.setNewdept(rs.getString("newdept"));
				d.setOldjob(rs.getString("oldjob"));
				d.setNewjob(rs.getString("newjob"));
				d.setMixDate(rs.getDate("mixDate"));
				list.add(d);
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
	public List<Deploy> queryByPage(int currentPage, int pageSize) {
		List<Deploy> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_deploy order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Deploy d = new Deploy();
				d.setId(rs.getInt("id"));
				d.setEmpid(rs.getInt("empid"));
				d.setOlddept(rs.getString("olddept"));
				d.setNewdept(rs.getString("newdept"));
				d.setOldjob(rs.getString("oldjob"));
				d.setNewjob(rs.getString("newjob"));
				d.setMixDate(rs.getDate("mixDate"));
				list.add(d);
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
	public List<Deploy> queryByPage(int currentPage, int pageSize, String condition) {
		List<Deploy> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_deploy "+condition+" order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Deploy d = new Deploy();
				d.setId(rs.getInt("id"));
				d.setEmpid(rs.getInt("empid"));
				d.setOlddept(rs.getString("olddept"));
				d.setNewdept(rs.getString("newdept"));
				d.setOldjob(rs.getString("oldjob"));
				d.setNewjob(rs.getString("newjob"));
				d.setMixDate(rs.getDate("mixDate"));
				list.add(d);
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
			pst = con.prepareStatement("select count(*) from hr_deploy");
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
			pst = con.prepareStatement("select count(*) from hr_deploy "+condition);
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
