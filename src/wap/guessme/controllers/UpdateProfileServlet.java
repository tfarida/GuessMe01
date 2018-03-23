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
import wap.guessme.utilities.AppHelper;

/**
 * Servlet implementation class UpdateProfileServlet
 */

// Written by Titin on March 21st - Update Profile Servlet

@WebServlet("/UpdateProfile")
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseService ds;

	public UpdateProfileServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Gamer gamer = (Gamer) (session.getAttribute("gamer"));
		System.out.println("gamer " + gamer.getId());

		String fullName = request.getParameter("fullName");
		String aboutMe = request.getParameter("aboutMe");
		String secretNumber = request.getParameter("mySN");
		String aboutSecretNumber = request.getParameter("aboutMySN");

		try {
			ds = new DatabaseService();
			ds.updateGamerProfile(gamer, gamer.getGamerName(), gamer.getEmailAddress(), fullName, gamer.getGender(),
					aboutMe, secretNumber, aboutSecretNumber);
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
