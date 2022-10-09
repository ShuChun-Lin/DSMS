<%@ page language="java" pageEncoding="UTF8"%>
<%@ page isELIgnored="false"%>
<%@include file="common/head.jsp"%>

<!-- ------------------------------------------------------------------------------------------- -->
 							<div class="col-9">
                                <div class="row">
                                    <div class="col-md-12 column">
                                        <div class="page-header">
                                            <h1>
                                                <small>員工</small>
                                                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addUpdateDLG" id="toAddDLG">新增人員</button>
                                            </h1>
                                        </div>
                                    </div>
                                    <hr>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 column">
                                        <form class="form-inline">
                                            <input type="text" name="queryUserName" id="queryUserName"
                                                 placeholder="輸入人名(User name)" value="${userName}">
                                            <select name="queryUserRole" id="queryUserRole">
                                            	<c:if test="${roleList != null }">
						   							<option value="0">-- 權限 --</option>
						   							<c:forEach var="role" items="${roleList}">
						   								<option	value="${role.id}">${role.roleName}</option>
						   							</c:forEach>
												</c:if>
                                            </select>
                                            <select name="queryUserDept" id="queryUserDept">
                                            	<c:if test="${deptList != null }">
						   							<option value="0">-- 部門 --</option>
						   							<c:forEach var="dept" items="${deptList}">
						   								<option	value="${dept.id}">${dept.fabDept}</option>
						   							</c:forEach>
												</c:if>
                                            </select>
                                            
                                            <input type="button" id="searchUser" value="搜尋">
                                            <span style="color:red;font-weight:bold" id="inputError"></span>
                                        </form>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-md-12 column">
                                        <table class="table table-hover table-striped" id="userTable">
                                        	<thead>
                                        		<tr>
                                        			<th>帳號</th>
                                        			<th>名稱</th>
                                        			<th>工號</th>
                                        			<th>部門</th>
                                        			<th>權限</th>
                                        			<th>操作</th>
                                        		</tr>
                                        	</thead>
                                        	<tbody id="tbody">
                                                <c:forEach var="user" items="${userList }">
                                                    <tr>
                                                        <td>${user.userCode }</td>
                                                        <td>${user.userName }</td>
                                                        <td>${user.userId }</td>
                                                        <td>${user.userDepartmentName }</td>
                                                        <td>${user.userRoleName }</td>
                                                        <td>
															<span><a class="modifyUser" href="javascript:;" userid=${user.id } username=${user.userName }>修改</a></span>
															<span><a class="deleteUser" href="javascript:;" userid=${user.id } username=${user.userName }>删除</a></span>
														</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <button style="display:none" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#deleteDLG" id="toDeleteDLG"></button>
                                        <button style="display:none" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addUpdateDLG" id="toUpdateDLG"></button>
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
            <p>你確定要刪除該員工嗎？</p>
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

<%-- 新增修改框 --%>
<div class="modal fade" id="addUpdateDLG">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
 
      <%-- 框頭部 --%>
      <div class="modal-header">
        <h4 class="modal-title" id="addUpdateTitle">修改人員</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
 
      <%-- 框內容 --%>
      <div class="modal-body">
      	<form action="">
      	  <input type="hidden" id="uid" value="">
		  <div class="form-group">
		  	<label for="userCode">帳號:</label>
		  	<input type="text" class="form-control" placeholder="Enter UserCode" id="userCode">
		  </div>
		  <div class="form-group">
		  	<label for="pwd" id="pwdLabel">密碼:</label>
		  	<input type="password" class="form-control" value="000000" id="pwd">
		  </div>
		  <div class="form-group">
		  	<label for="userName">名字:</label>
		  	<input type="text" class="form-control" placeholder="Enter Name" id="userName">
		  </div>
		  <div class="form-group">
		  	<label for="userId">工號:</label>
		  	<input type="text" class="form-control" placeholder="Enter employee id" id="userId">
		  </div>
		  <div class="form-group">
  			<label for="userDeptName">部門:</label>
  			<select class="form-control" id="userDeptName">
  				<option value="0">-- 部門 --</option>
  				<c:if test="${deptList != null }">
    				<c:forEach var="dept" items="${deptList}">
						<option	value="${dept.id}">${dept.fabDept}</option>
					</c:forEach>
				</c:if>
  			</select>
		  </div>
		  <div class="form-group">
  			<label for="userRoleName">權限:</label>
  			<select class="form-control" id="userRoleName">
  				<option value="0">-- 權限 --</option>
  				<c:if test="${roleList != null }">
    				<c:forEach var="role" items="${roleList}">
						<option	value="${role.id}">${role.roleName}</option>
					</c:forEach>
				</c:if>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/userList.js"></script>
                   