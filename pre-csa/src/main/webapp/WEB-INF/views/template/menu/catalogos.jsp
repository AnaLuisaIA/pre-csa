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

		<li id="tasaMenu"><a href="<c:url value='/tasa/'/>">
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
	
		<li id="catalogosMenu"><a
=======

		<li id="variablesMenu"><a
>>>>>>> d38ddb308954a4ace6bcf47df6afd8969673584e
			href="<c:url value='variables/IMSS-INFONAVIT'/>"> <i
				class="fa fa-list-alt"></i> Variables IMSS INFONAVIT
		</a></li>

<<<<<<< HEAD
	</ul>
	</li>

		<li id="catalogosMenu"><a href="<c:url value='/usuarios/'/>">
				<i class="fa fa-list-alt"></i> Tasas Sobre Nómina
=======
		<li id="tasasMenu"><a href="<c:url value='/usuarios/'/>"> <i
				class="fa fa-list-alt"></i> Tasas Sobre Nómina
>>>>>>> d38ddb308954a4ace6bcf47df6afd8969673584e
		</a></li>

		<li id="calendariosMenu"><a href="<c:url value='/calendario/'/>">
				<i class="fa fa-calendar-o"></i> Calendarios
		</a></li>

		<li id="usuariosMenu"><a href="<c:url value='/usuarios/'/>">
				<i class="fa fa-users"></i> Usuarios
		</a></li>


	</ul></li>

