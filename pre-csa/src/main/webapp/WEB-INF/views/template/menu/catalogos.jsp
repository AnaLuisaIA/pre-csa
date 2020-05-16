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

		<li id="variablesMenu"><a
			href="<c:url value='variables/IMSS-INFONAVIT'/>"> <i
				class="fa fa-list-alt"></i> Variables IMSS INFONAVIT
		</a></li>

		<li id="tasasMenu"><a href="<c:url value='/usuarios/'/>"> <i
				class="fa fa-list-alt"></i> Tasas Sobre Nómina
		</a></li>

		<li id="calendariosMenu"><a href="<c:url value='/calendario/'/>">
				<i class="fa fa-calendar-o"></i> Calendarios
		</a></li>

		<li id="usuariosMenu"><a href="<c:url value='/usuarios/'/>">
				<i class="fa fa-users"></i> Usuarios
		</a></li>


	</ul></li>

