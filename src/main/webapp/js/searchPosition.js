$(document).ready(function () {
	$("#searchArea").click(function () {
		
		$.ajax({
        	type: "POST",
        	url:"/DSMS/getArea.do",
            data: {
            	"statusId": $("#statusId").val(),
            	"partsCode": $("#partsCode").val(),
        		"positionName": $("#position").val()
            },

            success: function (data) {
            	// 刪除舊資料
        		var originChildLength = $("#areaList")[0].children.length;
        		for (let i=0; i<originChildLength; i++) {
        			$("#areaList")[0].children[0].remove();
        		}
            	// get warning
            	if (data.warning != null) {
            		$("#isAreaExist").text(data.warning);
            		return;
            	}
            	// get data
            	if (data != null && data != "") {
            		$("#isAreaExist").text("");
            		
        			// 寫入新資料
            		var areaName;
            		var areaDesc;

            		for (let i=0; i<data.length; i++) {
            			areaName = data[i].areaName;
            			areaDesc = data[i].areaDesc;

            			// console.log('data[' + i + '].areaName: ' + data[i].areaName);
            			
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
	
	$("#addAreaBtn").click(function() {
		if ($("#addAreaName").val() === null || $("#addAreaName").val() === "") {
			alert("請輸入儲區名稱");
			return;
		}
		
		var currentTimeStamp = Date.now();
		
		$.ajax({
        	type: "POST",
        	url:"/DSMS/addArea.do",
            data: {
            	"areaName": $("#addAreaName").val(),
            	"areaDesc": $("#addAreaDesc").val(),
            	"creationDate": currentTimeStamp
            },

            success: function (data) {
            	// 刪除舊資料
            	$("#addAreaName").val("");
            	$("#addAreaDesc").val("");
            	
            	// get warning
            	if (data.warning != null) {
            		alert(data.warning);
            		return;
            	}
            	// get data
            	if (data != null && data != "") {
            		alert(data.info);
            	} else {
            		alert("發生異常，伺服器無回應");
            	}
            }
        });
		$("#searchArea").click();
	});
	
	$("#addPositionBtn").click(function() {
		if ($("#addPositionName").val() === null || $("#addPositionName").val() === "") {
			alert("請輸入儲位名稱");
			return;
		}
		
		var currentTimeStamp = Date.now();
		var thisArea = $("#areaId").val();
		
		$.ajax({
        	type: "POST",
        	url:"/DSMS/addPosition.do",
            data: {
            	"positionName": $("#addPositionName").val(),
            	"partsCode": $("#addPartsCode").val(),
            	"positionStatus": $("#addPositionStatus").val(),
            	"creationDate": currentTimeStamp,
            	"positionArea": thisArea
            },

            success: function (data) {
            	// 刪除舊資料
            	$("#addPositionName").val("");
            	$("#addPositionParts").val("");
            	
            	// get warning
            	if (data.warning != null) {
            		alert(data.warning);
            		return;
            	}
            	// get data
            	if (data != null && data != "") {
            		alert(data.info);
            		$("#closeAddPositionModal").click();
            	} else {
            		alert("發生異常，伺服器無回應");
            	}
            }
        });
	});
	
	$("#updatePositionBtn").click(function() {
		if ($("#updatePositionName").val() === null || $("#updatePositionName").val() === "") {
			alert("請輸入儲位名稱");
			return;
		}
		
		var currentTimeStamp = Date.now();
		var thisArea = $("#areaId").val();
		
		$.ajax({
        	type: "POST",
        	url:"/DSMS/updatePosition.do",
            data: {
            	"positionName": $("#updatePositionName").val(),
            	"partsCode": $("#updatePartsCode").val(),
            	"positionStatus": $("#updatePositionStatus").val(),
            	"modifyDate": currentTimeStamp,
            	"positionArea": thisArea
            },

            success: function (data) {
            	// 刪除舊資料
            	$("#addPositionName").val("");
            	$("#addPositionParts").val("");
            	
            	// get warning
            	if (data.warning != null) {
            		alert(data.warning);
            		return;
            	}
            	// get data
            	if (data != null && data != "") {
            		alert(data.info);
            		$("#closeUpdatePositionModal").click();
            	} else {
            		alert("發生異常，伺服器無回應");
            	}
            }
        });
	});
	
	
});

function openPositionList(o) {
	// 將儲區寫在儲位 list 的 title
	$("#modal-title").text(o.text + ": 儲位清單");
	
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

        	// get warning
        	if (data[0].warning != null) {
        		$("#existPosition").text(data[0].warning);
        		return;
        	}
        	
            // 寫入新資料
        	$("#areaId").val(o.id);
            $("#existPosition").text("");
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
        	    var newTd3 = newRow.insertCell();
        	    var newTd4 = newRow.insertCell();
        	       
        	    newTd0.innerText = tPositionName;
        	    newTd1.innerText = tPartsCode;
        	    newTd2.innerText = tStatus;
        	    newTd3.innerHTML = '<button type="button" class="btn btn-primary" onclick="openUpdatePositionModal(this)">修改</button>';
        	    newTd4.innerHTML = '<button type="button" class="btn btn-danger" onclick="delPosition(this)">刪除</button>';
        	}
        }
	});
	// 開啟儲位 list
    $("#toPositionList").click();
}

function delPosition(o) {
	var r=confirm("確認刪除該儲位嗎?");
	if (r==true)
	{
//		console.log(o.parentNode.parentNode.children[0].innerText);
//		console.log($("#areaId").val());
		var thisRow = o.parentNode.parentNode;
		
		$.ajax({
	    	type: "POST",
	    	url:"/DSMS/deletePosition.do",
	        data: {
	        	"positionArea": $("#areaId").val(),
	        	"positionName": thisRow.children[0].innerText
	        },
	        success: function (data) {

	        	// delete fail
	        	if (data.warning != null) {
	        		alert(data.warning);
	        		return;
	        	}
	        	
	        	// delete success
	        	if (data.info != null) {
	        		alert(data.info);
		        	thisRow.remove();  // 刪除該行
	        	} else { // delete fail
	        		alert("server 無回應");
	        	}
	        }
		});
	}
}

function openUpdatePositionModal(o) {
	var thisRow = o.parentNode.parentNode;
	//console.log(o.parentNode.parentNode.children[0].innerText);
	if (thisRow.children[0].innerText === "null") {
		$("#updatePositionName").val("");
	} else {
		$("#updatePositionName").val(thisRow.children[0].innerText);
	}
	if (thisRow.children[1].innerText === "null") {
		$("#updatePartsCode").val("");
	} else {
		$("#updatePartsCode").val(thisRow.children[1].innerText);
	}
	if (thisRow.children[2].innerText === "scrap") {
		$("#updatePositionStatus").val(2);
	} else {
		$("#updatePositionStatus").val(1);
	}
	
	$("#toUpdatePosition").click();
}

function returnPositionListModal() {
	var thisPositionListArea = $("#areaId").val();
	document.getElementById(thisPositionListArea).click();
}