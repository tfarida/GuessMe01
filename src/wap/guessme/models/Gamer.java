package wap.guessme.models;

import java.util.Date;

/**
 * **
 * 
 * @author titin: Desription: bean to model app user
 *
 */
// Written by Titin on 20th March 2018- Model Class
public class Gamer {
	private int id;
	private String gamerName;
	private String emailAddress;
	private String password;
	private String fullName;
	private String gender;
	private Date createdAt;
	private String aboutMe;
	private int secretNumber;
	private String aboutSecretNumber;
	private String imagePath;
	private String imageFileLength;
	private Date lastModifiedAt;

	public Gamer() {

	}

	public void setDefault() {
		this.aboutMe = "";
		this.secretNumber = 0;
		this.aboutSecretNumber = "";
		this.imagePath = "";
		this.imageFileLength = "";
	}

	public Gamer(int id, String gamerName, String emailAddress, String password, String fullName, String gender,
			Date createdAt) {
		this.id = id;
		this.gamerName = gamerName;
		this.emailAddress = emailAddress;
		this.password = password;
		this.fullName = fullName;
		this.gender = gender;
		this.createdAt = createdAt;
		setDefault();
	}

	public Gamer(String gamerName, String emailAddress, String password, String fullName, String gender,
			Date createdAt) {
		this.gamerName = gamerName;
		this.emailAddress = emailAddress;
		this.password = password;
		this.fullName = fullName;
		this.gender = gender;
		this.createdAt = createdAt;
		setDefault();
	}

	public Gamer(String gamerName, String emailAddress, String fullName, String gender, Date createdAt) {
		this.gamerName = gamerName;
		this.emailAddress = emailAddress;
		this.password = password;
		this.fullName = fullName;
		this.gender = gender;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGamerName() {
		return gamerName;
	}

	public void setGamerName(String gamerName) {
		this.gamerName = gamerName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public int getSecretNumber() {
		return secretNumber;
	}

	public void setSecretNumber(int secretNumber) {
		this.secretNumber = secretNumber;
	}

	public String getAboutSecretNumber() {
		return aboutSecretNumber;
	}

	public void setAboutSecretNumber(String aboutSecretNumber) {
		this.aboutSecretNumber = aboutSecretNumber;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImageFileLength() {
		return imageFileLength;
	}

	public void setImageFileLength(String imageFileLength) {
		this.imageFileLength = imageFileLength;
	}

	public Date getLastModifiedAt() {
		return lastModifiedAt;
	}

	public void setLastModifiedAt(Date lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

}
