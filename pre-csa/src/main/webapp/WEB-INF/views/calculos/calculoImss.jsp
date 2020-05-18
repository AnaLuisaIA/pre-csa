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
				<div class="portlet box green-jungle" id="form_wizard_imss">

					<div class="portlet-title">
						<div class="caption">
							<i class="icon-layers"></i> <span
								class="caption-subject bold uppercase"> Cálculo de IMSS /
								INFONAVIT - </span> <span class="step-title">Paso 1 de 5</span>
						</div>
					</div>

					<div class="portlet-body form">

						<form class="form-horizontal" id="formImss" method="POST">

							<div class="form-wizard">
								<div class="form-body">
									<ul class="nav nav-pills nav-justified steps">

										<li class="active"><a href="#tab1" data-toggle="tab"
											class="step" aria-expanded="true"> <span class="number">1</span>
												<span class="desc"><i class="fa fa-check"></i> Datos</span>
										</a></li>

										<li><a href="#tab2" data-toggle="tab" class="step"> <span
												class="number">2</span> <span class="desc"><i
													class="fa fa-check"></i> Periodo</span>
										</a></li>

										<li><a href="#tab3" data-toggle="tab" class="step"
											aria-expanded="true"> <span class="number">3</span> <span
												class="desc"><i class="fa fa-check"></i> Nómina</span>
										</a></li>

										<li><a href="#tab4" data-toggle="tab" class="step"
											aria-expanded="true"> <span class="number">4</span> <span
												class="desc"><i class="fa fa-check"></i> Variables</span>
										</a></li>

										<li><a href="#tab5" data-toggle="tab" class="step"
											aria-expanded="true"> <span class="number">5</span> <span
												class="desc"><i class="fa fa-check"></i> Confirmación</span>
										</a></li>

									</ul>

									<div id="bar" class="progress progress-striped"
										role="progressbar">
										<div class="progress-bar progress-bar-success"></div>
									</div>

									<div class="tab-content">

										<div class="alert alert-danger display-none">
											<button class="close" data-dismiss="alert"></button>
											Hay errores en el formulario. Favor de verificar.
										</div>

										<div class="alert alert-success display-none">
											<button class="close" data-dismiss="alert"></button>
											La validación del formulario ha sido exitosa.
										</div>


										<div class="tab-pane" id="tab1">
											<h3 class="block">Proporciona el archivo de datos</h3>
											<div class="form-group">
												<label class="col-md-5 control-label">Selecciona el
													archivo que contiene los datos de carga <span
													class="required">*</span>
												</label>

												<div class="col-md-6 col-md-offset-1">
													<input type="file" path="archivo"
														accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
														class="form-control-file" required="true" name="archivo" />
												</div>
											</div>
										</div>

										<div class="tab-pane active" id="tab2">
											<h3 class="block">Proporciona los datos del periodo de
												cálculo</h3>
											<div class="form-group">
												<label class="control-label col-md-5">Selecciona el
													Tipo de Periodo <span class="required">*</span>
												</label>

												<div class="col-md-4">
													<select id="tipoPeriodo"
														class="form-control select2-container select2me"
														placeholder="Elija el tipo de periodo">
														<option value="">
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-5 control-label">Fecha inicial
													del periodo a calcular <span class="required">*</span>
												</label>
												<div class="input-group input-medium date date-picker"
													data-date-format="dd/mm/yyyy" data-date-end-date="0d">
													<span class="input-group-btn">
														<button class="btn default" type="button">
															<i class="fa fa-calendar"></i>
														</button>
													</span> <input path="fechaInicio" type="text" class="form-control"
														readonly="true" maxlenght="10" placeholder="Del..." />
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-5 control-label">Fecha final
													del periodo a calcular <span class="required">*</span>
												</label>
												<div class="input-group input-medium date date-picker"
													data-date-format="dd/mm/yyyy" data-date-end-date="0d">
													<span class="input-group-btn">
														<button class="btn default" type="button">
															<i class="fa fa-calendar"></i>
														</button>
													</span> <input path="fechaInicio" type="text" class="form-control"
														readonly="true" maxlenght="10" placeholder="Al..." />
												</div>
											</div>
										</div>

										<div class="tab-pane" id="tab3">
											<h3 class="block">Proporciona el tipo de nómina</h3>
											<div class="form-group">
												<label class="col-md-5 control-label">Selecciona el
													Tipo de Nómina <span class="required">*</span>
												</label>

												<div class="col-md-5">
													<select id="tipoNomina"
														class="form-control select2-container select2me"
														placeholder="Elija el tipo de nómina">
														<option value="">
													</select>
												</div>
											</div>
										</div>

										<div class="tab-pane" id="tab4">
											<h3 class="block">Proporciona la tabla de variables</h3>
											<div class="form-group">
												<label class="col-md-5 control-label">Selecciona la
													tabla de Variables IMSS / INFONAVIT <span class="required">*</span>
												</label>

												<div class="col-md-5">
													<select id="fechaVariables"
														class="form-control select2-container select2me"
														placeholder="Elija la fecha">
														<option value="">
													</select>
												</div>
											</div>
										</div>

										<div class="tab-pane" id="tab5">
											<h3 class="block">Confirma los datos ingresados para
												realizar el cálculo</h3>
											<div class="form-group">
												<label class="col-md-5 control-label">Tipo de Nómina
												</label>
												<div class="col-md-6 col-md-offset-1">
													<p class="form-control-static"></p>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-5 control-label">Tipo de
													Periodo </label>
												<div class="col-md-6 col-md-offset-1">
													<p class="form-control-static"></p>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-5 control-label">Fecha de
													inicio del periodo </label>
												<div class="col-md-6 col-md-offset-1">
													<p class="form-control-static"></p>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-5 control-label">Fecha de
													término del periodo </label>
												<div class="col-md-6 col-md-offset-1">
													<p class="form-control-static"></p>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-5 control-label">Nombre de
													archivo </label>
												<div class="col-md-6 col-md-offset-1">
													<p class="form-control-static"></p>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-5 control-label">Fecha inicial
													de tabla de variables </label>
												<div class="col-md-6 col-md-offset-1">
													<p class="form-control-static"></p>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-5 control-label">Fecha final de
													tabla de variables </label>
												<div class="col-md-6 col-md-offset-1">
													<p class="form-control-static"></p>
												</div>
											</div>
										</div>
									</div>

								</div>

								<div class="form-actions">
									<div class="row">
										<div class="col-md-offset-3 col-md-9">
											<a href="" class="btn default button-previous disabled">Regresar
												<i class="fa fa-angle-left"></i>
											</a> <a href="" class="btn btn-outline green button-next">Continuar
												<i class="fa fa-angle-right"></i>
											</a> <a href="" class="btn green button-submit">Descargar
												Archivo <i class="fa fa-check"></i>
											</a>
										</div>
									</div>
								</div>
							</div>
						</form>

					</div>

				</div>
			</div>
		</div>
	</tiles:putAttribute>

	<tiles:putAttribute name="scripts">
		<script type="text/javascript"
			src="<c:url value='/assets/csa/js/jquery.bootstrap.wizard.min.js'/>"></script>
		<script
			src="<c:url value='/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js'/>">
			
		</script>
		<script
			src="<c:url value='/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.es.min.js'/>">
			
		</script>
		<script
			src="<c:url value='/assets/global/plugins/bootstrap-select/bootstrap-select.min.js'/>"></script>
		<script
			src="<c:url value='/assets/global/plugins/select2/select2.min.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/scripts/jquery.spring-friendly.js'/>"></script>
	</tiles:putAttribute>

	<tiles:putAttribute name="ready"> 
		$('#calculosMain').addClass("start active open");
		$('#calculosMenu').addClass("active");
		
		$('.date-picker').datepicker({
			autoclose:true,
			language:'es'
		});
        
        var form = $('#formImss');
        var error = $('.alert-danger', form);
        var success = $('.alert-success', form);
		
		var handleTitle = function(tab, navigation, index) {
                var total = navigation.find('li').length;
                var current = index + 1;
                
                // set wizard title
                $('.step-title', $('#form_wizard_imss')).text('Paso ' + (index + 1) + ' de ' + total);
                
                // set done steps
                jQuery('li', $('#form_wizard_imss')).removeClass("done");
                var li_list = navigation.find('li');
                
                for (var i = 0; i < index; i++) {
                    jQuery(li_list[i]).addClass("done");
                }

                if (current == 1) {
                    $('#form_wizard_imss').find('.button-previous').hide();
                } else {
                    $('#form_wizard_imss').find('.button-previous').show();
                }

                if (current >= total) {
                    $('#form_wizard_imss').find('.button-next').hide();
                    $('#form_wizard_imss').find('.button-submit').show();
                    //displayConfirm();
                } else {
                    $('#form_wizard_imss').find('.button-next').show();
                    $('#form_wizard_imss').find('.button-submit').hide();
                }
                console.log("LLEGUE AL FINAL")
                Metronic.scrollTo($('.page-title'));
            }
		
		$('#form_wizard_imss').bootstrapWizard({
                'nextSelector': '.button-next',
                'previousSelector': '.button-previous',
                'onTabClick': function (tab, navigation, index, clickedIndex) {
                    return false;
                },
                
                'onNext': function (tab, navigation, index) {
                    success.hide();
                    error.hide();

                    /*if (form.valid() == false) {
                        return false;
                    }*/

                    handleTitle(tab, navigation, index);
                },
                
                'onPrevious': function (tab, navigation, index) {
                    success.hide();
                    error.hide();

                    handleTitle(tab, navigation, index);
                },
                
                'onTabShow': function (tab, navigation, index) {
                    var total = navigation.find('li').length;
                    var current = index + 1;
                    var $percent = (current / total) * 100;
                    
                    $('#form_wizard_imss').find('.progress-bar').css({
                        width: $percent + '%'
                    });
                }
            });
            
            $('#form_wizard_imss').find('.button-previous').hide();
            $('#form_wizard_imss').find('.button-submit').hide();
		
		
   </tiles:putAttribute>

	<tiles:putAttribute name="footer">
		<c:import url="/WEB-INF/views/template/footer.jsp"></c:import>
	</tiles:putAttribute>

</tiles:insertDefinition>