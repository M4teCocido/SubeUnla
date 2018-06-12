<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import = "modelo.fichadas.TransaccionSUBE" %>
<%@ page import = "modelo.fichadas.tren.ViajeEfectivoTren" %>
<%@ page import = "modelo.fichadas.tren	.FichadaTren" %>
<%@ page import = "util.FuncionesGregorian" %>
<%@ page import = "java.util.List" %>

	
<thead>
	<tr>
		<th>Fecha y Hora</th>
		<th>Nro. Tarjeta</th>
		<th>Linea</th>
		<th>Estacion Origen</th>
		<th>Estacion Destino</th>
		<th>Monto</th>
	</tr>
</thead>

<tbody>
	
	<% List<ViajeEfectivoTren> viajes = (List) request.getAttribute("viajes") ;
	for (ViajeEfectivoTren viaje : viajes){ %>
	
	<%  String color = "black";
		float monto = viaje.getImporte().floatValue() * -1f;
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
		
		String estacionEntrada = "Ninguna";
		String estacionSalida = "Ninguna";
		
		if (viaje.getEntrada() != null){
			estacionEntrada = ((FichadaTren) viaje.getEntrada().getFichada()).getEstacion().getNombre(); 
		}
		
		if (viaje.getSalida() != null){
			estacionSalida = ((FichadaTren) viaje.getSalida().getFichada()).getEstacion().getNombre(); 
		}
		
		String linea = viaje.getLinea().getNombre();
		
	%>
	<tr> 
		<td> <%= FuncionesGregorian.traerFechaCorta(viaje.getFechaHora()) %> </td>
		<td> <%= viaje.getTarjeta().getCodigo() %> </td>
		<td> <%= linea %> </td>
		<td> <%= estacionEntrada %> </td>
		<td> <%= estacionSalida %> </td> 
		<td> <font color="<%=color%>"> <%=prefix%><%= monto %> </font></td> 
	</tr> 
<% } %>
</tbody>
	
	

