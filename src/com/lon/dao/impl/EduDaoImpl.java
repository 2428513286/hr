package com.lon.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lon.dao.EduDao;
import com.lon.entity.Edu;
import com.lon.util.DBUtils;

public class EduDaoImpl implements EduDao{
	

		@Override
		public void add(Edu edu) {
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "insert into hr_edu(name,biginDate,endDate,adress,type) values(?,?,?,?,?)";
			try {
				pst=con.prepareStatement(sql);
				pst.setString(1, edu.getName());
				pst.setDate(2, new Date(edu.getBiginDate().getTime()));
				pst.setDate(3, new Date(edu.getEndDate().getTime()));
				pst.setString(4, edu.getAdress());
				pst.setInt(5, edu.getType());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtils.close(con, pst, null);
			}
			
			
			
		}

		@Override
		public void addMore(List<Edu> list) {
			Connection con=DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "insert into hr_edu(name,biginDate,endDate,adress,type) values(?,?,?,?,?)";
			
			
			try {
				pst = con.prepareStatement(sql);
				for(int i =0;i<list.size();i++) {
					Edu edu = list.get(i);
					pst.setString(1, edu.getName());
					pst.setDate(2, new Date(edu.getBiginDate().getTime()));
					pst.setDate(3, new Date(edu.getEndDate().getTime()));
					pst.setString(4, edu.getAdress());
					pst.setInt(5, edu.getType());
					
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
			String sql = "delete from hr_edu where id=?";
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
			String sql = "delete from hr_edu where id in("+ids+")";
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
		public void update(Edu edu) {
			Connection con=DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "update hr_edu set name=?,biginDate=?,endDate=?,adress=?,type=? where id=?";
			
			try {
				pst=con.prepareStatement(sql);
				pst.setString(1, edu.getName());
				pst.setDate(2, new Date(edu.getBiginDate().getTime()));
				pst.setDate(3, new Date(edu.getEndDate().getTime()));
				pst.setString(4, edu.getAdress());
				pst.setInt(5, edu.getType());
				pst.setInt(6, edu.getId());
				System.out.println("成功更新"+pst.executeUpdate()+"记录");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtils.close(con, pst, null);
			}
		}

		@Override
		public Edu queryById(int id) {
			Connection con = DBUtils.getConnection();
			PreparedStatement pst =null;
			ResultSet rs = null;
			Edu edu = null;
			String sql = "select * from hr_edu where id=?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, id);
				rs=pst.executeQuery();
				if(rs.next()){
					edu = new Edu();
					edu.setId(rs.getInt("id"));
					edu.setName(rs.getString("name"));
					edu.setBiginDate(rs.getDate("biginDate"));
					edu.setEndDate(rs.getDate("endDate"));
					edu.setAdress(rs.getString("adress"));
					edu.setType(rs.getInt("type"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtils.close(con, pst, rs);
			}
			return edu;
		}

		@Override
		public List<Edu> queryAll() {
			List<Edu> list = new ArrayList<>();
			Connection con =  DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from hr_edu where id=?";
			
			try {
				pst =con.prepareStatement(sql);
				rs=pst.executeQuery();
				if(rs.next()){
					Edu edu = new Edu();
					edu.setId(rs.getInt("id"));
					edu.setName(rs.getString("name"));
					edu.setBiginDate(rs.getDate("biginDate"));
					edu.setEndDate(rs.getDate("endDate"));
					edu.setAdress(rs.getString("adress"));
					edu.setType(rs.getInt("type"));
					list.add(edu);
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
		public List<Edu> queryByPage(int currentPage, int pageSize) {
			List<Edu> list = new ArrayList<>();
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			try {
				pst = con.prepareStatement("select * from hr_edu order by id limit ?,?");
				pst.setInt(1, (currentPage-1)*pageSize);
				pst.setInt(2, pageSize);
				rs = pst.executeQuery();
				while(rs.next()) {
					Edu edu = new Edu();
					edu.setId(rs.getInt("id"));
					edu.setName(rs.getString("name"));
					edu.setBiginDate(rs.getDate("biginDate"));
					edu.setEndDate(rs.getDate("endDate"));
					edu.setAdress(rs.getString("adress"));
					edu.setType(rs.getInt("type"));
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
		public List<Edu> queryByPage(int currentPage, int pageSize, String condition) {
			List<Edu> list = new ArrayList<>();
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			try {
				pst = con.prepareStatement("select * from hr_edu "+condition+" order by id limit ?,?");
				pst.setInt(1, (currentPage-1)*pageSize);
				pst.setInt(2, pageSize);
				rs = pst.executeQuery();
				while(rs.next()) {
					Edu edu = new Edu();
					edu.setId(rs.getInt("id"));
					edu.setName(rs.getString("name"));
					edu.setBiginDate(rs.getDate("biginDate"));
					edu.setEndDate(rs.getDate("endDate"));
					edu.setAdress(rs.getString("adress"));
					edu.setType(rs.getInt("type"));
					list.add(edu);
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
				pst = con.prepareStatement("select count(*) from hr_edu");
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
				pst = con.prepareStatement("select count(*) from hr_edu "+condition);
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
