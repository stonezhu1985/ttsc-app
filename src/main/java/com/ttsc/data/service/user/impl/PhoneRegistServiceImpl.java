package com.ttsc.data.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.user.PhoneRegistDao;
import com.ttsc.data.entity.PhoneRegistInfo;
import com.ttsc.data.service.user.PhoneRegistService;

@Component("phoneRegistService")
public class PhoneRegistServiceImpl implements PhoneRegistService {

	@Autowired
	private PhoneRegistDao phoneRegistDao;
	
	@Override
	public PhoneRegistInfo findPhoneRegistDaoByPhoneAndRegistCode(
			PhoneRegistInfo phoneRegistInfo) {
		// TODO Auto-generated method stub
		return phoneRegistDao.findPhoneRegistDaoByPhoneAndRegistCode(phoneRegistInfo);
	}

	@Override
	public void savePhoneRegistInfo(PhoneRegistInfo phoneRegistInfo) {
		// TODO Auto-generated method stub
		phoneRegistDao.savePhoneRegistInfo(phoneRegistInfo);
	}

	@Override
	public void updatePhoneRegistStatus(PhoneRegistInfo phoneRegistInfo) {
		// TODO Auto-generated method stub
		phoneRegistDao.updatePhoneRegistStatus(phoneRegistInfo);
	}

	@Override
	public void updatePhoneRegistStatusToInvalid(PhoneRegistInfo phoneRegistInfo) {
		// TODO Auto-generated method stub
		phoneRegistDao.updatePhoneRegistStatusToInvalid(phoneRegistInfo);
	}

	
}
