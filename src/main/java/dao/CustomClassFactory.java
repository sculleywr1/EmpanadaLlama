package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomClassFactory {
	public static String url = "jdbc:postgresql://104.154.74.145/restaurantdb";
	public static String username = "postgres";
	public static String password = "duergar1!";
	
	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(url, username, password);
	}
}
