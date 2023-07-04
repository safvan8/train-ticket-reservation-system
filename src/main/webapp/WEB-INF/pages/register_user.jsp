<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:400,700">
<title>New User Registration Form</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/registrationform_style.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/UserHome_Css.css">


</head>
<body>

	<!-- submitting the form with the proper enctype attribute set to "multipart/form-data".
	 This attribute is required to handle file uploads in HTML forms. 
	 By setting the enctype to "multipart/form-data", 
	 the form data will be encoded as multipart data, 
	 allowing file uploads to be processed correctly by your Spring controller-->
	
	<div class="tab green">
		<div class="signup-form">
			<form
				action="${pageContext.request.contextPath}/appUsers/confirmRegistration/customer"
				method="post" enctype="multipart/form-data">
				<h2>Register</h2>
				<p class="hint-text">Create your account. It's free and only
					takes a minute.</p>
				<div class="form-group">
					<div class="row">
						<div class="col">
							<input type="text" class="form-control" name="firstName"
								placeholder="First Name" required="required">
						</div>
						<div class="col">
							<input type="text" class="form-control" name="lastName"
								placeholder="Last Name" required="required">
						</div>
					</div>
				</div>
				<div class="form-group">
					<input type="email" class="form-control" name="username"
						placeholder="user@gmail.com" required="required">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" name="password"
						placeholder="Password" required="required">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" name="confirm_password"
						placeholder="Confirm Password" required="required">
				</div>

				<div class="form-group">
					<input type="text" class="form-control" name="address"
						placeholder="Address " required="required">
				</div>

				<div class="form-group">
					<input type="tel" class="form-control" name="phoneNumber"
						placeholder="Ph: +91" required="required">
				</div>

				<div class="form-group">
					<input type="file" class="form-control" name="image"
						placeholder="Upload Profile picture">
				</div>

				<div class="form-group">
					<label class="form-check-label"><input type="checkbox"
						required="required"> I accept the <a href="#">Terms of
							Use</a> &amp; <a href="#">Privacy Policy</a></label>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-success btn-lg btn-block">Register
						Now</button>
				</div>
			</form>
			<div class="text-center">
				Already have an account? <a href="#">Sign in</a>
			</div>
		</div>
	</div>
</body>
</html>