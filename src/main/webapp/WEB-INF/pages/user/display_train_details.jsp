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
					<a href="${pageContext.request.contextPath}/user/home">Home</a>
				</p>
			</div>

			<!-- View All trains Hyperink-->
			<div class="home">
				<p class="menu">
					<a href="${pageContext.request.contextPath}/user/viewAllTrains">View All Trains</a>
				</p>
			</div>

			<!-- Find Trains between 2 staions Hyperlink -->
			<div class="home">
				<p class="menu">
					<a href="${pageContext.request.contextPath}/user/findTrainsbetweenStaionsFwd">Trains between staions</a>
				</p>
			</div>

			<!-- Diplay train booking History Hyperlink -->
			<div class="home">
				<p class="menu">
					<a href="${pageContext.request.contextPath}/user/showTicketBookingHistory">Ticket Booking History</a>
				</p>
			</div>

			<!-- train fair Enquery Hyperlink -->
			<div class="home">
				<p class="menu">
					<a href="${pageContext.request.contextPath}/user/trainFairEnqueryFwd">Fair Enquery</a>
				</p>
			</div>

			<!-- Check Seats Availability Hyperlink -->
			<div class="home">
				<p class="menu">
					<a href="${pageContext.request.contextPath}/user/trainSeatsAvailablityCheckFwd">Check Seats
						Availability</a>
				</p>
			</div>
			
			<!-- Search a train using train Number Hyperlink -->
            <div class="home">
                <p class="menu">
                    <a href="${pageContext.request.contextPath}/user/searchTrainByNumberFwd">Search Train By Number</a>
                </p>
            </div>

		</header>

	<!-- Displaying the train search result to User -->
	<div class="main">
		<p class="menu">Train Details</p>
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
				<tr>
					<td>${train.trainName}</td>
					<td>${train.trainNo}</td>
					<td>${train.fromStation}</td>
					<td>${train.toStation}</td>
					<td>${train.seats}</td>
					<td>${train.fare}</td>
				</tr>

			</table>
		</div>
	</div>

</body>
</html>
