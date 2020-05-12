<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page pageEncoding="UTF-8" %>

    <li id="catalogos">
        <a href="javascript:;">
            <i class="icon-folder"></i>
            <span class="title">Catalogos</span>
            <span class="selected"></span>
            <span class="arrow"></span>
        </a>

        <ul class="sub-menu">
                <li id="catalogosMenu">
                    <a href="<c:url value='/variables/'/>">
                        <i class=""></i>
                        Variables IMSS/INFONAVIT </a>
                </li>
                <li id="TSN">
                    <a href="<c:url value='/'/>">
                        <i class=""></i>
                        Tasa Sobre Nomina</a>
                </li>
                <li id="calendarioISN">
                    <a href="<c:url value='/'/>">
                        <i class=""></i>
                        Calendario ISN</a>
                </li>
                <li id="calendarioISN">
                    <a href="<c:url value='/'/>">
                        <i class=""></i>
                        Usuarios</a>
                </li>
        </ul>
    </li>