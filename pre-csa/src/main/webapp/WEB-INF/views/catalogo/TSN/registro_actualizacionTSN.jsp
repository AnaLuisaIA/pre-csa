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
			Alta de Tasa Sobre Nómina
		</c:if>
		<c:if test="${not empty tasa.id}">
			Edición de Tasa Sobre Nómina
		</c:if>
	</tiles:putAttribute>

	<!-- Menu de navegación -->
	<tiles:putAttribute name="nav">
		<li><a href='<c:url value="/tasas/"/>'>Tasas sobre Nómina</a> <i
			class="fa fa-angle-right"></i></li>
		<c:if test="${empty tasa.id}">
			<li><a>Alta</a></li>
		</c:if>
		<c:if test="${not empty tasa.id}">
			<li><a>Edición</a></li>
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
								<c:if test="${empty tasa.id}">Alta de Tasa Sobre Nómina | </c:if>
								<c:if test="${not empty tasa.id}">Edición Tasa Sobre Nómina | </c:if>
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
								
								<!-- JQuery Validation -->
								<div class="alert alert-danger display-none">
									<button class="close" data-dismiss="alert"></button>
									Existen campos no válidos. Favor de verificar.
								</div>
								
								<div class="row">

									<div class="col-md-4">
										<c:if test="${empty tasa.id}">
											<input id="estatus" name="estatus" type="hidden" value="${1}" />
										</c:if>
										<c:if test="${not empty tasa.id}">
											<c:choose>
												<c:when test="${tasa.estatus == true}">
													<input id="estatus" name="estatus" type="hidden"
														value="${1}" />
												</c:when>
												<c:otherwise>
													<input id="estatus" name="estatus" type="hidden"
														value="${0}" />
												</c:otherwise>
											</c:choose>
										</c:if>
										<spring:bind path="tasa.estado">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label for="estado">Estado: *</label>
												<c:if test="${empty estado.id}">
													<form:input path="estado" class="form-control"
														placeholder="Nombre del Estado" />
												</c:if>
												<c:if test="${not empty estado.id}">
													<form:input path="estado" readonly="true"
														class="form-control" />
												</c:if>
												<form:errors path="estado" class="help-block"></form:errors>
											</div>
										</spring:bind>
									</div>

									<div class="col-md-4">
										<spring:bind path="tasa.tipoNomina">
											<div class="form-group">
												<label for="tipoNomina">Tipo Nómina: *</label>
												<form:select path="tipoNomina" required="true"
													class="form-control" id="tipoNomina">
													<form:option value=""></form:option>
													<form:options items="${tipoNomina}" itemValue="value" itemLabel="label"></form:options>
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
												<form:input path="valor" name="valor" class="form-control"
													step="0.000001" min="0" type="number" />
											</div>

										</div>
									</div>

									<div class="col-md-4">
										<div class="form-group">
											<label for="oficinas">Oficina: *</label>
											<form:select path="oficinas" id="oficinas" multiple="true"
												required="true" class="form-control">
												<form:options items="${ciudad}" />
											</form:select>
										</div>
									</div>

									<div class="col-md-4">
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
							
							<form:hidden path="id" />
							<form:hidden path="justificacion" id="justificacionTasaForm" />
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>


	<tiles:putAttribute name="scripts">
		<script
			src="<c:url value='/assets/global/plugins/jquery-validation/js/jquery.validate.min.js'/>"
			type="text/javascript"></script>
		<script
			src="<c:url value='/assets/global/plugins/jquery-validation/js/additional-methods.min.js'/>"
			type="text/javascript"></script>
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
			src="<c:url value='/assets/csa/js/tasa_sobre_nomina.js'/>"></script>

	</tiles:putAttribute>

	<tiles:putAttribute name="ready"> 
		$('#catalogos').addClass("start active open")
		$('#tasasMenu').addClass("active");
		
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
		
		$("#oficinas").select2();
		
		$('#tipoVariable').change(function(){
			form.validate().element($(this));
			if($(this).val() === 'TAP'){
				$("#valor").attr('readonly', true);
				$("#valor").val(0);
			} else {
				$("#valor").attr('readonly', false);
			}
			
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
	</tiles:putAttribute>

	<tiles:putAttribute name="footer">
		<c:import url="/WEB-INF/views/template/footer.jsp"></c:import>
	</tiles:putAttribute>

</tiles:insertDefinition>