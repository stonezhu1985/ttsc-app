package com.ttsc.data.entity;

/**
 * 用户第三方的账号关联表
 * 
 * @author stone.zhu
 * 
 */
public class UserThirdAccountInfoRelation {
	// id
	private int id;
	// 第三方平台信息的id
	private int thirdAccountInfoId;
	// 用户id
	private int userId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getThirdAccountInfoId() {
		return thirdAccountInfoId;
	}

	public void setThirdAccountInfoId(int thirdAccountInfoId) {
		this.thirdAccountInfoId = thirdAccountInfoId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
