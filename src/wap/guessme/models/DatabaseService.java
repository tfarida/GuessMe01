/**
 * 
 */
package wap.guessme.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.ApplicationPath;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import wap.guessme.utilities.AppHelper;

/**
 * @author romiezaw
 *
 */

//Written by Romie & Titin on Mar 20- for DB processing
@ApplicationPath("/api")
public class DatabaseService {

	private DatabaseConnection connection;
	private Connection conn = null;
	private PreparedStatement stmt;
	private Statement statement;
	private String QUERY;

	public DatabaseService() throws SQLException {
		try {
			connection = DatabaseConnection.getInstance();
			conn = connection.getConnection();
			System.out.println("Connection successful.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// start titins
	// titin:
	public boolean checkDuplicateGamer(String emailAddress, String gamerName) {
		boolean sameRecordFound = false;
		try {
			statement = conn.createStatement();
			QUERY = "select * from tb_user where emailAddress = '" + emailAddress + "' OR gamerName = '" + gamerName
					+ "'";
			ResultSet rs = statement.executeQuery(QUERY);
			int numRecords = rs.getFetchSize();
			if (rs.next()) {
				return true;
			}
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sameRecordFound;
	}

	// titin: get user info
			public Gamer getGamer(String emailAddressorGamerName) {
				Gamer gamer = null;
				try {
					statement = conn.createStatement();
					QUERY = "select * from tb_user where emailAddress = '" + emailAddressorGamerName + "' OR gamerName = '"
							+ emailAddressorGamerName + "'";
					ResultSet rs = statement.executeQuery(QUERY);
					if (rs.next()) {
						int id = rs.getInt("id");
						String gamerName = rs.getString("gamerName");
						String emailAddress = rs.getString("emailAddress");
						String password = rs.getString("password");
						String fullName = rs.getString("fullName");
						String gender = rs.getString("gender");
						Date createdAt = rs.getDate("createdAt");
						gamer = new Gamer(gamerName, emailAddress, password, fullName, gender, createdAt);
						gamer.setId(id);
						String aboutMe = rs.getString("aboutMe");;
						int secretNumber = rs.getInt("secretNumber");
						String aboutSecretNumber =   rs.getString("aboutSecretNumber");
						Date lastModifiedAt = rs.getDate("lastModifiedAt");
						gamer.setAboutMe(aboutMe);
						gamer.setSecretNumber(secretNumber);
						gamer.setAboutSecretNumber(aboutSecretNumber);
						gamer.setLastModifiedAt(lastModifiedAt);
						
						// System.out.println("Records found");
					} else {
						// System.out.println("No records found");
					}
					statement.close();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return gamer;
			}

	// titin: getOnlineGamers
	public List<GamerLog> getOnlineGamers(Gamer gm) {
		List<GamerLog> onlineGamers = new ArrayList<GamerLog>();
		try {
			statement = conn.createStatement();
			QUERY = "SELECT L.id, L.sessionId, L.loginAt, L.logoutAt, L.activeStatus "
					+ " , L.userId, U.gamerName, U.emailAddress, U.password, U.fullName, U.gender, U.createdAt   "
					+ " FROM tb_user_login L " + " left join tb_user U on L.userId = U.id "
					+ " where L.activeStatus = 1  AND L.userId != '" + gm.getId() + "'";
			// " group by L.userId ";
			System.out.println("QUERY: " + QUERY);
			ResultSet rs = statement.executeQuery(QUERY);
			while (rs.next()) {
				Gamer gamer;
				GamerLog gamerLog;
				int id = rs.getInt("userId");
				String gamerName = rs.getString("gamerName");
				String emailAddress = rs.getString("emailAddress");
				// String password = rs.getString("password");
				String fullName = rs.getString("fullName");
				String gender = rs.getString("gender");
				Date createdAt = rs.getDate("createdAt");
				gamer = new Gamer(gamerName, emailAddress, fullName, gender, createdAt);
				gamer.setId(id);

				id = rs.getInt("id");
				String sessionId = rs.getString("sessionId");
				Date loginAt = rs.getDate("loginAt");
				Date logoutAt = rs.getDate("logoutAt");
				int activeStatus = rs.getInt("activeStatus");

				gamerLog = new GamerLog();
				gamerLog.setGamer(gamer);
				gamerLog.setId(id);
				gamerLog.setSessionId(sessionId);
				gamerLog.setLoginAt(loginAt);
				gamerLog.setLogoutAt(logoutAt);
				gamerLog.setActiveStatus(activeStatus);
				onlineGamers.add(gamerLog);
			}
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return onlineGamers;
	}

	// titin: getOnlineGamers in JSON format
	public JSONObject[] getOnlineGamers_JSON(List<GamerLog> data) {
		JSONObject[] results = new JSONObject[data.size()];
		System.out.println(" data.size() = " + data.size());
		for (int i = 0; i < data.size(); i++) {
			JSONObject res = new JSONObject();
			res.put("id", data.get(i).getId());

			JSONObject gamer = new JSONObject();
			gamer.put("id", "" + data.get(i).getGamer().getId());
			gamer.put("gamerName", data.get(i).getGamer().getGamerName());
			gamer.put("emailAddress", data.get(i).getGamer().getEmailAddress());
			gamer.put("password", data.get(i).getGamer().getPassword());
			gamer.put("fullName", data.get(i).getGamer().getFullName());
			gamer.put("gender", data.get(i).getGamer().getGender());
			gamer.put("createdAt", data.get(i).getGamer().getCreatedAt().toString());

			res.put("gamer", gamer);

			// System.out.println(data.get(i).getGamer().getGamerName());
			res.put("sessionId", data.get(i).getSessionId());
			res.put("loginAt", data.get(i).getLoginAt().toString());
			// res.put("logoutAt", data.get(i).getLogoutAt().toString());
			res.put("activeStatus", "" + data.get(i).getActiveStatus());

			results[i] = res;
		}
		return results;
	}

	// titin: insert gamer log, once user login
	public int insertGamerLog(Gamer gamer, String sessionId) {
		int retInt = 0;
		try {

			// titin 2018_0321 before 3.50 pm: set curr active records to inactive
			updateGamerLog(gamer);

			statement = conn.createStatement();
			QUERY = "INSERT INTO tb_user_login(userId, sessionId, loginAt, activeStatus)" + "values ('" + gamer.getId()
					+ "', '" + sessionId + "', '" + AppHelper.getDateNow() + "', '1') ";

			retInt = statement.executeUpdate(QUERY, Statement.RETURN_GENERATED_KEYS);
			statement.close();
		} catch (SQLException ex) {
			System.err.println("SQLQueryException: " + ex.getMessage());
		}

		return retInt;
	}

	// titin: use case: update list of online users
	public void updateGamerLog(Gamer gamer) {
		try {
			statement = conn.createStatement();
			// set curr active records to inactive
			QUERY = "UPDATE tb_user_login set activeStatus = 0 where userId = '" + gamer.getId() + "'";
			statement.executeUpdate(QUERY);
			statement.close();
		} catch (SQLException ex) {
			System.err.println("SQLQueryException: " + ex.getMessage());
		}
	}

	// titin: insert new user once sign up is done // 21 mar 4:46 PM: change return
	// to Gamer.
	public Gamer insertNewGamer(String gamerName, String emailAddress, String password, String fullName,
			String gender) {
		int retInt = 0;
		Gamer gamer = null;
		try {
			statement = conn.createStatement();
			java.util.Date createdAt = new java.util.Date();
			QUERY = "INSERT INTO tb_user (gamerName, emailAddress,password,fullName,gender,createdAt)" + "VALUES ('"
					+ gamerName + "','" + emailAddress + "','" + password + "', '" + fullName + "',	'" + gender + "', '"
					+ AppHelper.getDateNow(createdAt) + "')";

			System.out.print(QUERY);
			retInt = statement.executeUpdate(QUERY, Statement.RETURN_GENERATED_KEYS);
			statement.close();

			gamer = new Gamer(retInt, gamerName, emailAddress, password, fullName, gender, createdAt);
			
			/*String aboutMe = rs.getString("aboutMe");;
			int secretNumber = rs.getInt("secretNumber");
			String aboutSecretNumber =   rs.getString("aboutSecretNumber");
			Date lastModifiedAt = rs.getDate("lastModifiedAt");*/
			gamer.setAboutMe("");
			gamer.setSecretNumber(0);
			gamer.setAboutSecretNumber("");
			//gamer.setLastModifiedAt(lastModifiedAt);
		} catch (SQLException ex) {
			System.err.println("SQLQueryException: " + ex.getMessage());
		}

		return gamer;
		// return retInt;
	}
	
	//titin: update gamer Profile
			public void updateGamerProfile(Gamer gamer,String gamerName, String emailAddress, String fullName, 
					String gender, String aboutMe, String secretNumber, String aboutSecretNumber) {
				try {
					
					QUERY = "UPDATE tb_user "
							+ "SET gamerName = '"+gamerName+"' " //1
							+ ", emailAddress = '"+emailAddress+"' " //2
							+ ", fullName =  '"+fullName+"' " //3
							+ ", gender =  '"+gender+"' " //3
							+ ", aboutMe = '"+aboutMe+"' " //5
							+ ", secretNumber = '"+secretNumber+"' " //6
							+ ", aboutSecretNumber = '"+aboutSecretNumber+"' " //7
							+ ", lastModifiedAt = '"+AppHelper.getDateNow()+"' "//10
							+ " WHERE id = '"+gamer.getId()+"' "; //12
					
					System.out.println("QUERY: "+QUERY);
					statement = conn.createStatement();
					statement.executeUpdate(QUERY);
					System.out.println("DONE: "+QUERY);
					statement.close();
				} catch (SQLException ex) {
					System.err.println("SQLQueryException: " + ex.getMessage());
				}
				
				
			}
			
			//titin: update profile image
			public void updateImageProfile(Gamer gamer, String imagePath) throws FileNotFoundException {
				try {
					/*String strpath= imageName;
					String filepath=strpath.substring(strpath.lastIndexOf("\\")+1);
					File imgfile = new File(strpath);*/
					File imageFile =  new File(imagePath); 
					FileInputStream fin = new FileInputStream(imageFile);
					
					QUERY = "UPDATE tb_user "
							+ "SET imageFile = ? " //1					
							+ ", imagePath = ? " //2
							+ ", imageFileLength = ? " //3
							//+ ", lastModifiedAt = ? "//4
							+ "WHERE id = ? "; //5 4
					
					PreparedStatement pre = conn.prepareStatement(QUERY);
					pre.setBinaryStream(1,fin,(int)imageFile.length());
					pre.setString(2,imagePath);
					pre.setLong(3,imageFile.length());
					pre.setInt(4, gamer.getId());//
					pre.executeUpdate();
					pre.close();
				} catch (SQLException ex) {
					System.err.println("SQLQueryException: " + ex.getMessage());
				}
				
				
			}

	// end of titins

			// retrieve opponent's details
			public GamerLog getGamerLoginDetails(int loginId) {

				GamerLog gamerLog = null;
				try {
					String gamerLgnSql = "select * from tb_user_login where userId=" + loginId;
					stmt = conn.prepareStatement(gamerLgnSql);
					ResultSet rs = stmt.executeQuery();

					while (rs.next()) {
						// Retrieve by column name
						int gamerId = Integer.parseInt(rs.getString("userId"));
						int activeStatus = Integer.parseInt(rs.getString("activeStatus"));

						String sessionId = rs.getString("sessionId");
						Date loginTime = rs.getDate("loginAt");
						Date logoutTime = rs.getDate("logoutAt");

						gamerLog = new GamerLog();
						gamerLog.setGamer(getGamerDetails(gamerId));
						gamerLog.setActiveStatus(activeStatus);
						gamerLog.setSessionId(sessionId);
						gamerLog.setLoginAt(loginTime);
						gamerLog.setLogoutAt(logoutTime);

					}
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return gamerLog;
			}

			public Gamer getGamerDetails(int gamerId) {

				Gamer opponent = null;
				String gamerSql = "select * from tb_user where id=" + gamerId;
				try {
					stmt = conn.prepareStatement(gamerSql);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						int id = Integer.parseInt(rs.getString("id"));
						String name = rs.getString("gamerName");
						String email = rs.getString("emailAddress");
						String password = rs.getString("password");
						String fullName = rs.getString("fullName");
						String gender = rs.getString("gender");
						Date joinedDate = rs.getDate("createdAt");

						opponent = new Gamer(id, name, email, password, fullName, gender, joinedDate);

					}
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return opponent;
			}

			public GamePlayer getPlayerDetails(int gamerId) {

				GamePlayer player2 = null;
				String gamerSql = "select * from tb_user where id=" + gamerId;
				try {
					stmt = conn.prepareStatement(gamerSql);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						int id = Integer.parseInt(rs.getString("id"));
						String name = rs.getString("gamerName");
						String email = rs.getString("emailAddress");
						String password = rs.getString("password");
						String fullName = rs.getString("fullName");
						String gender = rs.getString("gender");
						Date joinedDate = rs.getDate("createdAt");

						Gamer opponent = new Gamer(id, name, email, password, fullName, gender, joinedDate);
						player2 = new GamePlayer();
						player2.setGamer(opponent);

					}
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return player2;
			}

			// Start a new game and track record
			public Game startAGame(GamePlayer player1, GamePlayer player2) {

				System.out.println("Let's start a game!");
				Game game = new Game();

				// game.setStartTime(CLocalDateTime.now());
				game.addPlayer(player2);
				game.addPlayer(player2);
				return game;
			}

			public int insertGameHistory(Game game, int guessedNo, long timeTaken) {
				int retInt = 0;
				try {
					Statement stmt = conn.createStatement();
					int gameId = game.getGameId();

					int gamerId = game.getPlayers().get(0).getGamer().getId();

					System.out.println("History details : " + gameId + ", " + gamerId + ", " + guessedNo + ". " + timeTaken);

					String insertGmHisQry = "insert into tb_game_history(gameId, gamerId, guessedNumber, timeSpent)"
							+ "values ('" + gameId + "', '" + gamerId + "', '" + guessedNo + "', '" + timeTaken + "') ";

					retInt = stmt.executeUpdate(insertGmHisQry, Statement.RETURN_GENERATED_KEYS);
					stmt.close();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return retInt;
			}

			public int createNewGame(Game game, LocalDateTime startTime) {
				int retInt = 0;
				int generatedKey = 0;
				try {
					Statement stmt = conn.createStatement();
					// int gameId = game.getGameId();
					SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

					System.out.println("startime and endTime" + startTime);
					String createGmQry = "insert into tb_game(startTime) " + "values ('"
							+ f.format(Date.valueOf(startTime.toLocalDate())) + "') ";

					stmt.executeUpdate(createGmQry, Statement.RETURN_GENERATED_KEYS);

					ResultSet rs = stmt.getGeneratedKeys();

					if (rs.next()) {
						generatedKey = rs.getInt(1);
					}
					System.out.println(" generatedKey : " + generatedKey);
					System.out.println("Game id : " + retInt);
					stmt.close();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return generatedKey;
			}

			public int createNewPlayer(Game game) {
				int retInt = 0;
				try {
					for (int i = 0; i < 2; i++) {
						Statement stmt = conn.createStatement();
						int gameId = game.getGameId();
						int gamerId = game.getPlayers().get(i).getGamer().getId();

						String createPlayer = "insert into tb_game_player(gameId,gamerId) " + "values ('" + gameId + "','"
								+ gamerId + "')";

						retInt = stmt.executeUpdate(createPlayer, Statement.RETURN_GENERATED_KEYS);

						System.out.println("Game id : " + retInt);
					}
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return retInt;
			}

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public static String toJson(Object obj) {

				ObjectMapper mapper = new ObjectMapper();

				String jsonInString = "";
				try {
					jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(jsonInString);
				return jsonInString;
			}

			@SuppressWarnings({ "rawtypes", "unchecked" })
			public static JSONArray toJson(List list) {
				JSONArray jsonArray = new JSONArray();
				for (Object obj : list) {
					if (obj instanceof List) {
						jsonArray.add(toJson((List) obj));
					} else {
						jsonArray.add(obj);
					}
				}
				return jsonArray;
			}

}
