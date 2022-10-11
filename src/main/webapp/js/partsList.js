var partsObj;

$(function () {
	// 執行 form 搜尋 parts list
	$("#searchParts").submit(function (event) {
		$("#pageIndex").val(1); // 確保執行新一次搜尋時 頁碼為1 開始
		doSearchParts ();
		return false;  // 使 form submit 不會全頁面刷新
	});
	
	// parts table 中每一個刪除紐功能，delegate 使得後續 ajax 局部刷新的 "刪除"鈕也帶有 js功能
	$("#tbody").delegate(".deleteParts","click",function(){
		partsObj = $(this);
		changeDLGContent("確定要刪除物料【"+partsObj.attr("partsname")+"】嗎？");
		$("#toDeleteDLG").click();
	});
	
	// 按下確認刪除紐，執行刪除功能
	$('#deleteBtnYes').click(function () {
		deleteParts(partsObj);
	});
	
	// 新增物料 => 開啟物料框
	$('#toAddDLG').click(function () {
		// 清空 form 資料
		clearPartsForm();
	});
	
	// parts table 中每一個修改紐功能 => 開啟物料框
	$("#tbody").delegate(".modifyParts","click",function(){
		partsObj = $(this);
		// form 寫入選擇的使用者資料
		inputPartsForm(partsObj);
	});
	
	// form 確認鍵 => submit
	$("#addUpdateBtnYes").click(function () {
		$("#addUpdateDLGSubmit").click();
	});
	
	// 實際執行 修改 OR 新增 parts
	$("#addUpdateForm").submit(function (event) {

		// 新增與修改 URL 路徑不同
		var thisURL = "updateParts.do";
		if ($("#pid").val() == "") { // 新增沒有 pid
			thisURL = "addParts.do";
		}

		var inputParts = {
			id : $("#pid").val(),
			partsCode : $("#partsCode").val(),
			partsName : $("#partsName").val(),
			partsDepartment : $("#partsDeptName").val(),
			currrentDate : Date.now() // timeStamp
		};
		
		$.ajax({
			type:"POST",
			url:"/DSMS/" + thisURL,
			data:{partsFromView : JSON.stringify(inputParts)},
			dataType:"json",
			success:function(data){
				if (data.result == "true") {
					alert("執行成功");
					// 成功後重新刷新 parts table
					$("#pageIndex").val(1); // 確保執行新一次搜尋時 頁碼為1 開始
					doSearchParts ();
				} else if (data.result == "notexist") {
					alert("該物料不存在");
				} else if (data.result == "partscodebeenused") {
					alert("該料號已存在");
				} else if (data.result == "false") {
					alert("執行失敗");
				}
			},
			error:function(data){
				alert("對不起，執行異常");
			}
		});
		return false;
	});
});

// 捨棄了 jsp 判斷，改用 js => onload 會在載入頁面時執行
window.onload = function () {
    judgePageList();
}

//刪除 parts table 舊資料
function removePartsTable() {
	var originRowsLength = $("#partsTable")[0].rows.length;
	for (let i=1; i<originRowsLength; i++) {
		$("#partsTable")[0].deleteRow(1);
	}
}

// 刷新 parts table
function updatePartsTable(obj) {
	
	for (let i=3; i<obj.length; i++) {
		var newRow = tbody.insertRow();
    	var newTd0 = newRow.insertCell();
        var newTd1 = newRow.insertCell();
        var newTd2 = newRow.insertCell();
        var newTd3 = newRow.insertCell();

        newTd0.innerText = obj[i].partsCode;
        newTd1.innerText = obj[i].partsName;
        newTd2.innerText = obj[i].partsDeptName;
        newTd3.innerHTML = '<span><a class="modifyParts" href="javascript:;" partsid="" partsname="">修改</a></span> <span><a class="deleteParts" href="javascript:;" partsid="" partsname="">删除</a></span>';
        $("#tbody")[0].children[i-3].children[3].children[0].children[0].setAttribute("partsid",obj[i].id);
        $("#tbody")[0].children[i-3].children[3].children[0].children[0].setAttribute("partsname",obj[i].partsName);
        $("#tbody")[0].children[i-3].children[3].children[1].children[0].setAttribute("partsid",obj[i].id);
        $("#tbody")[0].children[i-3].children[3].children[1].children[0].setAttribute("partsname",obj[i].partsName);
	}
	
	$("#pageSpan").text("共" + obj[0].totalCount + "筆紀錄  " + obj[1].currentPageNo + "/" + obj[2].totalPageCount + "頁");
	$("#totalPageCount").val(obj[2].totalPageCount);
	$("#currentPageNo").val(obj[1].currentPageNo);
}

// 判斷切換頁面按鈕顯示
function judgePageList() {
	if ($("#currentPageNo").val() > 1) {
		$("#firstPage").show();
		$("#previousPage").show();
	} else {
		$("#firstPage").hide();
		$("#previousPage").hide();
	}
	if ($("#currentPageNo").val() < $("#totalPageCount").val()) {
		$("#nextPage").show();
		$("#lastPage").show();
	} else {
		$("#nextPage").hide();
		$("#lastPage").hide();
	}
}


function changePage(form,pageNumber){
	form.pageIndex.value = pageNumber;
	doSearchParts();
}

// 執行 ajax 查詢 part list 並局部刷新
function doSearchParts () {
	
	$.ajax({
		type:"POST",
		url:"/DSMS/searchParts.do",
		data:{
			partsCode:$("#queryPartsCode").val(),
			deptId:$("#queryPartsDept").val(),
			pageIndex:$("#pageIndex").val()
		},
		dataType:"json",
		success:function(data){
			
			if(data != null){
				// 清除原本 parts table
				removePartsTable();
				// 刷新 parts table 與 page 按鈕
				updatePartsTable(data);
				judgePageList();
			}
		},
		error:function(data){
			alert("對不起，搜尋異常");
		}
	});
}


function checkPageNumAndDo(form,num){
	//驗證用戶的輸入
	var regexp=/^[1-9]\d*$/;
	var totalPageCount = document.getElementById("totalPageCount").value;
	
	if(!regexp.test(num)){
		alert("請輸入大於0的正整數！");
		return false;
	}else if((num-totalPageCount) > 0){
		alert("請輸入小於總頁數的頁碼");
		return false;
	}else{
		changePage(form,num);
	}
}

// 刷新 DLG 內容
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

// 執行刪除功能
function deleteParts(obj){
	$.ajax({
		type:"GET",
		url:"/DSMS/deleteParts.do",
		data:{pid:obj.attr("partsid")},
		dataType:"json",
		success:function(data){
			if(data.delResult == "true"){//刪除成功：移除刪除行
				$("#closedeleteDLG").click();
				obj.parents("tr").remove();
			}else if(data.delResult == "false"){//刪除失敗
				alert("對不起，刪除物料【"+obj.attr("partsname")+"】失敗");
			}else if(data.delResult == "notexist"){
				alert("對不起，刪除物料【"+obj.attr("partsname")+"】不存在");
			}else if(data.delResult == "cannotdel") {
				alert("對不起，刪除物料【"+obj.attr("partsname")+"】庫房中有數量");
			}
		},
		error:function(data){
			alert("對不起，刪除失敗");
		}
	});
}

//要新增先清空 form物料框 資料
function clearPartsForm() {
	$("#addUpdateTitle").text("新增物料");  // 調整框的名稱
	$("#pid").val(""); // 新增人員沒有輸入id
	$("#partsCode").val("");
	$("#partsCode").attr("disabled",false);
	$("#partsName").val("");
	$("#partsDeptName").val("0");
	$("#partsDeptName").attr("disabled",false);
}

// 要修改由 partsid 查詢資料 ajax 寫入物料框
function inputPartsForm(obj) {
	$.ajax({
		type:"GET",
		url:"/DSMS/getUpdateParts.do",
		data:{pid:obj.attr("partsid")},
		dataType:"json",
		success:function(data){
			if (data != null && data.updateParts == null) {  //拿到要 update 的 parts 資訊
				$("#pid").val(obj.attr("partsid")); // 修改物料獲取id
				$("#addUpdateTitle").text("修改物料");  // 調整框的名稱
				// 將 parts 資料寫入
				$("#partsCode").val(data.partsCode);
				$("#partsCode").attr("disabled",true);
				$("#partsName").val(data.partsName);    // 只能修改物料名稱
				$("#partsDeptName").val(data.partsDepartment);
				$("#partsDeptName").attr("disabled",true);
				$("#toUpdateDLG").click();  // 開啟修改框
			} else {
				alert("沒有取得該物料資訊");
			}
		},
		error:function(data){
			alert("對不起，取得 parts 資訊異常");
		}
	});
}