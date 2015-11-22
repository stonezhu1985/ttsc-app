package com.ttsc.data.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.user.CityDao;
import com.ttsc.data.entity.City;
import com.ttsc.data.service.user.CityService;

@Component("cityService")
public class CityServiceImpl implements CityService {

	@Autowired CityDao cityDao;
	
	@Override
	public List<City> findCityInfo(int id) {
		// TODO Auto-generated method stub
		return cityDao.findCityInfo(id);
	}

}
