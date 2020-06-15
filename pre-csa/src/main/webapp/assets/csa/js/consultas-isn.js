var seleccionados_isn = function() {
	if($('input[name="calculos_isn"]:checked').length == 0){
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
    var checkboxes = $('[name="calculos_isn"]');
    if ($(this).is(':checked')) {
        checkboxes.prop('checked', true);
    } else {
        checkboxes.prop('checked', false);
    }
    $.uniform.update();
});

$('.consulta-calculo').click(function () {

    $.getJSON("getCalculoIsn", {
        id: $(this).data("id"),
        mes: $(this).data("mes")
    }, function (c) {

        if (c != null) {
        	
        	let i = 0;      	
            var tabla = $('#consulta_calculo_isn');
            
            tabla.find("thead tr").remove();
            tabla.find("tbody tr").remove();
                       
            tabla.find('thead').append('<tr></tr>');
            
            var thead = $('#consulta_calculo_isn > thead > tr');
            thead.append("<th>Clave Agente</th><th>Nombre</th>");
            
            while(i < c.numeroSemanas){
            	thead.append("<th>"+ (i+1)  +"</th>");
            	i++;
            }
            thead.append("<th>Base Gravable</th><th>ISN Mensual</th>");
            
            tabla.find('tbody').append('<tr></tr>');
            
            var tbody = $('#consulta_calculo_isn > tbody > tr');
            let filaInf = "";
            i = 0
            
            filaInf += '<td>'+ c.claveAgente +'</td><td>Nombre agente</td>';
            
            while(i < c.numeroSemanas){
            	filaInf += '<td>'+ ft.format(c.isnSemanal) +'</td>';
            	i++;
            }
            
            console.log(filaInf);
            filaInf += '<td>'+ ft.format(c.baseGravable) +'</td><td>'+ ft.format(c.isnMensual) +'</td>';
            tbody.append(filaInf);
            
            $('#modalConsulta').modal('show'); 
            $('#exportar_calculo').attr('href', 'exportarIsn?id=' + c.id);
        }

    });

});

$('#exportar_multiples_isn').click(function() {
	if(seleccionados_isn()){
		$('#exporta_calculos').submit();
	}
});