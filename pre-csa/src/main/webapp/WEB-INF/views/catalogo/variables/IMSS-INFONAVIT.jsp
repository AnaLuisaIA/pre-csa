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
		<li><a href='<c:url value="/"/>'>Variables IMSS e INFONAVIT</a> <i
			class="fa fa-angle-right"></i></li>
	</tiles:putAttribute>
	<meta charset="UTF-8">
	<tiles:putAttribute name="title">Variables IMSS e INFONAVIT</tiles:putAttribute>

	<tiles:putAttribute name="body">
	<%@include file="../../secciones/messages.jsp"%>
		<div class="row">

			<div class="col-md-12">
				<div class="portlet box green-jungle">
					<div class="portlet-title">
						<div class="caption">

							<i class="fa fa-globe"></i>Catalogo de Variables IMSS e INFONAVIT

						</div>
						<div class="actions">
							<a href="alta" class="btn default green-stripe"> <i
								class="fa fa-plus"></i> <span class="hidden-480"> AGREGAR
									VARIABLE </span>
							</a>
						</div>
					</div>
					<div class="portlet-body">
						<table id="tablaVariables"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th style="display: none;">Id</th>
									<th>Variable</th>
									<th>Descripcion</th>
									<th>Valor</th>
									<th>Tipo</th>
									<th>Fecha de Aplicacion</th>
									<th>Acciones</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="v" items="${variable}">
									<tr>
										<td style="display: none;">${v.id}</td>
										<td>${v.nombre}</td>
										<td>${v.descripcion}</td>
										<td>${v.valor}</td>
										<td>${v.tipo}</td>
										<td><fmt:formatDate value="${v.fechaAplicacion}"
												pattern="dd/MM/yyyy" /></td>
										<td>
											<c:choose>
												<c:when test="${v.estado == true}">
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

											<a href="editar?id=${v.id}" class="btn btn-primary btn-small"> 
												<i class="fa fa-edit"></i>
											 </a>
											<a class="btn btn-primary btn-small" id="btnEliminar"> 
												<i class="fa fa-remove (alias)"></i>
											 </a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<input type="hidden" name="justificacion" id="justificacionIMSSForm" />

					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-9 col-md-offset-2">
				<div class="portlet light portlet-fit bordered">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-pencil-square-o"></i>Bitacora de cambios
						</div>
						<div class="tools">
							<a href="" class="expand"></a>
						</div>
					</div>

					<div class="portlet-body portlet-collapsed">
						<div
							class="table-scrollable table-scrollable-borderless table-responsive">
							<table id="tablaBitacora" class="table table-hover table-light">
								<thead>
									<tr>
										<th>Accion</th>
										<th>Fecha y Hora</th>
										<th>Justificacion</th>
										<th>Colaborador</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="a" items="${acciones}">
										<tr>
											<td>${a.accion}</td>
											<td><fmt:formatDate value="${a.fechaAccion}"
												pattern="dd/MM/yyyy" /></td>
												<td>${a.justificacion}</td>
											<td>${a.numColaborador}- ${a.nombre} ${a.aPaterno}
												${a.aMaterno}</td>
												
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
		$('#tablaVariables').DataTable();
		$('#tablaBitacora').DataTable({
        	"searching": false,
        	"bLengthChange": false,
        	"order": [[ 1, "desc" ]]
		});
  
  //Metodo Eliminar Variable
    $(document).ready(function(){

    // code to read selected table row cell data (values).
    $("#tablaVariables").on('click','#btnEliminar',function(){
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
					     $('#justificacionIMSSForm').val(result);
					       var justificacionIMSSForm = $('#justificacionIMSSForm').val(); 
					      	window.location.href= 'delete?id='+id+"&justificacionIMSSForm="+justificacionIMSSForm;
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

     //Metodo Modifiar Estado Variable
    $(document).ready(function(){

    // code to read selected table row cell data (values).
    $("#tablaVariables").on('click','#btnModificarEstado',function(){
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
					     $('#justificacionIMSSForm').val(result);
					       var justificacionIMSSForm = $('#justificacionIMSSForm').val(); 
					      	window.location.href= 'editarEstado?id='+id+"&justificacionIMSSForm="+justificacionIMSSForm;
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