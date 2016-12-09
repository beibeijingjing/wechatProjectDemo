<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String sessionId="";
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<meta charset="UTF-8">

<link rel="stylesheet" type="text/css" href="<%=path%>/weixin/Css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/weixin/Css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/weixin/Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/weixin/Css/style.css" />
<script type="text/javascript" src="<%=path%>/weixin/Js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/weixin/Js/bootstrap.js"></script>
<script type="text/javascript" src="<%=path%>/weixin/Js/ckform.js"></script>
<script type="text/javascript" src="<%=path%>/weixin/Js/common.js"></script>

<style type="text/css">
body {
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
</style>
</head>
<body>

<script type="text/javascript">
  		var basePath='<%=path%>';
  		var sessionId='<%=sessionId%>';
  		
  		 $(function () {
  			 
  			$('#backid').click(function(){
  					window.location.href=basePath+'/pc/toGetSysUserList.do';
  			});
  			
  			$('#subBtn').click(submitData);
  			
  	    });
  		 
  		 function submitData(){
  			$.ajax({
	    			type : "POST",
	    			url : basePath + "/pc/addSysUser.do",
	    			data : {
	    				"loginName" : $('#loginName').val(),
	    				"password" : $('#password').val(),
	    				"nickname":$('#nickname').val(),
	    				"sex":$('input[type=radio][name=sex]:checked').val(),
	    				"telphone":$('#telphone').val(),
	    				"remark":$('#remark').val(),
	    				"delFlag":$('input[type=radio][name=status]:checked').val()
	    			},
	    			async : false,
	    			dataType : "json",
	    			success : function(result) {
	    				if(result.rtnCode == 0){
	    					alert(result.rtnMsg);
	    				}else{
	    					alert(result.rtnMsg);
	    				}
	    				
	    				window.location.href=basePath+'/pc/toGetSysUserList.do';
	    			}
	    		});
  		 }
 </script>
 
	<form action="" method="post" class="definewidth m20">
		<table class="table table-bordered table-hover m10">
			<tr>
				<td width="10%" class="tableleft">用户名</td>
				<td>
					<input type="text" name="loginName" id="loginName"/>
				</td>
			</tr>
			<tr>
 				<td width="10%" class="tableleft">昵称</td>
 				<td>
 				  <input type="text" name="nickname" id="nickname" value="${user.loginName }"/>
 				</td>
 			</tr>
			<tr>
				<td class="tableleft">密码</td>
				<td><input type="password" name="password" id="password"/></td>
			</tr>
			<tr>
				<td class="tableleft">手机号</td>
				<td><input type="text" name="telphone" id="telphone"/></td>
			</tr>
		    <tr>
				<td class="tableleft">性别</td>
				<td>
					<input type="radio" name="sex" value="1" checked="checked">男
					<input type="radio" name="sex" value="2">女
				</td>
			</tr>
			<tr>
				<td class="tableleft">备注</td>
				<td>
					<textarea id="remark" name="remark" rows="6" cols="40"></textarea>
				</td>
			</tr>
			
			<tr>
				<td class="tableleft">状态</td>
				<td><input type="radio" name="status" value="0" checked /> 启用 <input
					type="radio" name="status" value="1" /> 禁用</td>
			</tr>
			<tr>
				<td class="tableleft"></td>
				<td>
					<button id="subBtn" type="submit" class="btn btn-primary" type="button">保存</button>
					&nbsp;&nbsp;
					<button type="button" class="btn btn-success" name="backid"
						id="backid">返回列表</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
