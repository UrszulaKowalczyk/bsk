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

			<ul class="nav navbar-nav">
				<li><a href="<c:url value="/user" />">User</a></li>
				<li><a href="<c:url value="/tableLabel" />">Table-Label</a></li>
				<li><a href="<c:url value="/recipie" />">Recipie</a></li>
				<li><a href="<c:url value="/recipieIngredient" />">Recipie-Ingredient</a></li>
				<li><a href="<c:url value="/ingredient" />">Ingredient</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a>Logged as: ${user}</a></li>
				<li><a href="<c:url value="/logout" />"><span
						class="glyphicon glyphicon-log-in"></span> Log out</a></li>
			</ul>
		</div>
	</nav>
	
	<c:if test="${denied}">
		<div class="alert alert-danger">
			<center><strong>Permission denied.</strong><a href="#" class="close" data-dismiss="alert" aria-label="close" title="close">×</a></center>
		</div>
	</c:if>
	<c:if test="${error}">
		<div class="alert alert-danger">
			<center><strong>Invalid data format or permission denied.</strong><a href="#" class="close" data-dismiss="alert" aria-label="close" title="close">×</a></center>
		</div>
	</c:if>
	<c:if test="${invalid}">
		<div class="alert alert-danger">
			<center><strong>Invalid data format.</strong><a href="#" class="close" data-dismiss="alert" aria-label="close" title="close">×</a></center>
		</div>
	</c:if>
	

	<c:choose>
		<c:when test="${shownTable=='user'}">
			<jsp:include page="include/user.jsp" />
		</c:when>
		<c:when test="${shownTable=='tableLabel'}">
			<jsp:include page="include/tableLabel.jsp" />
		</c:when>
		<c:when test="${shownTable=='recipie'}">
			<jsp:include page="include/recipie.jsp" />
		</c:when>
		<c:when test="${shownTable=='recipieIngredient'}">
			<jsp:include page="include/recipieIngredient.jsp" />
		</c:when>
		<c:when test="${shownTable=='ingredient'}">
			<jsp:include page="include/ingredient.jsp" />
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>







	<form name="form">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
</body>
</html>