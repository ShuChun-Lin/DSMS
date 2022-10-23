var userObj;

$(function(){
	
	$('#deleteBtnYes').click(function () {
		deleteUser(userObj);
	});

//	$(".deleteUser").on("click",function(){
//		userObj = $(this);
//		changeDLGContent("確定要刪除員工【"+userObj.attr("username")+"】嗎？");
//		$("#toDeleteDLG").click();
//	});
	
	$("#tbody").delegate(".deleteUser","click",function(){
		userObj = $(this);
		changeDLGContent("確定要刪除員工【"+userObj.attr("username")+"】嗎？");
		$("#toDeleteDLG").click();
	});
	
	$("#tbody").delegate(".modifyUser","click",function(){
		userObj = $(this);
		// form 寫入選擇的使用者資料
		inputUserForm(userObj);
	});
	
	$('#toAddDLG').click(function () {
		// 清空 form 資料
		clearUserForm();
	});
	
	$("#addUpdateBtnYes").click(function () {
		$("#addUpdateDLGSubmit").click();
	})
	
	$("#searchUser").on("click",function(){
		$.ajax({
			type:"GET",
			url:"/DSMS/searchUser.do",
			data:{
				userName:$("#queryUserName").val(),
				roleId:$("#queryUserRole").val(),
				deptId:$("#queryUserDept").val()
			},
			dataType:"json",
			success:function(data){
				if(data != null){
					userList = data;
					removeUserTable();
					updateUserTable(userList);
					console.log(userList);
				}
			},
			error:function(data){
				alert("對不起，搜尋異常");
			}
		});
	});
	
	$("form").submit(function (event) {

		// 新增人員有密碼，修改沒有密碼
		var pwd = "";
		var thisURL = "updateUser.do";
		if ($("#uid").val() == "") {
			pwd = $("#pwd").val();
			thisURL = "addUser.do";
		}

		var inputUser = {
			id : $("#uid").val(),
			userCode : $("#userCode").val(),
			userName : $("#userName").val(),
			userPassword : pwd,
			userId : $("#userId").val(),
			userDepartment : $("#userDeptName").val(),
			userRole : $("#userRoleName").val(),
			currrentDate : Date.now() // timeStamp
		};
		
		$.ajax({
			type:"POST",
			url:"/DSMS/" + thisURL,
			data:{userFromView : JSON.stringify(inputUser)},
			dataType:"json",
			success:function(data){
				if (data.result == "true") {
					alert("執行成功");
					$("#searchUser").click();
				} else if (data.result == "cannotUpdate") {
					alert("無法修改自己");
				} else if (data.result == "notexist") {
					alert("該員工不存在");
				} else if (data.result == "useridbeenused") {
					alert("該工號已存在");
				} else if (data.result == "usercodebeenused") {
					alert("該帳號已被使用");
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

function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

function removeUserTable() {
	var originRowsLength = $("#userTable")[0].rows.length;
	for (let i=1; i<originRowsLength; i++) {
		$("#userTable")[0].deleteRow(1);
	}
}

function updateUserTable(o) {
	for (let i=0; i<o.length; i++) {
		var newRow = tbody.insertRow();
    	var newTd0 = newRow.insertCell();
        var newTd1 = newRow.insertCell();
        var newTd2 = newRow.insertCell();
        var newTd3 = newRow.insertCell();
        var newTd4 = newRow.insertCell();
        var newTd5 = newRow.insertCell();
        
        newTd0.innerText = o[i].userCode;
        newTd1.innerText = o[i].userName;
        newTd2.innerText = o[i].userId;
        newTd3.innerText = o[i].userDepartmentName;
        newTd4.innerText = o[i].userRoleName;
        newTd5.innerHTML = '<span><a class="modifyUser" href="javascript:;" userid="" username="">修改</a></span> <span><a class="deleteUser" href="javascript:;" userid="" username="">删除</a></span>';
        $("#tbody")[0].children[i].children[5].children[0].children[0].setAttribute("userid",o[i].id);
        $("#tbody")[0].children[i].children[5].children[0].children[0].setAttribute("username",o[i].userName);
        $("#tbody")[0].children[i].children[5].children[1].children[0].setAttribute("userid",o[i].id);
        $("#tbody")[0].children[i].children[5].children[1].children[0].setAttribute("username",o[i].userName);
	}
}

function clearUserForm() {
	$("#addUpdateTitle").text("新增人員");  // 調整框的名稱
	$("#uid").val(""); // 新增人員沒有輸入id
	$("#userCode").val("");
	$("#pwdLabel").text("密碼: (預設為000000)");
	$("#pwd").val("000000");
	$("#pwd").attr("disabled",false);
	$("#userName").val("");
	$("#userId").val("");
	$("#userId").attr("disabled",false);
	$("#userDeptName").val("0");
	$("#userRoleName").val("0");
}

function inputUserForm(obj) {
	$.ajax({
		type:"GET",
		url:"/DSMS/getUpdateUser.do",
		data:{uid:obj.attr("userid")},
		dataType:"json",
		success:function(data){
			if (data != null && data.updateUser == null) {  //拿到要 update 的 user 資訊
				$("#uid").val(obj.attr("userid")); // 修改人員獲取id
				$("#addUpdateTitle").text("修改人員");  // 調整框的名稱
				// 將 user 資料寫入
				$("#userCode").val(data.userCode);
				$("#pwdLabel").text("密碼:");
				$("#pwd").val("");
				$("#pwd").attr("disabled",true);
				$("#userName").val(data.userName);
				$("#userId").val(data.userId);
				$("#userId").attr("disabled",true);
				$("#userDeptName").val(data.userDepartment);
				$("#userRoleName").val(data.userRole);
				$("#toUpdateDLG").click();  // 開啟修改框
			} else if (data.updateUser == "cannotUpdate") {
				alert("不能修改自己");
			} else if (data.updateUser == "cannotUpdateADMIN") {
				alert("權限不足不能修改系統管理員");
			} else {
				alert("沒有取得該員工資訊");
			}
		},
		error:function(data){
			alert("對不起，取得 user 資訊異常");
		}
	});
}


function deleteUser(obj){
	$.ajax({
		type:"GET",
		url:"/DSMS/deleteUser.do",
		data:{uid:obj.attr("userid")},
		dataType:"json",
		success:function(data){
			if(data.delResult == "true"){//刪除成功：移除刪除行
				$("#closedeleteDLG").click();
				obj.parents("tr").remove();
			}else if(data.delResult == "false"){//刪除失敗
				alert("對不起，刪除員工【"+obj.attr("username")+"】失敗");
			}else if(data.delResult == "notexist"){
				alert("對不起，刪除員工【"+obj.attr("username")+"】不存在");
			}else if(data.delResult == "cannotdel"){
				alert("對不起，刪除員工【"+obj.attr("username")+"】不能刪除自己");
			}else if(data.delResult == "cannotdelADMIN"){
				alert("對不起，刪除員工【"+obj.attr("username")+"】權限不足不能刪除系統管理員");
			}
		},
		error:function(data){
			alert("對不起，刪除失敗");
		}
	});
}