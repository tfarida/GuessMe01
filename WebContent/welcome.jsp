<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="ct"%>
<html lang="en">
<head>
<!-- heading ditails and link to bootstrap frame work and 
other used css files by temesgen on 19th march -->

<title>Guess Me::Welcome</title>
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
<link rel="stylesheet" href="css/logo.css">
<script src="javascript/ajax.js"></script>

</head>
<body class="bg" background="images/bg_img.jpg">

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
					<li><x-logo data-name="" data-img="images/logo2.png"
							data-url="HowToPlay.jsp"></x-logo></li>
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
						<a href="updateProfile.jsp">${gamer.fullName}</a>
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
					<a href="#"></a>
				</p>
			</div>
			<div class="col-sm-7" id="centerSide">
				<div class="row">
					<div class="col-sm-12">
						<div class="panel panel-default text-left">
							<div class="panel-body">
								<p class="instructions">Try different codes and use your brain to figure it out,
								before all your moves are up.</p>
							</div>
						</div>
					</div>
				</div>
				<div class="row" id="activeGamers">
					<ct:if test="${onlineGamers != undefined}">
						<label>Here are your Online Friends! Play with </label>
					</ct:if>
					<ct:forEach var="onlineGamer" items="${onlineGamers}">
						<div class="col-sm-12">
							<div class="col-sm-2">
								<input type="hidden" min="0" step="1" name="opponentId"
									maxlength="4" class="form-control" id="opponentId"
									placeholder="Enter opponent's Id" required="required"
									value="${onlineGamer.gamer.id}" />
								<button type="submit" id="${onlineGamer.gamer.id}"
									class="btn btn-default btnActiveGamer">
									${onlineGamer.gamer.gamerName}</button>
							</div>
							<div class="col-sm-10">
								<div class="well"></div>
							</div>
						</div>

					</ct:forEach>
				</div>

				<div class="col-sm-12 right" id="playgame">
					<div class="row">
						<div class="col-sm-12rightofRight">
							<div id="winner">
								Your secret No is : <span id="yourSN"></span>
								<input type="hidden" id ="secretHidden"/>
							</div>
							
							<div class="well toBeAdd">
								<input type="text" min="0" step="1" name="myGuess" maxlength="4"
									class="form-control" id="myGuess" placeholder="Enter 4 digits"
									pattern="/^(?:([0-9])(?!.*\\1)){4}$/" required />
								<button type="submit" id="btnSubmit" class="btn btn-default">
									Check</button>
								&nbsp;
								<button type="Reset" id="btnReset" class="btn btn-default">
									Reset</button>
								&nbsp;
								
									<button type="submit" id="btnNew" class="btn btn-default">
									New</button>
								
								
								<br><br><br>
								<table class="tableAdd table table-responsive" width="100%" >
									<tr>
										<th>#</th>
										<th width="40%">Your guessing Number</th>
										<th width="50%" align="right">(Matched Digit , Matched Position)</th>
									</tr>
								</table>
							</div>
						</div>
						
					</div>
				</div>

			</div>
			<div class="col-sm-3 well" id="rightSide">
				<div class="thumbnail">
					<div><a href="" id="getHistory">Your Best Moves</a></div>
					<br>
				</div>

			</div>
		</div>
	</div>
	<!--
 <video class="fullscreen" autoplay loop>
  <source src="images/welcome.mp4" type="video/mp4">
</video>
  -->
</body>
</html>