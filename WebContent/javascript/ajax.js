// Written by Romie on - 19th March for Game Services calls

var counter = 0;

$(function() {
	// Written by Romie on - 19th March - to check the number
	$(".btnActiveGamer").click(getPlayer); // titin: for first load , maybe need it
	$(document).on('click', ".btnActiveGamer", getPlayer); // titin: after ajax
	// reload and update activeGamers list

	console.log("I am here!");
	$("#playgame").hide();
	$("#tableAdd").hide();
	$("#btnSubmit").click(function() {
		var myGuess = $("#myGuess").val();
		var btnValue = $("#btnSubmit").val();
		// var digitRegex = /(?:([0-9])(?!.*\\1)){4}/;
		// if(btnValue.match(digitRegex)){
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
		/*
		 * } else{ $("<div class='alert alert-warning'>") .html("<strong>Please
		 * enter 4 different digits. </strong>") .appendTo("#winner");
		 * $("#btnSubmit").attr("disabled",false); }
		 */
	});

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

		if (counter > 6) {
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
	// Written by Romie on - 22nd March - To reset
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


	$("#btnNew").click(function() {
		$("#activeGamers").show();
		
		$(".tableAdd").hide();
		$(".toBeAdd").hide();
		$("#winner").hide();
	});

	
	// Retrieve Opponent's details
	// console.log("Ready to retrieve ");
	function getPlayer() {
		// alert("HAI: "+this.id);
		// console.log("Ready to retrieve ");
		var opponentId = this.id; // titin: get gamerId
		// //$("#opponentId").val();
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

	// Start play a game
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
			// "data": {"city":$("#city").val()},
			"type" : "GET",
			"success" : showResult,
			"error" : ajaxFailure
		});
	}
	
	
	
	function showResult(data) {
		// alert ('HAI SUCCESS');
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
		// alert('fail' + xhr);
	}

	//End titin
});