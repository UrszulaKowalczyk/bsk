<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="include/head.jsp" />
</head>

<body>

	<h2>Add recipie:</h2>
	<form action="<c:url value="/addRecipie" />" method="post" >
		Title: 
		<br> <input type="text" name="title" placeholder="title"> <br> 
		Description:
		<br> <input type="text" name="description" placeholder="description"> <br> 
		Label:
		<br> 
		<select name="label">
			<c:forEach items="${recipies}" var="label">
				<option value="${label.value}">${label.value}</option>
			</c:forEach>
		</select>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<br> <input type="submit" value="Submit">
	</form>

</body>
</html>
