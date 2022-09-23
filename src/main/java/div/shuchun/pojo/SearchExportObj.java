package div.shuchun.pojo;

public class SearchExportObj {

	private String partsCode;
	private String partsName;
	private Integer quantity;
	private String areaName;
	private String positionName;
	
	public SearchExportObj(String partsCode, String partsName, Integer quantity, String areaName, String positionName) {
		super();
		this.partsCode = partsCode;
		this.partsName = partsName;
		this.quantity = quantity;
		this.areaName = areaName;
		this.positionName = positionName;
	}

	public SearchExportObj() {
		super();
	}
	
	public String toStringAsJson() {
		return "{\"partsCode\":\"" + partsCode + "\"," +
				"\"partsName\":\"" + partsName + "\"," +
				"\"quantity\":\"" + quantity + "\"," +
				"\"areaName\":\"" + areaName + "\"," +
				"\"positionName\":\"" + positionName + "\"}";
	}

	@Override
	public String toString() {
		return "ExportObj [partsCode=" + partsCode + ", partsName=" + partsName + ", quantity=" + quantity
				+ ", areaName=" + areaName + ", positionName=" + positionName + "]";
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	
	
}
	
	