package com.ttsc.data.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.user.BankInfoDao;
import com.ttsc.data.entity.BankInfo;
import com.ttsc.data.service.user.BankInfoService;

@Component("bankInfoService")
public class BankInfoServiceImpl implements BankInfoService {

	@Autowired
	private BankInfoDao bankInfoDao;
	
	@Override
	public List<BankInfo> findBankInfo() {
		// TODO Auto-generated method stub
		return bankInfoDao.findBankInfo();
	}

}
