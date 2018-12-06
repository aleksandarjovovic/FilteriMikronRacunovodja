

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
 * Servlet implementation class GetFakturaAllData
 */
@WebServlet("/GetFakturaAllData") 
public class GetFakturaAllData extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public GetFakturaAllData() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		Connection conn = null;
		try{
			
			System.out.println(id);
			conn = DBUtil.getConnection();
			Statement stmt = conn.createStatement();
			String query = "select * from racunovodja_test.fakture where idfaktura=" + id + ";";
			ResultSet rsPodaci;
			rsPodaci = stmt.executeQuery(query);
			
			int idKomitenti;
			
			Map<String, String> mapa = new LinkedHashMap<String, String>();
			while(rsPodaci.next()){
				mapa.put("idfaktura", id);
				mapa.put("idkomitenti", rsPodaci.getString("idkomitenti"));
				idKomitenti = Integer.parseInt(rsPodaci.getString("idkomitenti"));
				mapa.put("brojfakture", rsPodaci.getString("brojFakture"));
				mapa.put("datum", rsPodaci.getString("datum"));
				mapa.put("iznos", rsPodaci.getString("iznos"));
				mapa.put("valuta", rsPodaci.getString("valuta"));
				mapa.put("datumotpreme", rsPodaci.getString("datumOtpreme"));
				mapa.put("nacinotpreme", rsPodaci.getString("nacinOtpreme"));
				mapa.put("brojugovora", rsPodaci.getString("brojUgovora"));
				mapa.put("napomena", rsPodaci.getString("napomena"));
				mapa.put("placeno", rsPodaci.getString("placeno"));
			}
			
			
			
			
			String json = new Gson().toJson(mapa);
			
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
			
			stmt.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	}

}
