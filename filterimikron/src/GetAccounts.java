
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBUtil;
import webclients.SoapClientNBS;

/**
 * Servlet implementation class GetProizvodjac
 */
@WebServlet("/GetAccounts")
public class GetAccounts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public GetAccounts() {
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
			SoapClientNBS client = new SoapClientNBS();
			
			Statement statement0;
			
			try {
				statement0 = conn.createStatement();
				String queryGetID = "select * from komitenti";
				ResultSet rsKomitenti = statement0.executeQuery(queryGetID);
				while (rsKomitenti.next()) {
					client.getAccountsByPIB(rsKomitenti.getString("pib"), 1,
							rsKomitenti.getInt("idkomitenti"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (Exception e) {

		}
	}

}
