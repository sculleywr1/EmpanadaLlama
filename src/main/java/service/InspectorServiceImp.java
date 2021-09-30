package service;

import dao.InspectorDaoImp;
import models.Inspector;

public class InspectorServiceImp implements InspectorService {

	InspectorDaoImp dao = new InspectorDaoImp();
	@Override
	public Inspector getInspectorById(int index) {
		// TODO Auto-generated method stub
		return dao.getInspectorById(index);
	}

	@Override
	public boolean insertInspector(String userName, String password) {
		// TODO Auto-generated method stub
		return dao.insertInspector(userName, password);
	}

	@Override
	public Inspector getInspectorByUserPass(String userName, String password) {
		// TODO Auto-generated method stub
		return dao.getInspectorByUserPass(userName, password);
	}

}
