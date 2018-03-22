package wap.guessme.controllers;

import java.io.File;
import java.io.FileInputStream;
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
import wap.guessme.utilities.AppHelper;

/**
 * Servlet implementation class UpdateProfileServlet
 */
@WebServlet("/UpdateProfile")
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseService ds;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session  = request.getSession();
		Gamer gamer  = (Gamer)(session.getAttribute("gamer"));
		System.out.println("gamer "+gamer.getId());
		
		String fullName = request.getParameter("fullName");
		String aboutMe = request.getParameter("aboutMe");
		String secretNumber = request.getParameter("mySN");
		String aboutSecretNumber = request.getParameter("aboutMySN");
		
		/*System.out.println("Before File imageFile = new File(imagePath); ");
		String  imagePath = request.getParameter("profileImage"); 
		String filepath= imagePath.substring(imagePath.lastIndexOf("\\")+1);
		File imageFile = new File(imagePath);
		System.out.println("After File imageFile = new File(imagePath); ");
		FileInputStream fin = new FileInputStream(imageFile);
		System.out.println("After FileInputStream fin = new FileInputStream(imageFile); ");*/
		
		
		try {
			ds = new DatabaseService();	
			ds.updateGamerProfile(gamer,gamer.getGamerName(), gamer.getEmailAddress(), fullName, 
					 gamer.getGender(), aboutMe, secretNumber, aboutSecretNumber);
			//ds.updateImageProfile(gamer, imagePath); //, imageFileLength);
			// first will check  db fo duplicate email address or gamerName
			
			session.setAttribute("gamer", ds.getGamer(gamer.getEmailAddress()));
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorMessage", AppHelper.internalServerErrorMessage);
			RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
			rd.forward(request, response);	
			
		}			
		
		RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
		rd.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
