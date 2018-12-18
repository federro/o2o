package com.itianyi.myo2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itianyi.myo2o.entity.Award;

public interface AwardDao {
	/**
	 * 获取奖赏的列表
	 * @param award
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<Award> queryAwadList(@Param("awardCondition") Award award,
			@Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize);
	
	/**
	 * 获取奖品的数量
	 * @param awardCondition
	 * @return
	 */
	int queryAwardCount(@Param("awardCondition") Award awardCondition);
	
	/**
	 * 增加奖品
	 * @param award
	 * @return
	 */
	int insertAward(Award award);
	
	/**
	 * 更新奖品
	 * @param award
	 * @return
	 */
	int updateAward(Award award);
	
	/**
	 * 删除奖品
	 * @param award
	 * @return
	 */
	int deleteAward(Award award);
}
