var form = $("#saveVariable");
var error = $(".alert-danger", form);
var success = $(".alert-success", form);

form.validate({
  doNotHideMessage: true,
  errorElement: "span",
  errorClass: "help-block help-block-error",
  focusInvalid: false,
  rules: {
	nombre: {
      required: true,
      maxlength: 100
    },
    descripcion: {
      required: true,
      maxlength: 200
    },
    valor: {
      required: true,
      max: 100000.00,
      min: 0.00,
    },
    fechaAplicacion: {
      required: true
    },
    tipo: {
    	required: true
    }
  },

  messages: {
    nombre: {
    	required: "El campo Nombre es requerido",
    	maxlength: "Se excedieron los 100 caracteres"
    },
    descripcion: {
    	required: "El campo Descripción es requerido",
    	maxlength: "Se excedieron los 200 caracteres"
    },
    valor: {
    	required: "Ingrese un valor para la tasa",
    	max: "El valor de la tasa es inválido",
    	min: "El valor de la tasa tiene que ser mayor",
    },
    fechaAplicacion: {
    	required: "Seleccione una Fecha"
    },
    tipo: {
    	required: "El campo Tipo de Variable es requerido"
    }
  },

  errorPlacement: function (error, element) {
    if (element.parent(".input-group").size() > 0) {
      error.insertAfter(element.parent(".input-group"));
    } else if (element.attr("data-error-container")) {
      error.appendTo(element.attr("data-error-container"));
    } else {
      error.insertAfter(element);
    }
  },

  invalidHandler: function (event, validator) {
    success.hide();
    error.show();
    Metronic.scrollTo(error, -200);
  },

  highlight: function (element) {
    // hightlight error inputs
    $(element)
      .closest(".form-group")
      .removeClass("has-success")
      .addClass("has-error");
  },

  unhighlight: function (element) {
    // revert the change done by
    // hightlight
    $(element).closest(".form-group").removeClass("has-error");
  },

  success: function (label) {
    label
      .addClass("valid")
      .closest(".form-group")
      .removeClass("has-error")
      .addClass("has-success");
  },

  submitHandler: function (form) {
    success.show();
    error.hide();
    //form.submit();
    // add here some ajax code to submit your form or just call
    // form.submit() if you want to submit the form without ajax
  },
});

$('#tipo', form).change(function() {
	form.validate().element($(this)); // revalida select en cambio
});

$('.date-picker .form-control').change(function() {
	form.validate().element($(this)); // revalida las fechas en cambio
});

$('#btnGuardarVariable').click(function(e) {
	e.preventDefault();
	
	if(form.valid()){
		if($('#valor')[0].validity.stepMismatch){
			bootbox.alert("El campo <strong>Valor</strong> no puede tener más de 6 decimales");
		} 
		
		else{
			bootbox.setLocale('es');
	        bootbox.confirm({
	        	title: "Registrar Variable",
	        	message: "¿Está seguro de que desea continuar?",  
	        	callback: function(result){
			 	if(result){	
			    	bootbox.prompt({
			    		title: "Escriba Justificación",
			    		inputType: 'textarea',
			    		callback: function (result) {
			    			if(result != null && result != ""){
				     			$('#justificacionSolicitudForm').val(result);
				       			form.submit();
				   			} else if(result === "") {
				    			bootbox.alert({
								   message: "<b>El campo de Justificación es obligatorio.</b>",
								   size: 'small'
								});
				    		}
			    		}
					});			   
		       			
	        	}
	        }
	      });
		}
	}
});

$('#btnActualizarVariable').click(function(e) {
	e.preventDefault();
	
	if(form.valid()){
		if($('#valor')[0].validity.stepMismatch){
			bootbox.alert("El campo <strong>Valor</strong> no puede tener más de 6 decimales");
		} 
		
		else {
			bootbox.setLocale('es');
			bootbox.confirm({
				  title: "Actualizar Variable",
				  message: "¿Está seguro de que desea continuar?",
				  callback: function (result) {
				    if (result) {
				      bootbox.prompt({
				        title: "Escriba Justificación",
				        inputType: "textarea",
				        callback: function (result) {
				          if (result != null && result != "") {
				            $("#justificacionSolicitudForm").val(result);
				            $("#saveVariable").submit();
				          } else if (result === "") {
				            bootbox.alert({
				              message: "<b>El campo de Justificación es obligatorio.</b>",
				              size: "small",
				            });
				          }
				        },
				      });
				    }
				  },
				});
		}
	}
});