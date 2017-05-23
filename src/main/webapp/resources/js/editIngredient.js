function editIngredient(id) {
	console.log(id);
	var name = $('#name_' + id).html();
	$('#name_' + id).html(
			'<input type="text" id="newName_' + id + '" name="name" value="'
					+ name + '" >');
	$('#edit_ingredient_' + id).hide();
	$('#save_ingredient_' + id).show();
}

function updateIngredient(url, id) {
	var requestParams = {
		id : id,
		name : $('#newName_' + id).val()
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
			location.reload();
		},
		error : function(url) {
			location.reload();
		}

	});

}