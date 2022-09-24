$(document).ready(function () {
    $("#searchPartsForExport").click(function () {
    	
    	var queryPartsCode = $("#queryPartsCode").val();
    	
    	if (queryPartsCode == null || queryPartsCode == "") {
    		$("#inputError").text("請輸入料號");
    	} else {
    		$("#inputError").text("");
    		$.ajax({
            	type: "POST",
            	url:"/DSMS/getPartsInstList.do",
                data: {
                	"partsCode": $("#queryPartsCode").val(),
            		"statusId": $("#statusId").val()
                },

                success: function (data) {
                	// get warning
                	if (data.warning != null) {
                		$("#inputError").text(data.warning);
                		return;
                	}
                	// get data
                	if (data != null && data != "") {
                		// 刪除舊資料 Row
                		var originRowsLength = $("#partsTable")[0].rows.length;
                		for (let i=1; i<originRowsLength; i++) {
                			$("#partsTable")[0].deleteRow(1);
                		}
                		
            			// 寫入新資料
                		var tPartsCode;
                		var tPartsName;
                		var tQuantity;
                		var tArea
                		var tPosition;

                		for (let i=0; i<data.length; i++) {
                			tPartsCode = data[i].partsCode;
                			tPartsName = data[i].partsName;
                			tQuantity = data[i].quantity;
                			tArea = data[i].areaName;
                			tPosition = data[i].positionName;
                			
                			var newRow = partsTable.insertRow();
                	    	var newTd0 = newRow.insertCell();
                	        var newTd1 = newRow.insertCell();
                	        var newTd2 = newRow.insertCell();
                	        var newTd3 = newRow.insertCell();
                	        var newTd4 = newRow.insertCell();
                	        var newTd5 = newRow.insertCell();
                	        var newTd6 = newRow.insertCell();
                	        
                	        newTd0.innerText = tPartsCode;
                	        newTd1.innerText = tPartsName;
                	        newTd2.innerText = tQuantity;
                	        newTd3.innerText = tArea;
                	        newTd4.innerText = tPosition;
                	        newTd5.innerText = $("#statusId")[0].value;
                	        newTd6.innerHTML = '<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addCheckModal" onclick="toModal(this)">加入清單</button>';
                		}
                	} else {
                		alert("發生異常，伺服器無回應");
                	}
                }
            });
    	}   
    });
    
    $("#addBtn").click(function() {
    	// 彈出框資料傳給 export table
    	var newRow = exportTable.insertRow();
    	var newTd0 = newRow.insertCell();
        var newTd1 = newRow.insertCell();
        var newTd2 = newRow.insertCell();
        var newTd3 = newRow.insertCell();
        var newTd4 = newRow.insertCell();
        var newTd5 = newRow.insertCell();
        var newTd6 = newRow.insertCell();
        
        newTd0.innerText = $("#addPartsCode")[0].innerText;
        newTd1.innerText = $("#addPartsName")[0].innerText;
        newTd2.innerText = $("#addQuantity")[0].value;
        newTd3.innerText = $("#addArea")[0].innerText;
        newTd4.innerText = $("#addPosition")[0].innerText;
        newTd5.innerText = $("#addStatus")[0].innerText;
        newTd6.innerHTML = '<input type="button" class="btn btn-warning" value="刪除" onclick="del(this)">';
        
        // 關閉彈出框
        $("#closeCheckModel").click();
        // 開啟領料 list
        $("#toExportList").click();
    });
    
    $("#doExport").click(function() {
    	// get importTable parameter
    	var totalTableInfo = [];
    	var table = $('#exportTable')[0];
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
    		oneTableInfo.statusId = tableInfoTemp[5];
    		oneTableInfo.partsCode = tableInfoTemp[0];
    		oneTableInfo.quantity = tableInfoTemp[2];
    		oneTableInfo.position = tableInfoTemp[4];
    		totalTableInfo.push(oneTableInfo);
    	}
    	//console.log(totalTableInfo);
    	$.ajax({
        	type: "POST",
        	url:"/DSMS/exportParts.do",
            data: {
            	"tableInfo": JSON.stringify(totalTableInfo)
            },
            success: function (data) {
            	alert('領料成功');
            },
            error: function(data) {
            	alert('領料失敗');
            }
        });
    });
    
    
});

function toModal(o) {
	// 將表格資料傳給彈出框中
	var tPartsCode = $("#partsTable")[0].rows[o.parentNode.parentNode.rowIndex].cells[0].innerText;
	var tPartsName = $("#partsTable")[0].rows[o.parentNode.parentNode.rowIndex].cells[1].innerText;
	var tQuantity = $("#partsTable")[0].rows[o.parentNode.parentNode.rowIndex].cells[2].innerText;
	var tArea = $("#partsTable")[0].rows[o.parentNode.parentNode.rowIndex].cells[3].innerText;
	var tPosition = $("#partsTable")[0].rows[o.parentNode.parentNode.rowIndex].cells[4].innerText;
	var tStatus = $("#partsTable")[0].rows[o.parentNode.parentNode.rowIndex].cells[5].innerText;
	
	$("#addPartsCode").text(tPartsCode);
	$("#addPartsName").text(tPartsName);
	$("#addQuantity").val(tQuantity);
	$("#addQuantity").attr('max', tQuantity);
	$("#addArea").text(tArea);
	$("#addPosition").text(tPosition);
	$("#addStatus").text(tStatus);
}

function del(o) {
	// get table object
	var t = document.getElementById("exportTable");

	// delete this row
	t.deleteRow(o.parentNode.parentNode.rowIndex);
}
