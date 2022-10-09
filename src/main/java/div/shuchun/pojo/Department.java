package div.shuchun.pojo;

import java.util.Date;

public class Department {
	
	private int id;
	private String fabDept;
	private int createdBy;
	private Date creationDate;
	private int modifyBy;
	private Date modifyDate;
	
	public Department() {
		super();
	}

	public Department(int id, String fabDept, int createdBy, Date creationDate, int modifyBy, Date modifyDate) {
		super();
		this.id = id;
		this.fabDept = fabDept;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", fabDept=" + fabDept + ", createdBy=" + createdBy
				+ ", creationDate=" + creationDate + ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFabDept() {
		return fabDept;
	}

	public void setFabDept(String fabDept) {
		this.fabDept = fabDept;
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
