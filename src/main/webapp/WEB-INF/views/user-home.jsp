<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Home</title>
</head>
<body background="C:\Users\Admin\Desktop\Avengers_Logo_Deco_Light_01.jpg">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<form:form action="${contextPath}/movie/logout" method="post" commandName="movie">
<input type="submit" value="Logout">
</form:form>

<h1>Welcome Producer, ${account.firstName} </h1>

<a href="${contextPath}/genre/add" >Add a Movie Genre</a> <br />
<a href="${contextPath}/movie/add" >Add the Movie Description</a> <br />
<a href="${contextPath}/movie/customerlist" >View All Movies</a> <br />


</body>
</html>