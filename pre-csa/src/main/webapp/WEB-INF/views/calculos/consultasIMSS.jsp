<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="styles">
		<link
			href="<c:url value='/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css'/>"
			rel="stylesheet" type="text/css" />
		<link
			href="<c:url value='/assets/global/plugins/datatables/extensions/Scroller/css/dataTables.scroller.min.css'/>"
			rel="stylesheet" type="text/css" />
		<link
			href="<c:url value='/assets/global/plugins/select2/select2.css'/>"
			rel="stylesheet" type="text/css" />
		<link
			href="<c:url value='/assets/global/plugins/datatables/extensions/ColReorder/css/dataTables.colReorder.min.css'/>"
			rel="stylesheet" type="text/css" />
		<link
			href="<c:url value='/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css'/>"
			rel="stylesheet" type="text/css" />
	</tiles:putAttribute>

	<tiles:putAttribute name="nav">
		<li><a href='<c:url value="/calculos/consultaImss"/>'>Consultas
				IMSS e INFONAVIT</a> <i class="fa fa-angle-right"></i></li>
	</tiles:putAttribute>

	<tiles:putAttribute name="title">Consultas IMSS e INFONAVIT</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="row">

			<div class="col-md-12">
				<div class="portlet box green-jungle">

					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>Consultas IMSS e INFONAVIT
						</div>
						<div class="actions"></div>
					</div>

					<div class="portlet-body">
						<div align="right">
							<form:form id="buscarCalculoImss" modelAttribute="calculoIMSS"
								method="POST" class="form-inline" action="buscarCalculoImss">

								<div class="form-group">
									<label>Del: </label>
									<div class="input-group input-medium date date-picker"
										data-date-format="dd/mm/yyyy" data-date-end-date="0d">
										<span class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
										<form:input path="fechaInicio" type="text"
											class="form-control" readonly="true" maxlenght="10"
											placeholder="Del..." />
									</div>

									<label>Al: </label>
									<div class="input-group input-medium date date-picker"
										data-date-format="dd/mm/yyyy" data-date-end-date="0d">
										<span class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
										<form:input path="fechaFin" type="text" class="form-control"
											readonly="true" maxlenght="10" placeholder="Al..." />
									</div>

									<form:select path="numColaborador" class="form-control"
										name="numColaborador">
										<form:option value="">TODOS LOS COLABORADORES</form:option>
										<c:forEach var="u" items="${usuario}">
											<form:option value="${u.numColaborador}">
												<c:out value="${u.nombres} ${u.aPaterno} ${u.aMaterno}" />
											</form:option>
										</c:forEach>
									</form:select>

									<div class="form-group">
										<form:select path="fechaCalculo" class="form-control"
											name="fechaCalculo">
											<form:option value="">TODAS LAS FECHAS</form:option>
											<form:options items="${fecha}" itemValue="fechaCalculo"
												itemLabel="fechaCalculo"></form:options>
										</form:select>
									</div>
								</div>
								<button type="submit" class="btn default green">Buscar</button>
							</form:form>
						</div>

						<br />
						<form:form id="exporta_calculos" action="exportarCalculosImss" method="GET">
							<table id="tablaConsultaII"
								class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
								<thead>
									<tr>
										<th><input type="checkbox" id="todos"></th>
										<th>Periodo</th>
										<th>Total Pagado Patrón</th>
										<th>Total Trabajador</th>
										<th>Total IMSS</th>
										<th>INFONAVIT Patrón</th>
										<th>INFONAVIT Trabajador</th>
										<th>Total INFONAVIT</th>
										<th>Fecha de Cálculo</th>
										<th>Calculado por</th>
										<th>Acción</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<td align="right" colspan=2>Total:</td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td colspan="3"></td>
									</tr>
								</tfoot>
								<tbody>
									<c:forEach var="a" items="${acciones}">
										<tr>
											<td><input type="checkbox" name="calculos_imss"
												value="${a.id}"></td>
											<td><fmt:formatDate value="${a.periodoInicio}"
													pattern="dd/MM/yyyy" />-<fmt:formatDate
													value="${a.periodoFin}" pattern="dd/MM/yyyy" /></td>
											<td><fmt:setLocale value="es_MX" scope="session" /> <fmt:formatNumber
													value="${a.totalPatron}" type="currency" currencySymbol="$" /></td>
											<td><fmt:setLocale value="es_MX" scope="session" /> <fmt:formatNumber
													value="${a.totalTrabajador}" type="currency" currencySymbol="$" /></td>
											<td><fmt:setLocale value="es_MX" scope="session" /> <fmt:formatNumber
													value="${a.totalIMSS}" type="currency" currencySymbol="$" /></td>
											<td><fmt:setLocale value="es_MX" scope="session" /> <fmt:formatNumber
													value="${a.infonavitPatron}" type="currency" currencySymbol="$" /></td>
											<td><fmt:setLocale value="es_MX" scope="session" /> <fmt:formatNumber
													value="${a.infonavitTrabajador}" type="currency" currencySymbol="$" /></td>
											<td><fmt:setLocale value="es_MX" scope="session" /> <fmt:formatNumber
													value="${a.totalInfonavit}" type="currency" currencySymbol="$" /></td>
											<td><fmt:formatDate value="${a.fechaCalculo}"
													pattern="dd/MM/yyyy" /></td>
											<td>${a.nombres} ${a.aPaterno} ${a.aMaterno}</td>
											<td><a class="btn btn-sm btn-success consulta-calculo" data-id="${a.id}">
													<i class="fa fa-search"></i> Consultar
												</a></td>
									</c:forEach>
								</tbody>
							</table>
						</form:form>

						<div class="form-actions">
							<sec:authorize access="hasRole('GENERA_REPORTE')">
								<c:if test="${ botonExportar }">
									<button class="btn green-jungle" id="exportar_multiples_imss">
										<i class="fa fa-arrow-circle-o-down"></i> EXPORTAR CÁLCULOS
									</button>
								</c:if>
							</sec:authorize>
						</div>
					</div>


				</div>
			</div>
		</div>

		<!-- Modal Consulta única -->
		<div class="modal fade" tabindex="-1" role="dialog" id="modalConsulta">
			<div class="modal-dialog modal-full" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h3 class="modal-title">Cálculo IMSS / INFONAVIT</h3>
					</div>

					<div class="modal-body">

						<div class="row">
							<div class="col-md-12" id="periodoTxt"></div>
						</div>

						<div class="row">
							<div class="col-md-6" id="fechaCalculoTxt"></div>
							<div class="col-md-6" id="usuarioCalculoTxt"></div>
						</div>

						<table id="consulta_calculo_imss"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th rowspan="2">Clave Agente</th>
									<th colspan="8" style="text-align: center">CUOTA PATRONAL
										IMSS</th>
									<th colspan="5" style="text-align: center">CUOTA
										TRABAJADOR IMSS</th>
									<th rowspan="2">Total IMSS</th>
									<th rowspan="2">INFONAVIT Patrón</th>
									<th rowspan="2">INFONAVIT Trabajador</th>
									<th rowspan="2">Total INFONAVIT</th>
								</tr>
								<tr>
									<th>Cuota Fija</th>
									<th>Excedente 3UMA</th>
									<th>Prestaciones en Dinero</th>
									<th>Gastos Med. Pens.</th>
									<th>RT</th>
									<th>Guardería G y PS</th>
									<th>Inv y Vida</th>
									<th>Total Patrón</th>
									<th>Excedente 3 UMA</th>
									<th>Prestaciones en Dinero</th>
									<th>Gastos Med. Pens.</th>
									<th>Inv y Vida</th>
									<th>Total Trabajador</th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>

					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
						<sec:authorize access="hasRole('GENERA_REPORTE')">
							<a class="btn green-jungle" id="exportar_calculo"> <i
								class="fa fa-download"></i> Exportar información
							</a>
						</sec:authorize>
					</div>
				</div>
			</div>
		</div>

	</tiles:putAttribute>

	<tiles:putAttribute name="scripts">
		<script
			src="<c:url value='/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js'/>">
			
		</script>
		<script
			src="<c:url value='/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.es.min.js'/>">
			
		</script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/plugins/datatables/media/js/jquery.dataTables.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/plugins/datatables/extensions/ColReorder/js/dataTables.colReorder.min.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/plugins/datatables/extensions/Scroller/js/dataTables.scroller.min.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/global/scripts/jquery.spring-friendly.js'/>"></script>
		<script
			src="<c:url value='/assets/global/plugins/bootstrap-select/bootstrap-select.min.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/assets/csa/js/moment.js'/>"></script>
		<script
			src="<c:url value='/assets/global/plugins/select2/select2.min.js'/>"></script>
		<script
			src="<c:url value='/assets/csa/js/consultas-imss.js'/>"></script>
	</tiles:putAttribute>


	<tiles:putAttribute name="ready"> 
		$('#calculosMain').addClass("start active open");
		$('#tablaConsultaII').DataTable();
		$('#consultaIMSS').addClass("active");
		
		$('.date-picker').datepicker({
			autoclose:true,
			language:'es'
		});
			
		$('#numColaborador').select2({
			allowClear: true,
               escapeMarkup: function (m) {
               		return m;
            }				 
		});	
		
		$('#fechaCalculo').select2({
			allowClear: true,
               escapeMarkup: function (m) {
               		return m;
            }				 
		});
		
		$('#consulta_calculo_imss').DataTable({
	        "paging":   false,
	        "ordering": false,
	        "info":     false,
	        "searching": false
    	});
    	
	    //Mostrar Totales en footer de Tabla
		var filas = document.querySelectorAll("#tablaConsultaII tbody tr");
	
		var totalP = 0;
		var totalT = 0;
		var totalIMSS = 0;
	
		var totalIP = 0;
		var totalIT = 0;
		var totalI = 0;
		
		// recorremos cada una de las filas
		filas.forEach(function (e) {
		  // obtenemos las columnas de cada fila
		  var columnas = e.querySelectorAll("td");
	
		  //obtenemos los valores de la cantidad y importe
		  var tPatron = parseFloat(columnas[2].textContent.replace(/,/g,"").replace("$",""));
		  var tTrabajador = parseFloat(columnas[3].textContent.replace(/,/g,"").replace("$",""));
		  var tIMSS = parseFloat(columnas[4].textContent.replace(/,/g,"").replace("$",""));
	
		  var IP = parseFloat(columnas[5].textContent.replace(/,/g,"").replace("$",""));
		  var IT = parseFloat(columnas[6].textContent.replace(/,/g,"").replace("$",""));
		  var tI = parseFloat(columnas[7].textContent.replace(/,/g,"").replace("$",""));
	
		  totalP += tPatron;
		  totalT += tTrabajador;
		  totalIMSS += tIMSS;
	
		  totalIP += IP;
		  totalIT += IT;
		  totalI += tI;
		});
	
		// mostramos la suma total
		var filas = document.querySelectorAll("#tablaConsultaII tfoot tr td");
		filas[1].textContent = ft.format(totalP.toFixed(2));
		filas[2].textContent = ft.format(totalT.toFixed(2));
		filas[3].textContent = ft.format(totalIMSS.toFixed(2));
	
		filas[4].textContent = ft.format(totalIP.toFixed(2));
		filas[5].textContent = ft.format(totalIT.toFixed(2));
		filas[6].textContent = ft.format(totalI.toFixed(2));
		
   </tiles:putAttribute>

	<tiles:putAttribute name="footer">
		<c:import url="/WEB-INF/views/template/footer.jsp"></c:import>
	</tiles:putAttribute>

</tiles:insertDefinition>