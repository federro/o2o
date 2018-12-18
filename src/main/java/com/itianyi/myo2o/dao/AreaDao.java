package com.itianyi.myo2o.dao;

import java.util.List;

import com.itianyi.myo2o.entity.Area;

public interface AreaDao {
	/**
	 * 列出区域列表
	 * @return
	 */
	List<Area> queryArea();
	
	/**
	 * 插入区域列表
	 * @param area
	 * @return 返回受影响的行数
	 */
	int insertArea(Area area);
	
	/**
	 * 更新区域列表
	 * @param area
	 * @return 返回受影响的行数
	 */
	int updateArea(Area area);
	
	/**
	 * 删除区域列表
	 * @param area
	 * @return 返回受影响的行数
	 */
	int deleteArea(Area area);
	
	/**
	 * 批量删除区域列表
	 * @param areaList
	 * @return 返回受影响的行数
	 */
	int batchDeleteArea(List<Long> areaList);
}
