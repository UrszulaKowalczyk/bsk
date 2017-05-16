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
				<li><a href="<c:url value="/" />"><span
						class="glyphicon glyphicon-list"></span> Wróć </a></li>
				<li><a href="<c:url value="/logout" />"><span
						class="glyphicon glyphicon-log-in"></span> Wyloguj</a></li>
			</ul>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-8" style="background-color: lightgray;">
				<center>
					<h3>Ingredients</h3>
				</center>
			</div
				<c:forEach items="${ingredients}" var="ingredient">
					<br>

					<div class="col-sm-8" style="background-color: lavender;">
						<center>
							<h5>${ingredient.name}</h5>
						</center>
					</div>
				</c:forEach>
	
		</div>
	</div>



</body>
</html>