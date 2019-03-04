package com.lon.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import com.lon.dao.EmployeeDao;
import com.lon.entity.Employee;
import com.lon.util.DBUtils;
import com.lon.vo.EmployeeVO;

public class EmployeeDaoImpl implements EmployeeDao{

	@Override
	public void add(Employee employee) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst=null;
		String sql = "insert into hr_employee(name,sex,age,edu,degree,job,deptid,state,phone,address,createTime) values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pst=con.prepareStatement(sql);
			pst.setString(1, employee.getName());
			pst.setInt(2, employee.getSex());
			pst.setInt(3, employee.getAge());
			pst.setInt(4, employee.getEdu());
			pst.setInt(5, employee.getDegree());
			pst.setString(6, employee.getJob());
			pst.setInt(7, employee.getDeptid());
			pst.setInt(8, employee.getState());
			pst.setString(9, employee.getPhone());
			pst.setString(10, employee.getAddress());
			pst.setTimestamp(11, new Timestamp(System.currentTimeMillis()));
			
			System.out.println("成功增加"+pst.executeUpdate()+"记录");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
		
	}

	@Override
	public void addMore(List<Employee> list) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_employee(name,sex,age,edu,degree,job,deptid,state,phone,address,createTime) values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pst =con.prepareStatement(sql);
			for(int i=0;i<list.size();i++) {
				Employee e = list.get(i);
				pst.setString(1, e.getName());
				pst.setInt(2, e.getSex());
				pst.setInt(3, e.getAge());
				pst.setInt(4, e.getEdu());
				pst.setInt(5, e.getDegree());
				pst.setString(6, e.getJob());
				pst.setInt(7, e.getDeptid());
				pst.setInt(8, e.getState());
				pst.setString(9, e.getPhone());
				pst.setString(10, e.getAddress());
				pst.setTimestamp(11, new Timestamp(System.currentTimeMillis()));
				
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
			pst=con.prepareStatement("delete from hr_employee where id=?");
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
			pst=con.prepareStatement("delete from hr_employee where id in (" +ids+")");
			System.out.println("成功删除"+pst.executeUpdate()+"记录");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
		
	}

	@Override
	public void update(Employee employee) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst=con.prepareStatement("update hr_employee set name=?,sex=?,age=?,edu=?,degree=?,job=?,deptid=?,state=?,phone=?,address=?,createTime=? where id=?");
			pst.setString(1, employee.getName());
			pst.setInt(2, employee.getSex());
			pst.setInt(3, employee.getAge());
			pst.setInt(4, employee.getEdu());
			pst.setInt(5, employee.getDegree());
			pst.setString(6, employee.getJob());
			pst.setInt(7, employee.getDeptid());
			pst.setInt(8, employee.getState());
			pst.setString(9, employee.getPhone());
			pst.setString(10, employee.getAddress());
			pst.setTimestamp(11, new Timestamp(System.currentTimeMillis()));
			
			pst.setInt(12, employee.getId());
			
			System.out.println("成功更新"+pst.executeUpdate()+"记录");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
		
	}

	@Override
	public Employee queryById(int id) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Employee em = null;
		try {
			pst = con.prepareStatement("select * from hr_employee where id=?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				em = new Employee();
				em.setId(rs.getInt("id"));
				em.setName(rs.getString("name"));
				em.setSex(rs.getInt("sex"));
				em.setAge(rs.getInt("age"));
				em.setEdu(rs.getInt("edu"));
				em.setDegree(rs.getInt("degree"));
				em.setJob(rs.getString("job"));
				em.setDeptid(rs.getInt("deptid"));
				em.setState(rs.getInt("state"));
				em.setPhone(rs.getString("phone"));
				em.setAddress(rs.getString("address"));
				em.setCreateTime(rs.getTimestamp("createTime"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		
		return em;
	}

	@Override
	public List<Employee> queryAll() {
		List<Employee> list = new ArrayList<>();
		Connection con=DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_employee where id=?");
			rs= pst.executeQuery();
			while(rs.next()) {
				Employee em = new Employee();
				em.setId(rs.getInt("id"));
				em.setName(rs.getString("name"));
				em.setSex(rs.getInt("sex"));
				em.setAge(rs.getInt("age"));
				em.setEdu(rs.getInt("edu"));
				em.setDegree(rs.getInt("degree"));
				em.setJob(rs.getString("job"));
				em.setDeptid(rs.getInt("deptid"));
				em.setState(rs.getInt("state"));
				em.setPhone(rs.getString("phone"));
				em.setAddress(rs.getString("address"));
				em.setCreateTime(rs.getTimestamp("createTime"));
				list.add(em);
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
	public List<Employee> queryByPage(int currentPage, int pageSize) {
		List<Employee> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_employee order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Employee em = new Employee();
				em.setId(rs.getInt("id"));
				em.setName(rs.getString("name"));
				em.setSex(rs.getInt("sex"));
				em.setAge(rs.getInt("age"));
				em.setEdu(rs.getInt("edu"));
				em.setDegree(rs.getInt("degree"));
				em.setJob(rs.getString("job"));
				em.setDeptid(rs.getInt("deptid"));
				em.setState(rs.getInt("state"));
				em.setPhone(rs.getString("phone"));
				em.setAddress(rs.getString("address"));
				em.setCreateTime(rs.getTimestamp("createTime"));
				list.add(em);
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
	public List<Employee> queryByPage(int currentPage, int pageSize, String condition) {
		List<Employee> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_employee "+condition+" order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Employee em = new Employee();
				em.setId(rs.getInt("id"));
				em.setName(rs.getString("name"));
				em.setSex(rs.getInt("sex"));
				em.setAge(rs.getInt("age"));
				em.setEdu(rs.getInt("edu"));
				em.setDegree(rs.getInt("degree"));
				em.setJob(rs.getString("job"));
				em.setDeptid(rs.getInt("deptid"));
				em.setState(rs.getInt("state"));
				em.setPhone(rs.getString("phone"));
				em.setAddress(rs.getString("address"));
				em.setCreateTime(rs.getTimestamp("createTime"));
				list.add(em);
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
			pst = con.prepareStatement("select count(*) from hr_employee");
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
			pst = con.prepareStatement("select count(*) from hr_employee "+condition);
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
	public List<EmployeeVO> EmpQueryByPage(int currentPage, int pageSize) {
		List<EmployeeVO> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_employee, hr_department where hr_employee.deptid=hr_department.id order by hr_employee.id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				EmployeeVO em = new EmployeeVO();
				em.setId(rs.getInt("hr_employee.id"));
				em.setName(rs.getString("hr_employee.name"));
				em.setSex(rs.getInt("hr_employee.sex"));
				em.setAge(rs.getInt("hr_employee.age"));
				em.setEdu(rs.getInt("hr_employee.edu"));
				em.setDegree(rs.getInt("hr_employee.degree"));
				em.setJob(rs.getString("hr_employee.job"));
				em.setDeptname(rs.getString("hr_department.name"));
				em.setState(rs.getInt("hr_employee.state"));
				em.setPhone(rs.getString("hr_employee.phone"));
				em.setAddress(rs.getString("hr_employee.address"));
				em.setCreateTime(rs.getTimestamp("hr_employee.createTime"));
				list.add(em);
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
