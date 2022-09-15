package div.shuchun.pojo;

public class PartsInst {

	private Integer id;
	private int quantity;
	private String snCode;
	private int partsId;
	private int statusId;
	private int positionId;
	
	public PartsInst() {
		super();
	}

	public PartsInst(Integer id, int quantity, String snCode, int partsId, int statusId, int positionId) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.snCode = snCode;
		this.partsId = partsId;
		this.statusId = statusId;
		this.positionId = positionId;
	}

	@Override
	public String toString() {
		return "PartsInst [id=" + id + ", quantity=" + quantity + ", snCode=" + snCode + ", partsId=" + partsId
				+ ", statusId=" + statusId + ", positionId=" + positionId + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSnCode() {
		return snCode;
	}

	public void setSnCode(String snCode) {
		this.snCode = snCode;
	}

	public int getPartsId() {
		return partsId;
	}

	public void setPartsId(int partsId) {
		this.partsId = partsId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getPositionId() {
		return positionId;
	}

	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	
}
