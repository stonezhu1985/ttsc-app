package com.ttsc.data.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.user.UserBankInfoRelationDao;
import com.ttsc.data.entity.UserBankInfoRelation;
import com.ttsc.data.service.user.UserBankInfoRelationService;

@Component("userBankInfoRelationService")
public class UserBankInfoRelationServiceImpl implements
		UserBankInfoRelationService {

	@Autowired
	private UserBankInfoRelationDao userBankInfoRelationDao;
	
	@Override
	public List<UserBankInfoRelation> findUserBankInfoRelationByUserId(
			int userId) {
		// TODO Auto-generated method stub
		return userBankInfoRelationDao.findUserBankInfoRelationByUserId(userId);
	}

	@Override
	public void deleteUserBankInfoRelationById(int id) {
		// TODO Auto-generated method stub
		userBankInfoRelationDao.deleteUserBankInfoRelationById(id);
	}

	@Override
	public void saveUserBankInfoRelation(
			UserBankInfoRelation userBankInfoRelation) {
		// TODO Auto-generated method stub
		userBankInfoRelationDao.saveUserBankInfoRelation(userBankInfoRelation);
	}

}
