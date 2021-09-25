package models;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		String name = "Trevin";
		String pass = "king";
		String accountType = "restaurant";
		ArrayList<Fridge> fridges = new ArrayList<Fridge>();
		ArrayList<Inspector> inspectors = new ArrayList<Inspector>();
		String[] ingredients = {null, null, null};
		
		
		Inspector john = new Inspector("john", "speck", "inspector");
		inspectors.add(john);
		Fridge one = new Fridge(ingredients);
		fridges.add(one);
		
		Restaurant r = new Restaurant(name, pass, accountType, fridges);
		{
			
		}
		
		r.getFridgeInventory(0);
		r.addFridge(one);
		r.getFridgeInventory(0);
		r.addItem(0, "Pickles");
		r.addItem(0, "carrots");
		r.addInspectorToFridge(0, john);
		r.removeInspectorFromFridge(0, john);
		
	}

}
