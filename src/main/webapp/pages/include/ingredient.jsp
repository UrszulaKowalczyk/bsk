<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="<c:url value="/resources/js/editIngredient.js"/>"></script>

<c:if test="${ingredientTableInfo.visible}">

<div class="container">
	<h2>Table:   <b>${ingredientTableInfo.name}</b>   ( Label: ${ingredientTableInfo.level} )</h2>

	<div align="right">
	<b>Search: </b><input type="text" id="ingredientIdInput" onkeyup="myFunctionIngredient()" placeholder="by ingredient id" title="Type in a name">
	<b>Search: </b><input type="text" id="ingredientNameInput" onkeyup="myFunctionIngredient()" placeholder="by ingredient name" title="Type in a name">
	</div>
	<table class="table" id="ingredientTable">
		<thead>
			<tr>
				<c:forEach items="${ingredientTableInfo.columnsNames}"
					var="columnName">
					<th>${columnName}</th>
				</c:forEach>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ingredientList}" var="ingredient">
	
				<tr id="ingredient_${ingredient.id}">
				
				
					<td>${ingredient.id}</td>
					<td id="name_${ingredient.id}">${ingredient.name}</td>
					<c:if test="${ingredientTableInfo.canWrite}">
						<td>
							<button type="button" id="edit_ingredient_${ingredient.id}" onclick="editIngredient(${ingredient.id})">Edit</button>
							<button type="button" id="save_ingredient_${ingredient.id}" style="display: none;"
							onclick="updateIngredient('<c:url value="/updateIngredient" />', ${ingredient.id});" > Save </button>
						</td>
 						<td>
							<button type="button" id="delete_ingredient_${ingredient.id}"
							onclick="deleteRow('<c:url value="/deleteIngredient" />', ${ingredient.id});" > Delete </button>
						</td>
					</c:if>
						
			
				</tr>
				
			</c:forEach>
			<form name="myForm" action="<c:url value="/addIngredient" />" method="post">
				<c:if test="${ingredientTableInfo.canWrite}">
					<tr>
						<td><input type="text" /></td>
						<td><input name="name" type="text" /></td>
						<td><input type="submit" value="Insert" /></td>
					</tr>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</c:if>
			</form>
		</tbody>
	</table>

	<script>
	
	function myFunctionIngredient() {
	  var inputName, inputId, filterId, filterName, table, tr, td, i;
	  inputName = document.getElementById("ingredientNameInput");
	  filterName = inputName.value.toUpperCase();
	  inputId = document.getElementById("ingredientIdInput");
	  filterId = inputId.value.toUpperCase();
	  table = document.getElementById("ingredientTable");
	  tr = table.getElementsByTagName("tr");
	  for (i = 0; i < tr.length; i++) {
		tdId = tr[i].getElementsByTagName("td")[0];
	    tdName = tr[i].getElementsByTagName("td")[1];
	    if (tdId && tdName) {
	      if ((tdName.innerHTML.toUpperCase().indexOf(filterName) > -1) && (tdId.innerHTML.toUpperCase().indexOf(filterId) > -1)) {
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