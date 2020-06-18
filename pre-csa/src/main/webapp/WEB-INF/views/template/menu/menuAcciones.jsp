<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@page pageEncoding="UTF-8"%>

<sec:authorize
	access="hasAnyRole('CONSULTA_REPORTE', 'GENERA_REPORTE', 'GENERA_OP', 'CIERRA_CALCULO')">

	<li id="calculosMain"><a href="javascript:;"> <i
			class="icon-calculator"></i> <span class="title">C&aacute;lculos</span> <span
			class="selected"></span> <span class="arrow"></span>
	</a>

		<ul class="sub-menu">

			<sec:authorize access="hasRole('GENERA_OP')">
				<li id="calculosMenu"><a href="<c:url value='/calculos/'/>">
						<i class="fa fa-calculator"> </i> Realizar c&aacute;lculos
				</a></li>
			</sec:authorize>

			<sec:authorize
				access="hasAnyRole('CONSULTA_REPORTE', 'GENERA_REPORTE', 'CIERRA_CALCULO')">
				<li id="consultaIMSS"><a href="<c:url value='/calculos/consultaImss'/>"> <i
						class="fa fa-search"></i> Consulta C&aacute;lculos IMSS e INFONAVIT
				</a></li>
				<li id="consultaISN"><a href="<c:url value='/calculos/consultaISN'/>"> <i
						class="fa fa-search"></i> Consulta C&aacute;lculos ISN
				</a></li>
			</sec:authorize>

		</ul></li>

</sec:authorize>