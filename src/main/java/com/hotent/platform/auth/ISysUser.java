package com.hotent.platform.auth;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * 系统用户接口 
 * 2012-12-07 heyifan
 */
public interface ISysUser extends UserDetails{
	/**
	 * 设置账号
	 * @param account
	 */
	public void setAccount(String account);
	
	/**
	 * 获取账号
	 * 
	 * @return 账号
	 */
	public String getAccount();
	/**
	 * 设置短账号
	 * @param account
	 */
	public void setShortAccount(String shortAccount);
	
	/**
	 * 获取短账号
	 * 
	 * @return 账号
	 */
	public String getShortAccount();
	/**
	 * 设置密码
	 * @param enPassword 加密的密码字符串
	 */
	public void setPassword(String enPassword);

	/**
	 * 获取用户ID
	 * 
	 * @return 用户ID
	 */
	public Long getUserId();
	
	/**
	 * 设置用户ID
	 * @param userId
	 */
	public void setUserId(Long userId);

	/**
	 * 设置用户名称
	 * @param fullName
	 */
	public void setFullname(String fullName);
	
	/**
	 * 获取用户名称
	 * @return 用户名称
	 */
	public String getFullname();

	/**
	 * 获取账号是否过期
	 * @return
	 */
	public Short getIsExpired();
	
	/**
	 * 设置账号是否过期
	 * @param expired
	 */
	public void setIsExpired(Short expired);
	
	/**
	 * 获取账号是否锁定
	 * @return
	 */
	public Short getIsLock();
	
	/**
	 * 设置账号是否锁定
	 * @param lock
	 */
	public void setIsLock(Short lock);
	
	/**
	 * 获取账号的创建时间
	 * @return
	 */
	public java.util.Date getCreatetime();
	
	/**
	 * 设置用户的手机号码
	 * @param mobile
	 */
	public void setMobile(String mobile);
	
	/**
	 * 获取用户的手机号
	 * @return
	 */
	public String getMobile();

	/**
	 * 设置电话号码
	 * @param phone
	 */
	public void setPhone(String phone);
	
	/**
	 * 获取用户的电话号码
	 * @return
	 */
	public String getPhone();
	
	/**
	 * 设置email
	 */
	public void setEmail(String email);
	
	/**
	 * 获取用户的Email
	 * @return
	 */
	public String getEmail();
	
	/**
	 * 设置账号的状态
	 * @param status
	 */
	public void setStatus(Short status);
	
	/**
	 * 获取账号的状态
	 * @return
	 */
	public Short getStatus();
	
	/**
	 * 获取用户的来源类型
	 * @return
	 */
	public Short getFromType();
	
	/**
	 * 设置用户的来源类型
	 * @param fromType
	 */
	public void setFromType(Short fromType);
	
	/**
	 * 获取用户图片
	 * @return
	 */
	public String getPicture();
	
	/**
	 * 获取用户的组织ID
	 * @return
	 */
	public Long getUserOrgId();

	/**
	 * 设置用户的组织ID
	 * @param userOrgId
	 */
	public void setUserOrgId(Long userOrgId);
	
	public Long getOrgSn();
	public void setOrgSn(Long orgSn);
	
	public Long getOrgId();
	public void setOrgId(Long orgId);
	/**
	 * 获取用户的组织名
	 * @return
	 */
	public String getOrgName();

	/**
	 * 设置用户的组织名
	 * @param orgName
	 */
	public void setOrgName(String orgName);
	
	/**
	 * 返回所属组织机构类型
	 * @return
	 */
	public String getOrgType();

	public void setOrgType(String orgType);

	/**
	 * 返回所属类型Id
	 * @return
	 */
	public Long getTypeId();

	public void setTypeId(Long typeId);

	/**
	 * 返回所属类型名称
	 * @return
	 */
	public String getTypeName();

	public void setTypeName(String typeName);
	
	public Integer getSecurityLevel();

	public void setSecurityLevel(Integer securityLevel);
	public void setFromSysId(Long fromSysId);
	public Long getFromSysId();
	// 云网通行证
	public Short getIsApply();
	public void setIsApply(Short isApply);
	
	// 云网通行证统一用户标识
	public String getOpenId();
	public void setOpenId(String openId);
	
	//是否绑定手机
	public String getIsMobailTrue();
	public void setIsMobailTrue(String isMobailTrue);
	
	//是否验证邮箱
	public String getIsEmailTrue();
	public void setIsEmailTrue(String isEmailTrue);
	
	public Long getTotal();
	public void setTotal(Long total);
	

	//是否是管理员
	public long getIsCharge();

	public void setIsCharge(long isCharge);
	
	
	public long getUpdateTimes();
		

	public void setUpdateTimes(long updateTimes);
	

	public String getMark();
	public void setMark(String mark);

	public String getIsIndividual();

	public void setIsIndividual(String isIndividual);
    
	public String getState();

	public void setState(String state);
	
	
}
