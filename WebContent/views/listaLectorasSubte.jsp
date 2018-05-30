<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import = "modelo.lectoras.LectoraSubte" %>
<%@ page import = "java.util.List" %>
<option class="black-text text-lighten-3" value="" disabled selected>Elija una Lectora</option>
<% List<LectoraSubte> lectoras = (List<LectoraSubte>)request.getAttribute("lstLectoras") ;
for (LectoraSubte lectora : lectoras){ %>
	<option class="black-text text-lighten-3" 
		value = <%= lectora.getIdLectora() %>> 
		Lectora - <%= lectora.getNroSerie() %> 
	</option > 
	
<% } %>
