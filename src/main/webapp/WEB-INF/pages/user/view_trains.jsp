<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
				<a href="#">Home</a>
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
					href="${pageContext.request.contextPath}/user/trainsbetweenStaionsFwd">Trains
					between staions</a>
			</p>
		</div>

		<!-- Diplay train booking History Hyperlink -->
		<div class="home">
			<p class="menu">
				<a
					href="${pageContext.request.contextPath}/user/showBookingHistoryFwd">Ticket
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

		<!-- display user profile Hyperlink -->
		<div class="home">
			<p class="menu">
				<a href="${pageContext.request.contextPath}/user/userProfile">Profile</a>
			</p>
		</div>

		<!-- user Logout Hyperlink -->
		<div class="home">
			<p class="menu">
				<a href="${pageContext.request.contextPath}/user/userLogout">Logout</a>
			</p>
		</div>
	</header>

	<!-- To display all trains details received from DB -->
	<div class="main">
		<p class="menu">All Available running Trains - displaying to user
		</p>
		<div class="tab">
			<table border="1">
				<tr>
					<th>Train Name</th>
					<th>Train No.</th>
					<th>From Station</th>
					<th>To Station</th>
					<th>Seats Available</th>
					<th>Fare (INR)</th>
					<th>Action</th>
				</tr>

				<!-- checking if allTrains List is null or not before displying it -->
				<c:choose>
					<c:when test="${empty allTrains}">
						<tr>
							<td colspan="7">
								<h1>No Running trains available</h1>
							</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="train" items="${allTrains}">
							<tr>
								<td>${train.trainName}</td>
								<td>${train.trainNo}</td>
								<td>${train.fromStation}</td>
								<td>${train.toStation}</td>
								<td>${train.seats}</td>
								<td>${train.fare}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
	</div>

</body>
</html>