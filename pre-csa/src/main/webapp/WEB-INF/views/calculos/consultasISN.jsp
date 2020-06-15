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
	<tiles:putAttribute name="nav">
		<li><a href='<c:url value="/calculo/consultaIsn"/>'>Consultas
				ISN</a> <i class="fa fa-angle-right"></i></li>
	</tiles:putAttribute>
	<meta charset="UTF-8">
	<tiles:putAttribute name="title">Consultas ISN</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="row">

			<div class="col-md-12">
				<div class="portlet box green-jungle">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>Consultas ISN
						</div>
						<div class="actions"></div>
					</div>
					<div class="portlet-body">
						<div align="right">
							<form:form id="buscarCalculoISN" modelAttribute="calculoISN"
								method="POST" class="form-inline" action="buscarCalculoISN">

								<div class="form-group">
									<form:select path="claveAgente" class="form-control"
										name="claveAgente">
										<form:option value="">TODOS LOS AGENTES</form:option>
										<form:options items="${clave}" itemValue="claveAgente"
											itemLabel="claveAgente"></form:options>
									</form:select>

									<form:select path="idSemana" class="form-control"
										name="idSemana">
										<form:option value="">TODAS LAS SEMANAS</form:option>
										<c:forEach var="s" items="${semanas}">
											<form:option value="${s.id}">
												<fmt:formatDate value="${s.fechaInicio}"
													pattern="dd/MM/yyyy" /> - <fmt:formatDate
													value="${s.fechaFin}" pattern="dd/MM/yyyy" />												
											</form:option>
										</c:forEach>
									</form:select>
									
									<form:select path="fechaCalculo" class="form-control"
										name="fechaCalculo">
										<form:option value="">TODAS LAS FECHAS DE CÁLCULO</form:option>
										<form:options items="${fechaCalculo}" itemValue="fechaCalculo"
											itemLabel="fechaCalculo"></form:options>
									</form:select>
									
									<form:select path="numColaborador" class="form-control"
										name="numColaborador">
										<form:option value="">TODOS LOS COLABORADORES</form:option>
										<c:forEach var="u" items="${usuario}">
											<form:option value="${u.numColaborador}">
												<c:out value="${u.nombres} ${u.aPaterno} ${u.aMaterno}" />
											</form:option>
										</c:forEach>
									</form:select>
									
								</div>
								<button type="submit" class="btn default green">Buscar</button>
							</form:form>
						</div>

						<br />

						<form:form id="exporta_calculos" action="exportarCalculosIsn" method="GET">
							<table id="tablaConsultasISN"
								class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
								<thead>
									<tr>
										<th><input type="checkbox" id="todos"></th>
										<th>Clave Agente</th>
										<th>Período</th>
										<th>Localidad</th>
										<th>Tasa</th>
										<th>Base Gravable</th>
										<th>ISN Mensual</th>
										<th>ISN Semanal</th>
										<th>Relizó Cálculo</th>
										<th>Fecha Elaboración</th>
										<th>Acción</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<td align="right" colspan=5>Total:</td>
										<td></td>
										<td></td>
										<td></td>
										<td colspan="3"></td>
									</tr>
								</tfoot>
								<tbody>
									<c:forEach var="a" items="${acciones}">
										<tr>
											<td><input type="checkbox" name="calculos_isn"
												value="${a.id}"></td>
											<td>${a.claveAgente}</td>
											<td><fmt:formatDate value="${a.fechaInicio}"
													pattern="dd/MM/yyyy" /> - <fmt:formatDate
													value="${a.fechaFin}" pattern="dd/MM/yyyy" /></td>
											<td>${a.localidad}</td>
											<td>${a.tasa}</td>
											<td><fmt:setLocale value="es_MX" scope="session" /> <fmt:formatNumber
													value="${a.baseGravable}" type="currency" currencySymbol="$" /></td>
											<td><fmt:setLocale value="es_MX" scope="session" /> <fmt:formatNumber
													value="${a.isnMensual}" type="currency" currencySymbol="$" /></td>
											<td><fmt:setLocale value="es_MX" scope="session" /> <fmt:formatNumber
													value="${a.isnSemanal}" type="currency" currencySymbol="$" /></td>
											<td>${a.nombres} ${a.aPaterno} ${a.aMaterno}</td>
											<td><fmt:formatDate value="${a.fechaCalculo}"
													pattern="dd/MM/yyyy" /></td>
											<td><a class="btn btn-sm btn-success consulta-calculo"
												data-id="${a.id}" data-mes="${a.mes}"> <i class="fa fa-search"></i>
													Consultar
											</a></td>
									</c:forEach>
								</tbody>

							</table>
						</form:form>
						
						<div class="form-actions">
							<sec:authorize access="hasRole('GENERA_REPORTE')">
								<c:if test="${ botonExportar }">
									<button class="btn green-jungle" id="exportar_multiples_isn">
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
						<h3 class="modal-title">Cálculo ISN</h3>
					</div>

					<div class="modal-body">

						<table id="consulta_calculo_isn"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>Clave Agente</th>
									<th>Nombre</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
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
		<script src="<c:url value='/assets/csa/js/consultas-isn.js'/>"></script>

		<script>
			/**
			 * funcion que realiza los calculos
			 */
			$(document)
					.ready(
							function() {
								//tu codigo cuando se hace click sobre un tr

								// obtenemos todas las filas del tbody
								var filas = document
										.querySelectorAll("#tablaConsultasISN tbody tr");

								var totalbase = 0;
								var totalISNM = 0;
								var totalISNS = 0;

								// recorremos cada una de las filas
								filas
										.forEach(function(e) {

											// obtenemos las columnas de cada fila
											var columnas = e
													.querySelectorAll("td");

											//obtenemos los valores de la cantidad y importe
											var base = parseFloat(columnas[5].textContent.replace(/,/g,"").replace("$",""));
											var ISNM = parseFloat(columnas[6].textContent.replace(/,/g,"").replace("$",""));
											var ISNS = parseFloat(columnas[7].textContent.replace(/,/g,"").replace("$",""));
											totalbase += base;
											totalISNM += ISNM;
											totalISNS += ISNS;
										});

								// mostramos la suma total
								var filas = document
										.querySelectorAll("#tablaConsultasISN tfoot tr td");
								filas[1].textContent = ft.format(totalbase.toFixed(2));
								filas[2].textContent = ft.format(totalISNM.toFixed(2));
								filas[3].textContent = ft.format(totalISNS.toFixed(2));
							});
		</script>
	</tiles:putAttribute>


	<tiles:putAttribute name="ready"> 
		$('#calculosMain').addClass("start active open");
		$('#tablaConsultasISN').DataTable();
		$('#consultaISN').addClass("active");
		
		$('#claveAgente').select2({
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
		
		$('#numColaborador').select2({
			allowClear: true,
               escapeMarkup: function (m) {
               		return m;
            }				 
		});
		
		$('#idSemana').select2({
			allowClear: true,
               escapeMarkup: function (m) {
               		return m;
            }				 
		});
		
		$('#consulta_calculo_isn').DataTable({
    		"paging":   false,
    	    "ordering": false,
    	    "info":     false,
    	    "searching": false
        });
		
   </tiles:putAttribute>

	<tiles:putAttribute name="footer">
		<c:import url="/WEB-INF/views/template/footer.jsp"></c:import>
	</tiles:putAttribute>

</tiles:insertDefinition>