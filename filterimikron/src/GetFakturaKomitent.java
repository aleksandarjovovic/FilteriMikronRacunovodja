

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
 * Servlet implementation class GetFakturaKomitent
 */
@WebServlet("/GetFakturaKomitent") 
public class GetFakturaKomitent extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public GetFakturaKomitent() {
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
			System.out.println("aaaaaaa");
			conn = DBUtil.getConnection();
			Statement stmt = conn.createStatement();
			String query = "select idfaktura, concat(brojFakture,' - ',"
					+ "(select naziv from racunovodja_test.komitenti k where k.idkomitenti = f.idkomitenti)) as conc "
					+ "from racunovodja_test.fakture f order by f.datum desc";
			ResultSet rsFaktureKomitenti;
			rsFaktureKomitenti = stmt.executeQuery(query);
			Map<Integer, String> faktureMap = new LinkedHashMap<Integer, String>();
			while(rsFaktureKomitenti.next()){
				faktureMap.put(rsFaktureKomitenti.getInt("idfaktura"), rsFaktureKomitenti.getString("conc"));
			}
			String json = new Gson().toJson(faktureMap);
			
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
			
			stmt.close();
			
		}catch(Exception e){
			
		}
		
	}

}
