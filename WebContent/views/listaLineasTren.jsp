<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import = "modelo.fichadas.tren.LineaTren" %>
<%@ page import = "java.util.List" %>
<option class="black-text text-lighten-3" value="" disabled selected>Elija una linea</option>
<% List<LineaTren> lineas = (List)request.getAttribute("lineas") ;
for (LineaTren linea:lineas){ %>
	<option class="black-text text-lighten-3" 
		value = " <%= linea.getIdLinea() %> "> 
		<%= linea.getNombre() %> 
	</option > 
	
<% } %>
<option class="black-text text-lighten-3" value="1">A</option>
<option class="black-text text-lighten-3" value="2">B</option>
<option class="black-text text-lighten-3" value="3">C</option>