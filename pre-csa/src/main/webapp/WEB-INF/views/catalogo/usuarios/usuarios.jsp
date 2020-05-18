<%@ page language="java" contentType="text/html; charset=UTF-8"
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
		<li><a href='<c:url value="/usuarios/"/>'>Usuarios</a> <i
			class="fa fa-angle-right"></i></li>
	</tiles:putAttribute>


	<tiles:putAttribute name="title">Usuarios</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<%@include file="../../secciones/messages.jsp"%>
		<div class="row">

			<div class="col-md-12">
				<div class="portlet box green-jungle">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>Catálogo de Usuarios
						</div>
						<div class="actions">
							<a href="alta" class="btn default green-stripe"> <i
								class="fa fa-plus"></i> <span class="hidden-480"> AGREGAR
									USUARIO </span>
							</a>
						</div>
					</div>
					<div class="portlet-body">
						<table id="tablaUsuarios"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>Número de Colaborador</th>
									<th>Estado</th>
									<th>Nombre Completo</th>
									<th>Editar</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="u" items="${usuarios}">
									<tr>
										<td>${u.numColaborador}</td>
										<td><c:if test="${u.estado}">
												ACTIVO </c:if> <c:if test="${not u.estado}">
												INACTIVO </c:if></td>
										<td>${u.nombres} ${u.aPaterno} ${u.aMaterno}</td>
										<td><a href="editar?id=${u.id}"
											class="btn btn-primary btn-small"> <i class="fa fa-edit"></i></a></td>
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
								<tbody>
									<c:forEach var="a" items="${acciones}">
										<tr>
											<td>${a.accion}</td>
											<td><fmt:formatDate value="${a.fechaAccion}" type="both"
													dateStyle="short" timeStyle="short" /></td>
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
			src="<c:url value='/assets/global/scripts/jquery.spring-friendly.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/admin/pages/scripts/ui-idletimeout.js'/>"></script>


	</tiles:putAttribute>


	<tiles:putAttribute name="ready"> 
		$('#catalogos').addClass("start active open");
		$('#usuariosMenu').addClass("active");
		
		$('#tablaUsuarios').DataTable();
   </tiles:putAttribute>

	<tiles:putAttribute name="footer">
		<c:import url="/WEB-INF/views/template/footer.jsp"></c:import>
	</tiles:putAttribute>

</tiles:insertDefinition>