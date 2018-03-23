<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="ct"%>
<html lang="en">
<head>
<title>Guess Me</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="docorate.css">
<script src="javascript/ajax.js"></script>

<!-- heading ditails and link to bootstrap frame work and 
other used css files by temesgen on 19th march 2018-->
<!-- Updated by team (Titin/Romie/Temesgan) during project implementation -->

</head>
<body class="bg" background='images/img3.jpg'>

	<nav class="navbar navbar-inverse">
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
					<li class="active"><a href="welcome.jsp">Home</a></li>
					<li><a href="HowToPlay.jsp">How To play</a></li>
					<li><a href="aboutUs.jsp">About Us</a></li>
					<li><a href="logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container text-center">
		<div class="row">
			<div class="col-sm-2 well" id="leftside">
				<div class="well">
					<p>
						<a href="#">${gamer.fullName}</a>
					</p>
				</div>
				<div class="well"></div>
				<p>
					<a href="#">Game 1</a>
				</p>
				<p>
					<a href="#">Game 2</a>
				</p>
				<p>
					<a href="#">Game 3</a>
				</p>
			</div>
			<div class="col-sm-7" id="centerSide">
				<div class="row">
					<div class="col-sm-12">
						<div class="panel panel-default text-left">
							<div class="panel-body">
								<p style="color: green; font-weight: bold">My Profile</p>

								<p>Gamer Name: ${gamer.gamerName}</p>
								<p>Email Address: ${gamer.emailAddress}</p>

								<form method="POST" action="UpdateProfile">
									<p>
										Full Name: <input type="text" name="fullName"
											value="${gamer.fullName}" />
									</p>
									<p>
										About Me:
										<textarea name="aboutMe" placeholder="Write something.."
											style="height: 200px">${gamer.aboutMe}</textarea>
									</p>
									<p>
										My Secret Number: <input type="text" name="mySN"
											value="${gamer.secretNumber}" maxlength="4"
											placeholder="Enter 4 unique digits"
											pattern="^(?:([0-9])(?!.*\\1)){4}$" />
									</p>
									<p>
										About My Secret Number:
										<textarea name="aboutMySN" placeholder="Write something.."
											style="height: 200px">${gamer.aboutSecretNumber}</textarea>
									<p>
										<input type="submit" value="Update Profile" />
									</p>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-3 well" id="rightSide">
				<div class="thumbnail">
					<div>Tournament</div>
					<br>
				</div>
				<div class="thumbnail">
					<div>Your Best Moves</div>
					<br>
				</div>

			</div>
		</div>
	</div>

</body>
</html>