<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import = "modelo.fichadas.TransaccionSUBE" %>
<%@ page import = "util.FuncionesGregorian" %>
<%@ page import = "java.util.List" %>

	
<thead>
	<tr>
		<th>Fecha y Hora</th>
		<th>Nro. Tarjeta</th>
		<th>Monto</th>
	</tr>
</thead>

<tbody>
	
	<% List<TransaccionSUBE> transacciones = (List) request.getAttribute("transacciones") ;
	for (TransaccionSUBE transaccion : transacciones){ %>
	
	<%  String color = "black";
		float monto = transaccion.getImporte().floatValue() * -1f;
		String prefix = "";
		if (monto < 0){
			color = "red";
			prefix = "";
		} else if (monto > 0){
			color = "green";
			prefix = "+";
		} else {
			color = "black";
			prefix = "";
		}
	%>
	<tr> 
		<td> <%= FuncionesGregorian.traerFechaCorta(transaccion.getFichada().getFechaHora()) %> </td>
		<td> <%= transaccion.getTarjeta().getCodigo() %> </td> 
		<td> <font color="<%=color%>"> <%=prefix%><%= monto %> </font></td> 
	</tr> 
<% } %>
</tbody>
	
	

