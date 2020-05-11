<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page pageEncoding="UTF-8" %>
 
<tiles:insertDefinition name="defaultTemplate">
	
	<tiles:putAttribute name="styles">
		<link href="<c:url value='/assets/global/plugins/datatables/extensions/Scroller/css/dataTables.scroller.min.css'/>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value='/assets/global/plugins/datatables/extensions/ColReorder/css/dataTables.colReorder.min.css'/>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value='/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css'/>" rel="stylesheet" type="text/css"/>		
	</tiles:putAttribute>
	
	<tiles:putAttribute name="nav">
		<li><a href='<c:url value="/"/>'>Variablables IMSS e INFONAVIT</a> <i class="fa fa-angle-right"></i></li>	
	</tiles:putAttribute>

	<tiles:putAttribute name="title">Variablables IMSS e INFONAVIT</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		<div class="row">

			<div class="col-md-12">
				<div class="portlet box green-jungle">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>Catálogo de Variablables IMSS e INFONAVIT
						</div>
						<div class="actions">
						
						</div>
						
					</div>
					<div class="portlet-body">
						<table id="tablaVariables" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>Variable</th>
									<th>Descripcion</th>
									<th>Valor</th>
									<th>Tipo</th>
									<th>Fecha de Aplicación</th>
									<th>Acciones</th>
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
	
		<script type="text/javascript" src="<c:url value='/assets/global/plugins/datatables/media/js/jquery.dataTables.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/assets/global/plugins/datatables/extensions/TableTools/js/dataTables.tableTools.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/assets/global/plugins/datatables/extensions/ColReorder/js/dataTables.colReorder.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/assets/global/plugins/datatables/extensions/Scroller/js/dataTables.scroller.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/assets/admin/pages/scripts/argostable.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/assets/global/scripts/jquery.spring-friendly.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/assets/admin/pages/scripts/ui-idletimeout.js'/>"></script>
		

	</tiles:putAttribute>
	
		
	<tiles:putAttribute name="ready"> 
		$('#catalogos1').addClass("start active open");
		$('#tablaVariables').DataTable();
		$('#catalogosMenu').addClass("active");
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