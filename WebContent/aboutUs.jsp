<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
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
<link rel="stylesheet" href="css/movie.css">

</head>
<body class="bg" background="images/bg_img.jpg">

<!-- heading ditails and link to bootstrap frame work and 
other used css files by temesgen on 19th march -->
<!--navigation menu bar by temesgen on first day-->
<!-- Updated by team (Titin/Romie/Teme) during project implementation -->

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
					<li><a href="HowToPlay.jsp">How To play</a></li>
					<li class="active"><a href="aboutUs.jsp">About Us</a></li>
					<li>
						<c:if test="${sessionScope.gamer != null}">
							<a href="logout">Logout</a>
						</c:if>
					</li>
				</ul>

					
			</div>
		</div>
	</nav>
	
	<!-- blinked guess me by temesgen on 19th march  --> 
	
	<div class="blink">
		<img src="images/logo2.png">
	</div>

	<div class="aboutus">
		<h1 class="tmnt">About Us</h1>
		<br>
		<br> <img id="center" class="imageaboutus"
			src="images/aboutus1.jpeg" width="450" height="350" alt="">
		<script src="js/jquery-1.11.3.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</div>
</body>
</html>