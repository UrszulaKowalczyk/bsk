<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
      <jsp:include page="include/head.jsp"/>
  </head>

  <body>

    <div class="container" style = "width: 400px;">

        <form class="form-signin" action="<c:url value="/login"/>" method="post">
                <h2 style="margin-top: 0px;" class="form-signin-heading">Sign in</h2>
                <label for="username" class="sr-only">Name</label>
                <input type="text" name="username" id="username" class="form-control" placeholder="Email" required
                       autofocus>
                <label for="password" class="sr-only">Password</label>
                <input type="password" name="password" id="password" class="form-control" placeholder="Password" required>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        </form>

    </div> <!-- /container -->

  </body>
</html>
