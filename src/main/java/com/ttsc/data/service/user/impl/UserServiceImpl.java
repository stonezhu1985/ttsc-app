package com.ttsc.data.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.user.UserDao;
import com.ttsc.data.entity.User;
import com.ttsc.data.service.user.UserService;

@Component("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public int saveUser(User user) {
		// TODO Auto-generated method stub
		int id = userDao.saveUser(user);
		if(id <= 0){
			return -1;
		}
		return id;
	}

	@Override
	public User findUserByPhone(String phone) {
		// TODO Auto-generated method stub
		return userDao.findUserByPhone(phone);
	}

	@Override
	public User findUserByPhoneAndPassWord(User user) {
		// TODO Auto-generated method stub
		return userDao.findUserByPhoneAndPassWord(user);
	}

	@Override
	public void updateUserPassWordByPhone(User user) {
		// TODO Auto-generated method stub
		userDao.updateUserPassWordByPhone(user);
	}

	@Override
	public User findUserBUserId(int id) {
		// TODO Auto-generated method stub
		return userDao.findUserBUserId(id);
	}

	@Override
	public void updateHeaPortraitByUserId(User user) {
		// TODO Auto-generated method stub
		userDao.updateHeaPortraitByUserId(user);
	}

	@Override
	public void updateNameByUserId(User user) {
		// TODO Auto-generated method stub
		userDao.updateNameByUserId(user);
	}

	@Override
	public void updateQqByUserId(User user) {
		// TODO Auto-generated method stub
		userDao.updateQqByUserId(user);
	}

	@Override
	public void updateUserPassportByUserId(User user) {
		// TODO Auto-generated method stub
		userDao.updateUserPassportByUserId(user);
	}

	@Override
	public void updateWeiXinByUserId(User user) {
		// TODO Auto-generated method stub
		userDao.updateWeiXinByUserId(user);
	}

}
