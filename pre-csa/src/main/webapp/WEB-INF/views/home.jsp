<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<tiles:insertDefinition name="defaultTemplate">

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
					<div class="portlet-body">
						<div class="row">
							<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 col-offset-lg-2">
								<a href="<c:url value='/calculos/imss'/>" 
									class="dashboard-stat dashboard-stat-v2 grey-cascade">
									<div class="visual">
										<i class="fa fa-calculator"></i>
									</div>
									<div class="details">
										<div class="number">
											<span>Iniciar cálculos</span>
										</div>
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>

	<tiles:putAttribute name="scripts">
		<script type="text/javascript"
			src="<c:url value='/assets/global/scripts/jquery.spring-friendly.js'/>"></script>
	</tiles:putAttribute>

	<tiles:putAttribute name="ready"> 
			$('#calculosMain').addClass("start active open")
			$('#calculoMenu').addClass("active");

			$('#calculosMenu').addClass("active");
	
	</tiles:putAttribute>

	<tiles:putAttribute name="footer">
		<c:import url="/WEB-INF/views/template/footer.jsp"></c:import>
	</tiles:putAttribute>


</tiles:insertDefinition>