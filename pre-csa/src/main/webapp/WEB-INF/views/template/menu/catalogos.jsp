<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@page pageEncoding="UTF-8"%>

<sec:authorize
	access="hasAnyRole('CONSULTA_CAT', 'EDITA_CAT', 'CREA_CAT', 'CONSULTA_USER', 'EDITA_USER', 'CREA_USER')">

	<li id="catalogos"><a href="javascript:;"> <i
			class="icon-folder"></i> <span class="title">Catálogos</span> <span
			class="selected"></span> <span class="arrow"></span>
	</a>

		<ul class="sub-menu">

			<li id="variablesMenu"><a href="<c:url value='/variables/'/>">
					<i class="fa fa-list-alt"></i> Variables IMSS INFONAVIT
			</a></li>

			<li id="tasasMenu"><a href="<c:url value='/tasas/'/>"> <i
					class="fa fa-list-alt"></i> Tasas Sobre Nómina
			</a></li>

			<li id="calendariosMenu"><a href="<c:url value='/calendario/'/>">
					<i class="fa fa-calendar-o"></i> Calendarios
			</a></li>

			<sec:authorize access="hasAnyRole('CONSULTA_USER', 'EDITA_USER', 'CREA_USER')">
				<li id="usuariosMenu"><a href="<c:url value='/usuarios/'/>">
						<i class="fa fa-users"></i> Usuarios
				</a></li>
			</sec:authorize>

		</ul></li>

</sec:authorize>
