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
		  }
		    
	});
	
	/*$('input[type=submit]').on('click', validate);
	function validate() {
	  var password1 = $("#password").val();
	  var password2 = $("#confirmPassword").val();

	    if(password1 == password2) {
	       $("#passwordStatus").text("Matching");        
	    }
	    else {
	        $("#passwordStatus").text("Not Matching");  
	    } 
	}*/
	
	
});