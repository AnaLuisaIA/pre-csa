<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<span class="username username-hide-on-mobile">
	<sec:authentication property="principal.nombreCompleto"/>
</span>
<i class="fa fa-angle-down"></i>
</a>
<ul class="dropdown-menu dropdown-menu-default">
	<li>
		<p class="text-muted text-center"><sec:authentication property="principal.rol"/></p>
	</li>	
	<li class="divider">
	</li>
	
	<!-- 
	<li>	
		<a href="<c:url value="lock"></c:url>">
		<i class="icon-lock"></i> Bloquear </a>
	</li>
	
	-->
	
	<li>
		<a href="<c:url value="/j_spring_security_logout"></c:url>">
		<i class="icon-key"></i> Cerrar sesi&oacute;n </a>
	</li>
</ul>
