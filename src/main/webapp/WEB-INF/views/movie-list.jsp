<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Movies List</title>
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

	<a href="${contextPath}/"><input type="submit" value="Logout"></a><br/><br>
	<br>

	
	<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<td><b>Movie Name</b></td>
			<td><b>Movie Description</b></td>
			<td><b>Producer Name</b></td>
			<td><b>Genre</b></td>
			<td><b>Image</b></td>
			<td><b>Price</b></td>
			<td><b>Options</b></td>
			
		</tr>
		
		<c:forEach var="mov" items="${movies}">
		<form:form action="${contextPath}/cart/insert" method="post" commandName="cart">
			<form:hidden path="id" value="${mov.id}"/>
		<form:hidden path="title" value="${mov.title}"/>
		<form:hidden path="totalprice" value="${mov.price}"/>
		<form:hidden path="genre" value="${mov.genres}"/>
		<form:hidden path="filename" value="${mov.filename}"/>
			<tr>
			
				<td>${mov.title}</td>
				<td>${mov.message}</td>
				<td>${mov.account.username}</td>
				<td><c:forEach var="categ" items="${mov.genres}">
                    	${categ} , 
                    </c:forEach></td>
                <td><img height="150" width="150" src="${mov.filename}" /></td>
                <td>${mov.price}</td>
   
                <td><input type="submit" value="Add to Cart" /></td>
               
			</tr>
			 </form:form>
		</c:forEach>
	</table>
	
</body>
</html>