package service;

import java.util.List;

import models.Fridge;
import models.Inspector;
import models.Restaurant;

public interface FridgeService {

	//inserts
		public boolean insertFridge(Fridge myFridge, int index);
		
		//selects
		public List<Fridge> selectAllFridges();
		public List<Fridge> selectAllFridgesFromRestaurant(Restaurant r);
		public List<Fridge> selectAllFridgesRestaurantAndInspector(Restaurant r, Inspector i);
		public Fridge getSpecificFridge(int i);
		
		//updates
		public boolean addFoodToFridge(int index, String food);
		public boolean removeFoodFromFridge(int index, String food);
		public boolean addInspectorToFridge(Fridge f, Inspector i);
		public boolean removeInspectorFromFridge(Fridge f, Inspector i);
		
		//deletes
		public boolean deleteFridge(int index);
}
