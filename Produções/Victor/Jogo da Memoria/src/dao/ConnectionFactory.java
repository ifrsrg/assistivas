package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	static{
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public Connection getConnection(){
		try{
			return DriverManager.getConnection("jdbc:postgresql://localhost/Memoria", "postgres", "postgres");
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}