package com.ttsc.data.action.user;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import com.ttsc.data.action.BaseController;
import com.ttsc.data.entity.BankInfo;
import com.ttsc.data.entity.City;
import com.ttsc.data.entity.InvitationCode;
import com.ttsc.data.entity.PhoneRegistInfo;
import com.ttsc.data.entity.Province;
import com.ttsc.data.entity.ThirdAccountInfo;
import com.ttsc.data.entity.ThirdPart;
import com.ttsc.data.entity.User;
import com.ttsc.data.entity.UserBankInfoRelation;
import com.ttsc.data.entity.UserThirdAccountInfoRelation;
import com.ttsc.data.po.ProvinceInfo;
import com.ttsc.data.po.UserPo;
import com.ttsc.data.result.BasicResult;
import com.ttsc.data.service.user.BankInfoService;
import com.ttsc.data.service.user.CityService;
import com.ttsc.data.service.user.InvitationCodeService;
import com.ttsc.data.service.user.PhoneRegistService;
import com.ttsc.data.service.user.ProvinceService;
import com.ttsc.data.service.user.ThirdAccountInfoService;
import com.ttsc.data.service.user.ThirdPartService;
import com.ttsc.data.service.user.UserBankInfoRelationService;
import com.ttsc.data.service.user.UserService;
import com.ttsc.data.service.user.UserThirdAccountInfoRelationService;
import com.ttsc.data.util.Constant;
import com.ttsc.data.util.FileUtil;
import com.ttsc.data.util.ReadPropertiesUtil;
import com.ttsc.data.util.SMSSender;
import com.ttsc.data.util.StringUtil;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {


	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);
	
	@Autowired
	private InvitationCodeService invitationCodeService;

	@Autowired
	private UserService userService;

	@Autowired
	private PhoneRegistService phoneRegistService;

	@Autowired
	private BankInfoService bankInfoService;

	@Autowired
	private UserBankInfoRelationService userBankInfoRelationService;

	@Autowired
	private ThirdPartService thirdPartService;

	@Autowired
	private ThirdAccountInfoService thirdAccountInfoService;
	
	@Autowired
	private UserThirdAccountInfoRelationService userThirdAccountInfoRelationService;
	
	@Autowired
	private ProvinceService provinceService;
	
	@Autowired CityService cityService;

	/**
	 * 用户登陆
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "loginPass", method = RequestMethod.POST)
	@ResponseBody
	public BasicResult<UserPo> loginPass(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BasicResult<UserPo> bresult = new BasicResult<UserPo>();
		// 手机号
		String phone = request.getParameter("phone");
		if (StringUtils.isEmpty(phone)) {
			bresult.setCode("1");
			bresult.setMessage("手机号不可以为空!");
			return bresult;
		}
		if (!StringUtil.isMobile(phone)) {
			bresult.setCode("1");
			bresult.setMessage("手机号不正确");
			return bresult;
		}
		// 密码
		String userId = request.getParameter("userId");
		if (StringUtils.isEmpty(userId)) {
			bresult.setCode("1");
			bresult.setMessage("用户信息有误!");
			return bresult;
		}
		User checkUser = userService.findUserBUserId(Integer.parseInt(userId));
		if (checkUser == null) {
			bresult.setCode("1");
			bresult.setMessage("用户名信息有误!");
			return bresult;
		}
		if (!checkUser.getTelephone().equals(phone)) {
			bresult.setCode("1");
			bresult.setMessage("用户名信息有误!");
			return bresult;
		}
		UserPo po = new UserPo();
		po.setUser(checkUser);
		// 获取钱的账号信息
		// 获取银行信息
		List<BankInfo> bankInfoList = bankInfoService.findBankInfo();
		po.setBankInfoList(bankInfoList);
		// 绑定的账号信息
		List<UserBankInfoRelation> userBankInfoRelationList = userBankInfoRelationService
				.findUserBankInfoRelationByUserId(checkUser.getId());
		po.setUserBankInfoRelationList(userBankInfoRelationList);
		// 绑定的第三方账号信息
		// 第三方账号
		List<ThirdPart> thirdPartList = thirdPartService.findThirdPart();
		po.setThirdPartList(thirdPartList);
		// 绑定的第三方账号
		List<ThirdAccountInfo> thirdAccountInfoList = thirdAccountInfoService
				.findThirdAccountInfo(checkUser.getId());
		po.setThirdAccountInfoList(thirdAccountInfoList);
		bresult.setSingleResult(po);
		return bresult;
	}

	/**
	 * 用户登陆
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public BasicResult<UserPo> getUserInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BasicResult<UserPo> bresult = new BasicResult<UserPo>();
		try {
			// 手机号
			String phone = request.getParameter("phone");
			if (StringUtils.isEmpty(phone)) {
				bresult.setCode("1");
				bresult.setMessage("手机号不可以为空!");
				return bresult;
			}
			if (!StringUtil.isMobile(phone)) {
				bresult.setCode("1");
				bresult.setMessage("手机号不正确");
				return bresult;
			}
			// 密码
			String password = request.getParameter("password");
			if (StringUtils.isEmpty(password)) {
				bresult.setCode("1");
				bresult.setMessage("密码不可以为空!");
				return bresult;
			}
			User user = new User();
			user.setPassword(password);
			user.setTelephone(phone);
			User checkUser = userService.findUserByPhoneAndPassWord(user);
			if (checkUser == null) {
				bresult.setCode("1");
				bresult.setMessage("用户名或者密码不正确!");
				return bresult;
			}
			UserPo po = new UserPo();
			po.setUser(checkUser);
			// 获取钱的账号信息
			// 获取银行信息
			List<BankInfo> bankInfoList = bankInfoService.findBankInfo();
			po.setBankInfoList(bankInfoList);
			// 绑定的账号信息
			List<UserBankInfoRelation> userBankInfoRelationList = userBankInfoRelationService
					.findUserBankInfoRelationByUserId(checkUser.getId());
			po.setUserBankInfoRelationList(userBankInfoRelationList);
			// 绑定的第三方账号信息
			// 第三方账号
			List<ThirdPart> thirdPartList = thirdPartService.findThirdPart();
			po.setThirdPartList(thirdPartList);
			// 绑定的第三方账号
			List<ThirdAccountInfo> thirdAccountInfoList = thirdAccountInfoService
					.findThirdAccountInfo(checkUser.getId());
			po.setThirdAccountInfoList(thirdAccountInfoList);
			bresult.setSingleResult(po);
		} catch (Exception e) {
			e.printStackTrace();
			bresult.setCode("1");
			bresult.setMessage("服务器异常，请稍后再试!");
			return bresult;
		}
		return bresult;
	}

	/**
	 * 获取验证码
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getValidateCode", method = RequestMethod.GET)
	@ResponseBody
	public BasicResult<String> getValidateCode(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BasicResult<String> bresult = new BasicResult<String>();
		String code = "0";
		try {
			String phone = request.getParameter("phone");
			if (StringUtils.isEmpty(phone)) {
				bresult.setCode("1");
				bresult.setMessage("手机号不可以为空!");
				return bresult;
			}

			if (!StringUtil.isMobile(phone)) {
				bresult.setCode("1");
				bresult.setMessage("手机号不正确");
				return bresult;
			}

			code = StringUtil.getRomd();
			// 类型0：注册，1：忘记密码
			String type = request.getParameter("type");
			if (type.equals("0")) {
				// 校验用户是否已被注册
				User user = userService.findUserByPhone(phone);
				if (user != null) {
					bresult.setCode("1");
					bresult.setMessage("手机号已注册，请直接登录!");
					return bresult;
				}
			}
			PhoneRegistInfo phoneRegistInfo = new PhoneRegistInfo();
			phoneRegistInfo.setRegistCode(code);
			phoneRegistInfo.setTelephone(phone);
			// 更新之前的验证码为无效
			phoneRegistService
					.updatePhoneRegistStatusToInvalid(phoneRegistInfo);
			// 保存新的验证码
			phoneRegistService.savePhoneRegistInfo(phoneRegistInfo);
			Map<String,String> map = SMSSender.sendMailProperty(code,phone);
			//发送短信
			SMSSender.urlPost("", map);
		} catch (Exception e) {
			e.printStackTrace();
			bresult.setCode("1");
			bresult.setMessage("服务器异常，请稍后再试!");
			return bresult;
		}
		bresult.setSingleResult(code);
		return bresult;
	}

	/**
	 * 用户注册
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "userRegistered", method = RequestMethod.POST)
	@ResponseBody
	public BasicResult<Object> userRegistered(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BasicResult<Object> bresult = new BasicResult<Object>();
		// 手机号
		String phone = request.getParameter("phone");
		if (StringUtils.isEmpty(phone)) {
			bresult.setCode("1");
			bresult.setMessage("手机号不可以为空!");
			return bresult;
		}

		if (!StringUtil.isMobile(phone)) {
			bresult.setCode("1");
			bresult.setMessage("手机号不正确");
			return bresult;
		}

		// 密码
		String password = request.getParameter("password");
		if (StringUtils.isEmpty(password)) {
			bresult.setCode("1");
			bresult.setMessage("密码不可以为空!");
			return bresult;
		}
		// 验证码
		String registCode = request.getParameter("registCode");
		if (StringUtils.isEmpty(registCode)) {
			bresult.setCode("1");
			bresult.setMessage("注册码不可以为空!");
			return bresult;
		}
		// 邀请码
		String invitationCode = request.getParameter("invitationCode");
		if (StringUtils.isEmpty(invitationCode)) {
			invitationCode = "0";
		}
		try {
			// 校验用户是否已被注册
			User user = userService.findUserByPhone(phone);
			if (user != null) {
				bresult.setCode("1");
				bresult.setMessage("手机号已注册，请直接登录!");
				return bresult;
			}
			// 有邀请码
			int intInvitationCode = Integer.parseInt(invitationCode);
			if (intInvitationCode > 0) {
				// 1：查看邀请码是否真确
				InvitationCode invitationCodeObject = invitationCodeService
						.findInvitationByInvitationCode(intInvitationCode + "");
				if (invitationCodeObject == null) {
					bresult.setCode("1");
					bresult.setMessage("邀请码不正确！");
					return bresult;
				}
				// 邀请码正确，冲三元到帐户（后面去补）
			}
			// 校验验证码
			PhoneRegistInfo phoneRegistInfo = new PhoneRegistInfo();
			phoneRegistInfo.setRegistCode(registCode);
			phoneRegistInfo.setTelephone(phone);
			phoneRegistInfo.setStatus(Constant.status_unregist);
			PhoneRegistInfo info = phoneRegistService
					.findPhoneRegistDaoByPhoneAndRegistCode(phoneRegistInfo);
			if (info == null) {
				bresult.setCode("1");
				bresult.setMessage("验证码不正确！");
				return bresult;
			}
			User users = new User();
			users.setPassword(password);
			users.setTelephone(phone);

			if (intInvitationCode > 0) {
				users.setInvitationCode(intInvitationCode);
			}
			userService.saveUser(users);
			// 更新注册码为已注册
			PhoneRegistInfo newPhoneRegistInfo = new PhoneRegistInfo();
			newPhoneRegistInfo.setTelephone(phone);
			newPhoneRegistInfo.setRegistCode(registCode);
			phoneRegistService.updatePhoneRegistStatus(phoneRegistInfo);

			// 生成自己的邀请码
			InvitationCode myInvitationCode = invitationCodeService
					.findMaxInvitationCode();
			int myIntInvitationCode = Constant.minInvitationCode;
			if (myInvitationCode != null) {
				myIntInvitationCode = Integer.parseInt(myInvitationCode
						.getCode());
				myIntInvitationCode++;
			}
			// 保存自己的邀请码
			myInvitationCode = new InvitationCode();
			myInvitationCode.setCode(myIntInvitationCode + "");
			myInvitationCode.setUserId(users.getId());
			myInvitationCode.setType(0);
			invitationCodeService.saveInvitationCode(myInvitationCode);
			UserPo po = new UserPo();
			po.setUser(users);
			bresult.setSingleResult(po);
		} catch (Exception e) {
			e.printStackTrace();
			bresult.setCode("1");
			bresult.setMessage("服务器异常，请稍后再试!");
			return bresult;
		}
		return bresult;
	}

	/**
	 * 忘记密码
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "userForgetPassWord", method = RequestMethod.POST)
	@ResponseBody
	public BasicResult<String> userForgetPassWord(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BasicResult<String> bresult = new BasicResult<String>();
		try {
			// 手机号
			String phone = request.getParameter("phone");
			if (StringUtils.isEmpty(phone)) {
				bresult.setCode("1");
				bresult.setMessage("手机号不可以为空!");
				return bresult;
			}
			if (!StringUtil.isMobile(phone)) {
				bresult.setCode("1");
				bresult.setMessage("手机号不正确");
				return bresult;
			}
			// 验研码
			String registCode = request.getParameter("registCode");
			if (StringUtils.isEmpty(registCode)) {
				bresult.setCode("1");
				bresult.setMessage("校验码不可以为空!");
				return bresult;
			}
			// 校验证码
			PhoneRegistInfo phoneRegistInfo = new PhoneRegistInfo();
			phoneRegistInfo.setRegistCode(registCode);
			phoneRegistInfo.setTelephone(phone);
			phoneRegistInfo.setStatus(Constant.status_unregist);
			PhoneRegistInfo info = phoneRegistService
					.findPhoneRegistDaoByPhoneAndRegistCode(phoneRegistInfo);
			if (info == null) {
				bresult.setCode("1");
				bresult.setMessage("验证码不正确！");
				return bresult;
			}
			User userCheck = userService.findUserByPhone(phone);
			if (userCheck == null) {
				bresult.setCode("1");
				bresult.setMessage("用户没有注册,不能修改密码！");
				return bresult;
			}
			// 密码
			String newPassword = request.getParameter("newPassword");
			if (StringUtils.isEmpty(newPassword)) {
				bresult.setCode("1");
				bresult.setMessage("新密码不可以为空!");
				return bresult;
			}
			User user = new User();
			user.setTelephone(phone);
			user.setPassword(newPassword);
			// 更新密码
			userService.updateUserPassWordByPhone(user);
			bresult.setSingleResult("成功");
		} catch (Exception e) {
			e.printStackTrace();
			bresult.setCode("1");
			bresult.setMessage("服务器异常，请稍后再试!");
			return bresult;
		}
		return bresult;
	}

	/**
	 * 忘记密码
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "updatePassWord", method = RequestMethod.POST)
	@ResponseBody
	public BasicResult<String> updatePassWord(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BasicResult<String> bresult = new BasicResult<String>();
		try {
			// 手机号
			String phone = request.getParameter("phone");
			if (StringUtils.isEmpty(phone)) {
				bresult.setCode("1");
				bresult.setMessage("手机号不可以为空!");
				return bresult;
			}
			if (!StringUtil.isMobile(phone)) {
				bresult.setCode("1");
				bresult.setMessage("手机号不正确");
				return bresult;
			}
			// 密码
			String oldPassword = request.getParameter("oldPassword");
			if (StringUtils.isEmpty(oldPassword)) {
				bresult.setCode("1");
				bresult.setMessage("旧密码不可以为空!");
				return bresult;
			}
			// 密码
			String newPassword = request.getParameter("newPassword");
			if (StringUtils.isEmpty(newPassword)) {
				bresult.setCode("1");
				bresult.setMessage("新密码不可以为空!");
				return bresult;
			}
			User user = new User();
			user.setTelephone(phone);
			user.setPassword(oldPassword);
			User findUser = userService.findUserByPhoneAndPassWord(user);
			if (findUser == null) {
				bresult.setCode("1");
				bresult.setMessage("用户名或者密码不正确!");
				return bresult;
			}
			user.setPassword(newPassword);
			// 更新密码
			userService.updateUserPassWordByPhone(user);
		} catch (Exception e) {
			e.printStackTrace();
			bresult.setCode("1");
			bresult.setMessage("服务器异常，请稍后再试!");
			return bresult;
		}
		return bresult;
	}

	/**
	 * 修改用户的身份证信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "updateUserInfoPassPort", method = RequestMethod.POST)
	@ResponseBody
	public BasicResult<List<String> > updateUserInfoPassPort(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BasicResult<List<String> > bresult = new BasicResult<List<String> >();
		// 文件列表
		List<String> fileList = new ArrayList<String>();
		try {
			// 用户id
			String userId = request.getParameter("userId");
			// 真实姓名
			String realName = request.getParameter("realName");
			// 身份证号码
			String passPostNum = request.getParameter("passPostNum");
			// 性别
			String sex = request.getParameter("sex");
			logger.info(userId+";"+realName+";"+passPostNum+";"+sex);
			//创建一个通用的多部分解析器  
	        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
	        logger.info("multipartResolver.isMultipart(request):"+multipartResolver.isMultipart(request));
	        //判断 request 是否有文件上传,即多部分请求  
	        if(multipartResolver.isMultipart(request)){  
	            //转换成多部分request    
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
	            //取得request中的所有文件名  
	            Iterator<String> iter = multiRequest.getFileNames(); 
	            while(iter.hasNext()){  
	                //取得上传文件  
	                MultipartFile file = multiRequest.getFile(iter.next()); 
	                if(file != null){  
	                    //取得当前上传文件的文件名称  
	                    String myFileName = file.getOriginalFilename(); 
	                    logger.info("myFileName:"+myFileName);
	                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
	                    if(myFileName.trim() !=""){  
	    					String realPath = ReadPropertiesUtil.get("headPhotoFilePath")+"/"+Constant.mulu;
							// 真实名称
							String fixFileName = FileUtil.getFx(myFileName);
							// 文件名称
							String newName = UUID.randomUUID() + "." + fixFileName;
							File localFile = new File(realPath, newName);
							// 如果文件夹不存在则创建
							if (!localFile.exists() && !localFile.isDirectory()) {
								localFile.mkdirs();
							}  
	                        file.transferTo(localFile);  
	                        fileList.add(Constant.mulu + "/" + newName);
	                    }  
	                }  
	            }
	        }
			User user = new User();
			user.setId(Integer.parseInt(userId));
			user.setRealName(realName);
			user.setSex(Integer.parseInt(sex));
			user.setPassPostNum(passPostNum);
			user.setHandPassPortPhoto(fileList.get(1));
			user.setPassPortPhoto(fileList.get(0));
			user.setIsValidate(0);
			userService.updateUserPassportByUserId(user);
		} catch (Exception e) {
			e.printStackTrace();
			bresult.setCode("1");
			bresult.setMessage("服务器异常，请稍后再试!");
			return bresult;
		}
		bresult.setSingleResult(fileList);
		return bresult;
	}

	/**
	 * 修改用户的昵称
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "updateUserName", method = RequestMethod.POST)
	@ResponseBody
	public BasicResult<String> updateUserName(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BasicResult<String> bresult = new BasicResult<String>();
		try {
			// 用户id
			String userId = request.getParameter("userId");
			// 真实姓名
			String name = request.getParameter("name");
			User user = new User();
			user.setId(Integer.parseInt(userId));
			user.setName(name);
			userService.updateNameByUserId(user);
		} catch (Exception e) {
			e.printStackTrace();
			bresult.setCode("1");
			bresult.setMessage("服务器异常，请稍后再试!");
			return bresult;
		}
		return bresult;
	}
	
	/**
	 * 修改用户的QQ
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "updateUserQQ", method = RequestMethod.POST)
	@ResponseBody
	public BasicResult<String> updateUserQQ(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BasicResult<String> bresult = new BasicResult<String>();
		try {
			// 用户id
			String userId = request.getParameter("userId");
			// 真实姓名
			String qq = request.getParameter("qq");
			User user = new User();
			user.setId(Integer.parseInt(userId));
			user.setQq(qq);
			userService.updateQqByUserId(user);
		} catch (Exception e) {
			e.printStackTrace();
			bresult.setCode("1");
			bresult.setMessage("服务器异常，请稍后再试!");
			return bresult;
		}
		return bresult;
	}
	
	
	/**
	 * 修改用户的微信
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "updateUserWEIXIN", method = RequestMethod.POST)
	@ResponseBody
	public BasicResult<String> updateUserWEIXIN(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BasicResult<String> bresult = new BasicResult<String>();
		try {
			// 用户id
			String userId = request.getParameter("userId");
			// 真实姓名
			String weixin = request.getParameter("weixin");
			User user = new User();
			user.setId(Integer.parseInt(userId));
			user.setWeixin(weixin);
			userService.updateWeiXinByUserId(user);
		} catch (Exception e) {
			e.printStackTrace();
			bresult.setCode("1");
			bresult.setMessage("服务器异常，请稍后再试!");
			return bresult;
		}
		return bresult;
	}
	
	@RequestMapping(value = "updateUserHeaPortrait", method = RequestMethod.POST)
	@ResponseBody
	public BasicResult<List<String>> updateUserHeaPortrait(HttpServletRequest request,HttpServletResponse response) throws Exception {
		BasicResult<List<String>> bresult = new BasicResult<List<String>>();
		// 文件列表
		List<String> fileList = new ArrayList<String>();
		try {
			// 用户id
			String userId = request.getParameter("userId");		
			//创建一个通用的多部分解析器  
	        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
	        //判断 request 是否有文件上传,即多部分请求  
	        if(multipartResolver.isMultipart(request)){  
	            //转换成多部分request    
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
	            //取得request中的所有文件名  
	            Iterator<String> iter = multiRequest.getFileNames();  	            
	            while(iter.hasNext()){  
	                //取得上传文件  
	                MultipartFile file = multiRequest.getFile(iter.next()); 
	                if(file != null){  
	                    //取得当前上传文件的文件名称  
	                    String myFileName = file.getOriginalFilename();  
	                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
	                    if(myFileName.trim() !=""){  
	    					String realPath = ReadPropertiesUtil.get("headPhotoFilePath")+"/"+Constant.mulu;
							// 真实名称
							String fixFileName = FileUtil.getFx(myFileName);
							// 文件名称
							String newName = UUID.randomUUID() + "." + fixFileName;
							File localFile = new File(realPath, newName);
							// 如果文件夹不存在则创建
							if (!localFile.exists() && !localFile.isDirectory()) {
								localFile.mkdirs();
							}  
	                        file.transferTo(localFile);  
	                        fileList.add(Constant.mulu + "/" + newName);
	                    }  
	                }  
	            }
	        }
			User user = new User();
			user.setId(Integer.parseInt(userId));
			user.setHeaPortrait(fileList.get(0));
			userService.updateHeaPortraitByUserId(user);
		} catch (Exception e) {
			e.printStackTrace();
			bresult.setCode("1");
			bresult.setMessage("服务器异常，请稍后再试!");
			return bresult;
		}
		bresult.setSingleResult(fileList);
		return bresult;
	}
	

	/**
	 * 保存用户绑定的银行卡
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "saveUserBankRelationInfo", method = RequestMethod.POST)
	@ResponseBody
	public BasicResult<String> saveUserBankRelationInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BasicResult<String> bresult = new BasicResult<String>();
		UserBankInfoRelation userBankInfoRelation = new UserBankInfoRelation();
		try {
			// 用户id
			String userId = request.getParameter("userId");
			//姓名
			String name = request.getParameter("name");
			//账号
			String account = request.getParameter("account");
			//类型1,支付宝；2,财付通；3,银行卡
			String type = request.getParameter("type");
			//bankInfo的id
			String bankInfoId = request.getParameter("bankInfoId");
			userBankInfoRelation.setUserId(Integer.parseInt(userId));
			userBankInfoRelation.setName(name);
			userBankInfoRelation.setAccount(account);
			userBankInfoRelation.setBankInfoId(Integer.parseInt(bankInfoId));
			//if(type.equals(Constant.bankTypeBank)){
				//开户行
				String openAnaccount =request.getParameter("openAnaccount");
				//开户城市
				String bankCity = request.getParameter("bankCity");
				//开户行支行
				String bankZhihang = request.getParameter("bankZhihang");
				userBankInfoRelation.setOpenAnaccount(openAnaccount);
				userBankInfoRelation.setBankCity(bankCity);
				userBankInfoRelation.setBankZhiHang(bankZhihang);
			//}
			userBankInfoRelationService.saveUserBankInfoRelation(userBankInfoRelation);
		} catch (Exception e) {
			e.printStackTrace();
			bresult.setCode("1");
			bresult.setMessage("服务器异常，请稍后再试!");
			return bresult;
		}
		bresult.setSingleResult(userBankInfoRelation.getId()+"");
		return bresult;
	}
	
	/**
	 * 保存用户绑定的银行卡
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "deleteUserBankRelationInfo", method = RequestMethod.POST)
	@ResponseBody
	public BasicResult<String> deleteUserBankRelationInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BasicResult<String> bresult = new BasicResult<String>();
		UserBankInfoRelation userBankInfoRelation = new UserBankInfoRelation();
		try {
			// 用户id
			String userId = request.getParameter("userId");
			String userBankRelationInfoId = request.getParameter("userBankRelationInfoId");
			userBankInfoRelationService.deleteUserBankInfoRelationById(Integer.parseInt(userBankRelationInfoId));
		} catch (Exception e) {
			e.printStackTrace();
			bresult.setCode("1");
			bresult.setMessage("服务器异常，请稍后再试!");
			return bresult;
		}
		bresult.setSingleResult(userBankInfoRelation.getId()+"");
		return bresult;
	}
	
	/**
	 * 修改用户的第三方账号信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "saveUserThirdPartInfo", method = RequestMethod.POST)
	@ResponseBody
	public BasicResult<Map<String,Object>> saveUserThirdPartInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BasicResult<Map<String,Object>> bresult = new BasicResult<Map<String,Object>>();
		List<String> fileList = new ArrayList<String>();
		ThirdAccountInfo thirdAccountInfo = new ThirdAccountInfo();
		try {
			// 用户id
			String userId = request.getParameter("userId");
			//账号
			String account = request.getParameter("account");
			//收货人
			String consigneePerson = request.getParameter("consigneePerson");
			//收货手机号码
			String telephone = request.getParameter("telephone");
			//收货地址
			String address = request.getParameter("address");
			//第三方账号
			String thirdPartId = request.getParameter("thirdPartId");
			//第三方账号的level
			String thirdLevelInfoId = request.getParameter("thirdLevelInfoId");
			// 文件列表
			//城市
			String city = request.getParameter("city");
			//省份
			String province = request.getParameter("province");
			//校验账号是否已被绑定
			ThirdAccountInfo ta = new ThirdAccountInfo();
			ta.setAccount(account);
			ta.setThirdPartId(Integer.parseInt(thirdPartId));
			List<ThirdAccountInfo> thirdAccountInfoList = thirdAccountInfoService.findThirdAccountInfoByAccount(thirdAccountInfo);
			if(!CollectionUtils.isEmpty(thirdAccountInfoList)){
				bresult.setCode("1");
				bresult.setMessage("账号："+account+"已绑定，不可重复绑定!");
				return bresult;
			}
			logger.info(userId+";"+account+";"+consigneePerson+";"+telephone+";"+address+";"+thirdPartId+";"+thirdLevelInfoId+";"+city);

//			for (MultipartFile myfile : myfiles) {
//				if (myfile.isEmpty()) {
//					bresult.setCode("1");
//					bresult.setMessage("文件未上传!");
//					return bresult;
//				} else {
//					String realPath = ReadPropertiesUtil
//							.get("headPhotoFilePath");
//					// 真实名称
//					String fileRealName = myfile.getOriginalFilename();
//					String fixFileName = FileUtil.getFx(fileRealName);
//					// 文件名称
//					String newName = UUID.randomUUID() + "." + fixFileName;
//					File localFile = new File(realPath, newName);
//					// 如果文件夹不存在则创建
//					if (!localFile.exists() && !localFile.isDirectory()) {
//						localFile.mkdirs();
//					}
//					myfile.transferTo(localFile);
//					fileList.add(realPath + "/" + newName);
//				}
//			}
			
			//创建一个通用的多部分解析器  
	        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
	        //判断 request 是否有文件上传,即多部分请求  
	        if(multipartResolver.isMultipart(request)){  
	            //转换成多部分request    
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
	            //取得request中的所有文件名  
	            Iterator<String> iter = multiRequest.getFileNames();  	            
	            while(iter.hasNext()){  
	                //取得上传文件  
	                MultipartFile file = multiRequest.getFile(iter.next()); 
	                if(file != null){  
	                    //取得当前上传文件的文件名称  
	                    String myFileName = file.getOriginalFilename();  
	                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
	                    if(myFileName.trim() !=""){  
	    					String realPath = ReadPropertiesUtil.get("headPhotoFilePath")+"/"+Constant.mulu;
							// 真实名称
							String fixFileName = FileUtil.getFx(myFileName);
							// 文件名称
							String newName = UUID.randomUUID() + "." + fixFileName;
							File localFile = new File(realPath, newName);
							// 如果文件夹不存在则创建
							if (!localFile.exists() && !localFile.isDirectory()) {
								localFile.mkdirs();
							}  
	                        file.transferTo(localFile);  
	                        fileList.add(Constant.mulu + "/" + newName);
	                    }  
	                }  
	            }
	        }

			thirdAccountInfo.setAccount(account);
			thirdAccountInfo.setAddress(address);
			thirdAccountInfo.setConsigneePerson(consigneePerson);
			thirdAccountInfo.setTelephone(telephone);
			int intThirdPartId = Integer.parseInt(thirdPartId);
			thirdAccountInfo.setThirdPartId(intThirdPartId);
			thirdAccountInfo.setThirdLevelInfoId(Integer.parseInt(thirdLevelInfoId));
			thirdAccountInfo.setCity(Integer.parseInt(city));
			thirdAccountInfo.setProvince(Integer.parseInt(province));
			if(intThirdPartId == 1){
				thirdAccountInfo.setReputationPhoto(fileList.get(0));
				thirdAccountInfo.setRealNamePhoto(fileList.get(1));
				if(fileList.size() > 2){
					thirdAccountInfo.setFlowersPhoto(fileList.get(2));
				}
			}else{
				thirdAccountInfo.setRealNamePhoto(fileList.get(0));
				thirdAccountInfo.setReputationPhoto(fileList.get(1));
			}
			thirdAccountInfoService.saveThirdAccountInfo(thirdAccountInfo);
			UserThirdAccountInfoRelation userThirdAccountInfoRelation = new UserThirdAccountInfoRelation();
			userThirdAccountInfoRelation.setThirdAccountInfoId(thirdAccountInfo.getId());
			userThirdAccountInfoRelation.setUserId(Integer.parseInt(userId));
			userThirdAccountInfoRelationService.saveUserThirdAccountInfoRelation(userThirdAccountInfoRelation);
		} catch (Exception e) {
			e.printStackTrace();
			bresult.setCode("1");
			bresult.setMessage("服务器异常，请稍后再试!");
			return bresult;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("photos", fileList);
		map.put("thirdAccountInfoId", thirdAccountInfo.getId()+"");
		bresult.setSingleResult(map);
		return bresult;
	}
	
	
	/**
	 * 修改用户的第三方账号信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "deleteUserThirdPartInfo", method = RequestMethod.POST)
	@ResponseBody
	public BasicResult<String> deleteUserThirdPartInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BasicResult<String> bresult = new BasicResult<String>();
		try {
			// 用户id
			String userId = request.getParameter("userId");
			//id 第三方账号信息id
			String id = request.getParameter("id");
			List<ThirdAccountInfo> list = thirdAccountInfoService.findThirdAccountInfoById(Integer.parseInt(id));
			if(CollectionUtils.isEmpty(list)){
				bresult.setCode("1");
				bresult.setMessage("信息有误,第三方账号id是:"+id);
				return bresult;
			}
			thirdAccountInfoService.deleteThirdAccountInfoById(Integer.parseInt(id));
			//删除userThirdAccountInfoRelation
			userThirdAccountInfoRelationService.deleteUserThirdAccountInfoRelationByThirdAccountInfoId(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
			bresult.setCode("1");
			bresult.setMessage("服务器异常，请稍后再试!");
			return bresult;
		}
		return bresult;
	}
	
	
	/**
	 * 修改用户的第三方账号信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getProvice", method = RequestMethod.GET)
	@ResponseBody
	public BasicResult<List<ProvinceInfo>> getProvice(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BasicResult<List<ProvinceInfo>> bresult = new BasicResult<List<ProvinceInfo>>();
		List<ProvinceInfo> pList = new ArrayList<ProvinceInfo>();
		try {
			// 用户id
			String userId = request.getParameter("userId");
			List<Province> list = provinceService.findProvinceInfo();
			for(int i = 0; i < list.size(); i++){
				Province p = list.get(i);
				List<City> cityList = cityService.findCityInfo(p.getId());
				ProvinceInfo ps = new ProvinceInfo();
				ps.setCityList(cityList);
				ps.setId(p.getId());
				ps.setCityName(p.getCityName());
				pList.add(ps);
			}
		} catch (Exception e) {
			e.printStackTrace();
			bresult.setCode("1");
			bresult.setMessage("服务器异常，请稍后再试!");
			return bresult;
		}
		bresult.setSingleResult(pList);
		return bresult;
	}
}
