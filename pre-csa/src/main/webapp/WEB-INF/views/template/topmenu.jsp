<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page pageEncoding="UTF-8" %>

<div class="top-menu">
	<ul class="nav navbar-nav pull-right">
		<!-- BEGIN NOTIFICATION DROPDOWN -->
		<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
		<!-- <li class="dropdown dropdown-extended dropdown-notification" id="header_notification_bar">
			<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
			<i class="icon-bell"></i> -->
<!-- 			<span class="badge badge-default">
<!-- 				1 -->
<!-- 			</span> -->
<!-- 		</a>
			<ul class="dropdown-menu">
				<li class="external">
					<h3>Notificaciones <span class="bold"> pendientes</span></h3>
<!-- 					<a href="extra_profile.html">view all</a> -->
<!-- 			</li>
<!-- 				<li> -->
<!-- 					<ul class="dropdown-menu-list scroller" style="height: 250px;" data-handle-color="#637283"> -->
												
<!-- 					</ul> -->
<!-- 				</li> -->
<!--		</ul>
			
		</li>

		<!-- END TODO DROPDOWN -->
		<li class="dropdown dropdown-user">
			<h4 style="color: #397B39;
				font-weight: 400;" class="dropdown-toggle">
				Carga Social de Agentes (CSA)</h4>
		</li>

		<!-- BEGIN USER LOGIN DROPDOWN -->
		<li class="dropdown dropdown-user">
			<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
			<i class="fa fa-user" aria-hidden="true"></i>
			<c:import url="/WEB-INF/views/template/loginbar.jsp"></c:import>
		</li>
		<!-- END USER LOGIN DROPDOWN -->
		
		<!-- BEGIN QUICK SIDEBAR TOGGLER -->
		<li class="dropdown dropdown-quick-sidebar-toggler">
			<a href="<c:url value="/j_spring_security_logout"></c:url>" class="dropdown-toggle">
			<i class="icon-logout"></i>
			</a>
		</li>
		<!-- END QUICK SIDEBAR TOGGLER -->
	</ul>
</div>