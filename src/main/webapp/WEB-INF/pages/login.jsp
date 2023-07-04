<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		<span class="menu"> <a href="${pageContext.request.contextPath}/appUsers/register"> Register New
				User</a>
		</span>
	</div>

	<%-- Check if logoutMessage is not empty --%>
	<c:if test="${not empty logoutMessage}">
		<h3>${logoutMessage}</h3>
	</c:if>

	<%-- Check if loginFailedMessage is not empty --%>
	<c:if test="${not empty loginFailedMessage}">
		<h3>${loginFailedMessage}</h3>
	</c:if>

	<%-- Check if sessionExpired parameter is present --%>
	<c:if test="${param.sessionExpired eq 'true'}">
	    <h3>Session Expired, Login again</h3>
	</c:if>
	
	

	<div class="tab green">
		<form action="${pageContext.request.contextPath}/login"
			class="tab brown" method="post">
			Username: <input type="text" name="username"> <br>
			Password: <input type="password" name="password"> <br> <input
				type="submit" value="Login">
		</form>
	</div>

</body>

</html>