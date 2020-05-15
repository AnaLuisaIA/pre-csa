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
									<th>Variable</th>
									<th>Descripcion</th>
									<th>Valor</th>
									<th>Tipo</th>
									<th>Fecha de Aplicación</th>
									<th>Estado</th>
									<th>Acciones</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="v" items="${variable}">
									<tr>
										<td>${v.nombre}</td>
										<td>${v.descripcion}</td>
										<td>${v.valor}</td>
										<td>${v.tipo}</td>
										<td><fmt:formatDate value="${v.fechaAplicacion}"
												pattern="dd/MM/yyyy" /></td>
										<td><c:choose>
												<c:when test="${v.estado == true}">1</c:when>
												<c:otherwise>0</c:otherwise>
											</c:choose></td>
										<td>
											<c:choose>
												<c:when test="${v.estado == true}">
													<a onclick="return confirm('¿Deseas desactivar esta Variable?');" href="editarEstado?id=${v.id}" class="btn btn-primary btn-small" style="color:#06F61C;">
											 			<i class="fa fa-power-off"></i>
													</a>
												</c:when>
												<c:otherwise>
													<a href="editarEstado?id=${v.id}" onclick="return confirm('¿Deseas activar esta Variable?');"  class="btn btn-primary btn-small" style="color:#F60606;">
											 			<i class="fa fa-power-off"></i>
													</a>
												</c:otherwise>
											</c:choose>

											<a href="editar?id=${v.id}" class="btn btn-primary btn-small"> 
												<i class="fa fa-edit"></i>
											 </a>
											<a onclick="return confirm('¿Deseas Eliminar la Variable?');" href="delete?id=${v.id}" class="btn btn-primary btn-small"> 
												<i class="fa fa-remove (alias)"></i>
											</a>
										</td>
									</tr>

								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-9 col-md-offset-2">
				<div class="portlet light portlet-fit bordered">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-pencil-square-o"></i>Bitácora de cambios
						</div>
						<div class="tools">
							<a href="" class="expand"></a>
						</div>
					</div>

					<div class="portlet-body portlet-collapsed" style="display: none;">
						<div
							class="table-scrollable table-scrollable-borderless table-responsive">
							<table id="tablaBitacora" class="table table-hover table-light">
								<thead>
									<tr>
										<th>Acción</th>
										<th>Fecha y Hora</th>
										<th>Colaborador</th>
									</tr>
								</thead>


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
		$('#catalogosMenu').addClass("active");
		
		$(function() {

  $('#btnSi, #btnNo').on('click', function() {
    var elem = $(this);
    var activo = elem.text();
    var id = elem.val();
    var url = 'activa-alerta.php';

    var request = $.ajax({
      data: {id: id, activo:activo},
      type: "POST",
      url: url,
      dataType: "html"
    });

    request.done(function(text) {
      console.log(text);
      if (text == "exitoso") {
        if (activo == 'S') {
          elem.removeClass('btn-danger').addClass('btn-success');
          elem.text('S');
        } else {
          elem.removeClass('btn-success').addClass('btn-danger');
          elem.text('N');
        }
      }
    });

    request.fail(function(jqXHR, textStatus) {
      alert("Error de petición: " + textStatus);
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