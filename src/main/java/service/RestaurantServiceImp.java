package service;

import dao.RestaurantDaoImp;
import models.Restaurant;

public class RestaurantServiceImp implements RestaurantService {

	RestaurantDaoImp myDao = new RestaurantDaoImp();
	@Override
	public Restaurant getRestaurantById(int index) {
		// TODO Auto-generated method stub
		return myDao.getRestaurantById(index);
	}

	@Override
	public boolean insertRestaurant(String userName, String password) {
		// TODO Auto-generated method stub
		return myDao.insertRestaurant(userName, password);
	}

	@Override
	public Restaurant getRestaurantByUserPass(String userName, String password) {
		// TODO Auto-generated method stub
		return myDao.getRestaurantByUserPass(userName, password);
	}

}
