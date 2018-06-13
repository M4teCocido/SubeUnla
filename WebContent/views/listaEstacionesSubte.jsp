<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import = "modelo.fichadas.subte.EstacionSubte" %>
<%@ page import = "java.util.List" %>
<% List<EstacionSubte> estaciones = (List)request.getAttribute("lstEstaciones") ;
for (EstacionSubte estacion:estaciones){ %>
	<option class="black-text text-lighten-3" 
		value = "<%= estacion.getIdEstacion() %>"> 
		Estacion - <%= estacion.getNombre() %> 
	</option > 
	
<% } %>
