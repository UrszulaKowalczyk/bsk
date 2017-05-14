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
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value="/addRecipie" />"><span
						class="glyphicon glyphicon-cutlery"></span> Dodaj przepis</a></li>
				<li><a href="<c:url value="/logout" />"><span
						class="glyphicon glyphicon-log-in"></span> Wyloguj</a></li>
			</ul>
		</div>
	</nav>

	<c:forEach items="${recipies}" var="recipie">
	<br>
		<div class="container-fluid">
		<div class="row">
			<div class="col-sm-8" style="background-color: lightgray;">
				<center><h2>${recipie.title}</h2></center>
				<div class="row equal">
					<div class="col-sm-6" style="background-color: lavender"><center><h4>Składniki:</h4></center><div class="row">
						<div class="col-sm-12" style="background-color: white;">
						<ul>
							<c:forEach items="${recipie.ingredients}" var="ingredient">
							<li>${ingredient.name}</li>
							</c:forEach>
						</ul>
						</div>
					</div>
				</div>
					<div class="col-sm-6" style="background-color: lavender;"><center><h4>Sposób przygotowania:</h4></center>
						<div class="row">
							<div class="col-sm-12" style="background-color: white;">${recipie.description}</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
		
		
	</c:forEach>

</body>
</html>