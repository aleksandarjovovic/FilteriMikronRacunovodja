
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBUtil;

import com.google.gson.Gson;

import data.RSKartica;

/**
 * Servlet implementation class GetProizvodjac
 */
@WebServlet("/GetKartica")
public class GetKartica extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public GetKartica() {
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

		String id = request.getParameter("id");

		Connection conn = null;
		try {

			conn = DBUtil.getConnection();
			String sql = "{call getKartica(?)}";
			CallableStatement stmt = conn.prepareCall(sql);
			stmt.setString(1, id);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();

			List<RSKartica> lista = new ArrayList<RSKartica>();
			while (rs.next()) {
				RSKartica temp = new RSKartica(rs.getString("svrha"),
						rs.getString("brojFakture"), rs.getString("datum"),
						rs.getString("pozivNaBr"), rs.getDouble("iznos"));
				lista.add(temp);
			}
		
			String json = new Gson().toJson(lista);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);

			stmt.close();

		} catch (Exception e) {

		}
	}

}
