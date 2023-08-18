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
		<div class='tab'>
			<p class='menu green'>
				 Seats Booked Successfully!<br />
				<br /> Your Transaction Id is: ${ticketBookingResult.transactionId}
			</p>
		</div>
		<div class='tab'>
			<p class='menu'>
			<table>
				<tr>
					<td>Ticket ID:</td>
					<td colspan='1' style='color: blue;'>${ticketBookingResult.ticketId}</td>
				</tr>

				<tr>
					<td>Train Name:</td>
					<td>${ticketBookingResult.train.trainName}</td>
					<td>Train No:</td>
					<td>${ticketBookingResult.train.trainNo}</td>
				</tr>
				<tr>
					<td>Booked From:</td>
					<td>${ticketBookingResult.train.fromStation}</td>
					<td>To Station:</td>
					<td>${ticketBookingResult.train.toStation}</td>
				</tr>
				<tr>
					<td>Date Of Journey:</td>
					<td>${ticketBookingResult.journeyDate}</td>
					<td>Time(HH:MM):</td>
					<td>11:23</td>
				</tr>
				<tr>
					<td>Passengers:</td>
					<td>${ticketBookingResult.seatsRequired}</td>
					<td>Class:</td>
					<td>${ticketBookingResult.seatType}</td>
				</tr>
				<tr>
					<td>Booking Status:</td>
					<td style='color: green;'>ticketBookingResult.ticketStaus</td>
					<td>Amount Paid:</td>
					<td>&#8377; ${ticketBookingResult.ticketAmount}</td>
				</tr>
			</table>
		</div>
		<h3 style="color: black;text-align: center;">Save Ticket Id to track details</h3>
	</section>
</body>

</html>