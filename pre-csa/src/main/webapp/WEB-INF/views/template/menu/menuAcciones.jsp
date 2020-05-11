<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<li id="solicitudesMain">
	<a href="javascript:;">
		<i class="icon-doc"></i>
		<span class="title">Solicitudes</span>
		<span class="selected"></span>
		<span class="arrow"></span>
	</a>

	<ul class="sub-menu">
		<li id="solicitudesMenu">
			<a href="<c:url value='/solicitudes/'/>">
				<i class="fa fa-search"></i>
				Consultar solicitudes</a>
		</li>

		<sec:authorize access="hasAnyRole('CREAR_SOL')">
			<li id="nuevaSolicitud">
				<a href="<c:url value='/solicitudes/crear'/>">
					<i class="fa fa-plus-square"></i>
					Nueva solicitud</a>
			</li>
		</sec:authorize>

		<sec:authorize access="hasAnyRole('CARGAR_PAGOS')">
			<li id="pagosMenu">
				<a href="<c:url value='/pagos/'/>">
					<i class="fa fa-upload"></i>
					Cargar pagos</a>
			</li>
		</sec:authorize>
	</ul>
</li>