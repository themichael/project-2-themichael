$(document).ready(function() {
	$("#password_mismatch").attr("hidden", true);
	
	$("form").submit(function(e){
		//Check if there is no password mismatch
		if($("#new_password_input").val() != $("#confirm_password_input").val()) {
			e.preventDefault(e);
			$("#password_mismatch").attr("hidden", false);
		}		
	});
});