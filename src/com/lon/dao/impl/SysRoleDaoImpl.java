package com.lon.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lon.dao.SysRoleDao;
import com.lon.entity.SysRole;
import com.lon.util.DBUtils;

public class SysRoleDaoImpl implements SysRoleDao{

		@Override
		public void add(SysRole sysRole) {
			Connection con = com.lon.util.DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "insert into hr_sys_role(name,remark) values(?,?)";
			try {
				pst = con.prepareStatement(sql);
				pst.setString(1, sysRole.getName());
				pst.setString(2, sysRole.getRemark());
				
				System.out.println("成功新增【"+pst.executeUpdate()+"】条记录");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtils.close(con, pst, null);
			}
		}

		@Override
		public void addMore(List<SysRole> list) {
			Connection con = com.lon.util.DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "insert into hr_sys_role(name,remark) values(?,?)";
			try {
				pst=con.prepareStatement(sql);
				for(int i=0;i<list.size();i++) {
					SysRole sysRole = list.get(i);
					pst.setString(1, sysRole.getName());
					pst.setString(2, sysRole.getRemark());
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
			String sql = "delete from hr_sys_role where id=?";
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

		public void deleteMore(String ids) {
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "delete from hr_sys_role where id in ("+ids+")";
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
		public void update(SysRole sysRole) {
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "update hr_sys_role set name=?,remark=? where id=?";
			try {
				pst = con.prepareStatement(sql);
				pst.setString(1, sysRole.getName());
				pst.setString(2, sysRole.getRemark());
				pst.setInt(3, sysRole.getId());
				System.out.println("成功更新【"+pst.executeUpdate()+"】条记录");
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBUtils.close(con, pst, null);
			}
		}

		@Override
		public SysRole queryById(int id) {
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			SysRole s = null;
			String sql = "select * from hr_sys_role where id=?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, id);
				rs = pst.executeQuery();
				if(rs.next()) {
					s = new SysRole();
					s.setId(rs.getInt("id"));
					s.setName(rs.getString("name"));
					s.setRemark(rs.getString("remark"));
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
		public List<SysRole> queryAll() {
			List<SysRole> list = new ArrayList<>();
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from hr_sys_role order by id";
			try {
				pst = con.prepareStatement(sql);
				rs = pst.executeQuery();
				while(rs.next()) {
					SysRole a = new SysRole();
					a.setId(rs.getInt("id"));
					a.setName(rs.getString("name"));
					a.setRemark(rs.getString("remark"));
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
		public List<SysRole> queryByPage(int currentPage, int pageSize) {
			List<SysRole> list = new ArrayList<>();
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from hr_sys_role order by id limit ?,?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, (currentPage-1)*pageSize);
				pst.setInt(2, pageSize);
				rs = pst.executeQuery();
				while(rs.next()) {
					SysRole s = new SysRole();
					s.setId(rs.getInt("id"));
					s.setName(rs.getString("name"));
					s.setRemark(rs.getString("remark"));
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
		public List<SysRole> queryByPage(int currentPage, int pageSize, String condition) {
			List<SysRole> list = new ArrayList<>();
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from hr_sys_role "+condition+" order by id limit ?,?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, (currentPage-1)*pageSize);
				pst.setInt(2, pageSize);
				rs = pst.executeQuery();
				while(rs.next()) {
					SysRole s = new SysRole();
					s.setId(rs.getInt("id"));
					s.setName(rs.getString("name"));
					s.setRemark(rs.getString("remark"));
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
			String sql = "select count(*) from hr_sys_role";
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
			String sql = "select count(*) from hr_sys_role "+condition;
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
