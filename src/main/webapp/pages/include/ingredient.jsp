<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="<c:url value="/resources/js/editIngredient.js"/>"></script>
<div class="container">
	<h2>${ingredientTableInfo.name}(${ingredientTableInfo.level})</h2>

	<table class="table">
		<thead>
			<tr>
				<c:forEach items="${ingredientTableInfo.columnsNames}"
					var="columnName">
					<th>${columnName}</th>
				</c:forEach>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ingredientList}" var="ingredient">
	
				<tr>
				
					<td>${ingredient.id}</td>
					<td id="name_${ingredient.id}">${ingredient.name}</td>
					<c:if test="${ingredientTableInfo.canWrite}">
						<td>
							<button type="button" id="edit_${ingredient.id}" onclick="editIngredient(${ingredient.id})">Edit</button>
							<button type="button" id="save_${ingredient.id}" style="display: none;"
							onclick="updateIngredient('<c:url value="/updateIngredient" />', ${ingredient.id});" > Save </button>
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