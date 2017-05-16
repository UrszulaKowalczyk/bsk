<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="include/head.jsp" />
<script src="<c:url value="/resources/js/addNext.js"/>"></script>
</head>

<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value="/" />"><span
						class="glyphicon glyphicon-list"></span> Wróć </a></li>
				<li><a href="<c:url value="/logout" />"><span
						class="glyphicon glyphicon-log-in"></span> Wyloguj</a></li>
			</ul>
		</div>
	</nav>
	<h2>Dodaj składnik:</h2>

	<form action="<c:url value="/addIngredient" />" method="post">
		<div class="container">
			<div class="row">
				<div class="col-xs-6">
					<div class="form-group">
						<label for="title">Nazwa: </label> <input type="text" name="name"
							id="title" class="form-control">
					</div>

					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <input type="submit"
						value="Dodaj składnik">
				</div>
			</div>
		</div>

	</form>

</body>
</html>