package com.lon.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lon.dao.DepartmentDao;
import com.lon.entity.Department;
import com.lon.util.DBUtils;

public class DepartmentDaoImpl implements DepartmentDao{

	@Override
	public void add(Department department) {
		Connection con = com.lon.util.DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into hr_department(name,manager,offceNo,phone,remark) values(?,?,?,?,?)");
			pst.setString(1, department.getName());
			pst.setString(2, department.getManager());
			pst.setString(3, department.getOffceNo());
			pst.setString(4, department.getPhone());
			pst.setString(5, department.getRemark());
			
			System.out.println("成功新增【"+pst.executeUpdate()+"】条记录");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public void addMore(List<Department> list) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into hr_department(name,manager,offceNo,phone,remark) values(?,?,?,?,?)");
			for(int i=0;i<list.size();i++) {
				Department department = list.get(i);
				pst.setString(1, department.getName());
				pst.setString(2, department.getManager());
				pst.setString(3, department.getOffceNo());
				pst.setString(4, department.getPhone());
				pst.setString(5, department.getRemark());
				
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
			pst = con.prepareStatement("delete from hr_department where id=?");
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
		try {
			pst = con.prepareStatement("delete from hr_department where id in ("+ids+")");
			System.out.println("成功删除【"+pst.executeUpdate()+"】条记录");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public void update(Department department) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("update hr_department set name=?,manager=?,offceNo=?,phone=?,remark=? where id=?");
			pst.setString(1, department.getName());
			pst.setString(2, department.getManager());
			pst.setString(3, department.getOffceNo());
			pst.setString(4, department.getPhone());
			pst.setString(5, department.getRemark());
			
			pst.setInt(6, department.getId());
			System.out.println("成功更新【"+pst.executeUpdate()+"】条记录");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public Department queryById(int id) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Department d = null;
		try {
			pst = con.prepareStatement("select * from hr_department where id=?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				d = new Department();
				d.setId(rs.getInt("id"));
				d.setName(rs.getString("name"));
				d.setManager(rs.getString("manager"));
				d.setOffceNo(rs.getString("offceNo"));
				d.setPhone(rs.getString("phone"));
				d.setRemark(rs.getString("remark"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return d;
	}

	@Override
	public List<Department> queryAll() {
		List<Department> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_department order by id");
			rs = pst.executeQuery();
			while(rs.next()) {
				Department d = new Department();
				d.setId(rs.getInt("id"));
				d.setName(rs.getString("name"));
				d.setManager(rs.getString("manager"));
				d.setOffceNo(rs.getString("offceNo"));
				d.setPhone(rs.getString("phone"));
				d.setRemark(rs.getString("remark"));
				list.add(d);
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
	public List<Department> queryByPage(int currentPage, int pageSize) {
		List<Department> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_department order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Department d = new Department();
				d.setId(rs.getInt("id"));
				d.setName(rs.getString("name"));
				d.setManager(rs.getString("manager"));
				d.setOffceNo(rs.getString("offceNo"));
				d.setPhone(rs.getString("phone"));
				d.setRemark(rs.getString("remark"));
				list.add(d);
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
	public List<Department> queryByPage(int currentPage, int pageSize, String condition) {
		List<Department> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_department "+condition+" order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Department d = new Department();
				d.setId(rs.getInt("id"));
				d.setName(rs.getString("name"));
				d.setManager(rs.getString("manager"));
				d.setOffceNo(rs.getString("offceNo"));
				d.setPhone(rs.getString("phone"));
				d.setRemark(rs.getString("remark"));
				list.add(d);
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
			pst = con.prepareStatement("select count(*) from hr_department");
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
			pst = con.prepareStatement("select count(*) from hr_department "+condition);
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
