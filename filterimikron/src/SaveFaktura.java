
import invoices.NewInvoice;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class SaveFaktura
 */
@WebServlet("/SaveFaktura")
public class SaveFaktura extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public SaveFaktura() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	
		String idKomitenti = request.getParameter("idkomitenti");
		String direktorijum = request.getParameter("direktorijum");
		String brojFakture = request.getParameter("brojFakture");
		String datumFakture = request.getParameter("datumFakture");
		String datumPrometa = request.getParameter("datumPrometa");
		String nacinOtpreme = request.getParameter("nacinOtpreme");
		String valuta = request.getParameter("valuta");
		String placenoAvansno = request.getParameter("placenoAvansno");
		String brUgovora = request.getParameter("brUgovora");
		String svega = request.getParameter("svega");
		String slovima = request.getParameter("slovima");
		String stavke = request.getParameter("stavke");
		
		
		
		
		
		/*insert into faktura osnovni podaci, insert into stavka sa vezanim fk za fakturu, napravi excel fajl na odgovarajucoj lokaciji*/
		
		NewInvoice ni = new NewInvoice();
		ni.createNewInvoice(idKomitenti, direktorijum, brojFakture, datumFakture,datumPrometa,nacinOtpreme,valuta,placenoAvansno,brUgovora,svega,slovima,stavke);
		
		
		
		try {
			
			Map<String,String> mapa = new LinkedHashMap<String, String>();
			mapa.put("result", "ok");
			String json = new Gson().toJson(mapa);
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);


		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
