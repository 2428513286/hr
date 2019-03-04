package com.lon.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lon.dao.EduScoreDao;
import com.lon.entity.EduScore;
import com.lon.util.DBUtils;

public class EduScoreDaoImpl implements EduScoreDao{

		@Override
		public void add(EduScore eduScore) {
			Connection con = com.lon.util.DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "insert into hr_edu_score(empid,eduid,score) values(?,?,?)";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, eduScore.getEmpid());
				pst.setInt(2, eduScore.getEduid());
				pst.setInt(3, eduScore.getScore());
				
				System.out.println("成功新增【"+pst.executeUpdate()+"】条记录");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtils.close(con, pst, null);
			}
			
		}

		@Override
		public void addMore(List<EduScore> list) {
			Connection con = com.lon.util.DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "insert into hr_edu_score(empid,eduid,score) values(?,?,?)";
			try {
				pst = con.prepareStatement(sql);
				for(int i=0;i<list.size();i++) {
					EduScore ed = list.get(i);
					pst.setInt(1, ed.getEmpid());
					pst.setInt(2, ed.getEduid());
					pst.setInt(3, ed.getScore());
					
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
			String sql = "delete from hr_edu_score where id=?";
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
			String sql = "delete from hr_edu_score where id in ("+ids+")";
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
		public void update(EduScore eduScore) {
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			String sql = "update hr_edu_score set empid=?,eduid=?,score=? where id=?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, eduScore.getEmpid());
				pst.setInt(2, eduScore.getEduid());
				pst.setInt(3, eduScore.getScore());
				pst.setInt(4, eduScore.getId());
				System.out.println("成功更新【"+pst.executeUpdate()+"】条记录");
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBUtils.close(con, pst, null);
			}
		}

		@Override
		public EduScore queryById(int id) {
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			EduScore en = null;
			try {
				pst = con.prepareStatement("select * from hr_edu_score where id=?");
				pst.setInt(1, id);
				rs = pst.executeQuery();
				if(rs.next()) {
					en = new EduScore();
					en.setId(rs.getInt("id"));
					en.setEmpid(rs.getInt("empid"));
					en.setEduid(rs.getInt("eduid"));
					en.setScore(rs.getInt("score"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtils.close(con, pst, rs);
			}
			return en;
		}

		@Override
		public List<EduScore> queryAll() {
			List<EduScore> list = new ArrayList<>();
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from hr_edu_score order by id";
			try {
				pst = con.prepareStatement(sql);
				rs = pst.executeQuery();
				while(rs.next()) {
					EduScore a = new EduScore();
					a.setId(rs.getInt("id"));
					a.setEmpid(rs.getInt("empid"));
					a.setEduid(rs.getInt("eduid"));
					a.setScore(rs.getInt("score"));
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
		public List<EduScore> queryByPage(int currentPage, int pageSize) {
			List<EduScore> list = new ArrayList<>();
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from hr_edu_score order by id limit ?,?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, (currentPage-1)*pageSize);
				pst.setInt(2, pageSize);
				rs = pst.executeQuery();
				while(rs.next()) {
					EduScore a = new EduScore();
					a.setId(rs.getInt("id"));
					a.setEmpid(rs.getInt("empid"));
					a.setEduid(rs.getInt("eduid"));
					a.setScore(rs.getInt("score"));
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
		public List<EduScore> queryByPage(int currentPage, int pageSize, String condition) {
			List<EduScore> list = new ArrayList<>();
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from hr_edu_score "+condition+" order by id limit ?,?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, (currentPage-1)*pageSize);
				pst.setInt(2, pageSize);
				rs = pst.executeQuery();
				while(rs.next()) {
					EduScore a = new EduScore();
					a.setId(rs.getInt("id"));
					a.setEmpid(rs.getInt("empid"));
					a.setEduid(rs.getInt("eduid"));
					a.setScore(rs.getInt("score"));
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
			String sql = "select count(*) from hr_edu_score";
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
			String sql = "select count(*) from hr_edu_score "+condition;
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
