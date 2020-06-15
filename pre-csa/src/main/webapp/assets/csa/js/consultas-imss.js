var seleccionados = function() {
	if($('input[name="calculos_imss"]:checked').length == 0){
		bootbox.alert("<strong>Selecciona al menos un cálculo para exportar</strong>");
		return false;
	}
	return true;
}

var ft = new Intl.NumberFormat('es-MX', {
    style: 'currency',
    currency: 'MXN',
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
});

$('#todos').change(function () {
    var checkboxes = $('[name="calculos_imss"]');
    if ($(this).is(':checked')) {
        checkboxes.prop('checked', true);
    } else {
        checkboxes.prop('checked', false);
    }
    $.uniform.update();
});

$('.consulta-calculo').click(function () {

    $.getJSON("getCalculo", {
        id: $(this).data("id")
    }, function (c) {

        if (c != null) {

            var tabla = $('#consulta_calculo_imss');
            tabla.find("tbody tr").remove();

            $('#periodoTxt').find("h4").remove();
            $('#fechaCalculoTxt').find("h4").remove();
            $('#usuarioCalculoTxt').find("h4").remove();

            tabla.append("<tr><td> "+ c.claveAgente + " </td><td> " + ft.format(c.cuotaFijaP) 
            + " </td><td> "+ ft.format(c.excedenteP)  +" </td><td> " + ft.format(c.prestacionesP) 
            +" </td><td> " + ft.format(c.gastosMedP) + " </td><td> "+ ft.format(c.rtp) 
            +" </td><td> "+ ft.format(c.guarderiaP) +" </td><td> "+ ft.format(c.invVidaP) 
            +" </td><td> "+ ft.format(c.totalPatron) +" </td><td> "+ ft.format(c.excedenteT) 
            +" </td><td> "+ ft.format(c.prestacionesT) +" </td><td> "+ ft.format(c.gastosMedicosT) 
            +" </td><td> "+ ft.format(c.invVidaT) +" </td> <td> "+ ft.format(c.totalTrabajador) 
            +" </td><td> "+ ft.format(c.totalIMSS) +" </td> <td> "+ ft.format(c.infonavitPatron) 
            +" </td><td> "+ ft.format(c.infonavitTrabajador) +" </td> <td> "+ ft.format(c.totalInfonavit) 
            +" </td></tr>");

            $('#periodoTxt').append("<h4><strong > Periodo: </strong>" 
            + moment(c.periodoInicio).format("DD/MM/YYYY") + " - " + moment(c.periodoFin).format("DD/MM/YYYY") 
            + " </h4 > ");

            $('#fechaCalculoTxt').append("<h4><strong> Fecha de cálculo: </strong>" 
            + moment(c.fechaCalculo).format("DD/MM/YYYY") + " </h4> ");

            $('#usuarioCalculoTxt').append("<h4><strong> Realizó el cálculo: </strong>" 
            + c.numColaborador + " - " + c.nombres + " " + c.aPaterno + " " + c.aMaterno + " </h4> ");

            $('#modalConsulta').modal('show'); 
            $('#exportar_calculo').attr('href', 'exportarImss?id=' + c.id);
        }

    });

});

$('#exportar_multiples_imss').click(function() {
	if(seleccionados()){
		$('#exporta_calculos').submit();
	}
});