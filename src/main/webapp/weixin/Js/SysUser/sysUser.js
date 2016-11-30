
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
                showRefresh:false,
                queryParams:function(params) {
                	 return {
                		 limit: params.limit,
                		 offset: params.offset,
                		 status:$('#status').val()
                		 };
                },
                sortable: false,                     //是否启用排序
                sortOrder: "asc",                   //排序方式
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
                //showPaginationSwitch:true,
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
                    field: 'telephone',
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
    				if(result.rtnCode == 1){
    					alert("修改成功");
    				}else{
    					alert("修改失败");
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
    	+"<a href=\"#myModal\" role=\"button\" data-toggle=\"modal\">"+msg+"</a>&nbsp;";
    	
    	return url;
    }