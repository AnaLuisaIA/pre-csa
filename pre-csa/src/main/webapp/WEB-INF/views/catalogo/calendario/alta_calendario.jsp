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
							<a class="btn default btn-sm"
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
										<form:input type="file" path="archivo" accept=".xls, .xlsx"
											class="form-control-file" required="true" />
									</div>
								</div>
							</div>

							<div class="form-actions">
								<div class="row">
									<div class="col-md-offset-5 col-md-5">
										<button type="submit" class="btn btn-primary" id="btnGuardar">Guardar</button>
										<a href="<c:url value="/calendario/"/>" type="button"
											class="btn default">Cancelar</a>
									</div>
								</div>
							</div>

							<form:hidden path="anio" />
							<form:hidden path="fileName" />
						</form:form>
					</div>


				</div>
			</div>
		</div>
	</tiles:putAttribute>

	<tiles:putAttribute name="ready"> 
		$('#catalogos').addClass("start active open")
		$('#calendarioMenu').addClass("active");
		
		$('#btnGuardar').click(function(e){
			e.preventDefault();
			
			var nombreArchivo = $('#archivo')[0].files[0]['name'];
			var anio = nombreArchivo.substring(11,15);
			
			if(/^(19|20)\d{2}$/.test(anio) === true){
				bootbox.setLocale('es');
				bootbox.confirm({
			        message: "<b>¿Está seguro de cargar el calendario para el año " + anio + "?</b>",
			        callback: function(result){
				        if(result){
				        	$('#anio').val(anio);
				        	$('#fileName').val(nombreArchivo);
			       			$('#formCalendario').submit();
		        		}
		        	}
	       		});
	       		
			} else {
				bootbox.setLocale('es');
				bootbox.alert("<b>El nombre del archivo es incorrecto. Debe contener el año del calendario.</b>");
			}
			
		});
		
	</tiles:putAttribute>

	<tiles:putAttribute name="footer">
		<c:import url="/WEB-INF/views/template/footer.jsp"></c:import>
	</tiles:putAttribute>

</tiles:insertDefinition>