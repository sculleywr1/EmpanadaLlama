package models;

public class Account {

	private int accountId;
	private String userName;
	private String password;
	
	public Account() {
		// TODO Auto-generated constructor stub
	}
	
	public Account(int accountId, String userName, String password) {
		super();
		this.accountId = accountId;
		this.userName = userName;
		this.password = password;
	}

	public Account(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", userName=" + userName + ", password=" + password + "]";
	}
	
	
}
