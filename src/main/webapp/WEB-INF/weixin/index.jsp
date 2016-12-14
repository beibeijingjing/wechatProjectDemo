<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String sessionId="";
%>
<!DOCTYPE HTML>
<html>
<head>
<title>后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=path%>/weixin/assets/css/dpl-min.css" rel="stylesheet"
	type="text/css" />
<link href="<%=path%>/weixin/assets/css/bui-min.css" rel="stylesheet"
	type="text/css" />
<link href="<%=path%>/weixin/assets/css/main-min.css" rel="stylesheet"
	type="text/css" />
</head>
<body>

	<div class="header">

		<div class="dl-title">
			<!--<img src="/chinapost/Public/assets/img/top.png">-->
		</div>

		<div class="dl-log">
			欢迎您，<span class="dl-log-user">root</span><a
				href="/chinapost/index.php?m=Public&a=logout" title="退出系统"
				class="dl-log-quit">[退出]</a>
		</div>
	</div>
	<div class="content">
		<div class="dl-main-nav">
			<div class="dl-inform">
				<div class="dl-inform-title">
					<s class="dl-inform-icon dl-up"></s>
				</div>
			</div>
			<ul id="J_Nav" class="nav-list ks-clear">
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-home">系统管理</div></li>
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-order">业务管理</div></li>

			</ul>
		</div>
		<ul id="J_NavContent" class="dl-tab-conten">

		</ul>
	</div>
	<script type="text/javascript"
		src="<%=path%>/weixin/assets/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>/weixin/assets/js/bui-min.js"></script>
	<script type="text/javascript"
		src="<%=path%>/weixin/assets/js/common/main-min.js"></script>
	<script type="text/javascript"
		src="<%=path%>/weixin/assets/js/config-min.js"></script>
	<script>
    BUI.use('common/main',function(){
        var config = [
	            {id:'1',menu:
		        	[{text:'系统管理',items:
		        		[
		        		 {id:'6',text:'菜单管理',href:'<%=path%>/pc/toGetSysMenuList.do'},
		        		 {id:'4',text:'用户管理',href:'<%=path%>/pc/toGetSysUserList.do'},
		        		 {id:'3',text:'角色管理',href:'Role/index.html'}
		        		]
		        	}]
	            }
	            ,{id:'7',homePage : '9',menu:
	            	[{text:'公众号管理',items:
	            		[
	            		 {id:'9',text:'微信菜单管理',href:'<%=path%>/pc/toGetWxMenuList.do'},
	            		 {id:'10',text:'用户管理',href:'<%=path%>/pc/toGetWxUserList.do'},
	            		 {id:'10',text:'标签管理',href:'<%=path%>/pc/toGetWxUserLabelList.do'},
	            		 {id:'10',text:'关键字管理',href:'<%=path%>/pc/toGetWxKeywordList.do'}
	            		]
	                }]
	            }
            ];
        new PageUtil.MainPage({
            modulesConfig : config
        });
    });
</script>
	<div style="text-align: center;">
		<p>
			来源：<a href="http://www.mycodes.net/" target="_blank">源码之家</a>
		</p>
	</div>
</body>
</html>