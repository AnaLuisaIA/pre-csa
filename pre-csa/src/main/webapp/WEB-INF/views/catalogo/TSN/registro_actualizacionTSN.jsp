<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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
		<link
			href="<c:url value='/assets/global/plugins/select2/select2.css'/>"
			rel="stylesheet" type="text/css" />
		<link
			href="<c:url value='/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css'/>"
			rel="stylesheet" type="text/css" />
	</tiles:putAttribute>
	<tiles:putAttribute name="title">
		<c:if test="${empty tasa.id}">
			Alta de Tasas Sobre Nomina
		</c:if>
		<c:if test="${not empty tasa.id}">
			Edición de Tasa Sobre Nomina
		</c:if>
	</tiles:putAttribute>

	<!-- Menu de navegación -->
	<tiles:putAttribute name="nav">
		<li><a href='<c:url value="/tasa/"/>'>Alta</a> <i
			class="fa fa-angle-right"></i></li>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="row">

			<div class="col-md-10 col-md-offset-1">
				<!-- Cabecera de cuadro para formulario -->
				<div class="portlet box green-haze">
					<div class="portlet-title">
						<div class="caption">
							<h3>
								<c:if test="${empty tasa.id}">Alta de Tasas Sonbre Nomina | </c:if>
								<c:if test="${not empty tasa.id}">Edición Tasas Sobre Nomina | </c:if>
								<small class="form-text form-muted"> Los campos con *
									son obligatorios</small>
							</h3>
						</div>
						<div class="tools">
							<a href="" class="collapse"></a>
						</div>
					</div>

					<div class="portlet-body form">
						<!-- Comienza formulario para registro de variables-->
						<form:form id="saveTasa" action="save" method="POST"
							modelAttribute="tasa" rol="form">

							<div class="form-body">

								<!-- Mensaje de error, validación backend -->
								<!-- Bloques de campos de formulario a llenar -->
								<form:hidden path="id" />
								<div class="row">
									<div class="col-md-4">
									<c:if test="${empty tasa.id}"><input id="estatus" name="estatus" type="hidden" value="${1}" /></c:if>
										<c:if test="${not empty tasa.id}">
										<c:choose>
												<c:when test="${tasa.estatus == true}">
													<input id="estatus" name="estatus" type="hidden" value="${1}" />
												</c:when>
												<c:otherwise>
											 	<input id="estatus" name="estatus" type="hidden" value="${0}" />
												</c:otherwise>
											</c:choose>
										</c:if>
										
										<div class="form-group">
											<label for="estado">Estado: *</label>
											<c:if test="${empty tasa.id}"><form:input  path="estado" name="estado" class="form-control" placeholder="Ingrese el estado de la Tasa" /></c:if>
										<c:if test="${not empty tasa.id}">
										<form:input path="estado" name="estado" readonly="true"  class="form-control" placeholder="Ingrese el estado de la Tasa" />
										</c:if>
										</div>
									</div>
									<div class="col-md-4">
										<spring:bind path="tasa.tipoNomina">
											<div class="form-group">
												<label for="tipoNomina">Tipo Nomina: *</label>
												<form:select path="tipoNomina" required="true"
													class="form-control" id="tipoNominas">
													<form:option value="">Selecciona Un Tipo</form:option>
													<form:options items="${tipoNomina}"></form:options>
												</form:select>
												<form:errors path="tipoNomina" class="help-block"></form:errors>
											</div>
										</spring:bind>
									</div>
									<div class="col-md-4">
										<spring:bind path="tasa.tipoVariable">
											<div class="form-group">
												<label for="tipoVariable">Tipo Variable: *</label>
												<form:select path="tipoVariable" required="true"
													class="form-control" id="tipoVariable">
													<form:option value="">Selecciona Un Tipo</form:option>
													<form:options items="${tipoVariable}"></form:options>
												</form:select>
												<form:errors path="tipoVariable" class="help-block"></form:errors>
											</div>
										</spring:bind>
									</div>
								</div>
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<div class="form-group">
												<label for="valor">Valor: *</label>
												<form:input path="valor" name="valor" class="form-control"
													placeholder="Ingrese el valor de la Tasa" />
											</div>

										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">

											<div class="form-group">


												<div class="form-group">
													<label for="oficina">Oficina: *</label>
													<form:input path="oficina" name="oficina"
														class="form-control" placeholder="Ingrese la Oficina" />
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">

											<div class="form-group">


												<div class="form-group">
													<label for="fechaA">Fecha Aplicación: *</label>

													<div class="input-group input-medium date date-picker"
														data-date-format="dd/mm/yyyy" data-date-end-date="0d">
														<span class="input-group-btn">
															<button class="btn default" type="button">
																<i class="fa fa-calendar"></i>
															</button>
														</span>
														<form:input path="fechaAplicacion" type="text"
															class="form-control" readonly="true" maxlenght="10" />
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>

							</div>
							<div class="form-actions">
								<c:if test="${empty tasa.id}">
									<button type="submit" class="btn btn-primary"
										id="btnGuardarTasa">Guardar</button>
								</c:if>
								<c:if test="${not empty tasa.id}">
									<button type="submit" class="btn btn-primary"
										id="btnActualizarTasa">Actualizar</button>
								</c:if>
								<a href="../tasa/" id="cancelar" class="btn default">Cancelar</a>
							</div>
							<form:hidden path="justificacion" id="justificacionTasaForm" />
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>


	<tiles:putAttribute name="scripts">
		<script
			src="<c:url value='/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js'/>"></script>
		<script
			src="<c:url value='/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.es.min.js'/>"></script>
		<script
			src="<c:url value='/assets/global/plugins/bootstrap-select/bootstrap-select.min.js'/>"></script>
		<script
			src="<c:url value='/assets/global/plugins/select2/select2.min.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/scripts/jquery.spring-friendly.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/admin/pages/scripts/ui-idletimeout.js'/>"></script>

	</tiles:putAttribute>

	<tiles:putAttribute name="ready"> 
		$('#catalogos').addClass("start active open")
		$('#catalogosMenu').addClass("active");
		
		$('.date-picker').datepicker({
			autoclose: true,
			language: 'es'
		});
		
		$('#cancelar').click(function(e){
			e.preventDefault();
			var linkRedireccion = $(this).attr("href")
		
			bootbox.setLocale('es');
			bootbox.confirm({
				message: "No se guardará información de la Tasa. ¿Desea Cancelar?",
				callback: function(result){
					if(result){
						window.location.href = linkRedireccion;
					}
				}
			});
		});
		
		$('#btnGuardarTasa').click(function(e) {
        	e.preventDefault();
			mensaje = '';
			 
		    if ($("#es_colaborador").val() == '1') {	
		        if($("#inputNumColaborador").val() === ''){
		        	mensaje+= "El campo de <strong>Colaborador</strong> está vacío.<br>"
		        };
		    }
		    else {
		    

		    	if($("#estado").val() == ''){
		    		mensaje+= "El campo de <strong>estado</strong> está vacío.<br>"
		    	};
		    	
		    	if($("#tipoNomina").val() == ''){
		    		mensaje+= "El campo de <strong>Tipo Nomina</strong> está vacío.<br>"
		    	};
		    	
		    	if($("#tipoVariable").val() == ''){
		    		mensaje+= "El campo de <strong>Tipo Variable</strong> está vacío.<br>"
		    	};
		    	
		    	if($("#fechaAplicacion").val() == ''){
		    		mensaje+= "El campo de <strong>Fecha Aplicacion</strong> está vacío.<br>"
		    	};
		    	
		    	if($("#valor").val() == ''){
		    		mensaje+= "El campo de <strong>Valor</strong> está vacío.<br>"
		    	};
		    	
		    	if($("#oficina").val() == ''){
		    		mensaje+= "El campo de <strong>Oficina</strong> está vacío.<br>"
		    	};
}

			if(mensaje != ''){
				bootbox.alert(mensaje);
			} else {
		        bootbox.confirm({
		        	title: "Registrar Tasa",
			        message: "¿Está seguro de que desea continuar?",
			        buttons: {
			        	cancel: {
				            label: '<i class="fa fa-times"></i> Regresar'
				        },
				        confirm: {
				            label: '<i class="fa fa-check"></i> Confirmar'
				        }
			        },	   
			        callback: function(result){
				     if(result){	
				        		bootbox.prompt({
				    title: "Escriba Justificacion",
				    inputType: 'textarea',
				    callback: function (result) {
				    	if(result != null && result != ""){
					     $('#justificacionTasaForm').val(result);
					       $('#saveTasa').submit();
					    } else if(result === "") {
					    	bootbox.alert({
							   message: "<b>El campo de Justificacion es obligatorio.</b>",
							   size: 'small'
							});
					    }
				    }
				});			   
			       			
		        		}
		        	}
		        });
			}

        });
        
     $('#btnActualizarTasa').click(function(e) {
        	e.preventDefault();
			mensaje = '';
			 
		    if ($("#es_colaborador").val() == '1') {	
		        if($("#inputNumColaborador").val() === ''){
		        	mensaje+= "El campo de <strong>Colaborador</strong> está vacío.<br>"
		        };
		    }
		    else {
		    

		    	if($("#estado").val() == ''){
		    		mensaje+= "El campo de <strong>estado</strong> está vacío.<br>"
		    	};
		    	
		    	if($("#tipoNomina").val() == ''){
		    		mensaje+= "El campo de <strong>Tipo Nomina</strong> está vacío.<br>"
		    	};
		    	
		    	if($("#tipoVariable").val() == ''){
		    		mensaje+= "El campo de <strong>Tipo Variable</strong> está vacío.<br>"
		    	};
		    	
		    	if($("#fechaAplicacion").val() == ''){
		    		mensaje+= "El campo de <strong>Fecha Aplicacion</strong> está vacío.<br>"
		    	};
		    	
		    	if($("#valor").val() == ''){
		    		mensaje+= "El campo de <strong>Valor</strong> está vacío.<br>"
		    	};
		    	
		    	if($("#oficina").val() == ''){
		    		mensaje+= "El campo de <strong>Oficina</strong> está vacío.<br>"
		    	};
}

			if(mensaje != ''){
				bootbox.alert(mensaje);
			} else {
		        bootbox.confirm({
		        	title: "Actualizar Tasa",
			        message: "¿Está seguro de que desea continuar?",
			        buttons: {
			        	cancel: {
				            label: '<i class="fa fa-times"></i> Regresar'
				        },
				        confirm: {
				            label: '<i class="fa fa-check"></i> Confirmar'
				        }
			        },	   
			        callback: function(result){
				     if(result){	
				        		bootbox.prompt({
				    title: "Escriba Justificacion",
				    inputType: 'textarea',
				    callback: function (result) {
				    	if(result != null && result != ""){
					     $('#justificacionTasaForm').val(result);
					       $('#saveTasa').submit();
					    } else if(result === "") {
					    	bootbox.alert({
							   message: "<b>El campo de Justificacion es obligatorio.</b>",
							   size: 'small'
							});
					    }
				    }
				});			   
			       			
		        		}
		        	}
		        });
			}

        });
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