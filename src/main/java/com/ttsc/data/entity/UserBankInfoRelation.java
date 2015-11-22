package com.ttsc.data.entity;

/**
 * 用户绑定的账号信息
 * @author stone.zhu
 *
 */
public class UserBankInfoRelation {

	private int id;
	//账号信息id
	private int bankInfoId;
	//账号
	private String account;
	//名字
	private String name;
	//银行所在城市
	private String bankCity;
	//银行支行
	private String bankZhiHang;
	//用户id
	private int userId;
	//开户行
	private String openAnaccount;
	
	public String getOpenAnaccount() {
		return openAnaccount;
	}
	
	public void setOpenAnaccount(String openAnaccount) {
		this.openAnaccount = openAnaccount;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBankInfoId() {
		return bankInfoId;
	}
	public void setBankInfoId(int bankInfoId) {
		this.bankInfoId = bankInfoId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBankCity() {
		return bankCity;
	}
	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}
	public String getBankZhiHang() {
		return bankZhiHang;
	}
	public void setBankZhiHang(String bankZhiHang) {
		this.bankZhiHang = bankZhiHang;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
