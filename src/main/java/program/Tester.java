package program;
import dao.FridgeDaoImp;
import dao.InspectorDaoImp;
import dao.RestaurantDaoImp;
import models.Fridge;
import models.Inspector;
import models.Restaurant;
import service.InspectorServiceImp;
import service.InspectorService;

public class Tester {
	public static void main(String[] args) {
		InspectorService service = new InspectorServiceImp();
		InspectorDaoImp dao = new InspectorDaoImp();
		Inspector i = service.getInspectorByUserPass("malia", "baswell");
		FridgeDaoImp fdao = new FridgeDaoImp();
		RestaurantDaoImp rdao = new RestaurantDaoImp();
//		dao.insertInspector(i.getUserName(), i.getPassword());
		Restaurant r = rdao.getRestaurantByUserPass("John", "Jacob");
		
		
		
		System.out.println("congratulations, the program has finished");
	}
}
