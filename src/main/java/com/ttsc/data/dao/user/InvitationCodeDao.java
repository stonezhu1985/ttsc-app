package com.ttsc.data.dao.user;

import org.springframework.stereotype.Repository;

import com.ttsc.data.entity.InvitationCode;

/**
 *邀请码dao 
 * @author stone.zhu
 *
 */
@Repository
public interface InvitationCodeDao {
	
	/**
	 * 保存邀请码
	 * @param invitationCode 邀请码
	 * @return
	 */
	public int saveInvitationCode(InvitationCode invitationCode);
	
	/**
	 * 根据邀请码查信息
	 * @param code 邀请码
	 * @return
	 */
	public InvitationCode findInvitationByInvitationCode(String code);
	
	/**
	 * 获取最大的maxCode
	 * @return
	 */
	public InvitationCode findMaxInvitationCode();
}
