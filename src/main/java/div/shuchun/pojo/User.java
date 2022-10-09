package div.shuchun.pojo;

import java.util.Date;

public class User {

	private Integer id;
	private String userCode;
	private String userName;
	private String userPassword;
	private String userId;
	private Integer userDepartment;
	private Integer userRole;
	private Integer createdBy;
	private Date creationDate;
	private Integer modifyBy;
	private Date modifyDate;
	
	private String userDepartmentName;
	private String userRoleName;
	
	public User() {
		super();
	}

	public User(Integer id, String userCode, String userName, String userPassword, String userId,
			Integer userDepartment, Integer userRole, Integer createdBy, Date creationDate, Integer modifyBy,
			Date modifyDate, String userDepartmentName, String userRoleName) {
		super();
		this.id = id;
		this.userCode = userCode;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userId = userId;
		this.userDepartment = userDepartment;
		this.userRole = userRole;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
		this.userDepartmentName = userDepartmentName;
		this.userRoleName = userRoleName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userCode=" + userCode + ", userName=" + userName + ", userPassword=" + userPassword
				+ ", userId=" + userId + ", userDepartment=" + userDepartment + ", userRole=" + userRole
				+ ", createdBy=" + createdBy + ", creationDate=" + creationDate + ", modifyBy=" + modifyBy
				+ ", modifyDate=" + modifyDate + ", userDepartmentName=" + userDepartmentName + ", userRoleName="
				+ userRoleName + "]";
	}
	
	public String toStringAsJson() {
		return "{\"id\":\"" + id + "\"," +
				"\"userCode\":\"" + userCode + "\"," +
				"\"userName\":\"" + userName + "\"," +
				"\"userId\":\"" + userId + "\"," +
				"\"userDepartment\":\"" + userDepartment + "\"," +
				"\"userRole\":\"" + userRole + "\"," +
				"\"createdBy\":\"" + createdBy + "\"," +
				"\"creationDate\":\"" + creationDate + "\"," +
				"\"modifyBy\":\"" + modifyBy + "\"," +
				"\"modifyDate\":\"" + modifyDate + "\"," +
				"\"userDepartmentName\":\"" + userDepartmentName + "\"," +
				"\"userRoleName\":\"" + userRoleName + "\"}";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getUserDepartment() {
		return userDepartment;
	}

	public void setUserDepartment(Integer userDepartment) {
		this.userDepartment = userDepartment;
	}

	public Integer getUserRole() {
		return userRole;
	}

	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
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

	public String getUserDepartmentName() {
		return userDepartmentName;
	}

	public void setUserDepartmentName(String userDepartmentName) {
		this.userDepartmentName = userDepartmentName;
	}

	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	
}
