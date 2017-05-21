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
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value="/logout" />"><span
						class="glyphicon glyphicon-log-in"></span> Wyloguj</a></li>
			</ul>
		</div>
	</nav>

	<jsp:include page="include/ingredient.jsp"/>
	<jsp:include page="include/recipie.jsp"/>
	<jsp:include page="include/recipieIngredient.jsp"/>

</body>
</html>