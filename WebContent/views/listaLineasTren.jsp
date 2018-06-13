<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import = "modelo.fichadas.tren.LineaTren" %>
<%@ page import = "java.util.List" %>
<% List<LineaTren> lineas = (List)request.getAttribute("lstLineas") ;
for (LineaTren linea:lineas){ %>
	<option class="black-text text-lighten-3" 
		value = "<%= linea.getIdLinea() %>"> 
		Linea - <%= linea.getNombre() %> 
	</option > 
	
<% } %>
