package div.shuchun.pojo;

import java.util.Date;

public class Position {

	private int id;
	private String positionName;
	private int positionArea;
	private int positionParts;
	private int positionStatus;
	private int createdBy;
	private Date creationDate;
	private int modifyBy;
	private Date modifyDate;
	
	public Position() {
		super();
	}

	public Position(int id, String positionName, int positionArea, int positionParts, int positionStatus, int createdBy,
			Date creationDate, int modifyBy, Date modifyDate) {
		super();
		this.id = id;
		this.positionName = positionName;
		this.positionArea = positionArea;
		this.positionParts = positionParts;
		this.positionStatus = positionStatus;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "Position [id=" + id + ", positionName=" + positionName + ", positionArea=" + positionArea
				+ ", positionParts=" + positionParts + ", positionStatus=" + positionStatus + ", createdBy=" + createdBy
				+ ", creationDate=" + creationDate + ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public int getPositionArea() {
		return positionArea;
	}

	public void setPositionArea(int positionArea) {
		this.positionArea = positionArea;
	}

	public int getPositionParts() {
		return positionParts;
	}

	public void setPositionParts(int positionParts) {
		this.positionParts = positionParts;
	}

	public int getPositionStatus() {
		return positionStatus;
	}

	public void setPositionStatus(int positionStatus) {
		this.positionStatus = positionStatus;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(int modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}
