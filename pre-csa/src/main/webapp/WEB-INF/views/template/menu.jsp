<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page pageEncoding="UTF-8"%>

<ul class="page-sidebar-menu " data-keep-expanded="false"
	data-auto-scroll="true" data-slide-speed="200">
	<!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->

	<li class="inicio"><a href="<c:url value="/"/>"> <i
			class="icon-home"></i> <span class="title">Inicio</span> <span
			class="selected"></span> <span class="open"></span>
	</a></li>

	<li class="heading">
		<h3 class="uppercase">Opciones</h3>
	</li>

	<!-- MenÃº de solicitudes-->
	<c:import url="/WEB-INF/views/template/menu/menuAcciones.jsp"></c:import>

	<!-- MenÃº de catÃ¡logos -->
	<c:import url="/WEB-INF/views/template/menu/catalogos.jsp"></c:import>

	<!-- Menï¿½ de Bitï¿½cora del sistema -->
	<sec:authorize access="hasRole('CONSULTA_CAT')">
		<li id="bitacora"><a href="javascript:;"> <i
				class="icon-folder"></i> <span class="title">Bitácoras</span> <span
				class="selected"></span> <span class="arrow"></span>
		</a>

			<ul class="sub-menu">

				<li id="bitacoraMenu"><a href="<c:url value='/bitacora/'/>">
						<i class="fa fa-list-alt"></i> Bitácora del sistema
				</a></li>

			</ul></li>
	</sec:authorize>


</ul>