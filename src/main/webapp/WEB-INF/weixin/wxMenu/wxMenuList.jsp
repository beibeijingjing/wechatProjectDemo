<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css" href="<%=path%>/weixin/Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/weixin/Css/style.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/weixin/Css/bootstrap-table.css" />


<script type="text/javascript" src="<%=path%>/weixin/Js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/weixin/Js/bootstrap.js"></script>
<script type="text/javascript" src="<%=path%>/weixin/Js/bootstrap-table.js"></script>
<script type="text/javascript" src="<%=path%>/weixin/Js/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="<%=path%>/weixin/Js/ckform.js"></script>
<script type="text/javascript" src="<%=path%>/weixin/Js/common.js"></script>
<script type="text/javascript" src="<%=path%>/weixin/Js/wxMenu/wxMenu.js"></script>

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
  		    	doSearch();
  			$('#searchBtn').click(function(){
  				doSearch();
  			 });
  			 $('#addBtn').click(function(){
  				 toAdd(); 
  			 });
  			$('#delBtn').click(function(){
 				 toBatchDelete(); 
 			 });
  			/* $('#synBtn').click(function(){
 				 toSynchronizeMode(); 
 			 }); */
  			 
  	    }); 
 </script>
	
	
	<div id="toolbar" style="padding:10px;height:auto;">
			<div>
				<span>
					状态: 
					<select id="status">
						<option value="-1">全部</option>
						<option value="0">可用</option>
						<option value="1">隐藏</option>
					</select>
				</span>
				<span style="padding:15px;">
						<button id="searchBtn" class="btn btn-primary" >搜索</button>
						<button id="addBtn" class="btn btn-primary" >添加</button>
						<button id="delBtn" class="btn btn-primary" >批量删除</button>
						<button id="synBtn" class="btn btn-primary"  data-toggle="modal" data-target="#synModal">同步</button>
						<!-- <a href="#synModal" role="button" data-toggle="modal">同步</a> -->
		        </span>
			</div>
	  </div>
	
	<table class="table table-hover" id="cusTable">
       
	</table>
	
	<div id="add"></div>
	
	
	<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					禁用/启用
				</h4>
			</div>
			<div class="modal-body">
				确定要修改状态吗？
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消
				</button>
				<button type="button" class="btn btn-primary" onclick="toUpdateStatus();">
					确定
				</button>
			</div>
		</div>
	</div>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="synModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					同步
				</h4>
			</div>
			<div class="modal-body">
				确定要同步菜单吗？
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消
				</button>
				<button type="button" class="btn btn-primary" onclick="toSynchronization();">
					确定
				</button>
			</div>
		</div>
	</div>
</div>

</body>
</html>
