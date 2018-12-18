package com.itianyi.myo2o.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.itianyi.myo2o.dao.AreaDao;
import com.itianyi.myo2o.dto.AreaExecution;
import com.itianyi.myo2o.entity.Area;
import com.itianyi.myo2o.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService{

	@Autowired
	private AreaDao areaDao;
	@Override
	public List<Area> getAreaList() throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		return areaDao.queryArea();
	}

	@Override
	public AreaExecution addArea(Area area) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AreaExecution modifyArea(Area area) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AreaExecution removeArea(long areaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AreaExecution removeAreaList(List<Long> areaIdList) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
