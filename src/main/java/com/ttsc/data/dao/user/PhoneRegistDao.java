package com.ttsc.data.dao.user;

import org.springframework.stereotype.Repository;

import com.ttsc.data.entity.PhoneRegistInfo;

/**
 *注册码dao 
 * @author stone.zhu
 *
 */
@Repository
public interface PhoneRegistDao {

	/**
	 * 查询手机号，注册码在30分钟以内的
	 * @param phoneRegistInfo
	 * @return
	 */
	public PhoneRegistInfo findPhoneRegistDaoByPhoneAndRegistCode(PhoneRegistInfo phoneRegistInfo);
	
	/**
	 * 保存注册码
	 * @param phoneRegistInfo
	 */
	public void savePhoneRegistInfo(PhoneRegistInfo phoneRegistInfo);
	
	/**
	 * 更新手机注册的信息
	 * @param phoneRegistInfo
	 */
	public void updatePhoneRegistStatus(PhoneRegistInfo phoneRegistInfo);
	
	/**
	 * 更新验证码为无效
	 * @param phoneRegistInfo
	 */
	public void updatePhoneRegistStatusToInvalid(PhoneRegistInfo phoneRegistInfo);
}
