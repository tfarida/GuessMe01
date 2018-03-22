package wap.guessme.models;

import java.util.*; 

/**
 * **
 * @author titin: 
 * Desription: bean to model game data
 *
 */
public class Game {
	int gameId;
	Date startTime;
	Date endTime;
	List<GamePlayer> players;
	List<GameHistory> gameHist;
	
	public Game() {
		players = new ArrayList<GamePlayer>();
		gameHist = new ArrayList<GameHistory>();
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public List<GamePlayer> getPlayers() {
		return players;
	}
	public void addPlayer(GamePlayer player) {
		players.add(player);
	}
	public List<GameHistory> getGameHist() {
		return gameHist;
	}
	public void addGameHist(GameHistory gameHistory) {
		gameHist.add(gameHistory) ;
	}
	public Game(Date startTime) {
		this.startTime = startTime;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	
	
}
