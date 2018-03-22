package wap.guessme.models;

import java.util.Date;

/**
 * **
 * @author titin: 
 * Desription: bean to model user login/logout log
 *
 */

public class GamerLog {
	private int id;
	private Gamer gamer;
	private String sessionId;
	private Date loginAt;
	private Date logoutAt;
	private int activeStatus;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Gamer getGamer() {
		return gamer;
	}
	public void setGamer(Gamer gamer) {
		this.gamer = gamer;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Date getLoginAt() {
		return loginAt;
	}
	public void setLoginAt(Date loginAt) {
		this.loginAt = loginAt;
	}
	public Date getLogoutAt() {
		return logoutAt;
	}
	public void setLogoutAt(Date logouAt) {
		this.logoutAt = logouAt;
	}
	public int isActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}
	
	public int getActiveStatus() {
		return this.activeStatus;
	}
	
	

}
