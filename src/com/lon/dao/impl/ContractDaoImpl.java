package com.lon.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lon.dao.ContractDao;
import com.lon.entity.Contract;
import com.lon.util.DBUtils;
import com.lon.vo.ContractVO;

public class ContractDaoImpl implements ContractDao{

	@Override
	public void add(Contract contract) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst=null;
		String sql = "insert into hr_contract(empid,code,beginDate,endDate,job,content,attachment) values(?,?,?,?,?,?,?)";
		try {
			pst=con.prepareStatement(sql);
			pst.setInt(1, contract.getEmpid());
			pst.setString(2, contract.getCode());
			pst.setDate(3, new Date(contract.getBeginDate().getTime()));
			pst.setDate(4, new Date(contract.getEndDate().getTime()));
			pst.setString(5, contract.getJob());
			pst.setString(6, contract.getContent());
			pst.setString(7, contract.getAttachment());
			
			System.out.println("成功增加"+pst.executeUpdate()+"记录");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
		
	}

	@Override
	public void addMore(List<Contract> list) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_contract(empid,code,beginDate,endDate,job,content,attachment) values(?,?,?,?,?,?,?)";
		try {
			pst =con.prepareStatement(sql);
			for(int i=0;i<list.size();i++) {
				Contract c = list.get(i);
				pst.setInt(1, c.getEmpid());
				pst.setString(2, c.getCode());
				pst.setDate(3, new Date(c.getBeginDate().getTime()));
				pst.setDate(4, new Date(c.getEndDate().getTime()));
				pst.setString(5, c.getJob());
				pst.setString(6, c.getContent());
				pst.setString(7, c.getAttachment());
				
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
		try {
			pst=con.prepareStatement("delete from hr_contract where id=?");
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
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst=con.prepareStatement("delete from hr_contract where id in (" +ids+")");
			System.out.println("成功删除"+pst.executeUpdate()+"记录");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
		
		
	}

	@Override
	public void update(Contract contract) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		String sql = "update hr_contract set empid=?,code=?,beginDate=?,endDate=?,job=?,content=?,attachment=? where id=? ";
		try {
			pst =con.prepareStatement(sql);
			pst.setInt(1, contract.getEmpid());
			pst.setString(2, contract.getCode());
			pst.setDate(3, new Date(contract.getBeginDate().getTime()));
			pst.setDate(4, new Date(contract.getEndDate().getTime()));
			pst.setString(5, contract.getJob());
			pst.setString(6, contract.getContent());
			pst.setString(7, contract.getAttachment());
			pst.setInt(8, contract.getId());
			System.out.println("成功更新"+pst.executeUpdate()+"记录");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
		
	}

	@Override
	public Contract queryById(int id) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Contract c = null;
		try {
			pst = con.prepareStatement("select * from hr_contract where id=?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				c = new Contract();
				c.setId(rs.getInt("id"));
				c.setEmpid(rs.getInt("empid"));
				c.setCode(rs.getString("code"));
				c.setBeginDate(rs.getDate("beginDate"));
				c.setEndDate(rs.getDate("endDate"));
				c.setJob(rs.getString("job"));
				c.setContent(rs.getString("content"));
				c.setAttachment(rs.getString("attachment"));
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
	public List<Contract> queryAll() {
		List<Contract> list = new ArrayList<>();
		Connection con=DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_contract where id=?");
			rs= pst.executeQuery();
			while(rs.next()) {
				Contract c = new Contract();
				c.setId(rs.getInt("id"));
				c.setEmpid(rs.getInt("empid"));
				c.setCode(rs.getString("code"));
				c.setBeginDate(rs.getDate("beginDate"));
				c.setEndDate(rs.getDate("endDate"));
				c.setJob(rs.getString("job"));
				c.setContent(rs.getString("content"));
				c.setAttachment(rs.getString("attachment"));
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
	public List<Contract> queryByPage(int currentPage, int pageSize) {
		List<Contract> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_contract order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Contract c = new Contract();
				c.setId(rs.getInt("id"));
				c.setEmpid(rs.getInt("empid"));
				c.setCode(rs.getString("code"));
				c.setBeginDate(rs.getDate("beginDate"));
				c.setEndDate(rs.getDate("endDate"));
				c.setJob(rs.getString("job"));
				c.setContent(rs.getString("content"));
				c.setAttachment(rs.getString("attachment"));
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
	public List<Contract> queryByPage(int currentPage, int pageSize, String condition) {
		List<Contract> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_contract "+condition+" order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Contract c = new Contract();
				c.setId(rs.getInt("id"));
				c.setEmpid(rs.getInt("empid"));
				c.setCode(rs.getString("code"));
				c.setBeginDate(rs.getDate("beginDate"));
				c.setEndDate(rs.getDate("endDate"));
				c.setJob(rs.getString("job"));
				c.setContent(rs.getString("content"));
				c.setAttachment(rs.getString("attachment"));
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
			pst = con.prepareStatement("select count(*) from hr_contract");
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
			pst = con.prepareStatement("select count(*) from hr_contract "+condition);
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
	public List<ContractVO> cqueryByPage(int currentPage, int pageSize) {
		List<ContractVO> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_contract, hr_employee where hr_contract.empid=hr_employee.id order by hr_contract.id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				ContractVO c = new ContractVO();
				c.setId(rs.getInt("hr_contract.id"));
				c.setEmpname(rs.getString("hr_employee.name"));
				c.setCode(rs.getString("hr_contract.code"));
				c.setBeginDate(rs.getDate("hr_contract.beginDate"));
				c.setEndDate(rs.getDate("hr_contract.endDate"));
				c.setJob(rs.getString("hr_contract.job"));
				c.setContent(rs.getString("hr_contract.content"));
				c.setAttachment(rs.getString("hr_contract.attachment"));
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

	
}
