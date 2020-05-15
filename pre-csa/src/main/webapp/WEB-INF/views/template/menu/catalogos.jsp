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
<<<<<<< HEAD
		<li id="catalogosMenu"><a href="<c:url value='/variable/'/>">
				<i class="fa fa-calendar-o"></i> Variables IMSS INFONAVIT
		</a>
		</li>

		<li id="tasaMenu"><a href="<c:url value='/tasaSobreNomina/'/>">
				<i class="fa fa-calendar-o"></i> Tasas Sobre Nómina
		</a>
		</li>

		<li id="calendarioMenu"><a href="<c:url value='/calendario/'/>">
				<i class="fa fa-calendar-o"></i> Calendario ISN
		</a>
		</li>

		<li id="usuariosMenu"><a href="<c:url value='/usuarios/'/>">
				<i class="fa fa-users"></i> Usuarios
		</a>
		</li>
=======
	
		<li id="catalogosMenu"><a
			href="<c:url value='variables/IMSS-INFONAVIT'/>"> <i
				class="fa fa-list-alt"></i> Variables IMSS INFONAVIT
		</a></li>
>>>>>>> branch 'master' of https://github.com/AnaLuisaIA/pre-csa.git

<<<<<<< HEAD
	</ul>
	</li>
=======
		<li id="catalogosMenu"><a href="<c:url value='/usuarios/'/>">
				<i class="fa fa-list-alt"></i> Tasas Sobre Nómina
		</a></li>

		<li id="calendariosMenu"><a href="<c:url value='/calendario/'/>">
				<i class="fa fa-calendar-o"></i> Calendarios
		</a></li>

		<li id="usuariosMenu"><a href="<c:url value='/usuarios/'/>">
				<i class="fa fa-users"></i> Usuarios
		</a></li>


	</ul></li>
>>>>>>> branch 'master' of https://github.com/AnaLuisaIA/pre-csa.git
