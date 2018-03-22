package wap.guessme.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wap.guessme.models.DatabaseService;
import wap.guessme.models.Gamer;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseService ds;
    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				//response.getWriter().append("Served at: ").append(request.getContextPath());
				
				Gamer gamer=null;
				boolean validate = false;
				
				String emailOrGamerName = request.getParameter("emailOrGamerName");
				String password = request.getParameter("password");
				//System.out.println("Email Or Gamer Name: "+emailOrGamerName);
				//System.out.println("Password: "+password);				
				try {
					ds = new DatabaseService();
					gamer = ds.getGamer(emailOrGamerName);
					if(gamer != null) {
						validate = gamer.getPassword().equals(password);					
					}
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(validate) {
					//request.login(gamer.getGamerName(), gamer.getPassword());
					HttpSession session  = request.getSession();					
					ds.insertGamerLog(gamer, session.getId());
					
					session.setAttribute("gamer", gamer);
					session.setAttribute("onlineGamers", ds.getOnlineGamers(gamer));
					
					RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
					rd.forward(request, response);	
				}
				else {
					
					request.setAttribute("errorMessage", "User not found");
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);	
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
