package com.ttsc.data.service.user;

import com.ttsc.data.entity.UserThirdAccountInfoRelation;

/**
 * 第三方账号与用户的关联service
 * @author stone.zhu
 *
 */
public interface UserThirdAccountInfoRelationService {

	/**
	 * 保存用户的第三方关联关系表
	 * @param userThirdAccountInfoRelation
	 */
	public void saveUserThirdAccountInfoRelation(UserThirdAccountInfoRelation userThirdAccountInfoRelation);
	
	/**
	 * 删除用户的第三方关联关系表
	 * @param id
	 */
	public void deleteUserThirdAccountInfoRelationByThirdAccountInfoId(int id);
}
