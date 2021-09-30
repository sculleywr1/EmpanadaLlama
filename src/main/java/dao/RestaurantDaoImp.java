package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Restaurant;

public class RestaurantDaoImp implements RestaurantDao {

	@Override
	public Restaurant getRestaurantById(int index) {
		Restaurant r = null;
		try (Connection conn = CustomClassFactory.getConnection()) {

			String sql = "SELECT * FROM restaurant WHERE restaurant_id = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, index);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				r = new Restaurant(rs.getInt(1), rs.getString(2), rs.getString(3));
			}

		} catch (SQLException e) {
			System.out.println("Sorry, database was not contacted. Bring your developer coffee");
			e.printStackTrace();
			return null;
		}
		return r;
	}

	@Override
	public boolean insertRestaurant(String userName, String password) {
		// TODO Auto-generated method stub
		try (Connection conn = CustomClassFactory.getConnection()) {

			String sql = "INSERT INTO restaurant (restaurant_name, restaurant_pass) VALUES (?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);

			int i = ps.executeUpdate(); // <---update not query. this line is what sends the information to the DB
			if (i == 0) {
				System.out.println("Sorry, database was not updated. Returning to menu");
				return false;
			}

		} catch (SQLException e) {
			System.out.println("Sorry, database was not contacted. Bring your developer coffee");
			return false;
		}

		return true;
	}

	@Override
	public Restaurant getRestaurantByUserPass(String userName, String password) {
		Restaurant r = null;
		try (Connection conn = CustomClassFactory.getConnection()) {

			String sql = "SELECT * FROM restaurant WHERE restaurant_name = ? AND restaurant_pass = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				r = new Restaurant(rs.getInt(1), rs.getString(2), rs.getString(3));
				System.out.println(r.toString());
			}
			else
			{
				System.out.println("User name or password incorrect or account doesn't exist.");
			}

		} catch (SQLException e) {
			System.out.println("Sorry, database was not contacted. Bring your developer coffee");
			return null;
		}
		return r;
	}

}
