<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="title">Agregar Calendario</tiles:putAttribute>

	<tiles:putAttribute name="nav">
		<li><a href='<c:url value="/calendario/"/>'>Calendario ISN</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a href='<c:url value="/calendario/agregar"/>'>Nuevo</a> <i
			class="fa fa-angle-right"></i></li>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<%@include file="../../secciones/messages.jsp"%>
		<div class="row">

			<div class="col-md-12">
				<div class="portlet box green-jungle">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-file"></i>Cargar Calendario ISN
						</div>
						<div class="actions">
							<a class="btn default btn-sm green-stripe"
								href="<c:url value='/calendario/layout'/>"><i
								class="fa fa-arrow-circle-o-down"></i> Descargar Layout</a>
						</div>

					</div>
					<div class="portlet-body form">
						<form:form method="POST" modelAttribute="calendario"
							enctype="multipart/form-data" action="add"
							class="form-horizontal" id="formCalendario">

							<div class="form-body">
								<div class="form-group">
									<label class="col-md-5 control-label">Selecciona el
										archivo que contiene el calendario a importar</label>

									<div class="col-md-6 col-md-offset-1">
										<form:input type="file" path="archivo" 
											accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
											class="form-control-file" required="true" name="archivo"/>
									</div>
								</div>
							</div>

							<div class="form-actions">
								<div class="row">
									<div class="col-md-offset-5 col-md-5">
										<button type="submit" class="btn btn-primary" id="btnGuardar">Guardar</button>
										<a href="<c:url value="/calendario/"/>" type="button" id="cancelar"
											class="btn default">Cancelar</a>
									</div>
								</div>
							</div>

							<form:hidden path="anio" />
							<form:hidden path="fileName" />
							<form:hidden path="nuevo" />
						</form:form>
					</div>


				</div>
			</div>
		</div>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="scripts">
		<script src="<c:url value='/assets/global/plugins/jquery-validation/js/jquery.validate.min.js'/>" type="text/javascript"></script>
		<script src="<c:url value='/assets/global/plugins/jquery-validation/js/additional-methods.min.js'/>" type="text/javascript"></script>
		<script src="<c:url value='/assets/csa/js/alta.calendario.js'/>" type="text/javascript"></script>
	</tiles:putAttribute>

	<tiles:putAttribute name="ready"> 
		$('#catalogos').addClass("start active open")
		$('#calendarioMenu').addClass("active");
		
		$('#cancelar').click(function(e){
			e.preventDefault();
			var linkRedireccion = $(this).attr("href")
		
			bootbox.setLocale('es');
			bootbox.confirm({
				message: "No se guardará información del Calendario. ¿Desea Cancelar?",
				callback: function(result){
					if(result){
						window.location.href = linkRedireccion;
					}
				}
			});
		});
		
	</tiles:putAttribute>

	<tiles:putAttribute name="footer">
		<c:import url="/WEB-INF/views/template/footer.jsp"></c:import>
	</tiles:putAttribute>

</tiles:insertDefinition>