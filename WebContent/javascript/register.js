//Written by Titin on - 20st March 2018 

"use strict";
$(function(){
	/**
	 * by titin
	 * to check password and confirmed password matched
	 */
	///$('#btnSubmit').attr('disabled', 'disabled');
	//$('#btnSubmit').removeAttr('disabled');
	$('#password, #confirmPassword').on('keyup', function () {
		  if ($('#password').val() == $('#confirmPassword').val()) {
		    $('#passwordStatus').html('Passwords match.').css('color', 'green');
		    //$('#btnSubmit').attr({disabled:false});
		    $('#btnSubmit').removeAttr('disabled');
		  } else {
			  $('#passwordStatus').html("Passwords don't match!!").css('color', 'red');
			 // $('#btnSubmit').attr('disabled', 'disabled');
			  $("#btnSubmit").attr("disabled", true);
		  }
		    
	});
	
	
	
	
});