<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<tiles:insertDefinition name="defaultTemplate">

	<tiles:putAttribute name="styles">
		<link
			href="<c:url value='/assets/global/plugins/datatables/extensions/Scroller/css/dataTables.scroller.min.css'/>"
			rel="stylesheet" type="text/css" />
		<link
			href="<c:url value='/assets/global/plugins/datatables/extensions/ColReorder/css/dataTables.colReorder.min.css'/>"
			rel="stylesheet" type="text/css" />
		<link
			href="<c:url value='/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css'/>"
			rel="stylesheet" type="text/css" />
	</tiles:putAttribute>
	<tiles:putAttribute name="nav">
		<li><a href='<c:url value="/"/>'>Tasa Sobre Nomina</a> <i
			class="fa fa-angle-right"></i></li>
	</tiles:putAttribute>
	<meta charset="UTF-8">
	<tiles:putAttribute name="title">Tasa Sobre Nomina</tiles:putAttribute>

	<tiles:putAttribute name="body">
	<%@include file="../../secciones/messages.jsp"%>
		<div class="row">

			<div class="col-md-12">
				<div class="portlet box green-jungle">
					<div class="portlet-title">
						<div class="caption">

							<i class="fa fa-globe"></i>Catalogo de Tasa Sobre Nomina

						</div>
						<div class="actions">
							<a href="alta" class="btn default green-stripe"> <i
								class="fa fa-plus"></i> <span class="hidden-480"> AGREGAR
									TASA </span>
							</a>
						</div>
					</div>
					<div class="portlet-body">
						<table id="tablaTasas"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th style="display: none;">Id</th>
									<th>Estado</th>
									<th>Tipo Nomina</th>
									<th>Tipo Variable</th>
									<th>Valor</th>
									<th>Oficina</th>
									<th>Fecha Aplicación</th>
									<th>Acciones</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="t" items="${tasa}">
									<tr>
										<td style="display: none;">${t.id}</td>
										<td>${t.estado}</td>
										<td>${t.tipoNomina.label}</td>
										<td>${t.tipoVariable.label}</td>
										<td>${t.valor}</td>
										<td>${t.oficina}</td>
										<td><fmt:formatDate value="${t.fechaAplicacion}"
												pattern="dd/MM/yyyy" /></td>
										<td>
											<c:choose>
												<c:when test="${t.estatus == true}">
												<a id="btnModificarEstado" style="color:#06F61C;"  class="btn btn-primary "> 
												<i class="fa fa-power-off"></i>
												</a>
												</c:when>
												<c:otherwise>
												<a id="btnModificarEstado" style="color:#F60606;"  class="btn btn-primary "> 
												<i class="fa fa-power-off"></i>
												</a>
												</c:otherwise>
											</c:choose>

											<a href="editar?id=${t.id}" class="btn btn-primary btn-small"> 
												<i class="fa fa-edit"></i>
											 </a>
											<button type="submit" class="btn btn-danger" id="btnEliminar">
												<i class="fa fa-trash-o"></i>
											</button></td>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<input type="hidden" name="justificacion" id="justificacionTasaForm" />

					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light portlet-fit bordered">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-pencil-square-o"></i>Bitacora de cambios
						</div>
						<div class="tools">
							<a href="" class="expand"></a>
						</div>
					</div>

					<div class="portlet-body portlet-collapsed" style="display: none;">
						<div
							class="table-scrollable table-scrollable-borderless table-responsive">
							<table id="tablaBitacora"
								class="table table-striped table-bordered table-hover order-column dataTable no-footer">
								<thead>
									<tr>
										<th>Acción</th>
										<th>Fecha y Hora</th>
										<th>Justificación</th>
										<th>Colaborador</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="a" items="${accionest}">
										<tr>
											<td>${a.accion}</td>
											<td><fmt:formatDate value="${a.fecha}" type="both"
													dateStyle="short" timeStyle="short" /></td>
											<td>${a.justificacion}</td>
											<td>${a.numColaborador} - ${a.nombre} ${a.aPaterno} ${a.aMaterno}</td>

										</tr>
									</c:forEach>
								</tbody>
							</table>

						</div>
					</div>
				</div>

			</div>
		</div>
	</tiles:putAttribute>

	<tiles:putAttribute name="scripts">

		<script type="text/javascript"
			src="<c:url value='/assets/global/plugins/datatables/media/js/jquery.dataTables.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/plugins/datatables/extensions/TableTools/js/dataTables.tableTools.min.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/plugins/datatables/extensions/ColReorder/js/dataTables.colReorder.min.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/plugins/datatables/extensions/Scroller/js/dataTables.scroller.min.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/admin/pages/scripts/argostable.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/scripts/jquery.spring-friendly.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/admin/pages/scripts/ui-idletimeout.js'/>"></script>

	</tiles:putAttribute>


	<tiles:putAttribute name="ready"> 
		$('#catalogos').addClass("start active open");
		$('#tablaTasas').DataTable();
		$('#tablaBitacora').DataTable({
        	"searching": false,
        	"bLengthChange": false,
        	"order": [[ 1, "desc" ]]
		});

  
  //Metodo Eliminar Variable
    $(document).ready(function(){

    // code to read selected table row cell data (values).
    $("#tablaTasas").on('click','#btnEliminar',function(){
         // get the current row
         var currentRow=$(this).closest("tr"); 
         
         var col1=currentRow.find("td:eq(0)").text(); // get current row 1st TD value
         var id=col1;

          mensaje = "";
	        	bootbox.setLocale('es');
	        	bootbox.prompt({
				    title: "Escriba Justificacion",
				    inputType: 'textarea',
				    callback: function (result) {
				    	if(result != null && result != ""){
					     $('#justificacionTasaForm').val(result);
					       var justificacionTasaForm = $('#justificacionTasaForm').val(); 
					      	window.location.href= 'delete?id='+id+"&justificacionTasaForm="+justificacionTasaForm;
					    } else if(result === "") {
					    	bootbox.alert({
							   message: "<b>El campo de Justificacion es obligatorio.</b>",
							   size: 'small'
							});
					    }
				    }
				});
    });
});

     //Metodo Modificar Estado Variable
    $(document).ready(function(){

    // code to read selected table row cell data (values).
    $("#tablaTasas").on('click','#btnModificarEstado',function(){
         // get the current row
         var currentRow=$(this).closest("tr"); 
         
         var col1=currentRow.find("td:eq(0)").text(); // get current row 1st TD value
         var id=col1;

          mensaje = "";
	        	bootbox.setLocale('es');
	        	bootbox.prompt({
				    title: "Escriba Justificación",
				    inputType: 'textarea',
				    callback: function (result) {
				    	if(result != null && result != ""){
					     $('#justificacionTasaForm').val(result);
					       var justificacionTasaForm = $('#justificacionTasaForm').val(); 
					      	window.location.href= 'editarEstados?id='+id+"&justificacionTasaForm="+justificacionTasaForm;
					    } else if(result === "") {
					    	bootbox.alert({
							   message: "<b>El campo de Justificacion es obligatorio.</b>",
							   size: 'small'
							});
					    }
				    }
				});
    });
});

   </tiles:putAttribute>

	<tiles:putAttribute name="footer">
		<div class="page-footer-inner">
			2019 &copy; <a href="http://www.segurosargos.com/"
				title="Seguros Argos" target="_blank">Seguros Argos</a>
		</div>
		<div class="scroll-to-top">
			<i class="icon-arrow-up"></i>
		</div>
	</tiles:putAttribute>

</tiles:insertDefinition>