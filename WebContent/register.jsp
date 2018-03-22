<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	
<!-- heading ditails and link to bootstrap frame work and 
other used css files by temesgen on 19th march -->


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>Guess Me::Register</title>
<link rel="shortcut icon" href="images/logo2.png"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Guess Me::Register</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/docorate.css">
<script src="javascript/logo.js"></script>
<script src="javascript/register.js"></script>
<link rel="stylesheet" href="css/logo.css">

</head>
<body class="bg" background="images/bg_img.jpg" >

	<nav class="navbar navbar-inverse headerTop">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>

			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><x-logo data-name="" data-img="images/logo2.png"
							data-url="HowToPlay.jsp"></x-logo></li>
					<li class="active"><a href="login.jsp">Home</a></li>
					<li><a href="HowToPlay.jsp">How To play</a></li>
					<li><a href="aboutUs.jsp">About Us</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="blink">
		<img src="images/logo2.png">
	</div>


	<!--register form -->

	<form class="registerform" id="form" method="POST" action="register">
		
			
		<div class="container record">
			<p style="color: yellow">${successMessage}</p>
			<p style="color: red">${errorMessage}</p>

			<div>
				<label for="userName"><b>Username</b></label>
			</div>
			<div>
				<input type="text" placeholder="Enter username" name="userName"
					required="required">
			</div>

			<div>
				<label for="email"><b>Email</b></label>
			</div>
			<div>
				<input type="text" placeholder="Enter e-mail address" name="email"
					required="required">
			</div>

			<div>
				<label for="password"><b>Password</b></label>
			</div>
			<div>
				<input type="password" placeholder="Enter password" name="password"
					id="password" required="required">
			</div>
			<div>
				<label for="confirmPassword"><b>Confirm Password</b></label>
			</div>
			<div>
				<input type="password" placeholder="Re-enter password"
					name="confirmPassword" id="confirmPassword" required="required">
			</div>
			<div>
			<p>
				<span id='passwordStatus'></span>
			</p>
			</div>
			<div>
				<label for="fullName"><b>Full Name</b></label>
			</div>
			<div>
				<input type="text" placeholder="Enter full name" name="fullName"
					required>
			</div>

			<div>
				<label for="gender"><b>Gender</b></label>
			
				<input type="radio" name="gender" value="M" required><label
					for="gender">&nbsp;&nbsp;<b>male</b></label>
			
				<input type="radio" name="gender" value="F" required><label
					for="gender">&nbsp;&nbsp;<b>female</b></label>
			</div>
			<div>
				<button  class="btn btn-success" id="btnSubmit" type="submit">Register</button>
			</div>
		</div>
		
		
	</form>
</div>
	<script src="js/jquery-1.11.3.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>