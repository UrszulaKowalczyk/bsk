<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="<c:url value="/resources/js/editUser.js"/>"></script>
<div class="container">
	<h2>${userTableInfo.name}(${userTableInfo.level})</h2>

	<table class="table">
		<thead>
			<tr>
				<c:forEach items="${userTableInfo.columnsNames}" var="columnName">
					<th>${columnName}</th>
				</c:forEach>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList}" var="user">

				<tr id="user_${user.id}">


					<td>${user.id}</td>
					<td id="login_${user.id}">${user.login}</td>
					<td id="password_${user.id}">${user.password}</td>
					<td id="label_user_${user.id}">${user.label}</td>
					<c:if test="${userTableInfo.canWrite}">
						<td>
							<button type="button" id="edit_user_${user.id}"
								onclick="editUser(${user.id})">Edit</button>
							<button type="button" id="save_user_${user.id}"
								style="display: none;"
								onclick="updateUser('<c:url value="/updateUser" />', ${user.id});">
								Save</button>
						</td>
						<!-- 						<td>
							<button type="button" id="delete_${ingredient.id}"
							onclick="deleteIngredient('<c:url value="/deleteIngredient" />', ${ingredient.id});" > Delete </button>
						</td> -->
					</c:if>


				</tr>

			</c:forEach>
			<form name="myFormUser" action="<c:url value="/addUser" />"
				method="post">
				<c:if test="${userTableInfo.canWrite}">
					<tr>
						<td><input type="text" /></td>
						<td><input name="login" type="text" /></td>
						<td><input name="password" type="text" /></td>
						<td><input name="label" type="text" /></td>
						<td><input type="submit" value="Save" /></td>
					</tr>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</c:if>
			</form>
		</tbody>
	</table>


</div>