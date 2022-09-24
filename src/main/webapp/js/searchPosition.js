$(document).ready(function () {
	$("#searchArea").click(function () {
		
		$.ajax({
        	type: "POST",
        	url:"/DSMS/getArea.do",
            data: {
            	"partsCode": $("#partsCode").val(),
        		"statusId": $("#statusId").val(),
        		"positionName": $("#position").val()
            },

            success: function (data) {
            	// get warning
            	if (data.warning != null) {
            		$("#inputError").text(data.warning);
            		return;
            	}
            	// get data
            	if (data != null && data != "") {
            		// 刪除舊資料
            		var originChildLength = $("#areaList")[0].children.length;
            		for (let i=0; i<originChildLength; i++) {
            			$("#areaList")[0].children[0].remove();
            		}
            		
        			// 寫入新資料
            		var areaName;
            		var areaDesc;

            		for (let i=0; i<data.length; i++) {
            			areaName = data[i].areaName;
            			areaDesc = data[i].areaDesc;
            			console.log('data[i]: ' + data[i]);
            			console.log('data[' + i + '].areaName: ' + data[i].areaName);
            			
            			$("#areaList").append('<a class="list-group-item list-group-item-action" onclick="openPositionList(this)"></a>');
            			$("#areaList")[0].children[i].text = areaName + " (" + areaDesc + ")";
            			$("#areaList")[0].children[i].id = data[i].id;
            		}
            	} else {
            		alert("發生異常，伺服器無回應");
            	}
            }
        });
	});
});

function openPositionList(o) {
	// 將儲區寫在儲位 list 的 title
	
	
	// ajax 拿取資料寫入儲位 list
	$.ajax({
    	type: "POST",
    	url:"/DSMS/getAreaPosition.do",
        data: {
        	"areaId": o.id,
        	"statusId": $("#statusId").val(),
        	"partsCode": $("#partsCode").val(),
    		"positionName": $("#position").val()
        },
        success: function (data) {
        	// 刪除舊資料
        	var originRowsLength = $("#areaPositionTable")[0].rows.length;
        	for (let i=1; i<originRowsLength; i++) {
    			$("#areaPositionTable")[0].deleteRow(1);
    		}
        	// 寫入新資料
        	var tPositionName;
    		var tPartsCode;
    		var tStatus;
    		
    		for (let i=0; i<data.length; i++) {
    			tPositionName = data[i].positionName;
    			tPartsCode = data[i].partsCode;
    			tStatus = data[i].status;
    			
    			var newRow = areaPositionTable.insertRow();
    	    	var newTd0 = newRow.insertCell();
    	        var newTd1 = newRow.insertCell();
    	        var newTd2 = newRow.insertCell();
    	        
    	        newTd0.innerText = tPositionName;
    	        newTd1.innerText = tPartsCode;
    	        newTd2.innerText = tStatus;
    		}
        }
	});
	// 開啟儲位 list
    $("#toPositionList").click();
}