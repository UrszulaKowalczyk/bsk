<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="<c:url value="/resources/js/editRecipie.js"/>"></script>
<div class="container">
	<h2>${recipieTableInfo.name}(${recipieTableInfo.level})</h2>

	<table class="table">
		<thead>
			<tr>
				<c:forEach items="${recipieTableInfo.columnsNames}" var="columnName">
					<th>${columnName}</th>
				</c:forEach>
				<th></th>
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
						<!-- 						<td>
							<button type="button" id="delete_${ingredient.id}"
							onclick="deleteIngredient('<c:url value="/deleteIngredient" />', ${ingredient.id});" > Delete </button>
						</td> -->
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
						<td><input type="submit" value="Save" /></td>
					</tr>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</c:if>
			</form>
		</tbody>
	</table>


</div>