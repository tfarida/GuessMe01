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
 * Servlet implementation class Registration
 */

// Written by Titin on March 21st 2018 - Register Servlet

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseService ds;

	public RegistrationServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String gamerName = request.getParameter("userName");
		String emailAddress = request.getParameter("email");
		String password = request.getParameter("password");
		String fullName = request.getParameter("fullName");
		String gender = request.getParameter("gender");

		try {
			ds = new DatabaseService();
			// first will check db fo duplicate email address or gamerName
			boolean checkDuplicate = ds.checkDuplicateGamer(emailAddress, gamerName);
			if (!checkDuplicate) {
				Gamer gamer = ds.insertNewGamer(gamerName, emailAddress, password, fullName, gender);
				request.setAttribute("successMessage", "Your registration is done successfully!"); // titin: no need anymore actually
				HttpSession session = request.getSession();
				session.setAttribute("gamer", gamer);
				session.setAttribute("onlineGamers", ds.getOnlineGamers(gamer));

				RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("errorMessage", "User with same username or email address already exist!");
				RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorMessage", AppHelper.internalServerErrorMessage);
			RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
			rd.forward(request, response);

		}
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
