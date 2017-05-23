function editRecipieIngredient(id) {
	
	var recipieId = $('#recipieId_' + id).html();
	$('#recipieId_' + id).html( '<input type="text" id="newRecipieId_' + id + '" name="recipieId" value="' + recipieId + '" >');
	
	var ingredientId = $('#ingredientId_' + id).html();	
	$('#ingredientId_' + id).html('<input type="text" id="newIngredientId_' + id + '" name="ingredientId" value="' + ingredientId + '" >');
	
	$('#edit_recipieIngredient_' + id).hide();
	$('#save_recipieIngredient_' + id).show();
}

function updateRecipieIngredient(url, id) {
	var requestParams = {
		id : id,
		recipieId : $('#newRecipieId_' + id).val(),
		ingredientId : $('#newIngredientId_' + id).val()
		
	}
	var csrf = document.forms["myFormRecipieIngredient"]["_csrf"].value;
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