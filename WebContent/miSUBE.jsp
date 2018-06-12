<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "modelo.Usuario" %>
<%@ page import = "modelo.Persona" %>
<%@ page import = "negocio.TarjetaSubeABM" %>
<%@ page import = "modelo.TarjetaSube" %>
<%@ page import = "util.IndexableSet" %>

<%@ page import = "java.util.Set" %>

<%@ include file = "/views/header.jsp" %>
<% Usuario usuario = (Usuario) request.getAttribute("usuario"); %>
<% Persona persona = usuario.getPersona(); %>
<% Set<TarjetaSube> tarjetas = persona.getTarjetasAsociadas(); %>
<% TarjetaSube tarjeta = IndexableSet.get(tarjetas, tarjetas.size() - 1); %>

</head>
<body>
	<div class="row">
        <div class="col l2">
        	<br>
        	<br>
        	<a class="btn-floating tooltipped btn-medium waves-effect waves-light blue lighten-2" data-position="bottom" data-tooltip="Volver!" href="index.jsp"><i class="material-icons">arrow_back</i></a>
        </div>
        <div class="col l8">
            <h5 class="header col s12 light"><%= persona.getNombre() %> <%= persona.getApellido() %></h5>
        </div>
	</div>
	<div class="container">
		<h6>Informacion General:</h6>
		<div class="row">
			<div class="col l6">Tarjeta</div>
			<div class="col l3">Saldo</div>
			<div class="col l3">Dar de baja por:</div>
		</div>
		<div class="row">
			<div class="col l6">****-****-****-<%= tarjeta.getCodigo().substring(12) %></div>
			<div class="col l3">$ <%= tarjeta.getSaldo() %></div>
			<div class="col l3">Dar de baja por:</div>
		</div>
	

	        
		<h6>Movimientos:</h6>
		
	</div>

<%@ include file = "/views/footer.jsp" %>