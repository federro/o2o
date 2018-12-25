package com.itianyi.myo2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.itianyi.myo2o.entity.Area;

public class AreaDaoTest extends BaseTest{
	
	@Autowired
	private AreaDao areaDao;
	
	@Ignore
	@Test
	public void testAreaDao(){
		
		Area area = new Area();
		area.setAreaId(52L);
		area.setAreaName("中1苑");
		area.setAreaDesc("中1苑");
		area.setPriority(1);
		area.setCreateTime(new Date());
		area.setLastEditTime(new Date());
		
		int lineNum = areaDao.insertArea(area);
		assertEquals(1, lineNum);
	}
	
	@Test
	public void testQueryArea(){
		List<Area> areaList = areaDao.queryArea();
//		assertEquals(6, areaList.size());
		for(Area area : areaList){
			System.out.println(area.getAreaName());
		}
	}

}
