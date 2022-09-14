package div.shuchun.pojo;

public class ImportObj {

	private Integer statusId;
	private String partsCode;
	private Integer quantity;
	private String positionName;
	
	public ImportObj(Integer statusId, String partsCode, Integer quantity, String positionName) {
		super();
		this.statusId = statusId;
		this.partsCode = partsCode;
		this.quantity = quantity;
		this.positionName = positionName;
	}

	public ImportObj() {
		super();
	}
	
	@Override
	public String toString() {
		return "ImportObj [statusId=" + statusId + ", partsCode=" + partsCode + ", quantity=" + quantity
				+ ", positionName=" + positionName + "]";
	}


	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getPartsCode() {
		return partsCode;
	}

	public void setPartsCode(String partsCode) {
		this.partsCode = partsCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	
	
}
