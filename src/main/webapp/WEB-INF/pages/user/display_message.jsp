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
	<header>
		<h1 class="hd">National Ticket Booking Spot UH</h1>

		<!-- Home Hyperlik -->
		<div class="home">
			<p class="menu">
				<a href="${pageContext.request.contextPath}/user/home">Home</a>
			</p>
		</div>

		<!-- View All trains Hyperink-->
		<div class="home">
			<p class="menu">
				<a href="${pageContext.request.contextPath}/user/viewAllTrains">View
					All Trains</a>
			</p>
		</div>

		<!-- Find Trains between 2 staions Hyperlink -->
		<div class="home">
			<p class="menu">
				<a
					href="${pageContext.request.contextPath}/user/findTrainsbetweenStaionsFwd">Trains
					between staions</a>
			</p>
		</div>

		<!-- Diplay train booking History Hyperlink -->
		<div class="home">
			<p class="menu">
				<a
					href="${pageContext.request.contextPath}/user/showTicketBookingHistory">Ticket
					Booking History</a>
			</p>
		</div>

		<!-- train fair Enquery Hyperlink -->
		<div class="home">
			<p class="menu">
				<a
					href="${pageContext.request.contextPath}/user/trainFairEnqueryFwd">Fair
					Enquery</a>
			</p>
		</div>

		<!-- Check Seats Availability Hyperlink -->
		<div class="home">
			<p class="menu">
				<a
					href="${pageContext.request.contextPath}/user/trainSeatsAvailablityCheckFwd">Check
					Seats Availability</a>
			</p>
		</div>

		<!-- Search a train using train Number Hyperlink -->
		<div class="home">
			<p class="menu">
				<a
					href="${pageContext.request.contextPath}/user/searchTrainByNumberFwd">Search
					Train By Number</a>
			</p>
		</div>

		<!-- display user profile Hyperlink -->
		<div class="home">
			<p class="menu">
				<a href="${pageContext.request.contextPath}/userProfile/view">Profile</a>
			</p>
		</div>

		<!-- user Logout Hyperlink -->
		<div class="home">
			<p class="menu">
				<a href="${pageContext.request.contextPath}/logout">Logout</a>
			</p>
		</div>

	</header>
	<section>
		<div>
			<h1 class="hd">${message}</h1>
		</div>
	</section>
</body>
</html>