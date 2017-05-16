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
	<h2>Dodaj przepis:</h2>

	<form action="<c:url value="/addRecipie" />" method="post">
		<div class="container">
			<div class="row">
				<div class="col-xs-6">
					<div class="form-group">
						<label for="title">Nazwa: </label> <input type="text" name="title"
							id="title" class="form-control">
					</div>
					<div class="form-group">
						<label for="description">Opis:</label> <input type="text"
							class="form-control" name="description" id="description">
					</div>
					<c:if test="${ingredientWriteAccess == true }">
						<div class="form-group multiple-form-group" data-max=10>
							<label>Składniki własne (maksymalnie 10)</label>
							<div class="form-group input-group">
								<input type="text" name="multiple[]" class="form-control">
								<span class="input-group-btn"><button type="button"
										class="btn btn-default btn-add">+</button></span>
							</div>
						</div>
					</c:if>
					<c:if test="${ingredientReadAccess == true }">
					<div class="form-group multiple-form-group">
						<label>Składniki</label>
						<div class="form-group input-group">
							<select name="multiple[]" class="form-control">
								<c:forEach items="${ingredients}" var="ingredient">
									<option value="${ingredient.name}">${ingredient.name}</option>
								</c:forEach>
							</select> <span class="input-group-btn"><button type="button"
									class="btn btn-default btn-add">+</button></span>
						</div>
					</div>
					</c:if>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <input type="submit"
						value="Dodaj przepis">
				</div>
			</div>
		</div>

	</form>

</body>
</html>