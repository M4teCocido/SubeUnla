<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import = "modelo.fichadas.TransaccionSUBE" %>
<%@ page import = "util.FuncionesGregorian" %>
<%@ page import = "java.util.List" %>
<% List<TransaccionSUBE> transacciones = (List) request.getAttribute("transacciones") ;
for (TransaccionSUBE transaccion : transacciones){ %>
	<tr> 
		<td> <%= FuncionesGregorian.traerFechaCorta(transaccion.getFichada().getFechaHora()) %> </td>
		<td> <%= transaccion.getTarjeta().getCodigo() %> </td> 
		<td> <%= transaccion.getImporte() %> </td> 
	</tr > 
<% } %>
