package service;

import models.Inspector;

public interface InspectorService {

	public Inspector getInspectorById(int index);
	public boolean insertInspector(String userName, String password);
	public Inspector getInspectorByUserPass(String userName, String password);
}
