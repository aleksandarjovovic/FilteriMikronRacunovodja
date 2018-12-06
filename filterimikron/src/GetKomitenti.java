

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
 * Servlet implementation class GetKomitenti
 */
@WebServlet("/GetKomitenti") 
public class GetKomitenti extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public GetKomitenti() {
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
		
		
		Connection conn = null;
		try{
			
			conn = DBUtil.getConnection();
			Statement stmt = conn.createStatement();
			String query = "select * from komitenti order by naziv";
			ResultSet rsKomitenti;
			rsKomitenti = stmt.executeQuery(query);
			Map<Integer, String> proizvodjaciMap = new LinkedHashMap<Integer, String>();
			while(rsKomitenti.next()){
				proizvodjaciMap.put(rsKomitenti.getInt("idkomitenti"), rsKomitenti.getString("naziv"));
			}
			String json = new Gson().toJson(proizvodjaciMap);
			
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
			
			stmt.close();
			
		}catch(Exception e){
			
		}
		
	}

}
