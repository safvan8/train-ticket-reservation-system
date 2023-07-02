<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/UserHome_Css.css">

</head>

<body>

	<h1>National Ticket Booking Spot</h1>

	<div class="main">
		<span class="menu"> <a href="userlogin.html"> Register New
				User</a>
		</span>
	</div>
	
	<h3>${message}</h3>
	
	<div class="tab green">

		<form action="${pageContext.request.contextPath}/login"
			class="tab brown" method="post">
			Username : <input type="text" name="username"> <br>
			Passsword : <input type="password" name="password"> <br>
			<input type="submit" value="Login">
		</form>

	</div>

</body>

</html>