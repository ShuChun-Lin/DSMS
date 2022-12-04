package div.shuchun.utils;

/**
 * The class {@code PageSupprt} stores all elements
 * about page.
 * 
 * @author shuchun.lin
 *
 */
public class PageSupport {
	
	/** current page */
	private int currentPageNo = Constants.DEFAULT_CURRENT_PAGE_NO;
	
	/** total counts of data in the database */
	private int totalCount = 0;
	
	/** size of page */
	private int pageSize = Constants.PAGE_SIZE;
	
	/** counts of total pages */
	private int totalPageCount = 1;
	
	/**
	 * To set all elements of a instance of class {@code PageSupprt}
	 * 
	 * @param pageIndex
	 * @param totalCount
	 * @throws NumberFormatException - if the string does not contain aparsable integer.
	 * @see #setCurrentPageNo(int)
	 * @see #setTotalCount(int)
	 */
	public void pageCalculation(String pageIndex, int totalCount) {
		// 取得當前頁數
		if (pageIndex != null) {                                                     // 如果前端輸入不是數字怎麼辦????
			setCurrentPageNo(Integer.parseInt(pageIndex));  // String to int
		}
		
		// 計算 total count (大於0 會一併計算總頁數)
		setTotalCount(totalCount);
		
		// (控制不超過首頁和尾頁範圍)
		if (currentPageNo < 1) {
			currentPageNo = 1;
		}
		if (currentPageNo > totalPageCount) {
			currentPageNo = totalPageCount;
		}

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
	
	/**
	 * set total counts of {@code Parts} data
	 * if {@code toatlCount} bigger than 0,
	 * then set {@code totalCount} and set {@code totalPageCount}
	 * 
	 * @param totalCount
	 * @see #setTotalPageCountByRs()
	 */
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
