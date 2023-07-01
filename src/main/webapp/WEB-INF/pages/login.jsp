<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action=""${pageContext.request.contextPath}/login"" method="post">
		<br /> UserName: <input type="text" name="username"
			placeholder="Enter Your Username"> <br /> <br /> 
			Password:<input type="password" name="password"> <br /> <br /> 
			<input type="submit" value=" LOGIN "><br />

	</form>

</body>
</html>