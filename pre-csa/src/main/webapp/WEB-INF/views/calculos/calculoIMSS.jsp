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
			href="<c:url value='/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css'/>"
			rel="stylesheet" type="text/css" />
		<link
			href="<c:url value='/assets/global/plugins/select2/select2.css'/>"
			rel="stylesheet" type="text/css" />
	</tiles:putAttribute>

	<tiles:putAttribute name="nav">
		<li><a href='<c:url value="/calculos/"/>'>Cálculos</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a href='<c:url value="/calculos/imss"/>'>IMSS e
				INFONAVIT</a> <i class="fa fa-angle-right"></i></li>
	</tiles:putAttribute>

	<tiles:putAttribute name="title">Cálculo IMSS / INFONAVIT</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<%@include file="../secciones/messages.jsp"%>
		<div class="row">
			<div class="col-md-12">

				<div class="portlet box green-jungle" id="form_wizard_1">
					<div class="portlet-title">
						<div class="caption">
							<i class=" icon-layers"></i> <span
								class="caption-subject bold uppercase"> Cálculo IMSS /
								INFONAVIT - <span class="step-title"> Paso 1 de 5 </span>
							</span>
						</div>
						<div class="actions">
							<a class="btn default btn-sm green-stripe"
								href="<c:url value='/calculos/layout'/>"><i
								class="fa fa-arrow-circle-o-down"></i> DESCARGAR PLANTILLA DE
								ARCHIVO </a>
						</div>
					</div>
					<div class="portlet-body form">
						<form:form class="form-horizontal" action="calcularImss"
							id="submit_form" method="POST" modelAttribute="calculo"
							enctype="multipart/form-data">
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
													class="fa fa-check"></i> Periodo
											</span>
										</a></li>
										<li><a href="#tab3" data-toggle="tab" class="step active">
												<span class="number"> 3 </span> <span class="desc"> <i
													class="fa fa-check"></i> Nómina
											</span>
										</a></li>
										<li><a href="#tab4" data-toggle="tab" class="step"> <span
												class="number"> 4 </span> <span class="desc"> <i
													class="fa fa-check"></i> Variables
											</span>
										</a></li>
										<li><a href="#tab5" data-toggle="tab" class="step"> <span
												class="number"> 5 </span> <span class="desc"> <i
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
												<label class="col-md-5 control-label">Selecciona el
													archivo que contiene los datos de carga <span
													class="required">*</span>
												</label>
												<div class="col-md-6 col-md-offset-1">
													<form:input type="file" path="archivo"
														accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
														class="form-control-file" />
												</div>
											</div>
										</div>

										<div class="tab-pane" id="tab2">
											<h3 class="block">Proporciona los datos del periodo de
												cálculo</h3>
											<div class="form-group">
												<label class="control-label col-md-3">Selecciona el
													Tipo de Periodo <span class="required">*</span>
												</label>

												<div class="col-md-4">
													<form:select path="tipoPeriodo" class="form-control">
														<option value=""></option>
														<form:options items="${tipoPeriodo}" itemLabel="label"
															itemValue="value"></form:options>
													</form:select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">Fecha inicial
													del periodo a calcular <span class="required">*</span>
												</label>
												<div class="col-md-4">
													<div class="input-group date date-picker"
														data-date-format="dd/mm/yyyy" data-date-end-date="0d">
														<span class="input-group-btn">
															<button class="btn default" type="button">
																<i class="fa fa-calendar"></i>
															</button>
														</span>
														<form:input path="fechaInicio" type="text"
															class="form-control" readonly="true" placeholder="Del..." />
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">Fecha final
													del periodo a calcular <span class="required">*</span>
												</label>
												<div class="col-md-4">
													<div class="input-group date date-picker"
														data-date-format="dd/mm/yyyy" data-date-end-date="0d">
														<span class="input-group-btn">
															<button class="btn default" type="button">
																<i class="fa fa-calendar"></i>
															</button>
														</span>
														<form:input path="fechaFin" type="text"
															class="form-control" readonly="true" placeholder="Al..." />
													</div>
												</div>
											</div>
										</div>

										<div class="tab-pane" id="tab3">
											<h3 class="block">Proporciona el tipo de nómina</h3>
											<div class="form-group">
												<label class="control-label col-md-3">Selecciona el
													Tipo de Nómina <span class="required"> * </span>
												</label>
												<div class="col-md-4">
													<form:select path="tipoNomina" class="form-control">
														<option value=""></option>
														<form:options items="${tipoNomina}" itemLabel="label"
															itemValue="value" />
													</form:select>
												</div>
											</div>
										</div>

										<div class="tab-pane" id="tab4">
											<h3 class="block">Proporciona la tabla de variables</h3>
											<div class="form-group">
												<label class="col-md-3 control-label">Selecciona la
													tabla de Variables IMSS / INFONAVIT <span class="required">*</span>
												</label>

												<div class="col-md-4">
													<form:select path="fechaVariables" class="form-control"
														multiple="false">
														<option value=""></option>
														<c:forEach items="${periodos}" var="p">
															<form:option value="${p.id}"
																data-inicio="${p.fechaAplicacion}"
																data-fin="${p.fechaTermino}">
																<fmt:formatDate value="${p.fechaAplicacion}"
																	pattern="dd/MM/yyyy" /> - 
																<fmt:formatDate value="${p.fechaTermino}"
																	pattern="dd/MM/yyyy" />
															</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
										</div>

										<div class="tab-pane" id="tab5">
											<h3 class="block">Confirma los datos ingresados para
												realizar el cálculo</h3>
											<h4 class="form-section">Archivo de datos</h4>
											<div class="form-group">
												<label class="control-label col-md-3">Nombre de
													archivo:</label>
												<div class="col-md-4">
													<p class="form-control-static" data-display="archivo">
													</p>
												</div>
											</div>

											<h4 class="form-section">Periodo</h4>
											<div class="form-group">
												<label class="control-label col-md-3">Tipo de
													Periodo:</label>
												<div class="col-md-4">
													<p class="form-control-static" data-display="tipoPeriodo">
													</p>
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-md-3">Fecha inicial
													del periodo:</label>
												<div class="col-md-4">
													<p class="form-control-static" data-display="fechaInicio"></p>
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-md-3">Fecha final
													del periodo:</label>
												<div class="col-md-4">
													<p class="form-control-static" data-display="fechaFin"></p>
												</div>
											</div>

											<h4 class="form-section">Nómina</h4>
											<div class="form-group">
												<label class="control-label col-md-3">Tipo de
													Nómina:</label>
												<div class="col-md-4">
													<p class="form-control-static" data-display="tipoNomina">
													</p>
												</div>
											</div>

											<h4 class="form-section">Tabla de Variables</h4>
											<div class="form-group">
												<label class="control-label col-md-3">Variables IMSS
													/ INFONAVIT:</label>
												<div class="col-md-4">
													<p class="form-control-static"
														data-display="fechaVariables"></p>
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
											</a> <a class="btn green button-submit ladda-button"
												data-style="expand-right"><span class="ladda-label">Descargar
													Archivo</span><i class="fa fa-download"></i> </a> <a
												class="btn green button-continue" id="btnISN">
												Continuar con Cálculo ISN <i class="fa fa-angle-right"></i>
											</a>


										</div>
									</div>
								</div>
							</div>
							<form:hidden path="nombreArchivo" />
							<form:hidden path="fechaAplicacion" />
							<form:hidden path="fechaTermino" />
						</form:form>
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
			src="<c:url value='/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js'/>">
			
		</script>
		<script
			src="<c:url value='/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.es.min.js'/>">
			
		</script>
		<script
			src="<c:url value='/assets/global/plugins/bootstrap-select/bootstrap-select.min.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/scripts/jquery.spring-friendly.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/csa/js/form-wizard-imss.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/csa/js/moment.js'/>"></script>

	</tiles:putAttribute>

	<tiles:putAttribute name="ready"> 
		$('#calculosMain').addClass("start active open");
		$('#calculosMenu').addClass("active");
		
		$('.date-picker').datepicker({
			autoclose:true,
			language:'es'
		});
		
		$('#tipoPeriodo').select2({
			placeholder: "Seleccione el tipo de periodo",
			allowClear: true,
               escapeMarkup: function (m) {
                   return m;
               }				 
		});
			
         $("#tipoNomina").select2({
	     	placeholder: "Selecciona el tipo de nómina",
	        allowClear: true,
	        	escapeMarkup: function (m) {
	            	return m;
	            }
	    });
            
         $("#fechaVariables").select2({
             placeholder: "Selecciona el rango de fechas de las variables",
             allowClear: true,
             escapeMarkup: function (m) {
                 return m;
             }
         });
         
         wizard();

		$('#btnISN').click(function(){
			let nombreArchivo = $('#archivo')[0].files[0]['name'];
			let fecha1 = $('#fechaInicio').val();
			let fecha2 = $('#fechaFin').val();
			let periodo = $('#tipoPeriodo').val();
			
			window.location.href= "isn?nombreArchivo="+nombreArchivo+"&fechaInicio="+fecha1+"&fechaFin="+fecha2+"&periodo="+periodo;
		});
		
   </tiles:putAttribute>

	<tiles:putAttribute name="footer">
		<c:import url="/WEB-INF/views/template/footer.jsp"></c:import>
	</tiles:putAttribute>

</tiles:insertDefinition>