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
			<form action='${pageContext.request.contextPath}/user/proceedTrainBooking' method='post'>
				<table border="2" style="border: thick;">


					<!-- User ID and Train Number -->
					<tr>
						<td>User Name:</td>
						<td>${user.username}</td>
						<td>Train NO:</td>
						<td>${preBookingDetails.trainNo}</td>
					</tr>
					<!-- From Station and To Station -->
					<tr>
						<td>From Station:</td>
						<td>${preBookingDetails.fromStation}</td>
						<td>To Station:</td>
						<td>${preBookingDetails.toStation}</td>
					</tr>

					<!-- Journey Date and Number of Seats -->
					<tr>
						<td>Journey Date:</td>
						<td>
							<!-- passing ,user request data as hidden form fields -->
							<input type='hidden' name='trainNo' value='${preBookingDetails.trainNo}'>
							<input type='hidden' name='fromStation' value='${preBookingDetails.fromStation}'> 
							<input type='hidden' name='toStation' value='${preBookingDetails.toStation}'>  
							<% 
      							String currentDate= java.time.LocalDate.now().toString();
      						%>						
							<input type='date' name='journeyDate' value='<%= currentDate %>'></td>
						<td>No of Seats:</td>
						<td>
							<input type='number' name='seatsRequired' value='1'>
						</td>
					</tr>
					
					<!-- Select Class and Berth Preference -->
					<tr>
						<td>Select Class:</td>
						<td><select name='seatType' required>
								<option value='Sleeper(SL)'>Sleeper(SL)</option>
								<option value='Second Sitting(2S)'>Second Sitting(2S)</option>
								<option value='AC First Class(1A)'>AC First Class(1A)</option>
								<option value='AC 2 Tier(2A)'>AC 2 Tier(2A)</option>
						</select></td>
						<td>Berth Preference:</td>
						<td><select name='berthPreference'>
								<option value='NO'>No Preference</option>
								<option value='LB'>Lower Berth(LB)</option>
								<option value='UB'>Lower Berth(UB)</option>
								<option value='C'>Cabin</option>
						</select></td>
					</tr>
				</table>
		</div>
		<div class='tab'>
			<p1 class='menu'> <!-- Submit button --> <input type='submit'
				value='Pay And Book'> </p1>
		</div>
		</form>

	</section>

</body>

</html>