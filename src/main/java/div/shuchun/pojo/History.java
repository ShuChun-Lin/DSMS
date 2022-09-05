package div.shuchun.pojo;

import java.util.Date;

public class History {

	private int id;
	private Date eventDate;
	private String eventContent;
	private int userId;
	
	public History() {
		super();
	}

	public History(int id, Date eventDate, String eventContent, int userId) {
		super();
		this.id = id;
		this.eventDate = eventDate;
		this.eventContent = eventContent;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "History [id=" + id + ", eventDate=" + eventDate + ", eventContent=" + eventContent + ", userId="
				+ userId + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventContent() {
		return eventContent;
	}

	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
