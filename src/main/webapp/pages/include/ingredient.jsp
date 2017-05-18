<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<h2>${ingredientTableInfo.name}(${ingredientTableInfo.level})</h2>
	<form action="<c:url value="/addIngredient" />" method="post">
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
						<td>${ingredient.name}</td>
					</tr>
				</c:forEach>
				<c:if test="${ingredientTableInfo.canWrite}">
					<tr>
						<td><input type="text" /></td>
						<td><input name="name" type="text" /></td>
						<td><input type="submit" value="Save" /></td>
					</tr>
				</c:if>

			</tbody>
		</table>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
</div>