<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF8" %>
        <%@ page isELIgnored="false" %>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="UTF-8">
                    <meta http-equiv="X-UA-Compatible" content="IE=edge">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Test HOME</title>
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
                        rel="stylesheet">
                    <script
                        src="https://cdn.staticfile.org/twitter-bootstrap/5.1.1/js/bootstrap.bundle.min.js"></script>
                    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
                    <style>
                        body {
                            background-color: rgb(209, 221, 232);
                        }

                        .link {
                            padding: 3px;
                            margin: 3px;
                            color: white;
                            background-color: rgb(17, 103, 13);
                            border-style: solid;
                            border-radius: 5px;
                            text-decoration: none;
                        }

                        .link:hover {
                            background-color: rgb(251, 240, 28);
                        }

                        .logo-logout {

                            padding-left: 10px;
                            padding-right: 10px;
                            padding-top: 10px;
                            background-color: rgb(110, 132, 211);
                        }

                        .main {
                            margin-top: 20px;
                            padding-left: 10px;
                            padding-right: 10px;
                        }

                        .btn {
                            padding: 0px;
                        }

                        .operate {
                            background-color: rgb(110, 132, 211);
                            color: aliceblue;
                        }

                        .page-link {
                            color: black;
                            background-color: rgb(221, 228, 234);
                            border-style: solid;
                            border-color: black;
                        }
                    </style>
                </head>

                <body>
                    <header class="container-fluid fixed-top logo-logout">
                        <div class="row">
                            <div class="col-6 logo">
                                <h1 style="color:aliceblue">部門庫房管理</h1>
                            </div>
                            <div class="col-6 pt-3 text-end name-logout">
                                <span style="font-weight: bold;">${userSession.userName}</span>
                                <a class="link" href="${pageContext.request.contextPath}/logout.do">登出</a>
                            </div>
                        </div>
                        </div>
                    </header>
                    <div class="container-fluid main" style="margin-top:80px">
                        <div class="row">
                            <div class="col-2 fixed-left">
                                <div id="accordion">
                                    <div class="card">
                                        <a class="collapsed btn" data-bs-toggle="collapse" href="#collapseOne">
                                            <div class="card-header">
                                                <span>物料</span>
                                            </div>
                                        </a>
                                        <div id="collapseOne" class="collapse" data-bs-parent="#accordion">
                                            <div class="card-body">
                                                查料
                                            </div>
                                        </div>
                                        <div id="collapseOne" class="collapse" data-bs-parent="#accordion">
                                            <div class="card-body">
                                                領料
                                            </div>
                                        </div>
                                        <div id="collapseOne" class="collapse" data-bs-parent="#accordion">
                                            <div class="card-body">
                                                入料
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <a class="collapsed btn" data-bs-toggle="collapse" href="#collapseTwo">
                                            <div class="card-header">
                                                <span>儲放位置</span>
                                            </div>
                                        </a>
                                        <div id="collapseTwo" class="collapse" data-bs-parent="#accordion">
                                            <div class="card-body">
                                                儲區
                                            </div>
                                        </div>
                                        <div id="collapseTwo" class="collapse" data-bs-parent="#accordion">
                                            <div class="card-body">
                                                儲位
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <a class="collapsed btn" data-bs-toggle="collapse" href="#collapseThree">
                                            <div class="card-header">
                                                人員
                                            </div>
                                        </a>
                                        <div id="collapseThree" class="collapse" data-bs-parent="#accordion">
                                            <div class="card-body">
                                                查詢/修改
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
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
                    <footer class="footer text-center fixed-bottom">
                        使用 Bootstrap5
                    </footer>


                </body>
                <script type="text/javascript" src="${pageContext.request.contextPath }/js/rollpage.js"></script>

                </html>