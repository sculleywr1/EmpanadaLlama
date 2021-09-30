package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Inspector;
import models.Restaurant;

public class InspectorDaoImp implements InspectorDao {

	@Override
	public Inspector getInspectorById(int index) {
		Inspector i = null;
		try (Connection conn = CustomClassFactory.getConnection()) {

			String sql = "SELECT * FROM inspector WHERE inspector_id = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, index);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				i = new Inspector(rs.getInt(1), rs.getString(2), rs.getString(3));
				System.out.println(i.toString());
			}

		} catch (SQLException e) {
			System.out.println("Sorry, database was not contacted. Bring your developer coffee");
			e.printStackTrace();
			return null;
		}
		return i;
	}

	@Override
	public boolean insertInspector(String userName, String password) {
		try (Connection conn = CustomClassFactory.getConnection()) {

			String sql = "INSERT INTO inspector (inspector_name, inspector_pass) VALUES (?,?)";
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
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public Inspector getInspectorByUserPass(String userName, String password) {
		Inspector r = null;
		try (Connection conn = CustomClassFactory.getConnection()) {

			String sql = "SELECT * FROM inspector WHERE inspector_name = ? AND inspector_pass = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				r = new Inspector(rs.getInt(1), rs.getString(2), rs.getString(3));
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
