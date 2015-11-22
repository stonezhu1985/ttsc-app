package com.ttsc.data.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.user.UserThirdAccountInfoRelationDao;
import com.ttsc.data.entity.UserThirdAccountInfoRelation;
import com.ttsc.data.service.user.UserThirdAccountInfoRelationService;

@Component("userThirdAccountInfoRelationService")
public class UserThirdAccountInfoRelationServiceImpl implements
		UserThirdAccountInfoRelationService {

	@Autowired
	private UserThirdAccountInfoRelationDao userThirdAccountInfoRelationDao;
	
	@Override
	public void saveUserThirdAccountInfoRelation(
			UserThirdAccountInfoRelation userThirdAccountInfoRelation) {
		// TODO Auto-generated method stub
		userThirdAccountInfoRelationDao.saveUserThirdAccountInfoRelation(userThirdAccountInfoRelation);
	}

	@Override
	public void deleteUserThirdAccountInfoRelationByThirdAccountInfoId(int id) {
		// TODO Auto-generated method stub
		userThirdAccountInfoRelationDao.deleteUserThirdAccountInfoRelationByThirdAccountInfoId(id);
	}

}
