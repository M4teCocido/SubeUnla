<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "modelo.Usuario" %>
<%@ page import = "modelo.Permiso" %>
<%@ page import = "negocio.PermisoABM" %>
<%@ page import = "java.util.Set" %>

<%@ include file = "/views/header.jsp" %>
</head>
<body>
	<% Usuario usuario = (Usuario) request.getAttribute("usuario"); %>
	<% Set<Permiso> permisos = (Set<Permiso>) request.getAttribute("permisos"); %>
	<% PermisoABM abm = new PermisoABM(); 
	
		<div class="section no-pad-bot">
			<div class="row">
				<div class="col l2">
					<br>
					<br>
					<a class="btn-floating tooltipped btn-medium waves-effect waves-light blue lighten-2" data-position="bottom" data-tooltip="Volver!" href="index.jsp"><i class="material-icons">arrow_back</i></a>
				</div>
				<div class="col l8">
					<h3 class="header center orange-text">Panel de control</h3>
				</div>
			</div>
			<div class="row center">
				<h5 class="header col s12 light">Reportes, estadisticas y ABM.</h5>
			</div>
		</div>

	Permiso permisoSubeABM = abm.traerPermisoPorCodigo("SUBEABM");
	Permiso permisoReportes = abm.traerPermisoPorCodigo("REPORTES");
	Permiso permisoEstadisticas = abm.traerPermisoPorCodigo("ESTADISTICAS");

	%>
	<div class="section no-pad-bot" id="index-banner">
		<div class="container">
			<br><br>
			<h1 class="header center orange-text">¡Bienvenido <%= usuario.getNombreUsuario() %>!</h1>
			<div class="row center">
				<h5 class="header col s12 light">Panel de Administrador</h5>
	
			</div>
		<div class="container">
	<div class="row">
		
		<% if(permisos.contains(permisoSubeABM)){ %>
			<div class="col s3 center-align">
				<a class="waves-effect waves-light" href="/SubeUnla/AdministrarSubes"><i class="large material-icons">flash_on</i></a>
				<h6>Sube ABM</h6>
				<p>Ingresar, modificar o eliminar Tarjetas del sistema.</p>
			</div>
		<% } %>
		
		<% if(permisos.contains(permisoReportes)){ %>
			<div class="col s3 center-align">
				<a class="waves-effect waves-light" href="/SubeUnla/Listados"><i class="large material-icons">assessment</i></a>
				<h6>Reportes</h6>
				<p>Bueno, son reportes. ¿Que mas puedo decir?</p>
			</div>
		<% } %>
		
		<% if(permisos.contains(permisoEstadisticas)){ %>
			<div class="col s3 center-align">
				<a class="waves-effect waves-light" href="/SubeUnla/Estadisticas"><i class="large material-icons">equalizer</i></a>
				<h6>Estadisticas</h6>
				<p>Las estadisticas indican que las estadisticas no sirven.</p>
			</div>
		<% } %>
	</div>
<%@ include file = "/views/footer.jsp" %>
</body>
</html>