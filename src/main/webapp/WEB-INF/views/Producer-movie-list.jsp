<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Movies</title>
<style>
table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    text-align: left;
    padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}

th {
    background-color: #4CAF50;
    color: white;
}
</style>
</head>
<body background="C:\Users\Admin\Desktop\Avengers_Logo_Deco_Light_01.jpg">
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}/account/">Producer Home</a><br/>
	<br>

	<form:form action="${contextPath}/advert/cart" method="post" commandName="movie">
	<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<td><b>Product Name</b></td>
			<td><b>Product Description</b></td>
			<td><b>Seller Name</b></td>
			<td><b>Category</b></td>
			<td><b>Image</b></td>
			<td><b>Price</b></td>
			
		</tr>
		<c:forEach var="adv" items="${movies}">
			<tr>
				<td>${adv.title}</td>
				<td>${adv.message}</td>
				<td>${adv.account.username}</td>
				<td><c:forEach var="categ" items="${adv.genres}">
                    	${categ} , 
                    </c:forEach></td>
                <td><img height="150" width="150" src="${adv.filename}" /></td>
                <td>${adv.price}</td>
                
			</tr>
		</c:forEach>
	</table>
	</form:form>
</body>
</html>