<%@ page language="java" pageEncoding="UTF8" %>
    <%@ page isELIgnored="false" %>
        <%@include file="common/head.jsp" %>

            <!-- ------------------------------------------------------------------------------------------- -->
            <div class="col-9">
                <div class="row">
                    <div class="col-md-12 column">
                        <div class="page-header">
                            <h1>
                                <small>儲位/儲區</small>
                            </h1>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="row">
                	<form>
						<div class="row">
							<div class="col">
								<label for="statusId" class="form-label">物料狀態:</label>
                            	<select class="form-control" id="statusId" name="statusId" required>
                                	<option value="0">所有狀態</option>
                                	<option value="1">新品(1)</option>
                                	<option value="2">報廢品(2)</option>
                            	</select>
							</div>
							<div class="col">
								<label for="partsCode" class="form-label">料號:</label>
                            	<input type="text" class="form-control" id="partsCode" placeholder="Enter partsCode"
                                name="partsCode">
							</div>
							<div class="col">
								<label for="position" class="form-label">儲位:</label>
								<input type="text" class="form-control" id="position" placeholder="Enter position"
                                name="position">
							</div>
							<div class="col text-end">
								<button type="button" class="btn btn-primary" id="searchArea">搜尋</button>
								<%-- 要記得隱藏 --%>
								<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#positionListModal" id="toPositionList" style="display: none" >呼叫清單</button>
							</div>
						</div>
						<div class="row mt-3">
							<div class="col-12"><button type="button" data-bs-toggle="modal" data-bs-target="#toAddAreaModal" class="btn btn-success">新增儲區</button></div>
						</div>
					</form>
                </div>
                <hr>
                <div class="row clearfix" style="margin-bottom:30px">
                    <h2>儲區</h2>
                    <h2 id="isAreaExist" style="color:red"></h2>
  					<div class="list-group" id="areaList">
  					<%-- 準備放儲區資訊 --%>
  					</div>
                </div>
            </div>
            </div>
            </div>
            
<%-- 儲位清單框 --%>
<div class="modal fade" id="positionListModal">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
 
      <%-- 框頭部 --%>
      <div class="modal-header">
        <h4 class="modal-title" id="modal-title">儲位清單</h4>
        <button class="btn btn-primary" style="margin-left: 3px" id="updateArea">修改儲區</button>
        <button class="btn btn-danger" style="margin-left: 3px" id="deleteArea">刪除儲區</button>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
 
      <%-- 框內容 --%>
      <div class="modal-body">
      	<button type="button" data-bs-toggle="modal" data-bs-target="#toAddPositionModal" class="btn btn-success" style="margin-bottom: 10px">新增儲位</button>
      	<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#toUpdatePositionModal" id="toUpdatePosition" style="display:none">修改儲位</button>
      	<input type="hidden" id="areaId"/>
      	<table class="table table-hover table-striped" id="areaPositionTable">
        	<tr>
            	<th>儲位名稱</th>
            	<th>料號</th>
            	<th>狀態</th>
            </tr>
        </table>
        <h5 id="existPosition" style="color:red; font-weight:bold"></h5>
      </div>
 
      <%-- 框底部 --%>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">關閉</button>
      </div>
 
    </div>
  </div>
</div>

<%-- 新增儲區框 --%>
<div class="modal fade" id="toAddAreaModal">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
 
      <%-- 框頭部 --%>
      <div class="modal-header">
        <h4 class="modal-title" id="modal-title">新增儲區</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
 
      <%-- 框內容 --%>
      <div class="modal-body">
      	<h5 id="isAddAreaSuccess" style="color:red; font-weight:bold"></h5>
      	儲區名: <input type="text" class="form-control" id="addAreaName"><br>
      	描述: <input type="text" class="form-control" id="addAreaDesc"><br>
      	<button class="btn btn-primary" type="button" id="addAreaBtn">新增</button>
      </div>
 
      <%-- 框底部 --%>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">關閉</button>
      </div>
 
    </div>
  </div>
</div>

<%-- 新增儲位框 --%>
<div class="modal fade" id="toAddPositionModal">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
 
      <%-- 框頭部 --%>
      <div class="modal-header">
        <h4 class="modal-title" id="modal-title">新增儲位</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
 
      <%-- 框內容 --%>
      <div class="modal-body">
      	<h5 id="isAddPositionSuccess" style="color:red; font-weight:bold"></h5>
      	儲位名: <input type="text" class="form-control" id="addPositionName"><br>
      	料號: <input type="text" class="form-control" id="addPartsCode"><br>
      	狀態: <select class="form-control" id="addPositionStatus">
              	<option value="1">新品(1)</option>
              	<option value="2">報廢品(2)</option>
              </select>
      	<button class="btn btn-primary" type="button" id="addPositionBtn" style="margin-top: 10px">新增</button>
      </div>
 
      <%-- 框底部 --%>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal" id="closeAddPositionModal" onclick="returnPositionListModal()">關閉</button>
      </div>
 
    </div>
  </div>
</div>

<%-- 修改儲位框 --%>
<div class="modal fade" id="toUpdatePositionModal">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
 
      <%-- 框頭部 --%>
      <div class="modal-header">
        <h4 class="modal-title" id="modal-title">修改儲位</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
 
      <%-- 框內容 --%>
      <div class="modal-body">
      	<h5 id="isUpdatePositionSuccess" style="color:red; font-weight:bold"></h5>
      	儲位名: <input type="text" class="form-control" id="updatePositionName"><br>
      	料號: <input type="text" class="form-control" id="updatePartsCode"><br>
      	狀態: <select class="form-control" id="updatePositionStatus">
              	<option value="1">新品(1)</option>
              	<option value="2">報廢品(2)</option>
              </select>
      	<button class="btn btn-primary" type="button" id="updatePositionBtn" style="margin-top: 10px">修改</button>
      </div>
 
      <%-- 框底部 --%>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal" id="closeUpdatePositionModal" onclick="returnPositionListModal()">關閉</button>
      </div>
 
    </div>
  </div>
</div>
            <!-- ---------------------------------------------------------------------------------------------------- -->

            <%@include file="common/foot.jsp" %>
                <script type="text/javascript" src="${pageContext.request.contextPath }/js/searchPosition.js"></script>