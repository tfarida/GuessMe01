<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
<!-- heading ditails and link to bootstrap frame work and 
other used css files by temesgen on 19th march -->

<title>Guess Me!!</title>
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
<script src="logo.js"></script>
<link rel="stylesheet" href="logo.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/movie.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen"
	href="chrome-extension://amngkonmlkfeiikhbohohlokcgeoijoa/css/style.css">

<link rel="stylesheet" href="css/docorate.css">
<script src="javascript/logo.js"></script>
<link rel="stylesheet" href="css/logo.css">


</head>
<body class="bg" background="images/bg_img.jpg">

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
					<li>
						<c:choose> 
						<c:when test="${sessionScope.gamer != null}">
							<a href="welcome.jsp">Home</a>
						</c:when>
						<c:otherwise> 
							<a href="login.jsp">Home</a>
						</c:otherwise>
					</c:choose>
					</li>
					<li class="active"><a href="HowToPlay.jsp">How To play</a></li>
					<li><a href="aboutUs.jsp">About Us</a></li>
					<li>	
						<c:if test="${sessionScope.gamer != null}">
							<a href="logout">Logout</a>
						</c:if>
						
					</li>
				</ul>
			

			</div>
		</div>
	</nav>

	<div class="gridall">
		<h1 id="tmnt">How to PLAY</h1>
		<div class="allitem">
			<div class="nav1">
			<h3>Discover the HIDDEN Code!</h3>
			<br>
				<p id="howToPlay"><strong>Guess Me</strong><br><br>A Computer selects a four
					digit number, all four digits are different. Any number can be
					guessed in 7 tries or less. Once you have entered your number, the
					third column displays "(Matched Digit , Matched Position)" where,
					Matched digit represents total number of digits you guessed right,
					& Matched Position shows how many of those that exists are placed
					at the right spots. PLAY by entering your guess in the text box and
					click 'Check' to validate your entry. You may also click 'New' to
					start a new game.</p>
			</div>
		</div>

	</div>


	<script src="js/jquery-1.11.3.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>