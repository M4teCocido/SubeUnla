<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import = "modelo.TarjetaSube" %>
<% TarjetaSube.Resultado r = (TarjetaSube.Resultado)request.getAttribute("resultado"); 

	<div class="modal-content">
	<h4 id="headerModal">¡Fichada Exitosa!</h4>
	<p id="pModal">Mensaje</p>
	</div>
	<div class="modal-footer">
	<a href="#!" class="modal-close waves-effect waves-green btn-flat">Cerrar</a>
	</div>
	
%>
