package wap.guessme.models;

/**
 * **
 * @author titin: 
 * Desription: bean to model game history;
 *
 */

public class GameHistory {
	private Game game;
	private Gamer gamer;
	private int guessedNumber;
	private int timeSpent; // in seconds
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public Gamer getGamer() {
		return gamer;
	}
	public void setGamer(Gamer gamer) {
		this.gamer = gamer;
	}
	public int getGuessedNumber() {
		return guessedNumber;
	}
	public void setGuessedNumber(int guessedNumber) {
		this.guessedNumber = guessedNumber;
	}
	public int getTimeSpent() {
		return timeSpent;
	}
	public void setTimeSpent(int timeSpent) {
		this.timeSpent = timeSpent;
	}
	
	
	

}
