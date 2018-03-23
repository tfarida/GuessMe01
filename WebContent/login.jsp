<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- heading ditails and link to bootstrap frame work and 
other used css files by temesgen on 19th march -->

<title>Guess Me Game!!</title>
<link rel="shortcut icon" href="images/logo2.png"/>
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
<link rel="stylesheet" href="css/logo.css">

</head>
<body class="bg" background="images/bg_img.jpg" repeat-y>

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
				
	<!-- use of custom tags for the logo we use a shadow dome here
	 code by temesgen 21st of march 	 -->		
				
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


	<!--login form -->

	<form class="loginform" action="login" method="post">
		<div class="imgcontainer">
			<img src="images/img_avatar.jpg" alt="Avatar" class="avatar record">
		</div>

		<div class="container record">
			<div>
				<label for="uname"><b>Username</b></label>
			</div>
			<div>
				<input type="text" placeholder="Enter Username"
					name="emailOrGamerName" required>
			</div>
			<div>
				<label for="psw"><b>Password</b></label>
			</div>
			<div>
				<input type="password" placeholder="Enter Password" name="password"
					required>
			</div>
			<br>
			<div>
				<button class="btn btn-success" type="submit">Login</button>
				&nbsp; &nbsp; 
				 <label> <input type="checkbox" checked="checked"
					value="rememberMe" name="remember">&nbsp; Remember me</label>
				<br>
				<br> <a href="register.jsp" class="btn btn-default">Sign Up</a>
			</div>
			<div></div>
			<div></div>
		</div>
	</form>

	<script src="js/jquery-1.11.3.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

</body>
</html>