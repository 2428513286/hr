package com.lon.dao;

import java.util.List;

import com.lon.entity.Contract;
import com.lon.vo.ContractVO;

public interface ContractDao {

	/**
	 * 新增
	 * @param 
	 */
	public void add(Contract contract);
	/**
	 * 批量新增
	 * @param list
	 */
	public void addMore(List<Contract> list);
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
	public void update(Contract contract);
	/**
	 * 根据主键查询记录
	 * @param id
	 * @return
	 */
	public Contract queryById(int id);
	/**
	 * 查询所有记录
	 * @return
	 */
	public List<Contract> queryAll();
	/**
	 * 分页查询记录
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Contract> queryByPage(int currentPage,int pageSize);
	public List<ContractVO> cqueryByPage(int currentPage,int pageSize);
	/**
	 * 条件分页查询记录
	 * @param currentPage
	 * @param pageSize
	 * @param condition
	 * @return
	 */
	public List<Contract> queryByPage(int currentPage,int pageSize,String condition);
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
