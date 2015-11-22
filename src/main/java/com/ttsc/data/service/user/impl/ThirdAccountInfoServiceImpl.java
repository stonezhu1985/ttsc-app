package com.ttsc.data.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.user.ThirdAccountInfoDao;
import com.ttsc.data.entity.ThirdAccountInfo;
import com.ttsc.data.service.user.ThirdAccountInfoService;

@Component("thirdAccountInfoService")
public class ThirdAccountInfoServiceImpl implements ThirdAccountInfoService {

	@Autowired
	private ThirdAccountInfoDao thirdAccountInfoDao;
	
	@Override
	public List<ThirdAccountInfo> findThirdAccountInfo(int userId) {
		// TODO Auto-generated method stub
		return thirdAccountInfoDao.findThirdAccountInfo(userId);
	}

	@Override
	public void saveThirdAccountInfo(ThirdAccountInfo thirdAccountInfo) {
		// TODO Auto-generated method stub
		thirdAccountInfoDao.saveThirdAccountInfo(thirdAccountInfo);
	}

	@Override
	public void deleteThirdAccountInfoById(int id) {
		// TODO Auto-generated method stub
		thirdAccountInfoDao.deleteThirdAccountInfoById(id);
	}

	@Override
	public List<ThirdAccountInfo> findThirdAccountInfoById(int id) {
		// TODO Auto-generated method stub
		return thirdAccountInfoDao.findThirdAccountInfoById(id);
	}

	@Override
	public List<ThirdAccountInfo> findThirdAccountInfoByAccount(
			ThirdAccountInfo thirdAccountInfo) {
		// TODO Auto-generated method stub
		return thirdAccountInfoDao.findThirdAccountInfoByAccount(thirdAccountInfo);
	}

}
