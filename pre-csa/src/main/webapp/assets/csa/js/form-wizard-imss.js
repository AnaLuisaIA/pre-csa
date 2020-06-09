var validacionFile;
	
function setResultadoArchivo(x){
	validacionFile = x;
}

var wizard = function() {
	if (!jQuery().bootstrapWizard) {
		return;
	}

	var form = $('#submit_form');
	var error = $('.alert-danger', form);
	var success = $('.alert-success', form);

	form
			.validate({
				doNotHideMessage : true,
				errorElement : 'span',
				errorClass : 'help-block help-block-error',
				focusInvalid : false,
				rules : {
					// archivo
					archivo : {
						accept: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
						required : true
					},
					// periodo
					tipoPeriodo: {
						required : true
					},
					fechaInicio: {
						required : true,
						maxlength : 10
					},
					fechaFin: {
						required : true,
						maxlength : 10
					},
					// nomina
					tipoNomina: {
						required : true
					},
					// variables
					fechaVariables: {
						required : true
					}
				},

				messages : {
					archivo: {
						required : "Seleccione un archivo",
						accept : "El archivo tiene que tener la extensión .xlsx"
					},
					tipoPeriodo: {
						required : "Seleccione el tipo de periodo"
					},
					fechaInicio: {
						required : "La fecha es requerida"
					},
					fechaFin: {
						required : "La fecha es requerida"
					},
					tipoNomina: {
						required : "Selecciona un tipo de nómina"
					},
					fechaVariables: {
						required : "Selecciona la fecha de la tabla de variables"
					}
				},

				errorPlacement : function(error, element) {
					if (element.parent(".input-group").size() > 0) {
						error.insertAfter(element.parent(".input-group"));
					} else if (element.attr("data-error-container")) {
						error.appendTo(element.attr("data-error-container"));
					} else {
						error.insertAfter(element);
					}

				},

				invalidHandler : function(event, validator) {
					success.hide();
					error.show();
					Metronic.scrollTo(error, -200);
				},

				highlight : function(element) { // hightlight error inputs
					$(element).closest('.form-group')
							.removeClass('has-success').addClass('has-error');
				},

				unhighlight : function(element) { // revert the change done by
					// hightlight
					$(element).closest('.form-group').removeClass('has-error');
				},

				success : function(label) {
					label.addClass('valid').closest('.form-group').removeClass(
							'has-error').addClass('has-success');
				},

				submitHandler : function(form) {
					success.show();
					error.hide();
					form.submit();
					// add here some ajax code to submit your form or just call
					// form.submit() if you want to submit the form without ajax
				}

			});

	var displayConfirm = function() {
		$('#tab5 .form-control-static', form).each(
				function() {
					var input = $('[name="' + $(this).attr("data-display")
							+ '"]', form);
					if (input.is(":text") || input.is("textarea")) {
						$(this).html(input.val());
					} else if (input.is("select")) {
						$(this).html(input.find('option:selected').text());
					} else if (input.is(":file")){
						$(this).html(input[0].files[0]['name']);
					}
				});
	}

	var handleTitle = function(tab, navigation, index) {
		var total = navigation.find('li').length;
		var current = index + 1;
		// set wizard title
		$('.step-title', $('#form_wizard_1')).text(
				'Paso ' + (index + 1) + ' de ' + total);
		// set done steps
		jQuery('li', $('#form_wizard_1')).removeClass("done");
		var li_list = navigation.find('li');
		for (var i = 0; i < index; i++) {
			jQuery(li_list[i]).addClass("done");
		}

		if (current == 1) {
			$('#form_wizard_1').find('.button-previous').hide();
		} else {
			$('#form_wizard_1').find('.button-previous').show();
		}

		if (current >= total) {
			$('#form_wizard_1').find('.button-next').hide();
			$('#form_wizard_1').find('.button-submit').show();
			displayConfirm();
		} else {
			$('#form_wizard_1').find('.button-next').show();
			$('#form_wizard_1').find('.button-submit').hide();
		}
		Metronic.scrollTo($('.page-title'));
	}

	// default form wizard
	$('#form_wizard_1').bootstrapWizard({
		'nextSelector' : '.button-next',
		'previousSelector' : '.button-previous',
		onTabClick : function(tab, navigation, index, clickedIndex) {
			return false;
			/*
			 * success.hide(); error.hide(); if (form.valid() == false) { return
			 * false; } handleTitle(tab, navigation, clickedIndex);
			 */
		},
		onNext : function(tab, navigation, index) {
			success.hide();
			error.hide();

			if (form.valid() == false) {
				return false;
			} else if(form.valid() && index == 1 && !validacionFile){
				return false;
			} else if(form.valid() && index == 2 ){
				//Validación de periodo
				$.ajax({
					type: "POST",
					url: "checkPeriodo",
					data: jQuery.param({fechaInicio: $('#fechaInicio').val(),
						fechaFin: $('#fechaFin').val(), 
						periodo: $('#tipoPeriodo').val() }),
					contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
					
				    success: function (response) {
				        if(response){
					        bootbox.setLocale('es');
					        bootbox.confirm({
						        message: "<strong>El periodo ya ha sido calculado. ¿Desea recalcular?</strong>",
						        callback: function(result){
							        if(!result){
						       			window.location.href= "calculos/";
					        		} else {
					        			recalcularPeriodo();
					        		}
					        	}
					        });
				        }
				    },
				    error: function (error) {
				    	bootbox.alert("<strong>Hubo un error al verificar las fechas del periodo.</strong>");
				    }
				});
				
			}

			handleTitle(tab, navigation, index);
		},
		onPrevious : function(tab, navigation, index) {
			success.hide();
			error.hide();

			handleTitle(tab, navigation, index);
		},
		onTabShow : function(tab, navigation, index) {
			var total = navigation.find('li').length;
			var current = index + 1;
			var $percent = (current / total) * 100;
			$('#form_wizard_1').find('.progress-bar').css({
				width : $percent + '%'
			});
		}
	});

	$('#form_wizard_1').find('.button-previous').hide();
	$('#form_wizard_1').find('.button-continue').hide();
	
    $('#form_wizard_1 .button-submit').click(function () {
    	waitingDialog.show('Realizando cálculos...');
    	form.submit();
    	
    	setTimeout(function () {
    		waitingDialog.hide();
        	$('#form_wizard_1').find('.button-previous').hide();
        	$('#form_wizard_1').find('.button-submit').hide();
        	$('#form_wizard_1').find('.button-continue').show();
    	}, 3000);
    	
    }).hide();

	$('#tipoPeriodo', form).change(function() {
		form.validate().element($(this)); // revalida select en cambio
	});

	$('#tipoNomina', form).change(function() {
		form.validate().element($(this)); // revalida select en cambio
	});

	$('#fechaVariables', form).change(function() {
		form.validate().element($(this)); // revalida select en cambio
		
		var fechaAplicacion = $(this).find(':selected').data('inicio');
		var fechaTermino = $(this).find(':selected').data('fin');
		
		$('#fechaAplicacion').val(null);
		$('#fechaTermino').val(null);
		
		if(moment(fechaAplicacion).isValid()){
			$('#fechaAplicacion').val(moment(fechaAplicacion).format("DD/MM/YYYY"));
		}
		
		if(moment(fechaTermino).isValid() && fechaTermino !== undefined){
			$('#fechaTermino').val(moment(fechaTermino).format("DD/MM/YYYY"));
		}

	});

	$('.date-picker .form-control').change(function() {
		form.validate().element($(this)); // revalida las fechas en cambio
	});
	
	$('#archivo', form).change(function() {
		$("#errorValid").remove();
		
		form.validate().element($(this)); // revalida archivo en cambio
		
		if($('#archivo')[0].files.length != 0){
			validarArchivo();
		}
		
	});
}

var recalcularPeriodo = function() {
	$.ajax({
		type: "POST",
		url: "recalcularPeriodo",
		data: jQuery.param({fechaInicio: $('#fechaInicio').val(),
			fechaFin: $('#fechaFin').val(), 
			periodo: $('#tipoPeriodo').val() }),
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		
	    success: function (response) {
	        if(response){
		        return true;
	        }
	    },
	    error: function (error) {
	    	bootbox.alert("<strong>ERROR: No es posible recalcular el periodo.</strong>");
	    }
	});
}

var validarArchivo = function(){
	var fd = new FormData();
	fd.append("archivo", $('#archivo').prop('files')[0]);
	$.ajax({
		type: "POST",
		url: "checkFile",
		data: fd,
		cache: false,
	    contentType: false,
	    processData: false,
	    success: function(response){
	    	if(!response){
	    		$($('#archivo')).closest('.form-group')
					.removeClass('has-success').addClass('has-error');
	    		$('<span id="errorValid" class="help-block help-block-error">El archivo no es válido</span>')
	    			.insertAfter($('#archivo'));
	    		
	    		setResultadoArchivo(false);
	    	} else {
	    		setResultadoArchivo(true);
	    	}
	    },
	    error: function(error){
	    	$('.loading').hide();
	    	bootbox.alert("<strong>ERROR: No se pudo verificar el archivo de carga.</strong>");
	    }
		
	});
}