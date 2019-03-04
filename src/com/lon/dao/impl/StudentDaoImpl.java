package com.lon.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lon.dao.StudentDao;
import com.lon.entity.Student;
import com.lon.util.DBUtils;

public class StudentDaoImpl implements StudentDao{

	@Override
	public void add(Student student) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into student(name,username,password,sex,age,birthday,createTime) values(?,?,?,?,?,?,?)");
			pst.setString(1, student.getName());
			pst.setString(2, student.getUsername());
			pst.setString(3, student.getPassword());
			pst.setInt(4, student.getSex());
			pst.setInt(5, student.getAge());
			pst.setDate(6, new Date(student.getBirthday().getTime()));
			pst.setTimestamp(7, student.getCreateTime());
			System.out.println("成功新增【"+pst.executeUpdate()+"】条记录");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public void addMore(List<Student> list) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into student(name,username,password,sex,age,birthday,createTime) values(?,?,?,?,?,?,?)");
			for(int i=0;i<list.size();i++) {
				Student student = list.get(i);
				pst.setString(1, student.getName());
				pst.setString(2, student.getUsername());
				pst.setString(3, student.getPassword());
				pst.setInt(4, student.getSex());
				pst.setInt(5, student.getAge());
				pst.setDate(6, new Date(student.getBirthday().getTime()));
				pst.setTimestamp(7, student.getCreateTime());
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
			pst = con.prepareStatement("delete from student where id=?");
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
			pst = con.prepareStatement("delete from student where id in ("+ids+")");
			System.out.println("成功删除【"+pst.executeUpdate()+"】条记录");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public void update(Student student) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("update student set name=?,username=?,password=?,sex=?,age=?,birthday=?,createTime=? where id=?");
			pst.setString(1, student.getName());
			pst.setString(2, student.getUsername());
			pst.setString(3, student.getPassword());
			pst.setInt(4, student.getSex());
			pst.setInt(5, student.getAge());
			pst.setDate(6, new Date(student.getBirthday().getTime()));
			pst.setTimestamp(7, student.getCreateTime());
			pst.setInt(8, student.getId());
			System.out.println("成功更新【"+pst.executeUpdate()+"】条记录");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public Student queryById(int id) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Student s = null;
		try {
			pst = con.prepareStatement("select * from student where id=?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setUsername(rs.getString("username"));
				s.setPassword(rs.getString("password"));
				s.setSex(rs.getInt("sex"));
				s.setAge(rs.getInt("age"));
				s.setBirthday(rs.getDate("birthday"));
				s.setCreateTime(rs.getTimestamp("createTime"));
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
	public List<Student> queryAll() {
		List<Student> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from student order by id");
			rs = pst.executeQuery();
			while(rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setUsername(rs.getString("username"));
				s.setPassword(rs.getString("password"));
				s.setSex(rs.getInt("sex"));
				s.setAge(rs.getInt("age"));
				s.setBirthday(rs.getDate("birthday"));
				s.setCreateTime(rs.getTimestamp("createTime"));
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
	public List<Student> queryByPage(int currentPage, int pageSize) {
		List<Student> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from student order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setUsername(rs.getString("username"));
				s.setPassword(rs.getString("password"));
				s.setSex(rs.getInt("sex"));
				s.setAge(rs.getInt("age"));
				s.setBirthday(rs.getDate("birthday"));
				s.setCreateTime(rs.getTimestamp("createTime"));
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
	public List<Student> queryByPage(int currentPage, int pageSize, String condition) {
		List<Student> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from student "+condition+" order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setUsername(rs.getString("username"));
				s.setPassword(rs.getString("password"));
				s.setSex(rs.getInt("sex"));
				s.setAge(rs.getInt("age"));
				s.setBirthday(rs.getDate("birthday"));
				s.setCreateTime(rs.getTimestamp("createTime"));
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
			pst = con.prepareStatement("select count(*) from student");
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
			pst = con.prepareStatement("select count(*) from student "+condition);
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
