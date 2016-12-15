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
  					window.location.href=basePath+'/pc/toGetWxKeywordList.do';
  			});
  			
  			$('#subBtn').click(submitData);
  			
  			var delFlag="${keyword.delFlag}";
  			$('input[type=radio][name=status][value='+delFlag+']').attr('checked','checked');
  			
  	    });
  		 function submitData(){
  			// window.open(basePath+'/pc/toGetSysMenuList.do','_self')
  			 $.ajax({
	    			type : "POST",
	    			url : basePath + "/pc/updateKeyword.do",
	    			data : {
	    				"id":$('#id').val(),
	    				"wxKey" : $('#wxKey').val(),
	    				"wxMessage" : $('#wxMessage').val(),
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
	    			}
	    		});
  		 }
 </script>
 
	<form action="" method="post" class="definewidth m20">
		<input type="hidden" name="id" id="id" value="${keyword.id }"/>
		<table class="table table-bordered table-hover m10">
			<tr>
				<td class="tableleft">关键字</td>
				<td><input type="text" name="wxKey" id="wxKey" value="${keyword.wxKey }"/></td>
			</tr>
			<tr>
				<td class="tableleft">详细信息：</td>
				<td>
				<textarea id="wxMessage" rows="10" cols="25">${keyword.wxMessage}</textarea>
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