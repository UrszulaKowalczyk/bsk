function editUser(id) {
	var login = $('#login_' + id).html();
	$('#login_' + id).html( '<input type="text" id="newLogin_' + id + '" name="login" value="' + login + '" >');
	
	var password = $('#password_' + id).html();
	$('#password_' + id).html('<input type="text" id="newPassword_' + id + '" name="password" value="' + password + '" >');
	
	var label = $('#label_user_' + id).html();
	$('#label_user_' + id).html('<input type="text" id="newLabel_' + id + '" name="label" value="' + label + '" >');
	
	$('#edit_user_' + id).hide();
	$('#save_user_' + id).show();
}

function updateUser(url, id) {
	var requestParams = {
		id : id,
		login : $('#newLogin_' + id).val(),
		password : $('#newPassword_' + id).val(),
		label : $('#newLabel_' + id).val()
		
	}
	var csrf = document.forms["myFormUser"]["_csrf"].value;
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