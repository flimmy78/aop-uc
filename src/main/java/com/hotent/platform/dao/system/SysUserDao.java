/**
 * 对象功能:用户表 Dao类
 * 开发公司:北京航天软件有限公司
 * 开发人员:hotent
 * 创建时间:2011-11-03 16:02:46
 */
package com.hotent.platform.dao.system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.auth.ISysRole;
import com.hotent.platform.auth.ISysUser;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.SysUserOrg;

@Repository
public class SysUserDao extends BaseDao<ISysUser> implements UserDetailsService {
	@Resource
	SysRoleDao sysRoleDao;

	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return SysUser.class;
	}

	@Override
	public void add(ISysUser entity) {
		super.add(entity);
		//【HT】增加将用户加入到该组织中
		if(entity.getUserOrgId()!=null){
			SysUserOrg sysUserOrg = new SysUserOrg();
			//sysUserOrg.setUserOrgId(userOrgId);
		}
	}

	/**
	 * 重写UserDetailService的接口
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		ISysUser sysUser =null;
		if (!com.casic.util.StringUtil.isEmpty(username)) {
			if ("confadmin".equals(username)) {
				sysUser = getByAccount(username);
			} else {
				sysUser = getByAccountWithManager(username);
			}
		}else{
			throw new UsernameNotFoundException("用户不存在");
		}
		if (sysUser == null)
			throw new UsernameNotFoundException("用户不存在");
		return sysUser;
	}

	/**
	 * 根据用户账号查询系统用户
	 * 
	 * @param account
	 * @return
	 */
	public ISysUser getByAccount(String account) {
		ISysUser sysUser = (ISysUser) getUnique("getByAccount", account);
		return sysUser;
	}
	
	/**
	 * 多表关联
	 * 
	 * @param account
	 * @return
	 */
	public ISysUser getByAccountWithManager(String account) {
		//ISysUser sysUser = (ISysUser) getUnique("getByAccountWithManager", account);
		List <ISysUser> users = getBySqlKey("getByAccount", account);
		
		ISysUser sysUser=new SysUser();
		if(users.size()>0){
			sysUser=users.get(0);
		}
		return sysUser;
	}
	
	
	/**
	 * 根据openId获得用户
	 * 
	 * @param openId
	 * @return
	 */
	public ISysUser getUserByOpenId(String openId) {
		ISysUser sysUser = (ISysUser) getUnique("getByOpenId", openId);
		return sysUser;
	}

	/**
	 * 获取没有分配组织的用户
	 * 
	 * @return
	 */
	public List<ISysUser> getUserNoOrg(QueryFilter queryFilter) {
		return this.getBySqlKey("getUserNoOrg", queryFilter);
	}

	/**
	 * 对象功能：根据组织id查询员工
	 */
	public List<ISysUser> getUserByOrgId(QueryFilter queryFilter) {
		return this.getBySqlKey("getUserByOrgId", queryFilter);
	}

	/**
	 * 取到某个组织下的所有用户
	 * 
	 * @param orgId
	 * @return
	 */
	public List<ISysUser> getByOrgId(Long orgId) {

		return this.getBySqlKey("getByOrgId", orgId);
	}

	/**
	 * 取到某个岗位下的所有用户
	 * 
	 * @param posId
	 * @return
	 */
	public List<ISysUser> getByPosId(Long posId) {

		return this.getBySqlKey("getByPosId", posId);
	}

	/**
	 * 取到某个角色下的所有用户
	 * 
	 * @param roleId
	 * @return
	 */
	public List<ISysUser> getByRoleId(Long roleId) {

		return this.getBySqlKey("getByRoleId", roleId);
	}

	/**
	 * 对象功能：根据组织id查询员工
	 */
	public List<ISysUser> getUserByPath(String path) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("path", path);
		return this.getBySqlKey("getUserByOrgId", param);
	}

	/**
	 * 对象功能：根据查询条件查询用户
	 */
	public List<ISysUser> getUserByQuery(QueryFilter queryFilter) {
		return this.getBySqlKey("getUserByQuery", queryFilter);
	}

	/**
	 * 返回某个角色的所有用户Id
	 * 
	 * @param roleId
	 * @return
	 */
	public List<Long> getUserIdsByRoleId(Long roleId) {
		String statement = getIbatisMapperNamespace() + ".getUserIdsByRoleId";

		List list = getSqlSessionTemplate().selectList(statement, roleId);

		return list;
	}

	/**
	 * 根据角色id查询员
	 */
	public List<ISysUser> getUserByRoleId(QueryFilter queryFilter) {
		return this.getBySqlKey("getUserByRoleId", queryFilter);
	}
	
	/**
	 * modified by ziapple
	 * 对象功能：根据组织id和企业账号查询员工
	 */
	public List<ISysUser> getUserByOrgIdAndOrgSn(QueryFilter queryFilter) {
		return this.getBySqlKey("getUserByOrgIdAndOrgSn", queryFilter);
	}
	
	/*
	 *  modifyBy hotent.xianggang  添加对应方法  b
	 */
	/**
	 * 根据企业id查询员工
	 */
	public List<ISysUser> getUserByEnterprise(QueryFilter queryFilter) {
		return this.getBySqlKey("getUserByEnterprise", queryFilter);
	}
	
	public List<ISysUser> getUserByOrgSn(QueryFilter queryFilter) {
		return this.getBySqlKey("getUserByOrgSn", queryFilter);
	}
	/*
	 *  modifyBy hotent.xianggang  添加对应方法  e
	 */

	/**
	 * 根据组织id查询员工
	 */
	public List<ISysUser> getDistinctUserByPosPath(QueryFilter queryFilter) {
		return this.getBySqlKey("getDistinctUserByPosPath", queryFilter);
	}

	/**
	 * 根据组织path查询员工
	 */
	public List<ISysUser> getDistinctUserByOrgPath(QueryFilter queryFilter) {
		return this.getBySqlKey("getDistinctUserByOrgPath", queryFilter);
	}

	/**
	 * 判断是否存在该账号
	 */
	public boolean isAccountExist(String account) {
		Integer rtn = (Integer) this.getOne("isAccountExist", account);
		return rtn > 0;
	}
	
	/**
	 * zouping
	 * 2013-05-14
	 * 判断企业是否存在该账号
	 */
	public boolean isAccountInCompanyExist(String shortAccount,Long orgSn) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("shortAccount", shortAccount);
		map.put("orgSn", orgSn);
		Integer rtn = (Integer) this.getOne("isAccountInCompanyExist", map);
		return rtn > 0;
	}

	/**
	 * 更新时判定这个帐号是否已经存在。
	 * 
	 * @param userId
	 * @param account
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean isAccountExistForUpd(Long userId, String account) {
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("account", account);
		Integer rtn = (Integer) this.getOne("isAccountExistForUpd", map);
		return rtn > 0;
	}

	/**
	 * 根据用户参数属性查询用户。
	 * 
	 * @param property
	 * @return
	 */
	public List<ISysUser> getByUserOrParam(Map<String, String> property) {
		List<ISysUser> list = this.getBySqlKey(
				"getByUserOrParam_" + this.getDbType(), property);
		return list;
	}

	/**
	 * 根据组织属性查询用户。
	 * 
	 * @param property
	 * @return
	 */
	public List<ISysUser> getByOrgOrParam(Map<String, String> property) {
		return this
				.getBySqlKey("getByOrgOrParam_" + this.getDbType(), property);
	}

	/**
	 * 根据上下级关系取得用户。
	 * 
	 * <pre>
	 * 输入参数 :path 岗位路径。
	 * 			 DEPTH：深度。
	 * 			 condition：上级或下级。
	 * </pre>
	 * 
	 * @param p
	 * @return
	 */
	public List<ISysUser> getUpLowPost(Map<String, Object> p) {
		return this.getBySqlKey("getUpLowPost", p);
	}

	/**
	 * 根据上下级组织取得用户列表。
	 * 
	 * <pre>
	 * 	输入参数：
	 * 		demensionId：维度ID。
	 * 		path:		路径。
	 * 		condition：	上级或下级。
	 * 		depth：		上几级或下几级。
	 * </pre>
	 * 
	 * @param p
	 * @return
	 */
	public List<ISysUser> getUpLowOrg(Map<String, Object> p) {
		return this.getBySqlKey("getUpLowOrg", p);
	}

	/**
	 * 按ID列表取得用户列表，每个用户ID使用逗号分隔。
	 * 
	 * @param idSet
	 *            用户id使用逗号分隔。
	 * @return
	 */
	public List<ISysUser> getByIdSet(Set idSet) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (idSet == null || idSet.size() == 0) {
			params.put("ids", -1);
		} else {
			params.put("ids", StringUtil.getSetAsString(idSet));
		}
		return this.getBySqlKey("getByIdSet", params);
	}

	/**
	 * 根据邮件帐号获取用户对象。
	 * 
	 * @param address
	 * @return
	 */
	public ISysUser getByMail(String address) {
		return this.getUnique("getByMail", address);
	}

	/**
	 * 更新密码。
	 * 
	 * @param userId
	 *            用户ID
	 * @param pwd
	 *            加密过的密码
	 */
	public void updPwd(Long userId, String pwd) {
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("password", pwd);
		this.update("updPwd", map);
	}
	
	public void updateOrgId(Long userId, Long orgIdPrimary) {
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("orgIdPrimary", orgIdPrimary);
		this.update("updateOrgId", map);
	}
	
	public void updateFullName(String userFullName,Long userId){
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("fullname", userFullName);
		this.update("updateFullName", map);
	}

	/**
	 * 更新用户的状态。
	 * 
	 * @param userId
	 *            用户id
	 * @param status
	 *            1，激活，0，禁用，-1，删除
	 * @param isLock
	 *            0，未锁定，1，锁定
	 */
	public void updStatus(Long userId, Short status, Short isLock) {
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("status", status);
		map.put("isLock", isLock);
		this.update("updStatus", map);
	}

	public List<ISysUser> getDirectLeaderByOrgId(Long orgId) {
		List<ISysUser> users = this.getBySqlKey("getDirectLeaderByOrgId", orgId);
		return users;
	}

	/**
	 * 根据下属Id获得上级用户信息
	 * 
	 * @param orgId
	 * @return
	 */
	public List<ISysUser> getUserByUnderUserId(Long orgId) {
		List<ISysUser> users = this.getBySqlKey("getUserByUnderUserId", orgId);
		return users;
	}

	/**
	 * 根据数据来源，取得用户信息
	 * 
	 * @param type
	 * @return
	 */
	public List<ISysUser> getByFromType(short type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fromType", type);
		return this.getBySqlKey("getByFromType", params);
	}

	public List<ISysUser> getAllIncludeOrg() {
		return this.getBySqlKey("getAllIncludeOrg");
	}
	
	////ht:raise add b
	
	/**
	 * 根据企事ID和角色ID，取得用户
	 * @param orgIds
	 * @param roles
	 * @return
	 */
	public List<ISysUser> getByCompAndRoles(Long compId,List<Long> roleIds){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("compId", compId);
		param.put("roleIds", roleIds);
		List<ISysUser> sysUsers =  this.getBySqlKey("getByCompAndRoles", param);
		return sysUsers;
	}
	
	/**
	 * 根据身份证获取用户
	 * @return
	 */
	public List<ISysUser> getByCode(String code,Long tenantId){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("tenantId", tenantId);
		param.put("code", code);
		return this.getBySqlKey("getByCode", param);
	}
	////ht:raise add e
	
	public SysUser getUserIsCharge(Long tenantId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tenantId", tenantId);
		List<ISysUser> list = this.getBySqlKey("getUserIsCharge", params);
		if(list.size()>0){
			return (SysUser) list.get(0);
		}else{
			return null;
		}
		
	}
	

	public List<ISysUser> querySynchronizeList(QueryFilter queryFilter) {
		
		return this.getBySqlKey("querySynchronizeList", queryFilter);
	}
	
	
	public List<ISysUser> getByVip(String vip,String password) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("password", password);
		params.put("vip", vip);
		List<ISysUser> list =  this.getBySqlKey("getByVip", params);
		return list;
	}
	
	public List<ISysUser> getByMobile(String mobile,String password) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mobile", mobile);
		params.put("password", password);
		List<ISysUser> list =  this.getBySqlKey("getByMobile", params);
		return list;
	}
	
	public List<ISysUser> getByMobileOnly(String mobile) {
		Map<String, Object> params = new HashMap<String, Object>();		
		params.put("mobile", mobile);
		List<ISysUser> list =  this.getBySqlKey("getByMobileOnly", params);
		return list;
	}
	public List<ISysUser> getByEmail(String email,String password) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("password", password);
		params.put("email", email);
		List<ISysUser> list =  this.getBySqlKey("getByEmail", params);
		return list;
	}

	public String getPasswordByUserIdAndAccount(Map<String, Object> map) {
		return (String) this.getSqlSessionTemplate().selectOne("getPasswordByUserIdAndAccount", map);
	}
	
	
	public List<Map<String, Object>> getUserBytimeAndAddress(Map<String,Object> map){
		
		 List regionMap=this.getBySqlKey("getUserBytimeAndAddress", map);	
		 List< Map<String, Object>> params = new ArrayList< Map<String, Object>>();
		for ( Object map1 : regionMap) {
			System.out.println(map1);
			Map<String, Object> param =(Map<String, Object>) map1;
			Map<String, Object> param1 =new HashMap<String, Object>();
			param1.put("createtime", param.get("createtime"));
			param1.put("address",param.get("address"));
			param1.put("name",param.get("name"));
			param1.put("account",param.get("account"));
			param1.put("mobile",param.get("mobile"));
			param1.put("email",param.get("email"));
			System.out.println(param1);
			params.add(param1);
		}
		return params;
	}
	public Map<Object, Object> transfor(List<Map<String, Object>> list,  
            String key, String property) {  
        Map<Object, Object> map1 = new HashMap<Object, Object>();  
        for (Map<String, Object> map : list) {  
            map1.put(map.get(key).toString(), map.get(property));  
        }  
        return map1;  
    }

	/**
	 * @param usersystemId
	 * @return//查询子系统下面的所有企业下的用户
	 */
	public List<ISysUser> queryUserBySystemId(QueryFilter queryFilter) {
		List<ISysUser> list =  this.getBySqlKey("queryUserBySystemId", queryFilter);
		return list;
	}
}