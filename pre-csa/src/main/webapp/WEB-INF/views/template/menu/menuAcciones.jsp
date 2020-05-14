<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	<%@page pageEncoding="UTF-8"%>

<li id="calculosMain"><a href="javascript:;"> <i
		class="icon-calculator"></i> <span class="title">Cálculos</span> <span
		class="selected"></span> <span class="arrow"></span>
</a>

	<ul class="sub-menu">
		<li id="calculoMenu"><a href="<c:url value='/calculos/'/>"> <i
				class="fa fa-calculator"> </i> Cálculos
		</a></li>
		<li id="consultaIMSS"><a href="<c:url value='/imss/'/>"> <i
				class="fa fa-search"></i> Consulta Cálculos IMSS e INFONAVIT
		</a></li>
		<li id="consultaISN"><a href="<c:url value='/isn/'/>"> <i
				class="fa fa-search"></i> Consulta Cálculos ISN
		</a></li>

	</ul></li>