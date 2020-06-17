$('#btnGuardar').on('click', function(e){
	e.preventDefault();
	
	if($('#archivo')[0].files.length === 0){
		
		$('#archivo').closest('.form-group').addClass('has-error');
		$('input#archivo').after("<span class='help-block error-file'>Selecciona un archivo</span>");
		
	} else {

		var nombreArchivo = $('#archivo')[0].files[0]['name'];
		var anio = nombreArchivo.substring(11,15);
		
		if(/^(19|20)\d{2}$/.test(anio) === true){
			//REVISAR SI CALENDARIO EXISTE / CALENDARIO ES VÁLIDO
			checkCalendario(nombreArchivo, anio);
	   		
		} else {
			bootbox.setLocale('es');
			bootbox.alert("<b>El nombre del archivo es incorrecto. Debe contener el año del calendario.</b>");
		}	
	}
});

$('#archivo').on('change', function(){
	$('#archivo').closest('.form-group').removeClass('has-error');
	$('.help-block.error-file').remove();
});

var checkCalendario = function(nombreArchivo, anio){
	
	var fd = new FormData();
	fd.append("archivo", $('#archivo').prop('files')[0]);
	fd.append("anio", anio);
	
	$.ajax({
		type: "POST",
		url: "checkCalendario",
		data: fd,
		cache: false,
	    contentType: false,
	    processData: false,
	    success: function(response) {
	    	console.log(response);
			//Calendario existe
	    	if(response === 1){
	    		
				bootbox.setLocale('es');
				bootbox.confirm({
			        message: "<b>El calendario del año " + anio + " ya existe.</b> ¿Desea registrarlo nuevamente?",
			        callback: function(result){
				        if(result){
				        	$('#anio').val(anio);
				        	$('#fileName').val(nombreArchivo);
				        	$('#esNuevo').val(false);
				        	$('#formCalendario').submit();
		        		}
		        	}
		   		});
	    		
	    	} 
	    	//Calendario no es válido
	    	else if (response === 2){
				bootbox.alert("El archivo <b>"+ nombreArchivo +"</b> no es válido. Favor de verificar.");
	    	}
	    	//Nuevo calendario válido
	    	else {
	    		
				bootbox.setLocale('es');
				bootbox.confirm({
			        message: "<b>¿Está seguro de cargar el calendario para el año " + anio + "?</b>",
			        callback: function(result){
				        if(result){
				        	$('#anio').val(anio);
				        	$('#fileName').val(nombreArchivo);
				        	$('#esNuevo').val(true);
				        	$('#formCalendario').submit();
		        		}
		        	}
		   		});
	    	}
		},
		
		error: function(error) {
			bootbox.alert("<strong>Ha ocurrido un problema con la validación del archivo.");
		}
	});
	
}