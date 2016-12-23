(function(){
        $.extend($.fn,{
            mask: function(editFn,delFn,flag){
                this.unmask();
                // 参数
                var op = {
                    opacity: 0.6,
                    z: 10000,
                    bgcolor: '#ccc'
                };
                var original=$(document.body);
                var position={top:0,left:0};
                            if(this[0] && this[0]!==window.document){
                                original=this;
                                position=original.position();
                            }
                // 创建一个 Mask 层，追加到对象中
                var maskDiv=$('<div class="maskdivgen">&nbsp;</div>');
                maskDiv.appendTo(original);
                var maskWidth=original.outerWidth();
                if(!maskWidth){
                    maskWidth=original.width();
                }
                var  maskHeight=original.height();
                if(!maskHeight){
					maskHeight=original.outerHeight();
                }
				 var leftTop= $(this).offset().top;
                 var leftleft = $(this).offset().left;

                maskDiv.css({
                    position: 'absolute',
                    top: leftTop,
                    left:leftleft,
                    'z-index': op.z,
                    width: maskWidth,
                    height:maskHeight,
                    'background-color': op.bgcolor,
                    opacity: 0
                });
					
                    var msgDiv=$('<div style="position:absolute;line-height:30px;"></div>');
                   var editImg = $('<img  src="'+ baseUrl +'/common/images/imgEdit .png" width="20" align="absmiddle" height="20">');
				   editImg.bind("click",editFn);
				   var delImg = $('<img  src="'+ baseUrl +'/common/images/imgDel.png" width="20" align="absmiddle" height="20" style="margin-left:20px;">');
				   delImg.bind("click",delFn);
				     if(flag =="true"){
                     msgDiv.append(editImg);
                    }else{
					 msgDiv.append(editImg).append(delImg);
					}
					msgDiv.appendTo(maskDiv);
                    var widthspace=(maskDiv.width()-msgDiv.width());
                    var heightspace=(maskDiv.height()-msgDiv.height());
                    msgDiv.css({
                                cursor:'pointer',
                                top:(heightspace/2-2),
                                left:(widthspace/2-2)
                      });
                  
                  maskDiv.fadeIn('fast', function(){
                    // 淡入淡出效果
                    $(this).fadeTo('slow', op.opacity);
                })
                return maskDiv;
            },
         unmask: function(){
                     var original=$(document.body);
                         if(this[0] && this[0]!==window.document){
                            original=$(this[0]);
                      }
                      original.find("> div.maskdivgen").fadeOut('slow',0,function(){
                          $(this).remove();
                      });
            }
        });
    })();