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
			href="<c:url value='/assets/global/plugins/select2/select2.css'/>"
			rel="stylesheet" type="text/css" />
	</tiles:putAttribute>

	<tiles:putAttribute name="nav">
		<li><a href='<c:url value="/calculos/"/>'>Cálculos</a> <i
			class="fa fa-angle-right"></i></li>
		<li><span>ISN </span>
	</tiles:putAttribute>

	<tiles:putAttribute name="title">Cálculo ISN</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<%@include file="../secciones/messages.jsp"%>
		<div class="row">
			<div class="col-md-12">

				<div class="portlet box green-jungle" id="form_wizard_2">
					<div class="portlet-title">
						<div class="caption">
							<i class=" icon-layers"></i> <span
								class="caption-subject bold uppercase"> Cálculo ISN - <span
								class="step-title"> Paso 1 de 3 </span>
							</span>
						</div>
						<div class="actions"></div>
					</div>

					<div class="portlet-body form">
						<form:form class="form-horizontal" id="submit_form2" method="POST"
							action="calcularIsn" modelAttribute="isnForm">
							<div class="form-wizard">
								<div class="form-body">

									<ul class="nav nav-pills nav-justified steps">
										<li><a href="#tab1" data-toggle="tab" class="step"> <span
												class="number"> 1 </span> <span class="desc"> <i
													class="fa fa-check"></i> Datos
											</span>
										</a></li>
										<li><a href="#tab2" data-toggle="tab" class="step"> <span
												class="number"> 2 </span> <span class="desc"> <i
													class="fa fa-check"></i> Calendario
											</span>
										</a></li>
										<li><a href="#tab3" data-toggle="tab" class="step active">
												<span class="number"> 3 </span> <span class="desc"> <i
													class="fa fa-check"></i> Confirmación
											</span>
										</a></li>
									</ul>

									<div id="bar" class="progress progress-striped"
										role="progressbar">
										<div class="progress-bar progress-bar-success"></div>
									</div>

									<div class="tab-content">
										<div class="alert alert-danger display-none">
											<button class="close" data-dismiss="alert"></button>
											Existen campos no válidos. Favor de verificar.
										</div>

										<div class="tab-pane active" id="tab1">
											<h3 class="block">Proporciona el archivo de datos</h3>
											<div class="form-group">
												<label class="col-md-5 control-label">El cálculo se
													realizará con la información del archivo: </label>
												<div class="col-md-4 col-md-offset-1">
													<form:input path="nombreArchivo" readonly="true"
														class="form-control" />
												</div>
											</div>
										</div>

										<div class="tab-pane" id="tab2">
											<h3 class="block">Proporciona los datos del periodo de
												cálculo</h3>
											<spring:bind path="isnForm.anioCalendario">
												<div class="form-group ${status.error ? 'has-error' : ''}">
													<label class="control-label col-md-3">Selecciona el
														año del calendario <span class="required">*</span>
													</label>

													<div class="col-md-4">
														<form:select path="anioCalendario" class="form-control">
															<option value=""></option>
															<form:options items="${anios}" />
														</form:select>
														<form:errors path="anioCalendario" class="help-block" />
													</div>
												</div>
											</spring:bind>

											<spring:bind path="isnForm.semanaCalendario">
												<div class="form-group ${status.error ? 'has-error' : ''}">
													<label class="control-label col-md-3">Selecciona la
														semana <span class="required">*</span>
													</label>

													<div class="col-md-4">
														<form:select path="semanaCalendario" class="form-control"
															id="semanaCalendario" name="semanaCalendario">
															<option value=""></option>
														</form:select>
														<form:errors path="semanaCalendario" class="help-block" />
													</div>
												</div>
											</spring:bind>

										</div>

										<div class="tab-pane" id="tab3">
											<h3 class="block">Confirma los datos ingresados para
												realizar el cálculo</h3>
											<h4 class="form-section">Archivo de datos</h4>
											<div class="form-group">
												<label class="control-label col-md-3">Nombre de
													archivo:</label>
												<div class="col-md-4">
													<p class="form-control-static" data-display="nombreArchivo"></p>
												</div>
											</div>

											<h4 class="form-section">Periodo</h4>
											<div class="form-group">
												<label class="control-label col-md-3">Año:</label>
												<div class="col-md-4">
													<p class="form-control-static"
														data-display="anioCalendario"></p>
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-md-3">Semana:</label>
												<div class="col-md-4">
													<p class="form-control-static"
														data-display="semanaCalendario"></p>
												</div>
											</div>

										</div>
									</div>
								</div>
								<div class="form-actions">
									<div class="row">
										<div class="col-md-offset-3 col-md-9">
											<a href="javascript:;" class="btn default button-previous">
												<i class="fa fa-angle-left"></i> Regresar
											</a> <a href="javascript:;"
												class="btn btn-outline green button-next"> Continuar <i
												class="fa fa-angle-right"></i>
											</a> <a class="btn green button-submit" data-style="expand-right"><span>Descargar
													Archivo </span><i class="fa fa-download"></i> </a> <a
												class="btn green button-end" href="<c:url value="/calculos/" />">Terminar
											</a>

										</div>
									</div>
								</div>
							</div>

							<form:hidden path="tipoPeriodo" />
							<form:hidden path="fechaInicio" />
							<form:hidden path="fechaFin" />
						</form:form>

						<!-- Modal Total a Pagar -->
						<div class="modal fade in" tabindex="-1" role="dialog"
							id="modalTAP">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title">Completar Total a Pagar</h4>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">
										<div class="table-responsive">
											<table class="table table-striped table-bordered table-hover"
												id="tablaTAP">
												<thead>
													<tr>
														<th>Estado</th>
														<th>Total a Pagar</th>
													</tr>
												</thead>
												<tbody>
												</tbody>
											</table>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn dark btn-outline"
											data-dismiss="modal" id="btnCerrarTAP">Cancelar</button>
										<button type="button" class="btn green" id="btnGuardarTAP">Guardar
											valores</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>

	<tiles:putAttribute name="scripts">
		<script
			src="<c:url value='/assets/global/plugins/select2/select2.min.js'/>"></script>
		<script
			src="<c:url value='/assets/global/plugins/jquery-validation/js/jquery.validate.min.js'/>"
			type="text/javascript"></script>
		<script
			src="<c:url value='/assets/global/plugins/jquery-validation/js/additional-methods.min.js'/>"
			type="text/javascript"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js'/>"></script>
		<script
			src="<c:url value='/assets/global/plugins/bootstrap-select/bootstrap-select.min.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/scripts/jquery.spring-friendly.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/csa/js/form-wizard-isn.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/csa/js/moment.js'/>"></script>

	</tiles:putAttribute>

	<tiles:putAttribute name="ready"> 
		$('#calculosMain').addClass("start active open");
		$('#calculosMenu').addClass("active");
		
		$('#anioCalendario').select2({
			placeholder: "Seleccione el año",
			allowClear: true,
               escapeMarkup: function (m) {
                   return m;
               }				 
		});
			
         $("#semanaCalendario").select2({
	     	placeholder: "Selecciona el periodo",
	        allowClear: true,
	        	escapeMarkup: function (m) {
	            	return m;
	            }
	    });
		
		wizardIsn();
		
   </tiles:putAttribute>

	<tiles:putAttribute name="footer">
		<c:import url="/WEB-INF/views/template/footer.jsp"></c:import>
	</tiles:putAttribute>

</tiles:insertDefinition>