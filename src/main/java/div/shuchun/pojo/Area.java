package div.shuchun.pojo;

import java.util.Date;

public class Area {

	private Integer id;
	private String areaName;
	private String areaDesc;
	private Integer areaDepartment;
	private Integer createdBy;
	private Date creationDate;
	private Integer modifyBy;
	private Date modifyDate;
	
	public Area() {
		super();
	}

	public Area(Integer id, String areaName, String areaDesc, Integer areaDepartment, Integer createdBy, Date creationDate,
			Integer modifyBy, Date modifyDate) {
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getAreaDepartment() {
		return areaDepartment;
	}

	public void setAreaDepartment(Integer areaDepartment) {
		this.areaDepartment = areaDepartment;
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
