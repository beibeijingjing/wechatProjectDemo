/**
 * 
 */
package weixin.server.entity.msg;

import weixin.server.entity.base.WxBaseMsgEntity;
import weixin.server.entity.item.WxItemVoiceEntity;

/**
 * @author honey.zhao@aliyun.com
 * @version Dec 24, 2013
 *
 */
public class WxMsgVoiceEntity extends WxBaseMsgEntity {
	// 媒体ID  
    private String MediaId;  
    // 语音格式  
    private String Format;  
  
    public String getMediaId() {  
        return MediaId;  
    }  
  
    public void setMediaId(String mediaId) {  
        MediaId = mediaId;  
    }  
  
    public String getFormat() {  
        return Format;  
    }  
  
    public void setFormat(String format) {  
        Format = format;  
    }  
	
	
}
