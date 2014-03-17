var speakerNames = [ "speaker-1", "speaker-2", "speaker-3", "speaker-4",
		"speaker-5", "speaker-6" ];
var host = "115.88.201.42";
var port = "8080";
var appName = "qna";
var refreshTime = 1000;
var textLimit = 5;

var index_title ="Q&A Platform Example";
var index_context_title = "Q&A Platform Example";
var index_context_sub_msg = "input email correctly, and press \"connect\" button.";

var no_question_msg = "There are no question, now!";

var question_placeholder_msg = "Input text more than " + textLimit + ".";
var question_fail_msg = "fail to upload question msg.";
var question_success_msg = "success to upload question msg.";

function checkReg() {
	$.ajax({
		url : "http://" + host + ":" + port + "/" + appName + "/Check",
		dataType : "jsonp",
		jsonp : "callback",
		crossDomain : true,
		data : {
			type : "check"
		},
		success : function(data) {
			if (!data.code) {
				location.href = "index.html";
			} else {
				$('#header-title').html(data.user);
			}
		}
	});
}

function signOut() {
	$.ajax({
		url : "http://" + host + ":" + port + "/" + appName + "/Check",
		dataType : "jsonp",
		jsonp : "callback",
		crossDomain : true,
		data : {
			type : "logout"
		},
		success : function(data) {
			location.href = "index.html";
		}
	});
}

function signIn(email) {
	$.ajax({
		url : "http://" + host + ":" + port + "/" + appName + "/Check",
		dataType : "jsonp",
		jsonp : "callback",
		crossDomain : true,
		data : {
			type : "login",
			user : email
		},
		success : function(data) {
			if (data.code) {
				location.href = "qna.html";
			} else {
				$("#fail-popup").popup('open');
			}
		}
	});
}
