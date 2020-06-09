<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="styles">
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
			href="<c:url value='/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css'/>"
			rel="stylesheet" type="text/css" />
		<link href="<c:url value='/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css'/>"
			rel="stylesheet" type="text/css" />
	</tiles:putAttribute>
	<tiles:putAttribute name="nav">
		<li><a href='<c:url value="/"/>'>Consultas IMSS e INFONAVIT</a> <i
			class="fa fa-angle-right"></i></li>
	</tiles:putAttribute>
	<meta charset="UTF-8">
	<tiles:putAttribute name="title">Consultas IMSS e INFONAVIT</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="row">

			<div class="col-md-12">
				<div class="portlet box green-jungle">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>Consultas IMSS e INFONAVIT
						</div>
						<div class="actions">
							<a href="alta" class="btn default green-stripe"> <i
								class="fa fa-plus"></i> <span class="hidden-480">Exportar
									Seleccionados</span>
							</a>
						</div>
					</div>
					<div class="portlet-body">
						<div align="justify">
							<form:form id="buscarCalculoImss" modelAttribute="calculoIMSS" method="POST"
								class="form-inline" action="buscarCalculoImss">

								<div class="form-group">
								<label>Periodo: </label>
									<label>Del: </label>
									<div class="input-group input-medium date date-picker" data-date-format="dd/mm/yyyy"
										data-date-end-date="0d">
										<span class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
										<form:input path="fechaInicio" type="text" class="form-control" readonly="true"
											maxlenght="10" placeholder="Del..." />
									</div>

									<label>Al: </label>
									<div class="input-group input-medium date date-picker" data-date-format="dd/mm/yyyy"
										data-date-end-date="0d">
										<span class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
										<form:input path="fechaFin" type="text" class="form-control" readonly="true"
											maxlenght="10" placeholder="Al..." />
									</div>

											<form:select path="numColaborador" class="form-control" name="numColaborador" required="true">
												<form:option value=""></form:option>
												<c:forEach var="u" items="${usuario}">
													<form:option value="${u.numColaborador}"><c:out value="${u.nombres} ${u.aPaterno} ${u.aMaterno}"/></form:option>
											 	</c:forEach>
											</form:select>

									<div class="form-group">
											<form:select path="fechaCalculo" class="form-control"
												name="fechaCalculo" required="true">
												<form:option value=""></form:option>
												<form:option value="">01/01/2020</form:option>
												<form:options items="${fecha}" itemValue="fechaCalculo" itemLabel="fechaCalculo"></form:options>
											</form:select>
									</div>
								</div>
								<button type="submit" class="btn default green">Buscar</button>
							</form:form>
						</div>
						<br/>
						<table id="tablaConsultaII" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>Seleccionar Todos</th>
									<th>Período</th>
									<th>Total Pagado Patrón</th>
									<th>Total Trabajador</th>
									<th>Total IMSS</th>
									<th>INFONAVIT Patrón</th>
									<th>INFONAVIT Trabajador</th>
									<th>Total INFONAVIT</th>
									<th>Fecha de Cálculo</th>
									<th>Calculado por</th>
								</tr>
							 </thead>
							 
							<tbody>
								<c:forEach var="a" items="${acciones}">
									<tr>
									<td><div align="center"><input type="checkbox" id="cbox2"  value="second_checkbox"></div></td>
										<td><fmt:formatDate value="${a.periodoInicio}" pattern="dd/MM/yyyy" />-<fmt:formatDate value="${a.periodoFin}" pattern="dd/MM/yyyy" /></td>
										<td>${a.totalPatron}</td>
										<td>${a.totalTrabajador}</td>
										<td>${a.totalIMSS}</td>
										<td>${a.infonavitPatron}</td>
										<td>${a.infonavitTrabajador}</td>
										<td>${a.totalInfonavit}</td>
										<td><fmt:formatDate value="${a.fechaCalculo}" pattern="dd/MM/yyyy" /></td>
										<td>${a.nombres} ${a.aPaterno} ${a.aMaterno}</td>
								</c:forEach>
							</tbody>

						</table>
					</div>


				</div>
			</div>
		</div>
	</tiles:putAttribute>

	<tiles:putAttribute name="scripts">
		<script src="<c:url value='/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js'/>">
		</script>
		<script
			src="<c:url value='/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.es.min.js'/>">
		</script>
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
			<script
			src="<c:url value='/assets/global/plugins/bootstrap-select/bootstrap-select.min.js'/>"></script>
		<script
			src="<c:url value='/assets/global/plugins/select2/select2.min.js'/>"></script>

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
			placeholder: "Calculado Por:",
			allowClear: true,
               escapeMarkup: function (m) {
               		return m;
            }				 
		});	
		
		$('#fechaCalculo').select2({
			placeholder: "Fecha Calculo:",
			allowClear: true,
               escapeMarkup: function (m) {
               		return m;
            }				 
		});
   </tiles:putAttribute>

	<tiles:putAttribute name="footer">
		<c:import url="/WEB-INF/views/template/footer.jsp"></c:import>
	</tiles:putAttribute>

</tiles:insertDefinition>