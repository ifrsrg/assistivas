package DAO;

import java.sql.*;

public class UploadDAO {
	static{
		try {
			Connection c = null;
		    Statement stmt;
		    c = DriverManager.getConnection("jdbc:postgresql://localhost/soletrando", "postgres", "null");
			stmt = c.createStatement();
        	stmt.executeUpdate("CREATE TABLE IF NOT EXISTS palavras"
        			+ "(palavra varchar(20) UNIQUE NOT NULL, dificuldade INTEGER, caminho VARCHAR(50));");
        	
        	stmt.close();
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			throw new ExceptionInInitializerError(e1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void insertPalavras(String palavra, int dificuldade, String caminho){   
		Connection c = null;
    	String sql = "INSERT INTO palavras VALUES (?,?,?)";

        try{   
        c = DriverManager.getConnection("jdbc:postgresql://localhost/soletrando", "postgres", "null");
        	
        PreparedStatement cmd = c.prepareStatement(sql);
            	
        cmd.setString(1, palavra);
        cmd.setInt(2, dificuldade);
        cmd.setString(3, caminho);
            	
        cmd.close();
        c.close();
        
        }
        catch (Exception e){  
        	throw new RuntimeException(e);
        }
       
    }
}
