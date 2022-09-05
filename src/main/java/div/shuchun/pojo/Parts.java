package div.shuchun.pojo;

import java.util.Date;

public class Parts {

	private int id;
	private String partsCode;
	private String partsName;
	private int partsDepartment;
	private int createdBy;
	private Date creationDate;
	private int modifyBy;
	private Date modifyDate;
	
	public Parts() {
		super();
	}

	public Parts(int id, String partsCode, String partsName, int partsDepartment, int createdBy, Date creationDate,
			int modifyBy, Date modifyDate) {
		super();
		this.id = id;
		this.partsCode = partsCode;
		this.partsName = partsName;
		this.partsDepartment = partsDepartment;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "Parts [id=" + id + ", partsCode=" + partsCode + ", partsName=" + partsName + ", partsDepartment="
				+ partsDepartment + ", createdBy=" + createdBy + ", creationDate=" + creationDate + ", modifyBy="
				+ modifyBy + ", modifyDate=" + modifyDate + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getPartsDepartment() {
		return partsDepartment;
	}

	public void setPartsDepartment(int partsDepartment) {
		this.partsDepartment = partsDepartment;
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
