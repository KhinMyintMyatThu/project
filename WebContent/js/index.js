$(document).ready(function(){
	$(document).on("change",'#dob_month', '#dob_year',function(e) {
		e.preventDefault();
		var year = $('#dob_year').val();
		var month = $('#dob_month').val();
		if ((year != 0) &&  (month!=0)) {
			var lastday = 32 - new Date(year, month - 1, 32).getDate();
			var selected_day = $('#dob_day').val();

			if (selected_day > lastday) {
				$('#dob_day  > option[value=' + selected_day + ']').attr('selected', false);
				$('#dob_day  > option[value=' + lastday + ']').attr('selected', true);
			}

			for (var i = lastday + 1; i < 32; i++) {
				$('#dob_day  > option[value=' + i + ']').remove();	
			}

			for (var i = 29; i < lastday + 1; i++) {
				if (!$('#dob_day  > option[value=' + i + ']').length) {
					$('#dob_day').append($("<option></option>").attr("value",i).text(i));
				} 
			}
		}
	});
	
	$("#login_form").validate({
		rules:{
			email: {
		        required: true,
		        email: true
		     },
		     password:{
		    	 required: true
		     }
		    
		},
		messages:{
			email: "Invalid Email",
			password: "Please type password"
		},
		 errorElement : 'div',
		 errorLabelContainer: '.errorTxt',
	});
	$("#reg_form").validate({
		
	    rules: {
	      firstname: "required",
	      lastname: "required",
	      email: {
	        required: true,
	        email: true
	      },
	      password: {
	        required: true,
	        minlength: 5
	      },
	      confirmedpassword:{
	    	  required: true,
	    	  equalTo:"#password"
	      }
	    },
	    messages: {
	      firstname: "Please enter your firstname",
	      lastname: "Please enter your lastname",
	      password: {
	        required: "Please provide a password",
	        minlength: "Your password must be at least 5 characters long"
	      },
	      email:"Please enter a valid email address",
	      confirmedpassword:{
		    	  required: "Please confirm password",
		    	  equalTo:"Password does not match"
		      }
		      
	    },
	    errorElement : 'div',
	    errorLabelContainer: '.errorTxt',
	    // Make sure the form is submitted to the destination defined
	    // in the "action" attribute of the form when valid
	    submitHandler: function(form) {
	      form.submit();
	    }
	  });
	
	 
});