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
  					window.location.href=basePath+'/pc/toGetWxMenuList.do';
  			});
  			
  			$('#subBtn').click(submitData);
  			
  			//隐藏div
  			extendChange();
  			$('input[type=radio][name=isExtend]').change(function(){
  				extendChange();
  	  		});
  			
  			$('#wxMenuType').change(function(){
  				menuTypeChange();
  			});
  			
  			showMenu();
  	    });
  		 function extendChange(){
  				var isExtend=$('input[type=radio][name=isExtend]:checked').val();
				if(isExtend==0){
					$('#extendTr').hide();
				}
				if(isExtend==1){
					$('#extendTr').show();
				}
				menuTypeChange();
  		 }
  		 
  		 //事件类型触发事件
  		 function menuTypeChange(){
  			 var menuType=$('#wxMenuType').val();
  			 if(menuType==8||menuType==9){
  				$('#keyTr').hide();
  				$('#urlTr').hide();
  	  			$('#mediaTr').show();
  			 }else if(menuType==1){
  				$('#keyTr').hide();
  				$('#urlTr').show();
  	  			$('#mediaTr').hide();
  			 }else{
  				$('#keyTr').show();
  				$('#urlTr').hide();
  	  			$('#mediaTr').hide();
  			 }
  		 }
  		 
  		 function showMenu(){
  			 var show="";
  			<c:forEach items="${wxMenuList}" var="menu" begin="0">
  				show+="<option value='${menu.id}'>${menu.wxMenuName}</option>"
			</c:forEach>
  			$('#parentId').append(show);
  		 }
  		 
  		 function submitData(){
  			$.ajax({
	    			type : "POST",
	    			url : basePath + "/pc/addWxMenu.do",
	    			data : {
	    				"isExtend":$('input[type=radio][name=isExtend]:checked').val(),
	    				"parentId" : $('#parentId').val(),
	    				"wxMenuName" : $('#wxMenuName').val(),
	    				"wxMenuType":$('#wxMenuType').val(),
	    				"wxMenuNo":$('#wxMenuNo').val(),
	    				"wxMenuKey":$('#wxMenuKey').val(),
	    				"wxMenuUrl":$('#wxMenuUrl').val(),
	    				"wxMediaId":$('#wxMediaId').val(),
	    				"delFlag":$('input[type=radio][name=status]:checked').val()
	    			},
	    			async : false,
	    			dataType : "json",
	    			success : function(result) {
	    				if(result.rtnCode == 1){
	    					alert("添加成功");
	    				}else{
	    					alert("添加失败");
	    				}
	    				
	    				window.location.href=basePath+'/pc/toGetSysMenuList.do';
	    			}
	    		});
  		 }
 </script>
 
	<form action="" method="post" class="definewidth m20">
		<table class="table table-bordered table-hover m10">
			<tr width="10%">
				<td class="tableleft">是否扩展</td>
				<td>
					<input type="radio" name="isExtend" value="0" checked="checked">是
					<input type="radio" name="isExtend" value="1">否
				</td>
			</tr>
			<tr>
				<td class="tableleft">菜单名称</td>
				<td><input type="text" name="wxMenuName" id="wxMenuName"/></td>
			</tr>
			
			<tbody id="extendTr">
			<tr>
				<td width="10%" class="tableleft">上级</td>
				<td>
					<select name="parentId" id="parentId">
						<option value="0">--根--</option>
					</select>
				</td>
			</tr>
			<tr>
				<td width="10%" class="tableleft">事件类型</td>
				<td>
					<select name="wxMenuType" id="wxMenuType">
						<option value="0">点击</option>
						<option value="1">跳转url</option>
						<option value="2">扫码</option>
						<option value="3">扫码等待</option>
						<option value="4">拍照</option>
						<option value="5">拍照和相册</option>
						<option value="6">相册</option>
						<option value="7">位置</option>
						<option value="8">下发消息</option>
						<option value="9">跳转图文</option>
					</select>
				</td>
			</tr>
			
			<tr id="keyTr">
				<td class="tableleft">菜单key值</td>
				<td><input type="text" name="wxMenuKey"  id="wxMenuKey"/></td>
			</tr>
			<tr id="urlTr">
				<td class="tableleft">链接地址</td>
				<td><input type="text" name="wxMenuUrl"  id="wxMenuUrl"/></td>
			</tr>
			<tr id="mediaTr">
				<td class="tableleft">素材</td>
				<td><input type="text" name="wxMediaId"  id="wxMediaId"/></td>
			</tr>
			</tbody>
			
			<tr>
				<td class="tableleft">序号</td>
				<td><input type="text" name="wxMenuNo"  id="wxMenuNo"/></td>
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
