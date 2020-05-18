<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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
		<link
			href="<c:url value='/assets/global/plugins/select2/select2.css'/>"
			rel="stylesheet" type="text/css" />
	</tiles:putAttribute>

	<tiles:putAttribute name="nav">
		<li><a href='<c:url value="/calendario/"/>'>Calendario ISN</a> <i
			class="fa fa-angle-right"></i></li>
	</tiles:putAttribute>

	<tiles:putAttribute name="title">Calendario ISN</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<%@include file="../../secciones/messages.jsp"%>
		<div class="row">

			<div class="col-md-12">
				<div class="portlet box green-jungle">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-calendar"></i>Catálogo de Calendarios ISN
						</div>
						<div class="actions">
							<a class="btn default btn-sm green-stripe"
								href="<c:url value='/calendario/agregar'/>"><i
								class="fa fa-plus"></i> Agregar Calendario</a>
						</div>

					</div>
					<div class="portlet-body">

						<div align="right">
							<form:form id="buscarCalendario"
								modelAttribute="buscarCalendario" method="POST"
								class="form-inline" action="buscar">

								<!-- Mensaje de error, validación backend -->
								<spring:hasBindErrors name="usuario">
									<div class="alert alert-danger" style="display: block;">
										<button class="close" data-close="alert"></button>
										<p>ERROR: Favor de verificar campos</p>
									</div>
								</spring:hasBindErrors>

								<spring:bind path="buscarCalendario.filtroAnio">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<form:select path="filtroAnio"
											class="form-control select2-container select2me">
											<form:option value=""></form:option>
											<form:options items="${anios}" />
										</form:select>
										<form:errors path="filtroAnio" class="help-block"></form:errors>
									</div>
								</spring:bind>
								<button type="submit" class="btn default green">Buscar</button>

							</form:form>
						</div>
						<br>
						<table id="tablaCalendario"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>Año</th>
									<th>Semana</th>
									<th>Del</th>
									<th>Al</th>
									<th>Mes</th>
									<th>Trimestre</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="f" items="${fechas}">
									<tr>
										<td>${f.anio}</td>
										<td>${f.semana}</td>
										<td><fmt:formatDate value="${f.fechaInicio}"
												pattern="dd/MM/yyyy" /></td>
										<td><fmt:formatDate value="${f.fechaFin}"
												pattern="dd/MM/yyyy" /></td>
										<td>${f.mes}</td>
										<td>${f.trimestre}</td>
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

		<script
			src="<c:url value='/assets/global/plugins/bootstrap-select/bootstrap-select.min.js'/>"></script>
		<script
			src="<c:url value='/assets/global/plugins/select2/select2.min.js'/>"></script>
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
		$('#calendarioMenu').addClass("active");
		$('#tablaCalendario').DataTable();
		
		$('#filtroAnio').select2({
			placeholder: "Selecciona un año",
	   		allowClear: true
		});
		
   </tiles:putAttribute>

	<tiles:putAttribute name="footer">
		<c:import url="/WEB-INF/views/template/footer.jsp"></c:import>
	</tiles:putAttribute>

</tiles:insertDefinition>