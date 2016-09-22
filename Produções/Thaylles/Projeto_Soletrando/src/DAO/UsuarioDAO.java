package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import DAO.DB;
import model.*;

public class UsuarioDAO{
	
	static{
		try {
			Connection c = null;
		    Statement stmt;
		    c = DriverManager.getConnection("jdbc:postgresql://localhost/soletrando", "postgres", "Thaylles");
			stmt = c.createStatement();
        	stmt.executeUpdate("CREATE TABLE IF NOT EXISTS usuarios "
        			+ "(id_usuario SERIAL PRIMARY KEY, login VARCHAR(20) UNIQUE NOT NULL, "
        			+ "email VARCHAR(50) NOT NULL, senha VARCHAR(15) NOT NULL);");
        	
        	stmt.close();
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			throw new ExceptionInInitializerError(e1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void insert(Usuario usuario){   
		Connection c = null;
    	String sql = "INSERT INTO usuarios (login, email, senha) VALUES (?,?,?)";

        try{   
        c = DriverManager.getConnection("jdbc:postgresql://localhost/soletrando", "postgres", "Thaylles");
        
            	
            	PreparedStatement cmd = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            	cmd.setString(1, usuario.getLogin());
                cmd.setString(2, usuario.getEmail());
                cmd.setString(3, usuario.getSenha());
                cmd.execute();
                
                ResultSet key = cmd.getGeneratedKeys();
    			if (key.next()) {
    				usuario.setId(key.getInt(1));
    			}
                System.out.print("cadastrado");
                cmd.close();
                c.close();
        }
        catch (Exception e){  
        	throw new RuntimeException(e);
        }
       
    }
	
	public static Usuario select(int id){   
		Connection c = null;
        ResultSetMetaData rsmd = null;
        int row=0;

        try{   
            c = DriverManager.getConnection("jdbc:postgresql://localhost/soletrando","postgres","Thaylles");
            String sql = "SELECT login, email, senha FROM usuarios WHERE id_usuario = ?;";

			PreparedStatement cmd = c.prepareStatement(sql);

			cmd.setInt(1, id);
			ResultSet rs = cmd.executeQuery(); 
			
			if(rs.next()){
				Usuario usuario = new Usuario();
				usuario.setId(id);
				usuario.setLogin(rs.getString("login"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				System.out.println(usuario.getLogin());
				System.out.println(usuario.getSenha());
				return usuario;
			}
			
			return null;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }

	public static int verificador(Login usuario){
		Connection c = null;
        Statement s = null;
        int verificador = 0;
        
        try{   
        	
            c = DriverManager.getConnection("jdbc:postgresql://localhost/soletrando","postgres","Thaylles");
            if (c != null){   
                s = c.createStatement();
                String sql = "SELECT id_usuario FROM usuarios where login = ? and senha=?;";
                PreparedStatement cmd = c.prepareStatement(sql);
                
                cmd.setString(1, usuario.getLogin());
                cmd.setString(2, usuario.getSenha());
                
                ResultSet rs = cmd.executeQuery(); 

                while (rs.next()){  
                	verificador = rs.getInt("id_usuario");
                }
                rs.close();
                s.close();
            }
            c.close();
        }
        catch (Exception e){  
        	 e.printStackTrace();
             System.exit(1);
        }
      
        return verificador;
	}
}
