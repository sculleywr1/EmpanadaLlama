package dao;

import models.Restaurant;

public interface RestaurantDao {
	
	public Restaurant getRestaurantById(int index);
	public boolean insertRestaurant(String userName, String password);
	public Restaurant getRestaurantByUserPass(String userName, String password);
}
