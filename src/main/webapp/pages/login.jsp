<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="include/head.jsp" />
</head>

<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">PRZEPISY MA RYSI</a>
			</div>
		</div>
	</nav>
	
	<div class="container" style="width: 400px;">
		<form class="form-signin" action="<c:url value="/login"/>"
			method="post">
			<h2 style="margin-top: 0px;" class="form-signin-heading">Logowanie</h2><br>
			<label for="username" class="sr-only"></label> <input type="text"
				name="username" id="username" class="form-control"
				placeholder="Login" required autofocus> <br> <label
				for="password" class="sr-only"></label> <input
				type="password" name="password" id="password" class="form-control"
				placeholder="Hasło" required> <input type="hidden"
				name="${_csrf.parameterName}" value="${_csrf.token}" /><br>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Zaloguj</button>
		</form>

	</div>
	<!-- /container -->

</body>
</html>