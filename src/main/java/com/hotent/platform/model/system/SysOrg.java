package com.hotent.platform.model.system;

import java.util.Date;
import com.hotent.core.model.BaseModel;
import com.hotent.platform.auth.ISysOrg;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
/**
 * 对象功能:组织架构SYS_ORG Model对象
 * 开发公司:北京航天软件有限公司
 * 开发人员:zxx
 * 创建时间:2016 08 27
 */
public class SysOrg extends BaseModel implements ISysOrg{
	
	private static final long serialVersionUID = 1L;
	public final static Long BEGIN_DEMID=1L;//默认维度
	public final static Long BEGIN_ORGID=1L;//默认组织ID
	public final static Integer BEGIN_DEPTH=0;//默认深度
	public final static String BEGIN_PATH="1";//默认路径
	public final static Short BEGIN_TYPE=1;//默认组织类型
	public final static Long BEGIN_ORGSUPID=-1L;//默认组织父ID
	public final static Short BEGIN_SN=1;//默认sn
	public final static Short BEGIN_FROMTYPE=0;//默认组织类型
	
	public final static Short FROMTYPE_AD=1;
	public final static Short FROMTYPE_DB=0;

	public final static Integer IS_LEAF_N=1; //不是叶子节点
	public final static Integer IS_LEAF_Y=0; //是叶子节点

	public final static String IS_PARENT_N="false"; //不是父类节点
	public final static String IS_PARENT_Y="true"; //是父类节点
	
	public final static Long IS_SYSTEM_Y				=1L;
	public final static Long IS_SYSTEM_N				=0L;
	
	// 组织ID
	private Long orgId;
	// 维度编号
	private Long demId;
	// 维度名称
	private String demName;
	// 名称
	private String orgName;
	// 描述
	private String orgDesc;
	// 上级
	private Long orgSupId;
	// 上级组织名称(如果等于维度ID则说明该组织是该维度的根节点)
	private String orgSupName;	
	// 路径
	private String path;
	// 层次
	private Integer depth;
	// 类型
	private Long orgType;
	// 建立人
	private Long creatorId;
	// 建立时间
	private Date createtime;
	// 修改人
	private Long updateId;
	// 修改时间
	private Date updatetime;	
	//负责人id
	private String ownUser;	
	//负责人姓名
	private String ownUserName;	
	//创建人
	private String createName;
	//修改人
	private String updateName;
	// 序号
	private Long sn=0L;
	//在线人数
	private Integer onlineNum=0;
	private Short isPrimary;
	//是否根节点（0，非根节点,1,根节点)
	private Short isRoot=0;
	//数据来源（0，系统添加,1,来自AD同步)
	private Short fromType=0;
	//图标
	private String iconPath="";
	//树展开
	private String open="true";
	// 是否叶子结点(0否,1是),主要用于数据库保存
	private Integer isLeaf;
	// 是否父类,主要用于树的展示时用
	private String isParent;
	//状态
	private Long  orgStatus;
	//是否内置
	private Long  isSystem=IS_SYSTEM_N;
	//企业信息
	private SysOrgInfo sysOrgInfo;
	//管理员用户
	private SysUser sysUser;
	/**
	 * 经营类型
	 */
	private String manageType;
	
	private String openId;
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public Integer getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
		if(isLeaf!=null&&isLeaf==0){
			this.isParent=IS_PARENT_N;
		}else if(isLeaf!=null&&isLeaf>0){
			this.isParent=IS_PARENT_Y;
		}else{
			this.isParent=null;
		}
	}
	public String getIsParent() {
		if(this.isLeaf==null)return IS_PARENT_Y;
		else
		return this.isLeaf>0?IS_PARENT_Y:IS_PARENT_N;
	}
	public void setIsParent(String isParent) {
		this.isParent = isParent;
		if(isParent!=null&&isParent.equals(IS_PARENT_N)){
			this.isLeaf=IS_LEAF_Y;
		}else if(isParent!=null&&isParent.equals(IS_PARENT_Y)){
			this.isLeaf=IS_LEAF_N;
		}else{
			this.isLeaf=null;
		}
	}
	public String getIconPath() {
		return iconPath;
	}
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	public Short getIsPrimary()
	{
		return isPrimary;
	}
	public void setIsPrimary(Short isPrimary)
	{
		this.isPrimary = isPrimary;
	}

	public Long getSn()
	{
		return sn;
	}
	public void setSn(Long sn)
	{
		this.sn = sn;
	}
	public String getOwnUserName()
	{
		return ownUserName;
	}
	public void setOwnUserName(String ownUserName)
	{
		this.ownUserName = ownUserName;
	}
	public String getCreateName()
	{
		return createName;
	}
	public void setCreateName(String createName)
	{
		this.createName = createName;
	}
	public String getUpdateName()
	{
		return updateName;
	}
	public void setUpdateName(String updateName)
	{
		this.updateName = updateName;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	/**
	 * 返回 组织ID
	 * @return
	 */
	public Long getOrgId() {
		return orgId;
	}

	public void setDemId(Long demId) {
		this.demId = demId;
	}
	/**
	 * 返回 维度编号
	 * @return
	 */
	public Long getDemId() {
		return demId;
	}
    		
	
	public String getDemName() {
		return demName;
	}
	/**
	 * 返回 维度名称
	 * @return
	 */
	public void setDemName(String demName) {
		this.demName = demName;
	}
	
	
	public void setOrgName(String orgName){
		this.orgName =orgName;
	}
	/**
	 * 返回 名称
	 * @return
	 */
	public String getOrgName() {
		return orgName;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}
	/**
	 * 返回 描述
	 * @return
	 */
	public String getOrgDesc() {
		return orgDesc;
	}

	public void setOrgSupId(Long orgSupId) {
		this.orgSupId = orgSupId;
	}
	/**
	 * 返回 上级
	 * @return
	 */
	public Long getOrgSupId() {
		return orgSupId;
	}

	// 上级组织名称
	public String getOrgSupName()
	{
		return orgSupName;
	}
	// 上级组织名称
	public void setOrgSupName(String orgSupName)
	{
		this.orgSupName = orgSupName;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * 返回 路径
	 * @return
	 */
	public String getPath() {
		return path;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	/**
	 * 返回 层次
	 * @return
	 */
	public Integer getDepth() {
		return depth;
	}

	public void setOrgType(Long orgType) {
		this.orgType = orgType;
	}
	/**
	 * 返回 类型
	 * @return
	 */
	public Long getOrgType() {
		return orgType;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	/**
	 * 返回 建立人
	 * @return
	 */
	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 返回 建立时间
	 * @return
	 */
	public Date getCreatetime() {
		return createtime;
	}

	public void setUpdateId(Long updateId) {
		this.updateId = updateId;
	}
	/**
	 * 返回 修改人
	 * @return
	 */
	public Long getUpdateId() {
		return updateId;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	/**
	 * 返回 修改时间
	 * @return
	 */
	public Date getUpdatetime() {
		return  updatetime;
	}
	
	/**
	 * 返回负责人
	 * @return
	 */
	public void setOwnUser(String ownUser) {
		this.ownUser = ownUser;
	}
	
	/**
	 * 返回负责人
	 * @return
	 */
	public String getOwnUser() {
		return ownUser;
	}


   	public Integer getOnlineNum() {
		return onlineNum;
	}
	public void setOnlineNum(Integer onlineNum) {
		this.onlineNum = onlineNum;
	}
	
	
	public Short getIsRoot() {
		return isRoot;
	}
	public void setIsRoot(Short isRoot) {
		this.isRoot = isRoot;
	}
	public Short getFromType() {
		return fromType;
	}
	public void setFromType(Short fromType) {
		this.fromType = fromType;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysOrg)) {
			return false;
		}
		SysOrg rhs = (SysOrg) object;
		return new EqualsBuilder()
		.append(this.orgId, rhs.orgId)
		.append(this.demId, rhs.demId)
		.append(this.orgName, rhs.orgName)
		.append(this.orgDesc, rhs.orgDesc)
		.append(this.orgSupId, rhs.orgSupId)
		.append(this.path, rhs.path)
		.append(this.depth, rhs.depth)
		.append(this.orgType, rhs.orgType)
		.append(this.creatorId, rhs.creatorId)
		.append(this.createtime, rhs.createtime)
		.append(this.updateId, rhs.updateId)
		.append(this.updatetime, rhs.updatetime)
		.append(this.ownUser, rhs.ownUser)
		.append(this.sn, rhs.sn)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.orgId) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("orgId", this.orgId) 
		.append("demId", this.demId) 
		.append("orgName", this.orgName) 
		.toString();
	}
	
	/**
	 *设置组织级别
	 * 
	 *  @param orgLevel 
	 */
	public void setOrgLevel(Integer orgLevel) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 获取组织级别
	 * 
	 * @return
	 */
	public Integer getOrgLevel() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setOrgStatus(Long orgStatus) 
	{
		this.orgStatus = orgStatus;
	}
	/**
	 * 返回 org_status
	 * @return
	 */
	public Long getOrgStatus() 
	{
		return this.orgStatus;
	}
	public void setIsSystem(Long isSystem) 
	{
		this.isSystem = isSystem;
	}
	/**
	 * 返回 is_system
	 * @return
	 */
	public Long getIsSystem() 
	{
		return isSystem;
	}
	public SysOrgInfo getSysOrgInfo() {
		return sysOrgInfo;
	}
	public void setSysOrgInfo(SysOrgInfo sysOrgInfo) {
		this.sysOrgInfo = sysOrgInfo;
	}
	public SysUser getSysUser() {
		return sysUser;
	}
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	/**
	 * 经营类型
	 * @return
	 */
	public String getManageType() {
		return manageType;
	}
	public void setManageType(String manageType) {
		this.manageType = manageType;
	}
}