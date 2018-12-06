

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
@WebServlet("/GetOsnovniPodaciDB") 
public class GetOsnovniPodaciDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public GetOsnovniPodaciDB() {
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
			String query = "select * from komitenti where idkomitenti=" + id + ";";
			ResultSet rsPodaci;
			rsPodaci = stmt.executeQuery(query);
			
			Map<String, String> mapa = new LinkedHashMap<String, String>();
			while(rsPodaci.next()){
				mapa.put("naziv", rsPodaci.getString("naziv"));
				mapa.put("idkomitenti", rsPodaci.getString("idkomitenti"));
				mapa.put("pib", rsPodaci.getString("pib"));
				mapa.put("mb", rsPodaci.getString("mb"));
				mapa.put("adresaSedista", rsPodaci.getString("adresaSedista"));
				mapa.put("adresaIsporuke", rsPodaci.getString("adresaIsporuke"));
				mapa.put("telefon", rsPodaci.getString("telefon"));
				mapa.put("email", rsPodaci.getString("email"));
				mapa.put("kontaktOsoba", rsPodaci.getString("kontaktOsoba"));
				mapa.put("direktorijum", rsPodaci.getString("direktorijum"));
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
