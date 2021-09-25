package models;

import java.util.ArrayList;

public class Inspector extends Account {
	
	private ArrayList<Fridge> fridges = new ArrayList<Fridge>();

	public Inspector(String name, String pass, String accountType) {
		super(name, pass, accountType);
	}

	public ArrayList<Fridge> getFridges() {
		return fridges;
	}

	public void setFridges(ArrayList<Fridge> fridges) {
		this.fridges = fridges;
	}
	public void addFridge(Fridge f)
	{
		fridges.add(f);
	}

}
