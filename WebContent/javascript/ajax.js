// Written by Romie on - 19th March 2018 for Game Services calls

"use strict";
var counter = 0;				//A counter to check how many moves done by user.

$(function() {
	
	// Written by Romie on - 19th March 2018
	// Updated by Titin to load online users every 2 seconds. 
	$(".btnActiveGamer").click(getPlayer); 						// titin: for first load , maybe need it
	$(document).on('click', ".btnActiveGamer", getPlayer); 		// titin: after ajax  reload and update activeGamers list
	$("#playgame").hide();
	$("#tableAdd").hide();
	
	// Pass the user chosen number and action to Servlet
	$("#btnSubmit").click(function() {
		var myGuess = $("#myGuess").val();
		var btnValue = $("#btnSubmit").val();
		$.ajax({
			url : "GameSolver",
			data : {
				'myGuess' : myGuess,
				'action' : 'checker'
			},
			dataType : "json",
			type : "POST",
			success : winner,
			fail : looser
		})
	});

	// Written by Romie on - 19th March - to check the number
	function winner(response) {
		console.log(response);
		if (response != undefined) {
			$(".tableAdd").show();
			counter++;
			console.log("counter " + counter);

		}
		if (counter % 2 == 0) {
			$("<tr class='table-success'>").html(
					"<td class='table-success'>" + counter + "</td>" + "<td>" + response.number
							+ "</td>" + "<td>(" + response.match[0] + ","
							+ response.match[1] + ")</td></tr>").appendTo(
					".tableAdd");
		} else {
			$("<tr>").html(
					"<td>" + counter + "</td>" + "<td>" + response.number
							+ "</td>" + "<td>(" + response.match[0] + ","
							+ response.match[1] + ")</td></tr>").appendTo(
					".tableAdd");
		}

		if ((response.match[0] == 4) && (response.match[1] == 4)) {
			$("<div class='alert alert-success'>").html(
					"<strong>Congratulations ! You Won!</strong>").appendTo(
					"#winner");
			$("#btnSubmit").attr("disabled", true);
			$("#dataDiv").show();
			$("#winner").show();
			$("#yourSN").html("<b>"+$("#secretHidden").val()+"</b>");
		}

		if (counter > 10) {
			$("<div class='alert alert-danger'>")
					.html(
							"<strong>Sorry, you have reached the maximum attempt. You Fail ! </strong>")
					.appendTo("#winner");
			$("#btnSubmit").attr("disabled", true);
			$("#winner").show();
			$("#yourSN").html("<b>"+$("#secretHidden").val()+"</b>");
		}
	}

	function looser() {
		$("<div class='alert alert-warning'>").html(
				"<strong>Error ! Please try again later. </strong>").appendTo(
				"#winner");
		$("#btnSubmit").attr("disabled", false);
		$("#yourSN").html("<b>"+$("#secretHidden").val()+"</b>");
	}
	
	
	// Written by Romie on - 22nd March - To control reset button
	$('#btnReset').on('click', function() {
		 $('input[type=text]').each(function(){
		     $(this).val('');
		  });
		 $(".tableAdd").hide();
		 $("#activeGamers").hide();
			$("#btnSubmit").attr("disabled", false);
			$("#winner").empty();
			counter = 0;
	});
	
	// Written By Romie on 20th March - for new button click
	$("#btnNew").click(function() {
		$("#activeGamers").show();
		$(".tableAdd").hide();
		$(".toBeAdd").hide();
		$("#winner").hide();
	});

	
	// // Written By Romie on 20th March - retrive opponents details
	console.log("Ready to retrieve ");
	function getPlayer() {
		var opponentId = this.id; 					// titin: get gamerId
		console.log("opponentId = " + opponentId);
		$.ajax({
			url : "GameSolver",
			data : {
				'opponentId' : opponentId,
				'action' : 'getMyOpponent'
			},
			dataType : 'text',
			type : "POST",
			success : retrieveOpponent,
			fail : DBError
		})

		var opponentId = $("#opponentId").val();

	}
	
	function retrieveOpponent(opponentDetails) {
		var data = JSON.parse(opponentDetails);
		console.log(data);
		console.log("In ajax get Opponents");
		$("<div>").html(
				"<table class='table'>" + "<tr class='info'>" + "<td>"
						+ data.players[1].gamer.gamerName + "</td>" + "<td>"
						+ data.players[1].gamer.emailAddress + "</td></tr>")
				.appendTo("#p2");
		$("#secretHidden").val(data.players[1].pickedNumber);
		$("#activeGamers").hide();
		$("#playgame").show();
		$(".tableAdd").hide();
		$("#winner").hide();
	}

	function DBError() {
		console.log("Error");
	}

	// Written By Romie on 21th March - to start to play a game
	$("#btnPlay").click(function() {
		var secretNo = $("#secretNo").val();
		$.ajax({
			url : "GameSolver",
			data : {
				"secretNo" : secretNo,
				"action" : "keepMeSecret"
			},
			datatType : "text",
			success : startPlay,
			fail : gameError
		});
	})

	function startPlay(data) {
		console.log(data);
		$("<div>").html("Your secret No is : " + data).appendTo("#p1space");
	}

	function gameError() {
		console.log("Error");
	}
	

	// Start titin
	// Written by Titin - Date 21 March 2018
	var timerCheckOnlineUser = setInterval(checkOnlineUser, 2000);
	function checkOnlineUser() {
		console.log("We are here....");
		$.ajax({
			"url" : "welcome",
			"type" : "GET",
			"success" : showResult,
			"error" : ajaxFailure
		});
	}
	
	function showResult(data) {
		console.log(data);
		$("#activeGamers").html('');
		$
			.each(
				data,
				function(index, value) {
				console.log("" + data[index].gamer.id);
				$(
				"<div class='col-sm-12'> "
						+ " <div class='col-sm-2'> "
						+ " <input type='hidden' min='0' step='1' name='opponentId' "
						+ " maxlength='4' class='form-control' id='opponentId' "
						+ " placeholder='Enter opponent's Id' required='required' "
						+ " value='"
						+ data[index].gamer.id
						+ "' /> "
						+ " <button type='submit' id='"
						+ data[index].gamer.id
						+ "' class='btn btn-default btnActiveGamer'> "
						+ " " + data[index].gamer.gamerName
						+ "</button> " +

						" </div> "
						+ " <div class='col-sm-10'> "
						+ "<div class=well></div>"
						+ " </div> " + " </div>").appendTo(
				"#activeGamers")

				});
	}
	
	function ajaxFailure(xhr, status, exception) {
		console.log(xhr, status, exception);
	}

	//End titin
});