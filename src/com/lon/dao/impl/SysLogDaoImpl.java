package com.lon.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lon.dao.SysLogDao;
import com.lon.entity.SysLog;
import com.lon.util.DBUtils;

public class SysLogDaoImpl implements SysLogDao{


		@Override
		public void add(SysLog sysLog) {
			Connection con = com.lon.util.DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "insert into hr_sys_log(uid,loginTime,logoutTime) values(?,?,?)";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, sysLog.getUid());
				pst.setDate(2, new Date(sysLog.getLoginTime().getTime()));
				pst.setDate(3, new Date(sysLog.getLogoutTime().getTime()));
				
				System.out.println("成功新增【"+pst.executeUpdate()+"】条记录");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtils.close(con, pst, null);
			}
			
		}

		@Override
		public void addMore(List<SysLog> list) {
			Connection con = com.lon.util.DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "insert into hr_sys_log(uid,loginTime,logoutTime) values(?,?,?)";
			try {
				pst = con.prepareStatement(sql);
				for(int i=0;i<list.size();i++) {
					SysLog s = list.get(i);
					pst.setInt(1, s.getUid());
					pst.setDate(2, new Date(s.getLoginTime().getTime()));
					pst.setDate(3, new Date(s.getLogoutTime().getTime()));
					
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
			String sql = "delete from hr_sys_log where id=?";
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
			String sql = "delete from hr_sys_log where id in ("+ids+")";
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
		public void update(SysLog sysLog) {
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "update hr_sys_log set uid=?,loginTime=?,logoutTime=? where id=?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, sysLog.getUid());
				pst.setDate(2, new Date(sysLog.getLoginTime().getTime()));
				pst.setDate(3, new Date(sysLog.getLogoutTime().getTime()));
				pst.setInt(4, sysLog.getId());
				System.out.println("成功更新【"+pst.executeUpdate()+"】条记录");
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBUtils.close(con, pst, null);
			}
		}

		@Override
		public SysLog queryById(int id) {
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			SysLog s = null;
			try {
				pst = con.prepareStatement("select * from hr_sys_log where id=?");
				pst.setInt(1, id);
				rs = pst.executeQuery();
				if(rs.next()) {
					s = new SysLog();
					s.setId(rs.getInt("id"));
					s.setUid(rs.getInt("uid"));
					s.setLoginTime(rs.getDate("loginTime"));
					s.setLogoutTime(rs.getDate("logoutTime"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtils.close(con, pst, rs);
			}
			return s;
		}

		@Override
		public List<SysLog> queryAll() {
			List<SysLog> list = new ArrayList<>();
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from hr_sys_log order by id";
			try {
				pst = con.prepareStatement(sql);
				rs = pst.executeQuery();
				while(rs.next()) {
					SysLog s = new SysLog();
					s.setId(rs.getInt("id"));
					s.setUid(rs.getInt("uid"));
					s.setLoginTime(rs.getDate("loginTime"));
					s.setLogoutTime(rs.getDate("logoutTime"));
					list.add(s);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBUtils.close(con, pst, rs);
			}
			return list;
		}

		@Override
		public List<SysLog> queryByPage(int currentPage, int pageSize) {
			List<SysLog> list = new ArrayList<>();
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from hr_sys_log order by id limit ?,?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, (currentPage-1)*pageSize);
				pst.setInt(2, pageSize);
				rs = pst.executeQuery();
				while(rs.next()) {
					SysLog s = new SysLog();
					s.setId(rs.getInt("id"));
					s.setUid(rs.getInt("uid"));
					s.setLoginTime(rs.getDate("loginTime"));
					s.setLogoutTime(rs.getDate("logoutTime"));
					list.add(s);
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
		public List<SysLog> queryByPage(int currentPage, int pageSize, String condition) {
			List<SysLog> list = new ArrayList<>();
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from hr_sys_log "+condition+" order by id limit ?,?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, (currentPage-1)*pageSize);
				pst.setInt(2, pageSize);
				rs = pst.executeQuery();
				while(rs.next()) {
					SysLog s = new SysLog();
					s.setId(rs.getInt("id"));
					s.setUid(rs.getInt("uid"));
					s.setLoginTime(rs.getDate("loginTime"));
					s.setLogoutTime(rs.getDate("logoutTime"));
					list.add(s);
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
			String sql = "select count(*) from hr_sys_log";
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
			String sql = "select count(*) from hr_sys_log "+condition;
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
