package div.shuchun.utils;

public class PageSupport {
	// 當前頁碼 - 來自於用戶輸入
	private int currentPageNo = Constants.DEFAULT_CURRENT_PAGE_NO;
	
	// 總數量 (資料庫查詢)
	private int totalCount = 0;
	
	// 頁面容量
	private int pageSize = Constants.PAGE_SIZE;
	
	// 總頁數 - totalCount / pageSize (+ 1)
	private int totalPageCount = 1;
	
	// 主計算
	public void pageCalculation(String pageIndex, int totalCount) {
	  // 計算當前頁數
		if (pageIndex != null) {                                                     // 如果前端輸入不是數字怎麼辦????
			setCurrentPageNo(Integer.parseInt(pageIndex));  // 如果數字小於1 則 改為1
		}
		// (控制不超過首頁和尾頁範圍)
		if (currentPageNo < 1) {
			currentPageNo = 1;
		}
		if (currentPageNo > totalPageCount) {
			currentPageNo = totalPageCount;
		}
		
	  // 計算 total count (大於0 會一併計算總頁數)
		setTotalCount(totalCount);
	}
	
	
	
	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		if (currentPageNo > 0) {
			this.currentPageNo = currentPageNo;
		}
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		if (totalCount > 0) {
			this.totalCount = totalCount;
			// 設置總頁數
			this.setTotalPageCountByRs();
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize > 0) {
			this.pageSize = pageSize;
		}
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	
	public void setTotalPageCountByRs() {
		if (this.totalCount % this.pageSize == 0) {
			this.totalPageCount = this.totalCount / this.pageSize;
		} else if (this.totalCount % this.pageSize > 0) {
			this.totalPageCount = this.totalCount / this.pageSize + 1;
		} else {
			this.totalPageCount = 0;
		}
	}
	
	
	
}
