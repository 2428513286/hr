package com.lon.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lon.dao.SysRoleMenuDao;
import com.lon.entity.SysRoleMenu;
import com.lon.util.DBUtils;

public class SysRoleMenuDaoImpl implements SysRoleMenuDao{


		@Override
		public void add(SysRoleMenu sysRoleMenu) {
			Connection con = com.lon.util.DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "insert into hr_sys_rolemenu(rid,mid) values(?,?)";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, sysRoleMenu.getRid());
				pst.setInt(2, sysRoleMenu.getMid());
				
				System.out.println("成功新增【"+pst.executeUpdate()+"】条记录");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtils.close(con, pst, null);
			}
		}

		@Override
		public void addMore(List<SysRoleMenu> list) {
			Connection con = com.lon.util.DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "insert into hr_sys_rolemenu(rid,mid) values(?,?)";
			try {
				pst=con.prepareStatement(sql);
				for(int i=0;i<list.size();i++) {
					SysRoleMenu sysRoleMenu = list.get(i);
					pst.setInt(1, sysRoleMenu.getRid());
					pst.setInt(2, sysRoleMenu.getMid());
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
			String sql = "delete from hr_sys_rolemenu where id=?";
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
			String sql = "delete from hr_sys_rolemenu where id in ("+ids+")";
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
		public void update(SysRoleMenu sysRoleMenu) {
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "update hr_sys_rolemenu set rid=?,mid=? where id=?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, sysRoleMenu.getRid());
				pst.setInt(2, sysRoleMenu.getMid());
				pst.setInt(3, sysRoleMenu.getId());
				System.out.println("成功更新【"+pst.executeUpdate()+"】条记录");
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBUtils.close(con, pst, null);
			}
		}

		@Override
		public SysRoleMenu queryById(int id) {
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			SysRoleMenu s = null;
			String sql = "select * from hr_sys_rolemenu where id=?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, id);
				rs = pst.executeQuery();
				if(rs.next()) {
					s = new SysRoleMenu();
					s.setId(rs.getInt("id"));
					s.setRid(rs.getInt("rid"));
					s.setMid(rs.getInt("mid"));
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
		public List<SysRoleMenu> queryAll() {
			List<SysRoleMenu> list = new ArrayList<>();
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from hr_sys_rolemenu order by id";
			try {
				pst = con.prepareStatement(sql);
				rs = pst.executeQuery();
				while(rs.next()) {
					SysRoleMenu s = new SysRoleMenu();
					s.setId(rs.getInt("id"));
					s.setRid(rs.getInt("rid"));
					s.setMid(rs.getInt("mid"));
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
		public List<SysRoleMenu> queryByPage(int currentPage, int pageSize) {
			List<SysRoleMenu> list = new ArrayList<>();
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from hr_sys_rolemenu order by id limit ?,?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, (currentPage-1)*pageSize);
				pst.setInt(2, pageSize);
				rs = pst.executeQuery();
				while(rs.next()) {
					SysRoleMenu s = new SysRoleMenu();
					s.setId(rs.getInt("id"));
					s.setRid(rs.getInt("rid"));
					s.setMid(rs.getInt("mid"));
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
		public List<SysRoleMenu> queryByPage(int currentPage, int pageSize, String condition) {
			List<SysRoleMenu> list = new ArrayList<>();
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from hr_sys_rolemenu "+condition+" order by id limit ?,?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, (currentPage-1)*pageSize);
				pst.setInt(2, pageSize);
				rs = pst.executeQuery();
				while(rs.next()) {
					SysRoleMenu s = new SysRoleMenu();
					s.setId(rs.getInt("id"));
					s.setRid(rs.getInt("rid"));
					s.setMid(rs.getInt("mid"));
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
			String sql = "select count(*) from hr_sys_rolemenu";
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
			String sql = "select count(*) from hr_sys_rolemenu "+condition;
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
