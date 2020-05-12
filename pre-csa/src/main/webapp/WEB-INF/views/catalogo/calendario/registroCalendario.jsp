<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="title">Cargar Calendario ISN</tiles:putAttribute>
	
	<tiles:putAttribute name="nav">
		<li><a href='<c:url value="/solicitudes/"/>'>Calendario ISN</a> <i class="fa fa-angle-right"></i></li>	
	</tiles:putAttribute>	
	
		<tiles:putAttribute name="body">
		<%@include file="../../secciones/messages.jsp"%>
		<div class="row">

			<div class="col-md-8 col-md-offset-2">
				<div class="portlet box green-jungle">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-file"></i>Cargar Calendario ISN
						</div>
						
					</div>
					<div class="portlet-body">
						<form method="POST">
							
							<div class="row">
								<div class="col-md-10">
									<label>Selecciona el archivo que contiene el Calendario a Importar:</label>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a class="btn default btn-sm"><i class="fa fa-arrow-down"></i>Descargar Layout</a>
									<input type="file" accept=".xlsm" class="form-control-file"/>
								<div class="col-md-10">
									<div class="col-md-10">
										
								</div>
								</div>	
								</div>								
							</div>
							
							<br>
							 <div class="form-actions">
                             	<button type="submit" class="btn btn-primary" id="btnGuardar">Guardar</button>
                                <a href="<c:url value="/solicitudes/"/>" type="button"
                                   class="btn default">Cancelar</a>
                             </div>
						</form>
					</div>
		
		
				</div>
			</div>
		</div>		
	</tiles:putAttribute>	
	
	<tiles:putAttribute name="ready"> 
		$('#solicitudesMain').addClass("start active open")
		$('#nuevaSolicitud').addClass("active");
		
	</tiles:putAttribute>
	
	<tiles:putAttribute name="footer">
		<div class="page-footer-inner">							  
			2019 &copy; <a href="http://www.segurosargos.com/" title="Seguros Argos" target="_blank">Seguros Argos</a>
		</div>
		<div class="scroll-to-top">
			<i class="icon-arrow-up"></i>
		</div>
	</tiles:putAttribute>
	
</tiles:insertDefinition>