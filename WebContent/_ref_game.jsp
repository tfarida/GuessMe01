<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="javascript/ajax.js"></script>
	
<title>Guess Me</title>

</head>
<body bgcolor="#eeeeee">
 	<div class="container">
 		 <h2 >Guess Me </h2><br>
 			
 			<div id="winner"></div>
 		 	Start guessing your friend No.
		 	<div class="panel panel-primary">
		 	
				<input type="text" min="0" 
							step="1" 
							name="myGuess" 
							maxlength="4" 
							class="form-control" 
							id="myGuess"
							placeholder="Enter 4 digits" 
							pattern = "^([\d])((?!\1)[\d])(?!\2)((?!\1)[\d])(?!\3)(?!\2)((?!\1)[\d])$"
							required
			    /> 
			 </div><br>
			<button
                    type="submit"
                    id="btnSubmit"
                    class="btn btn-primary">
                    Submit
                </button>			
		  &nbsp;
			<button
                    type="Reset"
                    id="btnReset"
                    class="btn btn-primary">
                    Reset
                </button>
            <br><br>
		<br> <div  class="form-group" id="dataDiv"></div>
		
		<div class="panel panel-primary">
		 	
				<input type="text" min="0" 
							step="1" 
							name="opponentId" 
							maxlength="4" 
							class="form-control" 
							id="opponentId"
							placeholder="Enter opponent's Id" 
							required="required"
			    /> 
			 </div><br>
			<button
                    type="submit"
                    id="btnRetrieve"
                    class="btn btn-primary">
                    Retrieve
                </button> <br><br>	
			
			<div id="p2"></div>
			
			<div class="panel panel-primary">
		 	
				<input type="text" min="0" 
							step="1" 
							name="secretNo" 
							maxlength="4" 
							class="form-control" 
							id="secretNo"
							placeholder="Choose your 4 digits secret No." 
							required="required"
			    /> 
			 </div><br><br>
			<button
                    type="submit"
                    id="btnPlay"
                    class="btn btn-primary">
                    Play
            </button>
            <div id="p1space"></div>    	
	</div>
</body>
</html>