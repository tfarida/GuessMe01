package wap.guessme.controllers;

/**
 * @author romiezaw
 *
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import wap.guessme.models.DatabaseService;
import wap.guessme.models.Game;
import wap.guessme.models.GamePlayer;
import wap.guessme.models.GameService;
import wap.guessme.models.Gamer;
import wap.guessme.models.GamerLog;

//Written by Romie on March 19/22 2018- Servlet class for game control

@WebServlet("/GameSolver")
public class GameSolver extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GameService gameService;
	private DatabaseService dbService;

	public GameSolver() {
		gameService = new GameService();
		try {
			dbService = new DatabaseService();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (request.getParameter("action").equals("getMyOpponent")) {				//Call by ajax.jx
			
			int userId = Integer.parseInt(request.getParameter("opponentId"));

			// player1 for game
			Gamer me = (Gamer) session.getAttribute("gamer");
			GamePlayer player1 = new GamePlayer();
			player1.setGamer(me);
			System.out.println("Player 1 : " + player1.getGamer().getGamerName());

			// Player2 for game
			GamerLog gamer2Log = dbService.getGamerLoginDetails(userId);
			Gamer gamer2 = gamer2Log.getGamer();
			GamePlayer player2 = dbService.getPlayerDetails(gamer2.getId());
			System.out.println("Player 2 : " + player2.getGamer().getGamerName());

			// Start a game
			Game game = dbService.startAGame(player1, player2);
			dbService.createNewPlayer(game);
			
			//Store startTime in session
			LocalDateTime startTime = LocalDateTime.now();
			session.setAttribute("startTime", startTime);

			int gameId = dbService.createNewGame(game, (LocalDateTime) session.getAttribute("startTime"));
			System.out.println("game ID : " + game.getGameId());
			game.setGameId(gameId);
			session.setAttribute("newgame", game);

			//4 unique digits no generated by Game Service method.
			int guessMe = GameService.generateRandomNo();	
			session.setAttribute("secretNo", String.valueOf(guessMe));
			game.getPlayers().get(1).setPickedNumber(guessMe);
			
			//Convert to JsonString
			System.out.println("Converting to JSON.......");
			String jsonInString = DatabaseService.toJson(game);

			PrintWriter out = response.getWriter();
			out.println(jsonInString);
			out.flush();

		} else if (request.getParameter("action").equals("checker")) {				//Call by ajax 
			// Check the matchg no
			long timeTaken = 0;
			LocalDateTime endTime = LocalDateTime.now();
			LocalDateTime startTime = (LocalDateTime) session.getAttribute("startTime");
			if (startTime != null) {
				Duration duration = Duration.between(startTime, endTime);
				timeTaken = duration.getSeconds() % 60;
			}
			// The number which user choose from keyboard
			int myGuess = (Integer.parseInt(request.getParameter("myGuess")));
			// set player 2 secret no
			Game m = (Game) session.getAttribute("newgame");
			int guessMe = m.getPlayers().get(1).getPickedNumber(); // Player 2 secret no which player 1 needs to guess 
			System.out.println("No to guesss:::: " + guessMe);
			if (guessMe != 0) {
				gameService.setNumberToGuess(GameService.generateRandomNo());
			}

			// Update session to display on screen
			gameService.setNumberToGuess(guessMe);
			session.setAttribute("secretNo", String.valueOf(guessMe));
			dbService.insertGameHistory(m, myGuess, timeTaken);
			// Setting the no in game service class.
			gameService.setMyGuess(myGuess);
			
			// Convert to JSONObject
			JSONObject results = gameService.solveIt();
			PrintWriter out = response.getWriter();
			out.print(results);
			out.flush();

		} else if (request.getParameter("action").equals("keepMeSecret")) {				// Call by Ajax... 
																
			// Set secret No for player1
			int secretNo = Integer.parseInt(request.getParameter("secretNo"));
			gameService.setSecretNo(secretNo);

			// Update secrete no in session.
			Game m = (Game) session.getAttribute("newgame");
			m.getPlayers().get(0).setPickedNumber(secretNo);
			session.setAttribute("newgame", m);

			PrintWriter out = response.getWriter();
			out.print(secretNo);
			out.flush();
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
