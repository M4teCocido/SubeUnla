<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import = "modelo.fichadas.colectivo.TramoColectivo" %>
<%@ page import = "java.util.List" %>
<option class="black-text text-lighten-3" value="" disabled selected>Elija un Tramo</option>
<% List<TramoColectivo> tramos = (List<TramoColectivo>)request.getAttribute("lstTramos") ;
for (TramoColectivo tramo:tramos){ %>
	<option class="black-text text-lighten-3" 
		value = <%= tramo.getIdTramo() %>> 
		Tramo - <%= tramo.getNombre() %> - <%= tramo.getPrecio().setScale(2).toString() %> 
	</option > 
	
<% } %>
