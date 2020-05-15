<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
		<li><a href='<c:url value="/usuarios/"/>'>Alta</a> <i
			class="fa fa-angle-right"></i></li>
	</tiles:putAttribute>
	<body>

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
									 <small class="form-text form-muted"> Los campos con * son obligatorios</small>
								</h3>
							</div>
							<div class="tools">
								<a href="" class="collapse"></a>
							</div>
						</div>

						<div class="portlet-body form">
							<!-- Comienza formulario para registro de variables-->
							<form:form id="saveVariable" action="save" method="POST" modelAttribute="variable" rol="form">

								<div class="form-body">
								
									<!-- Mensaje de error, validación backend -->
									<!-- Bloques de campos de formulario a llenar -->
									<form:hidden path="id" />
									<div class="row">
											<div class="col-md-4">
											<input id="estado" name="estado" type="hidden" value="${1}"/>

                                                <div class="form-group">
                                                    <label for="variable">Variable: *</label>
                                                    <form:input path="nombre" name="nombre" class="form-control" placeholder="Ingrese el nombre de la variable"/>
                                                </div>
											</div>
											<div class="col-md-4">
                                                <div class="form-group">
                                                    <label for="descripcion">Descripción: *</label>
                                                    <form:input path="descripcion" name="descripcion" class="form-control" placeholder="Ingrese una descripción"/>
                                                </div>
											</div>
											<div class="col-md-4">
                                                <div class="form-group">
                                                    <label for="valor">Valor: *</label>
                                                    <form:input path="valor" name="valor" class="form-control" placeholder="Ingrese una descripción"/>
                                                </div>
											</div>
										</div>
										<div class="row">											
											<div class="col-md-4">
                                                <div class="form-group">
                                                    <label for="fechaA">Fecha Aplicación: *</label>										
                                                </div>
                                           		<div class="input-group input-medium date date-picker" 
												data-date-format="dd/mm/yyyy"
												data-date-end-date="0d">
										<span class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
										<form:input path="fechaAplicacion" type="text" class="form-control" readonly="true"
											maxlenght="10"/>
									</div>

											</div>
											<div class="col-md-4">
                                                <div class="form-group">
                                                   <div class="form-group">
															<spring:bind path="variable.tipo">
															<div class="form-group">
																<label for="tipos">Tipo: *</label>
																<form:select path="tipo" required="true" class="form-control" id="tipos">
																	<form:option value="">Selecciona Un Tipo</form:option>
																	<form:options items="${tipovariable}"></form:options>
																</form:select>
																<form:errors path="tipo" class="help-block"></form:errors>
															</div>
														</spring:bind>
                                        			</div>
                                        			
                                        			</div>
											</div>
											
										</div>
									</div>
									<div class="form-actions">
										<button type="submit" class="btn btn-primary"
											id="btnGuardarVariable">Guardar</button>
										<a href="../variable/"  id="cancelar" class="btn default">Cancelar</a>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>

			</div>

		</tiles:putAttribute>

	</body>

	<tiles:putAttribute name="scripts">

		<script
			src="<c:url value='/assets/global/plugins/bootstrap-select/bootstrap-select.min.js'/>"></script>
		<script
			src="<c:url value='/assets/global/plugins/select2/select2.min.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/plugins/datatables/media/js/jquery.dataTables.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/plugins/datatables/extensions/TableTools/js/dataTables.tableTools.min.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/plugins/datatables/extensions/ColReorder/js/dataTables.colReorder.min.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/plugins/datatables/extensions/Scroller/js/dataTables.scroller.min.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/admin/pages/scripts/argostable.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/scripts/jquery.spring-friendly.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/admin/pages/scripts/ui-idletimeout.js'/>"></script>


	</tiles:putAttribute>
	
	<tiles:putAttribute name="ready"> 
		$('#catalogos').addClass("start active open")
		$('#catalogosMenu').addClass("active");
		
		cambiosColaborador();
		
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
		
		$('#btnGuardarVariable').click(function(e) {
        	e.preventDefault();
			mensaje = '';
			
		    if ($("#es_colaborador").val() == '1') {	
		        if($("#inputNumColaborador").val() === ''){
		        	mensaje+= "El campo de <strong>Colaborador</strong> está vacío.<br>"
		        };
		    }
		    else {
		    
		    	if($("#nombre").val() == ''){
		    		mensaje+= "El campo de <strong>Variable</strong> está vacío.<br>"
		    	};
		    	
		    	if($("#descripcion").val() == ''){
		    		mensaje+= "El campo de <strong>Descripcion</strong> está vacío.<br>"
		    	};
		    	
		    	if($("#valor").val() == ''){
		    		mensaje+= "El campo de <strong>Valor</strong> está vacío.<br>"
		    	};
		    	
		    	if($("#fechaAplicacion").val() == 'dd/mm/aaaa'){
		    		mensaje+= "El campo de <strong>Fecha Aplicacion</strong> está vacío.<br>"
		    	};
		    	
		    	if($("#tipo").val() == ''){
		    		mensaje+= "El campo de <strong>Tipo</strong> está vacío.<br>"
		    	};

		
		    }
		    
		   	if($("#inputRolFV").val() == ''){
		    	mensaje+= "El campo de <strong>Rol asignado</strong> está vacío.<br>"
		    };
			
			if(mensaje != ''){
				bootbox.alert(mensaje);
			} else {
		        bootbox.confirm({
		        	title: "Resgitar Variable",
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