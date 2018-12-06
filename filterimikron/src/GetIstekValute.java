import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

import data.RSIstekValuta;

/**
 * Servlet implementation class GetProizvodjac
 */
@WebServlet("/GetIsteklaValuta")
public class GetIstekValute extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public GetIstekValute() {
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

			String queryIstekValuta = "select idfaktura, brojFakture, naziv, datum, valuta, iznos, placeno from racunovodja_test.fakture f left join racunovodja_test.komitenti k on f.idkomitenti=k.idkomitenti where valuta<curdate() order by valuta;";
			conn = DBUtil.getConnection();
			PreparedStatement stmt = null;

			/* istekla valuta */
			stmt = conn.prepareStatement(queryIstekValuta);
			ResultSet rsIsteklaValuta = stmt.executeQuery();
			List<RSIstekValuta> lista = new ArrayList<RSIstekValuta>();
			while (rsIsteklaValuta.next()) {
				RSIstekValuta temp = new RSIstekValuta(rsIsteklaValuta.getInt("idfaktura"), rsIsteklaValuta.getString("brojFakture"),rsIsteklaValuta.getString("naziv"),rsIsteklaValuta.getString("datum"),rsIsteklaValuta.getString("valuta"),
						rsIsteklaValuta.getDouble("iznos"), rsIsteklaValuta.getInt("placeno"));
				lista.add(temp);
			}
			
			String json = new Gson().toJson(lista);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);

			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
