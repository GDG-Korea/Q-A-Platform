$(document).ready(
		function() {

			checkReg();

			for (var i = 0; i < speakerNames.length; i++)
				$('#speakers').append(
						"<option value=\"" + speakerNames[i] + "\">"
								+ speakerNames[i] + "</option>");

			$('#tab-qna').click(function(e) {
				location.href = "qna.html";
			});

			$('#signout-btn').click(function(e) {
				signOut();
			});

			setInterval(function() {
				loadQnA($("#speakers option:selected").val());
			}, refreshTime);

		});

function loadQnA(speaker) {
	$
			.ajax({
				url : "http://" + host + ":" + port + "/" + appName
						+ "/LoadQnA",
				dataType : "jsonp",
				jsonp : "callback",
				crossDomain : true,
				data : {
					speaker : speaker
				},
				success : function(data) {
					var str = "";
					if (data.length == 0)
						str = no_question_msg;

					$('#header-title').html("Count : " + data.length);
					
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