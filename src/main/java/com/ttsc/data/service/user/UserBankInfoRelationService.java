package com.ttsc.data.service.user;

import java.util.List;

import com.ttsc.data.entity.UserBankInfoRelation;

/**
 *  用户绑定的收钱的账号信息
 * @author stone.zhu
 *
 */
public interface UserBankInfoRelationService {

	
	/**
	 * 查询用户保存的银行帐号信息
	 * @param userId
	 * @return
	 */
	public List<UserBankInfoRelation> findUserBankInfoRelationByUserId(int userId);
	
	/**
	 * 删除用户和绑定的银行卡的关系
	 * @param id
	 */
	public void deleteUserBankInfoRelationById(int id);
	
	/**
	 * 保存用户与银行卡的关系
	 * @param userBankInfoRelation
	 */
	public void saveUserBankInfoRelation(UserBankInfoRelation userBankInfoRelation);
}
