package com.ttsc.data.dao.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ttsc.data.entity.ThirdPart;

/**
 *第三方平台dao 
 * @author stone.zhu
 *
 */
@Repository
public interface ThirdPartDao {
	/**
	 * 获得第三方账号
	 * @return
	 */
	public List<ThirdPart> findThirdPart();
	
}
