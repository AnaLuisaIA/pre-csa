<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<li><a href='<c:url value="/variables/"/>'>Variables IMSS e
				INFONAVIT</a> <i class="fa fa-angle-right"></i></li>
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

							<i class="fa fa-globe"></i>Catálogo de Variables IMSS e INFONAVIT

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
									<th style="display: none;">IdVariable</th>
									<th style="display: none;">Id</th>
									<th>Variable</th>
									<th>Descripcion</th>
									<th>Valor</th>
									<th>Tipo</th>
									<th>Fecha de Aplicacion</th>
									<th>Fecha Termino</th>
									<th>Acciones</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="v" items="${variable}">
									<tr>
										<td style="display: none;">${v.idVariable}</td>
										<td style="display: none;">${v.id}</td>
										<td>${v.nombre}</td>
										<td>${v.descripcion}</td>
										<td>${v.valor}</td>
										<td>${v.tipo.label}</td>
										<td><fmt:formatDate value="${v.fechaAplicacion}"
												pattern="dd/MM/yyyy" /></td>
										<td><fmt:formatDate value="${v.fechaTermino}"
												pattern="dd/MM/yyyy" /></td>
										<td><c:choose>
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
											</c:choose> <a href="editar?idvariable=${v.idVariable}&idPeriodo=${v.id}"
											class="btn btn-primary btn-small"> <i class="fa fa-edit"></i>
										</a>
											<button type="submit" class="btn btn-danger" id="btnEliminar">
												<i class="fa fa-trash-o"></i>
											</button>
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
									<c:forEach var="a" items="${acciones}">
										<tr>
											<td>${a.accion}</td>
											<td><fmt:formatDate value="${a.fechaAccion}" type="both"
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
		$('#variablesMenu').addClass("active");
		
		$('#tablaVariables').DataTable();
		$('#tablaBitacora').DataTable({
        	"searching": false,
        	"bLengthChange": false,
        	"order": [[ 1, "desc" ]]
		});

  
  //Metodo Eliminar Variable
    // code to read selected table row cell data (values).
    $("#tablaVariables").on('click','#btnEliminar',function(){
         // get the current row
         var currentRow=$(this).closest("tr"); 
         
         var col1=currentRow.find("td:eq(0)").text(); // get current row 1st TD value
         var idVariable=col1;

          mensaje = "";
		        bootbox.confirm({
		        	title: "Eliminar Variable",
			        message: "Al eliminar esta variable se eliminarán los registros relacionados ¿Está seguro de que desea continuar?",
			        buttons: {
			        	cancel: {
				            label: '<i class="fa fa-times"></i> Regresar'
				        },
				        confirm: {
				            label: '<i class="fa fa-check"></i> Confirmar'
				        }
			        },	   
			        callback: function(result){
				     if(result){	
				        		bootbox.prompt({
				    title: "Escriba Justificacion",
				    inputType: 'textarea',
				    callback: function (result) {
				    	if(result != null && result != ""){
					     $('#justificacionIMSSForm').val(result);
					       var justificacionIMSSForm = $('#justificacionIMSSForm').val();
					      	window.location.href= 'delete?idVariable='+idVariable+"&justificacionIMSSForm="+justificacionIMSSForm;
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
    });


     //Metodo Modificar Estado Variable

    // code to read selected table row cell data (values).
    $("#tablaVariables").on('click','#btnModificarEstado',function(){
         // get the current row
         var currentRow=$(this).closest("tr"); 
         
         var col1=currentRow.find("td:eq(0)").text();
         var idVariable=col1;

		        bootbox.confirm({
		        	title: "Estado Variable",
			        message: "¿Está seguro de que desea continuar?",
			        buttons: {
			        	cancel: {
				            label: '<i class="fa fa-times"></i> Regresar'
				        },
				        confirm: {
				            label: '<i class="fa fa-check"></i> Confirmar'
				        }
			        },	   
			        callback: function(result){
				     if(result){	
				        		bootbox.prompt({
				    title: "Escriba Justificacion",
				    inputType: 'textarea',
				    callback: function (result) {
				    	if(result != null && result != ""){
					     $('#justificacionIMSSForm').val(result);
					       var justificacionIMSSForm = $('#justificacionIMSSForm').val();
					      	window.location.href= 'editarEstado?idVariable='+idVariable+"&justificacionIMSSForm="+justificacionIMSSForm;
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

    });

   </tiles:putAttribute>

	<tiles:putAttribute name="footer">
		<c:import url="/WEB-INF/views/template/footer.jsp"></c:import>
	</tiles:putAttribute>

</tiles:insertDefinition>