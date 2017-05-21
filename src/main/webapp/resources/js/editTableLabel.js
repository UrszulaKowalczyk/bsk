function editTableLabel(id) {
	
	var label = $('#label_' + id).html();
	$('#label_' + id).html( '<input type="text" id="newLabel_' + id + '" name="label" value="' + label + '" >');
	
	var tableName = $('#tableName_' + id).html();
	$('#tableName_' + id).html('<input type="text" id="newTableName_' + id + '" name="tableName" value="' + tableName + '" >');
	
	$('#edit_tableLabel_' + id).hide();
	$('#save_tableLabel_' + id).show();
}

function updateTableLabel(url, id) {
	var requestParams = {
		id : id,
		label : $('#newLabel_' + id).val(),
		tableName : $('#newTableName_' + id).val()
		
	}
	var csrf = document.forms["myFormTableLabel"]["_csrf"].value;
	$.ajax({
		type : "POST",
		url : url,
		contentType : "application/json",
		headers : {
			"X-CSRF-TOKEN" : csrf
		},
		data : JSON.stringify(requestParams),
		success : function(responseValue) {
			$('#label_'+id).html(requestParams.label);
			$('#tableName_'+id).html(requestParams.tableName);
			$('#save_tableLabel_'+id).hide();
			$('#edit_tableLabel_'+id).show();
			location.reload();
		},
		error : function(url) {
			location.reload();
		}

	});

}