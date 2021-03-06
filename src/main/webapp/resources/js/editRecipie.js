function editRecipie(id) {
	var title = $('#title_' + id).html();
	$('#title_' + id).html( '<input type="text" id="newTitle_' + id + '" name="title" value="' + title + '" >');
	var description = $('#description_' + id).html();
	$('#description_' + id).html('<input type="text" id="newDescription_' + id + '" name="description" value="' + description + '" >');
	$('#edit_recipie_' + id).hide();
	$('#save_recipie_' + id).show();
}

function updateRecipie(url, id) {
	var requestParams = {
		id : id,
		title : $('#newTitle_' + id).val(),
		description : $('#newDescription_' + id).val()
		
	}
	var csrf = document.forms["myFormRecipie"]["_csrf"].value;
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