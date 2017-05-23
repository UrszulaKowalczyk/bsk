<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="<c:url value="/resources/js/editUser.js"/>"></script>

<c:if test="${userTableInfo.visible}">

<div class="container">
	<h2>${userTableInfo.name}( Label: ${userTableInfo.level} )</h2>

	<table class="table table-bordered">
		<thead>
			<tr>
				<c:forEach items="${userTableInfo.columnsNames}" var="columnName">
					<th>${columnName}</th>
				</c:forEach>

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
						<td>
							<button type="button" id="delete_user_${user.id}"
							onclick="deleteRow('<c:url value="/deleteUser" />', ${user.id});" > Delete </button>
						</td>
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

</c:if>