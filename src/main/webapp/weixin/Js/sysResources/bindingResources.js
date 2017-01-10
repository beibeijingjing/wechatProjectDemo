
   function doSearch() {
            //先销毁在初始化 否则只查询第一次
	   		$("#cusTable").bootstrapTable('destroy'); 
	   
            $('#cusTable').bootstrapTable({
                url: basePath+'/pc/getSysResourcesVoList.do',         //请求后台的URL（*）
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
                		 roleId : roleId,
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
				    checkbox:true,
				    formatter:stateFormatter
				},
                 {
                    field: 'id',
                    title: '序号',
                    hidden:true,
                    visible:false
                }, {
                    field: 'resourceName',
                    title: '资源名称'
                }, {
                    field: 'resourceUrl',
                    title: '资源地址'
                }/*, {
                    field: 'sex',
                    title: '性别',
                    formatter: function (value, row, index) {
                    	var msg="";
                        if(value==0){
                        	msg="未知"
                        }
                       
                        return msg;
                    }
                }*/, {
                    field: 'resourceDesc',
                    title: '描述'
                }
               ]
            });
    };
    
    function stateFormatter(value, row, index) {
        if (row.roleId!=null &&row.roleId!='') {
            return {
                //disabled: true,
                checked: true
            };
        }else{
        	 return {
                 //disabled: true,
                 checked: false
             };
        }
    }
    
    
    function toBinding() {
    	var $data=$('#cusTable').bootstrapTable('getSelections');
    	if($data.length<=0){
    		alert("请选择资源文件");
    		return false;
    	}
    	
    	var resourceIdArray=new Array();  
    	$($data).each(function(index,value){
    		//alert(index+"   "+value.id)
    		resourceIdArray.push(value.id);//向数组中添加元素  
    	});  
    	var resourceIds=resourceIdArray.join(',');//将数组元素连接起来以构建一个字符串  
    	
    	//alert("resourceIds:"+"    "+resourceIds)
    	
    		$.ajax({
    			type : "POST",
    			url : basePath + "/pc/bindingRoleResources.do",
    			data : {
    				"roleId" : roleId,
    				"resourceIds":resourceIds
    			},
    			async : false,
    			dataType : "json",
    			success : function(result) {
    				$('#bindingModal').modal('hide');
    				doSearch();
    				if(result.rtnCode == 0){
    					alert(result.rtnMsg);
    				}else{
    					alert(result.rtnMsg);
    				}
    			}
    		});
    }
