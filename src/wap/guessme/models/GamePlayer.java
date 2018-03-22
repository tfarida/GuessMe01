package wap.guessme.models;

/**
 * **
 * @author titin: 
 * Desription: bean to model game player;
 *
 */
public class GamePlayer {
	private Game game;
	private Gamer gamer;
	private int pickedNumber;
	private boolean winStatus;
	
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
	public int getPickedNumber() {
		return pickedNumber;
	}
	public void setPickedNumber(int pickedNumber) {
		this.pickedNumber = pickedNumber;
	}
	public boolean isWinStatus() {
		return winStatus;
	}
	public void setWinStatus(boolean winStatus) {
		this.winStatus = winStatus;
	}
	
	
	
	
	
	

}
