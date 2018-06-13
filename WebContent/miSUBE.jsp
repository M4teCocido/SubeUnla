<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import = "modelo.Usuario" %>
<%@ page import = "modelo.Persona" %>
<%@ page import = "modelo.fichadas.TransaccionSUBE" %>
<%@ page import = "modelo.fichadas.colectivo.FichadaColectivo" %>
<%@ page import = "modelo.fichadas.subte.FichadaSubte" %>
<%@ page import = "modelo.fichadas.tren.FichadaTren" %>
<%@ page import = "modelo.lectoras.LectoraExterna" %>
<%@ page import = "util.FuncionesGregorian" %>
<%@ page import = "negocio.TarjetaSubeABM" %>
<%@ page import = "modelo.TarjetaSube" %>
<%@ page import = "util.IndexableSet" %>

<%@ page import = "java.util.Set" %>

<%@ include file = "/views/header.jsp" %>
<% Usuario usuario = (Usuario) request.getAttribute("usuario"); %>
<% Persona persona = usuario.getPersona(); %>
<% Set<TarjetaSube> tarjetas = persona.getTarjetasAsociadas(); %>
<% TarjetaSube tarjeta = IndexableSet.get(tarjetas, tarjetas.size() - 1); %>

</head>
<body>
	<div class="row">
        <div class="col l2">
        	<br>
        	<br>
        	<a class="btn-floating tooltipped btn-medium waves-effect waves-light blue lighten-2" data-position="bottom" data-tooltip="Volver!" href="index.jsp"><i class="material-icons">arrow_back</i></a>
        </div>
        <div class="col l8">
            <h5 class="header col s12 light"><%= persona.getNombre() %> <%= persona.getApellido() %></h5>
        </div>
	</div>
	<div class="container">
		<h6>Informacion General:</h6>
		<div class="row">
			<div class="col l6">Tarjeta</div>
			<div class="col l3">Saldo</div>
			<div class="col l3">Dar de baja</div>
		</div>
		<div class="row">
			<div class="col l6">****-****-****-<%= tarjeta.getCodigo().substring(12) %></div>
			<div class="col l3">$ <%= tarjeta.getSaldo() %></div>
			<div class="col l3">
				<button class="btn waves-effect waves-light blue lighten-2 black-text col l3" codigo="<%= tarjeta.getCodigo() %>" type="submit" id="bajaTarjeta">Aqui!<i class="material-icons right">clear</i>
  				</button>
  			</div>
		</div>
		<h6>Movimientos:</h6>
		<table>
	        <thead>
	       		<tr>
	              	<th>Fecha y Hora</th>
	              	<th>Tipo</th>
	              	<th>Medio</th>
	              	<th>Detalle</th>
	              	<th>Valor</th>
	          	</tr>
	        </thead>
	        <tbody id="movimientos">
	        	<% for(TransaccionSUBE transaccion : tarjeta.getTransacciones()){ %>
	        		<tr>
            			<td><%= FuncionesGregorian.traerFechaCortaHora(transaccion.getFichada().getFechaHora()) %></td>
           	 			<td>
           	 			<% 
           	 			String tipo = "";
           	 			if (transaccion.getFichada() instanceof FichadaColectivo){
           	 				tipo = "Boleto Colectivo";
           	 			}else if(transaccion.getFichada() instanceof FichadaSubte){
           	 				tipo = "Boleto Subte";
           	 			}else if (transaccion.getFichada() instanceof FichadaTren){
           	 				tipo = "Boleto Tren";
           	 			}else{
           	 				tipo = "Recarga";
           	 			} %><%= tipo %>
           	 			</td>
            			<td>
           				<% 
           	 			String medio = "";
           	 			if (transaccion.getFichada() instanceof FichadaColectivo){
           	 				FichadaColectivo fichada = (FichadaColectivo) transaccion.getFichada();
           	 				medio = fichada.getInterno().getLineaColectivo().getNombre();
           	 			}else if(transaccion.getFichada() instanceof FichadaSubte){
           	 				FichadaSubte fichada = (FichadaSubte) transaccion.getFichada();
           	 				medio = fichada.getEstacionSubte().getLineaSubte().getNombre();
           	 			}else if (transaccion.getFichada() instanceof FichadaTren){
           	 				FichadaTren fichada = (FichadaTren) transaccion.getFichada();
           	 				medio = fichada.getEstacion().getLinea().getNombre();
           	 			}else{
           	 				LectoraExterna lectora = (LectoraExterna) transaccion.getFichada().getLectora();
           	 				medio = lectora.getUbicacion();
           	 			} %><%= medio %>
            			</td>
            			<td>
            			<% 
           	 			String detalle = "";
           	 			if (transaccion.getFichada() instanceof FichadaColectivo){
           	 				FichadaColectivo fichada = (FichadaColectivo) transaccion.getFichada();
           	 				detalle = fichada.getInterno().getNombre();
           	 			}else if(transaccion.getFichada() instanceof FichadaSubte){
           	 				FichadaSubte fichada = (FichadaSubte) transaccion.getFichada();
           	 				detalle = fichada.getEstacionSubte().getNombre();
           	 			}else if (transaccion.getFichada() instanceof FichadaTren){
           	 				FichadaTren fichada = (FichadaTren) transaccion.getFichada();
           	 				detalle = fichada.getEstacion().getNombre();
           	 			}else{
           	 				LectoraExterna lectora = (LectoraExterna) transaccion.getFichada().getLectora();
           	 				detalle = lectora.getUbicacion();
           	 			} %><%= detalle %></td>
            			<td>$ <%= transaccion.getImporte() %></td>
          			</tr>
	        	<% } %>
	        </tbody>
      	</table>
	</div>

<%@ include file = "/views/footer.jsp" %>

</body>
</html>