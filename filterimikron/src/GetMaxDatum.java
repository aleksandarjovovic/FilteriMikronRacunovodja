

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet("/GetMaxDatum") 
public class GetMaxDatum extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public GetMaxDatum() {
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
			String query = "select max(datum) as maxDatum from uplate";
			ResultSet rsMaxDatum;
			rsMaxDatum = stmt.executeQuery(query);
			Map<String, String> datumMap = new LinkedHashMap<String, String>();
			while(rsMaxDatum.next()){
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(rsMaxDatum.getString("maxDatum"));
				String d = new SimpleDateFormat("dd.MM.yyyy").format(date);
				datumMap.put("maxDatum", d);
			}
			String json = new Gson().toJson(datumMap);
			
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
			
			stmt.close();
			
		}catch(Exception e){
			
		}
	}

}
