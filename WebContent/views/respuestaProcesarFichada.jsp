<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import = "modelo.TarjetaSube" %>
<% TarjetaSube.Resultado r = (TarjetaSube.Resultado)request.getAttribute("resultado"); %>
<% String strAprobado = (r.isAprobado() ? "¡Fichada Exitosa!" : "¡Fichada Fallida!"); %>
<div class="modal-content">
	<h4 align="center" id="headerModal"> <%= strAprobado %> </h4>
	<p align="center" id="pModal"><%= r.getMensaje() %> </p>
	</div>
	<div class="modal-footer">
	<a href="#!" class="modal-close waves-effect waves-green btn-flat">Cerrar</a>
</div>
	

