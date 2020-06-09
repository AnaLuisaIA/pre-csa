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
			href="<c:url value='/assets/global/plugins/datatables/extensions/ColReorder/css/dataTables.colReorder.min.css'/>"
			rel="stylesheet" type="text/css" />
		<link
			href="<c:url value='/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css'/>"
			rel="stylesheet" type="text/css" />
		<link
			href="<c:url value='/assets/global/plugins/select2/select2.css'/>"
			rel="stylesheet" type="text/css" />
		<link href="<c:url value='/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css'/>"
			rel="stylesheet" type="text/css" />
	</tiles:putAttribute>
	<tiles:putAttribute name="nav">
		<li><a href='<c:url value="/"/>'>Consultas ISN</a> <i
			class="fa fa-angle-right"></i></li>
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
						<div class="actions">
							<a href="alta" class="btn default green-stripe"> <i
								class="fa fa-plus"></i> <span class="hidden-480">Exportar Seleccionados</span>
							</a>
						</div>
					</div>
					<div class="portlet-body">
					<div align="justify">
							<form:form id="buscarCalculoISN" modelAttribute="calculoISN" method="POST"
								class="form-inline" action="buscarCalculoISN">

								<div class="form-group">
									<form:select path="claveAgente" class="form-control" name="claveAgente" required="true">
										<form:option value=""></form:option>
										<form:options items="${clave}" itemValue="claveAgente" itemLabel="claveAgente"></form:options>
									</form:select>
									<label>Periodo: </label>
									<label>Del: </label>
									<div class="input-group input-medium date date-picker" data-date-format="dd/mm/yyyy"
										data-date-end-date="0d">
										<span class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
										<form:input path="fechaI" type="text" class="form-control" readonly="true"
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
										<form:input path="fechaF" type="text" class="form-control" readonly="true"
											maxlenght="10" placeholder="Al..." />
									</div>
									<form:select path="fechaCalculo" class="form-control" name="fechaCalculo" required="true">
											<form:option value=""></form:option>
											<form:option value="">01/01/2020</form:option>
											<form:options items="${fechaCalculo}" itemValue="fechaCalculo" itemLabel="fechaCalculo"></form:options>
									</form:select>
									<form:select path="numColaborador" class="form-control" name="numColaborador" required="true">
										<form:option value=""></form:option>
											<c:forEach var="u" items="${usuario}">
													<form:option value="${u.numColaborador}"><c:out value="${u.nombres} ${u.aPaterno} ${u.aMaterno}"/></form:option>
											 </c:forEach>
										</form:select>
								</div>
								<button type="submit" class="btn default green">Buscar</button>
							</form:form>
						</div>
						<br/>
						<table id="tablaConsultasISN"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>Seleccionar Todos</th>
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
            						<td></td>
            						<td></td>
            						<td></td>
       						 	</tr>
   							 </tfoot>
							<tbody>
								<c:forEach var="a" items="${acciones}">
									<tr>
									<td><div align="center"><input type="checkbox" id="cbox2"  value="second_checkbox"></div></td>
										<td>${a.claveAgente}</td>
										<td><fmt:formatDate value="${a.fechaInicio}" pattern="dd/MM/yyyy" />-<fmt:formatDate value="${a.fechaFin}" pattern="dd/MM/yyyy" /></td>
										<td>${a.localidad}</td>
										<td>${a.tasa}</td>
										<td>${a.baseGravable}</td>
										<td>${a.isnMensual}</td>
										<td>${a.isnSemanal}</td>
										<td>${a.nombres} ${a.aPaterno} ${a.aMaterno}</td>
										<td><fmt:formatDate value="${a.fechaCalculo}" pattern="dd/MM/yyyy" /></td>
										<td><a href="#" class="btn btn-primary btn-small"><i class="fa fa-edit"></i></a></td>
								</c:forEach>
							</tbody>

						</table>
					</div>


				</div>
			</div>
		</div>
	</tiles:putAttribute>

	<tiles:putAttribute name="scripts">

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
		<script src="<c:url value='/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js'/>">
		</script>
		<script
			src="<c:url value='/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.es.min.js'/>">
		</script>

<script>
			/**
			 * funcion que realiza los calculos
			 */
			$(document).ready(function(){
  				//tu codigo cuando se hace click sobre un tr

    			// obtenemos todas las filas del tbody
    			var filas=document.querySelectorAll("#tablaConsultasISN tbody tr");
 
    			var totalbase=0;
    			var totalISNM =0;
    			var totalISNS =0;
 
    			// recorremos cada una de las filas
    			filas.forEach(function(e) {
 
       				 // obtenemos las columnas de cada fila
       				 var columnas=e.querySelectorAll("td");
 
        			//obtenemos los valores de la cantidad y importe
       				var base=parseFloat(columnas[5].textContent);
        			var ISNM=parseFloat(columnas[6].textContent);
        			var ISNS=parseFloat(columnas[7].textContent);
        			totalbase+=base;
        			totalISNM+=ISNM;
        			totalISNS+=ISNS;
    			});
 
    			// mostramos la suma total
    			var filas=document.querySelectorAll("#tablaConsultasISN tfoot tr td");
    			filas[1].textContent=totalbase.toFixed(2);
    			filas[2].textContent=totalISNM.toFixed(2);
    			filas[3].textContent=totalISNS.toFixed(2);
		});
</script>
	</tiles:putAttribute>


	<tiles:putAttribute name="ready"> 
		$('#calculosMain').addClass("start active open");
		$('#tablaConsultasISN').DataTable();
		$('#consultaISN').addClass("active");
		
		$('.date-picker').datepicker({
		autoclose:true,
		language:'es'
		});
		
		$('#claveAgente').select2({
			placeholder: "Clave Agente:",
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
		
		$('#numColaborador').select2({
			placeholder: "Calculado por:",
			allowClear: true,
               escapeMarkup: function (m) {
               		return m;
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