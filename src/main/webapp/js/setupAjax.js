$.ajaxSetup({
	type: "POST",
	complete: function(xhr, status) {
		var sessionStatus = xhr.getResponseHeader("sessionstatus");
		if (sessionStatus == "timeout") {
			alert("登入超時，請重新登入");
			window.top.location.href = window.top.location;
		}
	}
});