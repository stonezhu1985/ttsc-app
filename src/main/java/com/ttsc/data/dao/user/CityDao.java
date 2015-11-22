package com.ttsc.data.dao.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ttsc.data.entity.City;

/**
 *城市dao 
 * @author stone.zhu
 *
 */
@Repository
public interface CityDao {
	/**
	 * 根据省份id查询城市
	 * @param id
	 * @return
	 */
	public List<City> findCityInfo(int id);
}
