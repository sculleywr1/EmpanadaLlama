package dao;

import models.Inspector;

public interface InspectorDao {

	public Inspector getInspectorById(int index);
	public boolean insertInspector(String userName, String password);
	public Inspector getInspectorByUserPass(String userName, String password);
}
