<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="styles">
		<link
			href="<c:url value='/assets/global/plugins/select2/select2.css'/>"
			rel="stylesheet" type="text/css" />
	</tiles:putAttribute>
	<tiles:putAttribute name="title">Registro IMSS e INFONAVIT</tiles:putAttribute>

	<!-- Menu de navegaci�n -->
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
									<i class="fa fa-edit"></i> Alta de IMSS e INFONAVIT | <small
										class="form-text form-muted"> Los campos con * son
										obligatorios</small>
								</h3>
							</div>
							<div class="tools">
								<a href="" class="collapse"></a>
							</div>
						</div>

						<div class="portlet-body form">
							<!-- Comienza formulario para registro de variables-->
							<form:form id="addUser" action="add" method="POST"
								modelAttribute="" usuarioPerfil="form">

								<div class="form-body">
									<!-- Mensaje de error, validaci�n backend -->
									<!-- Bloques de campos de formulario a llenar -->
									<div class="row">
											<div class="col-md-4">
                                                <div class="form-group">
                                                    <label for="variable">Variable:</label>
                                                    <input name="variable" id="variable" class="form-control" placeholder="Ingrese el nombre de la variable"/>
                                                </div>
											</div>
											<div class="col-md-4">
                                                <div class="form-group">
                                                    <label for="descripcion">Descripci�n:</label>
                                                    <input name="descripcion" id="descripcion" class="form-control" placeholder="Ingrese una descripci�n"/>
                                                </div>
											</div>
											<div class="col-md-4">
                                                <div class="form-group">
                                                    <label for="valor">Valor:</label>
                                                    <input name="valor" id="valor" class="form-control" placeholder="Ingrese una descripci�n"/>
                                                </div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-4">
                                                <div class="form-group">
                                                    <label for="tipo">Tipo:</label>
                                                    <div class="form-group">
                                                    <label class="content-input">Patr�n
                                                    <input class="content-input" type="radio" name="tipo" id="patron">
                                                    <span class="i"></span>
                                                    </label>
                                                    <label for="trabajador">Trabajador</label>
                                                    <input class="css-checkbox" type="radio" name="tipo" id="trabajador">
                                                    <label for="generico">Gen�rico</label>
                                                    <input class="css-checkbox" type="radio" name="tipo" id="generico">
													</div>
                                                </div>
											</div>
											<div class="col-md-4">
                                                <div class="form-group">
                                                    <label for="fechaA">Fecha Aplicaci�n:</label>
                                                    <input name="fechaA" id="fechaA" class="form-control" type="date"/>
                                                </div>
											</div>
										</div>
									</div>
									<div class="form-actions">
										<button type="submit" class="btn btn-primary"
											id="btnGuardarUser">Guardar</button>
										<a href="../usuarios/" id="cancelar" class="btn default">Cancelar</a>
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
		<script src="<c:url value='/assets/admin/pages/scripts/altas.js'/>"
			type="text/javascript"></script>
		<script
			src="<c:url value='/assets/global/plugins/bootstrap-select/bootstrap-select.min.js'/>"></script>
		<script
			src="<c:url value='/assets/global/plugins/select2/select2.min.js'/>"></script>
	</tiles:putAttribute>

	<tiles:putAttribute name="ready"> 
		$('#catalogos').addClass("start active open")
		$('#usuariosMenu').addClass("active");
		
		cambiosColaborador();
		
		$('#cancelar').click(function(e){
			e.preventDefault();
			var linkRedireccion = $(this).attr("href")
		
			bootbox.setLocale('es');
			bootbox.confirm({
				message: "No se guardar� informaci�n del usuario. �Cancelar?",
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
			
		    if ($("#es_colaborador").val() == '1') {	
		        if($("#inputNumColaborador").val() === ''){
		        	mensaje+= "El campo de <strong>Colaborador</strong> est� vac�o.<br>"
		        };
		    }
		    else {
		    
		    	if($("#username").val() == ''){
		    		mensaje+= "El campo de <strong>Nombre de usuario</strong> est� vac�o.<br>"
		    	};
		    	
		    	if($("#password").val() == ''){
		    		mensaje+= "El campo de <strong>Contrase�a</strong> est� vac�o.<br>"
		    	};
		    	
		    	if($("#email").val() == '' || $("#email")[0].validity.typeMismatch){
		    		mensaje+= "El campo de <strong>Correo</strong> est� vac�o / es incorrecto.<br>"
		    	};

		    	if($("#nombreUser").val() == ''){
		    		mensaje+= "El campo de <strong>Nombre(s)</strong> est� vac�o.<br>"
		    	};
		    	
		    	if($("#aPaterno").val() == ''){
		    		mensaje+= "El campo de <strong>Apellido Paterno</strong> est� vac�o.<br>"
		    	};

				if($("#aMaterno").val() == ''){
		    		mensaje+= "El campo de <strong>Apellido Materno</strong> est� vac�o.<br>"
		    	};
		
		    }
		    
		   	if($("#inputRolFV").val() == ''){
		    	mensaje+= "El campo de <strong>Rol asignado</strong> est� vac�o.<br>"
		    };
			
			if(mensaje != ''){
				bootbox.alert(mensaje);
			} else {
		        bootbox.confirm({
		        	title: "Agregar Usuario",
			        message: "�Est� seguro de que desea continuar? Una vez creado el Usuario, la acci�n no se podr� deshacer.",
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
			       			$('#addUser').submit();
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