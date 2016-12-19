var KeywordId="";
var op="";
var img_id=null;
var img_path=null;

function showTitle(){
	$("#titleContent").html("   "+$("input[name=materialTitle]").val());
}

function backToList(){
       Base.goPage(baseUrl + "/weixinMgrPlatform/imageTextKeyword/imageTextKeywordList.html");
}


function uploadImg(){	

	uploadFiles("imgFile",function(data){
		$("input[name=imageUrl]").val("");
		img_id=data.id;
		$(".left_top_img_style").css('display','none');
		img_path=baseUrl + '/showerAction.action?id=' +data.id+ '&skip=true';
		$('#coverImg').attr('src',img_path );
		$("#imageUrl").val(img_path);
		showImg();	
		});
}

function showImg(){
	$("#coverImg").css('display','block');	
	if($("#haveImg").val()==""){
		
	}
	else{
		$("p").remove(".pclass");
	}
	
	$("#haveImg").val("1");
	var newImg ='<p class="pclass"><img class="imgClass" src="';
	newImg+=$("#imageUrl").val();
	newImg+='"/><a onclick="deleteImg()" class="deleteImg">删除</a></p>';
	$("#imgBox").append(newImg);
}

function deleteImg(){
	img_id=null;
	img_path=null;
	$("#coverImg").css('display','none');	
	$("p").remove(".pclass");
	$("input[name=imageUrl]").val("");
	$("input[name=haveImg]").val("");
	$('#coverImg').attr('src',"");
	$(".left_top_img_style").css('display','block');
}

var imgTextOne = {	
	init:function(){
			$("#materialUrl").attr("checked","checked");
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
            imgTextOne.defaultShow();
	},
	defaultShow :function(){
		var selectObj = "";
		$("input[name = 'materialType']").each(function(){
			  var _this = this;
			  var sel = $(_this).attr("checked");
			  if(sel == true){
			  	selectObj = $(_this).val();
			  }
		});
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
	submit:function(){		
		if ($("input:radio:checked").val() == "0002") {
			$("input[name=materialUrl]").val("");
		}
		else {
			ue.setContent("");
		}
		var obj={
			imgId:img_id,
			imgUrl:img_path,
			keywordId:KeywordId,
			articleIndex:1,
			materialTitle:$("input[name=materialTitle]").val(),
			abstractContent:$("#abstractContent").val(),
			materialType:$("input:radio:checked").val(),
			materialContent:ue.getContent(),
			materialUrl:$("input[name=materialUrl]").val(),
		}
		if (op == 'new') {
			doManager("imgTxtReplyManager", "addImgText", $.toJSON(obj), function(_data){
				//Base.alert("新建成功!");
				Base.goPage(baseUrl + "/weixinMgrPlatform/imageTextKeyword/imageTextKeywordList.html");
			});
		}
		else 
			if (op == 'edit') {
				doManager("imgTxtReplyManager", "updateImgText", $.toJSON(obj), function(_data){
					//Base.alert("编辑成功!");
					Base.goPage(baseUrl + "/weixinMgrPlatform/imageTextKeyword/imageTextKeywordList.html");
				});
			}
	}
}


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


$(function(){
	img_id=null;
	img_path=null;
	$("#coverImg").css('display','none');	
	op=getUrlParamByKey('operator');
	KeywordId=getUrlParamByKey('id');
	
	if(op=='edit'){
		imgTextOne.init();
		doManager("imgTxtReplyManager","getInfoById",KeywordId,function(_value){
		var _result =  $.fromJSON(_value.data);
		if (_result != "" && _result != null) {
				    img_id = _result.imgId;
				    var title=$.fromJSON(_value.data).materialTitle;
				    if(title!=null&&title!=""){
				    	$("#titleContent").html(" "+title);
					}
					$("input[name=materialTitle]").val($.fromJSON(_value.data).materialTitle);
				    $("#abstractContent").val($.fromJSON(_value.data).abstractContent);
					if ($.fromJSON(_value.data).materialType == '0001') {//url类型
						$("input[type='radio']").eq(0).attr("checked", true);
						$("#link_url_wx").css('display', 'block');
						$("#imgText_one_wx").css('display', 'none');
						$("input[name=materialUrl]").val($.fromJSON(_value.data).materialUrl);
					}
					else {//文本类型
						$("#link_url_wx").css('display', 'none');
						$("#imgText_one_wx").css('display', 'block');
						$("input[type='radio']").eq(1).attr("checked", true);
						ue.setContent($.fromJSON(_value.data).materialContent);
					}
				
					if (img_id != null) {//图片id不为空则显示图片
					 	$("input[name=haveImg]").val("1");
						$("#coverImg").css('display', 'block');
						$(".left_top_img_style").css('display', 'none');
						$('#coverImg').attr('src', baseUrl + '/showerAction.action?id=' + img_id + '&skip=true');
						img_path = $("#coverImg").attr("src");
					
						var newImg = "<p class='pclass'><img class='imgClass' src='";
						newImg += img_path;
						newImg += "'/><a onclick='deleteImg()' class='deleteImg'>删除</a></p>";
						$("#imgBox").append(newImg);
					 } 
			}
			else{
				img_id=null;
				$("input[type='radio']").eq(0).attr("checked", true);
				$("#link_url_wx").css('display', 'block');
				$("#imgText_one_wx").css('display', 'none');
				op="new";
			}
		});
	}
	else if(op=='new')
	{
		$("#link_url_wx").css('display','block'); 
		$("#imgText_one_wx").css('display','none');
		$("input[type='radio']").eq(0).attr("checked", true);
		$("input[name=haveImg]").val("");
		imgTextOne.init();
	}
})


