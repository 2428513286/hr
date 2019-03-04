package com.lon.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lon.dao.CertficateDao;
import com.lon.entity.Certficate;
import com.lon.util.DBUtils;

public class CertficateDaoImpl implements CertficateDao{


		@Override
		public void add(Certficate certficate) {
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "insert into hr_certficate(empid,eduid,name,code,getDate) values(?,?,?,?,?)";
			try {
				pst=con.prepareStatement(sql);
				pst.setInt(1, certficate.getEmpid());
				pst.setInt(2, certficate.getEduid());
				pst.setString(3, certficate.getName());
				pst.setString(4, certficate.getCode());
				pst.setDate(5, new Date(certficate.getGetDate().getTime()));
				
				pst.executeUpdate();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtils.close(con, pst, null);
			}
			
			
			
		}

		@Override
		public void addMore(List<Certficate> list) {
			Connection con=DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "insert into hr_certficate(empid,eduid,name,code,getDate) values(?,?,?,?,?)";
			
			
			try {
				pst = con.prepareStatement(sql);
				for(int i =0;i<list.size();i++) {
					Certficate c = list.get(i);
					pst.setInt(1, c.getEmpid());
					pst.setInt(2, c.getEduid());
					pst.setString(3, c.getName());
					pst.setString(4, c.getCode());
					pst.setDate(5, new Date(c.getGetDate().getTime()));
					
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
			String sql = "delete from hr_certficate where id=?";
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
			String sql = "delete from hr_certficate where id in("+ids+")";
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
		public void update(Certficate certficate) {
			Connection con=DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "update hr_certficate set empid=?,eduid=?,name=?,code=?,getDate=? where id=?";
			
			try {
				pst=con.prepareStatement(sql);
				pst.setInt(1, certficate.getEmpid());
				pst.setInt(2, certficate.getEduid());
				pst.setString(3, certficate.getName());
				pst.setString(4, certficate.getCode());
				pst.setDate(5, new Date(certficate.getGetDate().getTime()));
				pst.setInt(6, certficate.getId());
				System.out.println("成功更新"+pst.executeUpdate()+"记录");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtils.close(con, pst, null);
			}
		}

		@Override
		public Certficate queryById(int id) {
			Connection con = DBUtils.getConnection();
			PreparedStatement pst =null;
			ResultSet rs = null;
			Certficate c = null;
			String sql = "select * from hr_certficate where id=?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, id);
				rs=pst.executeQuery();
				if(rs.next()){
					c = new Certficate();
					c.setId(rs.getInt("id"));
					c.setEmpid(rs.getInt("empid"));
					c.setEduid(rs.getInt("eduid"));
					c.setName(rs.getString("name"));
					c.setCode(rs.getString("code"));
					c.setGetDate(rs.getDate("getDate"));
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtils.close(con, pst, rs);
			}
			return c;
		}

		@Override
		public List<Certficate> queryAll() {
			List<Certficate> list = new ArrayList<>();
			Connection con =  DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from hr_certficate where id=?";
			
			try {
				pst =con.prepareStatement(sql);
				rs=pst.executeQuery();
				if(rs.next()){
					Certficate c = new Certficate();
					c.setId(rs.getInt("id"));
					c.setEmpid(rs.getInt("empid"));
					c.setEduid(rs.getInt("eduid"));
					c.setName(rs.getString("name"));
					c.setCode(rs.getString("code"));
					c.setGetDate(rs.getDate("getDate"));
					list.add(c);
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
		public List<Certficate> queryByPage(int currentPage, int pageSize) {
			List<Certficate> list = new ArrayList<>();
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			try {
				pst = con.prepareStatement("select * from hr_certficate order by id limit ?,?");
				pst.setInt(1, (currentPage-1)*pageSize);
				pst.setInt(2, pageSize);
				rs = pst.executeQuery();
				while(rs.next()) {
					Certficate c = new Certficate();
					c.setId(rs.getInt("id"));
					c.setEmpid(rs.getInt("empid"));
					c.setEduid(rs.getInt("eduid"));
					c.setName(rs.getString("name"));
					c.setCode(rs.getString("code"));
					c.setGetDate(rs.getDate("getDate"));
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
		public List<Certficate> queryByPage(int currentPage, int pageSize, String condition) {
			List<Certficate> list = new ArrayList<>();
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			try {
				pst = con.prepareStatement("select * from hr_certficate "+condition+" order by id limit ?,?");
				pst.setInt(1, (currentPage-1)*pageSize);
				pst.setInt(2, pageSize);
				rs = pst.executeQuery();
				while(rs.next()) {
					Certficate c = new Certficate();
					c.setId(rs.getInt("id"));
					c.setEmpid(rs.getInt("empid"));
					c.setEduid(rs.getInt("eduid"));
					c.setName(rs.getString("name"));
					c.setCode(rs.getString("code"));
					c.setGetDate(rs.getDate("getDate"));
					list.add(c);
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
				pst = con.prepareStatement("select count(*) from hr_certficate");
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
				pst = con.prepareStatement("select count(*) from hr_certficate "+condition);
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
