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
		<c:if test="${empty usuario.id}">
			Alta de Usuario
		</c:if>
		<c:if test="${not empty usuario.id}">
			Edici�n de Usuario
		</c:if>
	</tiles:putAttribute>

	<!-- Menu de navegaci�n -->
	<tiles:putAttribute name="nav">
		<li><a href='<c:url value="/usuarios/"/>'>Alta</a> <i
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
								<c:if test="${empty usuario.id}">Alta de Usuario | </c:if>
								<c:if test="${not empty usuario.id}">Edici�n Usuario | </c:if>
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
						<form:form id="saveUsuario" action="save" method="POST"
							modelAttribute="usuario" rol="form">

							<div class="form-body">

								<!-- Mensaje de error, validaci�n backend -->
								<!-- Bloques de campos de formulario a llenar -->
								<form:hidden path="id" />
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label for="estado">Estado de Usuario: *</label>
											<form:select path="estado" required="true" class="form-control" id="estados">
													<form:option value="1">Activo</form:option>
													<form:option value="0">Inactivo</form:option>
											</form:select>
											<form:errors path="estado" class="help-block"></form:errors>
										</div>
									</div>
									<div class="col-md-4">
										  <div class="form-group ${status.error ? 'has-error' : ''}">
                                                    <label for="inputNumColaborador">Colaboradores: *</label>
                                                    <form:select id="numColaborador" 
                                                    	class="form-control select2-container select2me"
                                                        path="numColaborador">
                                                        <form:option value="">--------------------------------</form:option>
                                                        <form:options items="${numColaborador}" />
                                                    </form:select>
                                                </div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label for="permisos">Permisos: *</label>
											<form:select path="permiso" required="true" class="form-control" id="permisos">
													<form:option value="">Selecciona un Permiso</form:option>
													<form:options items="${permiso}"></form:options>
											</form:select>
										</div>
									</div>
								</div>

							</div>
							<div class="form-actions">
							<c:if test="${empty usuario.id}"><button type="submit" class="btn btn-primary"
												id="btnGuardarUsuario">Guardar</button> </c:if>
								<c:if test="${not empty usuario.id}"><button type="submit" class="btn btn-primary"
												id="btnActualizarUsuario">Actualizar</button></c:if>
								<a href="../usuarios/" id="cancelar" class="btn default">Cancelar</a>
							</div>
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
				message: "No se guardar� informaci�n de la Variable. �Desea Cancelar?",
				callback: function(result){
					if(result){
						window.location.href = linkRedireccion;
					}
				}
			});
		});
		
		$('#btnGuardarVariable').click(function(e) {
        	e.preventDefault();
			mensaje = '';
			
		    if ($("#es_colaborador").val() == '1') {	
		        if($("#inputNumColaborador").val() === ''){
		        	mensaje+= "El campo de <strong>Colaborador</strong> est� vac�o.<br>"
		        };
		    }
		    else {
		    
		    	if($("#nombre").val() == ''){
		    		mensaje+= "El campo de <strong>Variable</strong> est� vac�o.<br>"
		    	};
		    	
		    	if($("#descripcion").val() == ''){
		    		mensaje+= "El campo de <strong>Descripcion</strong> est� vac�o.<br>"
		    	};
		    	
		    	if($("#valor").val() == ''){
		    		mensaje+= "El campo de <strong>Valor</strong> est� vac�o.<br>"
		    	};
		    	
		    	if($("#fechaAplicacion").val() == 'dd/mm/aaaa'){
		    		mensaje+= "El campo de <strong>Fecha Aplicacion</strong> est� vac�o.<br>"
		    	};
		    	
		    	if($("#tipo").val() == ''){
		    		mensaje+= "El campo de <strong>Tipo</strong> est� vac�o.<br>"
		    	};

		
		    }
		    
		   	if($("#inputRolFV").val() == ''){
		    	mensaje+= "El campo de <strong>Rol asignado</strong> est� vac�o.<br>"
		    };
			
			if(mensaje != ''){
				bootbox.alert(mensaje);
			} else {
		        bootbox.confirm({
		        	title: "Resgitar Variable",
			        message: "�Est� seguro de que desea continuar?",
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
			       			$('#saveVariable').submit();
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