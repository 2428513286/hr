package com.lon.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.lon.dao.SysUserDao;
import com.lon.entity.SysUser;
import com.lon.util.DBUtils;

public class SysUserDaoImpl implements SysUserDao{
	

		@Override
		public void add(SysUser sysUser) {
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "insert into hr_sys_user(username,password,empid,state,createTime) values(?,?,?,?,?)";
			try {
				pst=con.prepareStatement(sql);
				pst.setString(1, sysUser.getUsername());
				pst.setString(2, sysUser.getPassword());
				pst.setInt(3, sysUser.getEmpid());
				pst.setInt(4, sysUser.getState());
				pst.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
				
				pst.executeUpdate();
			
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtils.close(con, pst, null);
			}
			
			
			
		}

		@Override
		public void addMore(List<SysUser> list) {
			Connection con=DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "insert into hr_sys_user(username,password,empid,state,createTime) values(?,?,?,?,?)";
			
			
			try {
				pst = con.prepareStatement(sql);
				for(int i =0;i<list.size();i++) {
					SysUser s = list.get(i);
					pst.setString(1, s.getUsername());
					pst.setString(2, s.getPassword());
					pst.setInt(3, s.getEmpid());
					pst.setInt(4, s.getState());
					pst.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
					
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
			Connection con =DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "delete from hr_sys_user where id=?";
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
			Connection con=DBUtils.getConnection();
			PreparedStatement pst=null;
			String sql = "delete from hr_sys_user where id in("+ids+")";
			try {
				pst = con.prepareStatement(sql);
				System.out.println("成功删除"+pst.executeUpdate()+"记录");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtils.close(con, pst, null);
			}
		}

		@Override
		public void update(SysUser sysUser) {
			Connection con=DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "update hr_sys_user set username=?,password=?,empid=?,state=?,createTime=? where id=?";
			
			try {
				pst=con.prepareStatement(sql);
				pst.setString(1, sysUser.getUsername());
				pst.setString(2, sysUser.getPassword());
				pst.setInt(3, sysUser.getEmpid());
				pst.setInt(4, sysUser.getState());
				pst.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
				pst.setInt(6, sysUser.getId());
				System.out.println("成功更新"+pst.executeUpdate()+"记录");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtils.close(con, pst, null);
			}
		}

		@Override
		public SysUser queryById(int id) {
			Connection con = DBUtils.getConnection();
			PreparedStatement pst =null;
			ResultSet rs = null;
			SysUser s = null;
			String sql = "select * from hr_sys_user where id=?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, id);
				rs=pst.executeQuery();
				if(rs.next()){
					s = new SysUser();
					s.setId(rs.getInt("id"));
					s.setUsername(rs.getString("username"));
					s.setPassword(rs.getString("password"));
					s.setEmpid(rs.getInt("empid"));
					s.setState(rs.getInt("state"));
					s.setCreateTime(rs.getTimestamp("createTime"));
					
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
		public List<SysUser> queryAll() {
			List<SysUser> list = new ArrayList<>();
			Connection con =  DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from hr_sys_user where id=?";
			
			try {
				pst =con.prepareStatement(sql);
				rs=pst.executeQuery();
				if(rs.next()){
					SysUser s = new SysUser();
					s.setId(rs.getInt("id"));
					s.setUsername(rs.getString("username"));
					s.setPassword(rs.getString("password"));
					s.setEmpid(rs.getInt("empid"));
					s.setState(rs.getInt("state"));
					s.setCreateTime(rs.getTimestamp("createTime"));
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
		public List<SysUser> queryByPage(int currentPage, int pageSize) {
			List<SysUser> list = new ArrayList<>();
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			try {
				pst = con.prepareStatement("select * from hr_sys_user order by id limit ?,?");
				pst.setInt(1, (currentPage-1)*pageSize);
				pst.setInt(2, pageSize);
				rs = pst.executeQuery();
				while(rs.next()) {
					SysUser s = new SysUser();
					s.setId(rs.getInt("id"));
					s.setUsername(rs.getString("username"));
					s.setPassword(rs.getString("password"));
					s.setEmpid(rs.getInt("empid"));
					s.setState(rs.getInt("state"));
					s.setCreateTime(rs.getTimestamp("createTime"));
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
		public List<SysUser> queryByPage(int currentPage, int pageSize, String condition) {
			List<SysUser> list = new ArrayList<>();
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			try {
				pst = con.prepareStatement("select * from hr_sys_user "+condition+" order by id limit ?,?");
				pst.setInt(1, (currentPage-1)*pageSize);
				pst.setInt(2, pageSize);
				rs = pst.executeQuery();
				while(rs.next()) {
					SysUser s = new SysUser();
					s.setId(rs.getInt("id"));
					s.setUsername(rs.getString("username"));
					s.setPassword(rs.getString("password"));
					s.setEmpid(rs.getInt("empid"));
					s.setState(rs.getInt("state"));
					s.setCreateTime(rs.getTimestamp("createTime"));
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
		public int getTotals() {
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			try {
				pst = con.prepareStatement("select count(*) from hr_sys_user");
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
			String sql = "select count(*) from hr_sys_user "+condition;
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
