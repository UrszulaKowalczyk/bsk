<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="<c:url value="/resources/js/editRecipieIngredient.js"/>"></script>

<c:if test="${recipieIngredientTableInfo.visible}">

<div class="container">
	<h2>Table:   <b>${recipieIngredientTableInfo.name}</b>   ( Label: ${recipieIngredientTableInfo.level} )</h2>
	<div align="right">
	<b>Search: </b><input type="text" id="recipeIngredientIdInput" onkeyup="myFunctionRecipeIngredient()" placeholder="by id" title="Type in a name">
	<b>Search: </b><input type="text" id="recipeIngredientRecipeIdInput" onkeyup="myFunctionRecipeIngredient()" placeholder="by recipe id" title="Type in a name">
	<b>Search: </b><input type="text" id="recipeIngredientIngredientIdInput" onkeyup="myFunctionRecipeIngredient()" placeholder="by ingredient id" title="Type in a name">
	</div>
	
	<table class="table" id="recipeIngredientTable">
		<thead>
			<tr>
				<c:forEach items="${recipieIngredientTableInfo.columnsNames}" var="columnName">
					<th>${columnName}</th>
				</c:forEach>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${recipieIngredientList}" var="recipieIngredient">

				<tr id="recipieIngredient_${recipieIngredient.id}">
				
					<td>${recipieIngredient.id}</td>
					<td id="recipieId_${recipieIngredient.id}">${recipieIngredient.recipieId}</td>
					<td id="ingredientId_${recipieIngredient.id}">${recipieIngredient.ingredientId}</td>
					<c:if test="${recipieIngredientTableInfo.canWrite}">
						<td>
							<button type="button" id="edit_recipieIngredient_${recipieIngredient.id}"
								onclick="editRecipieIngredient(${recipieIngredient.id})">Edit</button>
							<button type="button" id="save_recipieIngredient_${recipieIngredient.id}"
								style="display: none;"
								onclick="updateRecipieIngredient('<c:url value="/updateRecipieIngredient" />', ${recipieIngredient.id});">
								Save</button>
						</td>
						<td>
							<button type="button" id="delete_recipieIngredient_${recipieIngredient.id}"
							onclick="deleteRow('<c:url value="/deleteRecipieIngredient" />', ${recipieIngredient.id});" > Delete </button>
						</td>
					</c:if>


				</tr>

			</c:forEach>
			<form name="myFormRecipieIngredient" action="<c:url value="/addRecipieIngredient" />"
				method="post">
				<c:if test="${recipieIngredientTableInfo.canWrite}">
					<tr>
						<td><input type="text" /></td>
						<td><input name="recipieId" type="text" /></td>
						<td><input name="ingredientId" type="text" /></td>
						<td><input type="submit" value="Insert" /></td>
					</tr>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</c:if>
			</form>
		</tbody>
	</table>

<script>
	
	function myFunctionRecipeIngredient() {
	  var inputTitle, inputId, filterId, filterTitle, table, tr, td, i;
	  inputTitle = document.getElementById("recipeIngredientRecipeIdInput");
	  filterTitle = inputTitle.value.toUpperCase();
	  inputId = document.getElementById("recipeIngredientIdInput");
	  filterId = inputId.value.toUpperCase();
	  inputDescription = document.getElementById("recipeIngredientIngredientIdInput");
	  filterDescription = inputDescription.value.toUpperCase();
	  table = document.getElementById("recipeIngredientTable");
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