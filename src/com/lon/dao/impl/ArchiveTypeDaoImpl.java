package com.lon.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lon.dao.ArchiveTypeDao;
import com.lon.entity.ArchiveType;
import com.lon.entity.Department;
import com.lon.util.DBUtils;

public class ArchiveTypeDaoImpl implements ArchiveTypeDao{

	@Override
	public void add(ArchiveType archivetype) {
		Connection con = com.lon.util.DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into hr_archive_type(name,remark) values(?,?)");
			pst.setString(1, archivetype.getName());
			pst.setString(2, archivetype.getRemark());
			
			System.out.println("成功新增【"+pst.executeUpdate()+"】条记录");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public void addMore(List<ArchiveType> list) {
		Connection con = com.lon.util.DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst=con.prepareStatement("insert into hr_archive_type(name,remark) values(?,?)");
			for(int i=0;i<list.size();i++) {
				ArchiveType archiveType = list.get(i);
				pst.setString(1, archiveType.getName());
				pst.setString(2, archiveType.getRemark());
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
			pst = con.prepareStatement("delete from hr_archive_type where id=?");
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
			pst = con.prepareStatement("delete from hr_archive_type where id in ("+ids+")");
			System.out.println("成功删除【"+pst.executeUpdate()+"】条记录");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public void update(ArchiveType archivetype) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("update hr_archive_type set name=?,remark=? where id=?");
			pst.setString(1, archivetype.getName());
			pst.setString(2, archivetype.getRemark());
			pst.setInt(3, archivetype.getId());
			System.out.println("成功更新【"+pst.executeUpdate()+"】条记录");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public ArchiveType queryById(int id) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArchiveType a = null;
		try {
			pst = con.prepareStatement("select * from hr_archive_type where id=?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				a = new ArchiveType();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("name"));
				a.setRemark(rs.getString("remark"));
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
	public List<ArchiveType> queryAll() {
		List<ArchiveType> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_archive_type order by id");
			rs = pst.executeQuery();
			while(rs.next()) {
				ArchiveType a = new ArchiveType();
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
	public List<ArchiveType> queryByPage(int currentPage, int pageSize) {
		List<ArchiveType> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_archive_type order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				ArchiveType a = new ArchiveType();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("name"));
				a.setRemark(rs.getString("remark"));
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
	public List<ArchiveType> queryByPage(int currentPage, int pageSize, String condition) {
		List<ArchiveType> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_archive_type "+condition+" order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				ArchiveType a = new ArchiveType();
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
	public int getTotals() {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select count(*) from hr_archive_type");
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
			pst = con.prepareStatement("select count(*) from hr_archive_type "+condition);
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
