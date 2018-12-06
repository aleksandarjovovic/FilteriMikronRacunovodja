
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webclients.SoapClientNBSGetBasicData;

import com.google.gson.Gson;

/**
 * Servlet implementation class GetProizvodjac
 */
@WebServlet("/GetOsnovniPodaci")
public class GetOsnovniPodaci extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public GetOsnovniPodaci() {
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
		
		String pib = request.getParameter("pib");

		
		try {
			SoapClientNBSGetBasicData client = new SoapClientNBSGetBasicData();
			Map<String,String> mapa = client.getBasicDataByPIB(pib, 1);
			
			String json = new Gson().toJson(mapa);
			
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
			

		} catch (Exception e) {

		}
	}

}
