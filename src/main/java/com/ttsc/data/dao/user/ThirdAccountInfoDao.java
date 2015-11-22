package com.ttsc.data.dao.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ttsc.data.entity.ThirdAccountInfo;

/**
 *第三方账号dao 
 * @author stone.zhu
 *
 */
@Repository
public interface ThirdAccountInfoDao {
	/**
	 * 获取绑定的账号信息
	 * @param userId
	 * @return
	 */
	public List<ThirdAccountInfo> findThirdAccountInfo(int userId);
	
	/**
	 * 保存第三方账号
	 * @param thirdAccountInfo
	 */
	public void saveThirdAccountInfo(ThirdAccountInfo thirdAccountInfo);
	
	/**
	 * 根据id删除第三方账号信息
	 * @param id
	 */
	public void deleteThirdAccountInfoById(int id);
	
	/**
	 * 根据id获取绑定的账号信息
	 * @param userId
	 * @return
	 */
	public List<ThirdAccountInfo> findThirdAccountInfoById(int id);
	
	
	/**
	 * 根据账号查询
	 * @param account
	 * @return
	 */
	public List<ThirdAccountInfo> findThirdAccountInfoByAccount(ThirdAccountInfo thirdAccountInfo);
	
}
