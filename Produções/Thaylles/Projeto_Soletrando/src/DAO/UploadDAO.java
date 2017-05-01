package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Palavras;
import model.Upload;

public class UploadDAO {
	private static DB db = new DB();
	static{
		try(Connection c = db.getConnection()){
		    Statement stmt;
			stmt = c.createStatement();
        	stmt.executeUpdate("CREATE TABLE IF NOT EXISTS palavras"
        			+ "(palavra varchar(46) UNIQUE NOT NULL, dificuldade INTEGER, caminho VARCHAR(100));");
        	
        	stmt.close();
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			throw new ExceptionInInitializerError(e1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void insertPalavras(Upload palavra){   
		String sql = "INSERT INTO palavras VALUES (?,?,?)";

        try(Connection c = db.getConnection()){ 
        	
        PreparedStatement cmd = c.prepareStatement(sql);
            	
        cmd.setString(1, palavra.getPalavra());
        cmd.setInt(2, palavra.getDificuldade());
        cmd.setString(3, palavra.getCaminho());
        
        cmd.execute();
            	
        cmd.close();
        c.close();
        
        }
        catch (Exception e){  
        	throw new RuntimeException(e);
        }
       
    }
	
	public static List<Palavras> selectAll() {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT * FROM Palavras";
			
			PreparedStatement cmd = con.prepareStatement(sql);
		
			ResultSet rs = cmd.executeQuery(); // consulta devolve um ResultSet
			
			List<Palavras> Palavra = new ArrayList();
			
			while (rs.next()) {
				Palavras Palavras = new Palavras();
				Palavras.setDificuldade(rs.getInt("dificuldade"));
				Palavras.setPalavra(rs.getString("palavra"));
				Palavras.setCaminho(rs.getString("caminho"));
				Palavra.add(Palavras);
			}
			
			return Palavra;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	public static String selectRota(String palavra){   
	
        try(Connection c = db.getConnection()){ 
        	String sql = "SELECT caminho FROM palavras WHERE palavra = ?;";

			PreparedStatement cmd = c.prepareStatement(sql);

			cmd.setString(1, palavra);
			ResultSet rs = cmd.executeQuery(); 
			
			if(rs.next()){
				return rs.getString("palavra");
			}
			
			return null;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
