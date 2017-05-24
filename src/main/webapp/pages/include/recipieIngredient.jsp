<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="<c:url value="/resources/js/editRecipieIngredient.js"/>"></script>

<c:if test="${recipieIngredientTableInfo.visible}">

<div class="container">
	<h2>${recipieIngredientTableInfo.name}   ( Label: ${recipieIngredientTableInfo.level} )</h2>

	<table class="table">
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
						<td><input type="submit" value="Save" /></td>
					</tr>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</c:if>
			</form>
		</tbody>
	</table>


</div>

</c:if>