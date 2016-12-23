/**
 * author:Tao Tianran
 */

/** op='edit'编辑页面||op='new'新建页面*/
var op=getUrlParamByKey("operator");

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
       Base.goPage(baseUrl + "/weixinMgrPlatform/imageTextKeyword/imageTextKeywordList.html");
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
			var selectObj = "";
			$("input[name=materialType]").each(function(){
				  var _this = this;
				  var sel = $(_this).attr("checked");
				  if(sel == true){
				  	selectObj = $(_this).val();
				  }
			});
			if(selectObj !=""){
				  if("0001" == selectObj){
					urlOrTxt(true);
						
				  }else if("0002" == selectObj){
					urlOrTxt(false);
				  }
			}
		},
		addElement:function(){
			var newEle = "<div id=\"\" sort=\""+(sort++)+"\" allowDel=true  class=\"sub_img_div\" onmouseenter=\"imgTextMore.addIcon(this);\" onmouseleave=\"imgTextMore.delIcon(this);\">"+
						   	       	       "<div  class=\"sub_left\"><span id=\"childTitle\" class=\"sub_title\">标题</span></div>"+
										   "<div  class=\"sub_right\">"+
										   	   "<img id=\"smallImage\" style=\"width:80px;\" name=\"smallImage\" src=\"../../Images/suoluetu.jpg\">"+
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
								$(obj).find("#smallImage").attr("src", "../../Images/suoluetu.jpg");
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
	$("input[name=hasId]").val($(th).attr("id"));
	getInfoById($(th).attr("id"),function(_value){
	if(_value.id!=null||_value.id!=""){
		img_id=_value.imgId;
		img_url=_value.imgUrl;
		$("input[name=sort]").val(_value.articleIndex);
		$("input[name=materialTitle]").val(_value.materialTitle);
		$("#abstractContent").val(_value.abstractContent);
		//alert($(th).attr("id"))
		if(_value.imgId!=null&&_value.imgId!=""){
			if(_value.articleIndex==1){
				$(th).find("#coverImage").attr("src",baseUrl + '/showerAction.action?id=' +_value.imgId+ '&skip=true');
			}
			else{
				$(th).find("#smallImage").attr("src",baseUrl + '/showerAction.action?id=' +_value.imgId+ '&skip=true');
			}
		}
		if (_value.materialContent != null) {
			ue.setContent(_value.materialContent);
			urlOrTxt(false);
			$("input[type='radio']").eq(1).attr("checked", true);
			}
		else {
			$("input[name=materialUrl]").val(_value.materialUrl);
			urlOrTxt(true);
			$("input[type='radio']").eq(0).attr("checked", true);
			}
		if(img_id!=null&&img_id!=""){
			showImg();
		    }
		}
	else{
						
		}
	});
}

/**
 * 设置单选值
 */
function selectUrlOrTxt(){
	if($("input[type='radio']").eq(0).attr("checked")==true){
		
		setRadioVal("materialType","0001");
		ue.setContent("");
	}else{
		setRadioVal("materialType","0002");
		$("#materialUrl").val("");
	}
}
	
/**
 * 保存当前编辑的图文实体
 */
function doSubmit(){ 
	//alert($("#materialTitle").val())
	var number=0;
	var hasId=false;
	selectUrlOrTxt();
	if($("input[name=hasId]").val()==null && $("input[name=hasId]").val()==""||$("input[name=hasId]").val()=="topcover"||$("input[name=hasId]").val()=="childEle"){
		$("input[name=hasId]").val("");
		hasId=false;
	}
	else{
		hasId=true;
	}
	if(cover){
		number=1;
		}
	else{
			if($(th).attr("id")=="childEle"){
				number=2;
			}
			else if($("input[name=sort]").val()==""||$("input[name=sort]").val()==null){
				number=parseInt($(th).attr("sort"));
			}
			else{
				number=$("input[name=sort]").val();
			}
	}
	var returnObj = {
		id:hasId?$("input[name=hasId]").val():null,
		keywordId : getUrlParamByKey("keywordId"),
		imgId:img_id,
		imgUrl:img_url,
		articleIndex:number,
		materialTitle:$("#materialTitle").val(),
		abstractContent: $("#abstractContent").val(),
		materialContent: ue.getContent(),
		materialUrl:$("#materialUrl").val(),
		materialType:getRadioVal("materialType")
	}
	//alert($.toJSON(returnObj))
	if (op == 'new') {//新建界面
		if (returnObj.id==null||returnObj.id=="") {//如果是新建第一次编辑调用add
			doManager("imgTxtReplyManager", "addImgText", returnObj, function(_response){
				if (_response.result) {
					 var result =$.fromJSON(_response.data);
				  	 $("input[name=hasId]").val($.toJSON(result.id));
				  	 $(th).attr("id",$.toJSON(result.id));
					 th="#"+$.toJSON(result.id);
					 $("input[name=sort]").val(result.articleIndex);
					 Base.alert("新建添加成功！")
				}
			});
		}
		else{//如果是新建第二次编辑调用update
			doManager("imgTxtReplyManager", "updateImgTxtByImgTxtId", returnObj, function(_response){
				if (_response.result) {
					Base.alert("新建编辑成功！")
				    var result =$.fromJSON(_response.data);
				    $("input[name=hasId]").val($.toJSON(result.id));
				}
			});
		}
	}
	else if(op=='edit'){//编辑界面
		if ($(th).attr("id") != null && $(th).attr("id") !=""&& $(th).attr("id") !="topcover" && $(th).attr("id")!="childEle") {//如果编辑是在原来数据上更新
			doManager("imgTxtReplyManager", "updateImgTxtByImgTxtId", returnObj, function(_response){
				    Base.alert("编辑更新成功！")
				    var result =$.fromJSON(_response.data);
				    $("input[name=hasId]").val($.toJSON(result.id));
					$(th).attr("id",$.toJSON(result.id));
			});
		}
		else{
			doManager("imgTxtReplyManager", "addImgText", returnObj, function(_response){
				  
				   var result =$.fromJSON(_response.data);
				   $("input[name=hasId]").val($.toJSON(result.id));
				   $("input[name=sort]").val(result.articleIndex); 
				   $(th).attr("id",$.toJSON(result.id));
				   th="#"+$.toJSON(result.id);
				   Base.alert("编辑新建成功！")
			});
		}
	}
}

/**
 * 图片上传
 */
function uploadFiles(uploadName, callback){
	var inputFile = $("#" + uploadName).val();
    if (inputFile == "") {
        $$.showMessage('${sup.supManager.submit.warning}', '${sup.supUpload.choiceFile}');
        return;
    }
    $.ajaxFileUpload({
        url: baseUrl + '/uploaderAction.action?businessType=wcmImage',
        secureuri: false,
        fileElementId: uploadName,
        dataType: 'json',
        success: function(data, status){
            if (data.message == "File suffix is not allowed.") {
                $("#loadDiv").remove();
				$$.showMessage('${query.ui.prompt}','${sup.supUpload.uploadInfoOne}');
                return;
            }
            if (data.message == "File size is too large.") {
                $("#loadDiv").remove();
                $$.showMessage('${query.ui.prompt}', '${sup.supUpload.uploadInfoTwo}');
                return;
            }
            var attInfo = $.fromJSON(data.data);
			if($.isFunction(callback)){
				callback(attInfo);
			}
        },
        error: function(data, status, e){
            $("#loadDiv").remove();
            $$.showMessage('${query.ui.prompt}', e);
        }
    })
}

/**
 * 点击上传响应事件
 */
function uploadImg(){
	uploadFiles("imgFile",function(data){
		if(cover){//封面图片显示
			if(data.id !=null && data.id !=""){
				imgOrCover(true);
				$("#coverImage").attr('src', baseUrl + '/showerAction.action?id=' + data.id + '&skip=true');
				img_id = data.id;
				img_url=$("#coverImage").attr("src");
				$("#haveImg").val(img_id);
				showImg();
				}
		}
		else if(!cover){//其余图片显示
				if (data.id != null && data.id != "") {
					$(th).find("#smallImage").attr('src',baseUrl + '/showerAction.action?id=' + data.id + '&skip=true');
					img_id=data.id;
					img_url=$(th).find("#smallImage").attr("src");
					$("#haveImg").val(img_id);
					showImg();
				}
		}
	});
}

/**
 * 通过图文id返回对应图文实体
 */
function getInfoById(imgTxtId,callback){
	if(imgTxtId=="topcover"||imgTxtId=="childEle"){
		imgTxtId="";
	}
	var obj={
		id:imgTxtId
	}
	doManager("imgTxtReplyManager","getImgTxtByImgTxtId",$.toJSON(obj),function(_value){
		if (_value.result) {
			var results = $.fromJSON(_value.data);
			if ($.isFunction(callback)) {
				callback(results);
			}
		}
		else {
			Base.alert('${system.operator.error}');
		}
        }, true, {
            showWaiting: true			
	});
}


/**
 * 不同编辑下初始化环境
 */
function clearAll(){
	//alert($(th).attr("id"))
	if ($(th).attr("id") == "" || $(th).attr("id") == null||$(th).attr("id") == "topcover"||$(th).attr("id") == "childEle"  ) {
			if (cover) {
				$("#titleContent").html("");
				imgOrCover(false);
				$("#coverImage").attr('src',"");
			}
			else if(!cover){
				$(th).find("#childTitle").html("标题");
				$(th).find("#smallImage").attr('src',"../../Images/suoluetu.jpg");
			}
		}
	img_id="";
	img_url="";
	$("#haveImg").val("");
	$("input[name=materialTitle]").val("");
	$("input[name=imgFile]").val("");
	$("input[name=hasId]").val("");
	$("input[name=materialUrl]").val("");
	$("input[name=sort]").val("");
	$("#abstractContent").val("");
	ue.setContent("");
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
 * first_title_Img和coverImage切换
 * flag=true     显示img
 * flag=false    显示cover
 */
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
 * 编辑初始化
 */
function editImgTxtInit(){
	imgTextMore.init();
	cover=true;
	imgOrCover(false);
	urlOrTxt(true);	
	$("input[type='radio']").eq(0).attr("checked", true);
	doManager("imgTxtReplyManager","getAllImgTxtByKeywordId",keywordId,function(_response){
	
		if (_response.data != null) {		
				th = "#topcover";             
				var value = $.fromJSON(_response.data);
				var length = value.length;
			
				if(length>1){
					sort =parseInt(value[length-1].articleIndex) + 1;
				}
				else{
					sort=3;
				}
		
				if (_response.result) {
					if (value!=null&&value!="") {
						if (value[0].imgId!=null && value[0].imgId!= ""&&value[0].articleIndex=='1') {//如果图片地址不为空并且是封面
							imgOrCover(true);
							$("#coverImage").attr('src', baseUrl + '/showerAction.action?id=' + value[0].imgId + '&skip=true');
						}
						else{
							imgOrCover(false);
						}
						for (var i = 0; i < length; i++) {
							if (value[i].articleIndex=='1') {//如果是封面
								$("input[name=sort]").val(1);
								$("input[name=hasId]").val(value[i].id);
								$("#topcover").attr('id', value[i].id);
								th = "#" + value[i].id;
								$("#titleContent").html(value[i].materialTitle);
								img_url=value[i].imgUrl;
								img_id=value[i].imgId;
								if(img_id!=null&&img_id!=""){
									showImg();
								}
								editInit(value[i]);
							}
							else 
								if (value[i].articleIndex=='2') {
									$("#childEle").attr('id', value[i].id);
									if (value[i].materialTitle != null && value[i].materialTitle != "") {
										$("#childTitle").html(value[i].materialTitle);
									}
									if (value[i].imgId != null && value[i].imgId != "") {
										$("#smallImage").attr('src', baseUrl + '/showerAction.action?id=' + value[i].imgId + '&skip=true');
										
									}
								}
								else {
									var ct= "标题";
									var iu = "../../Images/suoluetu.jpg";
									if (value[i].materialTitle != null && value[i].materialTitle != "") {
										ct = value[i].materialTitle;
									}
									if (value[i].imgId != null && value[i].imgId != "") {
										iu = baseUrl + '/showerAction.action?id=' + value[i].imgId + '&skip=true';
									}
									
									var newEle = "<div sort=\""+value[i].articleIndex+"\" id=\"" + value[i].id + "\" allowDel=true  class=\"sub_img_div\" onmouseenter=\"imgTextMore.addIcon(this);\" onmouseleave=\"imgTextMore.delIcon(this);\">" +
									"<div  class=\"sub_left\"><span id=\"childTitle\" class=\"sub_title\">" +
									ct +
									"</span></div>" +
									"<div  class=\"sub_right\">" +
									"<img id=\"smallImage\" style=\"width:80px;\" name=\"smallImage\" src=\"" +
									iu +
									"\">" +
									"</div>" +
									"</div>" +
									"<div style=\"clear:both;\"></div>";
									$("#addNewEle").before(newEle);
								}
						}
					}
				}
			}		
		});
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
	newImg+=baseUrl + '/showerAction.action?id=' + img_id + '&skip=true';
	newImg+='"/><a onclick="deleteImg()" class="deleteImg">删除</a></p>';
	$("#imgBox").append(newImg);
	
	$("#haveImg").val("1");
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

$(function(){
	$("input[name=hasId]").val("");
	$("input[name=sort]").val("");
	$("input[name=element]").val("");
	keywordId=getUrlParamByKey("keywordId");
	if(op=='new'){//新建多图文
		createImgTxtInit();
	}
	else if(op=='edit'){//编辑多图文
		editImgTxtInit();
	}
})
	