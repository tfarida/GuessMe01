package wap.guessme.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import wap.guessme.models.DatabaseService;
import wap.guessme.models.Gamer;
import wap.guessme.models.GamerLog;

/**
 * Servlet implementation class WelcomeServlet
 */
@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		DatabaseService ds;
		try {
			PrintWriter out = response.getWriter();
			HttpSession session  = request.getSession();	
			ds = new DatabaseService();	
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			List<GamerLog> data = ds.getOnlineGamers((Gamer)(session.getAttribute("gamer")));
			JSONObject[] results = ds.getOnlineGamers_JSON(data); //new JSONObject[data.size()];
			/*System.out.println(" data.size() = " + data.size());
			for(int i = 0; i< data.size(); i++) {
				JSONObject res = new JSONObject();
				res.put("id", data.get(i).getId());
				
				JSONObject gamer = new JSONObject();
				gamer.put("id", ""+data.get(i).getGamer().getId() );
				gamer.put("gamerName", data.get(i).getGamer().getGamerName() );
				gamer.put("emailAddress", data.get(i).getGamer().getEmailAddress() );
				gamer.put("password", data.get(i).getGamer().getPassword() );
				gamer.put("fullName", data.get(i).getGamer().getFullName() );
				gamer.put("gender", data.get(i).getGamer().getGender() );
				gamer.put("createdAt", data.get(i).getGamer().getCreatedAt().toString());
				
				res.put("gamer", gamer);
				
				//System.out.println(data.get(i).getGamer().getGamerName());
				//res.put("id",""+data.get(i).getGamer().getId());				
				//res.put("gamerName",data.get(i).getGamer().getGamerName());
				
				//res.put("sessionId", data.get(i).getSessionId());
				//res.put("loginAt", data.get(i).getLoginAt());
				//res.put("logoutAt", data.get(i).getLogoutAt());
				//res.put("activeStatus", data.get(i).getActiveStatus());
				
				results[i] = res;
			}*/
			
			out.print(Arrays.toString(results));
			out.flush();
			
			//session.setAttribute("onlineGamers", ds.getOnlineGamers((Gamer)(session.getAttribute("gamer"))));
			//RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
			//rd.forward(request, response);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
