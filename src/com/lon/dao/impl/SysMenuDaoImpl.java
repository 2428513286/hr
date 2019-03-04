package com.lon.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lon.dao.SysMenuDao;
import com.lon.entity.SysMenu;
import com.lon.util.DBUtils;

public class SysMenuDaoImpl implements SysMenuDao{


		@Override
		public void add(SysMenu sysMenu) {
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "insert into hr_sys_menu(code,pcode,name,url,state,remark) values(?,?,?,?,?,?)";
			try {
				pst=con.prepareStatement(sql);
				pst.setString(1, sysMenu.getCode());
				pst.setString(2, sysMenu.getPcode());
				pst.setString(3, sysMenu.getName());
				pst.setString(4, sysMenu.getUrl());
				pst.setInt(5, sysMenu.getState());
				pst.setString(6, sysMenu.getRemark());
				
				pst.executeUpdate();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtils.close(con, pst, null);
			}
		}

		@Override
		public void addMore(List<SysMenu> list) {
			Connection con=DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "insert into hr_sys_menu(code,pcode,name,url,state,remark) values(?,?,?,?,?,?)";
			
			
			try {
				pst = con.prepareStatement(sql);
				for(int i =0;i<list.size();i++) {
					SysMenu s = list.get(i);
					pst.setString(1, s.getCode());
					pst.setString(2, s.getPcode());
					pst.setString(3, s.getName());
					pst.setString(4, s.getUrl());
					pst.setInt(5, s.getState());
					pst.setString(6, s.getRemark());
					
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
			String sql = "delete from hr_sys_menu where id=?";
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
			String sql = "delete from hr_sys_menu where id in("+ids+")";
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
		public void update(SysMenu sysMenu) {
			Connection con=DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "update hr_sys_menu set code=?,pcode=?,name=?,url=?,state=?,remark=? where id=?";
			
			try {
				pst=con.prepareStatement(sql);
				pst.setString(1, sysMenu.getCode());
				pst.setString(2, sysMenu.getPcode());
				pst.setString(3, sysMenu.getName());
				pst.setString(4, sysMenu.getUrl());
				pst.setInt(5, sysMenu.getState());
				pst.setString(6, sysMenu.getRemark());
				pst.setInt(7, sysMenu.getId());
				System.out.println("成功更新"+pst.executeUpdate()+"记录");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtils.close(con, pst, null);
			}
		}

		@Override
		public SysMenu queryById(int id) {
			Connection con = DBUtils.getConnection();
			PreparedStatement pst =null;
			ResultSet rs = null;
			SysMenu s = null;
			String sql = "select * from hr_sys_menu where id=?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, id);
				rs=pst.executeQuery();
				if(rs.next()){
					s = new SysMenu();
					s.setId(rs.getInt("id"));
					s.setCode(rs.getString("code"));
					s.setPcode(rs.getString("pcode"));
					s.setName(rs.getString("name"));
					s.setUrl(rs.getString("url"));
					s.setState(rs.getInt("state"));
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
		public List<SysMenu> queryAll() {
			List<SysMenu> list = new ArrayList<>();
			Connection con =  DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from hr_sys_menu where id=?";
			
			try {
				pst =con.prepareStatement(sql);
				rs=pst.executeQuery();
				if(rs.next()){
					SysMenu s = new SysMenu();
					s.setId(rs.getInt("id"));
					s.setCode(rs.getString("code"));
					s.setPcode(rs.getString("pcode"));
					s.setName(rs.getString("name"));
					s.setUrl(rs.getString("url"));
					s.setState(rs.getInt("state"));
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
		public List<SysMenu> queryByPage(int currentPage, int pageSize) {
			List<SysMenu> list = new ArrayList<>();
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from hr_sys_menu order by id limit ?,?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, (currentPage-1)*pageSize);
				pst.setInt(2, pageSize);
				rs = pst.executeQuery();
				while(rs.next()) {
					SysMenu s = new SysMenu();
					s.setId(rs.getInt("id"));
					s.setCode(rs.getString("code"));
					s.setPcode(rs.getString("pcode"));
					s.setName(rs.getString("name"));
					s.setUrl(rs.getString("url"));
					s.setState(rs.getInt("state"));
					s.setRemark(rs.getString("remark"));
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
		public List<SysMenu> queryByPage(int currentPage, int pageSize, String condition) {
			List<SysMenu> list = new ArrayList<>();
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from hr_sys_menu "+condition+" order by id limit ?,?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, (currentPage-1)*pageSize);
				pst.setInt(2, pageSize);
				rs = pst.executeQuery();
				while(rs.next()) {
					SysMenu s = new SysMenu();
					s.setId(rs.getInt("id"));
					s.setCode(rs.getString("code"));
					s.setPcode(rs.getString("pcode"));
					s.setName(rs.getString("name"));
					s.setUrl(rs.getString("url"));
					s.setState(rs.getInt("state"));
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
		public int getTotals() {
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			try {
				pst = con.prepareStatement("select count(*) from hr_sys_menu");
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
				pst = con.prepareStatement("select count(*) from hr_sys_menu "+condition);
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

	
