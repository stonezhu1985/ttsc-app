package com.ttsc.data.service.user;

import com.ttsc.data.entity.User;

/**
 * 用户操作service
 * @author stone.zhu
 *
 */
public interface UserService {
	
	/**
	 * 保存用户信息
	 * @param user
	 * @return
	 */
	public int saveUser(User user);
	
	/**
	 * 根据手机号码拿用户
	 * @param phone
	 * @return
	 */
	public User findUserByPhone(String phone);
	
	/**
	 * 根据用户名和密码查询用户
	 * @param user
	 * @return
	 */
	public User findUserByPhoneAndPassWord(User user);
	
	/**
	 * 更改密码
	 * @param user
	 */
	public void updateUserPassWordByPhone(User user);
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	public User findUserBUserId(int id);
	
	/**
	 * 更新用户的头像
	 * @param user
	 */
	public void updateHeaPortraitByUserId(User user);
	
	/**
	 * 更新用户昵称
	 * @param user
	 */
	public void updateNameByUserId(User user);
	
	/**
	 * 更新qq
	 * @param user
	 */
	public void updateQqByUserId(User user);
	
	/**
	 * 更新身份证信息
	 * @param user
	 */
	public void updateUserPassportByUserId(User user);
	
	/**
	 * 更新微信
	 * @param user
	 */
	public void updateWeiXinByUserId(User user);
}
