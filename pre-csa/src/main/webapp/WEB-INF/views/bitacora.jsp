<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<%@page pageEncoding="UTF-8" %>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="styles">
		<link
			href="<c:url value='/assets/global/plugins/datatables/extensions/Scroller/css/dataTables.scroller.min.css'/>"
			rel="stylesheet" type="text/css" />
		<link
			href="<c:url value='/assets/global/plugins/datatables/extensions/ColReorder/css/dataTables.colReorder.min.css'/>"
			rel="stylesheet" type="text/css" />
		<link href="<c:url value='/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css'/>"
			rel="stylesheet" type="text/css" />
		<link href="<c:url value='/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css'/>"
			rel="stylesheet" type="text/css" />

	</tiles:putAttribute>
	<tiles:putAttribute name="title">Bitácora de CSA</tiles:putAttribute>

	<tiles:putAttribute name="nav">
		<li><a href='<c:url value="/bitacora/"/>'>Bitácora</a> <i class="fa fa-angle-right"></i></li>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="row">

			<div class="col-md-12">
				<div class="portlet box green-jungle">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>Bitácora de acciones en CSA 
						</div>

					</div>
					<div class="portlet-body">

						<div align="right">
							<form:form id="buscarBitacora" modelAttribute="bitacora" method="POST"
								class="form-inline" action="buscarBitacora">

								<div class="form-group">
									<label>Del: </label>
									<div class="input-group input-medium date date-picker" data-date-format="dd/mm/yyyy"
										data-date-end-date="0d">
										<span class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
										<form:input path="fechaInicio" type="text" class="form-control" readonly="true"
											maxlenght="10" placeholder="Del..." />
									</div>

									<label>Al: </label>
									<div class="input-group input-medium date date-picker" data-date-format="dd/mm/yyyy"
										data-date-end-date="0d">
										<span class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
										<form:input path="fechaFin" type="text" class="form-control" readonly="true"
											maxlenght="10" placeholder="Al..." />
									</div>
								</div>

								<button type="submit" class="btn default green">Buscar</button>
							</form:form>
						</div>

						<br>

						<table id="tablaBitacora" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>Fecha de acción</th>
									<th>Acción en el sistema</th>
									<th>Usuario</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="a" items="${acciones}">
									<tr>
										<td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" 
											value="${a.fecha}" /></td>
										<td>${a.accion}</td>
										<td>${a.numColaborador} - ${a.nombres} ${a.aPaterno} ${a.aMaterno}</td>
								</c:forEach>
							</tbody>

						</table>
					</div>


				</div>
			</div>
		</div>
	</tiles:putAttribute>

	<tiles:putAttribute name="scripts">

		<script src="<c:url value='/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js'/>">
		</script>
		<script
			src="<c:url value='/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.es.min.js'/>">
		</script>
		<script src="<c:url value='/assets/global/plugins/bootstrap-select/bootstrap-select.min.js'/>"></script>
		<script src="<c:url value='/assets/global/plugins/select2/select2.min.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/plugins/datatables/media/js/jquery.dataTables.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/plugins/datatables/extensions/TableTools/js/dataTables.tableTools.min.js'/>">
		</script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/plugins/datatables/extensions/ColReorder/js/dataTables.colReorder.min.js'/>">
		</script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/plugins/datatables/extensions/Scroller/js/dataTables.scroller.min.js'/>">
		</script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/assets/global/scripts/jquery.spring-friendly.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/assets/admin/pages/scripts/ui-idletimeout.js'/>"></script>

	</tiles:putAttribute>

	<tiles:putAttribute name="ready">
		$('#bitacora').addClass("start active open")
		$('#bitacoraMenu').addClass("active");

		$('#tablaBitacora').DataTable();
		
		$('.date-picker').datepicker({
		autoclose:true,
		language:'es'
		});

	</tiles:putAttribute>

	<tiles:putAttribute name="footer">
		<div class="page-footer-inner">
			2019 &copy; <a href="http://www.segurosargos.com/" title="Seguros Argos" target="_blank">Seguros Argos</a>
		</div>
		<div class="scroll-to-top">
			<i class="icon-arrow-up"></i>
		</div>
	</tiles:putAttribute>


</tiles:insertDefinition>