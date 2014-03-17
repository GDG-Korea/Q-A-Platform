$(document).ready(
		function() {

			checkReg();

			for (var i = 0; i < speakerNames.length; i++)
				$('#speakers').append(
						"<option value=\"" + speakerNames[i] + "\">"
								+ speakerNames[i] + "</option>");
			if (speakerNames.length > 0) {
				$("#speakers").parent().find("span").html(speakerNames[0]);
				$("#speakers option:eq(0)").attr("selected", "selected");
			}

			$('#save-qna').click(function(e) {
				checkReg();
				var msg = $('#qna-msg').val();
				if (msg.length >= textLimit)
					saveQnA($("#speakers option:selected").val(), msg);
				else
					$("#low-len-popup").popup('open');
			});

			$('#tab-list').click(function(e) {
				location.href = "list.html";
			});

			$('#signout-btn').click(function(e) {
				signOut();
			});

			$('#qna-msg').attr("placeholder", question_placeholder_msg);
			$('#low-len-msg').html(question_placeholder_msg);
			$('#question-fail-msg').html(question_fail_msg);
			$('#question-success-msg').html(question_success_msg);

			setInterval(function() {
				loadQnA();
			}, refreshTime);

		});

function loadQnA() {
	$
			.ajax({
				url : "http://" + host + ":" + port + "/" + appName
						+ "/LoadQnA",
				dataType : "jsonp",
				jsonp : "callback",
				crossDomain : true,
				data : {
					speaker : "all"
				},
				success : function(data) {
					var str = "";
					if (data.length == 0)
						str = "<br/>" + no_question_msg;
					for (var i = 0; i < data.length; i++) {
						str += "<br/><div class=\"ui-corner-all custom-corners\"><div class=\"ui-bar ui-bar-a\"><a href=\"mailto://"
								+ data[i].user
								+ "\" target=\"_blank\">"
								+ data[i].user
								+ "</a> -> "
								+ data[i].speaker
								+ "</div><div class=\"ui-body ui-body-a\">"
								+ data[i].msg + "</div></div>"
					}

					$('#qna').html(str);
				}
			});
}

function saveQnA(speaker, msg) {
	$.ajax({
		url : "http://" + host + ":" + port + "/" + appName + "/SaveQnA",
		dataType : "jsonp",
		jsonp : "callback",
		crossDomain : true,
		data : {
			speaker : speaker,
			msg : msg
		},
		success : function(data) {
			$('#qna-msg').val('');
			if (data == null || !data.code) {
				$("#fail-popup").popup('open');
			} else {
				$("#success-popup").popup('open');
			}
		}
	});
}