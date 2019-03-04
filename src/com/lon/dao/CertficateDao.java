package com.lon.dao;

import java.util.List;

import com.lon.entity.Certficate;


public interface CertficateDao {

	/**
	 * 新增
	 * @param 
	 */
	public void add(Certficate certficate);
	/**
	 * 批量新增
	 * @param list
	 */
	public void addMore(List<Certficate> list);
	/**
	 * 根据主键删除
	 * @param id
	 */
	public void deleteById(int id);
	/**
	 * 根据多个主键批量删除
	 * @param ids
	 */
	public void deleteMore(String ids);
	/**
	 * 更新
	 * @param 
	 */
	public void update(Certficate certficate);
	/**
	 * 根据主键查询记录
	 * @param id
	 * @return
	 */
	public Certficate queryById(int id);
	/**
	 * 查询所有记录
	 * @return
	 */
	public List<Certficate> queryAll();
	/**
	 * 分页查询记录
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Certficate> queryByPage(int currentPage,int pageSize);
	/**
	 * 条件分页查询记录
	 * @param currentPage
	 * @param pageSize
	 * @param condition
	 * @return
	 */
	public List<Certficate> queryByPage(int currentPage,int pageSize,String condition);
	/**
	 * 查询总记录数
	 * @return
	 */
	public int getTotals();
	/**
	 * 根据条件查询总记录数
	 * @param condition
	 * @return
	 */
	public int getTotals(String condition);
}
