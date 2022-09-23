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
                    	<div class="mb-3">
                            <label for="statusId" class="form-label">物料狀態:</label>
                            <select class="form-control" id="statusId" name="statusId" onblur="clearForm()" required>
                                <option value="1">新品(1)</option>
                                <option value="2">報廢品(2)</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="partsCode" class="form-label">料號:</label>
                            <input type="text" class="form-control" id="partsCode" placeholder="Enter partsCode"
                                name="partsCode" required>
                        </div>
                        <div class="mb-3">
                            <label for="quantity" class="form-label">數量:</label>
                            <input type="number" class="form-control" id="quantity" placeholder="Enter quantity"
                                name="quantity" min="1" required>
                        </div>
                        <div class="mb-3">
                            <label for="position" class="form-label">儲位:</label>
                            <select class="form-control" id="position" name="position" required>
                                <option value="" style="display:none">--Please choose a position--</option>
                            </select>
                        </div>
                        <button type="button" class="btn btn-primary" id="addList">加入清單</button>
                    </form>
                </div>
                <hr>
                <div class="row clearfix" style="margin-bottom:30px">
                    <table class="table table-hover table-striped" id="importTable">
                    	<tr>
                    		<th>狀態</th>
                    		<th>料號</th>
                    		<th>數量</th>
                    		<th>儲位</th>
                    	</tr>
                    </table>
                    <div class="row">
                        <div class="col-12 text-end">
                            <button class="btn btn-primary" id="importBtn">入料</button>
                        </div>
                    </div>
                </div>
            </div>
            </div>
            </div>
            <!-- ---------------------------------------------------------------------------------------------------- -->

            <%@include file="common/foot.jsp" %>
                <script type="text/javascript" src="${pageContext.request.contextPath }/js/.js"></script>