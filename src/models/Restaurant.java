package models;

import java.util.ArrayList;

public class Restaurant extends Account {
	ArrayList<Fridge> refrigerators = new ArrayList<Fridge>();
	
	public Restaurant(String name, String pass, String accountType) {
		super(name, pass, accountType);
		// TODO Auto-generated constructor stub
	}

	public Restaurant(String name, String pass, String accountType, ArrayList<Fridge> refrigerators) {
		super(name, pass, accountType);
		this.refrigerators = refrigerators;
	}

	public ArrayList<Fridge> getRefrigerators() {
		return refrigerators;
	}

	public void setRefrigerators(ArrayList<Fridge> refrigerators) {
		this.refrigerators = refrigerators;
	}
	
	public void getFridgeInventory(int i)
	{
		Fridge lookIn = refrigerators.get(i);
		lookIn.getInventory();
	}
	
	public void addInspectorToFridge(int i, Inspector j)
	{
		refrigerators.get(i).addInspector(j);
		j.addFridge(refrigerators.get(i));
		Fridge a = refrigerators.get(i);
		
		for(Inspector k : a.getInspector())
		{
			System.out.println(k.name);
		}
		
	}	
	
	public void removeInspectorFromFridge(int i, Inspector j)
	{
		refrigerators.get(i).removeInspector(j);
		Fridge a = refrigerators.get(i);
		for (Inspector k : a.getInspector())
		{
			System.out.println(k.name);
		}
	}
	
	public void addFridge(Fridge f)
	{
		refrigerators.add(f);
		System.out.println("The refrigerator was added. There are now " + (refrigerators.lastIndexOf(f) + 1) + " refrigerators in this restaurant.");
	}
	public void removeFridge (int i)
	{
		refrigerators.remove(i);
		System.out.println("The refrigerator was removed! There are now " + refrigerators.size() + " refrigerators in this restaurant.");
	}
	
	public void removeItem (int i, String s )
	{
		refrigerators.get(i).removeItem(s);
		
	}
	
	public void addItem (int i, String s)
	{
		refrigerators.get(i).addItem(s);
	}
	
	public boolean inspectorAllowed(int a, Inspector i)
	{
		name = i.name;
		pass = i.pass;
		ArrayList<Inspector> inspectors = refrigerators.get(a).getInspector();
		Inspector candidate = inspectors.stream()
				  .filter(inspector -> inspector.name.equals(name))
				  .findAny()
				  .orElse(null);
		
		if (candidate == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	


}
