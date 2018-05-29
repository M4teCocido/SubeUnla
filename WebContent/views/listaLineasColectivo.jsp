<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import = "modelo.fichadas.colectivo.LineaColectivo" %>
<%@ page import = "java.util.List" %>
<option class="black-text text-lighten-3" value="" disabled selected>Elija una linea</option>
<% List<LineaColectivo> lineas = (List<LineaColectivo>)request.getAttribute("lstLineas") ;
for (LineaColectivo linea:lineas){ %>
	<option class="black-text text-lighten-3" 
		value = <%= linea.getIdLinea() %>> 
		<%= linea.getNombre() %> 
	</option > 
	
<% } %>
