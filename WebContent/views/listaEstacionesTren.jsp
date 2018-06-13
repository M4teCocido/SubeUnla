<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import = "modelo.fichadas.tren.EstacionTren" %>
<%@ page import = "java.util.List" %>
<% List<EstacionTren> estaciones = (List)request.getAttribute("lstEstaciones") ;
for (EstacionTren estacion:estaciones){ %>
	<option class="black-text text-lighten-3" 
		value = "<%= estacion.getIdEstacion() %>"> 
		Estacion - <%= estacion.getNombre() %> 
	</option > 
	
<% } %>
