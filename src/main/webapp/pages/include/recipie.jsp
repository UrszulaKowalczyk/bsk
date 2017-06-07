<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="<c:url value="/resources/js/editRecipie.js"/>"></script>

<c:if test="${recipieTableInfo.visible}">

<div class="container">
	<h2>Table:   <b>${recipieTableInfo.name}</b>   ( Label: ${recipieTableInfo.level} )</h2>

	<div align="right">
	<b>Search: </b><input type="text" id="recipeIdInput" onkeyup="myFunctionRecipe()" placeholder="by recipe id" title="Type in a name">
	<b>Search: </b><input type="text" id="recipeTitleInput" onkeyup="myFunctionRecipe()" placeholder="by recipe title" title="Type in a name">
	<b>Search: </b><input type="text" id="recipeDescriptionInput" onkeyup="myFunctionRecipe()" placeholder="by recipe description" title="Type in a name">
	</div>
	<table class="table" id="recipeTable">
		<thead>
			<tr>
				<c:forEach items="${recipieTableInfo.columnsNames}" var="columnName">
					<th>${columnName}</th>
				</c:forEach>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${recipieList}" var="recipie">

				<tr id="recipie_${recipie.id}">


					<td>${recipie.id}</td>
					<td id="title_${recipie.id}">${recipie.title}</td>
					<td id="description_${recipie.id}">${recipie.description}</td>
					<c:if test="${recipieTableInfo.canWrite}">
						<td>
							<button type="button" id="edit_recipie_${recipie.id}"
								onclick="editRecipie(${recipie.id})">Edit</button>
							<button type="button" id="save_recipie_${recipie.id}"
								style="display: none;"
								onclick="updateRecipie('<c:url value="/updateRecipie" />', ${recipie.id});">
								Save</button>
						</td>
						<td>
							<button type="button" id="delete_recipie_${recipie.id}"
							onclick="deleteRow('<c:url value="/deleteRecipie" />', ${recipie.id});" > Delete </button>
						</td>
					</c:if>


				</tr>

			</c:forEach>
			<form name="myFormRecipie" action="<c:url value="/addRecipie" />"
				method="post">
				<c:if test="${recipieTableInfo.canWrite}">
					<tr>
						<td><input type="text" /></td>
						<td><input name="title" type="text" /></td>
						<td><input name="description" type="text" /></td>
						<td><input type="submit" value="Insert" /></td>
					</tr>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</c:if>
			</form>
		</tbody>
	</table>
	
	<script>
	
	function myFunctionRecipe() {
	  var inputTitle, inputId, filterId, filterTitle, table, tr, td, i;
	  inputTitle = document.getElementById("recipeTitleInput");
	  filterTitle = inputTitle.value.toUpperCase();
	  inputId = document.getElementById("recipeIdInput");
	  filterId = inputId.value.toUpperCase();
	  inputDescription = document.getElementById("recipeDescriptionInput");
	  filterDescription = inputDescription.value.toUpperCase();
	  table = document.getElementById("recipeTable");
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