<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
		<li><a href='<c:url value="/"/>'>Calendario ISN</a> <i
			class="fa fa-angle-right"></i></li>
	</tiles:putAttribute>
	<meta charset="UTF-8">
	<tiles:putAttribute name="title">Calendario ISN</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="row">

			<div class="col-md-12">
				<div class="portlet box green-jungle">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>Catálogo Calendario ISN
						</div>
						<div class="actions">
							<a href="alta" class="btn default green-stripe"> <i
								class="fa fa-plus"></i> <span class="hidden-480"> AGREGAR
									CALENDARIO </span>
							</a>
						</div>
					</div>
					<div class="portlet-body">
						<table id="tablaVariables"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th rowspan="1" width="20px" height="20px">Semana</th>
									<th colspan="2" style="width:25px">Periodo</th>
									<th style="width:25px">Mes</th>
									<th style="width:25px">Trimestre</th>
								</tr>
								<tr>
									<th width="20px" height="20px"></th>
									<th style="width:50px">Del</th>
									<th style="width:50px">Al</th>
									<th style="width:25px"></th>
								</tr>
							</thead>
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
		$('#catalogos1').addClass("start active open");
		$('#tablaVariables').DataTable();
		$('#catalogosMenu').addClass("active");
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