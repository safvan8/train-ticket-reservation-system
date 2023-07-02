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
		<h1 class="hd">National Ticket Booking Spot</h1>

		<!-- Home Hyperlik -->
		<div class="home">
			<p class="menu">
				<a href="${pageContext.request.contextPath}/admin/home">Home</a>
			</p>
		</div>

		<!-- View All trains Hyperink-->
		<div class="home">
			<p class="menu">
				<a href="${pageContext.request.contextPath}/admin/viewAllTrains">View
					All Trains</a>
			</p>
		</div>

		<!-- Search a train using train Number Hyperlink -->
		<div class="home">
			<p class="menu">
				<a
					href="${pageContext.request.contextPath}/admin/searchTrainByNumberFwd">Search
					Train By Number</a>
			</p>
		</div>

		<!-- Add new Train Hyperlink -->
		<div class="home">
			<p class="menu">
				<a href="${pageContext.request.contextPath}/admin/addTrainFwd">Add
					new Train</a>
			</p>
		</div>

		<!-- Delete a train Hyperlink -->
		<div class="home">
			<p class="menu">
				<a href="${pageContext.request.contextPath}/admin/deleteTrainFwd">Delete
					an Existing Train</a>
			</p>
		</div>

		<!-- Logout Admin Hyperlink -->
		<div class="home">
			<p class="menu">
				<a href="${pageContext.request.contextPath}/admin/updateTrainFwd">Update
					Train Details</a>
			</p>
		</div>

		<!-- Add new Train -->
		<div class="home">
			<p class="menu">
				<a href="${pageContext.request.contextPath}/logout">Logout</a>
			</p>
		</div>
	</header>
		<!--Welcome message for the admin-->
		<div class="tab">
			<p class="menu">Hey, Admin ! Welcome to our new ITRTC Website</p>
		</div>
	
	<div class="main">
		<p class="menu"> Update Train By Number </p>
	</div>

	<div>
		<!-- Search Trains by Number - form-->
		<form
			action="${pageContext.request.contextPath}/admin/updateTrainByNumber"
			class="tab red" method="post">
			TrainNumber: <input type="number" name="trainNo"> <br>
			<input type="submit" value="Find Train Details">
		</form>
	</div>
</body>

</html>