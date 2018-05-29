<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import = "modelo.fichadas.colectivo.InternoColectivo" %>
<%@ page import = "java.util.List" %>
<option class="black-text text-lighten-3" value="" disabled selected>Elija un Interno</option>
<% List<InternoColectivo> internos = (List<InternoColectivo>)request.getAttribute("lstInternos") ;
for (InternoColectivo interno:internos){ %>
	<option class="black-text text-lighten-3" 
		value = <%= interno.getIdInterno() %>> 
		Interno - <%= interno.getNombre() %> 
	</option > 
	
<% } %>
