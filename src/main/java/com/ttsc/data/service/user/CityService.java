package com.ttsc.data.service.user;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ttsc.data.entity.City;


public interface CityService {

	/**
	 * 根据省份id查询城市
	 * @param id
	 * @return
	 */
	public List<City> findCityInfo(int id);
}
