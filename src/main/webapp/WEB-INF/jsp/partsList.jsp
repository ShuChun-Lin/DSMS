<%@ page language="java" pageEncoding="UTF8"%>
<%@ page isELIgnored="false"%>
<%@include file="common/head.jsp"%>

<!-- ------------------------------------------------------------------------------------------- -->
 							<div class="col-9">
                                <div class="row">
                                    <div class="col-md-12 column">
                                        <div class="page-header">
                                            <h1>
                                                <small>物料</small>
                                                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addUpdateDLG" id="toAddDLG">新增物料</button>
                                            </h1>
                                        </div>
                                    </div>
                                    <hr>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 column">
                                        <form class="form-inline" id="searchParts">
                                            <input type="text" name="queryPartsCode" id="queryPartsCode"
                                                 placeholder="輸入料號(Part code)" value="${partsCode}">
                                            <select name="queryPartsDept" id="queryPartsDept">
                                            	<c:choose>
                                            	<c:when test="${userSession.userRole == '1' }">
                                            		<c:if test="${deptList != null }">
						   								<option value="0">-- 部門 --</option>
						   								<c:forEach var="dept" items="${deptList}">
						   									<option	value="${dept.id}">${dept.fabDept}</option>
						   								</c:forEach>
													</c:if>
                                            	</c:when>
                                            	<c:otherwise>
                                            		<option	value="${userSession.userDepartment}">${userSession.userDepartmentName}</option>
                                            	</c:otherwise>
                                            	</c:choose>
                                            </select>
                                            <input type="hidden" name="pageIndex" id="pageIndex" value="1" />
                                            <input type="submit" value="搜尋">
                                            <span style="color:red;font-weight:bold" id="inputError"></span>
                                        </form>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-md-12 column">
                                        <table class="table table-hover table-striped" id="partsTable">
                                        	<thead>
                                        		<tr>
                                        			<th>料號</th>
                                        			<th>名稱</th>
                                        			<th>部門</th>
                                        			<th>操作</th>
                                        		</tr>
                                        	</thead>
                                        	<tbody id="tbody">
                                                <c:forEach var="parts" items="${partsList }">
                                                    <tr>
                                                        <td>${parts.partsCode }</td>
                                                        <td>${parts.partsName }</td>
                                                        <td>${parts.partsDeptName }</td>
                                                        <td>
															<span><a class="modifyParts" href="javascript:;" partsid=${parts.id } partsname=${parts.partsName }>修改</a></span>
															<span><a class="deleteParts" href="javascript:;" partsid=${parts.id } partsname=${parts.partsName }>删除</a></span>
														</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <button style="display:none" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#deleteDLG" id="toDeleteDLG"></button>
                                        <button style="display:none" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addUpdateDLG" id="toUpdateDLG"></button>
                                        <div class="row">
                                            <div class="col-6">
                                                <span id="pageSpan">共${totalCount }筆紀錄&nbsp;&nbsp; ${currentPageNo
                                                    }/${totalPageCount }頁 </span>
                                                <ul class="pagination pagination-sm">
                                                        <li class="page-item"><a class="page-link" id="firstPage"
                                                                href="javascript:changePage(document.forms[0],1);">首頁</a>
                                                        </li>
                                                        <li class="page-item"><a class="page-link" id="previousPage"
                                                                href="javascript:changePage(document.forms[0],$('#currentPageNo').val()-1);">上一頁</a>
                                                        </li>
                                                        <li class="page-item"><a class="page-link" id="nextPage"
                                                                href="javascript:changePage(document.forms[0],Number($('#currentPageNo').val())+1);">下一頁</a>
                                                        </li>
                                                        <li class="page-item"><a class="page-link" id="lastPage"
                                                                href="javascript:changePage(document.forms[0],$('#totalPageCount').val());">最後一頁</a>
                                                        </li>
                                                </ul>
                                            </div>
                                            <div class="col-6 text-end">
                                                <input type="hidden" id="totalPageCount" value="${totalPageCount}" />
                                                <input type="hidden" id="currentPageNo" value="${currentPageNo}" />
                                                <span><label>第</label>
                                                    <input type="text" name="inputPage" id="inputPage"
                                                        style="width: 40px;" />頁
                                                    <button type="button" class="btn btn-secondary"
                                                        onClick='checkPageNumAndDo(document.forms[0],document.getElementById("inputPage").value)'>GO</button>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
<%-- 刪除確認框 --%>
<div class="modal fade" id="deleteDLG">
  <div class="modal-dialog">
    <div class="modal-content">
 
      <%-- 框頭部 --%>
      <div class="modal-header">
        <h4 class="modal-title">刪除</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
 
      <%-- 框內容 --%>
      <div class="modal-body">
        <div class="removeMain">
            <p>你確定要刪除該物料嗎？</p>
        </div>
      </div>
 
      <%-- 框底部 --%>
      <div class="modal-footer">
        <button type="button" class="btn btn-success" data-bs-dismiss="modal" id="deleteBtnYes">確認</button>
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal" id="closedeleteDLG">取消</button>
      </div>
 
    </div>
  </div>
</div>

<%-- 新增/修改框 --%>
<div class="modal fade" id="addUpdateDLG">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
 
      <%-- 框頭部 --%>
      <div class="modal-header">
        <h4 class="modal-title" id="addUpdateTitle">修改物料</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
 
      <%-- 框內容 --%>
      <div class="modal-body">
      	<form id="addUpdateForm">
      	  <input type="hidden" id="pid" value="">
		  <div class="form-group">
		  	<label for="partsCode">料號:</label>
		  	<input type="text" class="form-control" placeholder="Enter Part code" id="partsCode" required>
		  </div>
		  <div class="form-group">
		  	<label for="partsName" id="pwdLabel">名稱:</label>
		  	<input type="text" class="form-control" placeholder="Enter Part name" id="partsName" required>
		  </div>
		  <div class="form-group">
  			<label for="partsDeptName">部門:</label>
  			<select class="form-control" id="partsDeptName">
  				<c:choose>
                <c:when test="${userSession.userRole == '1' }">
                	<c:if test="${deptList != null }">
						<option value="0">-- 部門 --</option>
						<c:forEach var="dept" items="${deptList}">
							<option	value="${dept.id}">${dept.fabDept}</option>
						</c:forEach>
					</c:if>
                </c:when>
                <c:otherwise>
                	<option	value="${userSession.userDepartment}">${userSession.userDepartmentName}</option>
                </c:otherwise>
                </c:choose>
  			</select>
		  </div>
		  <button style="display:none" type="submit" id="addUpdateDLGSubmit">Submit</button>
		</form>
      </div>
 
      <%-- 框底部 --%>
      <div class="modal-footer">
      	<button type="button" class="btn btn-success" data-bs-dismiss="modal" id="addUpdateBtnYes">確認</button>
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal" id="closeAddUpdateDLG">取消</button>
      </div>
 
    </div>
  </div>
</div>
                    <!-- ------------------------------------------------------------------------------------------- -->
                    
<%@include file="common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/partsList.js"></script>
                   