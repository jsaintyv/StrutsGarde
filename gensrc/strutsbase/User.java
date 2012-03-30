package strutsbase;

// Generated 28 mars 2012 11:32:37 by Hibernate Tools 3.4.0.CR1

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable {

	private Integer userId;
	private String login;
	private String passwordmd5;
	private UsersGroup mainGroup;

	public User() {
	}

	public User(String login, String passwordmd5, UsersGroup mainGroup) {
		this.login = login;
		this.passwordmd5 = passwordmd5;
		this.mainGroup = mainGroup;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPasswordmd5() {
		return this.passwordmd5;
	}

	public void setPasswordmd5(String passwordmd5) {
		this.passwordmd5 = passwordmd5;
	}

	public UsersGroup getMainGroup() {
		return this.mainGroup;
	}

	public void setMainGroup(UsersGroup mainGroup) {
		this.mainGroup = mainGroup;
	}

}
