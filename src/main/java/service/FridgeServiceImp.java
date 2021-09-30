package service;

import java.util.List;

import dao.FridgeDaoImp;
import models.Fridge;
import models.Inspector;
import models.Restaurant;

public class FridgeServiceImp implements FridgeService {

	FridgeDaoImp myDao = new FridgeDaoImp();
	@Override
	public boolean insertFridge(Fridge myFridge, int index) {
		// TODO Auto-generated method stub
		return myDao.insertFridge(myFridge, index);
	}

	@Override
	public List<Fridge> selectAllFridges() {
		// TODO Auto-generated method stub
		return myDao.selectAllFridges();
	}

	@Override
	public List<Fridge> selectAllFridgesFromRestaurant(Restaurant r) {
		// TODO Auto-generated method stub
		return myDao.selectAllFridgesFromRestaurant(r);
	}

	@Override
	public List<Fridge> selectAllFridgesRestaurantAndInspector(Restaurant r, Inspector i) {
		// TODO Auto-generated method stub
		return myDao.selectAllFridgesRestaurantAndInspector(r, i);
	}

	@Override
	public Fridge getSpecificFridge(int i) {
		// TODO Auto-generated method stub
		return myDao.getSpecificFridge(i);
	}

	@Override
	public boolean addFoodToFridge(int index, String food) {
		// TODO Auto-generated method stub
		return myDao.addFoodToFridge(index, food);
	}

	@Override
	public boolean removeFoodFromFridge(int index, String food) {
		// TODO Auto-generated method stub
		return myDao.removeFoodFromFridge(index, food);
	}

	@Override
	public boolean addInspectorToFridge(Fridge f, Inspector i) {
		// TODO Auto-generated method stub
		return myDao.addInspectorToFridge(f, i);
	}

	@Override
	public boolean removeInspectorFromFridge(Fridge f, Inspector i) {
		// TODO Auto-generated method stub
		return myDao.removeInspectorFromFridge(f, i);
	}

	@Override
	public boolean deleteFridge(int index) {
		// TODO Auto-generated method stub
		return myDao.deleteFridge(index);
	}

}
