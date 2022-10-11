package div.shuchun.pojo;

import java.util.Date;

public class Parts {

	private Integer id;
	private String partsCode;
	private String partsName;
	private Integer partsDepartment;
	private Integer createdBy;
	private Date creationDate;
	private Integer modifyBy;
	private Date modifyDate;
	
	private String partsDeptName;
	private Integer partsCount;
	
	
	public Parts() {
		super();
	}
	

	public Parts(Integer id, String partsCode, String partsName, Integer partsDepartment, Integer createdBy, Date creationDate,
			Integer modifyBy, Date modifyDate, String partsDeptName, Integer partsCount) {
		super();
		this.id = id;
		this.partsCode = partsCode;
		this.partsName = partsName;
		this.partsDepartment = partsDepartment;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
		this.partsDeptName = partsDeptName;
		this.partsCount = partsCount;
	}

	@Override
	public String toString() {
		return "Parts [id=" + id + ", partsCode=" + partsCode + ", partsName=" + partsName + ", partsDepartment="
				+ partsDepartment + ", createdBy=" + createdBy + ", creationDate=" + creationDate + ", modifyBy="
				+ modifyBy + ", modifyDate=" + modifyDate + ", partsDeptName=" + partsDeptName + ", partsCount="
				+ partsCount + "]";
	}
	
	public String toJsonString() {
		return "{\"id\":\"" + id + "\"," +
				"\"partsCode\":\"" + partsCode + "\"," +
				"\"partsName\":\"" + partsName + "\"," +
				"\"partsDepartment\":\"" + partsDepartment + "\"," +
				"\"createdBy\":\"" + createdBy + "\"," +
				"\"creationDate\":\"" + creationDate + "\"," +
				"\"modifyBy\":\"" + modifyBy + "\"," +
				"\"modifyDate\":\"" + modifyDate + "\"," +
				"\"partsDeptName\":\"" + partsDeptName + "\"," +
				"\"partsCount\":\"" + partsCount + "\"}";
	}


	public String getPartsDeptName() {
		return partsDeptName;
	}

	public void setPartsDeptName(String partsDeptName) {
		this.partsDeptName = partsDeptName;
	}

	public Integer getPartsCount() {
		return partsCount;
	}

	public void setPartsCount(Integer partsCount) {
		this.partsCount = partsCount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPartsCode() {
		return partsCode;
	}

	public void setPartsCode(String partsCode) {
		this.partsCode = partsCode;
	}

	public String getPartsName() {
		return partsName;
	}

	public void setPartsName(String partsName) {
		this.partsName = partsName;
	}

	public Integer getPartsDepartment() {
		return partsDepartment;
	}

	public void setPartsDepartment(Integer partsDepartment) {
		this.partsDepartment = partsDepartment;
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
