<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import = "modelo.fichadas.TransaccionSUBE" %>
<%@ page import = "modelo.fichadas.Fichada" %>
<%@ page import = "modelo.fichadas.colectivo.FichadaColectivo" %>
<%@ page import = "modelo.fichadas.subte.FichadaSubte" %>
<%@ page import = "modelo.fichadas.tren.FichadaTren" %>
<%@ page import = "util.FuncionesGregorian" %>
<%@ page import = "java.util.List" %>

	
<thead>
	<tr>
		<th>Fecha y Hora</th>
		<th>Nro. Tarjeta</th>
		<th>Tipo</th>
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
		String tipo = "";
		Fichada f = transaccion.getFichada();
		if (f instanceof FichadaColectivo)
			tipo = "COLECTIVO";
		else if (f instanceof FichadaSubte)
			tipo = "SUBTE";
		else if (f instanceof FichadaTren)
			tipo = "TREN";
		else 
			tipo = "Recarga";
				
	%>
	<tr> 
		<td> <%= FuncionesGregorian.traerFechaCorta(transaccion.getFichada().getFechaHora()) %> </td>
		<td> <%= transaccion.getTarjeta().getCodigo() %> </td>
		<td> <%= tipo %> </td>  
		<td> <font color="<%=color%>"> <%=prefix%><%= monto %> </font></td> 
	</tr> 
<% } %>
</tbody>
	
	

