package service;

import models.Restaurant;

public interface RestaurantService {

	public Restaurant getRestaurantById(int index);
	public boolean insertRestaurant(String userName, String password);
	public Restaurant getRestaurantByUserPass(String userName, String password);
}
