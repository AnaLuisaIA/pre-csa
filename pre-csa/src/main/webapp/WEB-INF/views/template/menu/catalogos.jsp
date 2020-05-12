<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@page pageEncoding="UTF-8"%>

<li id="catalogos"><a href="javascript:;"> <i
		class="icon-folder"></i> <span class="title">Catálogos</span> <span
		class="selected"></span> <span class="arrow"></span>
</a>

	<ul class="sub-menu">
		<li id="catalogosMenu"><a href="<c:url value='/usuarios/'/>">
				<i class="fa fa-users"></i> Usuarios
		</a></li>

		<li id="catalogosMenu"><a href="<c:url value='/usuarios/'/>">
				<i class="fa fa-list-alt"></i> Variables
		</a></li>

		<li id="catalogosMenu"><a href="<c:url value='/usuarios/'/>">
				<i class="fa fa-list-alt"></i> Tasas Sobre Nómina
		</a></li>

		<li id="catalogosMenu"><a href="<c:url value='/usuarios/'/>">
				<i class="fa fa-calendar-o"></i> Calendarios
		</a></li>

		<sec:authorize access="hasAnyRole('ALTA_ROL')">
			<li id="rolesMenu"><a href="<c:url value='/roles/'/>"> <i
					class="fa fa-tags"></i> Roles
			</a></li>
		</sec:authorize>

		<sec:authorize
			access="hasAnyRole('SUPER ADMINISTRADOR', 'ADMINISTRADOR')">
			<li id="correosMenu"><a href="<c:url value='/correo/'/>"> <i
					class="fa fa-envelope"></i> Correos
			</a></li>
		</sec:authorize>

	</ul></li>