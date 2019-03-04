<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生列表</title>
<script type="text/javascript">
	
$(function(){
	$("#nav a").click(function(){
		var title = $(this).text();
		var url = $(this).attr("url");
		if($("#tt").tabs('exists',title)){
			$("#tt").tabs('select',title)
		}else{
			$('#tt').tabs('add',{    
			    title:title,    
			    content:"<iframe src='"+url+"' style='width: 100%;height: 100%;'frameborder='0'></iframe>",    
			    closable:true,    
			});
		}
	})
});


</script>

</head>
<body>
	<div id="cc" class="easyui-layout" fit="true">   
    <div data-options="region:'north',split:false" style="height:100px;"></div>   
    <div data-options="region:'south',split:false" style="height:100px;"></div>   
    <div data-options="region:'west',title:'导航菜单',split:false" style="width:150px;">
			<div id="nav" class="easyui-accordion" fit="true">
				<div title="系统管理" data-options="iconCls:'icon-save'" style="overflow: auto; padding: 10px;">
						<ul>
							<li> <a href="#" url="SysUser.jsp">用户管理</a> </li>
							<li> <a href="#" url="SysUserRole.jsp">角色管理</a> </li>
							<li> <a href="#" url="SysMenu.jsp">菜单管理</a> </li>
							<li> <a href="#" url="SysRole.jsp">用户角色管理</a> </li>
							<li> <a href="#" url="SysRoleMenu.jsp">角色菜单管理</a> </li>
							<li> <a href="#" url="SysLog.jsp">用户日志管理</a> </li>
						</ul>
				</div>
				<div title="人员档案管理" data-options="iconCls:'icon-save',selected:true">
						<ul class="easyui-tree">
							<li> <a href="#" url="Department.jsp">部门管理</a> </li>
							<li> <a href="#" url="Employee.jsp">员工管理</a> </li>
							<li> <a href="#" url="ArchiveType.jsp">档案类型管理</a> </li>
							<li> <a href="#" url="Archive.jsp">档案管理</a> </li>
							<li> <a href="#" url="Resume.jsp">履历管理</a> </li>
							<li> <a href="#" url="Contract.jsp">合同管理</a> </li>
							<li> <a href="#" url="Rewards.jsp">奖惩管理</a> </li>
							<li> <a href="#" url="Student.jsp">学生信息管理</a> </li>
						</ul>
				</div>
				<div title="人事调配管理" data-options="iconCls:'icon-save'">
					<ul>
						<li> <a href="#" url="Deploy.jsp">人事调动管理</a> </li>
						<li> <a href="#" url="Engage.jsp">聘任管理</a> </li>
					</ul>
				</div>
				<div title="教育培训" data-options="iconCls:'icon-save'">
					<ul>
						<li> <a href="#" url="EduType.jsp">培训类别管理</a> </li>
						<li> <a href="#" url="Edu.jsp">培训管理</a> </li>
						<li> <a href="#" url="EduScore.jsp">培训成绩管理</a> </li>
						<li> <a href="#" url="Certficate.jsp">员工证书管理</a> </li>
					</ul>
				</div>
			</div>
		</div>   
    <div data-options="region:'center'" >
			<div id="tt" class="easyui-tabs" fit="true" border="false">
				<div title="欢迎" style="text-align: center;">
					欢迎使用
				</div>
			</div>

		</div>   
</div> 
</body>
</html>