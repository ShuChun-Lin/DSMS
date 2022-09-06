<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF8" %>
        <%@ page isELIgnored="false" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Test HOME</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet">
                <script src="https://cdn.staticfile.org/twitter-bootstrap/5.1.1/js/bootstrap.bundle.min.js"></script>
                <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
                <style>
                    body {
                        background-color: rgb(209, 221, 232);
                    }

                    .nav-link {
                        color: white;
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
                </style>
            </head>

            <body>
                <div class="container-fluid logo-logout">
                    <div class="row">
                        <div class="col-10 logo">
                            <h1 style="color:aliceblue">部門庫房管理</h1>
                        </div>
                        <div class="col-2 logout">
                            <ul class="nav justify-content-end">
                                <li class="nav-item">
                                    <span style="font-weight: bold;">${user}</span>
                                    <a class="nav-link" href="${pageContext.request.contextPath}/logout.do">登出</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="container-fluid main">
                    <div class="row">
                        <div class="col-2">
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
                                    <form class="form-inline" action="#" method="post">
                                        <input type="text" name="queryPartsName" placeholder="輸入料號(partsCode)" value="">
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
                                            <c:forEach var="book" items="${bookList }">
                                                <tr>
                                                    <td>${book.bookId }</td>
                                                    <td>${book.bookName }</td>
                                                    <td>${book.bookCounts }</td>
                                                    <td>${book.detail }</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <script>
                    var displayDate = document.getElementById('displayDate');
                    var currentTime = new Date();
                    displayDate.innerHTML = currentTime.toDateString();
                </script>
            </body>

            </html>