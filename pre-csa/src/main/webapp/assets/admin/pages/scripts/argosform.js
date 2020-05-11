var ArgosForm = new function(){
	return {
		init: function(options){
			jQuery.extend(jQuery.validator.messages, {
				  required: "Este campo es obligatorio.",
				  remote: "Por favor, rellena este campo.",
				  email: "Por favor, escribe una dirección de correo válida",
				  url: "Por favor, escribe una URL válida.",
				  date: "Por favor, escribe una fecha válida.",
				  dateISO: "Por favor, escribe una fecha (ISO) válida.",
				  number: "Por favor, escribe un número entero válido.",
				  digits: "Por favor, escribe sólo dígitos.",
				  creditcard: "Por favor, escribe un número de tarjeta válido.",
				  equalTo: "Por favor, escribe el mismo valor de nuevo.",
				  accept: "Por favor, escribe un valor con una extensión aceptada.",
				  maxlength: jQuery.validator.format("Por favor, no escribas más de {0} caracteres."),
				  minlength: jQuery.validator.format("Por favor, no escribas menos de {0} caracteres."),
				  rangelength: jQuery.validator.format("Por favor, escribe un valor entre {0} y {1} caracteres."),
				  range: jQuery.validator.format("Por favor, escribe un valor entre {0} y {1}."),
				  max: jQuery.validator.format("Por favor, escribe un valor menor o igual a {0}."),
				  min: jQuery.validator.format("Por favor, escribe un valor mayor o igual a {0}.")
				});
		},
		
		validate: function(form, successElement, errorElement, options){
			
			form.validate({
			    errorElement: 'span', //default input error message container
			    errorClass: 'help-block help-block-error', // default input error message class
			    focusInvalid: false, // do not focus the last invalid input
			    ignore: "",  // validate all fields including form hidden input
			    messages: options.messages,
			    rules: options.rules,

			    invalidHandler: function (event, validator) { //display error alert on form submit              
			        successElement.hide();
			        errorElement.show();
			        Metronic.scrollTo(errorElement, -200);
			    },

			    highlight: function (element) { // hightlight error inputs
			        $(element)
			            .closest('.form-group').addClass('has-error'); // set error class to the control group
			    },

			    unhighlight: function (element) { // revert the change done by hightlight
			        $(element)
			            .closest('.form-group').removeClass('has-error'); // set error class to the control group
			    },

			    success: function (label) {
			        label
			            .closest('.form-group').removeClass('has-error'); // set success class to the control group
			    },

			    submitHandler: function (form) {
			        successElement.show();
			        errorElement.hide();
			        
			        //form.submit();
			        ArgosForm.submit( {"form" : form });
			    }
			});
			
		},
		
		submit: function(options){
			
			
			options.form.submit();
			
			Metronic.blockUI({
			    boxed: true
			});

			window.setTimeout(function() {
			    Metronic.unblockUI();
			    Metronic.alert({
			    	container: "", // alerts parent container(by default placed after the page breadcrumbs)
		            place: "append", // "append" or "prepend" in container 
		            type: 'warning', // alert's type
		            message: "Su solicitud está tardando en completarse. Por favor inténtelo más tarde.", // alert's message
		            close: true, // make alert closable
		            reset: true, // close all previouse alerts first
		            focus: true, // auto scroll to the alert after shown
		            closeInSeconds: 0, // auto close after defined seconds
		            icon: "fa-warning" // put icon before the message
		        });
			}, 40000);
		}
	}
}