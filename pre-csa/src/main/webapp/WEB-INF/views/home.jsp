<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@page pageEncoding="UTF-8" %>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="styles">
		<link href="<c:url value='/assets/global/plugins/datatables/extensions/Scroller/css/dataTables.scroller.min.css'/>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value='/assets/global/plugins/datatables/extensions/ColReorder/css/dataTables.colReorder.min.css'/>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value='/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css'/>" rel="stylesheet" type="text/css"/>		
	</tiles:putAttribute>
	<tiles:putAttribute name="title">Bienvenido</tiles:putAttribute>
	
	<tiles:putAttribute name="nav">
		<li><a href='<c:url value="/"/>'>Cálculos</a> <i class="fa fa-angle-right"></i></li>	
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		<div class="row">

			<div class="col-md-12">
				<div class="portlet box green-jungle">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>Realizar cálculos de carga social
						</div>
						<!-- <div class="actions">
							<a href="" class="btn default green-stripe">
							<i class="fa fa-plus"></i>
							<span class="hidden-480">
							Nueva </span>
							</a>								
						</div> -->
						
					</div>
					<div class="portlet-body">

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
			$('#solicitudesMain').addClass("start active open")
			$('#tablaSolicitudes').DataTable();
			$('#solicitudesMenu').addClass("active");
		</tiles:putAttribute>
	
	<tiles:putAttribute name="footer">
		<div class="page-footer-inner">							  
			2020 &copy; <a href="http://www.segurosargos.com/" title="Seguros Argos" target="_blank">Seguros Argos</a>
		</div>
		<div class="scroll-to-top">
			<i class="icon-arrow-up"></i>
		</div>
	</tiles:putAttribute>
	
	
</tiles:insertDefinition>