var KeywordId="";
var op="";
var img_id=null;
var img_path=null;

function showTitle(){
	$("#titleContent").html("   "+$("input[name=materialTitle]").val());
}

function backToList(){
	window.location.href=basePath + "/pc/toGetImgTextOneList.do";
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

function uploadImg(){	

	uploadFiles("imgFile",function(data){
		$("input[name=imageUrl]").val("");
		img_id=data.mediaId;
		$(".left_top_img_style").css('display','none');
		img_path=data.url;
		/*alert(img_path)
		alert(HTMLDecode(img_path))*/
		$('#coverImg').attr('src',HTMLDecode(img_path));
		$("#imageUrl").val(img_path);
		showImg();	
	});
}
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
			//$("#materialUrl").attr("checked","checked");
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
	
	submit:function(){		
		if ($("input:radio:checked").val() == "0002") {
			$("input[name=materialUrl]").val("");
		}
		else {
			$("input[name=returnContent]").val("");
		}
		//提交页面信息
		submitInfo();
	}
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

//提交信息
function submitInfo(){
	var content=CKEDITOR.instances.returnContent.getData();
	$.ajax({
		type : "POST",
		url : basePath + "/pc/updateImgTextOne.do",
		data : {
			"article_id":$('#articelId').val(),
			"title" : $("input[name=materialTitle]").val(),
			"thumb_media_id":img_id,
			"thumb_media_url":img_path,
			"author":"",
			"digest":$("#abstractContent").val(),
			"content":ajax_encode(content),
			"content_source_url":$("input[name=materialUrl]").val()
		},
		async : false,
		dataType : "json",
		success : function(result) {
			if(result.rtnCode == 0){
				alert(result.rtnMsg);
			}else{
				alert(result.rtnMsg);
			}
			window.location.href=basePath + "/pc/toGetImgTextOneList.do";
		}
	});
}


