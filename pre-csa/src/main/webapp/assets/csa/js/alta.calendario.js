$('#btnGuardar').on('click', function(e){
	e.preventDefault();
	
	if($('#archivo')[0].files.length === 0){
		
		$('#archivo').closest('.form-group').addClass('has-error');
		$('input#archivo').after("<span class='help-block error-file'>Selecciona un archivo</span>");
		
	} else {

		var nombreArchivo = $('#archivo')[0].files[0]['name'];
		var anio = nombreArchivo.substring(11,15);
		
		if(/^(19|20)\d{2}$/.test(anio) === true){
			bootbox.setLocale('es');
			bootbox.confirm({
		        message: "<b>¿Está seguro de cargar el calendario para el año " + anio + "?</b>",
		        callback: function(result){
			        if(result){
			        	$('#anio').val(anio);
			        	$('#fileName').val(nombreArchivo);
			        	$('#formCalendario').submit();
	        		}
	        	}
	   		});
	   		
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