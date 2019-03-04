package com.lon.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lon.dao.RewardsDao;
import com.lon.entity.Rewards;
import com.lon.util.DBUtils;
import com.lon.vo.RewardsVO;

public class RewardsDaoImpl implements RewardsDao{

	@Override
	public void add(Rewards rewards) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_rewards(empid,title,content,type,createDate) values(?,?,?,?,?)";
		try {
			pst=con.prepareStatement(sql);
			pst.setInt(1, rewards.getEmpid());
			pst.setString(2, rewards.getTitle());
			pst.setString(3, rewards.getContent());
			pst.setInt(4, rewards.getType());
			pst.setDate(5, new Date(rewards.getCreateDate().getTime()));
			
			pst.executeUpdate();
		
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
		
		
		
	}

	@Override
	public void addMore(List<Rewards> list) {
		Connection con=DBUtils.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_rewards(empid,title,content,type,createDate) values(?,?,?,?,?)";
		
		
		try {
			pst = con.prepareStatement(sql);
			for(int i =0;i<list.size();i++) {
				Rewards r = list.get(i);
				pst.setInt(1, r.getEmpid());
				pst.setString(2, r.getTitle());
				pst.setString(3, r.getContent());
				pst.setString(4, r.getTitle());
				pst.setDate(5, new Date(r.getCreateDate().getTime()));
				
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
		String sql = "delete from hr_rewards where id=?";
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
		String sql = "delete from hr_rewards where id in("+ids+")";
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
	public void update(Rewards rewards) {
		Connection con=DBUtils.getConnection();
		PreparedStatement pst = null;
		String sql = "update hr_rewards set empid=?,title=?,content=?,type=?,createDate=? where id=?";
		
		try {
			pst=con.prepareStatement(sql);
			pst.setInt(1, rewards.getEmpid());
			pst.setString(2, rewards.getTitle());
			pst.setString(3, rewards.getContent());
			pst.setInt(4, rewards.getType());
			pst.setDate(5, new Date(rewards.getCreateDate().getTime()));
			pst.setInt(6, rewards.getId());
			System.out.println("成功更新"+pst.executeUpdate()+"记录");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public Rewards queryById(int id) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst =null;
		ResultSet rs = null;
		Rewards r = null;
		String sql = "select * from hr_rewards where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			if(rs.next()){
				r = new Rewards();
				r.setId(rs.getInt("id"));
				r.setEmpid(rs.getInt("empid"));
				r.setTitle(rs.getString("title"));
				r.setContent(rs.getString("content"));
				r.setType(rs.getInt("type"));
				r.setCreateDate(rs.getDate("createDate"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return r;
	}

	@Override
	public List<Rewards> queryAll() {
		List<Rewards> list = new ArrayList<>();
		Connection con =  DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_rewards where id=?";
		
		try {
			pst =con.prepareStatement(sql);
			rs=pst.executeQuery();
			if(rs.next()){
				Rewards r = new Rewards();
				r.setId(rs.getInt("id"));
				r.setEmpid(rs.getInt("empid"));
				r.setTitle(rs.getString("title"));
				r.setContent(rs.getString("content"));
				r.setType(rs.getInt("type"));
				r.setCreateDate(rs.getDate("createDate"));
				list.add(r);
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
	public List<Rewards> queryByPage(int currentPage, int pageSize) {
		List<Rewards> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_rewards order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Rewards r = new Rewards();
				r.setId(rs.getInt("id"));
				r.setEmpid(rs.getInt("empid"));
				r.setTitle(rs.getString("title"));
				r.setContent(rs.getString("content"));
				r.setType(rs.getInt("type"));
				r.setCreateDate(rs.getDate("createDate"));
				list.add(r);
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
	public List<Rewards> queryByPage(int currentPage, int pageSize, String condition) {
		List<Rewards> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_rewards "+condition+" order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Rewards r = new Rewards();
				r.setId(rs.getInt("id"));
				r.setEmpid(rs.getInt("empid"));
				r.setTitle(rs.getString("title"));
				r.setContent(rs.getString("content"));
				r.setType(rs.getInt("type"));
				r.setCreateDate(rs.getDate("createDate"));
				list.add(r);
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
			pst = con.prepareStatement("select count(*) from hr_rewards");
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
			pst = con.prepareStatement("select count(*) from hr_rewards "+condition);
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
	public List<RewardsVO> rqueryByPage(int currentPage, int pageSize) {
		List<RewardsVO> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_rewards, hr_employee where hr_rewards.empid=hr_employee.id order by hr_rewards.id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				RewardsVO r = new RewardsVO();
				r.setId(rs.getInt("hr_rewards.id"));
				r.setEmpname(rs.getString("hr_employee.name"));
				r.setTitle(rs.getString("hr_rewards.title"));
				r.setContent(rs.getString("hr_rewards.content"));
				r.setType(rs.getInt("hr_rewards.type"));
				r.setCreateDate(rs.getDate("hr_rewards.createDate"));
				list.add(r);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return list;
	}

	
}
