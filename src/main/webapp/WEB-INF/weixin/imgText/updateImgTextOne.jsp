<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String sessionId="";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	 	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	 	<script type="text/javascript" src="<%=path%>/weixin/Js/jquery.js"></script>
		<script src="<%=path%>/weixin/Js/ajaxfileupload.js"></script>
		<script type="text/javascript" src="<%=path%>/weixin/Js/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="<%=path%>/weixin/Js/imgText/updateImgTextOne.js"></script>
		<link type="text/css" rel="stylesheet" href="<%=path%>/weixin/Css/imgText/updateImgTextOne.css" />
		<title>单图文回复</title>
	</head>
	<body>
	<script type="text/javascript">
  		var basePath='<%=path%>';
  		var sessionId='<%=sessionId%>';
  		
  		$(function(){
  			$("#coverImg").css('display','none');	
  		    var linkUrl='${imgText.content_source_url}';
  			if (linkUrl!=null&&linkUrl!='') {//url类型
  				$("input[type='radio']").eq(0).attr("checked", true);
  				$("#link_url_wx").css('display', 'block');
  				$("#imgText_one_wx").css('display', 'none');
  			}else {//文本类型
  				$("#link_url_wx").css('display', 'none');
  				$("#imgText_one_wx").css('display', 'block');
  				$("input[type='radio']").eq(1).attr("checked", true);
  			}
  			img_id ='${imgText.thumb_media_id}';
  			if (img_id != null) {//图片id不为空则显示图片
  				img_path = '${imgText.thumb_media_url}';
  			 	$("input[name=haveImg]").val("1");
  				$("#coverImg").css('display', 'block');
  				$(".left_top_img_style").css('display', 'none');
  				$('#coverImg').attr('src', HTMLDecode(img_path));
  			
  				var newImg = "<p class='pclass'><img class='imgClass' src='";
  				newImg += HTMLDecode(img_path);
  				newImg += "'/><a onclick='deleteImg()' class='deleteImg'>删除</a></p>";
  				$("#imgBox").append(newImg);
  			 } 
  			//初始化
  			imgTextOne.init();
  			
  		})
 	</script>
 	
	     <div id="alert" class="alert" style="display:none">sss</div>
		 <div id="main_content" class="main_content">
		 	   <div id="main_title" class="main_title">图文管理：单图文管理 > 修改单图文回复 </div>
			   <table width="100%";cellpadding="0"; cellspacing="0">
			   	<tr>
			   		<td width="360px" valign="top">
			   			<div id="main_left" class="main_left">
					 	   	    <div id="left_top" class="c_top">
					 	   	    	<div class="left_top_bg"></div>
					 	   	    	<div class="left_middle_bg">
								 	    <div class="left_top_title" ><span id="titleContent" name="titleContent" class="titleCls">${imgText.title}</span></div>
										<div class="left_top_img_style" name="cover" id="cover">封面图片</div>
										<img id="coverImg" class="imgCover" src=""/>
					 	   	   		</div>
					 	   	   		<div class="left_bottom_bg"></div>
					 	   	    </div> 
								
								<div class="c_left_top">
									<div class="left_bot_style">
										<div id="backBtn" onclick="backToList()" class="btn2Font_04">返回</div>
									</div>
								</div>
					 	   </div>
			   		</td>
			   		<td  valign="top">
			   			<div id="main_right" class="main_right">
			   					 
			   				    <table  width="100%" cellpadding="0" cellspacing="0" border="0" >
			   					  	<tr>	
			   					  	    <td width="100px" >
			   					   			<div class="right_left_top"></div>
			   					   	    </td>
			   					   	    <td width="100%">
			   								<div class="right_top_middle"></div>
			   							</td>
			   							<td width="180px">
			   								<div class="right_right_top"></div>
			   							</td>
			   						</tr>
			   					</table>	
			   					<table  width="100%" cellpadding="0" cellspacing="0" border="0">	
			   					<tr>
			   					<td class="right_middle_left" valign="top" width="20" >
			   					</td>
			   					<td>
						   	    	<div id="imgTextForm" class="right_top_style">
										 <!--标题-->
										 <div>
											<div class="font_lable_1" >标题</div>
											<div class="input_div_1"><input name="materialTitle" type="text"  class="input_text_1" value="${imgText.title}" onkeyup="showTitle();"/></div>
										 </div>
										 
										 <!--封面-->
										<div style="margin-top:10px;">
										     <div>
											 	 <div class="font_lable_1" style="float:left;">封面</div>
												 <div class="font_lable_2">大图片建议尺寸：360像素 * 200像素，小图片建议尺寸：200像素 * 200像素 </div>
										         <div style="clear:both;"></div>
											 </div>
										<!--上传-->	 
											<form id="upload_file" method="post" enctype="multipart/form-data" class="input_div_2"> 
												<div id="imgBox" name="imgBox" class="_imgBox">
													<p style="height:1px"></p>
													<input style="width:60px;" type="file" name="file" id="imgFile" onchange="uploadImg();" accept="image/*"/> 
												</div>
											</form>
										 </div>
										 
										 <!--摘要-->
										<div style="margin-top:10px;">
										 	 <div>
											 	 <div class="font_lable_1" style="float:left;">摘要</div>
												 <div class="font_lable_2" style="float:left;margin-left:10px;">(选填)</div>
											 </div>
											 <div style="clear:both;"></div>
										     <div>
										     	<textarea name="abstractContent" id="abstractContent" class="textarea_style">${imgText.digest }</textarea>
										     </div>
										 </div>
										 
										 <!--单选-->
										 <div style="margin-top:10px;">
												<div class="font_lable_1" style="float:left;"><label><input name="materialType" id="materialType1" type="radio" value="0001" onclick="imgTextOne.defaultShow();" checked="true"/>链接到网址 </label></div>
												<div class="font_lable_1" style="float:left;margin-left:10px;"><label><input id="materialType2" name="materialType" type="radio" value="0002" onclick="imgTextOne.defaultShow();"/>正文</label> </div>
										 </div>
										 <div style="clear:both;"></div>
			                                         
										<div style="margin-top:10px;" id="link_url_wx">
										     <div class="input_div_1"><input id="" name="materialUrl" type="text" value="${imgText.content_source_url}"  class="input_text_1" /></div>
										 </div>
										 
										<div style="margin-top:10px;" id="imgText_one_wx">
										     	 <textarea id="returnContent" cols="20" rows="2" class="ckeditor" name="returnContent">${imgText.content}</textarea>
										 </div>
									 </div>
							  </td>
							  <td valign="top" width="20" class="right_middle_right">
							  </td>
							  </tr>
					   	      </table>
					   	      <table  width="100%" cellpadding="0" cellspacing="0" border="0" >
			   					  	<tr>	
			   					  	    <td width="85px" >
			   					   			<div class="right_bottom_left"></div>
			   					   	    </td>
			   					   	    <td width="100%">
			   								<div class="right_bottom_middle"></div>
			   							</td>
			   							<td width="180px">
			   								<div class="right_bottom_right"></div>
			   							</td>
			   						</tr>
			   					</table>	
				 	   	      <div id="right_bottom" class="c_bottom" style="background-color:#ff80c#8000ff;0;">
			 	   	         	     <div class="right_bot_style">
				 	   	      	     	    <div id="saveBtn" class="btn2Font_02" onclick="imgTextOne.submit()">保存</div>
				 	   	      	     </div>
				 	   	      </div>
						   </div>
						   <div style="clear:both;"></div>
			   		</td>
			   	</tr>
			   </table>
		 </div>
		
		 <input type="hidden" name="imageUrl" id="imageUrl" />
		 <input type="hidden" name="haveImg" id="haveImg"  />
		 <input type="hidden" name="articelId" id="articelId"  value="${imgText.article_id }"/>
	</body>
</html>
