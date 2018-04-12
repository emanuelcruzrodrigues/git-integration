$(document).ready(function(){
	actionRefreshLogScroll();
});

function actionRefreshLogScroll(){
    var $textarea = $('#tfLog');
    $textarea.scrollTop($textarea[0].scrollHeight);	
}

function actionRunCommitIntegration(){
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/integration/commit",
		success : function(data) {
			$("#integrationStartedDialog").modal('show');
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});
}

function actionRefreshLog(){
	document.activeElement.blur();
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/rest/log",
		success : function(data) {
			document.getElementById('tfLog').value = data;
			actionRefreshLogScroll();
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});
}