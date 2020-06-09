<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
		<li><a href='<c:url value="/"/>'>Consultas IMSS e INFONAVIT</a> <i
			class="fa fa-angle-right"></i></li>
	</tiles:putAttribute>
	<meta charset="UTF-8">
	<tiles:putAttribute name="title">Consultas IMSS e INFONAVIT</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="row">

			<div class="col-md-12">
				<div class="portlet box green-jungle">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>Consultas IMSS e INFONAVIT
						</div>
						<div class="actions">
							<a href="alta" class="btn default green-stripe"> <i
								class="fa fa-plus"></i> <span class="hidden-480">Exportar
									Seleccionados</span>
							</a>
						</div>
					</div>
					<div class="portlet-body">
						<table id="tablaConsultaII"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>Seleccionar Todos</th>
									<th>Período</th>
									<th>Total Pagado Patrón</th>
									<th>Total Trabajador</th>
									<th>Total IMSS</th>
									<th>INFONAVIT Patrón</th>
									<th>INFONAVIT Trabajador</th>
									<th>Total INFONAVIT</th>
									<th>Fecha de Cálculo</th>
									<th>Calculado por</th>
								</tr>
							<tbody>
								<!--<c:forEach var="c" items="${correos}">
									<tr>
										<td>${c.actividad.label}</td>
										<td>${c.asunto}</td>
										<td><a href="editar?id=${c.id}" class="btn btn-primary btn-small">
												<i class="fa fa-edit"></i></a></td>
									</tr>
								</c:forEach>-->
							</tbody>

						</table>
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
		$('#calculosMain').addClass("start active open");
		$('#tablaConsultaII').DataTable();
		$('#consultaIMSS').addClass("active");
   </tiles:putAttribute>

	<tiles:putAttribute name="footer">
		<c:import url="/WEB-INF/views/template/footer.jsp"></c:import>
	</tiles:putAttribute>

</tiles:insertDefinition>