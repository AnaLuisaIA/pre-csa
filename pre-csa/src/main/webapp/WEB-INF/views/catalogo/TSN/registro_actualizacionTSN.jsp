<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			Alta de Tasas Sobre Nómina
		</c:if>
		<c:if test="${not empty tasa.id}">
			Edición de Tasa Sobre Nómina
		</c:if>
	</tiles:putAttribute>

	<!-- Menu de navegación -->
	<tiles:putAttribute name="nav">
		<li><a href='<c:url value="/tasas/"/>'>Alta</a> <i
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
								<c:if test="${empty tasa.id}">Alta de Tasas Sobre Nómina | </c:if>
								<c:if test="${not empty tasa.id}">Edición Tasas Sobre Nómina | </c:if>
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
								<spring:hasBindErrors name="tasa">
									<div class="alert alert-danger" style="display: block;">
										<button class="close" data-close="alert"></button>
										<p>ERROR: Favor de verificar campos</p>
									</div>
								</spring:hasBindErrors>
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
										<spring:bind path="tasa.estado">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label for="estado">Estado: *</label>
											<c:if test="${empty tasa.id}"><form:input  path="estado" name="estado" class="form-control" placeholder="Ingrese el estado de la Tasa" /></c:if>
											<form:errors path="estado" class="help-block"></form:errors>
											<c:if test="${not empty tasa.id}"><form:input path="estado" name="estado" readonly="true"  class="form-control" placeholder="Ingrese el estado de la Tasa" /></c:if>
										</div>
										</spring:bind>
									</div>
									<div class="col-md-4">
										<spring:bind path="tasa.tipoNomina">
											<div class="form-group">
												<label for="tipoNomina">Tipo Nomina: *</label>
												<form:select path="tipoNomina" required="true"
													class="form-control" id="tipoNomina">
													<form:option value=""></form:option>
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
													<form:option value=""></form:option>
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
												<form:input path="valor" name="valor" class="form-control" step="0.000001" type="number"
													 />
											</div>

										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<div class="form-group">
												<div class="form-group">
													<label for="oficina">Oficina: *</label>
													<form:select path="oficina" id="oficina" multiple="true" required="true" class="form-control">
														<form:options items="${ciudad}"/>
													</form:select>
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
								<a href="../tasas/" id="cancelar" class="btn default">Cancelar</a>
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
		
		$('#tipoNomina').select2({
			placeholder: "Seleccione el tipo de nómina",
			allowClear: true,
               escapeMarkup: function (m) {
               		return m;
            }				 
		});	
		
		$('#tipoVariable').select2({
			placeholder: "Seleccione el tipo de variable",
			allowClear: true,
               escapeMarkup: function (m) {
               		return m;
            }				 
		});	
		
		$("#oficina").select2();
		
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
		    	}else{
		    		if($("#valor").val() < 0 ){
		    		mensaje+= "El campo de <strong>Valor</strong> no puede contener numeros negativos<br>"
		    	};
		    	
		    	}
		    	
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
				    title: "Escriba Justificación",
				    inputType: 'textarea',
				    callback: function (result) {
				    	if(result != null && result != ""){
					     $('#justificacionTasaForm').val(result);
					       $('#saveTasa').submit();
					    } else if(result === "") {
					    	bootbox.alert({
							   message: "<b>El campo de Justificación es obligatorio.</b>",
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
		    	}else{
		    		if($("#valor").val() < 0 ){
		    		mensaje+= "El campo de <strong>Valor</strong> no puede contener numeros negativos<br>"
		    	};
		    	
		    	}
		    	
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
				    title: "Escriba Justificación",
				    inputType: 'textarea',
				    callback: function (result) {
				    	if(result != null && result != ""){
					     $('#justificacionTasaForm').val(result);
					       $('#saveTasa').submit();
					    } else if(result === "") {
					    	bootbox.alert({
							   message: "<b>El campo de Justificación es obligatorio.</b>",
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