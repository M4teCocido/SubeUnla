<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import = "modelo.lectoras.LectoraExterna" %>
<%@ page import = "java.util.List" %>
<% List<LectoraExterna> lectoras = (List<LectoraExterna>)request.getAttribute("lstLectoras") ;
for (LectoraExterna lectora : lectoras){ %>
	<option class="black-text text-lighten-3" 
		value = <%= lectora.getIdLectora() %>> 
		Lectora - <%= lectora.getUbicacion() %> 
	</option > 
	
<% } %>
