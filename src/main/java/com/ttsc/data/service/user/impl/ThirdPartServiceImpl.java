package com.ttsc.data.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.user.ThirdPartDao;
import com.ttsc.data.entity.ThirdPart;
import com.ttsc.data.service.user.ThirdPartService;

@Component("thirdPartService")
public class ThirdPartServiceImpl implements ThirdPartService {

	@Autowired
	private ThirdPartDao thirdPartDao;
	
	@Override
	public List<ThirdPart> findThirdPart() {
		// TODO Auto-generated method stub
		return thirdPartDao.findThirdPart();
	}

}
