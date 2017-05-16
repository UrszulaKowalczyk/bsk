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

	<table class="table table-striped">
		<thead>
			<tr>
				<th></th>
				<th>Ingredients</th>
				<th>Recipies</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Read</td>
				<td><c:forEach items="${readeableTables}" var="table">
						<c:if test="${table.tableName == 'ingredient'}">
							<form action="<c:url value="/showIngredients" />" method="get">
								<input type="submit" type="button" class="btn btn-default" value="Read" />
							</form>
						</c:if>
					</c:forEach></td>
				<td><c:forEach items="${readeableTables}" var="table">
						<c:if test="${table.tableName == 'recipie'}">
							<form action="<c:url value="/showRecipies" />" method="get">
								<input type="submit" type="button" class="btn btn-default" value="Read" />
							</form>
						</c:if>
					</c:forEach></td>
			</tr>
			<tr>
				<td>Write</td>
				<td><c:forEach items="${writeableTables}" var="table">
						<c:if test="${table.tableName == 'ingredient'}">
							<form action="<c:url value="/addIngredient" />" method="get">
								<input type="submit" type="button" class="btn btn-default"
									value="Write" />
							</form>

						</c:if>
					</c:forEach></td>
				<td><c:forEach items="${writeableTables}" var="table">
						<c:if test="${table.tableName == 'recipie'}">
							<form action="<c:url value="/addRecipie" />" method="get">
								<input type="submit" type="button" class="btn btn-default"
									value="Write" />
							</form>
						</c:if>
					</c:forEach></td>
			</tr>
		</tbody>
	</table>

</body>
</html>