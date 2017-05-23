<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="<c:url value="/resources/js/editIngredient.js"/>"></script>

<c:if test="${ingredientTableInfo.visible}">

<div class="container">
	<h2>${ingredientTableInfo.name}( Label: ${ingredientTableInfo.level} )</h2>

	<table class="table table-bordered">
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