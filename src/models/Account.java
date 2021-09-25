package models;


public class Account {

	String name, pass, accountType;
	public Account(String name, String pass, String accountType) {
		this.name = name;
		this.pass = pass;
		this.accountType = accountType;
	}
	public String getName() {
		return name;
	}
	private void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	private void setPass(String pass) {
		this.pass = pass;
	}
	public String getAccountType() {
		return accountType;
	}
	private void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public void createUser(String n, String p, String a)
	{
		setName(n);
		setPass(p);
		setAccountType(a);
	}
	
	@Override
	public String toString() {
		return "Account [name=" + name + ", pass=" + pass + ", accountType=" + accountType + "]";
	}
}
