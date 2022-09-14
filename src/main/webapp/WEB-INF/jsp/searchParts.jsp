<%@ page language="java" pageEncoding="UTF8"%>
<%@ page isELIgnored="false"%>
<%@include file="common/head.jsp"%>
 							<div class="col-9">
                                <div class="row">
                                    <div class="col-md-12 column">
                                        <div class="page-header">
                                            <h1>
                                                <small>查詢物料</small>
                                            </h1>
                                        </div>
                                    </div>
                                    <hr>
                                </div>
                                <div class="row">
                                    <div class="col-md-4 column">
                                        <form class="form-inline" action="${pageContext.request.contextPath}/partSearch"
                                            method="post">
                                            <input type="text" name="queryPartsCode" placeholder="輸入料號(partsCode)"
                                                value="${partsCode}">
                                            <input type="hidden" name="pageIndex" value="1" />
                                            <input type="submit" value="搜尋">
                                            <span style="color:red;font-weight:bold">${err}</span>
                                        </form>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-md-12 column">
                                        <table class="table table-hover table-striped">
                                            <thead>
                                                <tr>
                                                    <th>料號</th>
                                                    <th>名稱</th>
                                                    <th>數量</th>
                                                    <th>廠/部門</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="parts" items="${partsList }">
                                                    <tr>
                                                        <td>${parts.partsCode }</td>
                                                        <td>${parts.partsName }</td>
                                                        <td>${parts.partsCount }</td>
                                                        <td>${parts.partsDeptName }</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <div class="row">
                                            <div class="col-6">
                                                <span>共${totalCount }筆紀錄&nbsp;&nbsp; ${currentPageNo
                                                    }/${totalPageCount }頁 </span>
                                                <ul class="pagination pagination-sm">
                                                    <c:if test="${currentPageNo > 1}">
                                                        <li class="page-item"><a class="page-link"
                                                                href="javascript:page_nav(document.forms[0],1);">首頁</a>
                                                        </li>
                                                        <li class="page-item"><a class="page-link"
                                                                href="javascript:page_nav(document.forms[0],${currentPageNo-1});">上一頁</a>
                                                        </li>
                                                    </c:if>
                                                    <c:if test="${currentPageNo < totalPageCount }">
                                                        <li class="page-item"><a class="page-link"
                                                                href="javascript:page_nav(document.forms[0],${currentPageNo+1 });">下一頁</a>
                                                        </li>
                                                        <li class="page-item"><a class="page-link"
                                                                href="javascript:page_nav(document.forms[0],${totalPageCount });">最後一頁</a>
                                                        </li>
                                                    </c:if>
                                                </ul>
                                            </div>
                                            <div class="col-6 text-end">
                                                <input type="hidden" id="totalPageCount" value="${totalPageCount}" />
                                                <span><label>第</label>
                                                    <input type="text" name="inputPage" id="inputPage"
                                                        style="width: 40px;" />頁
                                                    <button type="button" class="btn btn-secondary"
                                                        onClick='jump_to(document.forms[0],document.getElementById("inputPage").value)'>GO</button>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
<%@include file="common/foot.jsp" %>
                   