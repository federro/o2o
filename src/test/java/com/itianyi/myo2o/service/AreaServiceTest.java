package com.itianyi.myo2o.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.itianyi.myo2o.dao.BaseTest;
import com.itianyi.myo2o.entity.Area;

public class AreaServiceTest extends BaseTest{
	@Autowired
	private AreaService areaService;
	
	@Test
	public void testGetAreaList() throws Exception{
		List<Area> areList = areaService.getAreaList();
		assertEquals("东苑", areList.get(0).getAreaName());
		
	}
}
