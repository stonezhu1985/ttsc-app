package com.ttsc.data.dao.user;

import org.springframework.stereotype.Repository;

import com.ttsc.data.entity.UserThirdAccountInfoRelation;

/**
 * 第三方账号与用户的关联dao
 * @author stone.zhu
 *
 */
@Repository
public interface UserThirdAccountInfoRelationDao {

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
