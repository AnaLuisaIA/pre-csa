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
				<i class="fa fa-calendar-o"></i> Variables IMSS INFONAVIT
		</a>
		</li>

		<li id="catalogosMenu"><a href="<c:url value='/usuarios/'/>">
				<i class="fa fa-calendar-o"></i> Tasas Sobre Nómina
		</a>
		</li>

		<li id="catalogosMenu"><a href="<c:url value='/usuarios/'/>">
				<i class="fa fa-calendar-o"></i> Calendario ISN
		</a>
		</li>

		<li id="catalogosMenu"><a href="<c:url value='/usuarios/'/>">
				<i class="fa fa-users"></i> Usuarios
		</a>
	</ul>
	</li>
