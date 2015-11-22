package com.ttsc.data.service.user;

import java.util.List;

import com.ttsc.data.entity.ThirdPart;

/**
 * 第三方账号
 * @author stone.zhu
 *
 */
public interface ThirdPartService {

	/**
	 * 获得第三方账号
	 * @return
	 */
	public List<ThirdPart> findThirdPart();
	
}
