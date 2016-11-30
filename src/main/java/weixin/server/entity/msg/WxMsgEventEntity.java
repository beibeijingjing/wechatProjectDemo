/**
 * 
 */
package weixin.server.entity.msg;

import weixin.server.entity.base.WxBaseMsgEntity;

/**
 * @author honey.zhao@aliyun.com
 * @version Jul 28, 2013
 * 
 */
public class WxMsgEventEntity extends WxBaseMsgEntity {
	private String event;
	private String eventKey;
	private String ticket;
	private Double latitude;
	private Double longitude;
	private Double precisions;
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getEventKey() {
		return eventKey;
	}
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getPrecisions() {
		return precisions;
	}
	public void setPrecisions(Double precisions) {
		this.precisions = precisions;
	}
	public WxMsgEventEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String toString() {
		return "WxMsgEventEntity [event=" + event + ", eventKey=" + eventKey
				+ ", ticket=" + ticket + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", precisions=" + precisions + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result
				+ ((eventKey == null) ? 0 : eventKey.hashCode());
		result = prime * result
				+ ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result
				+ ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result
				+ ((precisions == null) ? 0 : precisions.hashCode());
		result = prime * result + ((ticket == null) ? 0 : ticket.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		WxMsgEventEntity other = (WxMsgEventEntity) obj;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (eventKey == null) {
			if (other.eventKey != null)
				return false;
		} else if (!eventKey.equals(other.eventKey))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (precisions == null) {
			if (other.precisions != null)
				return false;
		} else if (!precisions.equals(other.precisions))
			return false;
		if (ticket == null) {
			if (other.ticket != null)
				return false;
		} else if (!ticket.equals(other.ticket))
			return false;
		return true;
	}
	
	
}
