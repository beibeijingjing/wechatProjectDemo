/**
 * author:Tao Tianran
 */

/** 关键词id */
var keywordId="";

/** 记录当前操作的div */
var th="";

/** 上传成功返回的图片id */
var img_id="";

/** 图片地址 */
var img_url="";

/** cover=flase为非封面页编辑，cover=true为封面页编辑 */
var cover=true;

/** 排序序号 */
var sort=3;

//富文本编辑示例
var editor=null;

/**
 * 右边标题内容显示至左边div
 */
function showTitle(){
	if(cover){
		$("#titleContent").html($("input[name=materialTitle]").val());
	}
	else {
		$(th).find("#childTitle").html($("input[name=materialTitle]").val());
	}
}

/**
 * 返回list页面
 */
function backToList(){
	window.location.href=basePath + "/pc/toGetImgTextMoreList.do";
}

var imgTextMore = {
		init:function(){
			$("#saveBtn").bind("mouseover ",function(){
				$(this).removeClass("btn2Font_02").addClass("btn2Font_03");
			}).bind("mouseout ",function(){
				 $(this).removeClass("btn2Font_03").addClass("btn2Font_02");
			});
			$("#backBtn").bind("mouseover ",function(){
				$(this).removeClass("btn2Font_04").addClass("btn2Font_05");
			}).bind("mouseout ",function(){
				 $(this).removeClass("btn2Font_05").addClass("btn2Font_04");
			});
			imgTextMore.defaultShow();
	  },
		defaultShow :function(){
			var selectObj =$("input[name = 'materialType']:checked").val();
			if(selectObj !=""){
				  if("0001" == selectObj){
						$("#link_url_wx").css('display','block'); 
						$("#imgText_one_wx").css('display','none');
				  }else if("0002" == selectObj){
					  	$("#link_url_wx").css('display','none'); 
						$("#imgText_one_wx").css('display','block');
				  }
			}
		},
		addElement:function(){
			var newEle = "<div id=\"\" sort=\""+(sort++)+"\" allowDel=true  class=\"sub_img_div\" onmouseenter=\"imgTextMore.addIcon(this);\" onmouseleave=\"imgTextMore.delIcon(this);\">"+
						   	       	       "<div  class=\"sub_left\"><span id=\"childTitle\" class=\"sub_title\">标题</span></div>"+
										   "<div  class=\"sub_right\">"+
										   "<img id=\"smallImage\" style=\"width:80px;\" name=\"smallImage\" src='"+basePath+"/weixin/Images/suoluetu.jpg"+"'>"+
										   "</div>"+
						   	       "</div>"+	
									"<div style=\"clear:both;\"></div>";
				$("#addNewEle").before(newEle);
		},
		appendElement:function(id,title,imgUrl){
			var newEle = "<div id=\""+id+"\" sort=\""+(sort++)+"\" allowDel=true  class=\"sub_img_div\" onmouseenter=\"imgTextMore.addIcon(this);\" onmouseleave=\"imgTextMore.delIcon(this);\">"+
						   	       	       "<div  class=\"sub_left\"><span id=\"childTitle\" class=\"sub_title\">"+title+"</span></div>"+
										   "<div  class=\"sub_right\">"+
										   "<img id=\"smallImage\" style=\"width:80px;\" name=\"smallImage\" src='"+imgUrl+"'>"+
										   "</div>"+
						   	       "</div>"+	
									"<div style=\"clear:both;\"></div>";
				$("#addNewEle").before(newEle);
		},
		addIcon:function(obj){
			$(obj).mask(function(){//点击编辑响应事件
				
				editResponse(obj,false);
			
			},function(){//点击删除响应事件
				var isAllowDel = $(obj).attr("allowDel");
			    if(isAllowDel == true || "true" ==isAllowDel ) {
					if($(obj).attr("id")==""||$(obj).attr("id")==null){//如果删除项还未保存过
						$(obj).remove();
					}
					else{
						doManager("imgTxtReplyManager","deleteImgTxt",$(obj).attr("id"),function(_response){
							if (_response.result) {
								$(obj).remove();
							}
						});
					}
				}
				else{
					if ($(obj).attr("id") != "" && $(obj).attr("id") != null&&$(obj).attr("id") !="childEle") {
						doManager("imgTxtReplyManager", "deleteImgTxt", $(obj).attr("id"), function(_response){
							if (_response.result) {
								clearRightImg();
								clearAll();
								$(obj).attr("id", "childEle");
								$(obj).find("#childTitle").html("标题");
								$(obj).find("#smallImage").attr("src", basePath+"/weixin/Images/suoluetu.jpg");
							}
						});
					}
				}
			});
		},
		
		delIcon:function(obj){
			$(obj).unmask();
		},
		addFirstIcon:function(obj){
			$(obj).mask(function(){//点击封面编辑响应事件
				editResponse(obj,true);
			},function(){
				var isAllowDel = $(obj).attr("allowDel");
			    if(isAllowDel == true || "true" ==isAllowDel ) {
					$(obj).remove();
				}
			},"true" );
		}
	}

/**
 * 点击左侧编辑响应函数
 */	
function editResponse(obj,flag){
	endEdit();
	clearAll();
	cover=flag;
	clearRightImg();
	th=obj;
	underEdit();
	//alert("id:"+$(th).attr("id"))
	getInfoById($(th).attr("id"),function(_value){
		//通过id获取信息并回显
		if(_value.id!=null||_value.id!=""){
			img_id=_value.thumb_media_id;
			img_url=_value.thumb_media_url;
			$("input[name=sort]").val(_value.article_order);
			$("input[name=materialTitle]").val(_value.title);
			$("#abstractContent").val(_value.digest);
			$("input[name=materialUrl]").val(_value.content_source_url);
			editor.insertHtml(_value.content);
			if (_value.content != null) {
				urlOrTxt(false);
			}else {
				urlOrTxt(true);
			}
			if(img_id!=null&&img_id!=""){
				showImg();
			 }
	}
	
	});
}

/**
 * 通过图文id返回对应图文实体
 */
function getInfoById(imgTxtId,callback){
	if(imgTxtId=='topcover'){
		imgTxtId=$('#parentId').val();
	}
	if(imgTxtId=='childEle'){
		imgTxtId=$('#childId').val();
	}
	if(imgTxtId==null||imgTxtId==''||imgTxtId=='0'){
		return false;
	}
	
	$('#hasId').val(imgTxtId);
	//通过异步获取对应图文信息并回显
	$.ajax({
		type : "GET",
		url : basePath + '/pc/getImgText.do?id='+imgTxtId,
		async : true,
		dataType : "json",
		success : function(result) {
			//alert(JSON.stringify(result.data))
			if(result.rtnCode == 0){
				if ($.isFunction(callback)) {
					callback(result.data);
				}
			}
		}
	});
}


/**
 * 不同编辑下初始化环境
 */
function clearAll(){
	img_id="";
	img_url="";
	$("#haveImg").val("");
	$("input[name=materialTitle]").val("");
	$("input[name=imgFile]").val("");
	$("input[name=hasId]").val("");
	$("input[name=materialUrl]").val("");
	$("input[name=sort]").val("");
	$("#abstractContent").val("");
	$("#returnContent").val("");
}

/**
 * 设置单选值
 */
function selectUrlOrTxt(){
	if($("input[type='radio']").eq(0).attr("checked")==true){
		
		setRadioVal("materialType","0001");
		$("#materialContent").val("");
	}else{
		setRadioVal("materialType","0002");
		$("#materialUrl").val("");
	}
}

function setRadioVal(name,vl){
	$('input[type=radio][name='+name+'][value='+vl+']').attr("checked");
	
}
	


function ajax_encode(str)
{	
	if(str!=null&&str!=''){
		str = str.replace(/%/g,"{@bai@}");
	    str = str.replace(/ /g,"{@kong@}");
	    str = str.replace(/</g,"{@zuojian@}");
	    str = str.replace(/>/g,"{@youjian@}");
	    str = str.replace(/&/g,"{@and@}");
	    str = str.replace(/\"/g,"{@shuang@}");
	    str = str.replace(/\'/g,"{@dan@}");
	    str = str.replace(/\t/g,"{@tab@}");
	    str = str.replace(/\+/g,"{@jia@}");
	}
    
    return str;
}

//转义
function HTMLEncode(html) {
    var temp = document.createElement("div");
    (temp.textContent != null) ? (temp.textContent = html) : (temp.innerText = html);
    var output = temp.innerHTML;
    temp = null;
    return output;
}
//反转义
function HTMLDecode(text) { 
    var temp = document.createElement("div"); 
    temp.innerHTML = text; 
    var output = temp.innerText || temp.textContent; 
    temp = null; 
    return output; 
} 

/**
 * 图片上传
 */
function uploadFiles(uploadName, callback){
	var inputFile = $("#" + uploadName).val();
    if (inputFile == "") {
    	alert("请选择上传图片");
        return;
    }
	
    $.ajaxFileUpload({
        url: basePath + '/pc/common/uploadWxServer.do?fileType=0',
        secureuri: false,
        fileElementId: uploadName,
        dataType: 'json',
        success: function(data, status){
            if (data.message == "File suffix is not allowed.") {
                //$("#loadDiv").remove();
                alert("文件格式不正确");
                return;
            }
            if (data.message == "File size is too large.") {
                alert("文件太大");
                return;
            }
            var attInfo = jQuery.parseJSON(data);
			if($.isFunction(callback)){
				callback(attInfo);
			}
        },
        error: function(data, status, e){
        	alert(e)
        }
    })
}


/**
 * 点击上传响应事件
 */
function uploadImg(){
	uploadFiles("imgFile",function(data){
		if(cover){//封面图片显示
			if(data.mediaId !=null && data.mediaId !=""){
				imgOrCover(true);
				var imgPath=data.url;
				$("#coverImage").attr('src', HTMLDecode(imgPath));
				img_id = data.mediaId;
				img_url=$("#coverImage").attr("src");
				$("#haveImg").val(img_id);
				showImg();
			}
		}
		else if(!cover){//其余图片显示
				if (data.mediaId != null && data.mediaId != "") {
					var imgPath=data.url;
					$(th).find("#smallImage").attr('src', HTMLDecode(imgPath));
					img_id = data.mediaId;
					img_url=$(th).find("#smallImage").attr("src");
					$("#haveImg").val(img_id);
					showImg();
				}
		}
	});
}



/**
 * first_title_Img和coverImage切换
 * flag=true     显示img
 * flag=false    显示cover
 */
function clearAll(){
	img_id="";
	img_url="";
	$("#haveImg").val("");
	$("input[name=materialTitle]").val("");
	$("input[name=imgFile]").val("");
	$("input[name=hasId]").val("");
	$("input[name=materialUrl]").val("");
	$("input[name=sort]").val("");
	$("#abstractContent").val("");
	//初始化文本框
	editor.setData("");
}
	


function imgOrCover(flag){
	if(flag){
		$("#first_title_Img").css('display', 'none');
		$("#coverImage").css('display', 'block');
	}else{
		$("#first_title_Img").css('display', 'block');
		$("#coverImage").css('display', 'none');
	}
}



/**
 * 设置单选值
 */
function selectUrlOrTxt(){
	if($("input[type='radio']").eq(0).attr("checked")==true){
		setRadioVal("materialType","0001");
	}else{
		setRadioVal("materialType","0002");
	}
}

function setRadioVal(name,vl){
	$('input[type=radio][name='+name+'][value='+vl+']').attr("checked");
}
	

/**
 * 保存当前编辑的图文实体
 */
function doSubmit(){ 
	var number=0;
	var id="";
	var parentId="";
	selectUrlOrTxt();
	if(cover){
		number=1;
		id=$('#parentId').val();
		parentId='0';
	}
	else{
		parentId=$('#parentId').val();
			if($(th).attr("id")=="childEle"){
				number=2;
				id=$('#childId').val();
			}
			else if($("input[name=sort]").val()==""||$("input[name=sort]").val()==null){
				number=parseInt($(th).attr("sort"));
				id=$("input[name=hasId]").val();
			}
			else{
				number=$("input[name=sort]").val();
				id=$("input[name=hasId]").val();
			}
	}
	
	//提交信息
	submitInfo(id,number,parentId);
}


function submitInfo(id,number,parentId){
	var content=editor.getData();
	//判断封面编辑信息是否保存
	if(!cover){
		var parentId=$('#parentId').val();
		if(parentId=='0'){
			alert('请先编辑保存封面信息！');
			return false;
		}
	}
	
	$.ajax({
		type : "POST",
		url : basePath + "/pc/addImgTextMore.do",
		data : {
			"id":id,
			"title" : $("input[name=materialTitle]").val(),
			"parent_id":parentId,
			"thumb_media_id":img_id,
			"thumb_media_url":img_url,
			"author":"",
			"digest":$("#abstractContent").val(),
			"content":ajax_encode(content),
			"content_source_url":$("input[name=materialUrl]").val(),
			"article_order":number
		},
		async : true,
		dataType : "json",
		success : function(result) {
			if(result.rtnCode == 0){
				$('#hasId').val(result.id);
				if(number==1){
					//设置parentId
					$('#parentId').val(result.id);
				}else if(number==2){
					//设置childId
					$('#childId').val(result.id);
				}else{
					$(th).attr("id",result.id);
				}
				alert(result.rtnMsg);
			}else{
				alert(result.rtnMsg);
			}
		}
	});
	
}

function ajax_encode(str)
{	
	if(str!=null&&str!=''){
		str = str.replace(/%/g,"{@bai@}");
	    str = str.replace(/ /g,"{@kong@}");
	    str = str.replace(/</g,"{@zuojian@}");
	    str = str.replace(/>/g,"{@youjian@}");
	    str = str.replace(/&/g,"{@and@}");
	    str = str.replace(/\"/g,"{@shuang@}");
	    str = str.replace(/\'/g,"{@dan@}");
	    str = str.replace(/\t/g,"{@tab@}");
	    str = str.replace(/\+/g,"{@jia@}");
	}
    
    return str;
}

//转义
function HTMLEncode(html) {
    var temp = document.createElement("div");
    (temp.textContent != null) ? (temp.textContent = html) : (temp.innerText = html);
    var output = temp.innerHTML;
    temp = null;
    return output;
}
//反转义
function HTMLDecode(text) { 
    var temp = document.createElement("div"); 
    temp.innerHTML = text; 
    var output = temp.innerText || temp.textContent; 
    temp = null; 
    return output; 
} 

/**
 * 图片上传
 */
function uploadFiles(uploadName, callback){
	var inputFile = $("#" + uploadName).val();
    if (inputFile == "") {
    	alert("请选择上传图片");
        return;
    }
	
    $.ajaxFileUpload({
        url: basePath + '/pc/common/uploadWxServer.do?fileType=0',
        secureuri: false,
        fileElementId: uploadName,
        dataType: 'json',
        success: function(data, status){
            if (data.message == "File suffix is not allowed.") {
                //$("#loadDiv").remove();
                alert("文件格式不正确");
                return;
            }
            if (data.message == "File size is too large.") {
                alert("文件太大");
                return;
            }
            var attInfo = jQuery.parseJSON(data);
			if($.isFunction(callback)){
				callback(attInfo);
			}
        },
        error: function(data, status, e){
        	alert(e)
        }
    })
}


/**
 * 显示右侧小图片
 */	
function showImg(){
	$("#coverImg").css('display','block');	
	if($("#haveImg").val()!==""){
		$("p").remove(".pclass");
	}

	var newImg ='<p class="pclass"><img class="imgClass" src="';
	newImg+=img_url;
	newImg+='"/><a onclick="deleteImg()" class="deleteImg">删除</a></p>';
	$("#imgBox").append(newImg);
	
	$("#haveImg").val("1");
}


/**
 * link_url_wx和imgText_one_wx切换
 * flag=true     显示URL输入框
 * flag=false    显示正文输入框
 */
function urlOrTxt(flag){
	if(flag){
		$("#link_url_wx").css('display', 'block');
		$("#imgText_one_wx").css('display', 'none');
		$("input[type='radio']").eq(0).attr("checked", true);
	}
	else{
		$("#link_url_wx").css('display', 'none');
		$("#imgText_one_wx").css('display', 'block');
		$("input[type='radio']").eq(1).attr("checked", true);	
	}
}


/**
 * 根据图文实体填写页面
 */
function editInit(value){
	$("#materialTitle").val(value.materialTitle);
	$("#abstractContent").val(value.abstractContent);
	setRadioVal("materialType",value.materialType);
	if (value.materialType == '0001') {
		urlOrTxt(true);
		$("#materialUrl").val(value.materialUrl);
	}
	else{
		urlOrTxt(false);
		ue.setContent(value.materialContent);
	}
}

/**
 * 新建初始化 
 */
function createImgTxtInit(){
	imgTextMore.init();
	th="#topcover";
	cover=true;
	sort=3;
	imgOrCover(false);
	urlOrTxt(true);
	$("input[type='radio']").eq(0).attr("checked", true);
}


/**
 * 清除上一个编辑图文选中效果
 */
function endEdit(){
	$(th).css("box-shadow","0 0 0 #000000 inset");
	$(th).css("-webkit-box-shadow","0 0 0 #000000 inset");
	$(th).css("-moz-box-shadow","0 0 0 #000000 inset");
}

/**
 * 设置当前编辑图文选中效果
 */
function underEdit(){
	$(th).css("box-shadow","0 0 20px #000000 inset");
	$(th).css("-webkit-box-shadow","0 0 10px #000000 inset");
	$(th).css("-moz-box-shadow","0 0 10px #000000 inset");
}

/**
 * 清空右侧小图片
 */
function clearRightImg(){
	img_id = "";
	img_url = "";
	$("p").remove(".pclass");
	$("input[name=haveImg]").val("");
}

/**
 * 删除右侧小图片
 */	
function deleteImg(){
	img_id = "";
	img_url = "";
	if (cover) {
		$("#coverImage").attr('src',"");
		imgOrCover(false);
	}
	else if(!cover){
		$(th).find("#smallImage").attr('src',"../../Images/suoluetu.jpg")
	}
	$("p").remove(".pclass");
	$("input[name=haveImg]").val("");
}

function init(){
	$("input[name=hasId]").val("");
	$("input[name=sort]").val("");
	$("input[name=element]").val("");
	createImgTxtInit();
	//初始化编辑器
	editor = CKEDITOR.replace("returnContent");
}
	
