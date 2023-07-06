edit_train_details_form<%@ page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
	isELIgnored="false"%>

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
		<h1 class="hd">National Ticket Booking Spot</h1>

		<!-- Home Hyperlik -->
		<div class="home">
			<p class="menu">
				<a href="/">Home</a>
			</p>
		</div>

		<!-- View All trains Hyperink-->
		<div class="home">
			<p class="menu">
				<a href="${pageContext.request.contextPath}/admin/home">View All
					Trains</a>
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

		<!--Welcome message for the admin-->
		<div class="tab">
			<p class="menu">Hey, Admin ! Welcome to our new ITRTC Website</p>
		</div>

	</header>

	<!-- Displaying the train search result to Admin -->
	<div class="main">
		<p class="menu">Train details for update displaying to Admin</p>
		<div class="tab">
			<form action="${pageContext.request.contextPath}/admin/saveOrUpdateTrain"
				method="post">
				<table>
					<tr>
						<h6>
						<th>Train No.:</th>
						<td><input type="number" name="trainNo"
							value="${train.trainNo}" readonly="readonly">
							</td>
						</h6>
					</tr>
					<tr>
						<th>Train Name:</th>
						<td><input type="text" name="trainName"
							value="${train.trainName}"></td>
					</tr>
					<tr>
						<th>From Station:</th>
						<td><input type="text" name="fromStation"
							value="${train.fromStation}"></td>
					</tr>
					<tr>
						<th>To Station:</th>
						<td><input type="text" name="toStation"
							value="${train.toStation}"></td>
					</tr>
					<tr>
						<th>Seats Available:</th>
						<td><input type="number" name="seats" value="${train.seats}"></td>
					</tr>
					<tr>
						<th>Fare (INR):</th>
						<td><input type="number" name="fare" value="${train.fare}"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="Save"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>

</body>
</html>
