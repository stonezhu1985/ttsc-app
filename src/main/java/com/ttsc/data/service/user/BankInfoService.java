package com.ttsc.data.service.user;

import java.util.List;

import com.ttsc.data.entity.BankInfo;

public interface BankInfoService {

	/**
	 * 获取账号信息
	 * @return
	 */
	public List<BankInfo> findBankInfo();
}
