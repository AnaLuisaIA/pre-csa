<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@page pageEncoding="UTF-8" %>


<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="es" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title><fmt:message key="messages.title" /> | Seguros Argos Bienvenido</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport"/>
<meta content="Seguros Argos Web App" name="description"/>
<meta content="Seguros Argos Web" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/>
<link href="<c:url value='/assets/global/plugins/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value='/assets/global/plugins/simple-line-icons/simple-line-icons.min.css'/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value='/assets/global/plugins/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value='/assets/global/plugins/uniform/css/uniform.default.css'/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value='/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css'/>" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
<link href="<c:url value='/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css'/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value='/assets/global/plugins/fullcalendar/fullcalendar.min.css'/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value='/assets/global/plugins/jqvmap/jqvmap/jqvmap.css'/>" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL PLUGIN STYLES -->
<!-- BEGIN PAGE STYLES -->
<link href="<c:url value='/assets/admin/pages/css/tasks.css'/>" rel="stylesheet" type="text/css"/>
<script src="<c:url value='/assets/sac/js/autoNumeric.min.js'/>" type="text/javascript"></script>
<!-- END PAGE STYLES -->
<!-- BEGIN THEME STYLES -->

<link href="<c:url value='/assets/global/css/components-rounded.css'/>" id="style_components" rel="stylesheet" type="text/css"/>
<link href="<c:url value='/assets/global/css/plugins.css'/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value='/assets/admin/layout/css/layout.css'/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value='/assets/admin/layout/css/themes/argos.css'/>" rel="stylesheet" type="text/css" id="style_color"/>
<link href="<c:url value='/assets/admin/layout/css/custom.css'/>" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->

<tiles:insertAttribute name="styles" />

<link rel="shortcut icon" href="<c:url value='/assets/admin/layout/img/favicon.ico'></c:url>"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<!-- DOC: Apply "page-header-fixed-mobile" and "page-footer-fixed-mobile" class to body element to force fixed header or footer in mobile devices -->
<!-- DOC: Apply "page-sidebar-closed" class to the body and "page-sidebar-menu-closed" class to the sidebar menu element to hide the sidebar by default -->
<!-- DOC: Apply "page-sidebar-hide" class to the body to make the sidebar completely hidden on toggle -->
<!-- DOC: Apply "page-sidebar-closed-hide-logo" class to the body element to make the logo hidden on sidebar toggle -->
<!-- DOC: Apply "page-sidebar-hide" class to body element to completely hide the sidebar on sidebar toggle -->
<!-- DOC: Apply "page-sidebar-fixed" class to have fixed sidebar -->
<!-- DOC: Apply "page-footer-fixed" class to the body element to have fixed footer -->
<!-- DOC: Apply "page-sidebar-reversed" class to put the sidebar on the right side -->
<!-- DOC: Apply "page-full-width" class to the body element to have full width page without the sidebar menu -->
<body class="page-header-fixed page-quick-sidebar-over-content page-sidebar-closed-hide-logo page-container-bg-solid">
<!-- BEGIN HEADER -->
<div class="page-header navbar navbar-fixed-top">
	<!-- BEGIN HEADER INNER -->
	<div class="page-header-inner">
		<!-- BEGIN LOGO -->
		<div class="page-logo">
			<a href="<c:url value='/init'/>" >
			<img src="<c:url value='/assets/admin/layout/img/logo.png'/>" alt="logo" class="logo-default"/>
			</a>
			<div class="menu-toggler sidebar-toggler">
			</div>
		</div>
		
		<!-- END LOGO -->	
<!-- 		<form class="search-form" action="" method="GET"> -->
<!-- 			<div class="input-group"> -->
<!-- 				<input type="text" class="form-control" placeholder="Buscar..." name="q"> -->
<!-- 				<span class="input-group-btn"> -->
<!-- 				<a href="javascript:;" class="btn submit"><i class="icon-magnifier"></i></a> -->
<!-- 				</span> -->
<!-- 			</div> -->
<!-- 		</form> -->
		<!-- BEGIN RESPONSIVE MENU TOGGLER -->
		<a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
		</a>
		<!-- END RESPONSIVE MENU TOGGLER -->
		<!-- BEGIN TOP NAVIGATION MENU -->
			<c:import url="/WEB-INF/views/template/topmenu.jsp"></c:import>
		<!-- END TOP NAVIGATION MENU -->
	</div>
	<!-- END HEADER INNER -->
</div>
<!-- END HEADER -->
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN SIDEBAR -->
	<div class="page-sidebar-wrapper">
		<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
		<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
		<div class="page-sidebar navbar-collapse collapse">
			<c:import url="/WEB-INF/views/template/menu.jsp"></c:import>
			<!-- END SIDEBAR MENU -->
		</div>
	</div>
	<!-- END SIDEBAR -->
	<!-- BEGIN CONTENT -->
	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">Modal title</h4>
						</div>
						<div class="modal-body">
							 Widget settings form goes here
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue">Save changes</button>
							<button type="button" class="btn default" data-dismiss="modal">Close</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			
			<!-- BEGIN PAGE HEADER-->
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<c:url value="/"/>">Inicio</a>
						<i class="fa fa-angle-right"></i>
					</li>

					<tiles:insertAttribute name="nav" ignore="true" />

				</ul>

			</div>
			<h3 class="page-title">			
			<tiles:insertAttribute name="title" />			
			</h3>
			<!-- END PAGE HEADER-->	
			
			
			<tiles:insertAttribute name="body" />
			
			
			
			
<!-- 			<div class="clearfix"> -->
<!-- 			</div> -->
			
			
		</div>
	</div>
	<!-- END CONTENT -->
	<!-- BEGIN QUICK SIDEBAR -->
<%-- QMENU OCULTO	<c:import url="/WEB-INF/views/template/qmenu.jsp"></c:import> --%>
	<!-- END QUICK SIDEBAR -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<div class="page-footer">
	<tiles:insertAttribute name="footer">
		<c:import url="/WEB-INF/views/template/footer.jsp"></c:import>
	</tiles:insertAttribute>
</div>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="<c:url value='/assets/global/plugins/respond.min.js'/>"></script>
<script src="<c:url value='/assets/global/plugins/excanvas.min.js'/>"></script> 
<![endif]-->
<script src="<c:url value='/assets/global/plugins/jquery.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/assets/global/plugins/jquery-migrate.min.js'/>" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="<c:url value='/assets/global/plugins/jquery-ui/jquery-ui.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/assets/global/plugins/bootstrap/js/bootstrap.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/assets/global/plugins/jquery.blockui.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/assets/global/plugins/jquery.cokie.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/assets/global/plugins/uniform/jquery.uniform.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js'/>" type="text/javascript"></script>
<script src='<c:url value="/assets/global/plugins/bootbox/bootbox.min.js"></c:url>' type="text/javascript"></script>
<script src='<c:url value="/assets/global/plugins/bootbox/bootbox.locales.min.js"></c:url>' type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->

<!-- Agregar si se quiere el idle -->
<%-- <script src="<c:url value='/assets/global/plugins/jquery-idle-timeout/jquery.idletimeout.js'/>" type="text/javascript"></script> --%>
<%-- <script src="<c:url value='/assets/global/plugins/jquery-idle-timeout/jquery.idletimer.js'/>" type="text/javascript"></script> --%>

<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="<c:url value='/assets/global/scripts/metronic.js'/>" type="text/javascript"></script>
<script src="<c:url value='/assets/admin/layout/scripts/layout.js'/>" type="text/javascript"></script>
<!-- waiting for -->
	<script src="<c:url value='/assets/sac/js/bootstrap-waitingfor.min.js'/>"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<tiles:insertAttribute name="scripts" />
<script>
jQuery(document).ready(function() {
	
   Metronic.init({context : "<c:url value='/assets/'/>"});
   Layout.init(); 
   
   
   <tiles:insertAttribute name="ready" />
});


<tiles:insertAttribute name="functions" />
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
