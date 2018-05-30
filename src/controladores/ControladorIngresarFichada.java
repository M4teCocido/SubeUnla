package controladores;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TarjetaSubeDao;
import dao.fichadas.colectivo.LineaColectivoDao;
import dao.fichadas.colectivo.TramoColectivoDao;
import dao.fichadas.tren.EstacionTrenDao;
import dao.lectoras.LectoraColectivoDao;
import dao.lectoras.LectoraDao;
import dao.fichadas.subte.EstacionSubteDao;
import modelo.TarjetaSube;
import modelo.fichadas.FichadaRecarga;
import modelo.fichadas.colectivo.FichadaColectivo;
import modelo.fichadas.colectivo.InternoColectivo;
import modelo.fichadas.colectivo.LineaColectivo;
import modelo.fichadas.colectivo.TramoColectivo;
import modelo.fichadas.subte.EstacionSubte;
import modelo.fichadas.subte.LineaSubte;
import modelo.fichadas.tren.EstacionTren;
import modelo.fichadas.tren.LineaTren;
import modelo.lectoras.Lectora;
import modelo.lectoras.LectoraColectivo;
import modelo.lectoras.LectoraExterna;
import modelo.lectoras.LectoraSubte;
import modelo.lectoras.LectoraTren;
import negocio.LectoraExternaABM;
import negocio.LineaColectivoABM;
import negocio.LineaSubteABM;
import negocio.LineaTrenABM;
import negocio.LineaSubteABM;
import negocio.LineaTrenABM;
import negocio.LectorasExternasABM;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ControladorIngresarFichada extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	private void procesarPeticionTramosColectivo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idLinea = Integer.parseInt(request.getParameter("idLinea"));
		LineaColectivoABM abm = new LineaColectivoABM();
		LineaColectivo linea = abm.traerLineaPorId(idLinea);
		List<TramoColectivo> tramos = new ArrayList<TramoColectivo>();
		tramos.addAll(linea.getTramosColectivo());
		request.setAttribute("lstTramos", tramos);
		request.getRequestDispatcher("views/listaTramosColectivo.jsp").forward(request, response);
	}
	
	private void procesarPeticionLectorasCarga(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LectorasExternasABM abm = new LectorasExternasABM();
		List<LectoraExterna> lstLectora = abm.traerLectoras();
		request.setAttribute("lstLectoras", lstLectora);
		request.getRequestDispatcher("views/listaLectorasExternas.jsp").forward(request, response);
	}
	
	private void procesarPeticionLineasColectivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LineaColectivoABM abm = new LineaColectivoABM();
		List<LineaColectivo> lstLinea = abm.traerLineas(); 
		request.setAttribute( "lstLineas" , lstLinea );
		request.getRequestDispatcher( "views/listaLineasColectivo.jsp" ).forward( request , response );
		System.out.println("Cantidad Lineas Colectivo : " + lstLinea.size());
	}
	
	private void procesarPeticionLineasTren(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LineaTrenABM abm = new LineaTrenABM();
		List<LineaTren> lstLineas = abm.traerLineas();
		request.setAttribute("lstLineas", lstLineas);
		request.getRequestDispatcher("views/listaLineasTren.jsp").forward(request, response);
	}
	
	private void procesarPeticionLineasSubte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LineaSubteABM abm = new LineaSubteABM();
		List<LineaSubte> lstLineas = abm.traerLineas();
		request.setAttribute("lstLineas", lstLineas);
		request.getRequestDispatcher("views/listaLineasSubte.jsp").forward(request, response);
	}
	
	private void procesarPeticionInternosColectivo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idLinea = Integer.parseInt(request.getParameter("idLinea"));
		LineaColectivoABM abm = new LineaColectivoABM();
		List<InternoColectivo> lstInterno = abm.traerInternosPorIdLinea(idLinea);
		request.setAttribute("lstInternos", lstInterno);
		request.getRequestDispatcher("views/listaInternosColectivo.jsp").forward(request, response);
	}
	
	private void procesarPeticionEstacionesSubte(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idLinea = Integer.parseInt(request.getParameter("idLinea"));
		LineaSubteABM abm = new LineaSubteABM();
		LineaSubte linea = abm.traerLineaPorId(idLinea);
		List<EstacionSubte> lstEstacion = new ArrayList<EstacionSubte>();
		lstEstacion.addAll(linea.getRecorridoSubte());
		request.setAttribute("lstEstaciones", lstEstacion);
		request.getRequestDispatcher("views/listaEstacionesSubte.jsp").forward(request, response);
	}
	
	private void procesarPeticionEstacionesTren(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idLinea = Integer.parseInt(request.getParameter("idLinea"));
		LineaTrenABM abm = new LineaTrenABM();
		LineaTren linea = abm.traerLineaPorId(idLinea);
		List<EstacionTren> lstEstacion = new ArrayList<EstacionTren>();
		lstEstacion.addAll(linea.getEstaciones());
		request.setAttribute("lstEstaciones", lstEstacion);
		request.getRequestDispatcher("views/listaEstacionesTren.jsp").forward(request, response);
	}
	
	private void procesarPeticionLectorasSubte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idEstacion = Integer.parseInt(request.getParameter("idEstacion"));
		EstacionSubteDao dao = new EstacionSubteDao();
		EstacionSubte estacion = dao.traerEstacion(idEstacion);
		List<LectoraSubte> lstLectora = new ArrayList<LectoraSubte>();
		lstLectora.addAll(estacion.getLectoras());
		request.setAttribute("lstLectoras", lstLectora);
		request.getRequestDispatcher("views/listaLectorasSubte.jsp").forward(request, response);
	}
	
	private void procesarPeticionLectorasTren(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idEstacion = Integer.parseInt(request.getParameter("idEstacion"));
		EstacionTrenDao dao = new EstacionTrenDao();
		EstacionTren estacion = dao.traerEstacion(idEstacion);
		List<LectoraTren> lstLectora = new ArrayList<LectoraTren>();
		lstLectora.addAll(estacion.getLectoras());
		request.setAttribute("lstLectoras", lstLectora);
		request.getRequestDispatcher("views/listaLectorasTren.jsp").forward(request, response);
	}
	
	private void procesarPeticionCarga(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TarjetaSube tarjeta = this.obtenerTarjetaDesdeRequest(request);
		TarjetaSube.Resultado resultado;
		if (tarjeta != null) {
			int idLectora = Integer.parseInt(request.getParameter("idLectora"));
			int dia = Integer.parseInt(request.getParameter("dia"));
			int mes = Integer.parseInt(request.getParameter("mes"));
			int anio = Integer.parseInt(request.getParameter("anio"));
			int hora = Integer.parseInt(request.getParameter("hora"));
			int min = Integer.parseInt(request.getParameter("min"));
			GregorianCalendar fecha = new GregorianCalendar(anio, mes, dia, hora, min); //Pendiente levantar fecha.
			BigDecimal monto = new BigDecimal(request.getParameter("monto"));
			FichadaRecarga fichada = new FichadaRecarga(fecha, monto, this.obtenerLectora(idLectora));
			resultado = tarjeta.procesarFichada(fichada);
		} else {
			resultado = new TarjetaSube.Resultado(false, "La tarjeta ingresada no existe", null);
		}
		request.setAttribute("resultado", resultado);
		System.out.println("Resultado : " + resultado);
		request.getRequestDispatcher("views/respuestaProcesarFichada.jsp").forward(request, response);
	}
	
	private void procesarPeticionProcesarFichadaColectivo (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TarjetaSube tarjeta = this.obtenerTarjetaPorCodigo(codigo);
		TarjetaSube.Resultado resultado;
		if (tarjeta != null) {
			int idLectora = Integer.parseInt(request.getParameter("idLectora"));
			int idLinea = Integer.parseInt(request.getParameter("idLina"));
			int idInterno = Integer.parseInt(request.getParameter("idInterno"));
			int idTramo = Integer.parseInt(request.getParameter("idTramo"));
			int dia = Integer.parseInt(request.getParameter("dia"));
			int mes = Integer.parseInt(request.getParameter("mes"));
			int anio = Integer.parseInt(request.getParameter("anio"));
			int hora = Integer.parseInt(request.getParameter("hora"));
			int min = Integer.parseInt(request.getParameter("min"));
			GregorianCalendar fecha = new GregorianCalendar(anio, mes, dia, hora, min);
			FichadaColectivo fichada = new FichadaColectivo(fecha, this.obtenerTramoColectivo(idTramo), this.obtenerLectoraColectivo(idLectora));
			resultado = tarjeta.procesarFichada(fichada);
		} else {
			resultado = new TarjetaSube.Resultado(false, "La tarjeta ingresada no existe", null);
		}
		request.setAttribute("resultado", resultado);
		request.getRequestDispatcher("views/respuestaProcesarFichada.jsp").forward(request, response);
	}
	
	private void procesarPeticionProcesarFichadaTren (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codigo = request.getParameter("numerotarjeta");
		TarjetaSube tarjeta = this.obtenerTarjetaPorCodigo(codigo);
		TarjetaSube.Resultado resultado;
		if (tarjeta != null) {
			int idLectora = Integer.parseInt(request.getParameter("idLectora"));
			int idLinea = Integer.parseInt(request.getParameter("idLina"));
			int idInterno = Integer.parseInt(request.getParameter("idInterno"));
			int idTramo = Integer.parseInt(request.getParameter("idTramo"));
			int dia = Integer.parseInt(request.getParameter("dia"));
			int mes = Integer.parseInt(request.getParameter("mes"));
			int anio = Integer.parseInt(request.getParameter("anio"));
			int hora = Integer.parseInt(request.getParameter("hora"));
			int min = Integer.parseInt(request.getParameter("min"));
			GregorianCalendar fecha = new GregorianCalendar(anio, mes, dia, hora, min);
			FichadaColectivo fichada = new FichadaColectivo(fecha, this.obtenerTramoColectivo(idTramo), this.obtenerLectoraColectivo(idLectora));
			resultado = tarjeta.procesarFichada(fichada);
		} else {
			resultado = new TarjetaSube.Resultado(false, "La tarjeta ingresada no existe", null);
		}
		request.setAttribute("resultado", resultado);
		request.getRequestDispatcher("views/respuestaProcesarFichada.jsp").forward(request, response);
	}
	
	private void procesarPeticionProcesarFichadaSubte (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private Lectora obtenerLectora(int idLectora) {
		LectoraDao dao = new LectoraDao();
		return dao.traerLectora(idLectora);
	}
	
	private LectoraColectivo obtenerLectoraColectivo(int idLectora) {
		LectoraColectivoDao dao = new LectoraColectivoDao();
		return dao.traerLectora(idLectora);
	}
	
	private TramoColectivo obtenerTramoColectivo(int idTramo) {
		TramoColectivoDao dao = new TramoColectivoDao();
		return dao.traerTramo(idTramo);
	}
	
	private TarjetaSube obtenerTarjetaDesdeRequest(HttpServletRequest request) {
		String codigo = request.getParameter("nroTarjeta");
		return obtenerTarjetaPorCodigo(codigo);
	}
	
	private TarjetaSube obtenerTarjetaPorCodigo(String codigo) {
		TarjetaSubeDao dao = new TarjetaSubeDao();
		TarjetaSube tarjeta = dao.traerTarjeta(codigo);
		return tarjeta;
	}
	
	private void procesarPeticion(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html;	charset=UTF-8");
		try {
			String strNroValidacion= request.getParameter("nroValidacion");
			if (strNroValidacion == null)
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			else {
				int nroValidacion = Integer.parseInt(strNroValidacion);
				System.out.println("Numero : " + nroValidacion);
				switch(nroValidacion) {
				case 1: //Devolver tramos de colectivo
					this.procesarPeticionTramosColectivo(request, response);
					break;
				case 2: //Devolver lectoras de carga
					this.procesarPeticionLectorasCarga(request, response);
					break;
				case 3: //Devolver lineas de colectivo
					this.procesarPeticionLineasColectivo(request, response);
					break;
				case 4: //Devolver lineas de subte
					this.procesarPeticionLineasSubte(request, response);
					break;
				case 5: //Devolver lineas de tren
					this.procesarPeticionLineasTren(request, response);
					break;
				case 6: //Recibe linea de colectivo, devuelve internos de esa linea y tramos
					this.procesarPeticionInternosColectivo(request, response);
					break;
				case 7: //Recibe linea de subte, devuelve estaciones de esa linea
					this.procesarPeticionEstacionesSubte(request, response);
					break;
				case 8: //Recibe linea de tren, devuelve estaciones de esa linea
					this.procesarPeticionEstacionesTren(request, response);
					break;
				case 9: //Recibe idEstacionSubte, devuelve lectoras de esa estacion
					this.procesarPeticionLectorasSubte(request, response);
					break;
				case 10: //Recibe idEstacionTren, devuelve lectoras de esa estacion
					this.procesarPeticionLectorasTren(request, response);
					break;
				case 11: //Recibe numero Tarjeta, fecha, hora, idLectora, monto
					this.procesarPeticionCarga(request, response);
					break;
				case 12:
					break;
				case 13:
					break;
				case 14:
					break;
				default:
					break;
				}
			}
		} catch(Exception e) {
			System.out.println("Excepction agarrada : " + e.getMessage());
			e.printStackTrace();
			if (!response.isCommitted())
				response.sendError(500, e.getMessage());
		}
	}
}
