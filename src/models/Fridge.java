package models;

import java.util.ArrayList;

public class Fridge {
	
	private String[] inventory = new String[3];
	private ArrayList<Inspector> inspector = new ArrayList<Inspector>();
	
	

	public Fridge(String[] inventory) {
		super();
		this.inventory = inventory;
	}

	public void setInventory(String[] inventory) {
		this.inventory = inventory;
	}

	
	
	public ArrayList<Inspector> getInspector() {
		return inspector;
	}

	public void setInspector(ArrayList<Inspector> inspector) {
		this.inspector = inspector;
	}
	public void addInspector(Inspector i)
	{
		inspector.add(i);
		System.out.println("Inspector " + i.name + " has been added to this Fridge. Inspectors allowed in this fridge are: ");
	}
	
	public void removeInspector(Inspector n)
	{
		int i = inspector.indexOf(n);
		inspector.remove(i);
		System.out.println("Inspector has been removed from this fridge. Inspectors allowed in this fridge are: ");
	}

	public String[] getInventory() {
		
		System.out.println("The items in the Fridge selected are: ");
		for(String item : inventory)
		{
			System.out.println(item);
		}
		return inventory;
	}

	public Fridge addItem(String desire)
	{
		for (int i=0; i < inventory.length; i++)
		{
			if (inventory[i] == null)
			{
				System.out.println(i);
				inventory[i] = desire;
				System.out.println("Your " + desire + " has been stored in the fridge. The contents of the fridge are now: ");
				for (String item : inventory)
				{
					System.out.println(item);
				}
				Fridge f = new Fridge(inventory);
				return f;
			}
		}
		System.out.println("This fridge is full");
		return null;
	}
	
	public boolean removeItem(String desire)
	{
		for (int i = 0; i <= inventory.length; i++)
		{
			if (inventory[i] != null && i==2)
			{
				System.out.println("The item you requested cannot be found in this fridge. Check spelling and case and try again");
				return false;
			}
			else if (inventory[i].equals(desire))
			{
				inventory[i] = null;
				System.out.println("The item you requested has been removed. The remaining items in the fridge are: ");
				for (String item : inventory)
				{
					System.out.println(item);
				}
				return true;
			}
			
		}
		System.out.println("Something has gone horribly wrong if you see this message. The removeItem method is not working. Please contact the developer.");
		return false;
	}
	

	
}
