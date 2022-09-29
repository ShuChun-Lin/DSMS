package div.shuchun.pojo;

import java.util.Date;

public class Position {

	private Integer id;
	private String positionName;
	private Integer positionArea;
	private Integer positionParts;
	private Integer positionStatus;
	private Integer createdBy;
	private Date creationDate;
	private Integer modifyBy;
	private Date modifyDate;
	
	public Position() {
		super();
	}

	public Position(Integer id, String positionName, Integer positionArea, Integer positionParts, Integer positionStatus, Integer createdBy,
			Date creationDate, Integer modifyBy, Date modifyDate) {
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
	
	public String toStringAsJson() {
		return "{\"id\":\"" + id +
				"\", \"positionName\":\"" + positionName +
				"\", \"positionArea\":\"" + positionArea +
				"\", \"positionParts\":\"" + positionParts +
				"\", \"positionStatus\":\"" + positionStatus +
				"\", \"createdBy\":\"" + createdBy +
				"\", \"creationDate\":\"" + creationDate +
				"\", \"modifyBy\":\"" + modifyBy +
				"\", \"modifyDate\":\"" + modifyDate +
				"\"}";
	}

	@Override
	public String toString() {
		return "Position [id=" + id + ", positionName=" + positionName + ", positionArea=" + positionArea
				+ ", positionParts=" + positionParts + ", positionStatus=" + positionStatus + ", createdBy=" + createdBy
				+ ", creationDate=" + creationDate + ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Integer getPositionArea() {
		return positionArea;
	}

	public void setPositionArea(Integer positionArea) {
		this.positionArea = positionArea;
	}

	public Integer getPositionParts() {
		return positionParts;
	}

	public void setPositionParts(Integer positionParts) {
		this.positionParts = positionParts;
	}

	public Integer getPositionStatus() {
		return positionStatus;
	}

	public void setPositionStatus(Integer positionStatus) {
		this.positionStatus = positionStatus;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}
