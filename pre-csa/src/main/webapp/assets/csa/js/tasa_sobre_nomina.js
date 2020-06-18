var form = $("#saveTasa");
var error = $(".alert-danger", form);
var success = $(".alert-success", form);

form.validate({
  doNotHideMessage: true,
  errorElement: "span",
  errorClass: "help-block help-block-error",
  focusInvalid: false,
  rules: {
	estado: {
      required: true,
      maxlength: 200
    },
    tipoNomina: {
      required: true,
    },
    tipoVariable: {
      required: true,
    },
    valor: {
      required: true,
      max: 100000.00,
      min: 0.00,
    },
    oficinas: {
      required: true,
    },
    fechaAplicacion: {
      required: true,
    },
  },

  messages: {
    estado: {
    	required: "El campo Estado es requerido",
    	maxlength: "Se excedieron los 200 caracteres"
    },
    tipoNomina: {
    	required: "Se tiene que seleccionar una Nómina"
    },
    tipoVariable: {
    	required: "Se tiene que seleccionar un Tipo de Variable"
    },
    valor: {
    	required: "Ingrese un valor para la tasa",
    	number: "Ingrese un número válido",
    	max: "El valor de la tasa es inválido",
    	min: "El valor de la tasa tiene que ser mayor",
    },
    oficinas: {
    	required: "Seleccione al menos una Oficina"
    },
    fechaAplicacion: {
    	required: "Seleccione una Fecha"
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

$('#tipoNomina', form).change(function() {
	form.validate().element($(this)); // revalida select en cambio
});

$('#oficinas', form).change(function() {
	form.validate().element($(this)); // revalida select en cambio
});

$('.date-picker .form-control').change(function() {
	form.validate().element($(this)); // revalida las fechas en cambio
});

$('#btnGuardarTasa').click(function(e) {
	e.preventDefault();
	
	if(form.valid()){
		
		if($('#valor')[0].validity.stepMismatch){
			bootbox.alert("El campo <strong>Valor</strong> no puede tener más de 6 decimales");
		} else{
			bootbox.confirm({
				  title: "Registrar Tasa",
				  message: "¿Está seguro de que desea continuar?",
				  buttons: {
				    cancel: {
				      label: '<i class="fa fa-times"></i> Regresar'
				    },
				    confirm: {
				      label: '<i class="fa fa-check"></i> Confirmar'
				    }
				  },
				  callback: function (result) {
				    if (result) {
				      bootbox.prompt({
				        title: "Escriba Justificación",
				        inputType: 'textarea',
				        callback: function (result) {
				          if (result != null && result != "") {
				            $('#justificacionTasaForm').val(result);
				            form.submit();
				          } else if (result === "") {
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

$('#btnActualizarTasa').click(function(e) {
	e.preventDefault();
	
	if(form.valid()){
		if($('#valor')[0].validity.stepMismatch){
			bootbox.alert("El campo <strong>Valor</strong> no puede tener más de 6 decimales");
		} else {
			  bootbox.confirm({
			    title: "Actualizar Tasa",
			    message: "¿Está seguro de que desea continuar?",
			    buttons: {
			      cancel: {
			        label: '<i class="fa fa-times"></i> Regresar',
			      },
			      confirm: {
			        label: '<i class="fa fa-check"></i> Confirmar',
			      },
			    },
			    callback: function (result) {
			      if (result) {
			        bootbox.prompt({
			          title: "Escriba Justificación",
			          inputType: "textarea",
			          callback: function (result) {
			            if (result != null && result != "") {
			              $("#justificacionTasaForm").val(result);
			              form.submit();
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