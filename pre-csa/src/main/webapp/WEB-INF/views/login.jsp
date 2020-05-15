<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">

<head>
	<meta charset="utf-8" />
	<title>
		<fmt:message key="messages.title" /> | Seguros Argos</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta content="Seguros Argos Web App" name="description" />
	<meta content="Seguros Argos" name="author" />
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet"
		type="text/css" />
	<link href="assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	<link href="assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
	<link href="assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" />
	<!-- END GLOBAL MANDATORY STYLES -->
	<!-- BEGIN PAGE LEVEL STYLES -->
	<link href="assets/global/plugins/select2/select2.css" rel="stylesheet" type="text/css" />
	<link href="assets/admin/pages/css/login-soft.css" rel="stylesheet" type="text/css" />
	<!-- END PAGE LEVEL SCRIPTS -->
	<!-- BEGIN THEME STYLES -->
	<link href="assets/global/css/components.css" id="style_components" rel="stylesheet" type="text/css" />
	<link href="assets/global/css/plugins.css" rel="stylesheet" type="text/css" />
	<link href="assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css" />
	<!-- END THEME STYLES -->
	<link rel="shortcut icon" href="<c:url value='/assets/admin/layout/img/favicon.ico'></c:url>" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->

<body class="login">
	<!-- BEGIN LOGO -->
	<div class="logo">
		<a href="<c:url value=" init"></c:url>">
			<img src="assets/admin/layout/img/logo-big.png" alt="" />
		</a>
	</div>
	<!-- END LOGO -->

	<!-- BEGIN LOGIN -->
	<div class="content">

		<!-- BEGIN LOGIN FORM -->
		<form class="login-form" action="<c:url value='j_spring_security_check' />" method="post" id="loginSac">
			<h3 class="form-title text-center">Bienvenido al
				<fmt:message key="messages.title" />
			</h3>
			
			<!-- Mensaje de alerta cuando los campos no están completos
			<div class="alert alert-danger display-hide">
				<button class="close" data-close="alert"></button>
				<span>
					Los campos usuario y contraseña son necesarios</span>
			</div> -->

			<!-- Mensaje de error que aparece cuando las credenciales de acceso son incorrectas -->
			<c:choose>
			<c:when test="${param.error == true}">
				<div id="error" class="alert alert-danger" role="alert">
					<fmt:message key="messages.badCredentials" />
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
			</c:when>
			<c:when test="${param.error == 'failedRecovery'}">
				<div id="error" class="alert alert-danger" role="alert">
					<fmt:message key="messages.badRecovery" />
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
			</c:when>
			</c:choose>
			
			<!-- Mensajes de éxito cuando se solicita el reestablecimiento de la contraseña -->
			<c:choose>
				<c:when test="${param.scss == 'recoverySent'}">
					<div id="success" class="alert alert-success" role="alert">
						<fmt:message key="messages.recoverySent" />
						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
				</c:when>
				<c:when test="${param.scss == 'resetPassword'}">
					<div id="success" class="alert alert-success" role="alert">
						<fmt:message key="messages.resetPassword" />
						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
				</c:when>
			</c:choose>

			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">Usuario</label>
				<div class="input-icon">
					<i class="fa fa-user"></i>
					<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Usuario"
						name="j_username" required="required" autofocus />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">Password</label>
				<div class="input-icon">
					<i class="fa fa-key"></i>
					<input class="form-control placeholder-no-fix" type="password" autocomplete="off"
						placeholder="Password" name="j_password" required="required" />
				</div>
			</div>
			<div class="form-actions">
				<label class="checkbox">
				</label>
				<button type="submit" class="btn green pull-right">
					Entrar <i class="m-icon-swapright m-icon-white"></i>
				</button>
			</div>
			<div class="forget-password">
				<h4>Olvidaste tu contraseña</h4>
				<p>
					Recupera tu contraseña haciendo clic <a href="javascript:;" id="forget-password">
						aqui </a>
					.
				</p>
			</div>

		</form>
		<!-- END LOGIN FORM -->
		<!-- BEGIN FORGOT PASSWORD FORM -->
		<form class="forget-form" action="<c:url value ="/recovery/initRecovery"/>" method="post">
			<h3 class="text-center">Recuperar contraseña</h3>
			<p>
				Ingresa tu <strong>número de colaborador</strong> o <strong>nombre de usuario</strong>
				 de fuerza de ventas / asistente comercial para recuperar tu contraseña.
			</p>
			<div class="form-group">
				<div class="input-icon">
					<i class="fa fa-envelope"></i>
					<input class="form-control placeholder-no-fix" type="text" autocomplete="off"
						placeholder="..." name="numColab" required />
				</div>
			</div>
			<div class="form-actions">
				<button type="button" id="back-btn" class="btn">
					<i class="m-icon-swapleft"></i> Regresar </button>
				<button type="submit" class="btn green pull-right">
					Enviar <i class="m-icon-swapright m-icon-white"></i>
				</button>
			</div>
		</form>
		<!-- END FORGOT PASSWORD FORM -->

	</div>
	<!-- END LOGIN -->
	<!-- BEGIN COPYRIGHT -->
	<div class="copyright">
		<p class="font-weight-bolder">SEGUROS ARGOS</p>
	</div>
	<!-- END COPYRIGHT -->
	<!-- BEGIN CORE PLUGINS -->
	<script src="assets/global/plugins/jquery.min.js" type="text/javascript"></script>
	<script src="assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
	<script src="assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
	<script src="assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
	<script src="assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
	<script src="assets/global/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="assets/global/plugins/select2/select2.min.js"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="assets/global/scripts/metronic.js" type="text/javascript"></script>
	<script src="assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
	<script src="assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
	<script src="assets/admin/pages/scripts/login.js" type="text/javascript"></script>

	<!-- END PAGE LEVEL SCRIPTS -->
	<script>
		jQuery(document).ready(function () {

			$('.alert').alert();

			Metronic.init({
				context: "<c:url value='/assets/'/>"
			});
			Layout.init(); // init current layout
			Login.init();

			// init background slide images
			$.backstretch([

				"assets/admin/pages/media/bg/ag05.jpg",
				"assets/admin/pages/media/bg/ag06.jpg",
				"assets/admin/pages/media/bg/ag22.jpg",
				"assets/admin/pages/media/bg/ag23.jpg",
				"assets/admin/pages/media/bg/ag24.jpg",
				"assets/admin/pages/media/bg/ag26.jpg",
				"assets/admin/pages/media/bg/ag14.jpg",
				"assets/admin/pages/media/bg/ag15.jpg",
				"assets/admin/pages/media/bg/ag16.jpg",
				"assets/admin/pages/media/bg/ag17.jpg",
				"assets/admin/pages/media/bg/ag34.jpg",
				"assets/admin/pages/media/bg/ag35.jpg",
				"assets/admin/pages/media/bg/ag32.jpg",
				"assets/admin/pages/media/bg/ag07.jpg",
				"assets/admin/pages/media/bg/ag25.jpg",
				"assets/admin/pages/media/bg/ag08.jpg",
				"assets/admin/pages/media/bg/ag09.jpg",
				"assets/admin/pages/media/bg/ag01.jpg",
				"assets/admin/pages/media/bg/ag02.jpg",
				"assets/admin/pages/media/bg/ag03.jpg",
				"assets/admin/pages/media/bg/ag04.jpg",
				"assets/admin/pages/media/bg/ag10.jpg",
				"assets/admin/pages/media/bg/ag11.jpg",
				"assets/admin/pages/media/bg/ag12.jpg",
				"assets/admin/pages/media/bg/ag13.jpg",
				"assets/admin/pages/media/bg/ag14.jpg",
				"assets/admin/pages/media/bg/ag15.jpg",
				"assets/admin/pages/media/bg/ag16.jpg",
				"assets/admin/pages/media/bg/ag17.jpg",
				"assets/admin/pages/media/bg/ag18.jpg",
				"assets/admin/pages/media/bg/ag19.jpg",
				"assets/admin/pages/media/bg/ag20.jpg",
				"assets/admin/pages/media/bg/ag21.jpg",
				"assets/admin/pages/media/bg/ag22.jpg",
				"assets/admin/pages/media/bg/ag23.jpg",
				"assets/admin/pages/media/bg/ag24.jpg",
				"assets/admin/pages/media/bg/ag26.jpg",
				"assets/admin/pages/media/bg/ag27.jpg",
				"assets/admin/pages/media/bg/ag28.jpg",
				"assets/admin/pages/media/bg/ag29.jpg",
				"assets/admin/pages/media/bg/ag30.jpg",
				"assets/admin/pages/media/bg/ag31.jpg",
				"assets/admin/pages/media/bg/ag33.jpg",

			], {
				fade: 1000,
				duration: 5000
			});
		});
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->

</html>