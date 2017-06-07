<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="<c:url value="/resources/js/editUser.js"/>"></script>

<c:if test="${userTableInfo.visible}">

<div class="container">
	<h2>Table:   <b>${userTableInfo.name}</b>   ( Label: ${userTableInfo.level} )</h2>
	
	<div align="right">
	<b>Search: </b><input type="text" id="userIdInput" onkeyup="myFunctionUser()" placeholder="by id" title="Type in a name">
	<b>Search: </b><input type="text" id="userNameInput" onkeyup="myFunctionUser()" placeholder="by login" title="Type in a name">
	</div>

	<table class="table" id="userTable">
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
						<td><input type="submit" value="Insert" /></td>
					</tr>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</c:if>
			</form>
		</tbody>
	</table>
<script>
	
	function myFunctionUser() {
	  var inputTitle, inputId, filterId, filterTitle, table, tr, td, i;
	  inputTitle = document.getElementById("userNameInput");
	  filterTitle = inputTitle.value.toUpperCase();
	  inputId = document.getElementById("userIdInput");
	  filterId = inputId.value.toUpperCase();
	  table = document.getElementById("userTable");
	  tr = table.getElementsByTagName("tr");
	  for (i = 0; i < tr.length; i++) {
		tdId = tr[i].getElementsByTagName("td")[0];
	    tdTitle = tr[i].getElementsByTagName("td")[1];
	    if (tdId && tdTitle) {
	      if ((tdTitle.innerHTML.toUpperCase().indexOf(filterTitle) > -1) && (tdId.innerHTML.toUpperCase().indexOf(filterId) > -1)) {
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