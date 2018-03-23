package wap.guessme.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

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

// Written by Titin on March 21st 2018 - Welcome Servlet

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WelcomeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DatabaseService ds;
		try {
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			ds = new DatabaseService();

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			List<GamerLog> data = ds.getOnlineGamers((Gamer) (session.getAttribute("gamer")));
			JSONObject[] results = ds.getOnlineGamers_JSON(data); // new JSONObject[data.size()];

			out.print(Arrays.toString(results));
			out.flush();

		} catch (SQLException e) {
			e.printStackTrace();
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
