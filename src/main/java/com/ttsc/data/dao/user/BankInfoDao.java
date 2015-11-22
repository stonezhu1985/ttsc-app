package com.ttsc.data.dao.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ttsc.data.entity.BankInfo;

/**
 * 银行帐号信息
 * @author stone.zhu
 *
 */
@Repository
public interface BankInfoDao {

	/**
	 * 获取账号信息
	 * @return
	 */
	public List<BankInfo> findBankInfo();
	
}
