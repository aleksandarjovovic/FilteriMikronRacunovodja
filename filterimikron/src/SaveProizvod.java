

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

import data.Uporedne;

/**
 * Servlet implementation class SaveProizvod
 */
@WebServlet("/SaveProizvod")
public class SaveProizvod extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveProizvod() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String oznaka=request.getParameter("oznaka");
		String labels = request.getParameter("uporedne");
		
		//HashMap<String,String> map = new Gson().fromJson(labels, new TypeToken<HashMap<String, String>>(){}.getType());
				
		Connection conn = null;
		int currentID = 0;
		try{
			conn = DBUtil.getConnection();
			Statement statement1 = conn.createStatement();
			String query = "insert into proizvod(oznaka) values ('"+oznaka+"');";
			statement1.executeUpdate(query);
			statement1.close();
			
			Gson gson = new Gson(); 
			Uporedne[] lista = gson.fromJson(labels, Uporedne[].class);
			
			Statement statement2 = conn.createStatement();
			query = "select MAX(idproizvod) as ID from proizvod;";
			ResultSet rsId = statement2.executeQuery(query);
			while(rsId.next()){
				currentID = rsId.getInt("ID");
				System.out.println(currentID);
			}
			
			for(int i=0;i<lista.length;i++){
				System.out.println(lista[i].getId()); 
				System.out.println(lista[i].getLabel());
				Statement statement3 = conn.createStatement();
				query = "insert into uporedno(idproizvod,idproizvodjac,oznaka) values ('"+currentID+"','"+lista[i].getId()+"','"+lista[i].getLabel()+"');";
				statement3.executeUpdate(query);
			}
			
			Map<String, String> resp = new LinkedHashMap<String, String>();
			resp.put("success", "true");
			String json = new Gson().toJson(resp);
			
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
			
		}catch(Exception e){
			
		}
		
	}

}
