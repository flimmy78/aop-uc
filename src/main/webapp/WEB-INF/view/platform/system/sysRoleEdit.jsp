<%--
	time:2011-11-28 11:31:14
	desc:edit the 系统角色表
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ap" uri="/appleTag"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 系统角色表</title>
	<%@include file="/commons/include/form.jsp" %>
	<link href="${ctx}/styles/ligerUI/ligerui-all.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx}/js/calendar/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=sysRole"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			if(${sysRole.roleId==null}){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			
			$("a.save").click(function() {
				$('#sysRoleForm').submit(); 
			});
		});
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">
				<c:if test="${sysRole.roleId==null }">添加系统角色</c:if>
				<c:if test="${sysRole.roleId!=null }">编辑系统角色</c:if>  
				</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="javascript:void(0)">保存</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="javascript:window.location.href = document.referrer;">返回</a><!--window.history.go(-1);//返回上一页不刷新  
                                              window.location.href = document.referrer;//返回上一页并刷新   </div><a href="javascript:history.go(-1); parent.location.reload();">返回上一页重载页面</a>  -->
				</div>
			</div>
		</div>
		<div class="panel-body">
			
				<form id="sysRoleForm" method="post" action="saveRole.ht">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%">角色名称:<span style="color:red;">*</span> </th>
							<td><input type="text" id="roleName" name="roleName" value="${sysRole.roleName}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%">角色别名:<span style="color:red;">*</span> </th>
							<td><input type="text" id="alias" name="alias" value="${sysRole.alias}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%">角色类型: </th>
							<td>
								<ap:selectDB name="roleType" styleClass="input_010" style="width:100px;" id="roleType" where="parentId=10000005780001" optionValue="itemValue"
									optionText="itemName" table="SYS_DIC" selectedValue="${sysRole.roleType}">
								</ap:selectDB>
							</td>
						</tr>
						<tr>
							<th width="20%">子系统名称: </th>
							<td>
								<c:choose>
									<c:when test="${sysRole.roleId==null }">
										<select name="systemId" id="systemId" class="select" style="width:18%;">
										    <option value="">--请选择--</option>
											<c:forEach var="subsystem" items="${subsystemList }">
												<option value="${subsystem.systemId}"  >${subsystem.sysName}</option>
											</c:forEach>
										</select>
									</c:when>
									<c:otherwise>
										<input type="hidden" name="systemId" value="${sysRole.systemId }" />
										<c:forEach var="subsystem" items="${subsystemList }">
											 <c:if test="${sysRole.systemId==subsystem.systemId }">${subsystem.sysName }</c:if>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th width="20%">备注: </th>
							<td>
								<textarea rows="3" cols="50" id="memo" name="memo">${sysRole.memo}</textarea>
						</tr>
						<%-- <tr>
							<th width="20%">允许删除: </th>
							<td>
								<input type="radio" id="allowDel" name="allowDel" value="1" checked="checked" <c:if test="${sysRole.allowDel==1}"> checked="checked" </c:if> />允许
								<input type="radio" id="allowDel" name="allowDel" value="0"  <c:if test="${sysRole.allowDel==0}"> checked="checked" </c:if> >不允许
							</td>
						</tr> --%>
						<%-- <tr>
							<th width="20%">允许编辑: </th>
							<td>
							    <input type="radio" id="allowEdit" name="allowEdit" value="1" checked="checked" <c:if test="${sysRole.allowEdit==1}"> checked="checked" </c:if> />允许
								<input type="radio" id="allowEdit" name="allowEdit" value="0"  <c:if test="${sysRole.allowEdit==0}"> checked="checked" </c:if> >不允许
							</td>
						</tr> --%>
						<%-- <tr>
							<th width="20%">是否启用: </th>
							<td>
							    <input type="radio" id="enabled" name="enabled" value="1" checked="checked" <c:if test="${sysRole.enabled==1}"> checked="checked" </c:if> />启用
								<input type="radio" id="enabled" name="enabled" value="0"  <c:if test="${sysRole.enabled==0}"> checked="checked" </c:if> >禁用
							</td>
						</tr> --%>
						<%-- <tr>
							<th width="20%">是否企业管理员: </th>
							<td>
							    <input type="radio" id="isManager" name="isManager" value="1" checked="checked" <c:if test="${sysRole.isManager==1}"> checked="checked" </c:if> />yes
								<input type="radio" id="isManager" name="isManager" value="0"  <c:if test="${sysRole.isManager==0}"> checked="checked" </c:if> >no
							</td>
						</tr> --%>
					</table>
					<input type="hidden" name="roleId" value="${sysRole.roleId}" />
				</form>
			
		</div>
</div>
</body>
</html>
