

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
@WebServlet("/GetProizvodjac") 
public class GetProizvodjac extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public GetProizvodjac() {
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
			String query = "select * from proizvodjac";
			ResultSet rsProizvodjaci;
			rsProizvodjaci = stmt.executeQuery(query);
			Map<Integer, String> proizvodjaciMap = new LinkedHashMap<Integer, String>();
			while(rsProizvodjaci.next()){
				proizvodjaciMap.put(rsProizvodjaci.getInt("idproizvodjac"), rsProizvodjaci.getString("naziv"));
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
