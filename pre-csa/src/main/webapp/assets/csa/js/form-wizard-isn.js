var wizardIsn = function() {
	if (!jQuery().bootstrapWizard) {
		return;
	}

	var form = $('#submit_form2');
	var error = $('.alert-danger', form);
	var success = $('.alert-success', form);

	form.validate({
		doNotHideMessage : true,
		errorElement : 'span',
		errorClass : 'help-block help-block-error',
		focusInvalid : false,
		rules : {
			// calendario
			anioCalendario : {
				required : true
			},
			semanaCalendario : {
				required : true
			}
		},

		messages : {
			anioCalendario : {
				required : "El año del calendario es requerido"
			},
			semanaCalendario : {
				required : "La semana a calcular es requerida"
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
			$(element).closest('.form-group').removeClass('has-success')
					.addClass('has-error');
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
		$('#tab3 .form-control-static', form).each(
				function() {
					var input = $('[name="' + $(this).attr("data-display")
							+ '"]', form);
					if (input.is(":text") || input.is("textarea")) {
						$(this).html(input.val());
					} else if (input.is("select")) {
						$(this).html(input.find('option:selected').text());
					} else if (input.is(":file")) {
						$(this).html(input[0].files[0]['name']);
					}
				});
	}

	var handleTitle = function(tab, navigation, index) {
		var total = navigation.find('li').length;
		var current = index + 1;
		// set wizard title
		$('.step-title', $('#form_wizard_2')).text(
				'Paso ' + (index + 1) + ' de ' + total);
		// set done steps
		jQuery('li', $('#form_wizard_2')).removeClass("done");
		var li_list = navigation.find('li');
		for (var i = 0; i < index; i++) {
			jQuery(li_list[i]).addClass("done");
		}

		if (current == 1) {
			$('#form_wizard_2').find('.button-previous').hide();
		} else {
			$('#form_wizard_2').find('.button-previous').show();
		}

		if (current >= total) {
			$('#form_wizard_2').find('.button-next').hide();
			$('#form_wizard_2').find('.button-submit').show();
			displayConfirm();
		} else {
			$('#form_wizard_2').find('.button-next').show();
			$('#form_wizard_2').find('.button-submit').hide();
		}
		Metronic.scrollTo($('.page-title'));
	}

	// default form wizard
	$('#form_wizard_2').bootstrapWizard({
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
			$('#form_wizard_2').find('.progress-bar').css({
				width : $percent + '%'
			});
		}
	});

	$('#form_wizard_2').find('.button-previous').hide();
	$('#form_wizard_2').find('.button-continue').hide();
	$('#form_wizard_2').find('.button-end').hide();

	$('#form_wizard_2 .button-submit').click(function() {
		verificarTasas();
	}).hide();

	$('#anioCalendario', form).change(function() {
		form.validate().element($(this)); // revalida select en cambio
		
		if($('#anioCalendario') != ""){
			obtenerSemanas();
		}
	});

	$('#semanaCalendario', form).change(function() {
		form.validate().element($(this)); // revalida select en cambio
	});

}

var obtenerSemanas = function() {
	$.getJSON("getSemanas", {
		anio : $('#anioCalendario').val()
	}, function(semanas) {
		
		if(semanas != null){
			
			for(i = 0; i < semanas.length; i++) {
				
				var fecha1 = moment(semanas[i].fechaInicio).format("DD/MM/YYYY");
				var fecha2 = moment(semanas[i].fechaFin).format("DD/MM/YYYY");
				
				var option = $('<option/>');
				option.attr({
					'value' : semanas[i].id
				}).text(fecha1 + " - " + fecha2);
				
				$('#semanaCalendario').append(option);
			}
		}
	});
}

var verificarTasas = function() {
	$.getJSON("getTAP", {
		fechaInicio: $('#fechaInicio').val(),
		fechaFin: $('#fechaFin').val(), 
		periodo: $('#tipoPeriodo').val()
	}, function(localidades) {
		console.log(localidades);
		if(localidades != null || localidades.length > 0){
			
			$('#modalTAP').modal('show');
			
			var tabla = $('#tablaTAP');
			tabla.find("tbody tr").remove();
			localidades.forEach(function(l){
    			tabla.append("<tr><td class='estadoTasa'>" + l.estado 
    				+ "</td><td><input type='number' class='form-control input-small total-pagar' " +
    						"max='1000' step='0.01' value=" + l.totalAPagar + ">"
    				+"</td></tr>");
    		});
		} else {
			waitingDialog.show('Realizando cálculos...');
        	$('#submit_form2').submit();
        	
        	setTimeout(function () {
        		waitingDialog.hide();
            	$('#form_wizard_2').find('.button-previous').hide();
            	$('#form_wizard_2').find('.button-submit').hide();
            	$('#form_wizard_2').find('.button-end').show();
        	}, 3000);
        	
        	return true;
		}
		
	});
}

var registrarTAP = function(valores) {
	console.log(valores);
	
	$.ajax({
		type: "POST",
		url: "registrarTAP",
		data: JSON.stringify(valores),
		dataType: "json",
		contentType: "application/json; charset=utf-8",
	    success: function (response) {
	        if(response){
	        	$('#modalTAP').modal('hide');
	        	
	        	waitingDialog.show('Realizando cálculos...');
	        	$('#submit_form2').submit();
	        	
	        	setTimeout(function () {
	        		waitingDialog.hide();
	            	$('#form_wizard_2').find('.button-previous').hide();
	            	$('#form_wizard_2').find('.button-submit').hide();
	            	$('#form_wizard_2').find('.button-end').show();
	        	}, 3000);
	        	
		        return true;
	        }
	    },
	    error: function (error) {
	    	bootbox.alert("<strong>ERROR: No es posible asignar el Total a Pagar.</strong>");
	    }
	});
}

$('#btnGuardarTAP').click(function() {
	let valores = [];
	let mensaje = '';
	
	$('#tablaTAP > tbody > tr').each(function() {
		let estado = $(this).find(".estadoTasa").html(); 
		let valor = $(this).find("input").val();
		
		if(valor === '0' || valor === ''){
			mensaje += "El valor de Total a Pagar de <strong>" + estado + "</strong> no puede ser 0.<br>"
		} else if (parseInt(valor) > 1000){
			mensaje += "El estado <strong>" + estado + "</strong> no tiene un valor válido de Total a Pagar.<br>"
		} else {
			valores.push({"estado": estado,
				"valor": valor});
		}
		
	});
	
	if(mensaje != ''){
		bootbox.alert(mensaje);
	} else {
		registrarTAP(valores);
	}

	
});