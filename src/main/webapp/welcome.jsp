
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Krupa Cinemas</title>
</head>
<body background="C:\Users\Admin\Desktop\Avengers_Logo_Deco_Light_01.jpg">
	<h1>KRUPA CINEMAS</h1>
		
		<h2>Already a user?LOGIN</h2>
		<h2>Not Yet? Sign-up</h2>
	<a href="account/register.htm"><input type="submit" value="Sign Up"></a><br/><br><br>

	<h2>Login</h2>
	<form action="account/login" method="post">
	
		<table>
		
		<tr>
		    <td>User Name:</td>
		    <td><input name="username" size="30" required="required" /></td>
		</tr>
		
		<tr>
		    <td>Password:</td>
		    <td><input type="password" name="password" size="30" required="required"/></td>
		</tr>
		
		<tr>
		    <td colspan="2"><input type="submit" value="Login" /></td>
		</tr>
				
		</table>

	</form>


</body>
</html>

