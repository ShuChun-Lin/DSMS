<%@ page language="java" pageEncoding="UTF8"%>
<%@ page isELIgnored="false"%>
<%@include file="common/head.jsp"%>

<!-- ------------------------------------------------------------------------------------------- -->
 							<div class="col-9">
                                <div class="row">
                                    <div class="col-md-12 column">
                                        <div class="page-header">
                                            <h1>
                                                <small>領料</small>
                                                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exportListModal" id="toExportList">領料清單</button>
                                            </h1>
                                        </div>
                                    </div>
                                    <hr>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 column">
                                        <form class="form-inline">
                                            <input type="text" name="queryPartsCode" id="queryPartsCode"
                                                 placeholder="輸入料號(partsCode)" value="${partsCode}">
                                            <select name="statusId" id="statusId">
                                            	<option value="1" selected>新品(1)</option>
                                            	<option value="2">報廢品(2)</option>
                                            </select>
                                            <input type="hidden" name="pageIndex" value="1" />
                                            <input type="button" id="searchPartsForExport" value="搜尋">
                                            <span style="color:red;font-weight:bold" id="inputError"></span>
                                        </form>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-md-12 column">
                                        <table class="table table-hover table-striped" id="partsTable">
                                        	<tr>
                                        		<th>料號</th>
                                        		<th>名稱</th>
                                        		<th>數量</th>
                                        		<th>儲區</th>
                                        		<th>儲位</th>
                                        		<th>狀態</th>
                                        	</tr>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
<!-- 加入領料彈出框 -->
<div class="modal fade" id="addCheckModal">
  <div class="modal-dialog">
    <div class="modal-content">
 
      <!-- 框頭部 -->
      <div class="modal-header">
        <h4 class="modal-title">加入領料清單</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
 
      <!-- 框內容 -->
      <div class="modal-body">
      	料號: <small id="addPartsCode"></small><br>
      	料名: <small id="addPartsName"></small><br>
      	數量: <input type="number" id="addQuantity" min="1" max=""><br>
      	儲區: <small id="addArea"></small><br>
      	儲位: <small id="addPosition"></small><br>
      	狀態: <small id="addStatus"></small><br>
      	<button class="btn btn-primary" type="button" id="addBtn">加入</button>
      </div>
 
      <!-- 框底部 -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal" id="closeCheckModel">關閉</button>
      </div>
 
    </div>
  </div>
</div>

<!-- 領料清單框 -->
<div class="modal fade" id="exportListModal">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
 
      <!-- 框頭部 -->
      <div class="modal-header">
        <h4 class="modal-title">領料清單</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
 
      <!-- 框內容 -->
      <div class="modal-body">
      	<table class="table table-hover table-striped" id="exportTable">
        	<tr>
            	<th>料號</th>
            	<th>名稱</th>
            	<th>數量</th>
            	<th>儲區</th>
            	<th>儲位</th>
            	<th>狀態</th>
            </tr>
        </table>
      	<button class="btn btn-primary" type="button" id="doExport">領料</button>
      </div>
 
      <!-- 框底部 -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">關閉</button>
      </div>
 
    </div>
  </div>
</div>
                    <!-- ------------------------------------------------------------------------------------------- -->
                    
<%@include file="common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/export.js"></script>
                   