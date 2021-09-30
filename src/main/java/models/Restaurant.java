package models;

import java.util.ArrayList;
import java.util.List;

public class Restaurant extends Account {

	List<Fridge> refrigerators = new ArrayList<>();
	public Restaurant() {
		// TODO Auto-generated constructor stub
	}
	
	public Restaurant(int accountId, String userName, String password) {
		super(accountId, userName, password);
		// TODO Auto-generated constructor stub
	}

	public Restaurant(String userName, String password) {
		super(userName, password);
		// TODO Auto-generated constructor stub
	}

	public List<Fridge> getRefrigerators() {
		return refrigerators;
	}
	public void setRefrigerators(List<Fridge> refrigerators) {
		this.refrigerators = refrigerators;
	}
	
}
