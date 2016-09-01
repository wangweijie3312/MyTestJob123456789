package com.mysqltest.dao;

import java.util.List;

import com.mysqltest.pojo.Collect;

/**
 * @author wangweijie
 * 测试MySQL数据库的千万级读取
 */
public interface CollectDao {
	/**
	 * @param id
	 * 根据ID查询对象
	 */
	public void getObjById(Long id);
	
	/**
	 * 根据条件查询对象集合
	 * @param collect
	 * @return
	 */
	public List getObjsByObj(Collect collect);
	
	/**
	 * 更新对象
	 * @param collect
	 */
	public void updateObj(Collect collect);
	
	/**
	 * 根据ID删除对象
	 * @param id
	 */
	public void removeObj(Long id);
	
	/**
	 * 添加对象
	 * @param collect
	 */
	public void addObj(Collect collect);
	
	/**
	 * 添加集合对象
	 * @param list
	 */
	public void addObjs(List<Collect> list);
}
