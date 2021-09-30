package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Fridge;
import models.Inspector;
import models.Restaurant;

public class FridgeDaoImp implements FridgeDao {

	@Override
	public boolean insertFridge(Fridge myFridge, int index) {
		try (Connection conn = CustomClassFactory.getConnection()) {

			String sql = "INSERT INTO fridge (item_1, item_2, item_3) VALUES (?,?,?)";
			String sql2 = "INSERT INTO fridge_restaurant_junction VALUES (?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			PreparedStatement ps2 = conn.prepareStatement(sql2);

			ps.setString(1, myFridge.getItem1());
			ps.setString(2, myFridge.getItem2());
			ps.setString(3, myFridge.getItem3());

			int i = ps.executeUpdate(); // <---update not query. this line is what sends the information to the DB
			if (i == 0) {
				System.out.println("Sorry, database was not updated. Returning to menu");
				return false;
			}
			List<Fridge> f = new ArrayList<Fridge>();
			f = selectAllFridges();
			int fid = f.get(f.size()-1).getFridgeId();
			ps2.setInt(1, fid);
			ps2.setInt(2, index);
			int i1 = ps2.executeUpdate(); // <---update not query. this line is what sends the information to the DB
			if (i1 == 0) {
				System.out.println("Sorry, junction database was not updated. Returning to menu");
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public List<Fridge> selectAllFridgesFromRestaurant(Restaurant r) {
		List<Fridge> fridgeList = new ArrayList<>();
		RestaurantDaoImp dao = new RestaurantDaoImp();
		int a = dao.getRestaurantByUserPass(r.getUserName(), r.getPassword()).getAccountId();
		System.out.println(a);

		try (Connection conn = CustomClassFactory.getConnection()) {

			String sql = "SELECT fridge_id, item_1, item_2, item_3 FROM fridge JOIN fridge_restaurant_junction b ON fridge.fridge_id = b.my_fridge_id WHERE my_restaurant_id=?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a);

			ResultSet rs = ps.executeQuery(); // <----query not update. This line sends our statement to the DB

			while (rs.next()) {
				fridgeList.add(new Fridge(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}

		} catch (SQLException e) {
			System.out.println("Error communicating with database. Returning to main menu.");
			e.printStackTrace();
			return null;
		}

		return fridgeList;
	}

	@Override
	public List<Fridge> selectAllFridgesRestaurantAndInspector(Restaurant r, Inspector i) {
		List<Fridge> fridgeList = new ArrayList<>();
		RestaurantDaoImp rdao = new RestaurantDaoImp();
		InspectorDaoImp dao= new InspectorDaoImp();
		int a = rdao.getRestaurantByUserPass(r.getUserName(), r.getPassword()).getAccountId();
		int b = dao.getInspectorByUserPass(i.getUserName(), i.getPassword()).getAccountId();
		
		try (Connection conn = CustomClassFactory.getConnection()) {

			///// STEP 1: go find all the superhumans
			String sql = "SELECT fridge_id, item_1, item_2, item_3 FROM fridge JOIN fridge_inspector_junction a ON fridge.fridge_id = a.my_fridge_id JOIN fridge_restaurant_junction b ON fridge.fridge_id = b.my_fridge_id WHERE a.my_inspector_id = ? AND b.my_restaurant_id = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, b);
			ps.setInt(2, a);

			ResultSet rs = ps.executeQuery(); // <----query not update. This line sends our statement to the DB

			while (rs.next()) {
				fridgeList.add(new Fridge(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return fridgeList;
	}

	@Override
	public Fridge getSpecificFridge(int i) {
		Fridge f = null;
		try (Connection conn = CustomClassFactory.getConnection()) {

			String sql = "SELECT fridge_id, item_1, item_2, item_3 FROM fridge WHERE fridge_id = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				f = new Fridge(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return f;
	}

	@Override
	public boolean addFoodToFridge(int index, String food) {
		Fridge f;
		f = getSpecificFridge(index);
		try (Connection conn = CustomClassFactory.getConnection()) {

			if (f.getItem1() == null) {
				String sql = "UPDATE fridge SET item_1 = ? WHERE fridge_id = ?;";

				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, food);
				ps.setInt(2, f.getFridgeId());

				int i = ps.executeUpdate();
				if (i == 0) {
					System.out.println("The database was not updated. Please contact the developer and bring coffee");
					return false;
				} else {
					System.out.println("the " + food + " was added to the first shelf");
				}
			} else if (f.getItem2() == null) {
				String sql = "UPDATE fridge SET item_2 = ? WHERE fridge_id = ?;";

				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, food);
				ps.setInt(2, f.getFridgeId());

				int i = ps.executeUpdate();
				if (i == 0) {
					System.out.println("The database was not updated. Please contact the developer and bring coffee");
					return false;
				} else {
					System.out.println("the " + food + " was added to the second shelf");
				}
			} else if (f.getItem3() == null) {
				String sql = "UPDATE fridge SET item_3 = ? WHERE fridge_id = ?;";

				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, food);
				ps.setInt(2, f.getFridgeId());

				int i = ps.executeUpdate();
				if (i == 0) {
					System.out.println("The database was not updated. Please contact the developer and bring coffee");
					return false;
				} else {
					System.out.println("the " + food + " was added to the third shelf");
				}
			} else {
				System.out.println("This fridge is full. Please select another fridge.");
			}

		} catch (SQLException e) {
			System.out.println("Sorry. There was a database error");
			return false;
		}
		return true;

	}

	@Override
	public boolean removeFoodFromFridge(int index, String food) {
		Fridge f;
		f = getSpecificFridge(index);
		try (Connection conn = CustomClassFactory.getConnection()) {

			if (f.getItem1().equals(food)) {
				String sql = "UPDATE fridge SET item_1 = ? WHERE fridge_id = ?;";

				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, null);
				ps.setInt(2, f.getFridgeId());

				int i = ps.executeUpdate();
				if (i == 0) {
					System.out.println("The database was not updated. Please contact the developer and bring coffee");
					return false;
				} else {
					System.out.println("the " + food + " was removed from the first shelf");
				}
			} else if (f.getItem2().equals(food)) {
				String sql = "UPDATE fridge SET item_2 = ? WHERE fridge_id = ?;";

				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, null);
				ps.setInt(2, f.getFridgeId());

				int i = ps.executeUpdate();
				if (i == 0) {
					System.out.println("The database was not updated. Please contact the developer and bring coffee");
					return false;
				} else {
					System.out.println("the " + food + " was removed from the second shelf");
				}
			} else if (f.getItem3().equals(food)) {
				String sql = "UPDATE fridge SET item_3 = ? WHERE fridge_id = ?;";

				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, null);
				ps.setInt(2, f.getFridgeId());

				int i = ps.executeUpdate();
				if (i == 0) {
					System.out.println("The database was not updated. Please contact the developer and bring coffee");
					return false;
				} else {
					System.out.println("the " + food + " was removed from the third shelf");
				}
			} else {
				System.out.println(
						"This item is not in this fridge. Please check your spelling or select another fridge.");
			}

		} catch (SQLException e) {
			System.out.println("Sorry. There was a database error");
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteFridge(int index) {
		if (getSpecificFridge(index).getItem1() != null || getSpecificFridge(index).getItem2() != null || getSpecificFridge(index).getItem3() != null)
		{
			System.out.println("Sorry. There is food in that fridge. Please move the food to another fridge");
		}
		else if (getSpecificFridge(index) == null)
		{
			System.out.println("Sorry, there is no fridge by that index number. Returning to main menu!");
		}
		try (Connection conn = CustomClassFactory.getConnection()) {
			String sql = "DELETE FROM fridge WHERE fridge_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, index);

			int i = ps.executeUpdate(); // <---update not query. this line is what sends the information to the DB
			if (i == 0) {
				System.out.println("Sorry, database was not updated. Returning to menu");
				return false;
			}

		} catch (SQLException e) {
			System.out.println(
					"There was an error with the request sent to the database. Please bring your developer coffee");
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean addInspectorToFridge(Fridge f, Inspector i) {
		int a = f.getFridgeId();
		InspectorDaoImp dao = new InspectorDaoImp();
		int b = dao.getInspectorByUserPass(i.getUserName(), i.getPassword()).getAccountId();
		try (Connection conn = CustomClassFactory.getConnection()) {
			String sql = "INSERT INTO fridge_inspector_junction (my_fridge_id, my_inspector_id) VALUES (?, ?)";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a);
			ps.setInt(2, b);

			int i1 = ps.executeUpdate(); // <---update not query. this line is what sends the information to the DB
			if (i1 == 0) {
				System.out.println("Sorry, database was not updated. Returning to menu");
				return false;
			}

		} catch (SQLException e) {
			System.out.println(
					"There was an error with the request sent to the database. Please bring your developer coffee");
			return false;
		}

		return true;
		
	}

	@Override
	public boolean removeInspectorFromFridge(Fridge f, Inspector i) {
		int a = f.getFridgeId();
		int b = i.getAccountId();
		try (Connection conn = CustomClassFactory.getConnection()) {
			String sql = "DELETE FROM fridge_inspector_junction WHERE my_fridge_id = ? AND my_inspector_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a);
			ps.setInt(2, b);

			int i1 = ps.executeUpdate(); // <---update not query. this line is what sends the information to the DB
			if (i1 == 0) {
				System.out.println("Sorry, database was not updated. Returning to menu");
				return false;
			}

		} catch (SQLException e) {
			System.out.println(
					"There was an error with the request sent to the database. Please bring your developer coffee");
			return false;
		}

		return true;
	}

	@Override
	public List<Fridge> selectAllFridges() {
		List<Fridge> fridgeList = new ArrayList<>();

		try (Connection conn = CustomClassFactory.getConnection()) {

			String sql = "SELECT * from fridge";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery(); // <----query not update. This line sends our statement to the DB

			while (rs.next()) {
				fridgeList.add(new Fridge(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}

		} catch (SQLException e) {
			System.out.println("Error communicating with database. Returning to main menu.");
			return null;
		}

		return fridgeList;
	}

	

}
