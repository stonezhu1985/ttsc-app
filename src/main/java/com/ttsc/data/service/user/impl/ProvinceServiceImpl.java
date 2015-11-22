package com.ttsc.data.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.user.ProvinceDao;
import com.ttsc.data.entity.Province;
import com.ttsc.data.service.user.ProvinceService;

@Component("provinceService")
public class ProvinceServiceImpl implements ProvinceService {

	@Autowired ProvinceDao provinceDao;
	/**
	 * 查询省份
	 * @return
	 */
	public List<Province> findProvinceInfo() {
		return provinceDao.findProvinceInfo();
	}
}
