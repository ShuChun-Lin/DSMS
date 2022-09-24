package div.shuchun.pojo;

import java.util.Date;

public class Area {

	private int id;
	private String areaName;
	private String areaDesc;
	private int areaDepartment;
	private int createdBy;
	private Date creationDate;
	private int modifyBy;
	private Date modifyDate;
	
	public Area() {
		super();
	}

	public Area(int id, String areaName, String areaDesc, int areaDepartment, int createdBy, Date creationDate,
			int modifyBy, Date modifyDate) {
		super();
		this.id = id;
		this.areaName = areaName;
		this.areaDesc = areaDesc;
		this.areaDepartment = areaDepartment;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
	}
	
	public String toStringAsJson() {
		return "{\"id\":\"" + id + "\"," +
				"\"areaName\":\"" + areaName + "\"," +
				"\"areaDesc\":\"" + areaDesc + "\"," +
				"\"areaDepartment\":\"" + areaDepartment + "\"}";
	}


	@Override
	public String toString() {
		return "Area [id=" + id + ", areaName=" + areaName + ", areaDesc=" + areaDesc + ", areaDepartment="
				+ areaDepartment + ", createdBy=" + createdBy + ", creationDate=" + creationDate + ", modifyBy="
				+ modifyBy + ", modifyDate=" + modifyDate + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaDesc() {
		return areaDesc;
	}

	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}

	public int getAreaDepartment() {
		return areaDepartment;
	}

	public void setAreaDepartment(int areaDepartment) {
		this.areaDepartment = areaDepartment;
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
