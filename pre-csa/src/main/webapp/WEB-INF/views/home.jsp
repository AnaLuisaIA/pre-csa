<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="styles">
		<link
			href="<c:url value='/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css'/>"
			rel="stylesheet" type="text/css" />
	</tiles:putAttribute>

	<tiles:putAttribute name="title">Bienvenido</tiles:putAttribute>

	<tiles:putAttribute name="nav">
		<li><a href='<c:url value="/calculos/"/>'>Cálculos</a> <i
			class="fa fa-angle-right"></i></li>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="row">

			<div class="col-md-12">
				<div class="portlet box green-jungle">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-calculator"></i>Cálculos de Carga Social
						</div>
						<!-- <div class="actions">
							<a href="" class="btn default green-stripe">
							<i class="fa fa-plus"></i>
							<span class="hidden-480">
							Nueva </span>
							</a>								
						</div> -->

					</div>
					<div class="portlet-body"></div>


				</div>
			</div>
		</div>
	</tiles:putAttribute>

	<tiles:putAttribute name="scripts">
		<script type="text/javascript"
			src="<c:url value='/assets/global/scripts/jquery.spring-friendly.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/admin/pages/scripts/ui-idletimeout.js'/>"></script>
	</tiles:putAttribute>

	<tiles:putAttribute name="ready"> 
			$('#calculosMain').addClass("start active open")
			$('#calculosMenu').addClass("active");
	
	</tiles:putAttribute>

	<tiles:putAttribute name="footer">
		<c:import url="/WEB-INF/views/template/footer.jsp"></c:import>
	</tiles:putAttribute>


</tiles:insertDefinition>