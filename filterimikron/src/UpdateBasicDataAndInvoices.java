
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.FileVisitorBasicData;
import util.FileVisitorPrintedInvoices;

import com.google.gson.Gson;

/**
 * Servlet implementation class GetProizvodjac
 */
@WebServlet("/UpdateBasicDataAndInvoices")
public class UpdateBasicDataAndInvoices extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public UpdateBasicDataAndInvoices() {
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
	 *      action = 0 -> insert Basic data
	 *      action = 1 -> insert Printed invoices
	 *      
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");

		try {
			
			if(action.equalsIgnoreCase("0")){
				Path startingDir = Paths.get("D:\\FilteriMikron\\Filteri mikron 2018\\Fakture(Izlaz)");
				FileVisitorBasicData vis = new FileVisitorBasicData();
				Files.walkFileTree(startingDir, vis);
			}else if(action.equalsIgnoreCase("1")){
				Path startingDir = Paths.get("D:\\FilteriMikron\\Filteri mikron 2018\\Fakture(Izlaz)");
				FileVisitorPrintedInvoices vis = new FileVisitorPrintedInvoices();
				Files.walkFileTree(startingDir, vis);
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
