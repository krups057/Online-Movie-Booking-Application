<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Create Movie Form</title>
</head>
<body background="C:\Users\Admin\Desktop\Avengers_Logo_Deco_Light_01.jpg">
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}/account/">Producer Home</a><br/>

	<h2>New Movie Alert</h2>


	<form:form action="${contextPath}/movie/add" method="post"
		commandName="movie" enctype="multipart/form-data">

		<table>
			<tr>
				<td>Producer Name:</td>
				<td>${sessionScope.account.username}</td>
				<td><form:hidden path="postedBy"
						value="${sessionScope.account.personID}" /></td>
			</tr>

			<tr>
				<td>Select a Genres:</td>
				<td><form:select path="genres" items="${genres}"
						multiple="true" required="required" /></td>
			</tr>

			<tr>
				<td>Movie Name:</td>
				<td><form:input type="text" path="title" size="30" required="required"/>
				<font color="red"><form:errors path="title" /></font></td>
			</tr>

			<tr>
				<td>Movie Image:</td>
				<td><input type="file" name="photo" required="required"/>
				</td>
			</tr>
			
			<tr>
				<td>Image Name:</td>
				<td><form:input type="text" path="filename" required="required"/>
				<font color="red"><form:errors path="filename" /></font></td>
			</tr>
			
			<tr>
				<td>Movie Description:</td>
				<td><form:input type="text" path="message" size="30" required="required"/>
				<font color="red"><form:errors path="message" /></font></td>
			</tr>
			
			<tr>
				<td>Ticket Price:</td>
				<td><form:input type="number" path="price" size="30" required="required"/>
				<font color="red"><form:errors path="price" /></font></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Create Movie" /></td>
			</tr>
		</table>

	</form:form>

</body>
</html>