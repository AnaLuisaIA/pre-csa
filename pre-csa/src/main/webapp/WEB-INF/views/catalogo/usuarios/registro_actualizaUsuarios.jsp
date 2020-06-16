<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="styles">
		<link
			href="<c:url value='/assets/global/plugins/select2/select2.css'/>"
			rel="stylesheet" type="text/css" />
	</tiles:putAttribute>
	<tiles:putAttribute name="title">
		<c:if test="${empty usuario.id}">
			Alta de Usuario
		</c:if>
		<c:if test="${not empty usuario.id}">
			Edición de Usuario
		</c:if>
	</tiles:putAttribute>

	<!-- Menu de navegación -->
	<tiles:putAttribute name="nav">
		<li><a href='<c:url value="/usuarios/"/>'>Usuarios</a> <i
			class="fa fa-angle-right"></i></li>

		<c:if test="${empty usuario.id}">
			<li><a href='<c:url value="/usuarios/alta"/>'>Alta</a> <i
				class="fa fa-angle-right"></i></li>
		</c:if>
		<c:if test="${not empty usuario.id}">
			<li><a href='<c:url value="/usuarios/editar?id=${usuario.id}"/>'>Edición</a> <i
				class="fa fa-angle-right"></i></li>
		</c:if>
	</tiles:putAttribute>


	<tiles:putAttribute name="body">
		<%@include file="../../secciones/messages.jsp"%>
		<div class="row">

			<div class="col-md-10 col-md-offset-1">
				<!-- Cabecera de cuadro para formulario -->
				<div class="portlet box green-haze">
					<div class="portlet-title">
						<div class="caption">
							<h3>
								<i class="fa fa-edit"></i>
								<c:if test="${empty usuario.id}">Alta de Usuario | </c:if>
								<c:if test="${not empty usuario.id}">Edición de Usuario | </c:if>
								<small class="form-text form-muted"> Los campos con *
									son obligatorios</small>
							</h3>
						</div>
						<div class="tools">
							<a href="" class="collapse"></a>
						</div>
					</div>

					<div class="portlet-body form">
						<!-- Comienza formulario para registro de usuario -->
						<form:form id="saveUser" action="save" method="POST"
							modelAttribute="usuario">

							<div class="form-body">

								<!-- Mensaje de error, validación backend -->
								<spring:hasBindErrors name="usuario">
									<div class="alert alert-danger" style="display: block;">
										<button class="close" data-close="alert"></button>
										<p>ERROR: Favor de verificar campos</p>
									</div>
								</spring:hasBindErrors>

								<!-- Bloques de campos de formulario a llenar -->
								<div class="row">
									<div class="col-md-12">
										<spring:bind path="usuario.estado">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label for="estado">Estado de usuario <span
													class="required"> * </span></label>
												<form:select path="estado" required="true"
													class="form-control" id="estado">
													<form:option value="1">ACTIVO</form:option>
													<form:option value="0">INACTIVO</form:option>
												</form:select>
												<form:errors path="estado" class="help-block"></form:errors>
											</div>
										</spring:bind>
									</div>

								</div>

								<div class="row">
									<div class="form-group col-md-12">
										<spring:bind path="usuario.numColaborador">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label for="inputNumColaborador">Colaborador <span
													class="required"> * </span></label>
												<form:select id="inputNumColaborador" class="form-control"
													path="numColaborador">
													<form:option value=""></form:option>
													<form:options items="${colaboradores}" />
												</form:select>
												<form:errors path="numColaborador" class="help-block"></form:errors>
											</div>
										</spring:bind>
									</div>
								</div>

								<div class="row">
									<div class="form-group col-md-12">
										<spring:bind path="usuario.permisos">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label for="permisos">Permisos asignados al usuario
													<span class="required"> * </span>
												</label>
												<form:select path="permisos" required="true" multiple="true"
													class="form-control" id="permisos">
													<form:options items="${permisos}" itemLabel="descripcion"
														itemValue="value" />
												</form:select>
												<form:errors path="permisos" class="help-block"></form:errors>
											</div>
										</spring:bind>
									</div>
								</div>

								<div class="form-actions">
									<button type="submit" class="btn btn-primary"
										id="btnGuardarUser">Guardar</button>
									<a href="../usuarios/" id="cancelar" class="btn default">Cancelar</a>
								</div>
							</div>

							<form:hidden path="id" />
						</form:form>
					</div>
				</div>
			</div>

		</div>

	</tiles:putAttribute>


	<tiles:putAttribute name="scripts">
		<script
			src="<c:url value='/assets/global/plugins/bootstrap-select/bootstrap-select.min.js'/>"></script>
		<script
			src="<c:url value='/assets/global/plugins/select2/select2.min.js'/>"></script>
	</tiles:putAttribute>

	<tiles:putAttribute name="ready"> 
		$('#catalogos').addClass("start active open")
		$('#usuariosMenu').addClass("active");
		
		$('#estado').select2({
			placeholder: "Selecciona el estatus del usuario",
	   		allowClear: true
		});
		
		$('#inputNumColaborador').select2({
			placeholder: "Seleccione un colaborador de la lista",
	   		allowClear: true
		});

		<c:if test="${not empty usuario.id}">
			$('#inputNumColaborador').attr('readonly', 'true');
		</c:if>
		
		$("#permisos").select2();
		
		$('#cancelar').click(function(e){
			e.preventDefault();
			var linkRedireccion = $(this).attr("href")
		
			bootbox.setLocale('es');
			bootbox.confirm({
				message: "No se guardarán los cambios en el sistema. ¿Cancelar?",
				callback: function(result){
					if(result){
						window.location.href = linkRedireccion;
					}
				}
			});
		});
		
		$('#btnGuardarUser').click(function(e) {
        	e.preventDefault();
			mensaje = '';
				
		        if($("#inputNumColaborador").val() === ''){
		        	mensaje+= "El campo de <strong>Colaborador</strong> está vacío.<br>"
		        };
		    	if($("#permisos").val() == ''){
		    		mensaje+= "El campo de <strong>permisos</strong> está vacío.<br>"
		    	};
			
			if(mensaje != ''){
				bootbox.alert(mensaje);
			} else {
		        bootbox.confirm({
		        	title: "Agregar Usuario",
			        message: "¿Está seguro de que desea continuar? Una vez creado el Usuario, la acción no se podrá deshacer.",
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
			       			$('#saveUser').submit();
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