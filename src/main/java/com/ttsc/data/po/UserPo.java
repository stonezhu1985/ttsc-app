package com.ttsc.data.po;

import java.util.List;

import com.ttsc.data.entity.BankInfo;
import com.ttsc.data.entity.ThirdAccountInfo;
import com.ttsc.data.entity.ThirdPart;
import com.ttsc.data.entity.User;
import com.ttsc.data.entity.UserBankInfoRelation;
/**
 * user的po对象
 * @author stone.zhu
 *
 */
public class UserPo {
	//用户信息
	private User user;
	//银行信息
	private List<BankInfo> bankInfoList;
	//绑定的银行信息
	private List<UserBankInfoRelation> userBankInfoRelationList;
	//第三方账号信息
	private List<ThirdPart> thirdPartList;
	//绑定的第三方账号信息
	private List<ThirdAccountInfo> thirdAccountInfoList;

	public List<ThirdAccountInfo> getThirdAccountInfoList() {
		return thirdAccountInfoList;
	}

	public void setThirdAccountInfoList(List<ThirdAccountInfo> thirdAccountInfoList) {
		this.thirdAccountInfoList = thirdAccountInfoList;
	}

	public List<ThirdPart> getThirdPartList() {
		return thirdPartList;
	}

	public void setThirdPartList(List<ThirdPart> thirdPartList) {
		this.thirdPartList = thirdPartList;
	}

	public List<UserBankInfoRelation> getUserBankInfoRelationList() {
		return userBankInfoRelationList;
	}

	public void setUserBankInfoRelationList(
			List<UserBankInfoRelation> userBankInfoRelationList) {
		this.userBankInfoRelationList = userBankInfoRelationList;
	}

	public List<BankInfo> getBankInfoList() {
		return bankInfoList;
	}

	public void setBankInfoList(List<BankInfo> bankInfoList) {
		this.bankInfoList = bankInfoList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
