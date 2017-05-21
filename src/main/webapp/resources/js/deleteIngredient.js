function deleteIngredient(url, id) {
	var requestParams = {
		id : id
	}
	var csrf = document.forms["myForm"]["_csrf"].value;
	$.ajax({
		type : "POST",
		url : url,
		contentType : "application/json",
		headers : {
			"X-CSRF-TOKEN" : csrf
		},
		data : JSON.stringify(requestParams),
		success : function(responseValue) {
			$('#ingredient_' + id).remove();
		},
		error : function(url) {
			//location.reload();
		}

	});

}