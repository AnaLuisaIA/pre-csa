<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<li id="solicitudesMain"><a href="javascript:;"> <i
		class="icon-doc"></i> <span class="title">Calculos</span> <span
		class="selected"></span> <span class="arrow"></span>
</a>

	<ul class="sub-menu">
		<li id="solicitudesMenu"><a href="<c:url value='/'/>"> <i
				class=""> </i> Calculos
		</a></li>
		<li id="consultaIMSS"><a href="<c:url value='/'/>"> <i
				class=""></i> Consulta IMSS e INFONAVIT
		</a></li>
		<li id="consultaISN"><a href="<c:url value='/'/>"> <i
				class=""></i> Consulta ISN
		</a></li>

	</ul></li>