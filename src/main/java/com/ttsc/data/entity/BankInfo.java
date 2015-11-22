package com.ttsc.data.entity;

/**
 * 银行卡信息
 * @author stone.zhu
 *
 */
public class BankInfo {
	//id
	private int id;
	//银行名称
	private String bankName;
	//类型
	private int type;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
