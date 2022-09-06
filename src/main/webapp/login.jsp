<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF8" %>
    <%@ page isELIgnored="false" %>
      <!DOCTYPE html>
      <html>

      <head>
        <title>Login</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
        <style>
          #table_a {
            border: solid 1px rgba(0, 0, 0, 0.67);
            border-radius: 10px;
            padding: 10px;
            background-color: white;
          }

          body {
            background-color: rgb(209, 221, 232);
          }
        </style>
      </head>

      <body>
        <div class="container p-5 my-5"></div>
        <div class="container p-5 my-5">
          <div class="row">
            <div class="col-4"></div>
            <div class="col-4" id="table_a">
              <form action="${pageContext.request.contextPath}/login.do" method="post">
                <h4 style="text-align:center">登入</h4>
                <div class="mb-3 mt-3">
                  <label for="username" class="form-label">帳號:</label>
                  <input type="text" class="form-control" id="username" placeholder="Enter username" name="username">
                </div>
                <div class="mb-3">
                  <label for="pwd" class="form-label">密碼:</label>
                  <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pswd">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
                <span style="color:red; font-weight:bold">${error}</span>
              </form>
            </div>
            <div class="col-4"></div>
          </div>
        </div>



      </body>

      </html>