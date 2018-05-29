<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import = "modelo.fichadas.subte.LineaSubte" %>
<%@ page import = "java.util.List" %>
<option class="black-text text-lighten-3" value="" disabled selected>Elija una linea</option>
<% List<LineaSubte> lineas = (List)request.getAttribute("lstLineas") ;
for (LineaSubte linea:lineas){ %>
	<option class="black-text text-lighten-3" 
		value = "<%= linea.getIdLinea() %>"> 
		<%= linea.getNombre() %> 
	</option > 
	
<% } %>

