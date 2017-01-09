
   function doSearch() {
            //先销毁在初始化 否则只查询第一次
	   		$("#cusTable").bootstrapTable('destroy'); 
	   
            $('#cusTable').bootstrapTable({
                url: basePath+'/pc/getSysUserList.do',         //请求后台的URL（*）
                method: 'get',                      //请求方式（*）
                toolbar: '#toolbar',                //工具按钮用哪个容器
                striped: true,                      //是否显示行间隔色
                cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                paginationHAlign:'left',
                showRefresh:false,
                queryParams:function(params) {
                	 return {
                		 limit: params.limit,
                		 offset: params.offset,
                		 status:$('#status').val()
                		 };
                },
                sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber:1,                       //初始化加载第一页，默认第一页
                pageSize: 10,                       //每页的记录行数（*）
                pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
                clickToSelect: true,                //是否启用点击选中行
                uniqueId: "id",                     //每一行的唯一标识，一般为主键列
                cardView: false,                    //是否显示详细视图
                detailView: false,                   //是否显示父子表
                contentType: 'application/json;charset=UTF-8',//这里我就加了个utf-8
                dataType: 'json',
                onLoadSuccess: function(data){
                },
                  onLoadError: function(){  //加载失败时执行
                },
                columns: [
				{
				    field: '',
				    title: '选择框',
				    checkbox:true
				},
                 {
                    field: 'id',
                    title: '序号',
                    hidden:true,
                    visible:false
                }, {
                    field: 'loginName',
                    title: '登录名'
                }, {
                    field: 'nickname',
                    title: '昵称'
                }, {
                    field: 'sex',
                    title: '性别',
                    formatter: function (value, row, index) {
                    	var msg="";
                        if(value==0){
                        	msg="未知"
                        }
                        if(value==1){
                        	msg="男"
                        }
                        if(value==2){
                        	msg="女"
                        }
                        return msg;
                    }
                }, {
                    field: 'telphone',
                    title: '手机号'
                }
                , {
                    field: 'userType',
                    title: '用户类别'
                }, {
                    field: 'remark',
                    title: '备注'
                }, {
        			field : 'opt',
        			title : '操作',
        			width : '300px',
        			align : 'center',
        			formatter : formatOper
        		}
               ]
            });
    };
    
    
    function toUpdate(id) {
    	window.location.href=basePath + "/pc/toGetUpdateSysUser.do?id="+id;
    }

    function toAdd() {
    	window.location.href=basePath + "/pc/toGetAddSysUser.do";
    }
    
    function toUpdateStatus() {
    	var $data=$('#cusTable').bootstrapTable('getSelections');
    	if($data.length!=1){
    		alert("只能选择一行修改");
    		return false;
    	}
    	//alert(JSON.stringify($data));
    	var id=$data[0].id;
    	var flag=$data[0].delFlag;
    	var status=0;
    	if(flag==0){
    		status=1;
    	}
    		$.ajax({
    			type : "POST",
    			url : basePath + "/pc/updateSysUserStatus.do",
    			data : {
    				"id" : id,
    				"status" : status
    			},
    			async : false,
    			dataType : "json",
    			success : function(result) {
    				$('#myModal').modal('hide');
    				doSearch();
    				if(result.rtnCode == 0){
    					alert(result.rtnMsg);
    				}else{
    					alert(result.rtnMsg);
    				}
    			}
    		});
    }

    
    
    function toBatchBindingRole() {
    	var $data=$('#cusTable').bootstrapTable('getSelections');
    	if($data.length!=1){
    		alert("只能选择一行用户");
    		return false;
    	}
    	//alert(JSON.stringify($data));
    	var id=$data[0].id;
    	var roleIdArray=new Array();  
    	$('input[name="roleId"]:checked').each(function(){  
    		roleIdArray.push($(this).val());//向数组中添加元素  
    	});  
    	var roleIds=roleIdArray.join(',');//将数组元素连接起来以构建一个字符串  
    	//alert(roleIds.length+"   "+roleIds);  
    	if(roleIds.length<=0){
    		alert("请至少选择一个角色");
    		return false;
    	}    		$.ajax({
    			type : "POST",
    			url : basePath + "/pc/batchBindingUserRole.do",
    			data : {
    				"userId" : id,
    				"roleIds" : roleIds
    			},
    			async : false,
    			dataType : "json",
    			success : function(result) {
    				$('#userRoleModel').modal('hide');
    				doSearch();
    				if(result.rtnCode == 0){
    					alert(result.rtnMsg);
    				}else{
    					alert(result.rtnMsg);
    				}
    			}
    		});
    }

    function toGetUserRoleList() {
    	var $data=$('#cusTable').bootstrapTable('getSelections');
    	if($data.length!=1){
    		alert("只能选择一行用户");
    		return false;
    	}
    	//alert(JSON.stringify($data));
    	var id=$data[0].id;
    		$.ajax({
    			type : "GET",
    			url : basePath + "/pc/toGetUserRoleList.do",
    			data : {
    				"userId" : id,
    			},
    			async : false,
    			dataType : "json",
    			success : function(result) {
    				if(result.rtnCode == 0){
    					$("#userRoleDiv").empty();
    					var show = "";
						$.each(result.result, function(n, value) {
							if(value.isChecked==0){
								show += "<input type='checkbox' name='roleId' value='"+value.roleId+"'>"+value.roleName+"&nbsp;";
							}else{
								show += "<input type='checkbox' name='roleId' value='"+value.roleId+"' checked='checked'>"+value.roleName+"&nbsp;";
							}
							//alert(show)
						})
						
						$("#userRoleDiv").append(show);
    				}
    			}
    		});
    }
    
    function formatOper(value, row, index) {
    	var msg = '';
    		if (row.delFlag == 0) {
    			msg = "禁用";
    		} else if (row.delFlag == 1) {
    			msg = "启用";
    		}
    	var url = "<a href=\"javascript:void(0);\" onclick=\"toUpdate('" + row.id + "')\">修改</a>&nbsp;"
    	+"<a href=\"#myModal\" role=\"button\" data-toggle=\"modal\">"+msg+"</a>&nbsp;"
    	+"<a href=\"#userRoleModel\" role=\"button\" data-toggle=\"modal\">绑定角色</a>&nbsp;";
    	
    	return url;
    }