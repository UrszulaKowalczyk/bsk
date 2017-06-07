<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="<c:url value="/resources/js/editTableLabel.js"/>"></script>

<c:if test="${tableLabelTableInfo.visible}">

<div class="container">
	<h2>Table:   <b>${tableLabelTableInfo.name}</b>   ( Label: ${tableLabelTableInfo.level} )</h2>

<div align="right">
	<b>Search: </b><input type="text" id="tableLabelIdInput" onkeyup="myFunctionTableLabel()" placeholder="by id" title="Type in a name">
	<b>Search: </b><input type="text" id="tableLabelNameInput" onkeyup="myFunctionTableLabel()" placeholder="by name" title="Type in a name">
	<b>Search: </b><input type="text" id="tableLabelLabelInput" onkeyup="myFunctionTableLabel()" placeholder="by label" title="Type in a name">
	</div>

	<table class="table" id="tableLabelTable">
		<thead>
			<tr>
				<c:forEach items="${tableLabelTableInfo.columnsNames}" var="columnName">
					<th>${columnName}</th>
				</c:forEach>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${tableLabelList}" var="tableLabel">

				<tr id="tableLabel_${tableLabel.id}">


					<td>${tableLabel.id}</td>
					<td id="tableName_${tableLabel.id}">${tableLabel.tableName}</td>
					<td id="label_${tableLabel.id}">${tableLabel.label}</td>
					<c:if test="${tableLabelTableInfo.canWrite}">
						<td>
							<button type="button" id="edit_tableLabel_${tableLabel.id}"
								onclick="editTableLabel(${tableLabel.id})">Edit</button>
							<button type="button" id="save_tableLabel_${tableLabel.id}"
								style="display: none;"
								onclick="updateTableLabel('<c:url value="/updateTableLabel" />', ${tableLabel.id});">
								Save</button>
						</td>
						<td>
							<button type="button" id="delete_tableLabel_${tableLabel.id}"
							onclick="deleteRow('<c:url value="/deleteTableLabel" />', ${tableLabel.id});" > Delete </button>
						</td>
					</c:if>


				</tr>

			</c:forEach>
			<form name="myFormTableLabel" action="<c:url value="/addTableLabel" />"
				method="post">
				<c:if test="${tableLabelTableInfo.canWrite}">
					<tr>
						<td><input type="text" /></td>
						<td><input name="tableName" type="text" /></td>
						<td><input name="label" type="text" /></td>
						<td><input type="submit" value="Insert" /></td>
					</tr>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</c:if>
			</form>
		</tbody>
	</table>

<script>
	
	function myFunctionTableLabel() {
	  var inputTitle, inputId, filterId, filterTitle, table, tr, td, i;
	  inputTitle = document.getElementById("tableLabelNameInput");
	  filterTitle = inputTitle.value.toUpperCase();
	  inputId = document.getElementById("tableLabelIdInput");
	  filterId = inputId.value.toUpperCase();
	  inputDescription = document.getElementById("tableLabelLabelInput");
	  filterDescription = inputDescription.value.toUpperCase();
	  table = document.getElementById("tableLabelTable");
	  tr = table.getElementsByTagName("tr");
	  for (i = 0; i < tr.length; i++) {
		tdId = tr[i].getElementsByTagName("td")[0];
	    tdTitle = tr[i].getElementsByTagName("td")[1];
	    tdDescription = tr[i].getElementsByTagName("td")[2];
	    if (tdId && tdTitle && tdDescription) {
	      if ((tdTitle.innerHTML.toUpperCase().indexOf(filterTitle) > -1) && (tdId.innerHTML.toUpperCase().indexOf(filterId) > -1) && (tdDescription.innerHTML.toUpperCase().indexOf(filterDescription) > -1)) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    }       
	  }
	}
	</script>
</div>

</c:if>