package controladores;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.fichadas.colectivo.LineaColectivoDao;
import modelo.fichadas.colectivo.InternoColectivo;
import modelo.fichadas.colectivo.LineaColectivo;
import modelo.fichadas.colectivo.TramoColectivo;
import modelo.fichadas.subte.EstacionSubte;
import modelo.fichadas.subte.LineaSubte;
import modelo.fichadas.tren.EstacionTren;
import modelo.fichadas.tren.LineaTren;
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
	
	private void procesarPeticionEstacionesSubte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idLinea = Integer.parseInt(request.getParameter("idLinea"));
		LineaSubteABM abm = new LineaSubteABM();
		List<EstacionSubte> lstEstacion = abm.traerEstacionesPorIdLinea(idLinea);
		request.setAttribute("lstEstaciones", lstEstacion);
		request.getRequestDispatcher("views/listaEstacionesSubte.jsp").forward(request, response);
	}
	
	private void procesarPeticionEstacionesTren(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idLinea = Integer.parseInt(request.getParameter("idLinea"));
		LineaTrenABM abm = new LineaTrenABM();
		List<EstacionTren> lstEstacion = abm.traerEstacionesPorIdLinea(idLinea);
		request.setAttribute("lstEstaciones", lstEstacion);
		request.getRequestDispatcher("views/listaEstacionesTren.jsp").forward(request, response);
	}
	
	private void procesarPeticion(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html;	charset=UTF-8");
		try {
			int nroValidacion = Integer.parseInt(request.getParameter("nroValidacion"));
			LectoraExternaABM abmLectoraExterna = new LectoraExternaABM();
			LineaColectivoABM abmLineaColectivo = new LineaColectivoABM();
			LineaSubteABM abmLineaSubte = new LineaSubteABM();
			LineaTrenABM abmLineaTren = new LineaTrenABM();

			switch(nroValidacion) {
			case 1: //Devolver tramos de colectivo
				break;
			case 2: //Devolver lectoras de carga
				List<LectoraExterna> lstLectora = abmLectoraExterna.traerLectoras();
				request.setAttribute("lstLectora", lstLectora);
				request.getRequestDispatcher("/lstLectoras.jsp").forward(request, response);
				break;
			case 3: //Devolver lineas de colectivo
				this.procesarPeticionLineasColectivo(request, response);
				List<LineaColectivo> lstLinea = abmLineaColectivo.traerLineas();
				request.setAttribute("lstLineas", lstLinea);
				request.getRequestDispatcher("/lstLineasColectivo.jsp").forward(request, response);
				break;
			case 4: //Devolver lineas de subte
				List<LineaSubte> lstSubte = abmLineaSubte.traerLineas();
				request.setAttribute("lstLineas", lstSubte);
				request.getRequestDispatcher("/lstLineasSubte.jsp").forward(request, response);
				break;
			case 5: //Devolver lineas de tren
				List<LineaTren> lstTren = abmLineaTren.traerLineas();
				request.setAttribute("lstTren", lstTren);
				request.getRequestDispatcher("/lstLineaTren.jsp").forward(request, response);
				break;
			case 6: //Recibe linea de colectivo, devuelve internos de esa linea y tramos
				int idLineaColectivo = Integer.parseInt(request.getParameter("idLinea"));
				List<InternoColectivo> lstInterno = abmLineaColectivo.traerLineaPorId(idLineaColectivo).getInternosColectivo();
				request.setAttribute("lstInterno", lstInterno);
				request.getRequestDispatcher("/lstInternoColectivo.jsp").forward(request, response);
				break;
			case 7: //Recibe linea de subte, devuelve estaciones de esa linea
				int idLineaSubte = Integer.parseInt(request.getParameter("idLinea"));
				List<EstacionSubte> lstEstacionSubte = abmLineaSubte.traerLineaPorId(idLineaSubte).getRecorridoSubte();
				request.setAttribute("lstEstacionSubte", lstEstacionSubte);
				request.getRequestDispatcher("/lstEstacionSubte.jsp").forward(request, response);
				break;
			case 8: //Recibe linea de tren, devuelve estaciones de esa linea
				int idLineaTren = Integer.parseInt(request.getParameter("idLinea"));
				List<EstacionTren> lstEstacionTren = abmLineaTren.traerLineaPorId(idLineaTren).getEstaciones();
				request.setAttribute("lstEstacionTren", lstEstacionTren);
				request.getRequestDispatcher("/lstEstacionTren.jsp").forward(request, response); 
				break;
			case 9: //Recibe idEstacionSubte, devuelve lectoras de esa estacion
				int idEstacionSubte = Integer.parseInt(request.getParameter("idEstacion"));
				List<LectoraSubte> lstLectoraSubte = abmLineaSubte.traerLectorasPorIdEstacion(idEstacionSubte);
				request.setAttribute("lstEstacion", lstEstacionTren);
				request.getRequestDispatcher("/lstEstacionTren.jsp").forward(request, response); 
				break;
			case 10: //Recibe idEstacionTren, devuelve lectoras de esa estacion
				int idEstacionTren = Integer.parseInt(request.getParameter("idEstacion"));
				List<LectoraTren> lstLectoraTren = abmLineaTren.traerLectorasPorIdEstacion(idEstacionTren);
				request.setAttribute("lstEstacion", lstEstacionTren);
				request.getRequestDispatcher("/lstEstacionTren.jsp").forward(request, response); 
				break;
			default:
				break;
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
					break;
				case 10: //Recibe idEstacionTren, devuelve lectoras de esa estacion
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
