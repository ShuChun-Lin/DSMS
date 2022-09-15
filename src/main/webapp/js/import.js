$(document).ready(function () {
    $("#partsCode").blur(function () {
    	/*$.post("/DSMS/checkPosition",
    		{
        		"partsCode": $("#partsCode").val(),
        		"statusId": $("#statusId").val()
    		},
    		function (data) {
            	// 清除原先的 select option
            	while ($("#position option").length > 1) {
            		$("#position")[0].options.remove(1);
            	}
            	// 有傳回 警告
            	if (data[0].warning != null && data[0].warning != "") {
            		alert(data[0].warning);
            		return;
            	}
            	
            	if (data != null && data != "") {
            		var optValue;
            		var optText; 
            		for (var i=0; i<data.length; i++) {
            			//$("#position").addOption(data[i].id, data[i].positionName); 失敗Uncaught TypeError: $(...).addOption is not a function
            			optValue = data[i].id;
            			optText = data[i].positionName;
            			$("#position").append(`<option value="${optValue}">${optText}</option>`);
            		}
            	} else {
            		alert("發生異常，伺服器無回應");
            	}
            }	
    	);*/
        // $post(url,data,callback)
    	
        $.ajax({
        	type: "POST",
        	url:"/DSMS/checkPosition",
            data: {
            	"partsCode": $("#partsCode").val(),
        		"statusId": $("#statusId").val()
            },
            //contentType:'application/json;charset=UTF-8',
            //contentType:'application/x-www-form-urlencoded;charset=UTF-8',
            success: function (data) {
            	// 清除原先的 select option
            	while ($("#position option").length > 1) {
            		$("#position")[0].options.remove(1);
            	}
            	// 有傳回 警告
            	if (data[0].warning != null && data[0].warning != "") {
            		alert(data[0].warning);
            		return;
            	}
            	
            	if (data != null && data != "") {
            		var optValue;
            		var optText; 
            		for (var i=0; i<data.length; i++) {
            			//$("#position").addOption(data[i].id, data[i].positionName); 失敗Uncaught TypeError: $(...).addOption is not a function
            			optValue = data[i].id;
            			optText = data[i].positionName;
            			$("#position").append(`<option value="${optValue}">${optText}</option>`);
            		}
            	} else {
            		alert("發生異常，伺服器無回應");
            	}
            },
            error: function() {
            	window.location = "${pageContext.request.contextPath}/";
            }
        });
    });
    
    $("#statusId").blur(function () {
    	// 清掉下面所有表格資料
    	// 清除原先的 select option
    	while ($("#position option").length > 1) {
    		$("#position")[0].options.remove(1);
    	}
    	// 清除料號
    	$("#partsCode").val("");
    	// 清除數量
    	$("#quantity").val("");
    });
    
    $("#addList").click(function() {
    	var pStatus = $("#statusId").val();
    	var pCode = $("#partsCode").val();
    	var pQuantity = $("#quantity").val();
    	var pPosition = $("#position").find("option:selected").text();
    	
    	var newRow = importTable.insertRow();
    	var newTd0 = newRow.insertCell();
        var newTd1 = newRow.insertCell();
        var newTd2 = newRow.insertCell();
        var newTd3 = newRow.insertCell();
        var newTd4 = newRow.insertCell();
        
        newTd0.innerText = pStatus;
        newTd1.innerText = pCode;
        newTd2.innerText = pQuantity;
        newTd3.innerText = pPosition;
        newTd4.innerHTML = '<input type="button" class="btn btn-warning" value="刪除" onclick="del(this)">';
        
    });
    
    $('#importBtn').click(function() {
    	
    	// get importTable parameter
    	var totalTableInfo = [];
    	var table = $('#importTable')[0];
    	for (var i=1; i<table.rows.length; i++) {
    		var oneTableInfo = {
    				statusId: null,
    				partsCode: null,
    				quantity: null,
    				position: null
    		};
    		var tableInfoTemp = [];
    		for (var j=0; j<table.rows[i].cells.length-1; j++) {
    			tableInfoTemp.push(table.rows[i].cells[j].innerHTML);
    		}
    		oneTableInfo.statusId = tableInfoTemp[0];
    		oneTableInfo.partsCode = tableInfoTemp[1];
    		oneTableInfo.quantity = tableInfoTemp[2];
    		oneTableInfo.position = tableInfoTemp[3];
    		totalTableInfo.push(oneTableInfo);
    	}
    	console.log(totalTableInfo);
    	$.ajax({
        	type: "POST",
        	url:"/DSMS/importParts.do",
            data: {
            	"tableInfo": JSON.stringify(totalTableInfo)
            },
            success: function (data) {
            	alert('入料成功');
            },
            error: function() {
            	alert('入料失敗');
            }
        });
        
    });

});

function del(o) {
	// get table object
	var t = document.getElementById("importTable");
	console.log("delete a row");
	// delete this row
	t.deleteRow(o.parentNode.parentNode.rowIndex);
}



