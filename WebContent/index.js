$(document).ready(function() {

	$.ajax({
		url : "http://" + host + ":" + port + "/" + appName + "/Check",
		dataType : "jsonp",
		jsonp : "callback",
		crossDomain : true,
		data : {
			type : "check"
		},
		success : function(data) {
			if (data.code) {
				location.href = "qna.html";
			}
		}
	});

	$('#login-btn').click(function(e) {
		var email = $('#email').val();
		if (email.length > 0)
			signIn(email);
	});

	$('#header-title').html(index_title);
	$('#context-title').html(index_context_title);
	$('#context-sub-msg').html(index_context_sub_msg);
});
