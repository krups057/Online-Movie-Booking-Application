<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
	
	<form:form action="${contextPath}/cart/checkout" method="post" commandName="cart">
	

	<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<td><b>Movie Name</b></td>
			<td><b>Message</b></td>
			<td><b>Image</b></td>
			<td><b>Price</b></td>
			
			
		</tr>
		<c:forEach var="c" items="${c}">
			<tr>
				<td>${c.title}</td>
				<td><c:forEach var="categ" items="${c.message}">
                    	${categ} , 
                    </c:forEach>
                </td>
                <td><img height="150" width="150" src="${c.filename}" /></td>
                <td>${c.price}</td>
                
			</tr>
		</c:forEach>
	</table>
	<input type="submit" value="Checkout Items" />
	</form:form>
</body>
</html>