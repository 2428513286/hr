package com.lon.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.lon.dao.ArchiveDao;
import com.lon.entity.Archive;
import com.lon.util.DBUtils;
import com.lon.vo.ArchiveVO;

public class ArchiveDaoImpl implements ArchiveDao{

	@Override
	public void add(Archive archive) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst=null;
		String sql = "insert into hr_archive(empid,code,name,content,type,remark,createTime) values(?,?,?,?,?,?,?)";
		try {
			pst=con.prepareStatement(sql);
			pst.setInt(1, archive.getEmpid());
			pst.setString(2, archive.getCode());
			pst.setString(3, archive.getName());
			pst.setString(4, archive.getContent());
			pst.setInt(5, archive.getType());
			pst.setString(6, archive.getRemark());
			pst.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			
			System.out.println("成功增加"+pst.executeUpdate()+"记录");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
		
	}

	@Override
	public void addMore(List<Archive> list) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_archive(empid,code,name,content,type,remark,createTime) values(?,?,?,?,?,?,?)";
		try {
			pst =con.prepareStatement(sql);
			for(int i=0;i<list.size();i++) {
				Archive a = list.get(i);
				pst.setInt(1, a.getEmpid());
				pst.setString(2, a.getCode());
				pst.setString(3, a.getName());
				pst.setString(4, a.getContent());
				pst.setInt(5, a.getType());
				pst.setString(6, a.getRemark());
				pst.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
				
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
			pst=con.prepareStatement("delete from hr_archive where id=?");
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
			pst=con.prepareStatement("delete from hr_archive where id in (" +ids+")");
			System.out.println("成功删除"+pst.executeUpdate()+"记录");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
		
		
	}

	@Override
	public void update(Archive archive) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		String sql = "update hr_archive set empid=?,code=?,name=?,content=?,type=?,remark=?,createTime=? where id=? ";
		try {
			pst =con.prepareStatement(sql);
			pst.setInt(1, archive.getEmpid());
			pst.setString(2, archive.getCode());
			pst.setString(3, archive.getName());
			pst.setString(4, archive.getContent());
			pst.setInt(5, archive.getType());
			pst.setString(6, archive.getRemark());
			pst.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			pst.setInt(8, archive.getId());
			System.out.println("成功更新"+pst.executeUpdate()+"记录");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
		
	}

	@Override
	public Archive queryById(int id) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Archive a = null;
		try {
			pst = con.prepareStatement("select * from hr_archive where id=?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				a = new Archive();
				a.setId(rs.getInt("id"));
				a.setEmpid(rs.getInt("empid"));
				a.setCode(rs.getString("code"));
				a.setName(rs.getString("name"));
				a.setContent(rs.getString("content"));
				a.setType(rs.getInt("type"));
				a.setRemark(rs.getString("remark"));
				a.setCreateTime(rs.getTimestamp("createTime"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return a;
		
	}

	@Override
	public List<Archive> queryAll() {
		List<Archive> list = new ArrayList<>();
		Connection con=DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_archive where id=?");
			rs= pst.executeQuery();
			while(rs.next()) {
				Archive a = new Archive();
				a = new Archive();
				a.setId(rs.getInt("id"));
				a.setEmpid(rs.getInt("empid"));
				a.setCode(rs.getString("code"));
				a.setName(rs.getString("name"));
				a.setContent(rs.getString("content"));
				a.setType(rs.getInt("type"));
				a.setRemark(rs.getString("remark"));
				a.setCreateTime(rs.getTimestamp("createTime"));
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
	public List<Archive> queryByPage(int currentPage, int pageSize) {
		List<Archive> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_archive order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Archive a = new Archive();
				a = new Archive();
				a.setId(rs.getInt("id"));
				a.setEmpid(rs.getInt("empid"));
				a.setCode(rs.getString("code"));
				a.setName(rs.getString("name"));
				a.setContent(rs.getString("content"));
				a.setType(rs.getInt("type"));
				a.setRemark(rs.getString("remark"));
				a.setCreateTime(rs.getTimestamp("createTime"));
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
	public List<Archive> queryByPage(int currentPage, int pageSize, String condition) {
		List<Archive> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_archive "+condition+" order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Archive a = new Archive();
				a.setId(rs.getInt("id"));
				a.setEmpid(rs.getInt("empid"));
				a.setCode(rs.getString("code"));
				a.setName(rs.getString("name"));
				a.setContent(rs.getString("content"));
				a.setType(rs.getInt("type"));
				a.setRemark(rs.getString("remark"));
				a.setCreateTime(rs.getTimestamp("createTime"));
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
	public int getTotals() {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select count(*) from hr_archive");
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
			pst = con.prepareStatement("select count(*) from hr_archive "+condition);
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
	public List<ArchiveVO> AqueryByPage(int currentPage, int pageSize) {
		List<ArchiveVO> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_archive left JOIN hr_employee ON  hr_archive.empid=hr_employee.id left JOIN hr_archive_type ON hr_archive.type=hr_archive_type.id\r\n" + 
				"GROUP BY hr_archive.id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				ArchiveVO a = new ArchiveVO();
				a.setId(rs.getInt("hr_archive.id"));
				a.setEmpname(rs.getString("hr_employee.name"));
				a.setCode(rs.getString("hr_archive.code"));
				a.setName(rs.getString("hr_archive.name"));
				a.setContent(rs.getString("hr_archive.content"));
				a.setAtname(rs.getString("hr_archive_type.name"));
				a.setRemark(rs.getString("hr_archive.remark"));
				a.setCreateTime(rs.getTimestamp("hr_archive.createTime"));
				list.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return list;
	}

	
}
