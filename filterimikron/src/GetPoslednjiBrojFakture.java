
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBUtil;

import com.google.gson.Gson;

/**
 * Servlet implementation class GetProizvodjac
 */
@WebServlet("/GetPoslednjiBrojFakture")
public class GetPoslednjiBrojFakture extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public GetPoslednjiBrojFakture() {
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

		Connection conn = null;
		try {

			conn = DBUtil.getConnection();
			Statement stmt = conn.createStatement();
			String query = "select  concat(MAX(CAST(SUBSTRING(brojFakture, 1, length(brojFakture)-5) AS UNSIGNED))+1, '/',year(curdate())) as brojFakture from racunovodja_test.fakture where year(datum)=year(curdate());";
			ResultSet rsPodaci;
			rsPodaci = stmt.executeQuery(query);

			Map<String, String> mapa = new LinkedHashMap<String, String>();
			while (rsPodaci.next()) {
				mapa.put("brojFakture", rsPodaci.getString("brojFakture"));
			}

			String json = new Gson().toJson(mapa);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);

			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
