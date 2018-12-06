import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBUtil;

import com.google.gson.Gson;

import data.RSDuznici;

/**
 * Servlet implementation class GetProizvodjac
 */
@WebServlet("/GetStatistika")
public class GetStatistika extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public GetStatistika() {
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

		String godina = request.getParameter("godina");

		Connection conn = null;
		try {

			String queryBrojFaktura = "select count(*) as brojFaktura from fakture where year(datum)="
					+ godina + ";";
			String queryUkupanPromet = "select sum(iznos) as ukupanPromet from fakture where year(datum)="
					+ godina + ";";
			String queryUkupnaUplata;
			String queryTopKupci = "select naziv, sum(iznos) as iznos from racunovodja_test.komitenti k "
					+ "left join racunovodja_test.fakture f on k.idkomitenti=f.idkomitenti "
					+ "where year(f.datum)="
					+ godina
					+ " group by k.idkomitenti order by iznos desc;";

			if (godina.equalsIgnoreCase("2014")) {
				queryUkupnaUplata = "select sum(iznos) as ukupnaUplata from uplate2014 where year(datum)="
						+ godina + ";";
			} else {
				queryUkupnaUplata = "select sum(iznos) as ukupnaUplata from uplate where year(datum)="
						+ godina + ";";
			}

			conn = DBUtil.getConnection();
			PreparedStatement stmt = null;

			Map<String, Object> mapa = new LinkedHashMap<String, Object>();

			/* broj faktura */
			stmt = conn.prepareStatement(queryBrojFaktura);
			ResultSet rsBrojFaktura;
			rsBrojFaktura = stmt.executeQuery();
			while (rsBrojFaktura.next()) {
				mapa.put("brojFaktura", rsBrojFaktura.getString("brojFaktura"));
			}

			/* ukupan promet */
			stmt = conn.prepareStatement(queryUkupanPromet);
			ResultSet rsUkupanPromet;
			rsUkupanPromet = stmt.executeQuery();
			while (rsUkupanPromet.next()) {
				mapa.put("ukupanPromet",
						rsUkupanPromet.getString("ukupanPromet"));
			}

			/* ukupna uplata */
			stmt = conn.prepareStatement(queryUkupnaUplata);
			ResultSet rsUkupnaUplata;
			rsUkupnaUplata = stmt.executeQuery();
			while (rsUkupnaUplata.next()) {
				mapa.put("ukupnaUplata",
						rsUkupnaUplata.getString("ukupnaUplata"));
			}

			/* top kupci */
			stmt = conn.prepareStatement(queryTopKupci);
			ResultSet rsTopKupci = stmt.executeQuery();
			List<RSDuznici> lista = new ArrayList<RSDuznici>();
			while (rsTopKupci.next()) {
				RSDuznici temp = new RSDuznici(rsTopKupci.getString("naziv"),
						rsTopKupci.getDouble("iznos"));
				lista.add(temp);
			}
			
			mapa.put("topKupci",lista);

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
