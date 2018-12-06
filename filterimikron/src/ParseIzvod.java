
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import parsers.ParserXML;
import util.DBUtil;

import com.google.gson.Gson;

/**
 * Servlet implementation class GetProizvodjac
 */
@WebServlet("/ParseIzvod")
public class ParseIzvod extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ParseIzvod() {
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
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			
			
			
			Path startingDir = Paths.get("D://FilteriMikron//Filteri mikron 2018//izvod.xml");
			ParserXML parserXML = new ParserXML();
			parserXML.parseIzvodXMLToMySQL(startingDir.toString(), conn);
		
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
