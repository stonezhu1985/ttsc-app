package com.ttsc.data.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.user.InvitationCodeDao;
import com.ttsc.data.entity.InvitationCode;
import com.ttsc.data.service.user.InvitationCodeService;

@Component("invitationCodeService")
public class InvitationCodeServiceImpl implements InvitationCodeService {

	@Autowired
	private InvitationCodeDao invitationCodeDao;
	
	@Override
	public int saveInvitationCode(InvitationCode invitationCode) {
		// TODO Auto-generated method stub
		return invitationCodeDao.saveInvitationCode(invitationCode);
	}

	@Override
	public InvitationCode findInvitationByInvitationCode(String code) {
		// TODO Auto-generated method stub
		return invitationCodeDao.findInvitationByInvitationCode(code);
	}

	@Override
	public InvitationCode findMaxInvitationCode() {
		// TODO Auto-generated method stub
		return invitationCodeDao.findMaxInvitationCode();
	}

}
