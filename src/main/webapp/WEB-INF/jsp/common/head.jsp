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

                        /* .operate {
                            background-color: rgb(110, 132, 211);
                            color: aliceblue;
                        } */

                        .page-link {
                            color: black;
                            background-color: rgb(221, 228, 234);
                            border-style: solid;
                            border-color: black;
                        }

                        .collapse {
                            border-style: none;
                        }

                        .collapse:hover {
                            background-color: rgb(110, 132, 211);
                            color: white;
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
                                        <a id="collapseOne" class="collapse btn" data-bs-parent="#accordion"
                                            href="${pageContext.request.contextPath}/searchParts">
                                            <div class="card-body">
                                                查料
                                            </div>
                                        </a>
                                        <a id="collapseOne" class="collapse btn" data-bs-parent="#accordion"
                                            href="${pageContext.request.contextPath}/importParts">
                                            <div class="card-body">
                                                入料
                                            </div>
                                        </a>
                                        <a id="collapseOne" class="collapse btn" data-bs-parent="#accordion"
                                            href="${pageContext.request.contextPath}/exportParts">
                                            <div class="card-body">
                                                領料
                                            </div>
                                        </a>
                                    </div>
                                    <div class="card">
                                        <a class="collapsed btn" data-bs-toggle="collapse" href="#collapseTwo">
                                            <div class="card-header">
                                                <span>儲放位置</span>
                                            </div>
                                        </a>
                                        <a id="collapseTwo" class="collapse btn" data-bs-parent="#accordion"
                                            href="${pageContext.request.contextPath}/searchPosition">
                                            <div class="card-body">
                                                儲區/ 儲位
                                            </div>
                                        </a>
                                    </div>
                                    <div class="card">
                                        <a class="collapsed btn" data-bs-toggle="collapse" href="#collapseThree">
                                            <div class="card-header">
                                                管理
                                            </div>
                                        </a>
                                        <a id="collapseThree" class="collapse btn" data-bs-parent="#accordion"
                                            href="#">
                                            <div class="card-body">
                                                人員
                                            </div>
                                        </a>
                                        <a id="collapseThree" class="collapse btn" data-bs-parent="#accordion"
                                            href="#">
                                            <div class="card-body">
                                                物料
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </div>