function deleteRow(url, id) {
	var requestParams = {
		id : id
	}
	var csrf = document.forms["form"]["_csrf"].value;
	$.ajax({
		type : "POST",
		url : url,
		contentType : "application/json",
		headers : {
			"X-CSRF-TOKEN" : csrf
		},
		data : JSON.stringify(requestParams),
		success : function(responseValue) {
			location.reload();
		},
		error : function(url) {
			location.reload();
		}

	});

}