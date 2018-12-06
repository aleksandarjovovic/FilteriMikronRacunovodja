
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import partners.NewPartner;

import com.google.gson.Gson;

/**
 * Servlet implementation class GetProizvodjac
 */
@WebServlet("/SaveNewPartner")
public class SaveNewPartner extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public SaveNewPartner() {
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
		
		String idkomitenti = request.getParameter("idkomitenti");
		String update = request.getParameter("update");
		String dirName = request.getParameter("direktorijum");
		String name = request.getParameter("naziv");
		String address = request.getParameter("adresa");
		String addressOfMail = request.getParameter("isporuka");
		String pib = request.getParameter("pib");
		String NIN = request.getParameter("mb");
		String phone = request.getParameter("telefon");
		String email = request.getParameter("email");
		String contactPerson = request.getParameter("kontaktOsoba");

		try {
			NewPartner np = new NewPartner();
			if(update.equalsIgnoreCase("0")){
				np.createNewPartner(dirName, name, address, addressOfMail, pib, NIN, phone, email, contactPerson);
			}else if(update.equalsIgnoreCase("1")){
				np.updatePartner(idkomitenti,dirName, name, address, addressOfMail, pib, NIN, phone, email, contactPerson);
			}
			
			
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
