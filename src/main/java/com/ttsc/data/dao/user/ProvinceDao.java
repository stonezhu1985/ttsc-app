package com.ttsc.data.dao.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ttsc.data.entity.Province;

/**
 *省份dao 
 * @author stone.zhu
 *
 */
@Repository
public interface ProvinceDao {
	
	/**
	 * 查询省份
	 * @return
	 */
	public List<Province> findProvinceInfo();
	
}
