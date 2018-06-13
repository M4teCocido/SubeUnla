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
			<br><br>
		</div>
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
</div>
<%@ include file = "/views/footer.jsp" %>
</body>
</html>