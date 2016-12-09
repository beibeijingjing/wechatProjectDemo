
   function doSearch() {
            //先销毁在初始化 否则只查询第一次
	   		$("#cusTable").bootstrapTable('destroy'); 
	   
            $('#cusTable').bootstrapTable({
                url: basePath+'/pc/getWxUserList.do',         //请求后台的URL（*）
                method: 'get',                      //请求方式（*）
                toolbar: '#toolbar',                //工具按钮用哪个容器
                striped: true,                      //是否显示行间隔色
                cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                showRefresh:false,
                paginationHAlign:'left',
               // search:true,
                //searchAlign:'left',
                toolbarAlign:'left',
                buttonsAlign:'left',
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
                //strictSearch: true,
                clickToSelect: true,                //是否启用点击选中行
                //height: 460,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
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
                    title: 'id',
                    hidden:true,
                    visible:false
                }, {
                    field: 'openid',
                    title: 'openid' ,
                    hidden:true,
                    visible:false
                }, {
                    field: 'unionid',
                    title: 'unionid' ,
                    hidden:true,
                    visible:false
                }, {
                    field: 'headimgurl',
                    title: '头像',
                    formatter:formatImg
                    
                }, {
                    field: 'nickname',
                    title: '昵称'
                }, {
                    field: 'subscribe',
                    title: '订阅',
                    formatter:function(value, row, index){
                    	if(value==0){
                    		return "未订阅";
                    	}
                    	if(value==1){
                    		return "已订阅";
                    	}
                    }
                    
                }, {
                    field: 'country',
                    title: '国家'
                }, {
                    field: 'province',
                    title: '省份'
                }, {
                    field: 'city',
                    title: '城市'
                }, {
                    field: 'language',
                    title: '语言'
                }, {
                    field: 'remark',
                    title: '备注'
                }, {
                    field: 'tagidList',
                    title: '标签'
                },
                {
        			field : 'opt',
        			title : '操作',
        			width : '300px',
        			align : 'center',
        			formatter : formatOper
        		}
               ]
            });
    };
    
    function formatImg(value, row, index) {
    	return "<img src='" + value + "' height='100' width='100'/>";
    }
    
    
    function toAddUserLabel() {
    	var $data=$('#cusTable').bootstrapTable('getSelections');
    	//alert(JSON.stringify($data));
    	var ids="";
    	if($data.length>0){
    		$($data).each(function (index, obj) {
    			ids+=obj.openId+"@";
            });
    	}else{
    		alert("请选择操作对象");
    		return false;
    	}
    	
    	var labelIds="";
    	
    		$.ajax({
    			type : "POST",
    			url : basePath + "/pc/batchAddUserLabel.do",
    			data : {
    				"opendIds" : ids,
    				"labelIds":labelIds
    			},
    			async : false,
    			dataType : "json",
    			success : function(result) {
    				$('#addLabelModal').modal('hide');
    				doSearch();
    				if(result.rtnCode == 0){
    					alert(result.rtnMsg);
    				}else{
    					alert(result.rtnMsg);
    				}
    			}
    		});
    }
    
    function toBatchDelete() {
    	var $data=$('#cusTable').bootstrapTable('getSelections');
    	//alert(JSON.stringify($data));
    	var ids="";
    	if($data.length>0){
    		$($data).each(function (index, obj) {
    			ids+=obj.openId+"@";
            });
    	}else{
    		alert("请选择操作对象");
    		return false;
    	}
    		$.ajax({
    			type : "POST",
    			url : basePath + "/pc/batchPullBlackUser.do",
    			data : {
    				"opendIds" : ids,
    			},
    			async : false,
    			dataType : "json",
    			success : function(result) {
    				$('#delModal').modal('hide');
    				doSearch();
    				if(result.rtnCode == 0){
    					alert(result.rtnMsg);
    				}else{
    					alert(result.rtnMsg);
    				}
    			}
    		});
    }
    
    function toSynchronization() {
    		$.ajax({
    			type : "POST",
    			url : basePath + "/pc/synchronizeWxUserLabel.do",
    			data : {
    			},
    			async : false,
    			dataType : "json",
    			success : function(result) {
    				$('#synModal').modal('hide');
    				doSearch();
    				if(result.rtnCode == 0){
    					alert(result.rtnMsg);
    				}else{
    					alert(result.rtnMsg);
    				}
    			}
    		});
    }
    

   
    
    
    function formatOper(value, row, index) {
    	/*var msg = '';
    		if (row.delFlag == 0) {
    			msg = "禁用";
    		} else if (row.delFlag == 1) {
    			msg = "启用";
    		}*/
    	var url = "<a href=\"#addLabelModal\" role=\"button\" data-toggle=\"modal\">打标签</a>&nbsp;";
    	
    	return url;
    }