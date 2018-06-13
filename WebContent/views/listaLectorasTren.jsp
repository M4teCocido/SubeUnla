<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import = "modelo.lectoras.LectoraTren" %>
<%@ page import = "java.util.List" %>
<% List<LectoraTren> lectoras = (List<LectoraTren>)request.getAttribute("lstLectoras") ;
for (LectoraTren lectora : lectoras){ %>
	<option class="black-text text-lighten-3" 
		value = <%= lectora.getIdLectora() %>> 
		Lectora - <%= lectora.getNroSerie() %> - <%= (lectora.isEsEntrada() ? " (E)" : " (S)") %>  
	</option > 
	
<% } %>
