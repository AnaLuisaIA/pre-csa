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
		<c:if test="${empty variable.id}">
			Alta de Variables IMSS/INFONAVIT
		</c:if>
		<c:if test="${not empty variable.id}">
			Edición de Variables IMSS/INFONAVIT
		</c:if>
	</tiles:putAttribute>

	<!-- Menu de navegación -->
	<tiles:putAttribute name="nav">
		<li><a href='<c:url value="/variables/"/>'>Variables IMSS e
				INFONAVIT</a> <i class="fa fa-angle-right"></i></li>
		<c:if test="${empty variable.id}">
			<li><a href='<c:url value="/variables/alta"/>'>Alta</a> <i
				class="fa fa-angle-right"></i></li>
		</c:if>
		<c:if test="${not empty variable.id}">
			<li><a href='<c:url value="/variables/editar?id=${variable.id}"/>'>Edición</a> <i
				class="fa fa-angle-right"></i></li>
		</c:if>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="row">

			<div class="col-md-10 col-md-offset-1">
				<!-- Cabecera de cuadro para formulario -->
				<div class="portlet box green-haze">
					<div class="portlet-title">
						<div class="caption">
							<h3>
								<c:if test="${empty variable.id}">Alta de Variables IMSS/INFONAVIT | </c:if>
								<c:if test="${not empty variable.id}">Edición Variables IMSS/INFONAVIT | </c:if>
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
						<form:form id="saveVariable" action="save" method="POST"
							modelAttribute="variable">

							<div class="form-body">

								<!-- Mensaje de error, validación backend -->
								<spring:hasBindErrors name="variable">
									<div class="alert alert-danger" style="display: block;">
										<button class="close" data-close="alert"></button>
										<p>ERROR: Favor de verificar campos</p>
									</div>
								</spring:hasBindErrors>

								<!-- Bloques de campos de formulario a llenar -->
								<form:hidden path="id" />
								<form:hidden path="idPeriodo" />
								<div class="row">
									<div class="col-md-4">
										<c:if test="${empty variable.id}">
											<input id="estado" name="estado" type="hidden" value="${1}" />
										</c:if>
										<c:if test="${not empty variable.id}">
											<c:choose>
												<c:when test="${variable.estado == true}">
													<input id="estado" name="estado" type="hidden" value="${1}" />
												</c:when>
												<c:otherwise>
													<input id="estado" name="estado" type="hidden" value="${0}" />
												</c:otherwise>
											</c:choose>
										</c:if>

										<spring:bind path="variable.nombre">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label for="nombre">Variable: *</label>
												<c:if test="${empty variable.id}"><form:input path="nombre" class="form-control" placeholder="Nombre de la variable" /></c:if>
												<c:if test="${not empty variable.id}"><form:input path="nombre" readonly="true" class="form-control" placeholder="Nombre de la variable" /></c:if>
												<form:errors path="nombre" class="help-block"></form:errors>
											</div>
										</spring:bind>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label for="descripcion">Descripción: *</label>
											<form:input path="descripcion" name="descripcion" type="text"
												class="form-control" placeholder="Ingrese una descripción" />
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label for="valor">Valor: *</label>
											<form:input path="valor" name="valor" class="form-control"
												step="0.000001" type="number" />
											<form:hidden path="valorn" name="valorn"/>
										</div>
										</div>
								</div>

								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label for="fechaA">Fecha de Aplicación: *</label>

											<div class="input-group input-medium date date-picker"
												data-date-format="dd/mm/yyyy" data-date-end-date="0d">
												<span class="input-group-btn">
													<button class="btn default" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
												<form:input path="fechaAplicacion" type="text"
													class="form-control" maxlenght="10" readonly="true" />
												
											</div>
											<form:hidden path="fechaAplicacionn" name="fechaAplicacionn"/>
										</div>
									</div>

									<div class="col-md-4">
										<div class="form-group">
											<label for="tipos">Tipo de Variable: *</label>
											<form:select path="tipo" class="form-control"
												name="fecha_var" required="true">
												<form:option value=""></form:option>
												<form:options items="${tipovariable}"></form:options>
											</form:select>
										</div>
									</div>
								</div>

							</div>

							<div class="form-actions">
								<c:if test="${empty variable.id}">
									<button type="submit" class="btn btn-primary"
										id="btnGuardarVariable">Guardar</button>
								</c:if>
								<c:if test="${not empty variable.id}">
									<button type="submit" class="btn btn-primary"
										id="btnActualizarVariable">Actualizar</button>
								</c:if>
								<a href="../variables/" id="cancelar" class="btn default">Cancelar</a>
							</div>
							<form:hidden path="justificacion" id="justificacionSolicitudForm" />
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
		$('#variablesMenu').addClass("active");
		
		$('.date-picker').datepicker({
			autoclose: true,
			language: 'es'
		});
		
		$('#tipo').select2({
			placeholder: "Seleccione el tipo de variable",
			allowClear: true,
               escapeMarkup: function (m) {
               		return m;
            }				 
		});		
		
		$('#cancelar').click(function(e){
			e.preventDefault();
			var linkRedireccion = $(this).attr("href")
		
			bootbox.setLocale('es');
			bootbox.confirm({
				message: "No se guardará información de la Variable. ¿Desea Cancelar?",
				callback: function(result){
					if(result){
						window.location.href = linkRedireccion;
					}
				}
			});
		});
		//Registro Variable
		$('#btnGuardarVariable').click(function(e) {
        	e.preventDefault();
			mensaje = '';
		    
		    	if($("#nombre").val() == ''){
		    		mensaje+= "El campo de <strong>Variable</strong> está vacío.<br>"
		    	};
		    	
		    	if($("#descripcion").val() == ''){
		    		mensaje+= "El campo de <strong>Descripcion</strong> está vacío.<br>"
		    	};
		    	
		    	if($("#valor").val() == ''){
		    		mensaje+= "El campo de <strong>Valor</strong> está vacío.<br>"
		    	};
		    	
		    	if($("#fechaAplicacion").val() == ''){
		    		mensaje+= "El campo de <strong>Fecha Aplicacion</strong> está vacío.<br>"
		    	};
		    	
		    	if($("#tipo").val() == ''){
		    		mensaje+= "El campo de <strong>Tipo</strong> está vacío.<br>"
		    	};

			
			if(mensaje != ''){
				bootbox.alert(mensaje);
			} else {
		        bootbox.confirm({
		        	title: "Registrar Variable",
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
					     $('#justificacionSolicitudForm').val(result);
					       $('#saveVariable').submit();
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
        //Actualizar Variable
        $('#btnActualizarVariable').click(function(e) {
        	e.preventDefault();
			mensaje = '';
		    
		    	if($("#nombre").val() == ''){
		    		mensaje+= "El campo de <strong>Variable</strong> está vacío.<br>"
		    	};
		    	
		    	if($("#descripcion").val() == ''){
		    		mensaje+= "El campo de <strong>Descripcion</strong> está vacío.<br>"
		    	};
		    	
		    	if($("#valor").val() == ''){
		    		mensaje+= "El campo de <strong>Valor</strong> está vacío.<br>"
		    	};
		    	
		    	if($("#fechaAplicacion").val() == ''){
		    		mensaje+= "El campo de <strong>Fecha Aplicacion</strong> está vacío.<br>"
		    	};
		    	
		    	if($("#tipo").val() == ''){
		    		mensaje+= "El campo de <strong>Tipo</strong> está vacío.<br>"
		    	};

			
			if(mensaje != ''){
				bootbox.alert(mensaje);
			} else {
		        bootbox.confirm({
		        	title: "Actualizar Variable",
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
					     $('#justificacionSolicitudForm').val(result);
					       $('#saveVariable').submit();
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
		<c:import url="/WEB-INF/views/template/footer.jsp"></c:import>
	</tiles:putAttribute>

</tiles:insertDefinition>