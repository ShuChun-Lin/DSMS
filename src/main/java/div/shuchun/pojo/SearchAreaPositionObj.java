package div.shuchun.pojo;

public class SearchAreaPositionObj {

	private String positionName;
	private String partsCode;
	private String status;
	
	public SearchAreaPositionObj(String positionName, String partsCode, String status) {
		super();
		this.positionName = positionName;
		this.partsCode = partsCode;
		this.status = status;
	}

	public SearchAreaPositionObj() {
		super();
	}
	
	public String toStringAsJson() {
		return "{\"positionName\":\"" + positionName + "\"," +
				"\"partsCode\":\"" + partsCode + "\"," +
				"\"status\":\"" + status + "\"}";
	}

	@Override
	public String toString() {
		return "SearchAreaPositionObj [positionName=" + positionName + ", partsCode=" + partsCode + ", status=" + status
				+ "]";
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getPartsCode() {
		return partsCode;
	}

	public void setPartsCode(String partsCode) {
		this.partsCode = partsCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
