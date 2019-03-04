package com.lon.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lon.dao.SysUserRoleDao;
import com.lon.entity.SysUserRole;
import com.lon.util.DBUtils;

public class SysUserRoleDaoImpl implements SysUserRoleDao{


		@Override
		public void add(SysUserRole sysUserRole) {
			Connection con = com.lon.util.DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "insert into hr_sys_userrole(uid,rid) values(?,?)";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, sysUserRole.getUid());
				pst.setInt(2, sysUserRole.getRid());
				
				System.out.println("成功新增【"+pst.executeUpdate()+"】条记录");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtils.close(con, pst, null);
			}
		}

		@Override
		public void addMore(List<SysUserRole> list) {
			Connection con = com.lon.util.DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "insert into hr_sys_userrole(uid,rid) values(?,?)";
			try {
				pst=con.prepareStatement(sql);
				for(int i=0;i<list.size();i++) {
					SysUserRole sysUserRole = list.get(i);
					pst.setInt(1, sysUserRole.getUid());
					pst.setInt(2, sysUserRole.getRid());
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
			String sql = "delete from hr_sys_userrole where id=?";
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
			try {
				pst = con.prepareStatement("delete from hr_sys_userrole where id in ("+ids+")");
				System.out.println("成功删除【"+pst.executeUpdate()+"】条记录");
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBUtils.close(con, pst, null);
			}
		}

		@Override
		public void update(SysUserRole sysUserRole) {
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "update hr_sys_userrole set uid=?,rid=? where id=?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, sysUserRole.getUid());
				pst.setInt(2, sysUserRole.getRid());
				pst.setInt(3, sysUserRole.getId());
				System.out.println("成功更新【"+pst.executeUpdate()+"】条记录");
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBUtils.close(con, pst, null);
			}
		}

		@Override
		public SysUserRole queryById(int id) {
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			SysUserRole s = null;
			String sql  = "select * from hr_sys_userrole where id=?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, id);
				rs = pst.executeQuery();
				if(rs.next()) {
					s = new SysUserRole();
					s.setId(rs.getInt("id"));
					s.setUid(rs.getInt("uid"));
					s.setRid(rs.getInt("rid"));
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
		public List<SysUserRole> queryAll() {
			List<SysUserRole> list = new ArrayList<>();
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from hr_sys_userrole order by id";
			try {
				pst = con.prepareStatement(sql);
				rs = pst.executeQuery();
				while(rs.next()) {
					SysUserRole s = new SysUserRole();
					s.setId(rs.getInt("id"));
					s.setUid(rs.getInt("uid"));
					s.setRid(rs.getInt("rid"));
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
		public List<SysUserRole> queryByPage(int currentPage, int pageSize) {
			List<SysUserRole> list = new ArrayList<>();
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from hr_sys_userrole order by id limit ?,?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, (currentPage-1)*pageSize);
				pst.setInt(2, pageSize);
				rs = pst.executeQuery();
				while(rs.next()) {
					SysUserRole a = new SysUserRole();
					a.setId(rs.getInt("id"));
					a.setUid(rs.getInt("uid"));
					a.setRid(rs.getInt("rid"));
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
		public List<SysUserRole> queryByPage(int currentPage, int pageSize, String condition) {
			List<SysUserRole> list = new ArrayList<>();
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from hr_sys_userrole "+condition+" order by id limit ?,?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, (currentPage-1)*pageSize);
				pst.setInt(2, pageSize);
				rs = pst.executeQuery();
				while(rs.next()) {
					SysUserRole a = new SysUserRole();
					a.setId(rs.getInt("id"));
					a.setUid(rs.getInt("uid"));
					a.setRid(rs.getInt("rid"));
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
			String sql ="select count(*) from hr_sys_userrole";
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
			String sql = "select count(*) from hr_sys_userrole "+condition;
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
